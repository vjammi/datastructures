package dev.vjammi.ds.v2.dev.frameworks.di.lastevalkey;

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
