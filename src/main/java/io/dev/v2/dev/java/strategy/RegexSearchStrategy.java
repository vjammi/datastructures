package io.dev.v2.dev.java.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class RegexSearchStrategy implements SearchStrategy {
    @Override
    public List<String> search(String query, List<String> data) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(query);
        for (String item : data) {
            Matcher matcher = pattern.matcher(item);
            if (matcher.find()) {
                result.add(item);
            }
        }
        return result;
    }
}