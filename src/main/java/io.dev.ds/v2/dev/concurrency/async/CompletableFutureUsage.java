package dev.vjammi.ds.v2.dev.concurrency.async;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

import static java.lang.Thread.currentThread;


/**
 * https://youtu.be/9ueIL0SwEWI
 * https://stackoverflow.com/questions/54866391/mono-vs-completablefuture
 * https://schlining.medium.com/bridging-completeablefutures-and-rxjava-9dd6f0a9d188
 *
 * Stream                  CompletableFuture
 * 0, 1, more data         At most - 1 data or 1 error
 *
 * supplyAsync
 *
 * foEach - Consumer
 *          accept          thenAccept   - Pass consumer          (data -> printIt(data))
 *
 * thenRun
 *
 * map -    Function
 *          apply           thenApply
 *
 * reduce                  thenCombine - combines results of 2 async operations
 *
 * flatMap                 thenCompose
 */
public class CompletableFutureUsage {

    public static void main(String[] args) throws InterruptedException {
        CompletableFutureUsage obj = new CompletableFutureUsage();
        //obj.tryChaining();
        //Thread.sleep(10000);
        obj.tryCombine();
    }

    private void tryCombine() {
        int m = 5;
        int n = 2;
        Integer product = 0;

        CompletableFuture.supplyAsync(() -> m)
                .thenCombine(CompletableFuture.supplyAsync(() -> n), (a, b) -> {return a * b;})
                .thenApply(a -> {System.out.println(a); return a;});
        System.out.println("Product "+ product);
    }

    private void tryChaining() {
        CompletableFuture
                .supplyAsync(() -> doStep1("1234567890", "John Doe"))
                .thenAccept(transaction -> doStep2(transaction));
        System.out.println("-----");

        CompletableFuture
                .supplyAsync(() -> doStep1("1234567890", "John Doe"))
                .thenAccept(transaction -> doStep2(transaction));
                //.exceptionally(transaction -> handleErrorByRecovering());

    }

    private Object handleErrorByRecovering(Transaction transaction) {
        return null;
    }

    public static <T, R> CompletableFuture<Collection<R>> collectAll(Collection<T> items, Function<T, CompletableFuture<R>> fn) {

        // Storage for data as futures complete
        CopyOnWriteArrayList<R> returnValues = new CopyOnWriteArrayList<>();

        CompletableFuture[] futures = items.stream()
                .map(fn)
                .map(r -> r.thenAccept(returnValues::add))
                .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(futures)
                .thenApply(v -> returnValues);

    }

    private <U> Transaction doStep1(String accountId, String name) {
        Transaction transaction = new Transaction(accountId, name, System.currentTimeMillis());
        System.out.println("Transaction: " + transaction + " " + currentThread());
        return transaction;
    }

    private Order doStep2(Transaction transaction) {
        Order order = new Order(transaction);
        System.out.println("Order: " + order + " " + currentThread());
        return order;
    }

    private class Transaction {
        String account;
        String accountName;
        Long createdOn;

        public Transaction(String account, String accountName, Long createdOn) {
            this.account = account;
            this.accountName = accountName;
            this.createdOn = createdOn;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "account='" + account + '\'' +
                    ", accountName='" + accountName + '\'' +
                    ", createdOn='" + createdOn + '\'' +
                    '}';
        }
    }

    private class Order {
        String orderNumber;
        Transaction transaction;

        public Order(Transaction transaction) {
            this.orderNumber = "9876554434";
            this.transaction = transaction;
        }
    }
}
