## All possible subarrays for nums {1,2,3,4}
```
        1, 1
        1, 2
        1, 3
        1, 4

        2, 2
        2, 3
        2, 4

        3, 3
        3, 4

        4, 4
```
Printing All Subarrays
```
    public void subarray(int[] nums) {
        for (int i=0; i<nums.length; i++){
            for (int j=i; j<nums.length; j++){
                System.out.println(nums[i] +", " +nums[j]);
            }
        }
    }
```



## Difference between subarray, subset & subsequence
Array
    {1,2,3,4}
Subarray: contiguous sequence in an array i.e.
    {1,2},{1,2,3}

Subsequence: Need not to be contiguous, but maintains order i.e.
    {1,2,4}

Subset: Same as subsequence except it has empty set i.e.
    {1,3},{}

Given an array/sequence of size n
    Subarray = n*(n+1)/2
    Subseqeunce = (2^n) -1 (non-empty subsequences)
    Subset = 2^n

REFERENCES
https://stackoverflow.com/questions/26568560/difference-between-subarray-subset-subsequence
