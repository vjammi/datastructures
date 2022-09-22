package ds.patterns.usage.concurrency;

public class Task{

    public static void main(String[] args) {
        Thread subTask1 = new SubTask1();
        subTask1.run();


        Runnable subTask2 = new SubTask2();
        subTask2.run();

        Runnable subTask3 = SubTask3.getRunnable();
        subTask3.run();
    }


}

class SubTask1 extends Thread {
    public void run() {
        for (int i=0; i<10; i++) {
            System.out.println("Executing Task1 [extends Thread ]..." +Thread.currentThread().getName());
        }
    }
}

class SubTask2 implements Runnable {
    public void run() {
        for (int i=0; i<10; i++) {
            System.out.println("Executing Task2 [implements Runnable]..." +Thread.currentThread().getName());
        }
    }
}


class SubTask3 {
    public static Runnable getRunnable() {
        return () -> {
            System.out.println("SubTask3 [Lambda] ..." +Thread.currentThread().getName());
        };
    }
}
