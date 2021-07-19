package ds.sort;

public class InsertionSort {

    // Sort in ascending order
    // Move index i from left to right. In the ith iteration, swap a[i] with each larger entry to its left
    // Assumption is everything to the left of i is in ascending sorted order and everything to the right we have not seen yet.
    private void sort(int[] arr) {
        int n = arr.length;
        for (int i=0; i < n ; i++){
            printArray();
            for (int j=i; j>0 ; j--){ // Note j > 0 because we compare j and j-1
                if (arr[j-1] > arr[j])
                    exch(arr,j-1, j);
                else
                    break; // Optimization
            }
        }
    }

    private void exch(int[] a, int k, int j) {
        int swap = a[k];
        a[k] = a[j];
        a[j] = swap;
    }

    private void printArray() {
        System.out.println();
        for (int k=0; k<a.length; k++){
            System.out.print(a[k]+", ");
        }
    }
    public static void main(String[] args){
        InsertionSort obj = new InsertionSort();
        int[] arr = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
        obj.sort(arr);
    }
}
