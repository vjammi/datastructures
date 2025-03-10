package io.dev.v2.dev.graphql.parser.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.Map;

class GraphQLQueryValidatorWrapperTest {

    @Test
    void shouldRunSpecTest() throws JsonProcessingException {

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
    }
}