package ds.patterns.subarray;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    /**
         560. Subarray Sum Equals K
         Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

         Input: nums = [1,1,1], k = 2
         Output: 2

         Input: nums = [1,2,3], k = 3
         Output: 2


     For an array
        nums = [1,2,3,4,5]

     All possible subarrays would be
         i   [i, j]   Array Values
         ----------------------------
         0   [0, 0]   [1]
             [0, 1]   [1, 2]
             [0, 2]   [1, 2, 3]
             [0, 3]   [1, 2, 3, 4]
             [0, 4]   [1, 2, 3, 4, 5]

         1   [1, 1]  [2]
             [1, 2]  [2, 3]
             [1, 3]  [2, 3, 4]
             [1, 4]  [2, 3, 4, 5]

         2   [2, 2]  [3]
             [2, 3]  [3, 4]
             [2, 4]  [3, 4, 5]

         3   [3, 3]  [4]
             [3, 4]  [4, 5]

         4   [4, 4]  [ 5]

     */
     public int subarraySum(int[] nums, int k) {
         int count = 0;
         for (int i=0; i<nums.length; i++){
             int sum = 0;
             for (int j=i; j<nums.length; j++){  // here j == i - if a single element is equal to the sum
                 sum = sum + nums[j];
                 if (sum == k) {
                     count++;
                 }
             }
         }
         return count;
     }

     /**
            An approach keeping track of the cumulative sum
                                 |sum-k |  k   |
                                 |  7   |  7   |
                                 |  sum = 14   |
            nums        =        [3,    4,     7,    2,     -3,    1,     4,     2]      k = 7
            index       =         0     1      2     3       4     5      6      7
            cumSumMap   = {{0:1} {3:1} {7:1} {14:1} {16:1} {13:1} {14:2} {18:1} {20:1}
            sum         =         3     7     14     16     13     14     18     20
            sum-k       =        -4     0     7[1-2] 9      6      7[2-5] 11     13
            count       =         0     1     2      2      2      3      3      4


      Solution:
          The idea behind this approach is as follows: If the cumulative sum(represented by sum[i] for sum up to ith index)
          up to two indices is the same, the sum of the elements lying in between those indices is zero. Extending the same thought
          further, if the cumulative sum up to two indices, say iii and jjj is at a difference of kkk
          i.e. if sum[i]−sum[j] = k, the sum of elements lying between indices i and j is k.

          Based on these thoughts, we make use of a hashmap map which is used to store the cumulative sum up to all the indices
          possible along with the number of times the same sum occurs. We store the data in the form: (sum_i, no_of_occurrences_of_sum_i).
          We traverse over the array nums and keep on finding the cumulative sum. Every time we encounter a new sum, we make a new entry
          in the hashmap corresponding to that sum. If the same sum occurs again, we increment the count corresponding to that sum in the
          hashmap. Further, for every sum encountered, we also determine the number of times the sum sum−k has occurred already, since it
          will determine the number of times a subarray with sum kkk has occurred up to the current index. We increment the count by the
          same amount.
          After the complete array has been traversed, the count gives the required result.

      */
     public int subarraySumWithCumulativeSum(int[] nums, int k) {
         int count = 0;  int sum = 0;
         Map<Integer, Integer> cummulativeSumMap = new HashMap<>(); cummulativeSumMap.put(0, 1); // {sum, count}
         for (int i=0; i<nums.length; i++){
             sum = sum + nums[i];
             if (cummulativeSumMap.containsKey(sum-k))
                 count = count + cummulativeSumMap.get(sum-k);
             if (cummulativeSumMap.containsKey(sum))
                 cummulativeSumMap.put(sum, cummulativeSumMap.get(sum)+ 1);
             else
                 cummulativeSumMap.put(sum, 1);
         }
         return count;
     }

    public static void main(String[] args) {
        //int[] nums = {1,1,1}; int k = 2;  //
        int[] nums = {3, 4, 7, 2, -3, 1, 1, -1}; int k = 7;  // 5
        //int[] nums = {3, 4, 7, 2, -3, 1, 4, 2}; int k = 7; // 4
        SubarraySumEqualsK obj = new SubarraySumEqualsK();
        System.out.println(obj.subarraySum(nums, k));
        System.out.println(obj.subarraySumWithCumulativeSum(nums, k));
    }
}
