package io.dev.v2.dev.graphql.parser.v5;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLUnionType;
class TypeHandlerFactory {
    public static TypeHandler getHandler(GraphQLType type) {
        if (type instanceof GraphQLInterfaceType) {
            return new InterfaceTypeHandler();
        } else if (type instanceof GraphQLUnionType) {
            return new UnionTypeHandler();
        }
        return new DefaultTypeHandler();
    }
}
