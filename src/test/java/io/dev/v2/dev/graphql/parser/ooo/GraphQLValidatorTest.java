package io.dev.v2.dev.graphql.parser.ooo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import graphql.language.*;
import graphql.parser.Parser;

import java.util.Map;

public class GraphQLValidatorTest {

//
//    public static void main(String[] args) throws Exception {
//        String query = """
//            {
//                user {
//                    id
//                    name
//                    profile { age address }
//                    friends { id name }
//                    ... on Admin { role }
//                }
//            }
//        """;
//
//        String jsonResponse = """
//            {
//                "user": {
//                    "id": "one",   // ❌ Should be integer
//                    "name": "Alice",
//                    "profile": { "age": 25, "address": "123 Street" },
//                    "friends": "invalidArray", // ❌ Should be an array
//                    "role": "admin"
//                }
//            }
//        """;
//
//        Map<String, String> expectedTypes = Map.of(
//                "id", "Integer", "name", "String", "profile", "Object",
//                "age", "Integer", "address", "String", "friends", "Array",
//                "role", "String"
//        );
//
//        Parser parser = new Parser();
//        Document document = parser.parseDocument(query);
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
//        ValidationContext context = new ValidationContext(expectedTypes);
//
//        GraphQLQueryValidator validator = new GraphQLQueryValidator();
//
//        for (Definition<?> definition : document.getDefinitions()) {
//            if (definition instanceof OperationDefinition operation) {
//                for (Selection<?> selection : operation.getSelectionSet().getSelections()) {
//                    if (selection instanceof Field field) {
//                        validator.validate(field, jsonNode, context);
//                    }
//                }
//            }
//        }
//
//        if (context.hasErrors()) {
//            System.out.println("❌ Validation failed:");
//            context.getErrors().forEach(System.out::println);
//        } else {
//            System.out.println("✅ Validation passed!");
//        }
//    }


}



//import org.junit.jupiter.api.Test;
//
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//
//class GraphQLQueryValidatorTest {
//    @Test
//    void testFullValidation() throws Exception {
//        String query = """
//                           {
//                              user {
//                                  id
//                                  name
//                                  profile {
//                                    age
//                                    address
//                                  }
//                                  friends {
//                                    id
//                                    name
//                                  }
//                                  ... on Admin {
//                                    role
//                                  }
//                              }
//                           }
//                       """;
//
//                String jsonResponse = """
//                {
//                  "user": {
//                    "id": 1,
//                    "name": "Alice",
//                    "profile": { "age": 25, "address": "123 Street" },
//                    "friends": [{ "id": 2, "name": "Bob" }],
//                    "role": "admin"
//                  }
//                }
//                """;
//
//        Map<String, String> expectedTypes = Map.of(
//                "id", "Integer", "name", "String", "profile", "Object",
//                "age", "Integer", "address", "String", "friends", "Array", "role", "String"
//        );
//
//
//        ValidationContext context = GraphQLQueryValidator.validate(query, jsonResponse, expectedTypes);
//
//        assertFalse(context.hasErrors(), "No errors expected for valid query and response.");
//    }
//}
