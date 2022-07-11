package ds.patterns.heap;

import java.util.*;

/**
    347. Top K Frequent Elements
        Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
        Input: nums = {11,11,11,22,22,22,33,33,44,55,55,55,55,55,55}; int k = 2;
        Solution
             charToFreqMap =  size = 5
                 33 -> 2
                 22 -> 3
                 55 -> 6    <
                 11 -> 3    <
                 44 -> 1
             heap =  size = 2
                 0 = 11
                 1 = 55
             topKFrequentElements
                 0 = 55
                 1 = 11

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

    class FrequencyComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer n1, Integer n2) {
            return charToFreqMap.get(n1).compareTo(charToFreqMap.get(n2));  //
        }
    }

    Map<Integer, Integer> charToFreqMap;
    public int[] topKFrequent(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }

        // 1. build hash map : character and its frequency [how often it appears] - O(N) time
        charToFreqMap = new HashMap();
        for (int num: nums) {
            if (charToFreqMap.containsKey(num))
                charToFreqMap.put(num, charToFreqMap.get(num)+1);
            else
                charToFreqMap.put(num, 1);
        }

        // Note ***
        //   For top k elements we will use a Min Heap of size k, iterating thru all the n elements,
        //   storing the less frequent elements first.
        //   When the heap size exceeds k, we will remove the minimum element,
        //   that will guarantee that we have the k largest elements left in the heap.

        // Within the heap store the less frequent elements first -  O(N log K) solution
        Queue<Integer> queue = new PriorityQueue<>(new FrequencyComparator());
        // 2. Keep K top frequent elements in the heap iun reverse order using min heap
        // O(K log N) < O(N log N) time [iterate over n elements keeping K elements in ascending order]
        for (int key: charToFreqMap.keySet()) {
            queue.offer(key);
            if (queue.size() > k)
                queue.poll();
        }

        // 3. Build an output array - O(K log K) time. k = number of items to store in the pq
        int[] topKFrequentElements = new int[k];
        for (int i=k-1; i>=0; i--){
            topKFrequentElements[i] = queue.poll();
        }

        return topKFrequentElements;
    }

    public static void main(String[] args) {
        TopKFrequentElements obj = new TopKFrequentElements();
        int[] nums = {11,11,11,22,22,22,33,33,44,55,55,55,55,55,55};
        int k = 2;
        obj.topKFrequent(nums, k);
    }

}
