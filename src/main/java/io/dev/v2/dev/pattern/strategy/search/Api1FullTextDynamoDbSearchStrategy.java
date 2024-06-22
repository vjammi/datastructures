package io.dev.v2.dev.pattern.strategy.search;

public class Api1FullTextDynamoDbSearchStrategy implements FullTextSearchBehavior {

    public String search(String query) {
        String result = query + " - InstructionsFullTextDynamoDbSearchStrategy";
        System.out.println(result);
        return result;
    }
}
