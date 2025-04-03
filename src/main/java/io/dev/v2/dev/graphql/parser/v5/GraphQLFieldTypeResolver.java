package io.dev.v2.dev.graphql.parser.v5;

import graphql.schema.GraphQLSchema;

import java.io.IOException;
import java.util.Map;

public class GraphQLFieldTypeResolver {
    public static void main(String[] args) throws IOException {
        String schemaDirectory = "C:\\ds\\workspace\\datastructures\\src\\main\\java\\io\\dev\\v2\\dev\\graphql\\parser\\v4\\schema";
        GraphQLSchema schema = GraphQLSchemaFactory.loadSchema(schemaDirectory);

        String query = """
            query {
                user(id: \"123\") {
                    id
                    name
                    posts {
                        title
                        comments {
                            text
                            author {
                                username
                            }
                        }
                    }
                }
            }
        """;

        QueryFieldResolver resolver = new QueryFieldResolver(schema);
        Map<String, String> fieldTypes = resolver.resolve(query);
        fieldTypes.forEach((field, type) -> System.out.println(field + " -> " + type));
    }
}