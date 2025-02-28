package io.dev.v2.dev.graphql;

import graphql.language.*;
import graphql.parser.Parser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class GraphQLQueryAndResponseValidator {

    public static void main(String[] args) throws Exception {
        // Sample GraphQL Query
        String query = "{ user { id name age } }";

        // Sample JSON Response (from the server)
        String jsonResponse = "{ \"user\": { \"id\": 1, \"name\": \"Alice\", \"age\": 25 } }";

        // Parse the query and JSON response
        Parser parser = new Parser();
        Document document = parser.parseDocument(query);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Validate the query structure against JSON response
        boolean isValid = validateQuery(document, jsonNode);
        System.out.println("Validation Result: " + isValid);
    }

    private static boolean validateQuery(Document document, JsonNode jsonNode) {
        // Extract field names from the GraphQL query
        Set<String> requestedFields = new HashSet<>();
        for (Definition<?> definition : document.getDefinitions()) {
            if (definition instanceof OperationDefinition) {
                OperationDefinition operation = (OperationDefinition) definition;
                for (Selection<?> selection : operation.getSelectionSet().getSelections()) {
                    if (selection instanceof Field) {
                        Field field = (Field) selection;
                        requestedFields.add(field.getName());
                    }
                }
            }
        }

        // Compare with JSON response fields
        for (String field : requestedFields) {
            if (!jsonNode.has(field)) {
                System.out.println("Missing field: " + field);
                return false; // Validation failed
            }
        }
        return true; // All fields exist
    }
}