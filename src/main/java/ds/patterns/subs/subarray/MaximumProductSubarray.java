package ds.patterns.subs.subarray;

/**
    152. Maximum Product Subarray
    Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
    The test cases are generated so that the answer will fit in a 32-bit integer.
    A subarray is a contiguous subsequence of the array.

    Input: nums = [2,3,-2,4]
    Output: 6
    Explanation: [2,3] has the largest product 6.

    Input: nums = [-2,0,-1]
    Output: 0
    Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
*/

public class MaximumProductSubarray {

    // Runtime O(n^2) - 2 for loops
    // Space   O(1)
    public int maxProductNaive(int[] nums) {
        int maxProductSoFar = nums[0];          // Initialize to nums[0], since j starts from index 1
        for (int i=0; i<nums.length; i++){
            int product = 1;                    // Initialize to 1, not 0 :)
            for (int j=i; j<nums.length; j++){  // here j == i - if a single element is equal to the sum
                product = product * nums[j];
                if (product > maxProductSoFar){
                    maxProductSoFar = product;
                }
            }
        }
        return maxProductSoFar;
    }

       /*
        Scenarios
           1. If all positives we can keep multiplying all the elements
                +ve x Max (+ve) = +ve
           2. If we come across a -ve, then we will reduce the product
                -ve x Max (+ve) = -ve
           3. However, if we come across another negative, it will increase the product based on previous -ve
                -ve x Min (-ve) = +ve
           4. If we come across a 0 it will take our product down to 0

        //          nums = [-2  1 -3  4   -1  2   1  -5    4]
        //      minSoFar =  -2 -2 -3 -12 -24 -48 -48 -120 -480
        //      maxSoFar =  -2  1  6  24  12  24  24  240  960
        //     globalMax =  -2  1  6  24  24* 24  24  240  960   * Note that 24 is being carried from previous globalMax
    */

    public int maxProduct(int[] nums) {

        int subarrayMaxSumAtKMinusOne  = nums[0];
        int subarrayMinSumAtKMinusOne  = nums[0];
        int globalMaxSubarraySum       = nums[0];
        System.out.println(subarrayMinSumAtKMinusOne +" " +subarrayMaxSumAtKMinusOne +" " +globalMaxSubarraySum);

        for(int i=1; i<nums.length; i++){
            int subArrayAtK = nums[i];

            int subarrayMaxSumWithKthVal = 0;
            //subarrayMaxSumAtKMinusOne = Math.max(nums[i], subarrayMaxSumWithKthVal);
            if (Math.max(subArrayAtK * subarrayMaxSumAtKMinusOne, subArrayAtK * subarrayMinSumAtKMinusOne) > subArrayAtK){
                subarrayMaxSumWithKthVal = Math.max(subArrayAtK * subarrayMaxSumAtKMinusOne, subArrayAtK * subarrayMinSumAtKMinusOne);
            }else{
                subarrayMaxSumWithKthVal = subArrayAtK;
            }

            int subArrayMinSumWithKthVal = 0;
            //subarrayMinSumAtKMinusOne = Math.min(nums[i], subArrayMinSumWithKthVal);
            if (Math.min(subArrayAtK * subarrayMaxSumAtKMinusOne, subArrayAtK * subarrayMinSumAtKMinusOne) < subArrayAtK){
                subArrayMinSumWithKthVal = Math.min(subArrayAtK * subarrayMaxSumAtKMinusOne, subArrayAtK * subarrayMinSumAtKMinusOne); //subArrayMinSumWithKthVal;
            }else{
                subArrayMinSumWithKthVal = subArrayAtK;
            }

            globalMaxSubarraySum = Math.max(globalMaxSubarraySum, subarrayMaxSumWithKthVal);
            subarrayMaxSumAtKMinusOne = subarrayMaxSumWithKthVal;
            subarrayMinSumAtKMinusOne = subArrayMinSumWithKthVal;
        }

        return globalMaxSubarraySum;
    }

    public int maxProductConcise(int[] nums) {

        int subarrayMaxSumWithAtKMinusOne  = nums[0];
        int subarrayMinSumAtKMinusOne      = nums[0];
        int globalMaxSubarraySum           = nums[0];
        System.out.println(subarrayMinSumAtKMinusOne +" " +subarrayMaxSumWithAtKMinusOne +" " +globalMaxSubarraySum);

        for(int i = 1; i < nums.length; ++i){
            int subArrayAtK = nums[i];
            int subarrayMaxSumAtK = Math.max(subArrayAtK * subarrayMaxSumWithAtKMinusOne, subArrayAtK * subarrayMinSumAtKMinusOne);
            int subArrayMinSumAtK = Math.min(subArrayAtK * subarrayMaxSumWithAtKMinusOne, subArrayAtK * subarrayMinSumAtKMinusOne);

            subarrayMaxSumWithAtKMinusOne = Math.max(subArrayAtK, subarrayMaxSumAtK);
            subarrayMinSumAtKMinusOne = Math.min(subArrayAtK, subArrayMinSumAtK);

            globalMaxSubarraySum = Math.max(globalMaxSubarraySum, subarrayMaxSumWithAtKMinusOne);
            System.out.println(subarrayMinSumAtKMinusOne +" " +subarrayMaxSumWithAtKMinusOne +" " +globalMaxSubarraySum);
        }
        return globalMaxSubarraySum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        MaximumProductSubarray obj = new MaximumProductSubarray();
        System.out.println(obj.maxProduct(nums));
    }

}
