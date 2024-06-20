package io.dev.java.cf;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.hamcrest.CoreMatchers.is;

/*
https://junit.org/junit5/docs/current/user-guide/#writing-tests-assertions
Assertions.assertAll;
Assertions.assertEquals;
Assertions.assertNotNull;
Assertions.assertThrows;
Assertions.assertTimeout;
Assertions.assertTimeoutPreemptively;
Assertions.assertTrue;
*/

class CFSubject2Test {

    private static ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
    private List<String> results = new ArrayList<>();


    /**
     * Examples of creating simple CompletableFuture objects
     **/
    @Test
    public void test_completed_future() throws Exception {
        String expectedValue = "the expected value";
        String actualValue = "the expected value";
        CompletableFuture<String> alreadyCompleted = CompletableFuture.completedFuture(actualValue);
        String expected = alreadyCompleted.get();
        assertEquals(expected, expectedValue);
    }

    @Test
    public void test_runAsync() throws Exception {
        CompletableFuture<Void> runAsync = CompletableFuture
                .runAsync(() -> System.out.println("running async task"), newCachedThreadPool);
        pauseSeconds(1);
        assertEquals(runAsync.isDone(), true);
    }

    @Test
    public void test_supplyAsync() throws Exception {
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(simulatedTask(1, "Final Result"), newCachedThreadPool);

        assertEquals(completableFuture.get(), "Final Result");
    }


    /**
     * Consuming results of CompletableFutures
     * */

    @Test
    public void test_thenAcceptResult()  {

        CompletableFuture<Void> acceptingTask = CompletableFuture
                .supplyAsync(simulatedTask(1, "add when done"), newCachedThreadPool)
                .thenAccept(results::add);
        pauseSeconds(2);
        assertEquals(acceptingTask.isDone(), true);
        assertEquals(results.size(), 1);
        assertEquals(results.contains("add when done"), true);

    }

    @Test
    public void test_then_run_async() throws Exception {
        Map<String, String> cache = new HashMap<>();
        cache.put("key", "value");

        CompletableFuture<String> taskUsingCache = CompletableFuture.supplyAsync(simulatedTask(1, cache.get("key")), newCachedThreadPool);
        CompletableFuture<Void> cleanUp = taskUsingCache.thenRunAsync(cache::clear, newCachedThreadPool);

        cleanUp.get();
        String theValue = taskUsingCache.get();
        assertEquals(cache.isEmpty(), true);
        assertEquals(theValue, "value");
    }

    @Test
    public void test_run_after_both() throws Exception {

        CompletableFuture<Void> run1 = CompletableFuture.runAsync(() -> {
            pauseSeconds(2);
            results.add("first task");
        }, newCachedThreadPool);

        CompletableFuture<Void> run2 = CompletableFuture.runAsync(() -> {
            pauseSeconds(3);
            results.add("second task");
        }, newCachedThreadPool);

        CompletableFuture<Void> finisher = run1.runAfterBothAsync(run2, () -> results.add(results.get(0) + "&" + results.get(1)), newCachedThreadPool);
        pauseSeconds(4);
        assertEquals(finisher.isDone(), true);
        assertEquals(results.get(2), "first task&second task");
    }

    @Test
    public void test_run_after_either() throws Exception {
        CompletableFuture<Void> run1 = CompletableFuture.runAsync(() -> {
            pauseSeconds(2);
            results.add("should be first");
        }, newCachedThreadPool);

        CompletableFuture<Void> run2 = CompletableFuture.runAsync(() -> {
            pauseSeconds(3);
            results.add("should be second");
        }, newCachedThreadPool);

        CompletableFuture<Void> finisher = run1.runAfterEitherAsync(run2, () -> results.add(results.get(0).toUpperCase()), newCachedThreadPool);
        pauseSeconds(4);
        assertEquals(finisher.isDone(), true);
        assertEquals(results.get(1), "SHOULD BE FIRST");
    }

    @Test
    public void test_accept_either_async_calling_finishes_first() throws Exception {

        CompletableFuture<String> callingCompletable = CompletableFuture.supplyAsync(simulatedTask(1, "calling"), newCachedThreadPool);
        CompletableFuture<String> nestedCompletable = CompletableFuture.supplyAsync(simulatedTask(2, "nested"), newCachedThreadPool);

        CompletableFuture<Void> collector = callingCompletable.acceptEither(nestedCompletable, results::add);

        pauseSeconds(2);
        assertEquals(collector.isDone(), true);
        assertEquals(results.size(), 1);
        assertEquals(results.contains("calling"), true);
    }


    @Test
    public void test_accept_either_async_nested_finishes_first() throws Exception {

        CompletableFuture<String> callingCompletable = CompletableFuture.supplyAsync(simulatedTask(2, "calling"), newCachedThreadPool);
        CompletableFuture<String> nestedCompletable = CompletableFuture.supplyAsync(simulatedTask(1, "nested"), newCachedThreadPool);

        CompletableFuture<Void> collector = callingCompletable.acceptEither(nestedCompletable, results::add);

        pauseSeconds(2);
        assertEquals(collector.isDone(), true);
        assertEquals(results.size(), 1);
        assertEquals(results.contains("nested"), true);
    }

    @Test
    public void test_accept_both_async() throws Exception {

        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(simulatedTask(3, "first task"), newCachedThreadPool);
        CompletableFuture<String> secondTask = CompletableFuture.supplyAsync(simulatedTask(2, "second task"), newCachedThreadPool);

        BiConsumer<String, String> acceptBothResults = (f, s) -> {
            results.add(f);
            results.add(s);
        };


        CompletableFuture<Void> bothTasks = firstTask.thenAcceptBothAsync(secondTask, acceptBothResults);
        pauseSeconds(4);
        assertEquals(bothTasks.isDone(), true);
        assertEquals(results.size(), 2);
        assertEquals(results.get(0), "first task");
        assertEquals(results.get(1), "second task");

    }

    /**
     * Applying functions to CompletableFutures
     **/
    @Test
    public void test_apply() throws Exception {
        CompletableFuture<String> task = CompletableFuture.supplyAsync(simulatedTask(1, "change me"), newCachedThreadPool)
                .thenApply(String::toUpperCase);
        assertEquals(task.get(), "CHANGE ME");
    }


    @Test
    public void test_then_combine_async() throws Exception {
        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(simulatedTask(3, "combine all"), newCachedThreadPool);
        CompletableFuture<String> secondTask = CompletableFuture.supplyAsync(simulatedTask(2, "task results"), newCachedThreadPool);
        CompletableFuture<String> combined = firstTask.thenCombineAsync(secondTask, (f, s) -> f + " " + s, newCachedThreadPool);

        assertEquals(combined.get(), "combine all task results");
    }

    @Test
    public void test_then_combine_with_one_supplied_value() throws Exception {
        CompletableFuture<String> asyncComputedValue = CompletableFuture.supplyAsync(simulatedTask(2, "calculated value"), newCachedThreadPool);
        CompletableFuture<String> knowValueToCombine = CompletableFuture.completedFuture("known value");

        BinaryOperator<String> calcResults = (f, s) -> "taking a " + f + " then adding a " + s;
        CompletableFuture<String> combined = asyncComputedValue.thenCombine(knowValueToCombine, calcResults);

        assertEquals(combined.get(), "taking a calculated value then adding a known value");

    }

    @Test
    public void test_then_compose() throws Exception {

        Function<Integer, Supplier<List<Integer>>> getFirstTenMultiples = num -> () -> Stream.iterate(num, i -> i + num).limit(10).collect(Collectors.toList());
        Supplier<List<Integer>> multiplesSupplier = getFirstTenMultiples.apply(13);
        CompletableFuture<List<Integer>> getMultiples = CompletableFuture.supplyAsync(multiplesSupplier, newCachedThreadPool);

        Function<List<Integer>, CompletableFuture<Integer>> sumNumbers = multiples -> CompletableFuture.supplyAsync(() -> multiples.stream().mapToInt(Integer::intValue).sum());
        CompletableFuture<Integer> summedMultiples = getMultiples.thenComposeAsync(sumNumbers, newCachedThreadPool);

        assertEquals(summedMultiples.get(), 715);
    }


    @Test
    public void test_several_stage_combinations() throws Exception {
        CompletableFuture<String> stage1 = CompletableFuture.completedFuture("the quick ");
        CompletableFuture<String> stage2 = CompletableFuture.completedFuture("brown fox ");
        CompletableFuture<String> stage3 = stage1.thenCombine(stage2, (s1, s2) -> s1 + s2);

        Function<String, CompletableFuture<String>> upperCaseFunction = s -> CompletableFuture.completedFuture(s.toUpperCase());
        CompletableFuture<String> stage4 = stage3.thenCompose(upperCaseFunction);

        CompletableFuture<String> stage5 = CompletableFuture.supplyAsync(simulatedTask(2, "jumped over"));
        CompletableFuture<String> stage6 = stage4.thenCombineAsync(stage5, (s1, s2) -> s1 + s2, newCachedThreadPool);
        CompletableFuture<String> stage6_sub_1_slow = CompletableFuture.supplyAsync(simulatedTask(4, "fell into"));
        CompletableFuture<String> stage7 = stage6.applyToEitherAsync(stage6_sub_1_slow, String::toUpperCase, newCachedThreadPool);
        CompletableFuture<String> stage8 = CompletableFuture.supplyAsync(simulatedTask(3, " the lazy dog"), newCachedThreadPool);
        CompletableFuture<String> finalStage = stage7.thenCombineAsync(stage8, (s1, s2) -> s1 + s2, newCachedThreadPool);

        assertEquals(finalStage.get(), "THE QUICK BROWN FOX JUMPED OVER the lazy dog");

    }

    private Supplier<String> simulatedTask(int numSeconds, String taskResult) {
        return () -> {
            try {
                Thread.sleep(numSeconds * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return taskResult;
        };
    }


    private void pauseSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}