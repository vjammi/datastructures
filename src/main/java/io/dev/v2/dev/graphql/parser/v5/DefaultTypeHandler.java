package io.dev.v2.dev.graphql.parser.v5;

import graphql.language.FragmentDefinition;
import graphql.language.SelectionSet;
import graphql.schema.GraphQLFieldsContainer;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;

import java.util.Map;
class DefaultTypeHandler implements TypeHandler {
    public void handle(GraphQLType type, SelectionSet selectionSet, FieldVisitor visitor, String parentPath, GraphQLSchema schema, Map<String, FragmentDefinition> fragments) {
        if (type instanceof GraphQLFieldsContainer container) {
            visitor.traverse(container, selectionSet, parentPath, fragments);
        }
    }
}