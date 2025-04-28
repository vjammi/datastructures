package io.dev.v2.dev.graphql.parser.v5;

import graphql.language.Field;
import graphql.language.FragmentDefinition;
import graphql.language.Selection;
import graphql.language.SelectionSet;
import graphql.schema.*;

import java.util.LinkedHashMap;
import java.util.Map;

class FieldVisitor {
    private final GraphQLSchema schema;
    private final Map<String, String> fieldTypes = new LinkedHashMap<>();

    public FieldVisitor(GraphQLSchema schema) {
        this.schema = schema;
    }

    public void traverse(GraphQLType parentType, SelectionSet selectionSet, String parentPath, Map<String, FragmentDefinition> fragments) {
        if (selectionSet == null) return;

        for (Selection<?> selection : selectionSet.getSelections()) {
            if (selection instanceof Field field) {
                String fieldName = field.getName();
                boolean isList = GraphQLTypeUtil.isListType(parentType, fieldName);
                String fullPath = parentPath + "." + fieldName + (isList ? "[*]" : "");

                if (parentType instanceof GraphQLFieldsContainer container) {
                    GraphQLFieldDefinition fieldDef = container.getFieldDefinition(fieldName);

                    if (fieldDef != null) {
                        GraphQLType fieldType = GraphQLTypeUtil.unwrapType(fieldDef.getType());
                        fieldTypes.put(fullPath, fieldType.toString()); // getName() ???

                        if (fieldType instanceof GraphQLFieldsContainer || fieldType instanceof GraphQLInterfaceType || fieldType instanceof GraphQLUnionType) {
                            TypeHandler handler = TypeHandlerFactory.getHandler(fieldType);
                            handler.handle(fieldType, field.getSelectionSet(), this, fullPath, schema, fragments);
                        }
                    }
                }
            }
        }
    }

    public Map<String, String> getFieldTypes() {
        return fieldTypes;
    }
}