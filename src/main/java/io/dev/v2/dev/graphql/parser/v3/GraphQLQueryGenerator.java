package io.dev.v2.dev.graphql.parser.v3;

import graphql.language.*;
import graphql.parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GraphQLQueryGenerator {

    private final Map<String, TypeDefinition<?>> typeDefinitions;

    public GraphQLQueryGenerator(String schemaFilePath) throws IOException {
        String schemaContent = new String(Files.readAllBytes(Paths.get(schemaFilePath)));
        Document schemaDocument = new Parser().parseDocument(schemaContent);
        this.typeDefinitions = extractTypeDefinitions(schemaDocument);
    }

    private Map<String, TypeDefinition<?>> extractTypeDefinitions(Document document) {
        Map<String, TypeDefinition<?>> typeDefMap = new HashMap<>();
        for (Definition<?> definition : document.getDefinitions()) {
            if (definition instanceof TypeDefinition) {
                TypeDefinition<?> typeDefinition = (TypeDefinition<?>) definition;
                typeDefMap.put(typeDefinition.getName(), typeDefinition);
            }
        }
        return typeDefMap;
    }

    public String generateQuery(String rootTypeName) {
        return "{ " + generateSelectionSet(rootTypeName, new HashSet<>()) + " }";
    }

    private String generateSelectionSet(String typeName, Set<String> visitedTypes) {
        if (!typeDefinitions.containsKey(typeName)) {
            return "";
        }

        TypeDefinition<?> typeDef = typeDefinitions.get(typeName);
        StringBuilder queryBuilder = new StringBuilder();

        if (typeDef instanceof ObjectTypeDefinition || typeDef instanceof InterfaceTypeDefinition) {
            List<FieldDefinition> fields = ((ObjectTypeDefinition) typeDef).getFieldDefinitions();
            for (FieldDefinition field : fields) {
                queryBuilder.append(field.getName());

                Type<?> fieldType = field.getType();
                String fieldTypeName = getBaseTypeName(fieldType);

                if (typeDefinitions.containsKey(fieldTypeName) && !visitedTypes.contains(fieldTypeName)) {
                    visitedTypes.add(fieldTypeName);
                    queryBuilder.append(" { ").append(generateSelectionSet(fieldTypeName, visitedTypes)).append(" }");
                    visitedTypes.remove(fieldTypeName);
                }

                queryBuilder.append(" ");
            }
        }

        // Handle Union and Interface types with inline fragments
        if (typeDef instanceof UnionTypeDefinition) {
            for (Type<?> memberType : ((UnionTypeDefinition) typeDef).getMemberTypes()) {
                String memberTypeName = getBaseTypeName(memberType);
                if (typeDefinitions.containsKey(memberTypeName)) {
                    queryBuilder.append("... on ").append(memberTypeName).append(" { ")
                            .append(generateSelectionSet(memberTypeName, visitedTypes)).append(" } ");
                }
            }
        }

        return queryBuilder.toString().trim();
    }

    private String getBaseTypeName(Type<?> type) {
        if (type instanceof TypeName) {
            return ((TypeName) type).getName();
        } else if (type instanceof ListType) {
            return getBaseTypeName(((ListType) type).getType());
        } else if (type instanceof NonNullType) {
            return getBaseTypeName(((NonNullType) type).getType());
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        String schemaFilePath = "/ds/workspace/datastructures/src/main/java/io/dev/v2/dev/graphql/parser/v3/schema/schema.graphqls";
        GraphQLQueryGenerator generator = new GraphQLQueryGenerator(schemaFilePath);
        String query = generator.generateQuery("Query");
        System.out.println(query);
    }

}

