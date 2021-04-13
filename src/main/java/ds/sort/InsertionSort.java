package ds.sort;

public class InsertionSort {

    int[] a = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};

    private void sort() {
        int N = a.length;
        printArray();
        // Move index i from left to right
        // In the ith iteration, we swap a[i] with each larger entry to its left
        // assumption is everything to the left of i is in ascending sorted order. to the right we have not seen yet.

        for (int i=0; i < N ; i++){
            // we increment i
            for (int j=i; j>0 ; j--){
                //System.out.print(a[j] +", ");
                if (a[j-1] > a[j]){
                    exch(a,j-1, j);
                }else{
                    break;
                }
            }
            printArray();
        }
        printArray();
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

        InsertionSort insSort = new InsertionSort();
        insSort.sort();
    }
}
