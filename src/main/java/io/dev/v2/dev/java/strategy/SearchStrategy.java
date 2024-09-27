package io.dev.v2.dev.java.strategy;

import java.util.List;

public interface SearchStrategy {
    List<String> search(String query, List<String> data);
}
