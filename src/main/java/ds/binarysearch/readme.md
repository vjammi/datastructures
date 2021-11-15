## Binary Search

### Search Recursively
```
    // a = [0, 11, 22, 33, 44, 55, 66, 66, 66, 66, 66, 66, 66, 66, 109, 119, 120]
    // i    0  1   2   3   4   5   6   7   8   9   10  11  12  13  14   15   16
    //                             ^       ^                   ^
    //                            first   mid                 last

    private void search(int[] a, int key, int low, int high) {
        if (low > high) // Base condition #1 - will cause the recursion to end
            return;

        // Could result in an integer overflow - When low and high are both at integer max at the same time.
        // int mid = (low + high) / 2;

        // A better approach would be, mid=low+(high-low)/2;
        //      0+(16-0)/2   = 0+8      = 8
        //      8+(16-8)/2   = 8+4      = 12
        //      12+(16-12)/2 = 12+(4)/2 = 14
        int mid = low + (high-low) / 2;
        if (key == a[mid]) {  // Base condition #2 - Will cause the recursion to end
            result = a[mid]; System.out.println("Found " + a[mid] + " at index " + mid +" Result " +result);
            return;
        }else if(key < a[mid]) {
            high = mid - 1;
            search(a, key, low, high);
        } else { // if (key > a[mid])
            low = mid + 1;
            search(a, key, low, high);
        }
    }
```

### Search Iteratively
```
    private int search(int[] a, int key, int low, int high) {

        while (low <= high) {
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

