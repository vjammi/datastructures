## Subarray, Subsequence, Subset, Substrings
```
If we consider the array 
    {1,2,3,4}
    
A subarray would be a contiguous sequence of elements in the array
    {1},{1,2},{1,2,3},{1,2,3,4}...

A subsequence would need not to be contiguous sequence of elements, but the sequence/enrichedTransaction of the elements need to be maintained
    {1,2,4}

A subset is similar to a subsequence except that in addition it has an an empty set
    {1,3},{}

Given an array/sequence of size n
    Subarray    = n * (n+1)/2
    Subseqeunce = (2^n) -1 (non-empty subsequences)
    Subset      = 2^n
```
REFERENCES
https://stackoverflow.com/questions/26568560/difference-between-subarray-subset-subsequence