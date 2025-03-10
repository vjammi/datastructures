package io.dev.v2.dev.graphql.parser.v2;

import com.fasterxml.jackson.databind.JsonNode;
import graphql.language.Field;
import graphql.language.InlineFragment;
import graphql.language.Selection;

class ObjectFieldValidator implements Validator {
    private final GraphQLQueryValidator queryValidator;

    public ObjectFieldValidator(GraphQLQueryValidator queryValidator) {
        this.queryValidator = queryValidator;
    }

    @Override
    public void validate(Field field, JsonNode jsonNode, ValidationContext context, String parentPath, StringBuffer depth) {
        if (!jsonNode.isObject()) {
            context.addError("❌ Expected an object for field: " + field.getName());
            return;
        }

        for (Selection<?> selection : field.getSelectionSet().getSelections()) {
            if (selection instanceof Field subField) {
                queryValidator.validate(subField, jsonNode, context, parentPath, depth);
            } else if (selection instanceof InlineFragment inlineFragment) {
                new InlineFragmentValidator(queryValidator).validate(inlineFragment, jsonNode, context, parentPath, depth);
            }
        }
    }
}