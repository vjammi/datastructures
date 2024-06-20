package dev.vjammi.ds.v2.dev.concurrency.async;

import java.util.Collection;
import java.util.concurrent.*;
import java.util.function.Function;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;


/**
 * https://youtu.be/9ueIL0SwEWI
 * https://stackoverflow.com/questions/54866391/mono-vs-completablefuture
 * https://schlining.medium.com/bridging-completeablefutures-and-rxjava-9dd6f0a9d188
 *
 * Stream                  CompletableFuture
 * 0, 1, more data         At most - 1 data or 1 error
 *
 *                          supplyAsync
 *
 * foEach - Consumer
 *          accept          thenAccept                      [ Pass consumer (data -> printIt(data)) ]
 *
 * map -    Function
 *          apply           thenApply
 *
 * reduce                  thenCombine                      [ Combines results of 2 async operations ]
 *
 * flatMap                 thenCompose
 *
 * ??? thenRun
 */
public class CompletableFutureUsage2 {

    public static void main(String[] args) throws InterruptedException {
        CompletableFutureUsage2 obj = new CompletableFutureUsage2();

        try {
            obj.tryChaining1();
            System.out.println("----");
            CompletableFuture<EnrichedTransaction> orderCompletableFuture = obj.tryChaining2();
            System.out.println(orderCompletableFuture.get());
            System.out.println("----");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void tryChaining1() {
        CompletableFuture
                .supplyAsync(() -> fetchTransaction("1234567890", "John Doe"))
                .thenApply(tran -> new Transaction(tran.account, tran.accountName, tran.balance + 100))
                .thenApply(tran -> new Transaction(tran.account, tran.accountName, tran.balance + 200))
                .thenAccept(order -> System.out.println(order));
    }

    private CompletableFuture<EnrichedTransaction>  tryChaining2() {

        EnrichedTransaction enrichedTransaction = new EnrichedTransaction("12312331", null);
        CompletableFuture<EnrichedTransaction> orderCF = CompletableFuture
                .supplyAsync(() -> fetchTransaction("1234567890", "John Doe"))
                .thenApply(transaction -> populatePhoneNumber(transaction))
                .thenApply(transaction -> populateAddress(transaction))
                .thenApply(transaction -> populateCityAndZip(transaction))
                .thenApply(transaction -> populateGeoLocation(transaction))
                .thenCompose(transaction5 -> { return result(enrichedTransaction); });
        return orderCF;
    }


    private CompletableFuture<EnrichedTransaction> result(EnrichedTransaction enrichedTransaction) {
        CompletableFuture<EnrichedTransaction> orderCompletableFuture = CompletableFuture.completedFuture(enrichedTransaction);
        System.out.println("result " + enrichedTransaction.toString());
        return orderCompletableFuture;
    }


    private void tryPipeline() {
        CompletableFuture
                .supplyAsync(() -> fetchTransaction("1234567890", "John Doe"))
                .thenApply(tran -> new Transaction(tran.account, tran.accountName, tran.balance + 200))
                .completeOnTimeout(null, 3, TimeUnit.SECONDS)
                .thenAccept(data -> System.out.println(data))
                .exceptionally(throwable -> {
                  return handle();
                });
                //.thenAccept(System.out::println);

    }

    private Void handle() {
        return null;
    }

    private Transaction handleErrorByRecovering(Throwable throwable) {
        System.err.println(throwable.getCause());
        return new Transaction(null, null, 0);
    }

    private void tryCombine() {
        int m = 5;
        int n = 2;
        Integer product = 0;

        CompletableFuture.supplyAsync(() -> m)
                .thenCombine(CompletableFuture.supplyAsync(() -> n), (a, b) -> {
                    return a * b;
                })
                .thenApply(a -> {System.out.println(a); return a;});

        System.out.println("Product "+ product);
    }

    //    public CompletionStage<Transaction> someMethod(Transaction transaction) {
    //        CompletableFuture<Transaction> shortCut = new CompletableFuture<>();
    //        CompletableFuture<Transaction2> withChain = new CompletableFuture<Transaction2>();
    //
    //        CompletableFuture.runAsync(() -> {
    //            if (transaction.balance < 0)
    //                withChain.complete(new Transaction2(null, null, 0));
    //            else
    //                shortCut.complete(null);
    //        });
    //
    //        return withChain
    //                .thenCompose(result -> someMethodThatReturnsACompletionStage(result))
    //                .thenApply(result -> result.someCondition() ? SomeResult.RESULT_2 : SomeResult.RESULT_3)
    //                .applyToEither(shortCut, Function.identity());
    //    }

    private <U> Transaction fetchTransaction(String accountId, String name) {
        Transaction transaction = new Transaction(accountId, name, System.currentTimeMillis());
        System.out.println("Transaction: " + transaction + " " + currentThread());
        return transaction;
    }

    private EnrichedTransaction populatePhoneNumber(Transaction transaction) {
        System.out.println("enrichTransaction  : " + transaction + " " + currentThread());
        EnrichedTransaction enrichedTransaction = new EnrichedTransaction("1234567890", transaction);
        return enrichedTransaction;
    }

    private EnrichedTransaction populateAddress(EnrichedTransaction enrichedTransaction) {
        System.out.println("enrichOrder  : " + enrichedTransaction + " " + currentThread());
        EnrichedTransaction enrichedTransaction2 = new EnrichedTransaction(enrichedTransaction.orderNumber +"1", enrichedTransaction.transaction);
        return enrichedTransaction2;
    }

    private EnrichedTransaction populateCityAndZip(EnrichedTransaction enrichedTransaction) {
        System.out.println("enrichOrder  : " + enrichedTransaction + " " + currentThread());
        EnrichedTransaction enrichedTransaction2 = new EnrichedTransaction(enrichedTransaction.orderNumber +"1", enrichedTransaction.transaction);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return enrichedTransaction2;
    }

    private EnrichedTransaction populateGeoLocation(EnrichedTransaction enrichedTransaction) {
        System.out.println("enrichOrder  : " + enrichedTransaction + " " + currentThread());
        EnrichedTransaction enrichedTransaction2 = new EnrichedTransaction(enrichedTransaction.orderNumber +"1", enrichedTransaction.transaction);
        return enrichedTransaction2;
    }

    private EnrichedTransaction doStep2(Transaction transaction) {
        EnrichedTransaction enrichedTransaction = new EnrichedTransaction("1234567890", transaction);
        System.out.println("Order: " + enrichedTransaction + " " + currentThread());
        return enrichedTransaction;
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

    private class Transaction {
        String account;
        String accountName;
        double balance;

        public Transaction(String account, String accountName, double balance) {
            this.account = account;
            this.accountName = accountName;
            this.balance = balance;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "account='" + account + '\'' +
                    ", accountName='" + accountName + '\'' +
                    ", createdOn='" + balance + '\'' +
                    '}';
        }
    }

    private class EnrichedTransaction {
        String orderNumber;
        Transaction transaction;

        public EnrichedTransaction(String orderNum, Transaction transaction) {
            this.orderNumber = orderNum;
            this.transaction = transaction;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "orderNumber='" + orderNumber + '\'' +
                    ", transaction=" + transaction +
                    '}';
        }
    }
}
