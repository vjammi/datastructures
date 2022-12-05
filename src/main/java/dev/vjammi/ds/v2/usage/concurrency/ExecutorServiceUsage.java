package dev.vjammi.ds.v2.usage.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceUsage {
    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(3);   //pool-1

            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName());  //thread-1
            });

            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName());  //thread-2
            });




            executorService = Executors.newFixedThreadPool(3);   //pool-2

            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName());  //thread-1
            });
        } finally {
            executorService.shutdown();
        }
    }
}
