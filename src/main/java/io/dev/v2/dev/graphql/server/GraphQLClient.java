package io.dev.v2.dev.graphql.server;

import graphql.ExecutionResult;

import java.io.IOException;

public class GraphQLClient {

    public static void main(String[] args) throws IOException {
        GraphQLProvider provider = new GraphQLProvider();
        provider.init();
        ExecutionResult executionResult = provider.getGraphQL().execute("{ hello }");
        System.out.println(executionResult.getData().toString()); // {hello=Hello World!}
    }

}
