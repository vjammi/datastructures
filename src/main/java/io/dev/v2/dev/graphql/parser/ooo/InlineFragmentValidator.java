package io.dev.v2.dev.graphql.parser.ooo;

import com.fasterxml.jackson.databind.JsonNode;
import graphql.language.Field;
import graphql.language.InlineFragment;
import graphql.language.Selection;

class InlineFragmentValidator implements Validator {
    private final GraphQLQueryValidator queryValidator;

    public InlineFragmentValidator(GraphQLQueryValidator queryValidator) {
        this.queryValidator = queryValidator;
    }

    @Override
    public void validate(Field field, JsonNode jsonNode, ValidationContext context) {
        if (!jsonNode.isObject()) {
            context.addError("❌ Inline fragment expected an object for field: " + field.getName());
            return;
        }

        for (Selection<?> selection : field.getSelectionSet().getSelections()) {
            if (selection instanceof Field subField) {
                queryValidator.validate(subField, jsonNode, context);
            }
        }
    }

    public void validate(InlineFragment fragment, JsonNode jsonNode, ValidationContext context) {
        if (!jsonNode.isObject()) {
            context.addError("❌ Expected an object for inline fragment.");
            return;
        }

        for (Selection<?> selection : fragment.getSelectionSet().getSelections()) {
            if (selection instanceof Field subField) {
                queryValidator.validate(subField, jsonNode, context);
            }
        }
    }
}