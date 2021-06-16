package ds.binaryheap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueJavaUtil {

    class MaxIntegerComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer x, Integer y){
            return y - x;
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

    public PriorityQueueJavaUtil(int[] nums) {
        //this.minHeap = new PriorityQueue<>(size); // Default - MinIntegerComparator
        this.minHeap = new PriorityQueue<>(nums.length+1, new MinIntegerComparator()); // Default - MinIntegerComparator
        this.maxHeap = new PriorityQueue<>(nums.length+1, new MaxIntegerComparator()); // MaxIntegerComparator

        for(int i = 1; i< nums.length; i++){
            //int data = new Random().nextInt(100) +1; //number between 0 to 100
            minHeap.add(nums[i]);
            maxHeap.add(nums[i]);
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
        int[] nums = new int[]{15,14,13,20,15,10,9,8,7,4,3};
        PriorityQueueJavaUtil pq = new PriorityQueueJavaUtil(nums);

        System.out.println("Min Heap");
        pq.iterateMinHeap();

        System.out.println("");

        System.out.println("Max Heap");
        pq.iterateMaxHeap();
    }
}
