package io.dev.v2.dev.graphql.server;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class HelloDataFetcher implements DataFetcher<String> {

    // DataFetcher<String> helloDataFetcher = environment -> "Hello World!";

    @Override
    public String get(DataFetchingEnvironment environment) throws Exception {
        return "Hello World";
    }
}
