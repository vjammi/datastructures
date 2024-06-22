package io.dev.v2.sort;

public class InsertionSortComparable {

    private static void sort(Comparable[] a) {
        // Move index i from left to right
        // In the ith iteration, we swap a[i] with each larger entry to its left
        // assumption is everything to the left of i is in ascending sorted order. to the right we have not seen yet.
        int N = a.length;
        for (int i=0; i < N ; i++){
            // we increment i
            for (int j=i; j>0 ; j--){
                //if (greater(a[j], a[j-1])){ // Descending
                if (less(a[j], a[j-1])){    // Ascending
                    exch(a, j,j-1);
                }else{
                    break;
                }
            }
            show(a);
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    private static void exch(Object[] a, int j, int k) {
        Object swap = a[j];
        a[j] = a[k];
        a[k] = swap;
    }

    private static void show(Comparable[] a) {
        for (int k=0; k<a.length; k++){
            System.out.print(a[k]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        Integer[] ia = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
        InsertionSortComparable.sort(ia);

        String[] sa = {"S","O","R","T","E","X","A","M","P","L","E"};
        InsertionSortComparable.sort(sa);

    }
}
