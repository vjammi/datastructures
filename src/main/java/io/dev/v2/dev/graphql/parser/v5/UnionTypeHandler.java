package io.dev.v2.dev.graphql.parser.v5;

import graphql.language.FragmentDefinition;
import graphql.language.SelectionSet;
import graphql.schema.GraphQLNamedOutputType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLUnionType;

import java.util.Map;
class UnionTypeHandler implements TypeHandler {
    public void handle(GraphQLType type, SelectionSet selectionSet, FieldVisitor visitor, String parentPath, GraphQLSchema schema, Map<String, FragmentDefinition> fragments) {
        for (GraphQLNamedOutputType member : ((GraphQLUnionType) type).getTypes()) {
            visitor.traverse(member, selectionSet, parentPath + "(union: " + member.getName() + ")", fragments);
        }
    }
}
