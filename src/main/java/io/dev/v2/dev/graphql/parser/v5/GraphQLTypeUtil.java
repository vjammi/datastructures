package io.dev.v2.dev.graphql.parser.v5;

import graphql.schema.*;

class GraphQLTypeUtil {
    public static GraphQLType unwrapType(GraphQLType type) {
        if (type instanceof GraphQLNonNull nonNullType) {
            return unwrapType(nonNullType.getWrappedType());
        } else if (type instanceof GraphQLList listType) {
            return unwrapType(listType.getWrappedType());
        }
        return type;
    }

    public static boolean isListType(GraphQLType parentType, String fieldName) {
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

