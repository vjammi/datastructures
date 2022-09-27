package ds.patterns.usage.concurrency;

import java.util.concurrent.*;

public class CountdownLatchUsage {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        spawnCallableTasksUsingCountDownLatch();
    }

    private static void spawnCallableTasksUsingCountDownLatch() throws InterruptedException, ExecutionException {
        System.out.println("Creating callable tasks ...");

        CountDownLatch latch = new CountDownLatch(3);

        CallableTaskVer2 task1 = new CallableTaskVer2(0, 10, latch);
        CallableTaskVer2 task2 = new CallableTaskVer2(1, 20, latch);
        CallableTaskVer2 task3 = new CallableTaskVer2(2, 30, latch);

        System.out.println("Launching executor thread pool  service ...");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Integer> future1 = executorService.submit(task1);
        Future<Integer> future2 = executorService.submit(task2);
        Future<Integer> future3 = executorService.submit(task3);

        latch.await();

        // Now get results once the tasks are completed. This will block until then
        Integer result1 = future1.get();
        System.out.println("Future Result for Task1 : " + result1);
        Integer result2 = future2.get();
        System.out.println("Future Result for Task2 : " + result2);
        Integer result3 = future3.get();
        System.out.println("Future Result for Task3 : " + result3);

        System.out.println("Shutdown the executor service ...");
        executorService.shutdown();
    }

}

class CallableTaskVer2 implements Callable<Integer> {
   private volatile boolean interrupt = false;
   private CountDownLatch latch;
   private int counter;
   private int taskNum;

    private Thread worker;

    public CallableTaskVer2(int taskNum, int counter, CountDownLatch latch) {
        this.taskNum = taskNum;
        this.counter = counter;
        this.latch = latch;
        System.out.println("Task Started ..." +taskNum);
    }


    public void start(FutureTask futureTask, int taskNum, int counter, CountDownLatch iterations) {
        worker = new Thread(futureTask);
        worker.start();
        this.taskNum = taskNum;
        this.counter = counter;
        this.latch = iterations;
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
            System.out.println(Thread.currentThread().getName() +" taskNum "+taskNum+ " running. counter "+ counter +". Task interrupted? " + interrupt);
            counter--;
        }

        System.out.println(Thread.currentThread().getName() +" taskNum "+taskNum+ " counter " + counter +" iterations. Task interrupted? " + interrupt);
        latch.countDown();
        return 100+taskNum;
    }

}