package io.dev.v2.dev.graphql.parser.ooo;

import com.fasterxml.jackson.databind.JsonNode;
import graphql.language.Field;

public interface Validator {
    void validate(Field field, JsonNode jsonNode, ValidationContext context);
}
