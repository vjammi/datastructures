package dev.vjammi.ds.v2.dev.java.patterns.di.lastevalkey;

import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        Map<String, String> lastEvaluatedKey1 = new HashMap();
        lastEvaluatedKey1.put("key1", "1234567890");
        ItemByIdRequest request1 = new ItemByIdRequest(new LastEvaluatedReferenceNumber(lastEvaluatedKey1));
        request1.doSomething();

        Map<String, String> lastEvaluatedKey2 = new HashMap();
        lastEvaluatedKey2.put("key1", "9087654321");
        ItemByIdRequest request2 = new ItemByIdRequest(new LastEvaluatedUniqueId(lastEvaluatedKey2));
        request2.doSomething();
    }

}
