package io.dev.v2.dev.graphql.parser.v4;
import graphql.GraphQL;
import graphql.language.*;
import graphql.parser.Parser;
import graphql.schema.*;
import graphql.schema.idl.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GraphQLFieldTypeResolver {

    public static void main(String[] args) throws IOException {
        String schemaDirectory = "C:\\ds\\workspace\\datastructures\\src\\main\\java\\io\\dev\\v2\\dev\\graphql\\parser\\v4\\schema";
        GraphQLSchema schema = loadGraphQLSchema(schemaDirectory);

        String query = """
            query {
                user(id: \"123\") {
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
                    address{
                        city
                        zip
                    }
                }
            }
        """;

        Map<String, String> fieldTypes = resolveQueryFieldTypes(schema, query);
        fieldTypes.forEach((field, type) -> System.out.println(field + " -> " + type));
    }

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

    private static Map<String, String> resolveQueryFieldTypes(GraphQLSchema schema, String query) {
        Document document = new Parser().parseDocument(query);
        Map<String, String> fieldTypes = new LinkedHashMap<>();
        Map<String, FragmentDefinition> fragments = new HashMap<>();

        for (Definition<?> definition : document.getDefinitions()) {
            if (definition instanceof FragmentDefinition fragmentDefinition) {
                fragments.put(fragmentDefinition.getName(), fragmentDefinition);
            }
        }

        for (Definition<?> definition : document.getDefinitions()) {
            if (definition instanceof OperationDefinition operationDefinition) {
                traverseSelectionSet(schema.getQueryType(), operationDefinition.getSelectionSet(), fieldTypes, "$", schema, fragments);
            }
        }
        return fieldTypes;
    }

    private static void traverseSelectionSet(GraphQLType parentType, SelectionSet selectionSet,
                                             Map<String, String> fieldTypes, String parentPath,
                                             GraphQLSchema schema, Map<String, FragmentDefinition> fragments) {
        if (selectionSet == null) return;

        for (Selection<?> selection : selectionSet.getSelections()) {
            if (selection instanceof Field field) {
                String fieldName = field.getName();
                boolean isList = isListType(parentType, fieldName);
                String fullPath = parentPath + "." + fieldName + (isList ? "[*]" : "");

                if (parentType instanceof GraphQLFieldsContainer container) {
                    GraphQLFieldDefinition fieldDef = container.getFieldDefinition(fieldName);
                    if (fieldDef != null) {
                        GraphQLType fieldType = unwrapType(fieldDef.getType());
                        fieldTypes.put(fullPath, fieldType.toString());

                        if (fieldType instanceof GraphQLFieldsContainer ||
                                fieldType instanceof GraphQLInterfaceType ||
                                fieldType instanceof GraphQLUnionType) {
                            handleComplexType(fieldType, field.getSelectionSet(), fieldTypes, fullPath, schema, fragments);
                        }
                    }
                }
            } else if (selection instanceof InlineFragment inlineFragment) {
                GraphQLType typeCondition = schema.getType(inlineFragment.getTypeCondition().getName());
                traverseSelectionSet(typeCondition, inlineFragment.getSelectionSet(), fieldTypes, parentPath, schema, fragments);
            } else if (selection instanceof FragmentSpread fragmentSpread) {
                FragmentDefinition frag = fragments.get(fragmentSpread.getName());
                if (frag != null) {
                    GraphQLType fragType = schema.getType(frag.getTypeCondition().getName());
                    traverseSelectionSet(fragType, frag.getSelectionSet(), fieldTypes, parentPath, schema, fragments);
                }
            }
        }
    }

    private static void handleComplexType(GraphQLType type, SelectionSet selectionSet,
                                          Map<String, String> fieldTypes, String parentPath,
                                          GraphQLSchema schema, Map<String, FragmentDefinition> fragments) {
        if (type instanceof GraphQLFieldsContainer container) {
            traverseSelectionSet(container, selectionSet, fieldTypes, parentPath, schema, fragments);
        } else if (type instanceof GraphQLInterfaceType interfaceType) {
            for (GraphQLObjectType impl : schema.getImplementations(interfaceType)) {
                traverseSelectionSet(impl, selectionSet, fieldTypes, parentPath + "(impl: " + impl.getName() + ")", schema, fragments);
            }
        } else if (type instanceof GraphQLUnionType unionType) {
            for (GraphQLNamedOutputType member : unionType.getTypes()) {
                traverseSelectionSet(member, selectionSet, fieldTypes, parentPath + "(union: " + member.getName() + ")", schema, fragments);
            }
        }
    }

    private static GraphQLType unwrapType(GraphQLType type) {
        if (type instanceof GraphQLNonNull nonNullType) {
            return unwrapType(nonNullType.getWrappedType());
        } else if (type instanceof GraphQLList listType) {
            return unwrapType(listType.getWrappedType());
        }
        return type;
    }

    private static boolean isListType(GraphQLType parentType, String fieldName) {
        if (parentType instanceof GraphQLFieldsContainer container) {
            GraphQLFieldDefinition fieldDef = container.getFieldDefinition(fieldName);
            return fieldDef != null && isList(fieldDef.getType());
        }
        return false;
    }

    private static boolean isList(GraphQLType type) {
        if (type instanceof GraphQLNonNull nonNullType) {
            return isList(nonNullType.getWrappedType());
        }
        return type instanceof GraphQLList;
    }
}
