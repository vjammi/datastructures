package dev.vjammi.ds.v2.dev.aws.dynamo.gsi;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.*;

public class SearchForAccountsAcrossDatePartitions {

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    static DynamoDBMapper mapper = new DynamoDBMapper(client);

    public static String BASE_TABLE_NAME = "Accounts";
    public static String CREATED_DATE_INDEX = "CreatedDateIndex";
    public static String PAYMENT_REFERENCE_INDEX = "PaymentReferenceIndex";

    public static void main(String[] args) throws Exception {
        //createTable();
        //loadData();
        query();
        // deleteTable(BASE_TABLE_NAME);
    }

    private static void query() {
        for(int i=0; i<10; i++) {
            String indexPartitionKey = "2023-02-01::" + i;
            Account account = new Account();
            //account.setAccountId("9876543230");
            // account.setBranchId("1001");
            account.setCreatedDateWithSubPartitions(indexPartitionKey);
            //account.setProcessingDatetimeWithAccountId("1675209601000::9876543211");
            queryIndex(CREATED_DATE_INDEX, indexPartitionKey, account);
        }
    }

    public static void createTable() {

        // Attribute definitions
        ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("accountId").withAttributeType("S"));
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("branchId").withAttributeType("S"));

        attributeDefinitions.add(new AttributeDefinition().withAttributeName("createdDateWithSubPartitions").withAttributeType("S"));
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("processingDatetimeWithAccountId").withAttributeType("S"));

        attributeDefinitions.add(new AttributeDefinition().withAttributeName("paymentReference").withAttributeType("S"));
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("createdDate").withAttributeType("S"));

        // Key schema for table
        ArrayList<KeySchemaElement> baseTableKeySchema = new ArrayList<KeySchemaElement>();
        baseTableKeySchema.add(new KeySchemaElement().withAttributeName("accountId").withKeyType(KeyType.HASH)); // Partition key
        baseTableKeySchema.add(new KeySchemaElement().withAttributeName("branchId").withKeyType(KeyType.RANGE)); // Sort key  // TODO: BranchId

        // Initial provisioned throughput settings for the indexes
        ProvisionedThroughput indexProvisionedThroughput = new ProvisionedThroughput()
                .withReadCapacityUnits(1L)
                .withWriteCapacityUnits(1L);

        // CreateDateIndex
        GlobalSecondaryIndex createDateIndex = new GlobalSecondaryIndex()
                .withIndexName(CREATED_DATE_INDEX)
                .withProvisionedThroughput(indexProvisionedThroughput)
                .withKeySchema(new KeySchemaElement().withAttributeName("createdDateWithSubPartitions").withKeyType(KeyType.HASH), // Partition key  //  2013-11-15-1...2013-11-15-N - N Partitions for each day
                           new KeySchemaElement().withAttributeName("processingDatetimeWithAccountId").withKeyType(KeyType.RANGE))   // Sort key
                .withProjection(new Projection()
                    .withProjectionType("KEYS_ONLY"));

        // ReferenceNumberIndex
        GlobalSecondaryIndex referenceNumberIndex = new GlobalSecondaryIndex()
                .withIndexName(PAYMENT_REFERENCE_INDEX)
                .withProvisionedThroughput(indexProvisionedThroughput)
                .withKeySchema(new KeySchemaElement().withAttributeName("paymentReference").withKeyType(KeyType.HASH), // Partition key
                           new KeySchemaElement().withAttributeName("createdDate").withKeyType(KeyType.RANGE))    // Sort key
                .withProjection(new Projection()
                    .withProjectionType("INCLUDE")
                    .withNonKeyAttributes("accountId", "branchId"));

        CreateTableRequest createTableRequest = new CreateTableRequest()
                .withTableName(BASE_TABLE_NAME)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits((long) 1)
                        .withWriteCapacityUnits((long) 1))
                .withAttributeDefinitions(attributeDefinitions)
                .withKeySchema(baseTableKeySchema)
                .withGlobalSecondaryIndexes(createDateIndex, referenceNumberIndex);

        System.out.println("Creating table " + BASE_TABLE_NAME + "...");
        dynamoDB.createTable(createTableRequest);

        // Wait for table to become active
        System.out.println("Waiting for " + BASE_TABLE_NAME + " to become ACTIVE...");
        try {
            Table table = dynamoDB.getTable(BASE_TABLE_NAME);
            table.waitForActive();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //https://stackoverflow.com/questions/21730183/dynamodb-global-secondary-index-with-exclusive-start-key
    //https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Query.Pagination.html
    public static void queryIndex(String indexName, String indexPartitionKey, Account account) { //2023-02-01::7
        System.out.println("\n***********************************************************\n");
        System.out.println("Querying Dynamo GSI " + indexName + " for partition " +indexPartitionKey);

        DynamoDBQueryExpression<Account> queryExpression = getQueryExpression(indexPartitionKey, account,null);
        int pageNum = 0;
        do {
            QueryResultPage<Account> resultPage = mapper.queryPage(Account.class, queryExpression);
            List<Account> results = resultPage.getResults();
            printPageResults(pageNum, results);
            setExclusiveStartKey(queryExpression, pageNum, resultPage);
            pageNum++;
        }while(queryExpression.getExclusiveStartKey() != null);
    }

    private static void printPageResults(int pageNum, List<Account> results) {
        System.out.println("Prinrting results for page# " + pageNum);
        for (int i = 0; i< results.size(); i++) {
            System.out.println("  " +i +" - "+ results.get(i).toString());
        }
    }

    private static void setExclusiveStartKey(DynamoDBQueryExpression<Account> queryExpression, int pageNum, QueryResultPage<Account> accountQueryResultPage) {
        Map<String, AttributeValue> lastEvaluatedKey = accountQueryResultPage.getLastEvaluatedKey();
        System.out.println("Last Evaluated Key: " +lastEvaluatedKey);
        queryExpression.setExclusiveStartKey(lastEvaluatedKey);
    }

    private static DynamoDBQueryExpression<Account> getQueryExpression(String createdDateWithSubPartitions, Account account, String exclusiveStartKey) {
        Map<String, AttributeValue> attributeValueMap = null;
        if (exclusiveStartKey != null) {
            attributeValueMap = new HashMap<>();
            attributeValueMap.put(":createdDateWithSubPartitions", new AttributeValue().withS(createdDateWithSubPartitions));
        }

        DynamoDBQueryExpression<Account> queryExpression = new DynamoDBQueryExpression<Account>()
                .withIndexName(CREATED_DATE_INDEX)
                .withHashKeyValues(account)
                .withConsistentRead(false) //  Consistent reads are not supported on global secondary indexes

                //.withKeyConditionExpression("createdDateWithSubPartitions = :createdDateWithSubPartitions")
                //.withKeyConditionExpression("createdDateWithSubPartitions = :createdDateWithSubPartitions and processingDatetimeWithAccountId > :processingDatetimeWithAccountId")
                //.withExpressionAttributeValues(attributeValueMap)

                .withLimit(5)
                .withExclusiveStartKey(attributeValueMap);

        return queryExpression;
    }

    private static Map<String, Condition> makeReplyKeyConditions(String forumName, String threadSubject) {
        String replyId = forumName + "#" + threadSubject;

        Condition partitionKeyCondition = new Condition()
                .withComparisonOperator(ComparisonOperator.EQ)
                .withAttributeValueList(new AttributeValue().withS(replyId));

        Map<String, Condition> keyConditions = new HashMap<String, Condition>();
        keyConditions.put("Id", partitionKeyCondition);

        return keyConditions;
    }

    public static void deleteTable(String tableName) {

        System.out.println("Deleting table " + tableName + "...");

        Table table = dynamoDB.getTable(tableName);
        table.delete();

        // Wait for table to be deleted
        System.out.println("Waiting for " + tableName + " to be deleted...");
        try {
            table.waitForDelete();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void loadData() {

        System.out.println("Loading data into table " + BASE_TABLE_NAME + "...");

        Random randomBranch = new Random();
        Random randomReferenceNumber = new Random();
        Random randomSubPartition = new Random();
        String[] branches = {"1001", "1002","1003","1004","1005","1006","1007","1008","1009","1010","1011","1012","1013","1014","1015"}; //15
        Long[] accountIds = {9876543211L, 9876543212L,9876543213L,9876543214L,9876543215L,9876543216L,9876543217L,9876543218L,9876543219L,9876543220L,
                             9876543221L, 9876543222L,9876543223L,9876543224L,9876543225L,9876543226L,9876543227L,9876543228L,9876543229L,9876543230L}; //20
        String createdDate = "2023-02-01";
        Long processingDateTime = 1675209601000L; // 2023-02-01 0:00:01

        for (int i=0; i<10000; i++) {
            String branchId = branches[randomBranch.nextInt(15)];
            Long accountId = accountIds[randomBranch.nextInt(20)];
            Integer referenceNumber = randomReferenceNumber.nextInt(99999999);
            int subPartition = randomSubPartition.nextInt(10);

            String createdDateWithSubPartitions = createdDate + "::" + subPartition;
            String processingDatetimeWithTransactionId = processingDateTime + "::" + accountId;

            putItem(String.valueOf(accountId), branchId,
                    createdDate,
                    String.valueOf(processingDateTime), String.valueOf(subPartition),
                    createdDateWithSubPartitions, processingDatetimeWithTransactionId,
                    String.valueOf(referenceNumber));

            processingDateTime = processingDateTime + 1000;
        }
    }

    public static void putItem(String accountId, String branchId,
                               String createDate,
                               String processingDatetime, String subPartition,
                               String createdDateWithSubPartitions, String  processingDatetimeWithAccountId,
                               String referenceNumber) {

        System.out.println(accountId +" " +branchId
                +" " + createDate
                +" " + processingDatetime  +" " + subPartition
                +" " + createdDateWithSubPartitions  +" " +  processingDatetimeWithAccountId
                +" " + referenceNumber);

        Table table = dynamoDB.getTable(BASE_TABLE_NAME);

        Item item = new Item()
                .withPrimaryKey("accountId", accountId).withString("branchId", branchId)
                .withString("createDate", createDate)
                .withString("referenceNumber", referenceNumber)
                .withString("processingDatetime", processingDatetime)
                .withString("subPartition", subPartition)
                .withString("createdDateWithSubPartitions", createdDateWithSubPartitions)
                .withString("processingDatetimeWithAccountId", processingDatetimeWithAccountId);

        table.putItem(item);
    }

}

/*
    String[] eventIds =   {"dabe2678-2583-43b2-913e-1fbcd020890a", "a268578e-662b-4077-9f78-57875a729c93", "cb3781f1-6c62-4771-beef-4e1a69d302d6",
            "1f0d1851-2ed1-4316-9ed0-73f8795bbe47", "a6eb84a1-beeb-414b-a7ea-518b964ffc6f", "b8f8fedd-2ea4-42e5-93d3-d695e25629fd",
            "7c4b7391-40db-4bc7-b8ed-5101376f099a", "37883233-47bf-4146-865f-54b747323dce", "8387f9b5-4a74-4a80-bede-76f93cc4afdf",
            "090a9b28-e72d-4f4a-9436-521855108b8b", "e1a82228-69ea-4b74-b0f6-188a9151c08b", "0abaf0d2-fcc8-4aac-9c41-50793f15cb65",
            "ae03e8a0-bc65-4511-8ffa-4071bc3fc4a0", "360b5971-55bb-4b38-a42f-d0fd2972f5fd", "075a9fde-b5a1-4772-aab8-2034adc17542",
            "631ceccc-d753-493c-8ecc-1d451b670d54", "aa0420ef-1e55-42ae-8052-785095c4b77d", "02e8899d-334b-407e-b1f7-61cfb8ae86d1",
            "678d7853-608b-4923-ad7e-6a969008d073", "98de9a30-9acf-41c9-ba9b-944396ff7ced" };

    Long[] referenceNumbers = {76543211L, 76543212L, 76543213L,76543214L,76543215L,76543216L,76543217L,76543218L,76543219L,76543220L,
            76543221L, 76543222L,76543223L,76543224L,76543225L,76543226L,76543227L,76543228L,76543229L,76543230L};*/
