package io.dev.v2.dev.graphql.parser.v0;

import graphql.language.*;
import graphql.parser.Parser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GraphQLQueryAndResponseValidatorEnhanced {

    public static void main(String[] args) throws Exception {
        // Sample GraphQL Query with Nested Fields
        String query = "{ user { id name age address { city zip } } }";

        // Sample JSON Response (from the server)
        String jsonResponse = "{ \"user\": { \"id\": 1, \"name\": \"Alice\", \"age\": 25, \"address\": { \"city\": \"NYC\", \"zip\": \"10001\" } } }";

        // Parse the query and JSON response
        Parser parser = new Parser();
        Document document = parser.parseDocument(query);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Validate the query structure against JSON response
        boolean isValid = validateQuery(document, jsonNode);

        System.out.println("\nFinal Validation Result: " + isValid);
    }

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

    private static boolean validateField(Field field, JsonNode jsonNode) {
        String fieldName = field.getName();

        if (!jsonNode.has(fieldName)) {
            System.out.println("❌ Missing field in JSON: " + fieldName);
            return false;
        }

        JsonNode fieldNode = jsonNode.get(fieldName);

        // If there are subfields, recursively validate them
        if (field.getSelectionSet() != null) {
            if (!fieldNode.isObject()) {
                System.out.println("❌ Expected object for field '" + fieldName + "' but found: " + fieldNode.getNodeType());
                return false;
            }

            for (Selection<?> subSelection : field.getSelectionSet().getSelections()) {
                if (subSelection instanceof Field) {
                    Field subField = (Field) subSelection;
                    if (!validateField(subField, fieldNode)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}



