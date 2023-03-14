package dev.vjammi.ds.v2.dev.aws.dynamo.gsi;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "Accounts") //DynamoDBIndexes-Account
public class Account {

    public static final String CREATED_DATE_INDEX = "CreatedDateIndex";

    private String accountId;
    private String branchId;
    private String createdDateWithSubPartitions;
    private String processingDatetimeWithAccountId;
    private String paymentReference;
    private String createdDate;

    @DynamoDBHashKey(attributeName = "accountId")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @DynamoDBRangeKey(attributeName = "branchId")
    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = CREATED_DATE_INDEX , attributeName = "createdDateWithSubPartitions")
    public String getCreatedDateWithSubPartitions() {
        return createdDateWithSubPartitions;
    }

    public void setCreatedDateWithSubPartitions(String createdDateWithSubPartitions) {
        this.createdDateWithSubPartitions = createdDateWithSubPartitions;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = CREATED_DATE_INDEX , attributeName = "processingDatetimeWithAccountId")
    public String getProcessingDatetimeWithAccountId() {
        return processingDatetimeWithAccountId;
    }

    public void setProcessingDatetimeWithAccountId(String processingDatetimeWithAccountId) {
        this.processingDatetimeWithAccountId = processingDatetimeWithAccountId;
    }

    @DynamoDBAttribute(attributeName = "paymentReference")
    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    @DynamoDBAttribute(attributeName = "createdDate")
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", branchId='" + branchId + '\'' +
                ", createdDateWithSubPartitions='" + createdDateWithSubPartitions + '\'' +
                ", processingDatetimeWithAccountId='" + processingDatetimeWithAccountId + '\'' +
                ", referenceNumber='" + paymentReference + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
