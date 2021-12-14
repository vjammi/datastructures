package ds.patterns.subarray;

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
    public int maxProduct(int[] nums) {
        int maxProductSoFar = nums[0];          // Initialize to nums[0], since j starts from index 1
        for (int i=0; i<nums.length; i++){
            int product = 1;                    // Initialize to 1, not 0 :)
            for (int j=i; j<nums.length; j++){  // here j == i - if a single element is equal to the sum
                product = product * nums[j];
                if (product > maxProductSoFar){
                    maxProductSoFar = product;
                }
                //System.out.println("[" +nums[i] +", " +nums[j] +"]    MaxProduct =" +maxProductSoFar);
            }
            //System.out.println("");
        }
        return maxProductSoFar;
    }

       /*
        //      Using Kadane's Algorithm

        //                     nums = [-2, 1, -3, 4, -1,   2,  1, -5,   4]
        //      currSubArrayProdWith =    -2   6  24 -24 -48 -48  240  960
        //   currSubArrayProdWithout =     1  -3 -12  12  24  24 -120 -480
        //      maxSubArrayProdSoFar =     1   6  24  24  24  24  240  960


        //          nums =    [2, -5,   3,   1,   -4  0  -10  2  8]
        //   minSoFar    =     2  -10 -30  -30   -12
        //   maxSoFar    =     2   -5   3    3   120  -4

        //   maxProduct  =     2   -5  24 -24 -48  ???

        minSoFar = Math.min(num, Math.min())
        // -2 +  1 >  1  [F]
        //  1 + -3 > -3  [T]
        // -2 +  4 >  4  [F]
        //  4 + -1 > -1  [T]
        //  3 +  2 >  2  [T]
        //  5 +  1 >  1  [T]
        //  6 + -5 > -5  [T]
        //  1 +  4 >  4  [T]

            [-2, 1, -3, 4, -1, 2, 1, -5, 4]
            [2,3,-2,4]
    */

    public int maxProduct1(int[] nums) {

        //int maxSubArrayProductSoFar       = nums[0];
        int minSoFar   = nums[0];
        int maxSoFar = nums[0];

        for (int i=1; i < nums.length; i++){
            int num = nums[i];
            minSoFar    = Math.min(num, Math.min(minSoFar*num, maxSoFar*num));

            int tempMax  = Math.max(num, Math.max( minSoFar*num, maxSoFar*num));
            maxSoFar = Math.max(maxSoFar, tempMax);

        }
        return maxSoFar;
    }

}
