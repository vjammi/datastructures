package ds.patterns.binarysearch;

public class BinarySearch {

    // a = [0, 11, 22, 33, 44, 55, 66, 66, 66, 66, 66, 66, 66, 66, 109, 119, 120]
    // i    0  1   2   3   4   5   6   7   8   9   10  11  12  13  14   15   16
    //                             ^       ^                   ^
    //                            first   mid                 last

    // Note:
    // This could result in an integer overflow - When sum of low and high is integer max.
    //      int mid = (low + high)
    // A better approach would be to use
    //      int mid = low + (high-low)2;
    // For example
    //        0+(16-0)/2   = 0+8      = 8
    //        8+(16-8)/2   = 8+4      = 12
    //        12+(16-12)/2 = 12+(4)/2 = 14

    private int result = -1;

    private int search(int[] arr, int key, int low, int high) {
        if (low > high)
            return -1;

        int mid = low + (high-low) / 2;

        if (key == arr[mid]) {
            return mid;
        }else if(key < arr[mid]) {
            high = mid - 1;
            return search(arr, key, low, high);
        } else { // if (key > arr[mid])
            low = mid + 1;
            return search(arr, key, low, high);
        }
    }

    private int searchIteratively(int[] a, int key, int low, int high) {

        while (low <= high) { // *** low <= high. // When low > high, we exit out of the while loop, return -1 since the element was not found in the array.
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
        // When low > high, we exit out of the while loop, returning -1 since the element was not found in the array.
        return -1;
    }

    private void searchFirstOccurrence(int[] a, int key, int low, int high) {
        if (low > high)
            return;

        // Could result in an integer overflow - When low and high are both at integer max.
        // int mid = (low + high) / 2;
        int mid = low + (high - low)/2;

        // Note: while searching for the first occurrence if you go too far left passing the first occurrence, you will still be bought back
        if (key == a[mid]) {
            result = a[mid]; System.out.println("Found an occurrence " + a[mid] + " at index " + mid +" Result " +result);
            high = mid - 1;
            searchFirstOccurrence(a, key, low, high);
        }else if(key < a[mid]) {
            high = mid - 1;
            searchFirstOccurrence(a, key, low, high);
        } else { // if (key > a[mid])
            low = mid + 1;
            searchFirstOccurrence(a, key, low, high);
        }
    }
    private void searchLastOccurrence(int[] a, int key, int low, int high) {
        if (low > high)
            return;

        int mid = low + (high - low)/2;

        // Note: While searching for the last occurrence if you go too far right passing the last occurrence,
        // you will still be bought back
        if (key == a[mid]) {
            result = a[mid];
            System.out.println("Found an occurrence " + a[mid] + " at index " + mid +" Result " +result);
            low = mid + 1;
            searchLastOccurrence(a, key, low, high);
        }else if(key < a[mid]) {
            high = mid - 1;
            searchFirstOccurrence(a, key, low, high);
        } else { // if (key > a[mid])
            low = mid + 1;
            searchLastOccurrence(a, key, low, high);
        }
    }

    // first occurrence - O(log(n))
    // last  occurrence - O(log(n))
    // # of occurrences - (lastIndex - firstIndex + 1) 2 x O(log(n))
    private int countOccurrence(int[] a, int key, int low, int high){
        int numOfOccurrences = 0;
        // To be implemented - return (lastIndex - firstIndex + 1); // 2 x O(log(n))
        return numOfOccurrences;
    }

    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();
        //int[] a = {10,15,17,19,20,31,44,55,77,99, 100, 101,102, 106, 109, 110};
                // 0  1   2   3   4   5   6   7   8   9   10  11  12  13  14   15   16
        int[] a = {0, 11, 22, 33, 44, 55, 66, 66, 66, 66, 66, 66, 66, 66, 109, 119, 120};
        //                                ^       ^
        //                               mid      mid

        int low = 0; int high = a.length - 1;
        System.out.println("result: " +search.search(a, 119, low, high));
        //search.searchIteratively(a, 66, low, high);
        //search.searchFirstOccurrence(a, 66, low, high);
        //search.searchLastOccurrence(a, 66, low, high);
    }
}
