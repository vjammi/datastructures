package io.dev.v2.dev.java.strategy;

import java.util.List;

public class SearchServiceClient {
    public static void main(String[] args) {
        // Choose strategy at runtime
        SearchStrategy simpleSearch = new SimpleSearchStrategy();
        SearchStrategy regexSearch = new RegexSearchStrategy();

        // Create a search service and set strategy
        ProductSearchService searchService = new ProductSearchService(simpleSearch);

        // Perform a search using simple strategy
        List<String> results = searchService.searchProducts("Phone");
        System.out.println("Simple Search Results: " + results);

        // Switch to regex search strategy at runtime
        searchService.setSearchStrategy(regexSearch);
        results = searchService.searchProducts("P.*");
        System.out.println("Regex Search Results: " + results);
    }
}

