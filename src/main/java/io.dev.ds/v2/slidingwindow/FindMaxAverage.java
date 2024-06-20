package dev.vjammi.ds.v2.slidingwindow;

/**
 * 643. Maximum Average Subarray I
 *
 * You are given an integer array nums consisting of n elements, and an integer k.
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
 * Any answer with a calculation error less than 10-5 will be accepted.
 *
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 **/
public class FindMaxAverage {

    public double findMaxAverage(int[] nums, int k){
        int n = nums.length, sum = 0, maxSum = Integer.MIN_VALUE;

        for (int i=0; i<k; i++){
            sum = sum + nums[i];
        }

        int j = 0;
        for (int i=k; i<=n; i++){
            maxSum = Math.max(sum, maxSum);

            sum = sum - nums[j];
            j--;
        }

        return (maxSum * 1.0) / k;
    }
}
