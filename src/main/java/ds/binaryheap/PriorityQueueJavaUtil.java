package ds.binaryheap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueJavaUtil {

    class MaxIntegerComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer x, Integer y){
            return x - y;
        }
    }

    class MinIntegerComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer x, Integer y){
            return x - y;
        }
    }

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public PriorityQueueJavaUtil(int size) {
        //this.minHeap = new PriorityQueue<>(size); // Default - MinIntegerComparator
        this.minHeap = new PriorityQueue<>(size, new MinIntegerComparator()); // Default - MinIntegerComparator
        this.maxHeap = new PriorityQueue<>(size, new MaxIntegerComparator()); // MaxIntegerComparator

        for(int i = 1; i<= size; ++i){
            int data = new Random().nextInt(100) +1; //number between 0 to 100
            minHeap.add(data);
            maxHeap.add(data);
        }
    }

    private void iterateMinHeap() {
        Iterator<Integer> heapIterator = minHeap.iterator();
        while (heapIterator.hasNext()) {
            System.out.print(heapIterator.next() + " ");
        }
    }

    private void iterateMaxHeap() {
        Iterator<Integer> heapIterator = maxHeap.iterator();
        while (heapIterator.hasNext()) {
            System.out.print(heapIterator.next() + " ");
        }
    }

    public static void main(String[] args) {
        PriorityQueueJavaUtil pq = new PriorityQueueJavaUtil(10);

        System.out.print("Min Heap");
        pq.iterateMinHeap();

        System.out.print("Max Heap");
        pq.iterateMaxHeap();
    }
}
