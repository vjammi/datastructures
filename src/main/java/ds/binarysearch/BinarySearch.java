package ds.binarysearch;

public class BinarySearch {

    private int result = -1;

    // a = [0, 11, 22, 33, 44, 55, 66, 66, 66, 66, 66, 66, 66, 66, 109, 119, 120]
    // i    0  1   2   3   4   5   6   7   8   9   10  11  12  13  14   15   16
    //                             ^       ^                   ^
    //                            first   mid                 last
    private void searchRecursively(int[] a, int key, int low, int high) {
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
            searchFirstOccurrence(a, key, low, high);
        } else { // if (key > a[mid])
            low = mid + 1;
            searchFirstOccurrence(a, key, low, high);
        }
    }

    private int searchIteratively(int[] a, int key, int low, int high) {

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

    private void searchFirstOccurrence(int[] a, int key, int low, int high) {
        if (low > high)
            return;

        // Could result in an integer overflow - When low and high are both at integer max.
        //int mid = (low + high) / 2;

        int mid = low + (high - low)/2;
        // 0+(16-0)/2   = 0+8      = 8
        // 8+(16-8)/2   = 8+4      = 12
        // 12+(16-12)/2 = 12+(4)/2 = 14

        // Note: while searching for the first occurrence if you go too far left passing the first occurrence,
        // you will still be bought back
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

        // Note: while searching for the last occurrence if you go too far right passing the last occurrence,
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
        //                                ^        ^
        //                               mid      mid

        int low = 0; int high = a.length - 1;

        search.searchRecursively(a, 6, low, high);
        search.searchIteratively(a, 66, low, high);

        search.searchFirstOccurrence(a, 66, low, high);
        search.searchLastOccurrence(a, 66, low, high);
    }
}
