package io.dev.v2.dev.graphql.parser.v2;

class InlineFragmentValidatorTest {
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Test
//    void testValidInlineFragment() throws Exception {
//        String jsonResponse = """
//                {
//                  "user": {
//                    "id": 1,
//                    "name": "Alice",
//                    "__typename": "Admin",
//                    "role": "SuperAdmin"
//                  }
//                }
//                """;
//
//        ValidationContext context = new ValidationContext(Map.of("id", "Integer", "name", "String", "role", "String"));
//
//        Map<String, Validator> validators = new HashMap<>();
//        validators.put("role", new ScalarFieldValidator());
//
//        InlineFragmentValidator validator = new InlineFragmentValidator(validators);
//
//        InlineFragment inlineFragment = InlineFragment.newInlineFragment()
//                .typeCondition(new TypeName("Admin"))
//                .selectionSet(new SelectionSet(List.of(new Field("role"))))
//                .build();
//
//        Field userField = new Field("user", new SelectionSet(List.of(inlineFragment)));
//
//        //userField.setSelectionSet(new SelectionSet(List.of(inlineFragment)));
//
//        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
//        validator.validate(userField, jsonNode.get("user"), context);
//
//        assertFalse(context.hasErrors(), "No errors expected for a valid inline fragment.");
//    }
//
//    @Test
//    void testInlineFragmentWithWrongType() throws Exception {
//        String jsonResponse = """
//                {
//                  "user": {
//                    "id": 1,
//                    "name": "Alice",
//                    "__typename": "RegularUser"
//                  }
//                }
//                """;
//
//        ValidationContext context = new ValidationContext(Map.of("id", "Integer", "name", "String", "role", "String"));
//
//        Map<String, Validator> validators = new HashMap<>();
//        validators.put("role", new ScalarFieldValidator());
//
//        InlineFragmentValidator validator = new InlineFragmentValidator(validators);
//
//        InlineFragment inlineFragment = InlineFragment.newInlineFragment()
//                .typeCondition(new TypeName("Admin"))
//                .selectionSet(new SelectionSet(List.of(new Field("role"))))
//                .build();
//        Field userField = new Field("user", new SelectionSet(List.of(inlineFragment)));
//
//        //userField.setSelectionSet(new SelectionSet(List.of(inlineFragment)));
//
//        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
//        validator.validate(userField, jsonNode.get("user"), context);
//
//        assertFalse(context.hasErrors(), "No errors expected when inline fragment does not match type.");
//    }
}

