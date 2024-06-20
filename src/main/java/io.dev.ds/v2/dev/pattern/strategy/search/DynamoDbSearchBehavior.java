package dev.vjammi.ds.v2.dev.pattern.strategy.search;

public interface DynamoDbSearchBehavior {
    public String search(String fullTextSearchResult);
}
