package dev.vjammi.ds.v2.usage.compare.comparators;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ComparatorUsageInPriorityQueue {

    class MinIntegerComparator implements Comparator<Integer>{
        public int compare(Integer x, Integer y){
            return x - y;
        }
    }

    class MaxIntegerComparator implements Comparator<Integer>{
        public int compare(Integer x, Integer y){
            return y - x;
        }
    }

    public void testPriorityQueueComparator(int[] nums) {
        Queue<Integer> minHeap0 = new PriorityQueue<>(nums.length);                                          // Default - MinIntegerComparator
        Queue<Integer> minHeap1 = new PriorityQueue<>(nums.length+1, new MinIntegerComparator()); // Default - MinIntegerComparator
        Queue<Integer> maxHeap2 = new PriorityQueue<>(nums.length+1, new MaxIntegerComparator()); // MaxIntegerComparator

        for(int i = 1; i< nums.length; i++){
            minHeap0.add(nums[i]);
            minHeap1.add(nums[i]);
            maxHeap2.add(nums[i]);
        }

        Iterator<Integer> heapIterator0 = minHeap0.iterator();
        while (heapIterator0.hasNext()) {
            System.out.print(heapIterator0.next() + " ");
        }
        System.out.println();

        Iterator<Integer> heapIterator1 = minHeap1.iterator();
        while (heapIterator1.hasNext()) {
            System.out.print(heapIterator1.next() + " ");
        }
        System.out.println();

        Iterator<Integer> heapIterator2 = maxHeap2.iterator();
        while (heapIterator2.hasNext()) {
            System.out.print(heapIterator2.next() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{15,14,13,20,15,10,9,8,7,4,3};
        ComparatorUsageInPriorityQueue pq = new ComparatorUsageInPriorityQueue();
        pq.testPriorityQueueComparator(nums);
    }
}
