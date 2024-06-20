package dev.vjammi.ds.v2.dev.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceUsage {
    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            Runnable someTask = () -> {
                System.out.println(Thread.currentThread().getName());  //thread-1
            };

            executorService = Executors.newFixedThreadPool(3);   //pool-1
            // Submits a Runnable task for execution and returns a Future representing that task.
            // The Future's get method will return null upon successful completion.
            executorService.submit(someTask);
            executorService.submit(someTask);

            executorService = Executors.newFixedThreadPool(3);   //pool-2
            executorService.submit(someTask);
        } finally {
            executorService.shutdown();
        }
    }
}
