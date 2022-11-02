package ds.patterns.usage.concurrency;

import java.util.ArrayList;
import java.util.List;

public class WaitNotifyAndCondVar {

    public static void main(String[] args) {

        List<Integer> sharedQueue = new ArrayList<>();

        // Option1
        ProducerTask producerTask1 = new ProducerTask(sharedQueue, 5);
        ConsumerTask consumerTask1 = new ConsumerTask(sharedQueue);
        producerTask1.start();
        consumerTask1.start();

        // Option2 - Alternate Representation
        //        ProducerTask producerTask2 = new ProducerTask(sharedQueue, 5);
        //        ConsumerTask consumerTask2 = new ConsumerTask(sharedQueue);
        //        Thread producer = new Thread(producerTask2, "Producer");
        //        Thread consumer = new Thread(consumerTask2, "Consumer");
        //        producer.start();
        //        consumer.start();
    }

}

class ProducerTask implements Runnable {

    private final List<Integer> taskQueue;
    private final int CAPACITY;
    private int counter = 0;
    private Thread producer;

    public ProducerTask(List<Integer> sharedQueue, int size) {
        this.taskQueue = sharedQueue;
        this.CAPACITY = size;
    }

    public void start() {
        producer = new Thread(this, "Producer");
        producer.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                produce(counter++);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void produce(int i) throws InterruptedException {

        synchronized (taskQueue) {

            while (taskQueue.size() == CAPACITY) {
                System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
                taskQueue.wait();
            }

            Thread.sleep(1000);

            taskQueue.add(i);
            System.out.println("Produced: " + i);

            //taskQueue.notify();
            taskQueue.notifyAll();
        }

    }

}

class ConsumerTask implements Runnable {

    private final List<Integer> taskQueue;
    private Thread consumer;

    public ConsumerTask(List<Integer> sharedQueue) {
        this.taskQueue = sharedQueue;
    }

    public void start() {
        consumer = new Thread(this,"Consumer");
        consumer.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void consume() throws InterruptedException {

        synchronized (taskQueue) {

            while (taskQueue.isEmpty()) {
                System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
                taskQueue.wait();
            }

            Thread.sleep(1000);

            int i = taskQueue.remove(0);
            System.out.println("Consumed: " + i);

            //taskQueue.notify();
            taskQueue.notifyAll();
        }

    }


}


