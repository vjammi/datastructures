package io.dev.v2.dev.graphql.parser.v5;

import graphql.language.FragmentDefinition;
import graphql.language.SelectionSet;
import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;

import java.util.Map;
class InterfaceTypeHandler implements TypeHandler {
    public void handle(GraphQLType type, SelectionSet selectionSet, FieldVisitor visitor, String parentPath, GraphQLSchema schema, Map<String, FragmentDefinition> fragments) {
        for (GraphQLObjectType impl : schema.getImplementations((GraphQLInterfaceType) type)) {
            visitor.traverse(impl, selectionSet, parentPath + "(impl: " + impl.getName() + ")", fragments);
        }
    }
}
