package io.dev.java.cf;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CFSubject1Test {


    @Test
    void thenSupplyTest() throws ExecutionException, InterruptedException {
        CFSubject1 subject = new CFSubject1();
        String orderNumber = "1234567890";
        CompletableFuture<CFSubject1.TransactionResponse> responseCf = subject.processSupplyAsync(orderNumber);
        pauseSeconds(1);
        assertEquals(orderNumber, responseCf.get().transaction.orderNumber);
    }

    @Test
    void thenAcceptTestWithResultTest() {
        CFSubject1 subject = new CFSubject1();
        CompletableFuture<Void> responseCf = subject.processThenAccept("1234567890");
        pauseSeconds(1);
        assertEquals(responseCf.isDone(), true);
    }

    @Test
    void runAfterBothAsyncTest() {
        CFSubject1 subject = new CFSubject1();
        CompletableFuture<Void> responseCf = subject.processThenRunAsync();
        pauseSeconds(1);
        assertEquals(responseCf.isDone(), true);
    }

    @Test
    void acceptEitherAsyncTest() {
        CFSubject1 subject = new CFSubject1();
        CompletableFuture<Void> responseCf = subject.processAcceptEither();
        pauseSeconds(1);
        assertEquals(responseCf.isDone(), true);
    }


    @Test
    void thenAcceptBothAsyncTest() {
        CFSubject1 subject = new CFSubject1();
        CompletableFuture<Void> responseCf = subject.processAcceptBothAsync();
        pauseSeconds(1);
        assertEquals(responseCf.isDone(), true);
    }


    /**
     * Applying functions to CompletableFutures
     **/

    @Test
    void thenApplyTest() throws ExecutionException, InterruptedException {
        CFSubject1 subject = new CFSubject1();
        String orderNumber = "1234567890";
        CompletableFuture<CFSubject1.Transaction> responseCf = subject.processThenApply(orderNumber);
        pauseSeconds(1);
        assertEquals(orderNumber, responseCf.get().account);
    }

    @Test
    void thenCombineAsyncTest() throws ExecutionException, InterruptedException {
        CFSubject1 subject = new CFSubject1();
        String orderNumber = "1234567890";
        CompletableFuture<List<CFSubject1.Transaction>> responseCf = subject.processThenCombineAsync(orderNumber);
        pauseSeconds(1);
        assertEquals(2, responseCf.get().size());
    }

    @Test
    void processThenComposeTest() throws ExecutionException, InterruptedException {
        CFSubject1 subject = new CFSubject1();
        CompletableFuture<Integer> responseCf = subject.processThenCompose(1);
        pauseSeconds(1);
        assertEquals(21, responseCf.get());
    }

    /**
     * Handling Exceptions
     *
     */
    @Test
    void handleExceptionsTest() throws ExecutionException, InterruptedException {
        CFSubject1 subject = new CFSubject1();

        CompletableFuture<String> responseCf1 = subject.processHandleExceptionsTest("VJ");
        assertEquals("Hello, VJ!", responseCf1.get());

        CompletableFuture<String> responseCf2 = subject.processHandleExceptionsTest(null);
        assertEquals("Hello, Stranger!", responseCf2.get());
    }

    @Test
    void completeExceptionallyTest() throws ExecutionException, InterruptedException {
        CFSubject1 subject = new CFSubject1();

        CompletableFuture<String> responseCf1 = subject.processCompleteExceptionally("VJ");
        assertThrows(ExecutionException.class, responseCf1::get);
    }


    @Test
    void completeAndCompleteExceptionallyTest() throws ExecutionException, InterruptedException {
        CFSubject1 subject = new CFSubject1();

        CompletableFuture<String>  responseCf1 = subject.processCompleteAndCompleteExceptionally("/foo");
        assertEquals(responseCf1.get(), "{SUCCESS RESPONSE}!");

        CompletableFuture<String> responseCf2 = subject.processCompleteAndCompleteExceptionally(null);
        assertEquals(responseCf2.get(), "{}!");
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
}