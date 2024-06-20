package dev.vjammi.ds.v1.arrays;
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
public class MaximumSubarray {

    // Runtime O(n^2)
    // Space   O(1)
    public int maxSubArray(int[] nums) {
        int maxSumSoFar = 0;
        for (int i=0; i<nums.length; i++){
            int sum = 0;
            for (int j=i; j<nums.length; j++){  // here j == i - if a single element is equal to the sum
                sum = sum + nums[j];
                if (sum > maxSumSoFar){
                    maxSumSoFar = sum;
                }
                System.out.println("[" +nums[i] +", " +nums[j] +"]    MaxSum =" +maxSumSoFar);
            }
            System.out.println();
        }
        return maxSumSoFar;
    }

    /**
         Kadane's Algorithm
         Runtime O(n)
         Space   O(1)
         a [-2,1,-3,4,-1,2,1,-5,4]
         subArray = a[k] || a[k-1] + a[k]
    */
    public int maxSubArray2(int[] nums) {

        return 0;
    }

}
