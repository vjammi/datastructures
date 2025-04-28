package io.dev.v2.dev.graphql.schema.print;

import org.springframework.graphql.execution.GraphQlSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
@Component
public class SchemaPrinterBean {

    private final GraphQlSource graphQlSource;

    public SchemaPrinterBean(GraphQlSource graphQlSource) {
        this.graphQlSource = graphQlSource;
        printSchema();
    }
    public void printSchema() {
        graphql.schema.GraphQLSchema schema = graphQlSource.schema();

        graphql.schema.idl.SchemaPrinter schemaPrinter = new graphql.schema.idl.SchemaPrinter(
                graphql.schema.idl.SchemaPrinter.Options.defaultOptions()
                        .includeScalarTypes(true)
                        .includeSchemaDefinition(true)
                        .includeDirectives(true)
        );

        String printedSchema = schemaPrinter.print(schema);

        System.out.println(printedSchema); // <<< Prints your schema
    }

    public void printSchema2() {
        graphql.schema.GraphQLSchema schema = graphQlSource.schema();
        graphql.schema.idl.SchemaPrinter schemaPrinter = new graphql.schema.idl.SchemaPrinter();
        String printedSchema = schemaPrinter.print(schema);

        try {
            Files.write(Paths.get("schema.graphqls"), printedSchema.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
