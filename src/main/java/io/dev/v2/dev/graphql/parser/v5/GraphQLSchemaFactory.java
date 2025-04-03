package io.dev.v2.dev.graphql.parser.v5;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

class GraphQLSchemaFactory {
    public static GraphQLSchema loadSchema(String schemaDirectory) throws IOException {
        File folder = new File(schemaDirectory);
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        SchemaParser schemaParser = new SchemaParser();

        for (File file : Objects.requireNonNull(folder.listFiles((dir, name) -> name.endsWith(".graphqls")))) {
            String schemaContent = Files.readString(Paths.get(file.getAbsolutePath()));
            typeRegistry.merge(schemaParser.parse(schemaContent));
        }

        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring().build();
        return new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);
    }
}
