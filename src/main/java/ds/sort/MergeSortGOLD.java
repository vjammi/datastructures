package ds.sort;

public class MergeSortGOLD {

    public void sort(int[] a){
        int low = 0;
        int high = a.length-1;

        int[] aux = new int[a.length];
        sort(a, aux, low, high);
    }

    private void sort(int[] a, int[] aux, int lo, int hi){
        if (hi <= lo) return;          // checks if there is something to do first

        int mid = lo + (hi-lo) / 2;    // computes the mid point
        sort(a, aux, lo, mid);         // sort the first half
        sort(a, aux, mid+1, hi);    // sort the second half
        merge(a, aux, lo, mid, hi);    // merge them together
    }

    public void merge (int[] a, int[] aux, int lo, int mid, int hi){
        // Copy in aux
        for (int k = lo; k<=hi; k++){
            aux[k] = a[k];
        }

        // Sort
        int i = lo; int j = mid+1;
        for (int k = lo; k <= hi; k++){
            if ( i > mid){
                a[k] = aux[j++];
            }else if( j > hi){
                a[k] = aux[i++];
            }

            else if (aux[j] < aux[i]){
                a[k] = aux[j++];
            }else{
                a[k] = aux[i++];
            }
            printArray(a, k);
        }
    }

    private void testMergeSort() {
        int[] a = {10, 17, 1, 19, 5, 11, 5, 15, 20, 8,  12, 16, 3,  23, 14, 18};
        sort(a);
    }

    public static void main (String[] args){
        MergeSortGOLD mergeSort = new MergeSortGOLD();
        mergeSort.testMergeSort();
    }

    private void printArray(int[] a, int index) {
        System.out.println();
        System.out.print(index +" - ");
        for (int k=0; k<a.length; k++){
            System.out.print(a[k]+", ");
        }
    }

}
