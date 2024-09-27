package io.dev.v2.dev.java.strategy;

import java.util.List;

public abstract class AbstractSearchService {

    protected SearchStrategy searchStrategy;

    public AbstractSearchService(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<String> performSearch(String query, List<String> data) {
        return searchStrategy.search(query, data);
    }

    // Abstract methods to be implemented by subclasses
    public abstract List<String> getData();
}
