package ds.patterns.subarray;

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
             System.out.println("");
         }
         return count;
     }


    public static void main(String[] args) {
        //int[] nums = {1,1,1};
        int[] nums = {1,2,3,4,5};
        int k = 2;

        SubarraySumEqualsK obj = new SubarraySumEqualsK();
        System.out.println(obj.subarraySum(nums, k));
    }
}
