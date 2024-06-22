package io.dev.v2.dev.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSetUsage {

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, AtomicInteger> concurrentMap = new ConcurrentHashMap<>();

        concurrentMap.put(1, new AtomicInteger(100));
        System.out.println(concurrentMap);

        System.out.println("Updated from 10 to 200: " +concurrentMap.get(1).compareAndSet(10, 200));
        System.out.println("Updated from 100 to 200: " +concurrentMap.get(1).compareAndSet(100, 200));
        System.out.println(concurrentMap);
    }

}
