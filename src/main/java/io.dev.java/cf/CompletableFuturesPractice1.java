package io.dev.java.cf;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.Thread.currentThread;

/**
 * https://youtu.be/9ueIL0SwEWI
 * https://stackoverflow.com/questions/54866391/mono-vs-completablefuture
 * https://schlining.medium.com/bridging-completeablefutures-and-rxjava-9dd6f0a9d188
  ---------------------------------------------------------------------------------------------------------
   Stream                                           CompletableFuture
  ---------------------------------------------------------------------------------------------------------
  0, 1, more data                                  At most - 1 data or 1 error

                      NA                           supplyAsync  [Creates a CompletableFuture. Source of data]
  forEach  Consumer   accept                       thenAccept   [Pass a consumer - CompletableFuture<Void> thenAccept(Consumer<? super T> action)]   [(data -> printIt(data)) ]
  map Function        apply                        thenApply
  filter Predicate                                 NA
  reduce                                           thenCombine  [Combines results of 2 async operations]
  flatMap                                          thenCompose [1 to many]
                                                   thenRun
 */

public class CompletableFuturesPractice1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuturesPractice1 obj = new CompletableFuturesPractice1();
        System.out.println("----");
        obj.tryChaining1();
        System.out.println("----");
        //obj.tryChaining2();
        System.out.println("----");
    }

    private void tryChaining1() throws ExecutionException, InterruptedException {

        Supplier<Transaction> fetchTxnSupplierFn = () -> fetchTransaction("1234567890", "John Doe");
        Function<Transaction, Transaction> txnMapperFn1 = rawTxn -> new Transaction(rawTxn.account, rawTxn.accountName, rawTxn.balance + 100);
        Function<Transaction, EnrichedTransaction> txnMapperFn2 = txn -> new EnrichedTransaction(txn.account, txn);
        Function<Throwable, EnrichedTransaction> exceptionMapperFn = throwable -> { System.out.println(throwable.getMessage()); return null;};
        Function<EnrichedTransaction, TransactionResponse> txnMapperFn3 = enrichedTxn -> new TransactionResponse(enrichedTxn);
        Consumer<TransactionResponse> printConsumerFn = System.out::println;
        Runnable runnable = () -> System.out.println("Do something else...");
        RuntimeException completedExceptionally = new RuntimeException("completeExceptionally");

        CompletableFuture<TransactionResponse> orderCF = new CompletableFuture<>();
        orderCF.supplyAsync(fetchTxnSupplierFn)
                .thenApply(txnMapperFn1)
                .thenApply(txnMapperFn2)
                .exceptionally(exceptionMapperFn)
                .thenApply(txnMapperFn3)
                .thenAccept(printConsumerFn)
                .thenRun(runnable)
                //.completeOnTimeout(null, 3, TimeUnit.SECONDS)
                .orTimeout(3, TimeUnit.SECONDS);
                //.complete();
                //.completeExceptionally(completedExceptionally);

        TransactionResponse transactionResponse = orderCF.get();
        System.out.println(transactionResponse);

        //        CompletableFuture<EnrichedTransaction> orderCF1 = new CompletableFuture<>();
        //        orderCF1
        //                .supplyAsync(() -> fetchTransaction("1234567890", "John Doe"))                     // Run the first task on background thread - some web service
        //                .thenApply(tran -> new Transaction(tran.account, tran.accountName, tran.balance + 100))   // map - transformation
        //                .thenApply(tran -> new Transaction(tran.account, tran.accountName, tran.balance + 200))   // map - transformation
        //                .thenAccept(order -> System.out.println(order))
        //                .thenRun(() -> System.out.println("done"))
        //                .thenRun(() -> System.out.println("Not really done"));
    }

    private CompletableFuture<EnrichedTransaction> tryChaining2() {

        EnrichedTransaction enrichedTransaction = new EnrichedTransaction("12312331", null);
        CompletableFuture<EnrichedTransaction> orderCF = CompletableFuture
                .supplyAsync(() -> fetchTransaction("1234567890", "John Doe"))
                .thenApply(transaction -> populatePhoneNumber(transaction))
                .thenApply(transaction -> populateAddress(transaction))
                .thenApply(transaction -> populateCityAndZip(transaction))
                .thenApply(transaction -> populateGeoLocation(transaction))
                .thenCompose(transaction5 -> {
                    return result(enrichedTransaction);
                });
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
                .thenApply(a -> {
                    System.out.println(a);
                    return a;
                });

        System.out.println("Product " + product);
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

    private Transaction fetchTransaction(String accountId, String name) {
        //List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction(accountId, name, System.currentTimeMillis());
        System.out.println("Raw Transaction: " + transaction + " " + currentThread());
        //transactions.add(transaction);
        return transaction;
    }

    private EnrichedTransaction populatePhoneNumber(Transaction transaction) {
        System.out.println("enrichTransaction  : " + transaction + " " + currentThread());
        EnrichedTransaction enrichedTransaction = new EnrichedTransaction("1234567890", transaction);
        return enrichedTransaction;
    }

    private EnrichedTransaction populateAddress(EnrichedTransaction enrichedTransaction) {
        System.out.println("enrichOrder  : " + enrichedTransaction + " " + currentThread());
        EnrichedTransaction enrichedTransaction2 = new EnrichedTransaction(enrichedTransaction.orderNumber + "1", enrichedTransaction.transaction);
        return enrichedTransaction2;
    }

    private EnrichedTransaction populateCityAndZip(EnrichedTransaction enrichedTransaction) {
        System.out.println("enrichOrder  : " + enrichedTransaction + " " + currentThread());
        EnrichedTransaction enrichedTransaction2 = new EnrichedTransaction(enrichedTransaction.orderNumber + "1", enrichedTransaction.transaction);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return enrichedTransaction2;
    }

    private EnrichedTransaction populateGeoLocation(EnrichedTransaction enrichedTransaction) {
        System.out.println("Enrich order  : " + enrichedTransaction + " " + currentThread());
        EnrichedTransaction enrichedTransaction2 = new EnrichedTransaction(enrichedTransaction.orderNumber + "1", enrichedTransaction.transaction);
        return enrichedTransaction2;
    }

    private EnrichedTransaction doStep2(Transaction transaction) {
        EnrichedTransaction enrichedTransaction = new EnrichedTransaction("1234567890", transaction);
        System.out.println("Enriched Transaction: " + enrichedTransaction + " " + currentThread());
        return enrichedTransaction;
    }

    private TransactionResponse doStep3(EnrichedTransaction enrichedTransaction) {
        TransactionResponse response = new TransactionResponse(enrichedTransaction);
        System.out.println("Response: " + response + " " + currentThread());
        return response;
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

    class Transaction {
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

    class EnrichedTransaction {
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
    class TransactionResponse {
        EnrichedTransaction transaction;

        public TransactionResponse(EnrichedTransaction transaction) {
            this.transaction = transaction;
        }

        @Override
        public String toString() {
            return "TransactionResponse{" +
                    "transaction=" + transaction +
                    '}';
        }
    }
}