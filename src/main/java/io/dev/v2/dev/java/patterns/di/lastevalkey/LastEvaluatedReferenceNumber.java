package io.dev.v2.dev.java.patterns.di.lastevalkey;

import java.util.Map;

public class LastEvaluatedReferenceNumber implements LastEvaluatedKey{

    Map<String, String> lastEvaluatedKey;

    public LastEvaluatedReferenceNumber(Map<String, String> lastEvaluatedKey) {
        this.lastEvaluatedKey = lastEvaluatedKey;
    }

    @Override
    public String getLastEvaluatedKey() {
        System.out.println("LastEvaluatedReferenceNumber...");
        return lastEvaluatedKey.get("key1");
    }
}
