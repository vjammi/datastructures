package io.dev.v2.dev.graphql.parser.v2;

import com.fasterxml.jackson.databind.JsonNode;
import graphql.language.Field;

public interface Validator {
    void validate(Field field, JsonNode jsonNode, ValidationContext context, String parentPath, StringBuffer depth);
}
