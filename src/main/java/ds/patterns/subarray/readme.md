## All possible subarrays for nums {1,2,3,4}
```
For an array
    nums = [1,2,3,4,5]

All possible subarrays are
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
Kadane's Algorithm
```
     https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm
     Approach
          nums                = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
                                            |---------|          
          currSubArraySum     =  -2  1  -2  4   3  5  6  1   5
          maxSubArraySumSoFar =  -2  1   1  4   4  5  6  6   6
          
         if   subarray upto a[k-1] + a[k] > a[k]
              Add the a[k] to the existing subarray
         else
              Discard the subarray, and start a new one starting a[k]
     Runtime O(n)
     Space   O(1)
              
```


```
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

        //          nums = [-2  1 -3  4   -1  2   1  -5    4]
        //      minSoFar =  -2 -2 -3 -12 -24 -48 -48 -120 -480
        //      maxSoFar =  -2  1  6  24  12  24  24  240  960
        //     globalMax =  -2  1  6  24  24* 24  24  240  960   * Note that 24 is being carried from previous globalMax
    */
```

```
    //  int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    public int maxProduct(int[] nums) {
        int maxSoFar  = nums[0];
        int minSoFar  = nums[0];
        int globalMax = nums[0];
        System.out.println(minSoFar +" " +maxSoFar +" " +globalMax);

        for(int i = 1; i < nums.length; ++i){
            int tmp = maxSoFar;
            maxSoFar = Math.max(nums[i], Math.max(nums[i]*maxSoFar, nums[i]*minSoFar));
            minSoFar = Math.min(nums[i], Math.min(nums[i]*tmp, nums[i]*minSoFar));
            globalMax = Math.max(globalMax, maxSoFar);
            System.out.println(minSoFar +" " +maxSoFar +" " +globalMax);
        }
        return globalMax;
    }
```

## Difference between subarray, subset & subsequence
```
If we consider the array 
    {1,2,3,4}
A subarray would be a contiguous sequence of elements in the array
    {1},{1,2},{1,2,3},{1,2,3,4}...

A subsequence would need not to be contiguous sequence of elements, but the sequence needs to maintain order
    {1,2,4}

A subset is similar to a subsequence except, in addition to an empty set
    {1,3},{}

Given an array/sequence of size n
    Subarray    = n * (n+1)/2
    Subseqeunce = (2^n) -1 (non-empty subsequences)
    Subset      = 2^n
```

REFERENCES
https://stackoverflow.com/questions/26568560/difference-between-subarray-subset-subsequence
