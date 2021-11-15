package alg.sort;

public class MergeSort {

    public static void sort(int[] arr){
        int low = 0; int high = arr.length-1;
        int[] aux = new int[arr.length]; // Using an aux array - we have space O(n)
        sort(arr, aux, low, high);
    }

    private static void sort(int[] arr, int[] aux, int lo, int hi){
        if (hi <= lo) // or if (lo < hi) ???
            return;                      // checks if there is something to do first
        int mid = lo + (hi-lo) / 2;      // computes the mid point
        sort(arr, aux, lo, mid);         // sort the left/first half
        sort(arr, aux, mid+1, hi);    // sort the right/second half
        merge(arr, aux, lo, mid, hi);    // merge the two halves together
    }

    public static void merge (int[] arr, int[] aux, int lo, int mid, int hi){
        // Copy orig arr to aux array
        for (int k = lo; k<=hi; k++){
            aux[k] = arr[k];
        }
        // Iterate aux array using i & j pointers, with i=lo, j=mid+1  - aux[i++], aux[j++]
        // Iterate orig array using k pointer from lo to hi            - arr[k]
        // Sorting - Copy the elements from aux array, back to the original array in sorted order.
        int i = lo; int j = mid+1;          // Iterate aux array using i & j pointers, with i=lo, j=mid+1  - aux[i++], aux[j++]
        for (int k = lo; k <= hi; k++){     // Iterate orig array using k pointer from lo to hi            - arr[k]
            // Alternate Path - Once one of the sub-arrays are exhausted (i>mid) or (j>hi), we take the remaining
            // elements from the other sub half and move them into the original array.
            if      (i>mid)                     arr[k] = aux[j++];
            else if (j>hi)                      arr[k] = aux[i++];
            // Normal Path    - Compare the min values between i and j and copy the smallest element into the original array.
            else if (aux[j]<aux[i])             arr[k] = aux[j++];
            else                                arr[k] = aux[i++];
        }
        printArray(arr, "m");
    }

    public static void main (String[] args){
        int[] arr = {10, 17, 2, 19, 6, 11, 5}; //, 15, 20, 8,  12, 16, 3,  23, 14, 18};
        MergeSort.sort(arr);
    }

    private static void printArray(int[] a, String side) {
        System.out.print(side +": ");
        for (int k=0; k<a.length; k++){
            System.out.print(a[k]+", ");
        }
        System.out.println();
    }

}
