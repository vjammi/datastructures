package io.dev.v2.dev.graphql.parser.ooo;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.language.Field;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ScalarFieldValidatorTest {
    private final ScalarFieldValidator validator = new ScalarFieldValidator();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testValidIntegerField() throws Exception {
        JsonNode jsonNode = objectMapper.readTree("{\"age\": 30}");
        ValidationContext context = new ValidationContext(Map.of("age", "Integer"));
        validator.validate(new Field("age"), jsonNode, context);
        assertFalse(context.hasErrors(), "No errors expected for correct integer field.");
    }

    @Test
    void testMissingField() throws Exception {
        JsonNode jsonNode = objectMapper.readTree("{}");
        ValidationContext context = new ValidationContext(Map.of("name", "String"));
        validator.validate(new Field("name"), jsonNode, context);
        assertTrue(context.hasErrors(), "Error expected for missing field.");
        assertEquals("❌ Missing field: name", context.getErrors().get(0));
    }

    @Test
    void testTypeMismatch() throws Exception {
        JsonNode jsonNode = objectMapper.readTree("{\"age\": \"thirty\"}");
        ValidationContext context = new ValidationContext(Map.of("age", "Integer"));
        validator.validate(new Field("age"), jsonNode, context);
        assertTrue(context.hasErrors(), "Error expected for type mismatch.");
    }
}
