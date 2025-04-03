package io.dev.v2.dev.graphql.parser.v3;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SimpleJsonFieldReader {

    public static void main(String[] args) {
        String jsonString = """ 
                       {
                            "firstName": "John", 
                            "address": { 
                                "street" : "123 Main St",
                                "city" : "Anytown",
                                "zip" : {
                                    "code": "32432"
                                }
                            }
                       }
                        """;
        String fieldNameToRead = "address.zip.code";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);

            String[] fieldParts = fieldNameToRead.split(".");
            JsonNode currentNode = rootNode;

            for (String fieldPart : fieldParts) {
                currentNode = currentNode.get(fieldPart);
                if (currentNode == null) {
                    System.out.println("Field '" + fieldNameToRead + "' not found.");
                    return;
                }
            }

            System.out.println("Value of '" + fieldNameToRead + "': " + currentNode.asText());

        } catch (IOException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
    }
}
