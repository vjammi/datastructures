package io.dev.v2.dev.graphql.parser.v5;


import graphql.language.Definition;
import graphql.language.Document;
import graphql.language.OperationDefinition;
import graphql.parser.Parser;
import graphql.schema.GraphQLSchema;

import java.util.HashMap;
import java.util.Map;

class QueryFieldResolver {
    private final GraphQLSchema schema;

    public QueryFieldResolver(GraphQLSchema schema) {
        this.schema = schema;
    }

    public Map<String, String> resolve(String query) {
        Document document = new Parser().parseDocument(query);
        FieldVisitor visitor = new FieldVisitor(schema);

        for (Definition<?> definition : document.getDefinitions()) {
            if (definition instanceof OperationDefinition operationDefinition) {
                visitor.traverse(schema.getQueryType(), operationDefinition.getSelectionSet(), "$", new HashMap<>());
            }
        }
        return visitor.getFieldTypes();
    }
}
