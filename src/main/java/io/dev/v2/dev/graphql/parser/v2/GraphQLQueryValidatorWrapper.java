package io.dev.v2.dev.graphql.parser.v2;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.language.*;
import graphql.parser.Parser;

import java.util.Map;

public class GraphQLQueryValidatorWrapper {

    public static void main(String[] args) throws Exception {

        String query = """
            {
                user {
                    id
                    name
                    profile { age address }
                    friends { id name }
                    ... on Admin { role }
                }
            }
        """;

        String response = """
                    {
                        "user": {
                            "id": 1001,   
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

        Map<String, String> expectedTypes = Map.of(
                "user", "Object",
                "id", "Integer",
                "name", "String",
                "profile", "Object",
                "age", "Integer",
                "address", "String",
                "friends", "Array",
                "role", "String"
        );


        ValidationContext context = GraphQLQueryValidatorWrapper.validateQuery(query, response, expectedTypes);

        if (context.hasErrors()) {
            System.out.println("❌ Validation failed:");
            context.getErrors().forEach(System.out::println);
        } else {
            System.out.println("✅ Validation passed!");
        }

        System.out.println("\nTypes ...");
        context.getQualifiedExpectedTypes().forEach(System.out::println);
    }
    public static ValidationContext validateQuery(String query, String jsonResponse, Map<String, String> expectedTypes) throws JsonProcessingException {

        Parser parser = new Parser();
        Document document = parser.parseDocument(query);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        ValidationContext context = new ValidationContext(expectedTypes);

        GraphQLQueryValidator validator = new GraphQLQueryValidator();

        for (Definition<?> definition : document.getDefinitions()) {
            if (definition instanceof OperationDefinition operation) {
                for (Selection<?> selection : operation.getSelectionSet().getSelections()) {
                    if (selection instanceof Field field) {
                        validator.validate(field, jsonNode, context, "", new StringBuffer());
                    }
                }
            }
        }

        return context;
    }

}
