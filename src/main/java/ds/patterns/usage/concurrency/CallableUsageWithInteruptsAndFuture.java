package ds.patterns.usage.concurrency;

import java.util.concurrent.*;

public class CallableUsageWithInteruptsAndFuture {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        simpleCallableTask();
        spawnACallableTaskWithInterrupt();
        spawnCallableTasksUsingExecutorService();
    }

    private static void simpleCallableTask() {
        System.out.println("Creating a callable task ...");
        CallableTaskVer1 task = new CallableTaskVer1(0);
        Integer result = task.call();
        System.out.println("Callable Result: " +result);
    }

    private static void spawnCallableTasksUsingExecutorService() throws InterruptedException, ExecutionException {
        System.out.println("Creating callable tasks ...");
        CallableTaskVer1 task1 = new CallableTaskVer1(0);
        CallableTaskVer1 task2 = new CallableTaskVer1(1);

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
        CallableTaskVer1 task = new CallableTaskVer1(0);
        FutureTask[] futureTask = new FutureTask[1];
        futureTask[0] = new FutureTask(task);

        // Option 1
        task.start(futureTask[0], 0);

        // Option 2
        // Thread worker = new Thread(futureTask[0]);
        // worker.start();

        // Sleep for 3000 ms
        Thread.sleep(3000);

        // Then stop/interrupt Task after 3000 ms
        task.stop();

        // Now get Task Results
        System.out.println("Task Results: " +futureTask[0].get());
    }
}


class CallableTaskVer1 implements Callable<Integer> {

   private volatile boolean interrupt = false;
   private volatile int iterations = 0;
   private int taskNum;

    private Thread worker;

    public CallableTaskVer1(int taskNum) {
        this.taskNum = taskNum;
        System.out.println("Task Started..." +taskNum);
    }


    public void start(FutureTask futureTask, int taskNum) {
        worker = new Thread(futureTask);
        worker.start();
        this.taskNum = taskNum;
        System.out.println("Task Started..." +taskNum);
    }

    public void stop() {
        interrupt = true;
        System.out.println("Task Interrupted..."+taskNum);
    }

    @Override
    public Integer call() {
        while (!interrupt && iterations < 10) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() +" taskNum "+taskNum+ " running. Iteration # "+ iterations +". Task interrupted? " + interrupt);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted," + e.getMessage());
            }
            iterations++;
        }
        System.out.println(Thread.currentThread().getName() +" taskNum "+taskNum+ " completed after " + iterations +" iterations. Task interrupted? " + interrupt);
        return iterations + 100 + taskNum;
    }

}