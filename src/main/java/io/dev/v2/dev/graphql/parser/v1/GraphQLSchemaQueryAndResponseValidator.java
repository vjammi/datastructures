package io.dev.v2.dev.graphql.parser.v1;

import graphql.Scalars;
import graphql.language.*;
import graphql.parser.Parser;
import graphql.schema.*;
import graphql.validation.ValidationError;
import graphql.validation.Validator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class GraphQLSchemaQueryAndResponseValidator {

    public static void main(String[] args) throws Exception {
        // 1. Define a simple GraphQL Schema
        String schemaSDL = """
                type Query {
                    user: User
                }
                type User {
                    id: ID
                    name: String
                    age: Int
                    address: Address
                }
                type Address {
                    city: String
                    zip: String
                }
                """;

        // 2. Parse the schema and build a GraphQL Schema object
        GraphQLSchema schema = buildSchema(schemaSDL);

        // 3. Define the GraphQL query (user is requesting specific fields)
        String query = "{ user { id name age address { city zip } } }";

        // 4. Define the JSON response (simulating server response)
        String jsonResponse = "{ \"user\": { \"id\": 1, \"name\": \"Alice\", \"age\": 25, \"address\": { \"city\": \"NYC\", \"zip\": \"10001\" } } }";

        // 5. Validate the query against the schema
        if (!validateQueryAgainstSchema(query, schema)) {
            System.out.println("❌ Query validation failed!");
            return;
        }

        // 6. Parse the response and validate against the query & schema
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        boolean isValid = validateResponseAgainstQueryAndSchema(query, schema, jsonNode);
        System.out.println("\n✅ Final Validation Result: " + isValid);
    }

    // Build GraphQL Schema from SDL (Schema Definition Language)
    private static GraphQLSchema buildSchema(String schemaSDL) {
        return GraphQLSchema.newSchema()
                .query(GraphQLObjectType.newObject()
                        .name("Query")
                        .field(GraphQLFieldDefinition.newFieldDefinition()
                                .name("user")
                                .type(GraphQLObjectType.newObject()
                                        .name("User")
                                        .field(GraphQLFieldDefinition.newFieldDefinition().name("id").type(Scalars.GraphQLID))
                                        .field(GraphQLFieldDefinition.newFieldDefinition().name("name").type(Scalars.GraphQLString))
                                        .field(GraphQLFieldDefinition.newFieldDefinition().name("age").type(Scalars.GraphQLInt))
                                        .field(GraphQLFieldDefinition.newFieldDefinition()
                                                .name("address")
                                                .type(GraphQLObjectType.newObject()
                                                        .name("Address")
                                                        .field(GraphQLFieldDefinition.newFieldDefinition().name("city").type(Scalars.GraphQLString))
                                                        .field(GraphQLFieldDefinition.newFieldDefinition().name("zip").type(Scalars.GraphQLString))
                                                        .build()))
                                        .build()))
                        .build())
                .build();
    }

    // Validate the GraphQL query against the schema
    private static boolean validateQueryAgainstSchema(String query, GraphQLSchema schema) {
        Parser parser = new Parser();
        Document document = parser.parseDocument(query);

        Validator validator = new Validator();
        List<ValidationError> errors = validator.validateDocument(schema, document, Locale.getDefault());

        if (!errors.isEmpty()) {
            errors.forEach(error -> System.out.println("❌ Query Error: " + error.getDescription()));
            return false;
        }
        System.out.println("✅ Query is valid against schema.");
        return true;
    }

    // Validate the JSON response against the query and schema
    private static boolean validateResponseAgainstQueryAndSchema(String query, GraphQLSchema schema, JsonNode jsonNode) {
        Parser parser = new Parser();
        Document document = parser.parseDocument(query);

        for (Definition<?> definition : document.getDefinitions()) {
            if (definition instanceof OperationDefinition) {
                OperationDefinition operation = (OperationDefinition) definition;
                for (Selection<?> selection : operation.getSelectionSet().getSelections()) {
                    if (selection instanceof Field) {
                        Field field = (Field) selection;
                        if (!validateField(field, jsonNode, schema.getQueryType())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    // Recursive validation of fields and types
    private static boolean validateField(Field field, JsonNode jsonNode, GraphQLObjectType parentType) {
        String fieldName = field.getName();

        if (!jsonNode.has(fieldName)) {
            System.out.println("❌ Missing field in JSON: " + fieldName);
            return false;
        }

        JsonNode fieldNode = jsonNode.get(fieldName);
        GraphQLType fieldType = parentType.getFieldDefinition(fieldName).getType();

        // If field has subfields, validate recursively
        if (field.getSelectionSet() != null) {
            if (!(fieldType instanceof GraphQLObjectType)) {
                System.out.println("❌ Expected object for field '" + fieldName + "' but found: " + fieldType.getClass().getName());
                return false;
            }
            GraphQLObjectType subType = (GraphQLObjectType) fieldType;
            for (Selection<?> subSelection : field.getSelectionSet().getSelections()) {
                if (subSelection instanceof Field) {
                    Field subField = (Field) subSelection;
                    if (!validateField(subField, fieldNode, subType)) {
                        return false;
                    }
                }
            }
        } else {
            // Validate primitive types
            if (fieldType == Scalars.GraphQLInt && !fieldNode.isInt()) {
                System.out.println("❌ Type mismatch for field '" + fieldName + "', expected Int but found: " + fieldNode.getNodeType());
                return false;
            }
            if (fieldType == Scalars.GraphQLString && !fieldNode.isTextual()) {
                System.out.println("❌ Type mismatch for field '" + fieldName + "', expected String but found: " + fieldNode.getNodeType());
                return false;
            }
        }
        return true;
    }
}