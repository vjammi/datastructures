package dev.vjammi.ds.v1.queue;


class MyCircularQueue {

    private final int[] queue;
    private int head;
    private int tail;
    private final int capacity;
    private int size;

    // MyCircularQueue(k) Initializes the object with the size of the queue to be k.
    public MyCircularQueue(int k){
        capacity = k;
        queue = new int[k];
        size =  0;
        head = -1;
        tail = -1;
        System.out.println("MyCircularQueue Head " +head +" Tail " +tail
                +" Size " +size +" Capacity " +capacity);
    }
    // enQueue(b) Head 1 Tail 1 Size 1 val 3
    // boolean enQueue(int value) Inserts an element into the circular queue.
    // Return true if the operation is successful.
    public boolean enQueue(int value) {
        System.out.println("enQueue(b) Head " +head +" Tail " +tail  +" Size " +size +" val " +value);
        // right now, if queue is full, api will not add the newer element. It should have rather overwritten or threw an error
//        if (size == capacity)
//            return false;

        if (size == 0){ // head == -1
            int newTail = 0;
            queue[newTail] = value;
            head = newTail;
            tail = newTail;
            size = size + 1;
        }else if (size > 0){
            int newTail = (tail + 1) % capacity;
            queue[newTail] = value;
            tail = newTail;
            size = size + 1;
        }
        System.out.println(" enQueue(a) Head " +head +" Tail " +tail  +" Size " +size +" val " +value);
        return true;
    }

    // boolean deQueue() Deletes an element from the circular queue.
    // Return true if the operation is successful.
    public boolean deQueue() {
        System.out.println("deQueue (b) Head " +head +" Tail " +tail +" Size " +size);
        if (size == 0)
            return false;

        if (size==1){
            queue[head] = 0;
            head = -1;
            tail = -1;
            size =  0;
        }else if (size>1){
            queue[head] = 0;
            head = (head + 1)%capacity;
            size = size-1;
        }
        System.out.println(" deQueue (a) Head " +head +" Tail " +tail +" Size " +size);
        return true;
    }

    // int Front() Gets the front item from the queue.
    // If the queue is empty, return -1.
    public int Front() {
        System.out.println("Front(b) Head " +head+" Tail " +tail  +" Size " +size);
        if (head == -1) return -1;
        int front = queue[head];
        System.out.println(" Front(a) Head " +head+" Tail " +tail  +" Size " +size + " Front " + front);
        return front;
    }

    // int Rear() Gets the last item from the queue.
    // If the queue is empty, return -1.
    public int Rear() {
        System.out.println("Rear Head " +head +" Tail " +tail  +" Size " +size);
        if (size == 0 ) return -1;
        int rear = queue[tail];
        System.out.println(" Rear(a) Head " +head +" Tail " +tail  +" Size " +size +" Rear " +rear);
        return rear;
    }

    // boolean isEmpty() Checks whether the circular queue is empty or not.
    public boolean isEmpty() {
        System.out.println("isEmpty Size " +size +" Capacity " +capacity);
        return size == 0;
    }

    // boolean isFull() Checks whether the circular queue is full or not.
    public boolean isFull() {
        System.out.println("isFull Size " +size + " Capacity " +capacity);
        return size == capacity;
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
    public static void main (String[] args){
        // Your MyCircularQueue object will be instantiated and called as such
        MyCircularQueue obj = new MyCircularQueue(10);
        boolean param_1 = obj.enQueue(1);
        boolean param_2 = obj.enQueue(2);
        boolean param_3 = obj.enQueue(3);
        boolean param_4 = obj.enQueue(4);
        boolean param_5 = obj.enQueue(5);
        boolean param_6 = obj.enQueue(6);
        boolean param_7 = obj.enQueue(7);
        boolean param_8 = obj.enQueue(8);
        boolean param_9 = obj.enQueue(9);
        boolean param_10 = obj.enQueue(10);
        boolean param_11 = obj.enQueue(11);
        boolean param_12 = obj.enQueue(12);
        boolean param_13 = obj.enQueue(13);

        boolean param_22 = obj.deQueue();
        int param_33 = obj.Front();
        int param_44 = obj.Rear();
        boolean param_55 = obj.isEmpty();
        boolean param_66 = obj.isFull();
    }

}

