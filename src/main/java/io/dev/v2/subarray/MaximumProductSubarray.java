package io.dev.v2.subarray;

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

    /**
        Scenarios

           1. If all positives we can keep multiplying all the elements
                +ve x Max (+ve) = +ve
           2. If we come across a -ve, then we will reduce the product
                -ve x Max (+ve) = -ve
           3. However, if we come across another negative, it will increase the product based on previous -ve
                -ve x Min (-ve) = +ve
           4. If we come across a 0 it will take our product down to 0

           //                n =      1 ----------------------- n-1
           //           nums = [-2  1 -3  4   -1  2   1  -5    4]
           //   minProdAtKth =  -2 -2 -3 -12 -24 -48 -48 -120 -480
           //   maxProdAtKth =  -2  1  6  24  12  24  24  240  960
           // globalMaxAtKth =  -2  1  6  24  24* 24  24  240  960      * Note that 24 is being carried from previous globalMax

           //   globalMaxAtKth = max(globalMaxAtKth, maxProdAtKth)

    */

    public int maxProduct(int[] nums) {

        int maxProdAtKMinus1   = nums[0];
        int minProdAtKMinus1   = nums[0];
        int globalMaxAtKth     = nums[0];
        System.out.println(minProdAtKMinus1 +" " +maxProdAtKMinus1 +" " +globalMaxAtKth);

        for(int k=1; k<nums.length; k++){
            int current = nums[k];

            int maxProdAtKth = 0;
            if (Math.max(current * maxProdAtKMinus1, current * minProdAtKMinus1) > current){
                maxProdAtKth = Math.max(current * maxProdAtKMinus1, current * minProdAtKMinus1);
            }else{
                maxProdAtKth = current;
            }

            int minProdAtKth = 0;
            if (Math.min(current * maxProdAtKMinus1, current * minProdAtKMinus1) < current){
                minProdAtKth = Math.min(current * maxProdAtKMinus1, current * minProdAtKMinus1); //minProdAtKth;
            }else{
                minProdAtKth = current;
            }

            globalMaxAtKth = Math.max(globalMaxAtKth, maxProdAtKth);
            maxProdAtKMinus1 = maxProdAtKth;
            minProdAtKMinus1 = minProdAtKth;
        }

        return globalMaxAtKth;
    }

    public int maxProductConcise(int[] nums) {

        int maxProdAtKMinusOne  = nums[0];
        int minProdAtKMinusOne  = nums[0];
        int globalMax           = nums[0];

        for(int i = 1; i < nums.length; ++i){
            int current = nums[i];
            int maxProdAtK = Math.max(current * maxProdAtKMinusOne, current * minProdAtKMinusOne);
            int minProdAtK = Math.min(current * maxProdAtKMinusOne, current * minProdAtKMinusOne);

            maxProdAtKMinusOne = Math.max(current, maxProdAtK);
            minProdAtKMinusOne = Math.min(current, minProdAtK);

            globalMax = Math.max(globalMax, maxProdAtKMinusOne);
            System.out.println(minProdAtKMinusOne +" " +maxProdAtKMinusOne +" " +globalMax);
        }
        return globalMax;
    }


    /**

         Input: nums = [2, -5,  3,  1,  -4,   0, -10, 2,   8]
                        ^   ^
             current    2  -5   3   1   -4    0  -10  2    8
             maxProd    2  -5   3   3   120   0   0   2    16
             minProd    2  -10 -30 -30  -12   0  -10 -20  -160

        globalMaxProd   2        3      120                     = 120
     */
    public int maxProductRefactored(int[] nums) {
        int minProd =  nums[0]; // Min product at current minus-1 th index
        int maxProd =  nums[0]; // Max product at current minus-1 th index
        int globalMaxProd = maxProd;

        for (int i=1; i<nums.length; i++){
            int current = nums[i];
            int maxProdCurrIndex = Math.max(current, Math.max(minProd * current, maxProd * current));
            int minProdCurrIndex = Math.min(current, Math.min(minProd * current, maxProd * current));
            maxProd = maxProdCurrIndex;
            minProd = minProdCurrIndex;
            if (maxProd > globalMaxProd)  globalMaxProd = maxProd;
        }
        return globalMaxProd;
    }

    public static void main(String[] args) {
        int[] nums = {2, -5,  3,  1,  -4,   0, -10, 2, 8}; //{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        MaximumProductSubarray obj = new MaximumProductSubarray();
        System.out.println(obj.maxProduct(nums));
    }

}
