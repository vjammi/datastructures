## Two Pointers

Two pointers is a technique used for searching pairs of numbers in a array or a list.

Given an array, find if there exists any pair of elements (arr[i], arr[j]) such that their sum is equal to the target.

### Naive Approach
```
    // arr[] = {3, 5, 9, 2, 8, 10, 11}; target = 17;
    //          ^  ^
  private boolean isPairSum_naive(int arr[], int target) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)            // as equal i and j means same element
                    continue;
                int sum = arr[i] + arr[j];
                if (sum == target) // pair exists
                    return true;
                if (sum > target)  // as the array is sorted
                    break;
            }
        }
        return false; // No pair found with given sum.
    }
```

### Two Pointer Approach

Given the array is sorted in ascending order, find if there exists any pair of elements (arr[i], arr[j]) such that their sum is equal to the target.

We take two pointers, one representing the first element and other representing the last element of the array, and then we add the values kept at both the pointers.
If their sum is smaller than target then we shift the left pointer to right or if their sum is greater than target then we shift the right pointer to left, in order to get closer to the sum.
We keep moving the pointers until we get the sum as target.

The algorithm uses the fact that the input array is sorted. We start the sum of extreme values (smallest and largest) and conditionally move both pointers.
We move left pointer i when the sum of arr[i] and arr[j] is less than target. We do not miss any pair because the sum is already smaller than target. Same logic applies for right pointer j.

```
    // Two pointer based solution to find if there is a pair in arr[0...n-1] with a given sum.
    // arr[] = {3, 5, 9, 2, 8, 10, 11}; target = 17
    //          ^                   ^
    public boolean isPairSum_twoPointers(int arr[], int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int i = 0;          // represents first pointer
        int j = n-1;        // represents second pointer
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == target) { // If we find a pair
                return true;
            }else if (sum < target) { // If sum is less than the target, we move towards higher values (i++)
                i++;
            }else if (sum > target){ // If sum is grater than the target, we move towards lower values (j--)
                j--;
            }
        }
        return false;
    }
```
### Two Pointers Vs Sliding Window
In a two pointer technique we compare the value at the both pointers instead of taking the elements between the pointers.
Also, there is a variation of two pointers called the fast and slow and pointer.

In sliding window typically we use all of the elements within the window for the problem (for eg - sum of all elements in the window).



