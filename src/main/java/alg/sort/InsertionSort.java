package alg.sort;

public class InsertionSort {

    // Sort in ascending order
    // Move index i from left to right. In the ith iteration, swap a[i] with each larger entry to its left
    // Assumption is everything to the left of i is in ascending sorted order and everything to the right we have not seen yet.
    private void sort(int[] arr) {
        int n = arr.length;
        for (int i=1; i<n; i++){
            for (int j=i; j>0 ; j--){ // Note *** j > 0 because we compare j and j-1
                if (arr[j] < arr[j-1])
                    exch(arr,j, j-1);
                else
                    break; // Optimization
            }
            print(arr);
        }
    }

    private void exch(int[] a, int j, int k) {
        int swap = a[k];
        a[k] = a[j];
        a[j] = swap;
    }

    private void print(int[] arr) {
        for (int k=0; k<arr.length; k++){
            System.out.print(arr[k]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        InsertionSort obj = new InsertionSort();
        int[] arr = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
        obj.sort(arr);
    }
}
