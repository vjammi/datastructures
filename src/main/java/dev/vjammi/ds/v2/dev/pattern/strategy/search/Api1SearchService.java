package dev.vjammi.ds.v2.dev.pattern.strategy.search;
public class Api1SearchService extends SearchService{
    public Api1SearchService(RequestValidationBehavior requestValidationBehavior, Api1FullTextDynamoDbSearchStrategy fullTextSearchStrategy, Api1DynamodbDynamoDbSearchStrategy dynamodbSearchStrategy) {
        setValidationStrategy(requestValidationBehavior);
        setDynamodbSearchStrategy(dynamodbSearchStrategy);
        setFullTextSearchStrategy(fullTextSearchStrategy);
    }
    @Override
    String buildRelayResponse(String result) {
        return "RelayResponse - " +result;
    }
    @Override
    public String search(String query){
        validateRequest(query);
        String fullTextSearchResult = performFullTextSearch(query);
        String dynamoDBSearchResult = performDynamoDBSearch(fullTextSearchResult);
        String response = buildRelayResponse(dynamoDBSearchResult);
        return response;
    }

}
