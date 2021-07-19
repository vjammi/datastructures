package ds.sort;

public class MergeSort {

    public static void sort(int[] arr){
        int low = 0; int high = arr.length-1;
        int[] aux = new int[arr.length];
        sort(arr, aux, low, high);
    }

    private static void sort(int[] arr, int[] aux, int lo, int hi){
        if (hi <= lo)
            return;            // checks if there is something to do first
        int mid = lo + (hi-lo) / 2;      // computes the mid point
        sort(arr, aux, lo, mid);         // sort the left/first half
        sort(arr, aux, mid+1, hi);    // sort the right/second half
        merge(arr, aux, lo, mid, hi);    // merge the two halves together
    }

    public static void merge (int[] arr, int[] aux, int lo, int mid, int hi){
        // Copy arr to aux array
        for (int k = lo; k<=hi; k++){
            aux[k] = arr[k];
        }

        // Sort
        int i = lo; int j = mid+1;
        for (int k = lo; k <= hi; k++){
            // When all elements from one of the either halves of the au array have been merged into the original arr,
            // then copy the other sorted half of the au array into the original array, as it is.
            if      (i>mid)                     arr[k] = aux[j++];
            else if (j>hi)                      arr[k] = aux[i++];
            // Normal Path
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
