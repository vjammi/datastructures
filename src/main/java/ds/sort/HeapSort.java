package ds.sort;

public class HeapSort {

    // Rearranges the array in ascending order, using the natural order.
    public static void sort(Comparable[] pq) {
        int n = pq.length;

        System.out.println("heapify phase - heap construction phase. reorganize the original array into a heap.");
        // heapify phase - heap construction phase. reorganize the original array into a heap.
        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);
        print(pq);

        // sortdown phase - we pull the items out of the heap in decreasing order to build the sorted result
        System.out.println("sortdown phase - we pull the items out of the heap in decreasing order to build the sorted result");
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
        System.out.println("Final PQ ");
        print(pq);
    }

    private static void sink(Comparable[] pq, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    // Indices are "off-by-one" to support 1-based indexing.
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }
    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    private static void print(Comparable[] arr){
        for ( Object item: arr){
            System.out.print(item +" ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Integer[] a = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
        HeapSort.sort(a);
    }

}
