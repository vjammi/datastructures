package io.dev.v2.dev.graphql.parser.v2;


class ObjectFieldValidatorTest {
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Test
//    void testValidNestedObject() throws Exception {
//        JsonNode jsonNode = objectMapper.readTree("{\"profile\": {\"age\": 25}}");
//        ValidationContext context = new ValidationContext(Map.of("profile", "Object", "age", "Integer"));
//
//        Map<String, Validator> validators = new HashMap<>();
//        validators.put("profile", new ObjectFieldValidator(validators));
//
//        Field profileField = new Field("profile", new SelectionSet(List.of(new Field("age"))));
//        //profileField.setSelectionSet(new SelectionSet(List.of(new Field("age"))));
//
//        validators.get("profile").validate(profileField, jsonNode, context);
//        assertFalse(context.hasErrors(), "No errors expected for valid nested object.");
//    }
//
//    @Test
//    void testMissingNestedField() throws Exception {
//        JsonNode jsonNode = objectMapper.readTree("{\"profile\": {}}");
//        ValidationContext context = new ValidationContext(Map.of("profile", "Object", "age", "Integer"));
//
//        Map<String, Validator> validators = new HashMap<>();
//        validators.put("profile", new ObjectFieldValidator(validators));
//
//        Field profileField = new Field("profile", new SelectionSet(List.of(new Field("age"))));
//        //profileField.setSelectionSet(new SelectionSet(List.of(new Field("age"))));
//
//        validators.get("profile").validate(profileField, jsonNode, context);
//        assertTrue(context.hasErrors(), "Error expected for missing nested field.");
//        assertEquals("❌ Missing field: age", context.getErrors().get(0));
//    }
}
