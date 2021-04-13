package ds.sort;

public class QuickSort {

    public int partition2(int[] a, int lo, int hi){

        int ii = lo, jj = hi+1;

        while(true){

            for (int i = lo+1; i <= hi; i++){
                if ( a[i] > a[lo] ){
                    ii = i;
                    System.out.println("Breaking: " + i +" - "+ a[i] +" ");
                    break;
                }
            }

            for (int j = hi; j >= lo; j--){
                if ( a[j] < a[lo] ){
                    jj = j;
                    System.out.println("Breaking: " + j +" - "+ a[j] +" ");
                    break;
                }
                System.out.print(j +"-"+ a[j] +", ");
            }
            if (ii >= jj) break;
            exch(a, ii, jj);
            printArray(a, lo, hi, " ");
        }

        exch(a, lo, jj);
        return jj;
    }

    private int partition(int[] a, int lo, int hi) {

        int i = lo, j = hi+1;
        while (true) { // while (i>>> lo  <<<<j) is true

            while (a[++i] < a[lo])  // Enter/continue incrementing, while the next ith element is lesser than low,
                if (i == hi) break; // break if you reach the end

            while (a[lo] < a[--j])  // Enter/continue decrementing, while the next jth element is greater than low,
                if (j == lo) break; // break if you reach the low

            if (i >= j)             // When pointers cross, break
                break;

            exch(a, i, j);          // Exchange the ith and jth element, when they are out of place.
        }
        //System.out.println(">>>> lo: " +lo +" j: " + j);
        //printArray(a, lo, hi, "j="+j);
        exch(a, lo, j); // When i and j (i>=j) pointers cross exchange j and lo
        return j;
    }

    private void sort(int[] a, int lo, int hi)  {
        if (hi <= lo)
            return;

        //printArray(a, lo, hi, "P ");
        int j = partition(a, lo, hi);
        System.out.println("j: " + j +" a[j] " +a[j]);


        sort(a, lo, j-1);
        //printArray(a, j+1, hi, "< ");

        sort(a, j+1, hi);
        //printArray(a, j+1, hi, "> ");
    }

    public void sort(int[] a) {
        //StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }


    private void printArray(int[] a, int lo, int hi, String note) {
        System.out.print(note + " ");
        for (int k=lo; k<=hi; k++){
            System.out.print(a[k]+", ");
        }
        System.out.println();
    }

    private void exch(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args){
        // 1    2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25
        // A    B   C   D   E   F   G   H   I   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z

                // K   R   A  T   E  L   E  P   U   I   M   Q   C   X   O   S
        int[] a = {10, 17, 1, 19, 5, 11, 5, 15, 20, 8,  12, 16, 3,  23, 14, 18};

        QuickSort sort = new QuickSort();
        sort.printArray(a, 0, a.length-1, "Start ");
        sort.sort(a);
        sort.printArray(a, 0, a.length-1, "End ");
    }
}