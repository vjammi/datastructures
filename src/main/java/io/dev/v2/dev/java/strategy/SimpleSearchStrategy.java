package io.dev.v2.dev.java.strategy;

import java.util.ArrayList;
import java.util.List;

public class SimpleSearchStrategy implements SearchStrategy {
    @Override
    public List<String> search(String query, List<String> data) {
        List<String> result = new ArrayList<>();
        for (String item : data) {
            if (item.contains(query)) {
                result.add(item);
            }
        }
        return result;
    }
}
