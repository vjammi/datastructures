package dev.vjammi.ds.v2.dev.libraries;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class ParseJsonUsingGson {

    public static void main(String[] args) throws IOException {
        parseJsonFile();
        readCharbyCharFromFileUsingFileReader();
        readFileUsingBufferedReader();
    }

    private static void parseJsonFile() throws FileNotFoundException {
        Reader reader = new FileReader("src/main/resources/payload.json");
        //Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/payload.json"));
        JsonReader jsonReader = new JsonReader(reader);

        // JsonElement: A class representing an element of JSON. It could either be a JsonObject, a JsonArray, a JsonPrimitive or a JsonNull.
        JsonElement jsonElement = Streams.parse(jsonReader);
        JsonObject payload = jsonElement.getAsJsonObject();
        System.out.println("\nPayload " +payload);

        // JsonObject
        JsonObject header = payload.getAsJsonObject("header");
        System.out.println("\nHeader " +header);
        System.out.println("id "+ header.get("id").toString());
        System.out.println("timestamp: " +header.get("timestamp").toString());

        // JsonArray
        JsonArray message = payload.getAsJsonArray("message");
        System.out.println("\nMessage " +message);
        for (int i=0; i<message.size(); i++) {
            JsonObject record = message.get(i).getAsJsonObject();
            System.out.println("Record " +i +": "+ record);
            System.out.println("Name " +i +": "+ record.get("name"));
        }
    }

    private static void readCharbyCharFromFileUsingFileReader() throws IOException {
        Reader reader = new FileReader("src/main/resources/payload.json");
        int content;
        StringBuilder builder = new StringBuilder();
        while ((content = reader.read()) != -1) {
            char chars = (char) content;
            builder.append(chars);
        }
        System.out.print(builder);
    }


    private static void readFileUsingBufferedReader() throws FileNotFoundException {
        Reader reader = new FileReader("src/main/resources/payload.json");
        try (BufferedReader br = new BufferedReader(reader)){
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
               builder.append(line);
            }
            System.out.print(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
