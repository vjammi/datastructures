package dev.vjammi.ds.v2.dev.frameworks.di.lastevalkey;

import java.util.Map;

public class LastEvaluatedUniqueId implements LastEvaluatedKey {

    private Map<String, String> lastEvaluatedKey;

    public LastEvaluatedUniqueId(Map<String, String> lastEvaluatedKey) {
        this.lastEvaluatedKey = lastEvaluatedKey;
    }

    @Override
    public String getLastEvaluatedKey() {
        System.out.println("LastEvaluatedUniqueId...");
        return lastEvaluatedKey.get("key1");
    }
}
