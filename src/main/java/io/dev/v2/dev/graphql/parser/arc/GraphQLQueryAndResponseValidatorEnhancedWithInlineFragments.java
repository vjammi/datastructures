package io.dev.v2.dev.graphql.parser.arc;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.language.*;
import graphql.parser.Parser;

import java.util.*;

public class GraphQLQueryAndResponseValidatorEnhancedWithInlineFragments {

    public static void main(String[] args) throws Exception {
        // Sample GraphQL Query with Nested Fields and Inline Fragment
        String query = "{ user { id name age address { city zip } ... on Admin { role } } }";

        // Sample JSON Response from Server
        String jsonResponse = "{ \"user\": { \"id\": 1, \"name\": \"Alice\", \"age\": 25, \"address\": { \"city\": \"New York\", \"zip\": \"10001\" }, \"role\": \"Admin\" } }";

        // Parse the GraphQL Query
        Parser parser = new Parser();
        Document document = parser.parseDocument(query);

        // Parse the JSON Response
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Validate the Query against the Response
        boolean isValid = validateQuery(document, jsonNode);
        System.out.println("Validation Result: " + isValid);
    }

    /**
     * Validate a GraphQL query against a JSON response.
     */
    private static boolean validateQuery(Document document, JsonNode jsonNode) {
        for (Definition<?> definition : document.getDefinitions()) {
            if (definition instanceof OperationDefinition) {
                OperationDefinition operation = (OperationDefinition) definition;
                for (Selection<?> selection : operation.getSelectionSet().getSelections()) {
                    if (selection instanceof Field) {
                        Field field = (Field) selection;
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
     * Recursively validate a field from the GraphQL query against the JSON response.
     */
    private static boolean validateField(Field field, JsonNode jsonNode) {
        String fieldName = field.getName();

        if (!jsonNode.has(fieldName)) {
            System.out.println("❌ Missing field: " + fieldName);
            return false;
        }

        JsonNode jsonFieldNode = jsonNode.get(fieldName);

        // If the field has subfields, validate recursively
        if (field.getSelectionSet() != null) {
            if (!jsonFieldNode.isObject()) {
                System.out.println("❌ Expected object for field: " + fieldName);
                return false;
            }

            for (Selection<?> subSelection : field.getSelectionSet().getSelections()) {
                if (subSelection instanceof Field) {
                    Field subField = (Field) subSelection;
                    if (!validateField(subField, jsonFieldNode)) {
                        return false;
                    }
                } else if (subSelection instanceof InlineFragment) {
                    if (!validateInlineFragment((InlineFragment) subSelection, jsonFieldNode)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Validate inline fragments (e.g., "... on Admin { role }") against JSON.
     */
    private static boolean validateInlineFragment(InlineFragment fragment, JsonNode jsonNode) {
        for (Selection<?> selection : fragment.getSelectionSet().getSelections()) {
            if (selection instanceof Field) {
                Field field = (Field) selection;
                if (!validateField(field, jsonNode)) {
                    return false;
                }
            }
        }
        return true;
    }
}