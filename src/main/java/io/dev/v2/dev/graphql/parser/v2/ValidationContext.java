package io.dev.v2.dev.graphql.parser.v2;

import java.util.*;

class ValidationContext {
    private final List<String> errors = new ArrayList<>();

    private final List<String> qualifiedExpectedTypes = new ArrayList<>();

    private final Map<String, String> expectedTypes;
    public ValidationContext(Map<String, String> expectedTypes) {
        this.expectedTypes = expectedTypes;
    }
    public void addError(String error) {
        errors.add(error);
    }

    public void addQualifiedExpectedType(String type){
        qualifiedExpectedTypes.add(type);
    }
    public List<String> getQualifiedExpectedTypes() {
        return qualifiedExpectedTypes;
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

    //public String getExpectedType(String qualifiedFieldName) {
    //    return expectedTypes.get(qualifiedFieldName);
    //}
}

