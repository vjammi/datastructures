package io.dev.v2.dev.graphql.parser.v5;

import graphql.language.FragmentDefinition;
import graphql.language.SelectionSet;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;

import java.util.Map;

interface TypeHandler {
    void handle(GraphQLType type, SelectionSet selectionSet, FieldVisitor visitor, String parentPath, GraphQLSchema schema, Map<String, FragmentDefinition> fragments);
}
