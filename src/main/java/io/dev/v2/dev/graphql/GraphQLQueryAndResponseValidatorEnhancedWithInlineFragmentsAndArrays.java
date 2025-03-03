package io.dev.v2.dev.graphql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.language.*;
import graphql.parser.Parser;

public class GraphQLQueryAndResponseValidatorEnhancedWithInlineFragmentsAndArrays{

    public static void main(String[] args) throws Exception {
        // Sample GraphQL Query with nested fields, inline fragments, and arrays
        String query = "{ user { id name profile { age address } friends { id name } ... on Admin { role } } }";

        // Sample JSON Response from the server
        String jsonResponse = """
                {
                  "user": {
                    "id": 1,
                    "name": "Alice",
                    "profile": { "age": 25, "address": "123 Street" },
                    "friends": [
                      { "id": 2, "name": "Bob" },
                      { "id": 3, "name": "Charlie" }
                    ],
                    "role": "admin"
                  }
                }
                """;

        // Parse the query and JSON response
        Parser parser = new Parser();
        Document document = parser.parseDocument(query);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Validate the query against the JSON response
        boolean isValid = validateQuery(document, jsonNode);
        System.out.println("Validation Result: " + isValid);
    }

    /**
     * Validates the GraphQL query structure against the JSON response.
     */
    private static boolean validateQuery(Document document, JsonNode jsonNode) {
        for (Definition<?> definition : document.getDefinitions()) {
            if (definition instanceof OperationDefinition operation) {
                for (Selection<?> selection : operation.getSelectionSet().getSelections()) {
                    if (selection instanceof Field field) {
                        if (!validateField(field, jsonNode)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Recursively validates a GraphQL field against the JSON response.
     */
    private static boolean validateField(Field field, JsonNode jsonNode) {
        String fieldName = field.getName();

        // Handle nested objects
        if (jsonNode.has(fieldName)) {
            JsonNode fieldNode = jsonNode.get(fieldName);

            // If it has sub-fields, validate them recursively
            if (field.getSelectionSet() != null) {
                for (Selection<?> subSelection : field.getSelectionSet().getSelections()) {
                    if (subSelection instanceof Field subField) {
                        if (!validateField(subField, fieldNode)) {
                            return false;
                        }
                    } else if (subSelection instanceof InlineFragment inlineFragment) {
                        if (!validateInlineFragment(inlineFragment, fieldNode)) {
                            return false;
                        }
                    }
                }
            }

            return true;
        } else {
            System.out.println("❌ Missing field: " + fieldName);
            return false;
        }
    }

    /**
     * Handles validation of inline fragments (e.g., `... on Admin { role }`).
     */
    private static boolean validateInlineFragment(InlineFragment inlineFragment, JsonNode jsonNode) {
        if (inlineFragment.getSelectionSet() != null) {
            for (Selection<?> subSelection : inlineFragment.getSelectionSet().getSelections()) {
                if (subSelection instanceof Field field) {
                    if (!validateField(field, jsonNode)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Handles validation of arrays (lists of objects).
     */
    private static boolean validateArray(Field field, JsonNode jsonNode) {
        if (!jsonNode.has(field.getName())) {
            System.out.println("❌ Missing field: " + field.getName());
            return false;
        }

        JsonNode arrayNode = jsonNode.get(field.getName());
        if (!arrayNode.isArray()) {
            System.out.println("❌ Expected an array but found: " + arrayNode.getNodeType());
            return false;
        }

        for (JsonNode item : arrayNode) {
            for (Selection<?> selection : field.getSelectionSet().getSelections()) {
                if (selection instanceof Field subField) {
                    if (!validateField(subField, item)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
