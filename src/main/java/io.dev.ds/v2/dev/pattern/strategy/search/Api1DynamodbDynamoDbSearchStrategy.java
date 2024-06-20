package dev.vjammi.ds.v2.dev.pattern.strategy.search;

public class Api1DynamodbDynamoDbSearchStrategy implements DynamoDbSearchBehavior {

    public String search(String fullTextSearchResult) {
        String result = fullTextSearchResult + " - InstructionsDynamodbDynamoDbSearchStrategy";
        System.out.println(result);
        return result;
    }

}
