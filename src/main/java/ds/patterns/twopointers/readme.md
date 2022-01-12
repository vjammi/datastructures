## Two Pointers
Two pointers is a technique used for searching pairs of numbers in a array or a list.

### Problem #1
Given the array, find if there exists any pair of elements (arr[i], arr[j]) such that their sum is equal to the target.

#### Naive Approach
```
    /**
         0  1  2  3  4  5   6  7
         arr[] = {3, 5, 9, 2, 8, 10, 11} target  = 8  // unsorted
         arr[] = {2, 3, 5, 8, 9, 10, 11}  target = 8  // sorted
             i =                 ^
             j =                     ^

        Runtime: O(n^2)
     */
    private boolean isPairSumNaive1(int[] arr, int target) {
        Arrays.sort(arr);

        int length = arr.length;
        for (int i = 0; i < length-1; i++) {
            for (int j = i+1; j < length; j++) {  // We are looking for distinct pairs, not pair an element by itself. So i cannot be j to form the pair
                int sum = arr[i] + arr[j];
                if (sum == target)      // pair found
                    return true;
            }
        }
        return false;          // No pair found with given sum.
    }

    /**
                  0  1  2  3  4  5   6  7
         arr[] = {3, 5, 9, 2, 8, 10, 11} target  = 8  // unsorted
         arr[] = {2, 3, 5, 8, 9, 10, 11}  target = 8  // sorted
             i =                 ^
             j =                     ^

        Runtime: O(n * n/2) = O(n^2)
    */
    private boolean isPairSumNaive2(int[] arr, int target) {
        Arrays.sort(arr);
        // iterate i=0 to n-1, j=i+1 to n
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) { // Optimization: j=i+1
                int sum = arr[i] + arr[j];
                if (sum == target)      // pair found
                    return true;

                if (sum > target) // Optimization: Since the array is sorted, we break iterating the inner loop and start at the next ith pointer
                    break;
                else
                    continue; // continue iterating  the inner loop looking for the pair.
            }
        }
        return false; // No pair found
    }
```

#### Two Pointer Approach

We take two pointers, one representing the first element and other representing the last element of the array, and then we add the values kept at both the pointers.
If their sum is smaller than target then we shift the left pointer to right or if their sum is greater than target then we shift the right pointer to left, in order to get closer to the sum.
We keep moving the pointers until we get the sum as target or we exhaust the search space.

The algorithm uses the fact that the input array is sorted. We start the sum of extreme values (smallest and largest) and conditionally move both pointers.
We move left pointer i when the sum of arr[i] and arr[j] is less than target. We do not miss any pair because the sum is already smaller than target. Same logic applies for right pointer j.
```
    /**
         Two pointer technique based solution to find if there is a pair in arr[0...n-1] with a given sum.
         arr[] = {3, 5, 9, 2, 8, 10, 11} target  = 8  // unsorted
         arr[] = {2, 3, 5, 8, 9, 10, 11}  target = 8  // sorted
                  -> ^  ^ <------------
                     i  j
         Runtime = O(n)
     */
    public boolean isPairSumUsingTwoPointers(int[] arr, int target) {
        Arrays.sort(arr);
        int i = 0; int j = arr.length-1;
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == target) { // If we find a pair
                return true;
            }else if (sum < target) { // If sum is less than the target, we move towards higher values (i++)
                i++;
            }else if (sum > target){ // If sum is greater than the target, we move towards lower values (j--)
                j--;
            }
        }
        return false;
    }
```

### Problem #2
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0. Note that the solution set must not contain duplicate triplets.

#### Naive Approach
```
     // Input: nums = [-1,0,1,2,-1,-4]
     // Output: [[-1,-1,2],[-1,0,1]]
     // Runtime: O(n^3)

    public List<List<Integer>> threeSumNaive(int[] nums) {
        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet();
        int size = nums.length;
        for (int i=0; i < size-2; i++){
            for (int j=i+1; j < size-1; j++){
                for (int k=j+1; k <size; k++){
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0) {
                        List<Integer> list = new ArrayList();
                        list.add(nums[i]); list.add(nums[j]); list.add(nums[k]);
                        set.add(list);
                    }
                }
            }
        }
        return new ArrayList(set);
    }

#### Two Pointer Approach
```
    /**
         [-1 0 1 2 -1 -4]  - We will iterate array from i = 0; i< size-2;
           ^ ^         ^
           i j --> <-- k

        Runtime: O(n)
    */

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3)
            return new ArrayList<>(); // if size of array less than 3

        Arrays.sort(nums); // sort array

        Set<List<Integer>> set = new HashSet<>();
        int size = nums.length;
        for (int i = 0; i< size-2; i++) {   // We iterate array from i = 0; i< size-2;

            int j = i+1;
            int k = size-1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0)
                    set.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                else if(sum > 0)
                    k--;
                else if (sum < 0)
                    j++;
            }

        }
        return new ArrayList<>(set);
    }
```

## Two Pointers Vs Sliding Window
In a two pointer technique we compare the value at the both pointers instead of taking the elements between the pointers.
Also, there is a variation of two pointers called the fast and slow and pointer.

In sliding window typically we use all of the elements within the window for the problem, say to sum of all elements within a window.

References:
https://zengruiwang.medium.com/sliding-window-technique-360d840d5740
https://www.geeksforgeeks.org/two-pointers-technique/
https://stackoverflow.com/questions/64078162/is-two-pointer-problem-same-as-sliding-window
