package ds.patterns.usage.concurrency;

public class RunnableUsageWithInterrupts {

    public static void main(String[] args) throws InterruptedException {
        // Checking the thread’s interrupt status using a volatile boolean flag - When the thread is interrupted, we will stop the thread gracefully
        interruptThreadUsingAVolatileFlag();


        // Interrupt a thread's execution by calling the interrupt() method on the thread
        // Within the run method - check if the thread has been interrupted periodically using Thread.isInterrupted()
        // Note: Sending an interrupt is a nicer approach to stop a long-waiting thread.
        interruptTaskViaThreadDotIsInterruptedMethod();
    }

    private static void interruptThreadUsingAVolatileFlag() throws InterruptedException {
        RunnableTaskVer1 task1 = new RunnableTaskVer1();
        RunnableTaskVer1 task2 = new RunnableTaskVer1();

        task1.start();
        task2.start();

        Thread.sleep(10000);

        task1.stop();
        task2.stop();
    }

    private static void interruptTaskViaThreadDotIsInterruptedMethod() throws InterruptedException {
        RunnableTaskVer2 task1 = new RunnableTaskVer2();
        RunnableTaskVer2 task2 = new RunnableTaskVer2();

        task1.start();
        task2.start();

        Thread.sleep(1100);

        task1.interrupt();
        task2.interrupt();
    }

}

class RunnableTaskVer1 implements Runnable {

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
    public void run(){

        while (!flag) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " Running...");
        }

        System.out.println(Thread.currentThread().getName() + " Stopped");
        return;

    }
}


class RunnableTaskVer2 implements Runnable {
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