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
            for (int j=i; j<nums.length; j++){  // here j == i - if a single element is equal to the sum
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
         Kadane's Algorithm: https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm
             Runtime O(n)
             Space   O(1)

         Approach
                                                |---------|
              nums                = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
              currSubArraySum     =  -2  1  -2  4   3  5  6  1   5
              maxSubArraySumSoFar =  -2  1   1  4   4  5  6  6   6

                         -2 +  1 >  1  [F]
                         1 + -3 > -3  [T]
                         -2 +  4 >  4  [F]
                         4 + -1 > -1  [T]
                         3 +  2 >  2  [T]
                         5 +  1 >  1  [T]
                         6 + -5 > -5  [T]
                         1 +  4 >  4  [T]

              if   subarray upto a[k-1] + a[k] > a[k]
                    // Add the a[k] to the existing subarray
              else
                    // Discard the subarray, and start a new one starting a[k]
    */
    public int maxSubArray(int[] nums) {

        int maxSubArraySumSoFar = nums[0]; // should it be initialized to 0 ???
        int currSubArraySum = nums[0];

        for (int i=1; i < nums.length; i++){

            int num = nums[i];
            // Check to add num at current index to existing subarray [positive subArray + positive num or positive subArray + negative num
            //      or
            // To discard the existing subarray and start new subarray [negative subarray + positive current num]
            if ( currSubArraySum + num > num ){
                currSubArraySum = currSubArraySum + num;
            }else{
                currSubArraySum = num; // Not resetting or keeping track of the start index
            }
            maxSubArraySumSoFar = Math.max(maxSubArraySumSoFar, currSubArraySum);
        }
        return maxSubArraySumSoFar;
    }


    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        new MaximumSumSubarray().maxSubArray(nums);
    }

}
