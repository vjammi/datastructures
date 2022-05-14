## Binary Search

```
    // a = [0, 11, 22, 33, 44, 55, 66, 66, 66, 66, 66, 66, 66, 66, 109, 119, 120]
    // i    0  1   2   3   4   5   6   7   8   9   10  11  12  13  14   15   16
    //                             ^       ^                   ^
    //                            first   mid                 last
```

```
        // Could result in an integer overflow - When low and high are both at integer max at the same time.
        //  int mid = (low + high) / 2;

        // A better approach would be,
        //  mid=low+(high-low)/2;
        //      0+(16-0)/2   = 0+8      = 8
        //      8+(16-8)/2   = 8+4      = 12
        //      12+(16-12)/2 = 12+(4)/2 = 14
```

### Search Recursively
```
    private int search(int[] arr, int key, int low, int high) {
        // When low > high, we exit out of the recursive loop, return -1 since the element was not found in the array
        if (low > high) // ***  ~ while (low <= high) { 
            return -1;

        int mid = low + (high-low) / 2;

        if (key == arr[mid]) {
            System.out.println("Found " + arr[mid] + " at index " + mid);
            return mid;
        }else if(key < arr[mid]) {
            high = mid - 1;
            return search(arr, key, low, high);
        } else { // if (key > arr[mid])
            low = mid + 1;
            return search(arr, key, low, high);
        }
    }
```

### Search Iteratively
```
    private int search(int[] a, int key, int low, int high) {

        while (low <= high) {    // *** ~ if (low > high)
            int mid = (low + high) / 2;
            if (a[mid] == key) {
                System.out.println("Found the key [" + key + "] at mid[" + mid + "] Low[" + low + "] High[" + high + "]");
                return mid;
            } else if (key < a[mid]) {
                high = mid - 1;
            } else if (key > a[mid]) {
                low = mid + 1;
            }
        }
        // When low > high, we exit out of the while loop, return -1 since the element was not found in the array.
        return -1;
    }
```

## Search in a Rotated Array
```
         num     33
         nums   [44, 55, 66, 77, 88, 99, 1, 11, 22, 33  33]
            i    0   1   2   3   4   5   6  7   8   9   10
                 ^                                      ^
          mid                        ^
                                                ^
                                                    ^ 
                                                 33 found
        0+(11-0)/2   = 0+11/2   = 5
        6+(10-6)/2   = 6+4/2    = 8
        9+(10-9)/2   = 9+1/2    = 9
```

```
    public int search(int[] nums, int target, int low, int high) {
        if (low > high)
            return -1;

        int mid = low + (high-low)/2;

        if (nums[mid] == target){                           // At some point mid will contain the target
            return mid;
        }
        else if (nums[low] <= nums[mid]){                   //  else - Search space to the left of mid is sorted
        
            // Determine if the target is within the left sorted space, or right rotated search space
            if (target >= nums[low] && target < nums[mid])  //  if - num is in the left sorted search space
                return search(nums, target, low, mid-1);    
            else                                            //  else - num is in the right rotated/sorted search space
                return search(nums, target, mid+1, high);   // We eleminate the left sorted space (similar to searching in the original mixed search space)
        }
        else{ //  else - (nums[mid+1] > nums[high]) - Search space to the right of mid is sorted
            // Determine if the target is within the right sorted space, or left rotated search space
            if (target > nums[mid] && target <= nums[high]) //  if - num is in the right sorted search space
                return search(nums, target, mid+1, high);
            else                                            // else - num is in the left rotated/sorted search space. 
                return search(nums, target, low, mid-1);    //  We eleminate the right sorted space (similar to searching in the original mixed search space)
        }
    }
```

## Binary Search Patterns

### 1 Given an array of integers sorted in ascending order, find the frequency of a given target value
```

    nums = [1,2,2,2,2,2,3,4,5,6,7,8], target = 2
    frequency = 5
            0  1  2  3  4  5  6  7  8  9  10 11
    nums = [1, 2, 2, 2, 2, 2, 2, 2, 5, 6, 7, 8]
                           ^
                  ^
            ^
               ^
            ^

              low = 1
                                    ^
                        ^
                              ^
```
### 2 Given a sorted array of integers, find the number of times the array has been rotated.
```
            0   1   2   3   4   5   6   7   8   9
    nums = [15, 16, 17, 18, 19, 20, 21, 12, 13, 14]
                            ^
                                    ^
            --------------------------  ^   ------
    // Property that can can be noted - The pivot element is lesser than its left and right

```

### 3  Given a sorted roted array of integers nums, find the target number.
```
              0   1   2   3   4   5   6   7   8   9
      nums = [15, 16, 17, 18, 19, 20, 21, 12, 13, 14]
                              ^
                                      ^
              --------------------------  ^   ------
      // Property that can can be noted - The pivot element is lesser than its left and right
```

### 4 Given an M rows and N Cols matrix containing binary digits, with each row sorted
      Write a function to find the row which has max num of 1s
```               // 0 ...........................  N-1
    int nums[][] =  [0   0   0   0   1   1   1   1   1]      // 0
                    [0   0   0   0   0   1   1   1   1]
                    [0   0   0   1   1   1   1   1   1]
                    [0   0   0   0   0   1   1   1   1]
                    [0   1   1   1   1   1   1   1   1]
                    [0   0   1   1   1   1   1   1   1]       // M-1

    Approach
       - Find the leftmost 1 in each row.
       - Repeat for all rows to find the max num of 1s

    Runtime
       M log(N)
```


### 5 Given an M by N matrix containing integers, with each row sorted from left to right with the first integer of each row
      greater than the last integer of the previous row.
      Write a function to find the target value in the matrix.

```
                  // 0   ........ N-1
    int nums[][] =  [0   1   2   3   4]      // 0
                    [5   6   7   8   9]
                    [10  11  12  13 14]
                    [15  16  17  18 19]
                    [20  21  22  23 24]
                    [25  26  27  28 29]       // M-1
         target = 17

    Approach:
        - Run a binary search across the index 0 of each row, to find which row the target might lie in.
        - Once the row is found continue with the binary search across the values of the row to find the target within that row
    Runtime
        log (M+N)
```
### 6 Given an array of positive integers and a positive integer s,
      find the minimal length of a contiguous subarray of which the sum >= s
      If there isnt one, return 0 instead.


References:
- https://youtube.com/playlist?list=PL2_aWCzGMAwL3ldWlrii6YeLszojgH77j
- Binary Search 
  - Part 1: https://youtu.be/U66U1-umNbQ
  - Part 2: https://youtu.be/V343UXw4P3Y
- Search element in a circular sorted array 
  - https://youtu.be/uufaK2uLnSI?list=PL2_aWCzGMAwL3ldWlrii6YeLszojgH77j&t=597
- Binary Search CodeSchool
  - https://youtu.be/4qjprDkJrjY
- How many times an array has been rotated?
  - https://youtu.be/4qjprDkJrjY