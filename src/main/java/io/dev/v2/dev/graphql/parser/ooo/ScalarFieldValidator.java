package io.dev.v2.dev.graphql.parser.ooo;

import com.fasterxml.jackson.databind.JsonNode;
import graphql.language.Field;

class ScalarFieldValidator implements Validator {
    @Override
    public void validate(Field field, JsonNode jsonNode, ValidationContext context) {
        //System.out.println(field.getName() + " > ");
        String expectedType = context.getExpectedType(field.getName());

        if (!validateType(jsonNode, expectedType)) {
            context.addError("❌ Type mismatch for field: " + field.getName() + " (Expected: " + expectedType + ")");
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
}