package io.dev.v2.dev.graphql.parser.ooo;

import com.fasterxml.jackson.databind.JsonNode;
import graphql.language.Field;
import java.util.HashMap;
import java.util.Map;
public class GraphQLQueryValidator {
    private Map<String, Validator> validators;
    public GraphQLQueryValidator() {
        this.validators = new HashMap<>();
        validators.put("scalar", new ScalarFieldValidator());
        validators.put("object", new ObjectFieldValidator(this));
        validators.put("array", new ArrayFieldValidator(this));
        validators.put("inlineFragment", new InlineFragmentValidator(this));
    }

    public void validate(Field field, JsonNode jsonNode, ValidationContext context) {
        String fieldName = field.getName();

        if (!jsonNode.has(fieldName)) {
            context.addError("❌ Missing field: " + fieldName);
            return;
        }

        JsonNode fieldNode = jsonNode.get(fieldName);

        String expectedType = context.getExpectedType(fieldName);

        System.out.println(field.getName() + " " +expectedType +" " +fieldNode);

        Validator validator = switch (expectedType) {
            case "Object" -> validators.get("object");
            case "Array" -> validators.get("array");
            case "InlineFragment" -> validators.get("inlineFragment");
            default -> validators.get("scalar");
        };

        validator.validate(field, fieldNode, context);
    }

    //    public static void main(String[] args) throws Exception {
    //        String query = "{ user { id name profile { age address } friends { id name } ... on Admin { role } } }";
    //
    //        String jsonResponse = """
    //                {
    //                  "user": {
    //                    "id": "one",   // ❌ Wrong type (string instead of int)
    //                    "name": "Alice",
    //                    "profile": { "age": 25, "address": "123 Street" },
    //                    "friends": "invalidArray", // ❌ Should be an array
    //                    "role": "admin"
    //                  }
    //                }
    //                """;
    //
    //        Map<String, String> expectedTypes = Map.of(
    //                "id", "Integer",
    //                "name", "String",
    //                "profile", "Object",
    //                "age", "Integer",
    //                "address", "String",
    //                "friends", "Array",
    //                "role", "String"
    //        );
    //
    //        ValidationContext context = GraphQLQueryValidator.validateGraphQLQuery(query, jsonResponse, expectedTypes);
    //
    //        if (context.hasErrors()) {
    //            System.out.println("❌ Validation failed:");
    //            context.getErrors().forEach(System.out::println);
    //        } else {
    //            System.out.println("✅ Validation passed!");
    //        }
    //    }
}
