package io.dev.v3.cf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


/**
 *      Creating simple CompletableFuture objects
 *      Consuming results of CompletableFutures
 *      Applying functions to CompletableFutures
 **/

public class CFSubject1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CFSubject1 obj = new CFSubject1();
        System.out.println("----");
        obj.processSupplyAsync("123456789");
        System.out.println("----");
        //obj.tryChaining2();
        System.out.println("----");
    }


    /**
     *  Executor Service
     * */

    public Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    /**
     *
     *
     **/

    private List<TransactionResponse> results = new ArrayList<>();

    public CompletableFuture<TransactionResponse> processSupplyAsync(String orderNumber) {
        Supplier<Transaction> fetchTxnSupplierFn = () -> fetchTransaction(orderNumber, "John Doe");
        Function<Transaction, Transaction> txnMapperFn1 = rawTxn -> new Transaction(rawTxn.account, rawTxn.accountName, rawTxn.balance + 100);
        Function<Transaction, EnrichedTransaction> txnMapperFn2 = txn -> new EnrichedTransaction(txn.account, txn);
        //txn -> simulateException(txn);
        //Function<Throwable, EnrichedTransaction> exceptionMapperFn = throwable -> { System.out.println(throwable.getMessage()); return null;};
        Function<EnrichedTransaction, TransactionResponse> txnMapperFn3 = enrichedTxn -> new TransactionResponse(enrichedTxn);
        Consumer<TransactionResponse> printConsumerFn = System.out::println;
        Runnable runnable = () -> System.out.println("Do something else...");
        RuntimeException completedExceptionally = new RuntimeException("completeExceptionally");

        CompletableFuture<TransactionResponse> response = CompletableFuture.supplyAsync(fetchTxnSupplierFn)
                .thenApply(txnMapperFn1)
                .thenApply(txnMapperFn2)
                //.thenRunAsync(() -> System.out.println(""))
                .thenApply(txnMapperFn3);

        return response;
    }

    public CompletableFuture<Void> processThenAccept(String orderNumber) {

        Supplier<Transaction> fetchTxnSupplierFn = () -> fetchTransaction(orderNumber, "John Doe");
        Function<Transaction, Transaction> txnMapperFn1 = rawTxn -> new Transaction(rawTxn.account, rawTxn.accountName, rawTxn.balance + 100);
        Function<Transaction, EnrichedTransaction> txnMapperFn2 = txn -> new EnrichedTransaction(txn.account, txn);
        //txn -> simulateException(txn);
        //Function<Throwable, EnrichedTransaction> exceptionMapperFn = throwable -> { System.out.println(throwable.getMessage()); return null;};
        Function<EnrichedTransaction, TransactionResponse> txnMapperFn3 = enrichedTxn -> new TransactionResponse(enrichedTxn);
        Consumer<TransactionResponse> printConsumerFn = System.out::println;
        Runnable runnable = () -> System.out.println("Do something else...");
        RuntimeException completedExceptionally = new RuntimeException("completeExceptionally");

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(fetchTxnSupplierFn)
                .thenApply(txnMapperFn1)
                .thenApply(txnMapperFn2)
                //.thenApply(txnMapperExceptionFn2)
                //.exceptionally(txnMapperExceptionFn2)
                .thenApply(txnMapperFn3)
                .thenAccept(transactionResponse -> results.add(transactionResponse)) ; //Note: thenAccept returns CompletableFuture<Void> not  CompletableFuture<Void>
        //.thenRun(runnable);
        //.completeOnTimeout(null, 1, TimeUnit.SECONDS);
        //.orTimeout(3, TimeUnit.SECONDS);
        //.complete(results);
        //.completeExceptionally(completedExceptionally);
        System.out.println("Results Size: " +results.size());

        return completableFuture;
    }


    public CompletableFuture<Void> processThenRunAsync() {
        List<String> groupTaskResults = new ArrayList<>();

        CompletableFuture<Void> taskGroup1 = CompletableFuture
                .runAsync(() -> {System.out.println("Run Task11 Async"); groupTaskResults.add("11");})
                .thenRunAsync(() -> {System.out.println("Run Task12 Async"); groupTaskResults.add("12");});

        CompletableFuture<Void> taskGroup2 = CompletableFuture
                .runAsync(() -> {System.out.println("Run Task21 Async"); groupTaskResults.add("21");});

        CompletableFuture<Void> combineTaskGroups = taskGroup1.runAfterBothAsync(taskGroup2, () -> groupTaskResults.add("33") );

        System.out.println(groupTaskResults);

        return combineTaskGroups;
    }

    public CompletableFuture<Void> processAcceptBothAsync() {
        List<String> bothTaskResults = new ArrayList<>();

        CompletableFuture<String> taskGroup1 = CompletableFuture
                .supplyAsync(() -> {System.out.println("Run Task11 Async"); return "11"; });

        CompletableFuture<String> taskGroup2 = CompletableFuture
                .supplyAsync(() -> {System.out.println("Run Task21 Async"); return "21"; });

        pauseSeconds(2);

        BiConsumer<String, String> acceptBothResults = (f, s) -> {
            bothTaskResults.add(f);
            bothTaskResults.add(s);
        };

        CompletableFuture<Void> bothTasks = taskGroup1.thenAcceptBothAsync(taskGroup2, acceptBothResults);
        pauseSeconds(4);
        System.out.println(bothTaskResults);

        return bothTasks;
    }

    public CompletableFuture<Void> processAcceptEither() {
        List<String> eitherTaskResults = new ArrayList<>();

        CompletableFuture<String> taskGroup1 = CompletableFuture
                .supplyAsync(() -> {System.out.println("Run Task11 Async"); return "11"; });

        CompletableFuture<String> taskGroup2 = CompletableFuture
                .supplyAsync(() -> {System.out.println("Run Task21 Async"); return "21"; });

        pauseSeconds(2);

        Consumer<String> addEither = f -> {
            eitherTaskResults.add(f);
        };
        CompletableFuture<Void> combineTaskGroups = taskGroup1.acceptEither(taskGroup2, addEither);
        System.out.println(eitherTaskResults);

        return combineTaskGroups;
    }


    /**
     * Applying functions to CompletableFutures
     **/

    public CompletableFuture<Transaction> processThenApply(String orderNumber) {
        CompletableFuture<Transaction> response = CompletableFuture.supplyAsync(() -> fetchTransaction(orderNumber, "John Doe"))
                .thenApply(rawTxn -> new Transaction(rawTxn.account, rawTxn.accountName, rawTxn.balance + 100));

        return response;
    }

    public CompletableFuture<List<Transaction>> processThenCombineAsync(String orderNumber) {
        CompletableFuture<Transaction> response1 = CompletableFuture.supplyAsync(() -> fetchTransaction(orderNumber, "John Doe"));
        CompletableFuture<Transaction> response2 = CompletableFuture.supplyAsync(() -> fetchTransaction(orderNumber, "John Doe"));

        BiFunction<Transaction, Transaction, List<Transaction>> combinedResultsBiFunction = (f, s) -> {
            List<Transaction> list = new ArrayList<>();
            list.add(f);
            list.add(s);
            return list;
        };
        CompletableFuture<List<Transaction>> combinedResponse = response1.thenCombineAsync(response2, combinedResultsBiFunction);
        return combinedResponse;
    }


    public CompletableFuture<Transaction> processThenCombineWithOneSuppliedValue(String orderNumber) {
        CompletableFuture<Transaction> asyncComputedResponse = CompletableFuture.supplyAsync(() -> fetchTransaction(orderNumber, "John Doe"));
        CompletableFuture<Transaction> knownResponse = CompletableFuture.completedFuture(fetchTransaction(orderNumber, "John Doe"));

        BinaryOperator<Transaction> binaryOperatorFunction = (f, s) -> new Transaction( f.account, s.accountName, 100);
        CompletableFuture<Transaction> combinedResponse = asyncComputedResponse.thenCombine(knownResponse, binaryOperatorFunction);

        return combinedResponse;
    }

    public CompletableFuture<Integer> processThenComposeAsync(String orderNumber) {
        Function<Integer, Supplier<List<Integer>>> getFirstTenMultiples = num -> () -> Stream.iterate(num, i -> i + num).limit(10).collect(Collectors.toList());
        Supplier<List<Integer>> multiplesSupplier = getFirstTenMultiples.apply(13);
        CompletableFuture<List<Integer>> getMultiples = CompletableFuture.supplyAsync(multiplesSupplier);

        Function<List<Integer>, CompletableFuture<Integer>> sumNumbers = multiples -> CompletableFuture.supplyAsync(() -> multiples.stream().mapToInt(Integer::intValue).sum());
        CompletableFuture<Integer> summedMultiples = getMultiples.thenComposeAsync(sumNumbers);

        return summedMultiples;
    }

    // https://stackoverflow.com/questions/43019126/completablefuture-thenapply-vs-thencompose
    // https://www.baeldung.com/java-completablefuture
    public CompletableFuture<Integer> processThenCompose(int userId) {
        // Get userInfo
        CompletableFuture<Integer> userInfoCF = CompletableFuture
                .supplyAsync(() -> getUserInfo(userId));

        // Pass the userInfo to get the userRating
        // Option 1: thenApply
        CompletableFuture<CompletableFuture<Integer>>  userRating1  = userInfoCF
                .thenApply(userInfo -> CompletableFuture.supplyAsync(() -> getUserRating(userInfo)));

        // Option 2: thenCompose
        CompletableFuture<Integer> userRating2 = userInfoCF
                .thenCompose( userInfo -> CompletableFuture.supplyAsync(() -> getUserRating(userInfo)));

        System.out.println(userRating2);
        return userRating2;
    }

    private static int getUserRating(Integer userInfo) {
        return userInfo + 10;
    }

    private static int getUserInfo(int userId) {
        return userId + 10;
    }

    public CompletableFuture<Void> processAllOf() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "There");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
        return combinedFuture;
    }


    /**
     * Handling Exceptions
     *
     */

    public CompletableFuture<String> processHandleExceptionsTest(String name){
        CompletableFuture<String> completableFuture
                =  CompletableFuture.supplyAsync(() -> returnResultsOrThrowAnException(name))
                                    .handle((data, exception) -> {
                                        System.out.println("Data " +data);
                                        System.out.println("Exception " +exception);
                                        return data != null ? data : "Hello, Stranger";
                                    })
                                    .thenApply(nam -> nam + "!");
        return completableFuture;
    }

    private static String returnResultsOrThrowAnException(String name) {
        if (name == null) {
            throw new RuntimeException("Computation error!");
        }
        return "Hello, " + name;
    }

    public CompletableFuture<String> processCompleteExceptionally(String name){
        CompletableFuture<String> completableFuture =
                CompletableFuture.supplyAsync(() -> returnResultsOrThrowAnException(name));
                completableFuture.completeExceptionally(new RuntimeException("Calculation failed!"));
        return completableFuture;
    }


    public CompletableFuture<String> processCompleteAndCompleteExceptionally(String url) {
        CompletableFuture<String> completableFuture =
                CompletableFuture.supplyAsync(() -> getResponse(url))
                .handle((data, exception) -> {
                    System.out.println("Data " +data);
                    System.out.println("Exception " +exception);
                    return data != null ? data : "{}";
                })
                .thenApply(resp -> resp +"!");
        return completableFuture;
    }
    private String getResponse(String url) {
        if (url == null) {
            throw new RuntimeException("Invalid Url!");
        }
        //var finalUrl = "http://localhost:8081/api/v1/product/add?" + url;
        //var request = HttpRequest.newBuilder().uri(new URI(finalUrl)).GET().build();
        //var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //return response.body();
        return "{SUCCESS RESPONSE}";
    }


    /**
     * Utility Methods
     **/

    private void pauseSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private EnrichedTransaction simulateException(Transaction txn) {
        if (txn.account.equals("1234567890"))
            throw new RuntimeException("RuntimeException... ");

        return new EnrichedTransaction(txn.account, txn);
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