package io.dev.v2.dev.graphql.server;

import graphql.GraphQL;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.GraphQLSchema;

import java.io.File;
import java.io.IOException;

public class GraphQLProvider {
    private GraphQL graphQL;

    public void init() throws IOException {
        // Load SDL
        File schemaFile = new File("C:\\ds\\workspace\\datastructures\\src\\main\\resources\\graphql\\ schema.graphqls");

        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);

        RuntimeWiring runtimeWiring = buildRuntimeWiring();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);

        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("hello", new HelloDataFetcher()))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}

