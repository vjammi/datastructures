package ds.patterns.subarray;

/**
 * 53. Maximum Subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * A subarray is a contiguous part of an array.
 * <p>
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * <p>
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * <p>
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 **/

// A SubArray needs to be contiguous
public class MaximumSumSubarray {

    // Runtime O(n^2) - 2 for loops
    // Space   O(1)
    public int maxSubArrayNaive1(int[] nums) {
        if (nums.length == 0) return 0;
        int maxSubarraySum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            // i - j(i-len-1) are various subarrays
            int subarraySum = 0;
            for (int j = i; j < nums.length; j++) {
                subarraySum = subarraySum + nums[j];
                maxSubarraySum = Math.max(maxSubarraySum, subarraySum);
            }
        }
        return maxSubarraySum;
    }

    /**
     * Insight from Kadane's Algorithm could help [https://youtu.be/86CQq3pKSUw?t=259]
     * Runtime O(n)
     * Space   O(1)
     * <p>
     * Approach
     * |---------|
     * nums                =  [-2, 1, -3, 4, -1, 2, 1, -5, 4]
     * subArray at k       =   -2  1  -2  4   3  5  6  1   5
     * maxSubArray at k-1  =   -2  1   1  4   4  5  6  6   6
     * [-2, 1, -3]+[4]
     * [4]
     * <p>
     * if max subarray upto a[k-1] + subarray at a[k] > subarray at a[k]
     * Add the a[k] to the existing subarray
     * else
     * Discard the previous max subarray, and start a new one starting a[k]
     * <p>
     * -2 +  1 >  1  [F]
     * 1 + -3 > -3  [T]
     * -2 +  4 >  4  [F]
     * 4 + -1 > -1  [T]
     * 3 +  2 >  2  [T]
     * 5 +  1 >  1  [T]
     * 6 + -5 > -5  [T]
     * 1 +  4 >  4  [T]
     * Why does this work?
     */

    /*
               nums                      = [5, 4, -1, 7, 8]
               indexes                      0  1   2  3  4
        subArrayAtK                      =  5  4  -1  7  4
    subArrayMaxAtKMinusOne + subArrayAtK =     9   8  15 19
    subArrayMaxsoFar                     =  5  9   8  15 19


               nums                      = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
               indexes                       0  1   2  3   4  5  6   7  8
    subArrayMaxsoFar                     =  -2 -1   4
        subArrayAtK                      =      1  -3  4
    subArrayMaxsoFar + subArrayAtK       =     -1   4


    */
    public int maxSubArrayKadane(int[] nums) {
        if (nums.length == 0)
            return 0;

        // We initialize currSubArrayAtKMinusOne & maxSubArraySumSoFar with the value of the first index[0'th]
        // We will then iterate from 1 - len-1 to find the max for the entire subarray
        int globalMaxSubarraySum = nums[0];
        int currSubArrayAtKMinusOne = nums[0];
        for (int k = 1; k < nums.length; k++) {
            //    if max subarray upto a[k-1] + subarray at a[k] > subarray at a[k] -  [positive subArray + positive subArrayAtK or positive subArray + negative subArrayAtK
            //          Add the a[k] to the existing subarray
            //    else
            //          Discard the previous max subarray, and start a new one starting a[k] [negative subarray + positive current subArrayAtK]
            // We then update the globalMaxSum with the currentMaxSum collected in currSubArrayAtKMinusOne

            //  indexes                 0   1   2   3   4  5  6   7   8
            //  nums                =  [-2, 1, -3,  4, -1, 2, 1, -5, 4]
            //  maxsum until k-1 + k   [         ]+[4]
            //                                     [4]

            int subArrayAtK = nums[k];
            int subarraySumWithKthVal = 0;
            if (currSubArrayAtKMinusOne + subArrayAtK > subArrayAtK) {
                // Val at the current index can added to the sum
                // *** Note that it is not yet known if currSubArrayAtKMinusOne + subArrayAtK > globalMaxSubarraySum. We do that check later
                subarraySumWithKthVal = currSubArrayAtKMinusOne + subArrayAtK;
            } else {
                subarraySumWithKthVal = subArrayAtK;
            }
            globalMaxSubarraySum = Math.max(globalMaxSubarraySum, subarraySumWithKthVal);
            currSubArrayAtKMinusOne = subarraySumWithKthVal;  // Add subArrayAtK to the current running subarray
        }
        return globalMaxSubarraySum;
    }

    // Returns sum of maximum sum subarray in arr[low..high]
    // https://www.geeksforgeeks.org/maximum-subarray-sum-using-divide-and-conquer-algorithm/
    // https://youtu.be/Eo2wQIPSwrw?t=287

    int maxSubArraySum(int arr[], int low, int high) {
        // Base Case: Only one element
        if (low == high)
            return arr[low];

        // Find middle point
        int middle = (low + high) / 2;

        //    a) Maximum subarray sum in left half
        //    b) Maximum subarray sum in right half
        //    c) Maximum subarray sum such that the subarray crosses the midpoint
        return Math.max(Math.max(maxSubArraySum(arr, low, middle), maxSubArraySum(arr, middle+1, high)), maxPivotCrossingSum(arr, low, middle, high));
    }

    // Find the maximum possible sum in arr[] such that arr[middle] is part of it
    int maxPivotCrossingSum(int arr[], int low, int middle, int high) {
        // Include elements on left of mid.
        int runningLeftSum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = middle; i >= low; i--) {
            runningLeftSum = runningLeftSum + arr[i];
            if (runningLeftSum > leftSum)
                leftSum = runningLeftSum;
        }

        // Include elements on right of mid
        int runningRightSum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = middle + 1; i <= high; i++) {
            runningRightSum = runningRightSum + arr[i];
            if (runningRightSum > rightSum)
                rightSum = runningRightSum;
        }

        // Return runningSum of elements on left and right of mid
        // returning only leftSum + rightSum will fail for [-2, 1]
        return Math.max(leftSum + rightSum, Math.max(leftSum, rightSum));
    }


    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        //new MaximumSumSubarray().maxSubArray(nums);
        new MaximumSumSubarray().maxSubArraySum(nums, 0, nums.length-1);

    }
}
