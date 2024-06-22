package io.dev.v2.queue;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 622. Design Circular Queue
 * */
public class CircularQueue {

    private class ListNode {

        public ListNode nextListNode;

        public ListNode(int value) {

        }
    }

    private ListNode head, tail;
    private int count;
    private final int capacity;
    // Additional variable to secure the access of our queue
    private final ReentrantLock queueLock = new ReentrantLock();

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public CircularQueue(int k) {
        this.capacity = k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        // ensure the exclusive access for the following block.
        queueLock.lock();
        try {
            if (this.count == this.capacity)
                return false;

            ListNode newListNode = new ListNode(value);
            if (this.count == 0) {
                head = tail = newListNode;
            } else {
                tail.nextListNode = newListNode;
                tail = newListNode;
            }
            this.count += 1;

        } finally {
            queueLock.unlock();
        }
        return true;
    }


    /**
     * Your MyCircularQueue object will be instantiated and called as such:
     * MyCircularQueue obj = new MyCircularQueue(k);
     * boolean param_1 = obj.enQueue(value);
     * boolean param_2 = obj.deQueue();
     * int param_3 = obj.Front();
     * int param_4 = obj.Rear();
     * boolean param_5 = obj.isEmpty();
     * boolean param_6 = obj.isFull();
     */
}
