package dev.vjammi.ds.v2.dev.caching.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.graph.Graph;

import java.security.Key;
import java.util.concurrent.TimeUnit;

public class CaffeineCacheUsage {

    public static void main(String[] args) {
        Cache<Key, Graph> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();

        // Lookup an entry, or null if not found
        Key key = null;
        Graph graph = cache.getIfPresent(key);
        // Lookup and compute an entry if absent, or null if not computable
                graph = cache.get(key, k -> createExpensiveGraph(key));
        // Insert or update an entry
                cache.put(key, graph);
        // Remove an entry
                cache.invalidate(key);



    }

    private static Graph createExpensiveGraph(Key key) {
        return null;
    }
}
