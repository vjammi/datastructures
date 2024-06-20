package io.dev.java.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFuturesInExecutorService {

    public static void main(String args[]) throws Exception {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        CompletableFuture<String> root = new CompletableFuture<>();
        executor.submit(() -> {
            CompletableFuture<String> cf1 = CompletableFuture.completedFuture("first");
            CompletableFuture<String> cf2 = CompletableFuture.completedFuture("second");
            //...
            CompletableFuture<String> cf10 = CompletableFuture.completedFuture("ten");
            System.out.println("running");

            CompletableFuture<Void> allOfCompletableFuture = CompletableFuture.allOf(cf1, cf2, cf10);
            allOfCompletableFuture
            .thenRun(() -> {
                    root.complete(" Completed");
            });
        });

        // once the internal 10 have completed (successfully)
        root.thenAccept(r -> {
            System.out.println(r.toString()); // "some value"
        });

        Thread.sleep(10000);
        executor.shutdown();
    }
}
