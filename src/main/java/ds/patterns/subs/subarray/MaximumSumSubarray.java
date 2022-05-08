package ds.patterns.subs.subarray;
/**
     53. Maximum Subarray
     Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
     A subarray is a contiguous part of an array.

     Example 1:
     Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
     Output: 6
     Explanation: [4,-1,2,1] has the largest sum = 6.

     Example 2:
     Input: nums = [1]
     Output: 1

     Example 3:
     Input: nums = [5,4,-1,7,8]
     Output: 23
 **/

// A SubArray needs to be contiguous
public class MaximumSumSubarray {

    // Runtime O(n^2) - 2 for loops
    // Space   O(1)
    public int maxSubArrayNaive(int[] nums) {
        int maxSumSoFar = 0;
        for (int i=0; i<nums.length; i++){
            int sum = 0;
            for (int j=0; j<nums.length; j++){  // here j == i - if a single element is equal to the sum
                sum = sum + nums[j];
                if (sum > maxSumSoFar){
                    maxSumSoFar = sum;
                }
                System.out.println("[" +nums[i] +", " +nums[j] +"]    MaxSum =" +maxSumSoFar);
            }
            System.out.println("");
        }
        return maxSumSoFar;
    }

    /**
         Insight from Kadane's Algorithm could help [https://youtu.be/86CQq3pKSUw?t=259]
             Runtime O(n)
             Space   O(1)

         Approach
                                                |---------|
              nums                =  [-2, 1, -3, 4, -1, 2, 1, -5, 4]
              subArray at k       =   -2  1  -2  4   3  5  6  1   5
              maxSubArray at k-1  =   -2  1   1  4   4  5  6  6   6
                                    [-2, 1, -3]+[4]
                                                [4]

                                     if max subarray upto a[k-1] + subarray at a[k] > subarray at a[k]
                                        Add the a[k] to the existing subarray
                                     else
                                        Discard the previous max subarray, and start a new one starting a[k]

                                     -2 +  1 >  1  [F]
                                     1 + -3 > -3  [T]
                                     -2 +  4 >  4  [F]
                                     4 + -1 > -1  [T]
                                     3 +  2 >  2  [T]
                                     5 +  1 >  1  [T]
                                     6 + -5 > -5  [T]
                                     1 +  4 >  4  [T]
        Why does this work?



    */
    public int maxSubArray(int[] nums) {

        int maxSubArraySumSoFar = nums[0]; // should it be initialized to 0 ???
        int maxSubArrayAtKMinus1 = nums[0];

        for (int k=1; k < nums.length; k++){
            int subArrayAtK = nums[k];

            //    if max subarray upto a[k-1] + subarray at a[k] > subarray at a[k] -  [positive subArray + positive subArrayAtK or positive subArray + negative subArrayAtK
            //    Add the a[k] to the existing subarray
            //                             else
            //    Discard the previous max subarray, and start a new one starting a[k] [negative subarray + positive current subArrayAtK]
            //  indexes                 0   1   2   3   4  5  6   7   8
            //  nums                =  [-2, 1, -3,  4, -1, 2, 1, -5, 4]
            //  maxsum until k-1 + k   [         ]+[4]
            //      here k=3                       [4]
            if ( maxSubArrayAtKMinus1 + subArrayAtK > subArrayAtK ){
                maxSubArrayAtKMinus1 = maxSubArrayAtKMinus1 + subArrayAtK;
            }else{
                maxSubArrayAtKMinus1 = subArrayAtK; // Not resetting or keeping track of the start index
            }
            maxSubArraySumSoFar = Math.max(maxSubArraySumSoFar, maxSubArrayAtKMinus1);
        }
        return maxSubArraySumSoFar;
    }


    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        new MaximumSumSubarray().maxSubArray(nums);
    }

}
