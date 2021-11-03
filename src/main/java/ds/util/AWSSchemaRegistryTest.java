package ds.util;

import com.amazonaws.services.schemaregistry.serializers.GlueSchemaRegistryKafkaSerializer;
import com.amazonaws.services.schemaregistry.serializers.json.JsonDataWithSchema;
import com.amazonaws.services.schemaregistry.utils.AWSSchemaRegistryConstants;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import software.amazon.awssdk.services.glue.model.Compatibility;
import software.amazon.awssdk.services.glue.model.DataFormat;
import java.util.Properties;

// https://github.com/awslabs/aws-glue-schema-registry/blob/master/README.md
public class AWSSchemaRegistryTest {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GlueSchemaRegistryKafkaSerializer.class.getName());
        properties.put(AWSSchemaRegistryConstants.DATA_FORMAT, DataFormat.JSON.name());
        properties.put(AWSSchemaRegistryConstants.AWS_REGION, "us-east-1");
        properties.put(AWSSchemaRegistryConstants.REGISTRY_NAME, "my-registry");
        properties.put(AWSSchemaRegistryConstants.SCHEMA_NAME, "my-schema");
        properties.put(AWSSchemaRegistryConstants.SCHEMA_AUTO_REGISTRATION_SETTING, true); // If not passed, defaults to false
        properties.put(AWSSchemaRegistryConstants.SCHEMA_NAMING_GENERATION_CLASS, "com.amazonaws.services.schemaregistry.serializers.avro.CustomerProvidedSchemaNamingStrategy");
        properties.put(AWSSchemaRegistryConstants.DESCRIPTION, "This registry is used for several purposes."); // If not passed, constructs a description
        properties.put(AWSSchemaRegistryConstants.COMPATIBILITY_SETTING, Compatibility.FULL); // Pass a compatibility mode. If not passed, uses Compatibility.BACKWARD

        // If not passed, defaults to no compression
        properties.put(AWSSchemaRegistryConstants.COMPRESSION_TYPE, AWSSchemaRegistryConstants.COMPRESSION.ZLIB.name());

        // In Memory cache is used by Producer to store schema to schema version id mapping and by consumer to store schema version id to schema mapping. This cache allows Producers and Consumers to save time and hits on IO calls to Schema Registry.
        // The cache is available by default. However, it can be fine-tuned by providing cache specific properties.
        properties.put(AWSSchemaRegistryConstants.CACHE_TIME_TO_LIVE_MILLIS, "60000"); // If not passed, defaults to 24 Hours
        properties.put(AWSSchemaRegistryConstants.CACHE_SIZE, "100"); // Maximum number of elements in a cache - If not passed, defaults to 200

        





        String jsonSchema = "{\n" + "        \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n"
                + "        \"type\": \"object\",\n" + "        \"properties\": {\n" + "          \"employee\": {\n"
                + "            \"type\": \"object\",\n" + "            \"properties\": {\n"
                + "              \"name\": {\n" + "                \"type\": \"string\"\n" + "              },\n"
                + "              \"age\": {\n" + "                \"type\": \"integer\"\n" + "              },\n"
                + "              \"city\": {\n" + "                \"type\": \"string\"\n" + "              }\n"
                + "            },\n" + "            \"required\": [\n" + "              \"name\",\n"
                + "              \"age\",\n" + "              \"city\"\n" + "            ]\n" + "          }\n"
                + "        },\n" + "        \"required\": [\n" + "          \"employee\"\n" + "        ]\n"
                + "}";
        System.out.println(jsonSchema);

        String jsonPayload = "{\n" + "        \"employee\": {\n" + "          \"name\": \"John\",\n" + "          \"age\": 30,\n"
                + "          \"city\": \"New York\"\n" + "}\n" + "";
        System.out.println(jsonPayload);
        JsonDataWithSchema dataWithSchema = JsonDataWithSchema.builder(jsonSchema, jsonPayload).build();
        System.out.println(dataWithSchema);
    }
}
