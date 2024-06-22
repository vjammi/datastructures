package io.dev.v2.dev.concurrency;

import java.util.concurrent.*;

public class CallableUsageWithInterruptsAndFuture {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        simpleCallableTask();
        spawnACallableTaskWithInterrupt();
        spawnCallableTasksUsingExecutorService();
    }

    private static void simpleCallableTask() throws InterruptedException {
        System.out.println("Creating a callable task ...");
        CallableTaskVer1 task = new CallableTaskVer1(0, 10);
        Integer result = task.call();
        System.out.println("Callable Result: " +result);
    }

    private static void spawnCallableTasksUsingExecutorService() throws InterruptedException, ExecutionException {
        System.out.println("Creating callable tasks ...");
        CallableTaskVer1 task1 = new CallableTaskVer1(0, 10);
        CallableTaskVer1 task2 = new CallableTaskVer1(1, 10);

        System.out.println("Launching executor thread pool  service ...");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = executorService.submit(task1);
        Future<Integer> future2 = executorService.submit(task2);

        // Sleep for 3000 ms
        Thread.sleep(3000);

        // Now get results once the tasks are completed. This will block until then
        Integer result1 = future1.get();
        System.out.println("Future Result for Task1 : " +result1);
        Integer result2 = future2.get();
        System.out.println("Future Result for Task2 : " +result2);


        //        // Cancel/Interrupt Task if needed after a timeout interval
        //        System.out.println("Interrupt Task1 after timeout ");
        //        future1.cancel(true);
        //        System.out.println("Interrupt Task2 after timeout");
        //        future2.cancel(true);

        System.out.println("Shutdown the executor service ...");
        executorService.shutdown();
    }

    private static void spawnACallableTaskWithInterrupt() throws InterruptedException, ExecutionException {
        CallableTaskVer1 task = new CallableTaskVer1(0, 10);
        FutureTask futureTask = new FutureTask(task);

        // Option 1
        task.start(futureTask, 0, 10);

        // Option 2
        // Thread worker = new Thread(futureTask);
        // worker.start();

        // Sleep for 3000 ms
        Thread.sleep(3000);

        // Then stop/interrupt Task after 3000 ms
        task.stop();

        // Now get Task Results
        System.out.println("Task Results: " +futureTask.get());
    }
}


class CallableTaskVer1 implements Callable<Integer> {

   private volatile boolean interrupt = false;
   private int counter;
   private int taskNum;

    private Thread worker;

    public CallableTaskVer1(int taskNum, int counter) {
        this.taskNum = taskNum;
        this.counter = counter;
        System.out.println("Task Started ..." +taskNum);
    }


    public void start(FutureTask futureTask, int taskNum, int counter) {
        worker = new Thread(futureTask);
        worker.start();
        this.taskNum = taskNum;
        this.counter = counter;
        System.out.println("Task Started..." +taskNum);
    }

    public void stop() {
        interrupt = true;
        System.out.println("Task Interrupted..."+taskNum);
    }

    @Override
    public Integer call() throws InterruptedException{

        while (!interrupt && counter > 0) {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() +" taskNum "+taskNum+ " running. Iteration # "+ counter +". Task interrupted? " + interrupt);
            counter--;
        }

        System.out.println(Thread.currentThread().getName() +" taskNum "+taskNum+ " completed after " + counter +" iterations. Task interrupted? " + interrupt);
        return 100+taskNum;
    }

}