package io.dev.v2.dev.graphql.parser.v2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayFieldValidatorTest {
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Test
//    void testValidArrayField() throws Exception {
//        JsonNode jsonNode = objectMapper.readTree("{\"friends\": [{\"name\": \"Alice\"}, {\"name\": \"Bob\"}]}");
//        ValidationContext context = new ValidationContext(Map.of("friends", "Array", "name", "String"));
//
//        Map<String, Validator> validators = new HashMap<>();
//        validators.put("friends", new ArrayFieldValidator(new ObjectFieldValidator(validators)));
//
//        Field friendsField = new Field("friends", new SelectionSet(List.of(new Field("name"))));
//        // friendsField.setSelectionSet(new SelectionSet(List.of(new Field("name"))));
//
//        validators.get("friends").validate(friendsField, jsonNode, context);
//        assertFalse(context.hasErrors(), "No errors expected for valid array.");
//    }
//
//    @Test
//    void testInvalidArrayType() throws Exception {
//        JsonNode jsonNode = objectMapper.readTree("{\"friends\": \"invalidArray\"}");
//        ValidationContext context = new ValidationContext(Map.of("friends", "Array"));
//
//        Map<String, Validator> validators = new HashMap<>();
//        validators.put("friends", new ArrayFieldValidator(new ObjectFieldValidator(validators)));
//
//        Field friendsField = new Field("friends", new SelectionSet(List.of(new Field("name"))));
//        //friendsField.setSelectionSet(new SelectionSet(List.of(new Field("name"))));
//
//        validators.get("friends").validate(friendsField, jsonNode, context);
//        assertTrue(context.hasErrors(), "Error expected for invalid array type.");
//    }
}
