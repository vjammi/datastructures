package ds.patterns.designsystems.concurrency;

public class InterruptingThreadExecution {

    public static void main(String[] args) {
        // Checking the thread’s interrupt status using a volatile boolean flag - When the thread is interrupted, we will stop the thread gracefully
        interruptThreadUsingAVolatileFlag();


        // Interrupt a thread's execution by calling the interrupt() method on the thread
        // Within the run method - check if the thread has been interrupted periodically using Thread.isInterrupted()
        // Note: Sending an interrupt is a nicer approach to stop a long-waiting thread.
        interruptTaskViaThreadDotIsInterruptedMethod();
    }

    private static void interruptThreadUsingAVolatileFlag() {
        TaskVer1 task1 = new TaskVer1();
        TaskVer1 task2 = new TaskVer1();

        task1.start();
        task2.start();

        try {
            Thread.sleep(10000);
            task1.stop();
            task2.stop();
        } catch (InterruptedException e) {
            System.out.println("Caught:" + e);
        }
    }

    private static void interruptTaskViaThreadDotIsInterruptedMethod() {
        TaskVer2 task1 = new TaskVer2();
        TaskVer2 task2 = new TaskVer2();

        task1.start();
        task2.start();

        try {
            Thread.sleep(1100);
            task1.interrupt();
            task2.interrupt();
        } catch (InterruptedException e) {
            System.out.println("Caught:" + e);
        }
    }

}

class TaskVer1 implements Runnable {

    private volatile boolean flag = false;
    private Thread worker;

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void stop() {
        flag = true;
    }

    @Override
    public void run() {

        while (!flag) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + " Running...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted," + e.getMessage());
            }
        }

        System.out.println(Thread.currentThread().getName() + " Stopped");
        return;

    }
}


class TaskVer2 implements Runnable {
    private Thread worker;

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void interrupt() {
        worker.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + " Running...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted with reason : " + e.getMessage());
            }
        }
        System.out.println(Thread.currentThread().getName() + " Stopped");
        return;
    }
}