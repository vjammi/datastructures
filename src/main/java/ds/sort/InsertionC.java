package ds.sort;

import java.util.Comparator;

public class InsertionC {

    private static void sort(Comparable[] a) {
        int N = a.length;
        show(a);

        // Move index i from left to right
        // In the ith iteration, we swap a[i] with each larger entry to its left
        // assumption is everything to the left of i is in ascending sorted order. to the right we have not seen yet.
        for (int i=0; i < N ; i++){
            // we increment i
            for (int j=i; j>0 ; j--){
                if (less(a[j-1], a[j])){
                    exch(a,j-1, j);
                }else{
                    break;
                }
            }
            //show(a);
        }
        show(a);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) > 0; //a[j-1] > a[j];
    }

    private static void exch(Object[] a, int k, int j) {
        Object swap = a[k];
        a[k] = a[j];
        a[j] = swap;
    }


    private static boolean less(Object v, Object w, Comparator comprator) {
        return comprator.compare(v, w) > 0; //a[j-1] > a[j];
    }


    private static void show(Comparable[] a) {
        System.out.println();
        for (int k=0; k<a.length; k++){
            System.out.print(a[k]+" ");
        }
    }

    public static void main(String[] args){
        Integer[] ia = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
        InsertionC.sort(ia);

        String[] sa = {"S","O","R","T","E","X","A","M","P","L","E"};
        InsertionC.sort(sa);

    }
}
