package ds.patterns.usage.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSetUsage {

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, AtomicInteger> concurrentMap = new ConcurrentHashMap<>();

        concurrentMap.put(1, new AtomicInteger(100));
        concurrentMap.put(2, new AtomicInteger(200));

        System.out.println(concurrentMap);
        concurrentMap.get(1).compareAndSet(10, 200);
        System.out.println(concurrentMap);
        concurrentMap.get(1).compareAndSet(100, 200);
        System.out.println(concurrentMap);

        concurrentMap.get(2).compareAndSet(20, 400);
        System.out.println(concurrentMap);
        concurrentMap.get(2).compareAndSet(200, 400);
        System.out.println(concurrentMap);
    }

}
