## Two Pointers

Two pointers is a technique used for searching pairs of something in a sorted array or list.

Given a sorted array A (sorted in ascending order), having N integers, find if there exists any pair of elements (A[i], A[j]) such that their sum is equal to X.

We take two pointers, one representing the first element and other representing the last element of the array, and then we add the values kept at both the pointers.
If their sum is smaller than X then we shift the left pointer to right or if their sum is greater than X then we shift the right pointer to left, in order to get closer to the sum.
We keep moving the pointers until we get the sum as X.
The algorithm uses the fact that the input array is sorted. We start the sum of extreme values (smallest and largest) and conditionally move both pointers.
We move left pointer i when the sum of A[i] and A[j] is less than X. We do not miss any pair because the sum is already smaller than X. Same logic applies for right pointer j.

```
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

Two pointer technique based solution to find if there is a pair in arr[0...n-1] with a given sum.
```
    public int isPairSum_twoPointers(int arr[], int target) {
        int n = arr.length;
        int i = 0;          // represents first pointer
        int j = n-1;        // represents second pointer
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == target) { // If we find a pair
                return 1;
            }else if (sum < target) { // If sum of elements at current pointers is less, we move towards higher values by doing i++
                i++;
            }else if (sum > target){ // If sum of elements at current pointers is more, we move towards lower values by doing j--
                j--;
            }
        }
        return 0;
    }
```


