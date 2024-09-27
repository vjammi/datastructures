package io.dev.v2.dev.java.strategy;

import java.util.Arrays;
import java.util.List;

public class ProductSearchService extends AbstractSearchService {

    public ProductSearchService(SearchStrategy searchStrategy) {
        super(searchStrategy);
    }

    @Override
    public List<String> getData() {
        // Example data, could come from database or API
        return Arrays.asList("Laptop", "Phone", "Tablet", "Smartwatch");
    }

    public List<String> searchProducts(String query) {
        List<String> data = getData();
        return performSearch(query, data);
    }
}
