package dev.vjammi.ds.v2.dev.concurrency;

import java.util.UUID;
import java.util.concurrent.*;

/**
 *  BlockingQueue implementation
 *      - implements a producer-consumer usecase
 *      - is thread-safe
 *      - does not accept null
 *      - queuing methods are atomic
 *
 *   put(E e) throws InterruptedException: Inserts the specified element into this queue, waiting up to the
 *             specified wait time if necessary for space to become available.
 *             If the queue is full, it waits for the space to be available.
 *
 *   E take() throws InterruptedException:   Retrieves and removes the head of this queue, waiting if necessary
 *             until an element becomes available.
 *
 * */
public class BlockingQueueUsage {

    public static void main(String args[]) throws InterruptedException {
        // Variants of blocking queue
        BlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(5);
        BlockingQueue priorityBlockingQueue = new PriorityBlockingQueue(5);
        BlockingQueue synchronousQueue  = new SynchronousQueue();
        BlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(5);

        Producer producer = new Producer(arrayBlockingQueue);
        Consumer consumer1 = new Consumer(arrayBlockingQueue);
        Consumer consumer2 = new Consumer(arrayBlockingQueue);

        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread consumerThread1 = new Thread(consumer1);
        consumerThread1.start();
        Thread consumerThread2 = new Thread(consumer2);
        consumerThread2.start();

        Thread.sleep(100);

        producerThread.interrupt();
        consumerThread1.interrupt();
        consumerThread2.interrupt();
    }
}

record Producer(BlockingQueue queue) implements Runnable {

    public void run() {
        try {
            while (true) {
                queue.put(produce());
            }
        } catch (InterruptedException ex) {
        }
    }

    Object produce() {
        String uuid = String.valueOf(UUID.randomUUID());
        System.out.println("Producing..." + uuid);
        return uuid;
    }
}

record Consumer(BlockingQueue queue) implements Runnable {

    public void run() {
        try {
            while (true) {
                consume(queue.take());
            }
        } catch (InterruptedException ex) {
        }
    }

    void consume(Object x) {
        System.out.println("    Consuming " + x.toString());
    }
}

//public class MyBlockingQueue implements BlockingQueue {
//
//    @Override
//    public boolean add(Object o) {
//        return false;
//    }
//
//    @Override
//    public boolean offer(Object o) {
//        return false;
//    }
//
//    @Override
//    public Object remove() {
//        return null;
//    }
//
//    @Override
//    public Object poll() {
//        return null;
//    }
//
//    @Override
//    public Object element() {
//        return null;
//    }
//
//    @Override
//    public Object peek() {
//        return null;
//    }
//
//    @Override
//    public void put(Object o) throws InterruptedException {
//
//    }
//
//    @Override
//    public boolean offer(Object o, long timeout, TimeUnit unit) throws InterruptedException {
//        return false;
//    }
//
//    @Override
//    public Object take() throws InterruptedException {
//        return null;
//    }
//
//    @Override
//    public Object poll(long timeout, TimeUnit unit) throws InterruptedException {
//        return null;
//    }
//
//    @Override
//    public int remainingCapacity() {
//        return 0;
//    }
//
//    @Override
//    public boolean remove(Object o) {
//        return false;
//    }
//
//    @Override
//    public boolean addAll(Collection c) {
//        return false;
//    }
//
//    @Override
//    public void clear() {
//
//    }
//
//    @Override
//    public boolean retainAll(Collection c) {
//        return false;
//    }
//
//    @Override
//    public boolean removeAll(Collection c) {
//        return false;
//    }
//
//    @Override
//    public boolean containsAll(Collection c) {
//        return false;
//    }
//
//    @Override
//    public int size() {
//        return 0;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return false;
//    }
//
//    @Override
//    public boolean contains(Object o) {
//        return false;
//    }
//
//    @Override
//    public Iterator iterator() {
//        return null;
//    }
//
//    @Override
//    public Object[] toArray() {
//        return new Object[0];
//    }
//
//    @Override
//    public Object[] toArray(Object[] a) {
//        return new Object[0];
//    }
//
//    @Override
//    public int drainTo(Collection c) {
//        return 0;
//    }
//
//    @Override
//    public int drainTo(Collection c, int maxElements) {
//        return 0;
//    }
//}
