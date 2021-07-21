package ds.patterns.heap;

import java.util.*;

/**
347. Top K Frequent Elements
    Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]
    Input: nums = [1], k = 1
    Output: [1]

Next challenges:
    Word Frequency
    Kth Largest Element in an Array
    Sort Characters By Frequency
    Split Array into Consecutive Subsequences
    Top K Frequent Words
    K Closest Points to Origin
    Sort Features by Popularity
*/

public class TopKFrequentElements {

    Map<Integer, Integer> charToFreqMap;
    public int[] topKFrequent(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }

        // 1. build hash map : character and its frequency [how often it appears] - O(N) time
        charToFreqMap = new HashMap();
        for (int num: nums) {
            if (charToFreqMap.containsKey(num)) charToFreqMap.put(num, charToFreqMap.get(num)+1);
            else charToFreqMap.put(num, 1);
        }

        // init heap 'the less frequent element first'
        Queue<Integer> heap = new PriorityQueue<>(new FrequencyComparator());

        // 2. keep k top frequent elements in the heap - O(N log k) < O(N log N) time
        for (int key: charToFreqMap.keySet()) {
            heap.add(key);
            if (heap.size() > k) heap.poll();
        }

        // 3. build an output array - O(k log k) time. k = number of items to store in the pq
        int[] topKFrequentElements = new int[k];
        for (int i=k-1; i>=0; i--){
            topKFrequentElements[i] = heap.poll();
        }
        return topKFrequentElements;
    }

    class FrequencyComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer n1, Integer n2) {
            //return charToFreqMap1.get(n1) - charToFreqMap1.get(n2); //    descending
            //int diff = charToFreqMap.get(n1).compareTo(charToFreqMap.get(n2));
            //System.out.print(diff +" "); // 1 1 0 -1 -1

            //int diff11 = charToFreqMap.get(n1) - charToFreqMap.get(n2); //    descending
            //System.out.print(diff11 +" "); // 1 4 0 -2 -1
            //
            //return charToFreqMap.get(n2) - charToFreqMap.get(n1); //    ascending
            //int diff2 = charToFreqMap.get(n2).compareTo(charToFreqMap.get(n1));
            //return  diff2;
            //System.out.print(diff2 +" "); // -1 -1 -1 1 1
            //int diff22 = charToFreqMap.get(n2) - charToFreqMap.get(n1);
            //System.out.print(diff22 +" "); // -1 -3 -1 3 2

            //return charToFreqMap.get(n1) - charToFreqMap.get(n2);             //    descending
            int diff = charToFreqMap.get(n1).compareTo(charToFreqMap.get(n2));  // descending
            System.out.print(diff +" "); // 1 1 0 -1 -1
            return diff;
        }
    }

    public static void main(String[] args) {
        TopKFrequentElements obj = new TopKFrequentElements();
        int[] nums = {11,11,11,22,22,22,33,33,44,55,55,55,55,55,55};
        int k = 2;
        obj.topKFrequent(nums, k);
    }

}
