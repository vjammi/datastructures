## Sliding Window

### Overview
Used within an iterable / sequential data structures such as an array or String.
Problems can be solved naively using quadratic time complexity, but sliding window reduces the time complexity to linear time.
You have a window over a section of an array and you slide the window from start to the end. As you do that, you change the size of the window.

### Type of Problems
1. Minimum Size SubArray Sum
2. Longest SubArray without Repeating Characters
3. Find All Anagrams in a String

### Sliding Window Technique - Details
This technique shows how a nested for loop in some problems can be converted to a single for loop to reduce the time complexity.
We can take a problem to illustrate the technique
Given an array of integers of size n, calculate the max sum of k consecutive elements in the array.
```
Input  : arr[] = {100, 200, 300, 400}
         k = 2
Output : 700
```
Notice how we get maximum sum by adding sub-array {300, 400} of size 2.
```
Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}
         k = 4
Output : 39
```
Notice how we get maximum sum by adding sub-array {4, 2, 10, 23} of size 4.

### Solution using nested loop - O(n*k)
```
    // Runtime O(n*k)
    public int maxSumNestedLoop(int arr[], int n, int k) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n-k+1; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum = sum + arr[j];
            }
            maxSum = Math.max(sum, maxSum);
            System.out.println("MaxSum:" +maxSum +" @ Window i: " +i +" j:" +(i+k));
        }
        return maxSum;
    }
```

### Solution using Sliding Window - O(n)
The time complexity of this solution is O(n) because each element is visited at most twice.
In the worst case scenario, all elements will be visited once by the start pointer and another time by the end pointer.
The space complexity would be O(1) because the solution does not create new data structures.
```
    // Return maximum sum in a sub-array of size k.
    //    int arr[] = {1, 4, 2, 10, 2, 3, 1, 0, 20}, n = 9, k = 4
    //                 0  1  2  3   4  5  6  7  8
    //                              ^
    //                    ^
    // Runtime O(n)
    private int maxSum = 0;
    public int maxSumSlidingWindow(int arr[], int n, int k) {
        if (n<k)
            System.out.println("invalid input");

        int sum = 0;
        int windowSize = 0;
        int j = 0;
        for (int i=0; i<n; i++){
            sum = sum + arr[i];
            windowSize++;

            while(windowSize >= k){
                maxSum = Math.max(sum, maxSum);
                System.out.println("MaxSum:" +maxSum +" @ Window j: " +j +" i:" +i);

                sum = sum - arr[j];
                windowSize--;
                j++;
            }
        }
        return maxSum;
    }
```

### Sliding Window Vs Two Pointers
In sliding window typically we use all of the elements within the window for the problem (for eg - sum of all elements in the window).
In a two pointer technique we compare the value at the both pointers instead of taking the elements between the pointers.
Also, there is a variation of two pointers called the fast and slow and pointer.

References:
https://zengruiwang.medium.com/sliding-window-technique-360d840d5740
https://www.geeksforgeeks.org/two-pointers-technique/
https://stackoverflow.com/questions/64078162/is-two-pointer-problem-same-as-sliding-window