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

## Difference between subarray, subset & subsequence
```
If we consider the array
    {1,2,3,4}
A subarray would be a contiguous sequence if elements in the array i.e.
    {1},{1,2},{1,2,3},{1,2,3,4}...

A subsequence would need not to be contiguous, but needs to maintain order i.e.
    {1,2,4}

However, a subset is similar to a subsequence except that has an empty set i.e.
    {1,3},{}

Given an array/sequence of size n
    Subarray    = n * (n+1)/2
    Subseqeunce = (2^n) -1 (non-empty subsequences)
    Subset      = 2^n
```

REFERENCES
https://stackoverflow.com/questions/26568560/difference-between-subarray-subset-subsequence
