package io.dev.v2.dev.graphql.parser.ooo;

import com.fasterxml.jackson.databind.JsonNode;
import graphql.language.Field;
import graphql.language.Selection;

class ArrayFieldValidator implements Validator {
    private final GraphQLQueryValidator queryValidator;

    public ArrayFieldValidator(GraphQLQueryValidator queryValidator) {
        this.queryValidator = queryValidator;
    }

    @Override
    public void validate(Field field, JsonNode jsonNode, ValidationContext context) {
        //System.out.println(field.getName() + " > ");
        if (!jsonNode.isArray()) {
            context.addError("❌ Expected an array for field: " + field.getName());
            return;
        }

        for (JsonNode item : jsonNode) {
            for (Selection<?> selection : field.getSelectionSet().getSelections()) {
                if (selection instanceof Field subField) {
                    queryValidator.validate(subField, item, context);
                }
            }
        }
    }
}