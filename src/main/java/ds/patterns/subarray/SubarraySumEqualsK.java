package ds.patterns.subarray;

public class SubarraySumEqualsK {

    /**
         560. Subarray Sum Equals K
         Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

         Input: nums = [1,1,1], k = 2
         Output: 2

         Input: nums = [1,2,3], k = 3
         Output: 2
     */

     /**
         [1 2 3 4 5]
         [1, 1] = 1 - 0
         [1, 2] = 3 - 0
         [1, 3] = 6 - 0
         [1, 4] = 10 - 0
         [1, 5] = 15 - 0

         [2, 2] = 2 - 0
         [2, 3] = 5 - 0
         [2, 4] = 9 - 0
         [2, 5] = 14 - 0

         [3, 3] = 3 - 0
         [3, 4] = 7 - 0
         [3, 5] = 12 - 0

         [4, 4] = 4 - 0
         [4, 5] = 9 - 0

         [5, 5] = 5 - 0
     */
     public int subarraySum1(int[] nums, int k) {
         int count = 0;
         for (int i=0; i<nums.length; i++){
             int sum = 0;
             for (int j=i; j<nums.length; j++){  // here j == i - if a single element is equal to the sum
                 sum = sum + nums[j];
                 if (sum == k) {
                     count++;
                 }
                 System.out.println("[" +nums[i] +", " +nums[j] +"]   " +count  +" total number of continuous sub arrays whose sum equals to k");
             }
             System.out.println("");
         }
         return count;
     }
}
