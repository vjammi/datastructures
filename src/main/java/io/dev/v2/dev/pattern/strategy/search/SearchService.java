package io.dev.v2.dev.pattern.strategy.search;

public abstract class SearchService {

    private RequestValidationBehavior validationStrategy;
    private FullTextSearchBehavior fullTextSearchStrategy;
    private DynamoDbSearchBehavior dynamodbSearchStrategy;
    public void setValidationStrategy(RequestValidationBehavior validationStrategy) {
        this.validationStrategy = validationStrategy;
    }
    public void setFullTextSearchStrategy(Api1FullTextDynamoDbSearchStrategy fullTextSearchStrategy) {
        this.fullTextSearchStrategy = fullTextSearchStrategy;
    }
    public void setDynamodbSearchStrategy(Api1DynamodbDynamoDbSearchStrategy dynamodbSearchStrategy) {
        this.dynamodbSearchStrategy = dynamodbSearchStrategy;
    }
    public String validateRequest(String query){
        String result = validationStrategy.validate(query);
        return result;
    }

    public String performFullTextSearch(String query){
        String result = fullTextSearchStrategy.search(query);
        return result;
    }
    public String performDynamoDBSearch(String fullTextSearchResult){
        String result = dynamodbSearchStrategy.search(fullTextSearchResult);
        return result;
    }
    abstract String buildRelayResponse(String result2);
    abstract String search(String query);

}
