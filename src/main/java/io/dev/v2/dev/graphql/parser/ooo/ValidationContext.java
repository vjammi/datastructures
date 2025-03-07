package io.dev.v2.dev.graphql.parser.ooo;

import java.util.*;

class ValidationContext {
    private final List<String> errors = new ArrayList<>();
    private final Map<String, String> expectedTypes;

    public ValidationContext(Map<String, String> expectedTypes) {
        this.expectedTypes = expectedTypes;
    }

    public void addError(String error) {
        errors.add(error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getExpectedType(String fieldName) {
        return expectedTypes.getOrDefault(fieldName, "Unknown");
    }
}

