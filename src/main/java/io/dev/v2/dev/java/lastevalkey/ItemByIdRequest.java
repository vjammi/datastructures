package io.dev.v2.dev.java.lastevalkey;

public class ItemByIdRequest {

    LastEvaluatedKey lastEvaluatedKey;

    public ItemByIdRequest(LastEvaluatedKey lastEvaluatedKey) {
        this.lastEvaluatedKey = lastEvaluatedKey;
    }

    public void doSomething(){
        String key1 = lastEvaluatedKey.getLastEvaluatedKey();
        System.out.println(key1);
    }

}