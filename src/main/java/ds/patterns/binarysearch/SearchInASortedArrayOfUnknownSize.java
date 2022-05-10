package ds.patterns.binarysearch;
/**
    702. Search in a Sorted Array of Unknown Size
    This is an interactive problem.
    You have a sorted array of unique elements and an unknown size. You do not have an access to the array but you can use the ArrayReader interface to access it. You can call ArrayReader.get(i) that:
        returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
        returns 231 - 1 if the i is out of the boundary of the array.
    You are also given an integer target.
    Return the index k of the hidden array where secret[k] == target or return -1 otherwise.
    You must write an algorithm with O(log n) runtime complexity.

    Input: secret = [-1,0,3,5,9,12], target = 9
    Output: 4
    Explanation: 9 exists in secret and its index is 4.

    Input: secret = [-1,0,3,5,9,12], target = 2
    Output: -1
    Explanation: 2 does not exist in secret so return -1.

 */
public class SearchInASortedArrayOfUnknownSize {
    public int search(ArrayReader reader, int target) {
        int low = 0;


        // Option 1 to find the high
        //int high = target - reader.get(0);
        //System.out.println("Lo "+ low +" Hi " +high);

        //Option 2: Init high to 1, because the array is sorted we could increment high sequentially or exponentially until value at high > target
        // Best case - Element in array, will find the high in log(n)
        // Worst Case - Element not in array, it will look thru the entire array of size 0-high (0-9999) toi determine its not in array
        int high = 1;
        while(reader.get(high) < target) {
            // high++;            // Increment by 1 - O(n) traversal
            high = high * 2;      // exponential    - O(log(n)) traversal
            // high = high << 1;
        }
        System.out.println("Low "+ low +" High " +high);
        return search(reader, target, low, high);

    }

    public int search(ArrayReader reader, int target, int low, int high) {
        if (low > high)
            return -1;

        int mid = low + (high-low)/2;
        int midValue = reader.get(mid);
        if (target == midValue){
            return mid;
        }else if (target < midValue){
            return search(reader, target, low, mid-1);
        }else{
            return search(reader, target, mid+1, high);
        }
    }

    private class ArrayReader {

        public int get(int high) {
            return 100; // Just to make it compile
        }

    }
}
