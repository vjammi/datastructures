package io.dev.v2.dev.graphql.parser.v3;

import graphql.language.*;
import graphql.parser.Parser;
import graphql.schema.*;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GraphQLFieldTypeResolver {

    public static void main(String[] args) throws IOException {
        // Step 1: Load and merge schema files
        String schemaDirectory = "/ds/workspace/datastructures/src/main/java/io/dev/v2/dev/graphql/parser/v3/schema";
        GraphQLSchema schema = loadGraphQLSchema(schemaDirectory);

        // Step 2: Sample GraphQL Query
        String query = """
            query {
                user(id: "123") {
                    id
                    name
                    posts {
                        title
                        comments {
                            text
                            author {
                                username
                            }
                        }
                    }
                }
            }
            """;

        // Step 3: Resolve field types
        Map<String, String> fieldTypes = resolveQueryFieldTypes(schema, query);
        fieldTypes.forEach((field, type) -> System.out.println(field + " -> " + type));
    }

    /**
     * Loads and merges GraphQL schema files from a directory.
     */
    private static GraphQLSchema loadGraphQLSchema(String schemaDirectory) throws IOException {
        File folder = new File(schemaDirectory);
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        SchemaParser schemaParser = new SchemaParser();

        for (File file : Objects.requireNonNull(folder.listFiles((dir, name) -> name.endsWith(".graphqls")))) {
            String schemaContent = Files.readString(Paths.get(file.getAbsolutePath()));
            typeRegistry.merge(schemaParser.parse(schemaContent));
        }

        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring().build();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    /**
     * Resolves field types for a given GraphQL query.
     */
    private static Map<String, String> resolveQueryFieldTypes(GraphQLSchema schema, String query) {
        Document document = new Parser().parseDocument(query);
        Map<String, String> fieldTypes = new LinkedHashMap<>();

        for (Definition<?> definition : document.getDefinitions()) {
            if (definition instanceof OperationDefinition operationDefinition) {
                traverseSelectionSet(schema.getQueryType(), operationDefinition.getSelectionSet(), fieldTypes, "");
            }
        }
        return fieldTypes;
    }

    /**
     * Recursively traverses a GraphQL selection set and resolves field types.
     */
    private static void traverseSelectionSet(GraphQLType parentType, SelectionSet selectionSet, Map<String, String> fieldTypes, String parentPath) {
        if (selectionSet == null) return;

        for (Selection<?> selection : selectionSet.getSelections()) {
            if (selection instanceof Field field) {
                String fieldName = field.getName();
                String fullPath = parentPath.isEmpty() ? fieldName : parentPath + "." + fieldName;

                if (parentType instanceof GraphQLFieldsContainer container) {
                    GraphQLFieldDefinition fieldDef = container.getFieldDefinition(fieldName);
                    if (fieldDef != null) {
                        GraphQLType fieldType = unwrapType(fieldDef.getType());
                        fieldTypes.put(fullPath, fieldType.toString()); //fieldType.getName()

                        if (fieldType instanceof GraphQLFieldsContainer) {
                            traverseSelectionSet(fieldType, field.getSelectionSet(), fieldTypes, fullPath);
                        }
                    }
                }
            }
        }
    }

    /**
     * Unwraps GraphQL type to get the underlying base type.
     */
    private static GraphQLType unwrapType(GraphQLType type) {
        if (type instanceof GraphQLNonNull nonNullType) {
            return unwrapType(nonNullType.getWrappedType());
        } else if (type instanceof GraphQLList listType) {
            return unwrapType(listType.getWrappedType());
        }
        return type;
    }
}
