package ds.binarysearch;

public class BinarySearch {
    private int result = -1;

    public BinarySearch() {}

    //     0  1   2   3   4   5   6   7   8   9   10  11  12  13  14   15   16
    //a = [0, 11, 22, 33, 44, 55, 66, 66, 66, 66, 66, 66, 66, 66, 109, 119, 120]
    //                            ^       ^                   ^
    //                            first   mid                 last
    private int search(int[] a, int key, int low, int high) {
        int mid = (low + high) / 2;
        if (key < a[mid]) {
            high = mid - 1;
            return search(a, key, low, high);
        } else if (key > a[mid]) {
            low = mid + 1;
            return search(a, key, low, high);
        } else {  // if (key == a[mid])
            System.out.println("Found the key "+a[mid] + " at index " + mid);
            return a[mid];
        }
    }
    private void searchFirstOccurrence(int[] a, int key, int low, int high) {
        if (low > high)
            return;

        int mid = (low + high) / 2;

        if (key == a[mid]) {
            result = a[mid];
            System.out.println("Found an occurrence " + a[mid] + " at index " + mid +" Result " +result);

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

        int mid = (low + high) / 2;

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
        return -1; // or low???
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
        search.search(a, 66, low, high);
        search.searchFirstOccurrence(a, 66, low, high);
        search.searchLastOccurrence(a, 66, low, high);
        search.searchIteratively(a, 66, low, high);
    }
}
