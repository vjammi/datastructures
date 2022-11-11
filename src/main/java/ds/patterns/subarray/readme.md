## All possible subarrays for nums {1,2,3,4}
```
For an array
    nums = [1,2,3,4,5]

All possible subarrays are
    i   [i, j]   Array Values
    ----------------------------
    0   [0, 0]   [1]                        SubArrays starting from the 0th index 
        [0, 1]   [1, 2]
        [0, 2]   [1, 2, 3]
        [0, 3]   [1, 2, 3, 4]
        [0, 4]   [1, 2, 3, 4, 5]

    1   [1, 1]  [2]                         SubArrays starting from the 1th index
        [1, 2]  [2, 3]
        [1, 3]  [2, 3, 4]
        [1, 4]  [2, 3, 4, 5]

    2   [2, 2]  [3]                        SubArrays starting from the 2th index 
        [2, 3]  [3, 4]
        [2, 4]  [3, 4, 5]

    3   [3, 3]  [4]                        SubArrays starting from the 3rd index 
        [3, 4]  [4, 5]

    4   [4, 4]  [ 5]                       SubArrays starting from the 4th index 
```

## Printing All Subarrays
```
    public void subarray(int[] nums) {
        for (int i=0; i<nums.length; i++){
            for (int j=i; j<nums.length; j++){
                System.out.println(nums[i] +", " +nums[j]);
            }
        }
    }
```

## 53. Maximum Subarray 
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
A subarray is a contiguous part of an array.
```
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

```
## Kadane's Algorithm 

### Approach
```
         https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm
     
//                            k =      1 -------------------- n-1
//                         nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
//                                            |---------|          
//                  subArrayAtK =      1, -3, 4, -1, 2, 1, -5, 4
//         subArraySumAtKMinus1 =  -2  1  -2  4   3  5  6   1  5    // subArraySumAtKMinus1 + subArrayAtK
//                                     f   f  f   y  y  y   f  y    // subArraySumAtKMinus1 + subArrayAtK > subArrayAtK 
//          maxSubArraySumSoFar =  -2  1   1  4   4  5  6   6  6
          
//         if   subarray upto a[k-1] + a[k] > a[k]
//              Add the a[k] to the existing subarray
//         else
//              Discard the subarray, and start a new one starting a[k]
              
//         Runtime O(n)
//         Space   O(1)
              
```
### Implementation
```
    public int maxSubArray(int[] nums) {

        int subArraySumAtKMinus1 = nums[0];
        int maxSubArraySumSoFar  = nums[0]; // should it be initialized to 0 ???

        for (int k=1; k < nums.length; k++){
            int num = nums[k];
            // Check to add num at current index to existing subarray. At this point we could have 2 options: 
            //      positive subArray until k-1 with a positive num at current index k 
            //      positive subArray until k-1 with a negative num at current index k
            if ( subArraySumAtKMinus1 + num > num ){
                subArraySumAtKMinus1 = subArraySumAtKMinus1 + num;
            }else{ // To discard the existing subarray and start new subarray [negative subarray + positive current num]
                subArraySumAtKMinus1 = num; // Not resetting or keeping track of the start index
            }
            maxSubArraySumSoFar = Math.max(maxSubArraySumSoFar, subArraySumAtKMinus1);
        }
        return maxSubArraySumSoFar;
    }         
```

## 152. Maximum Product Subarray
Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
The test cases are generated so that the answer will fit in a 32-bit integer.
A subarray is a contiguous subsequence of the array.
```
    Scenarios     
    1. If all positives we can keep multiplying all the elements
        +ve x Max (+ve) = +ve
    2. If we come across a -ve, then we will reduce the product
        -ve x Max (+ve) = -ve
    3. However, if we come across another negative, it will increase the product based on previous -ve
        -ve x Min (-ve) = +ve
    4. If we come across a 0 it will take our product down to 0
    
    //                n =    1 ----------------------- n-1
    //           nums = [-2  1 -3  4   -1  2   1  -5    4]
    //   minProdAtKth =  -2 -2 -3 -12 -24 -48 -48 -120 -480
    //   maxProdAtKth =  -2  1  6  24  12  24  24  240  960
    // globalMaxAtKth =  -2  1  6  24  24* 24  24  240  960      * Note that 24 is being carried from previous globalMax    
    //      globalMaxAtKth = max(globalMaxAtKth, maxProdAtKth)        

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
```
