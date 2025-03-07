package io.dev.v2.dev.graphql.parser.initial;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.language.*;
import graphql.parser.Parser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphQLResponseValidatorWithInlineFragmentsArraysFieldValidationsFixed {

    public static void main(String[] args) throws Exception {
        // Sample GraphQL Query with nested fields, inline fragments, and arrays
        // String query = "{ user { id name profile { age address } friends { id name } ... on Admin { role } } }";
        String query = """ 
                query userQuery{ 
                    user { 
                        id 
                        name
                        ... on Admin { role } 
                        profile { age address } 
                        friends { id name }                         
                    } 
                }
                """;

        // Sample JSON Response (with errors)
        // ❌ Wrong type (string instead of int) - "id": "ONE",
        // ❌ Should be an array but is a string - "friends": "invalidArray",
        String jsonResponse = """
                {
                  "user": {
                    "id": 1,    
                    "name": "Alice",
                    "role": "admin",
                    "profile": { "age": 25, "address": "123 Street" },
                    "friends": [
                      { "id": 2, "name": "Bob" },
                      { "id": 3, "name": "Charlie" }
                    ]                    
                  }
                }
                """;

        // Expected type mappings for validation
        Map<String, String> expectedTypes = Map.of(
                "id", "Integer",
                "name", "String",
                "profile", "Object",
                "age", "Integer",
                "address", "String",
                "friends", "Array",
                "role", "String"
        );

        // Parse the query and JSON response
        Parser parser = new Parser();
        Document document = parser.parseDocument(query);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Validate the query against the JSON response
        List<String> errors = new ArrayList<>();
        boolean isValid = validateQuery(document, jsonNode, expectedTypes, errors);

        // Print validation results
        if (isValid) {
            System.out.println("✅ Validation passed!");
        } else {
            System.out.println("❌ Validation failed with errors:");
            errors.forEach(System.out::println);
        }
    }

    /**
     * Validates the GraphQL query structure against the JSON response.
     */
    private static boolean validateQuery(Document document, JsonNode jsonNode, Map<String, String> expectedTypes, List<String> errors) {
        for (Definition<?> definition : document.getDefinitions()) {
            if (definition instanceof OperationDefinition operation) {
                for (Selection<?> selection : operation.getSelectionSet().getSelections()) {
                    if (selection instanceof Field field) {
                        validateField(field, jsonNode, expectedTypes, errors);
                    }
                }
            }
        }
        return errors.isEmpty();
    }

    /**
     * Recursively validates a GraphQL field against the JSON response.
     */
    private static void validateField(Field field, JsonNode jsonNode, Map<String, String> expectedTypes, List<String> errors) {
        String fieldName = field.getName();

        if (!jsonNode.has(fieldName)) {
            errors.add("❌ Missing field: " + fieldName);
            return;
        }

        JsonNode fieldNode = jsonNode.get(fieldName);
        String expectedType = expectedTypes.getOrDefault(fieldName, "Unknown");

        // Handle array fields correctly
        if ("Array".equals(expectedType)) {
            validateArray(field, jsonNode, expectedTypes, errors);
            return;
        }

        // Validate type
        if (!validateType(fieldNode, expectedType)) {
            errors.add("❌ Type mismatch for field: " + fieldName + " (Expected: " + expectedType + ", Found: " + fieldNode.getNodeType() + ")");
            return;
        }

        // Handle nested objects
        if (field.getSelectionSet() != null) {
            for (Selection<?> subSelection : field.getSelectionSet().getSelections()) {
                if (subSelection instanceof Field subField) {
                    validateField(subField, fieldNode, expectedTypes, errors);
                } else if (subSelection instanceof InlineFragment inlineFragment) {
                    validateInlineFragment(inlineFragment, fieldNode, expectedTypes, errors);
                }
            }
        }
    }

    /**
     * Handles validation of inline fragments (e.g., `... on Admin { role }`).
     */
    private static void validateInlineFragment(InlineFragment inlineFragment, JsonNode jsonNode, Map<String, String> expectedTypes, List<String> errors) {
        if (inlineFragment.getSelectionSet() != null) {
            for (Selection<?> subSelection : inlineFragment.getSelectionSet().getSelections()) {
                if (subSelection instanceof Field field) {
                    validateField(field, jsonNode, expectedTypes, errors);
                }
            }
        }
    }

    /**
     * Handles validation of arrays (lists of objects).
     */
    private static void validateArray(Field field, JsonNode jsonNode, Map<String, String> expectedTypes, List<String> errors) {
        String fieldName = field.getName();

        if (!jsonNode.has(fieldName)) {
            errors.add("❌ Missing field: " + fieldName);
            return;
        }

        JsonNode arrayNode = jsonNode.get(fieldName);
        if (!arrayNode.isArray()) {
            errors.add("❌ Expected an array but found: " + arrayNode.getNodeType() + " for field: " + fieldName);
            return;
        }

        for (JsonNode item : arrayNode) {
            for (Selection<?> selection : field.getSelectionSet().getSelections()) {
                if (selection instanceof Field subField) {
                    validateField(subField, item, expectedTypes, errors);
                }
            }
        }
    }

    /**
     * Validates that the JSON field matches the expected GraphQL type.
     */
    private static boolean validateType(JsonNode fieldNode, String expectedType) {
        boolean isValid = switch (expectedType) {
            case "Integer" -> fieldNode.isInt();
            case "String" -> fieldNode.isTextual();
            case "Boolean" -> fieldNode.isBoolean();
            case "Object" -> fieldNode.isObject();
            case "Array" -> fieldNode.isArray();
            default -> true; // Unknown types are ignored
        };
        return isValid;
    }
}
