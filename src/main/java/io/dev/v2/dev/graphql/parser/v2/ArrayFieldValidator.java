package io.dev.v2.dev.graphql.parser.v2;

import com.fasterxml.jackson.databind.JsonNode;
import graphql.language.Field;
import graphql.language.Selection;

class ArrayFieldValidator implements Validator {
    private final GraphQLQueryValidator queryValidator;

    public ArrayFieldValidator(GraphQLQueryValidator queryValidator) {
        this.queryValidator = queryValidator;
    }

    @Override
    public void validate(Field field, JsonNode jsonNode, ValidationContext context, String parentPath, StringBuffer depth) {
        //System.out.println(field.getName() + " > ");
        if (!jsonNode.isArray()) {
            context.addError("❌ Expected an array for field: " + field.getName());
            return;
        }

        for (JsonNode item : jsonNode) {
            for (Selection<?> selection : field.getSelectionSet().getSelections()) {
                if (selection instanceof Field subField) {
                    queryValidator.validate(subField, item, context, parentPath, depth);
                }
            }
        }
    }
}