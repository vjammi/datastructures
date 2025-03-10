package io.dev.v2.dev.graphql.parser.v2;

import com.fasterxml.jackson.databind.JsonNode;
import graphql.language.Field;

class ScalarFieldValidator implements Validator {
    @Override
    public void validate(Field field, JsonNode jsonNode, ValidationContext context, String parentPath, StringBuffer depth) {
        //String expectedType = context.getExpectedType(field.getName());
        String qualifiedFieldName = buildQualifiedFieldName(field, parentPath);
        String expectedType = context.getExpectedType(qualifiedFieldName);

        if (!validateType(jsonNode, expectedType)) {
            //context.addError("❌ Type mismatch for field: " + field.getName() + " (Expected: " + expectedType + ")");
            context.addError("❌ Type mismatch for field: " + qualifiedFieldName + " (Expected: " + expectedType + ")");
        }
    }

    private boolean validateType(JsonNode fieldNode, String expectedType) {
        return switch (expectedType) {
            case "Integer" -> fieldNode.isInt();
            case "String" -> fieldNode.isTextual();
            case "Boolean" -> fieldNode.isBoolean();
            case "Object" -> fieldNode.isObject();
            case "Array" -> fieldNode.isArray();
            default -> true;
        };
    }
    private String buildQualifiedFieldName(Field field, String parentPath) {
        return parentPath.isEmpty() ? field.getName() : parentPath + "." + field.getName();
    }
}