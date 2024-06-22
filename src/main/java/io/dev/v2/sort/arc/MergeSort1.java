package io.dev.v2.sort.arc;

// GOLD :)
public class MergeSort1 {

    public static void main(String[] args){
        MergeSort1 sort = new MergeSort1();

        int[] a = {10, 17, 1, 19, 5, 11, 5, 15, 20, 8,  12, 16, 3,  23, 14, 18, 25};
        sort.printArray(a, 0, a.length-1, "Start ");
        sort.sort(a);
        sort.printArray(a, 0, a.length-1, "End ");
    }

    private void printArray(int[] a, int lo, int hi, String comment) {
        System.out.print(comment + " ");
        for (int k=lo; k<=hi; k++){
            System.out.print(a[k]+", ");
        }
        System.out.println();
    }

    private void sort(int[] a) {
        int[] aux = new int[a.length];
        int lo = 0;
        int hi = a.length-1;

        sort(a, aux, lo, hi);
    }

    private void sort(int[] a, int[] aux, int lo, int hi) {

        if (hi <= lo )
            return;

        int mid = lo + (hi-lo)/2;


        printArray(a, mid+1, hi, "> ");
        sort(a, aux, mid+1, hi);

        printArray(a, lo, mid, "< ");
        sort(a, aux, lo, mid);

        printArray(a, lo, hi, "+ ");
        merge(a, aux, lo, mid, hi);
        printArray(a, lo, hi, "+ ");
    }

    private void merge(int[] a, int[] aux, int lo, int mid, int hi) {

        // copy to aux
        for (int k=lo; k<=hi; k++){
            aux[k] = a[k];
        }

        // merge
        int i = lo, j = mid+1;
        for (int k = lo; k<= hi; k++) {

            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (aux[j] < aux[i])
                a[k] = aux[j++];
            else
                a[k] = aux[i++];

        }
    }
}

/*
sort(a, aux, lo, mid);
sort(a, aux, mid+1, hi);

Start  10, 17, 1, 19, 5, 11, 5, 15, 20, 8, 12, 16, 3, 23, 14, 18, 25,
<  10, 17, 1, 19, 5, 11, 5, 15, 20,
<  10, 17, 1, 19, 5,
<  10, 17, 1,
<  10, 17,
<  10,
>  17,
+  10, 17,
+  10, 17,
>  1,
+  10, 17, 1,
+  1, 10, 17,
>  19, 5,
<  19,
>  5,
+  19, 5,
+  5, 19,
+  1, 10, 17, 5, 19,
+  1, 5, 10, 17, 19,
>  11, 5, 15, 20,
<  11, 5,
<  11,
>  5,
+  11, 5,
+  5, 11,
>  15, 20,
<  15,
>  20,
+  15, 20,
+  15, 20,
+  5, 11, 15, 20,
+  5, 11, 15, 20,
+  1, 5, 10, 17, 19, 5, 11, 15, 20,
+  1, 5, 5, 10, 11, 15, 17, 19, 20,
>  8, 12, 16, 3, 23, 14, 18, 25,
<  8, 12, 16, 3,
<  8, 12,
<  8,
>  12,
+  8, 12,
+  8, 12,
>  16, 3,
<  16,
>  3,
+  16, 3,
+  3, 16,
+  8, 12, 3, 16,
+  3, 8, 12, 16,
>  23, 14, 18, 25,
<  23, 14,
<  23,
>  14,
+  23, 14,
+  14, 23,
>  18, 25,
<  18,
>  25,
+  18, 25,
+  18, 25,
+  14, 23, 18, 25,
+  14, 18, 23, 25,
+  3, 8, 12, 16, 14, 18, 23, 25,
+  3, 8, 12, 14, 16, 18, 23, 25,
+  1, 5, 5, 10, 11, 15, 17, 19, 20, 3, 8, 12, 14, 16, 18, 23, 25,
+  1, 3, 5, 5, 8, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20, 23, 25,
End  1, 3, 5, 5, 8, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20, 23, 25,


sort(a, aux, mid+1, hi);
sort(a, aux, lo, mid);

Start  10, 17, 1, 19, 5, 11, 5, 15, 20, 8, 12, 16, 3, 23, 14, 18, 25,
>  8, 12, 16, 3, 23, 14, 18, 25,
>  23, 14, 18, 25,
>  18, 25,
>  25,
<  18,
+  18, 25,
+  18, 25,
<  23, 14,
>  14,
<  23,
+  23, 14,
+  14, 23,
+  14, 23, 18, 25,
+  14, 18, 23, 25,
<  8, 12, 16, 3,
>  16, 3,
>  3,
<  16,
+  16, 3,
+  3, 16,
<  8, 12,
>  12,
<  8,
+  8, 12,
+  8, 12,
+  8, 12, 3, 16,
+  3, 8, 12, 16,
+  3, 8, 12, 16, 14, 18, 23, 25,
+  3, 8, 12, 14, 16, 18, 23, 25,
<  10, 17, 1, 19, 5, 11, 5, 15, 20,
>  11, 5, 15, 20,
>  15, 20,
>  20,
<  15,
+  15, 20,
+  15, 20,
<  11, 5,
>  5,
<  11,
+  11, 5,
+  5, 11,
+  5, 11, 15, 20,
+  5, 11, 15, 20,
<  10, 17, 1, 19, 5,
>  19, 5,
>  5,
<  19,
+  19, 5,
+  5, 19,
<  10, 17, 1,
>  1,
<  10, 17,
>  17,
<  10,
+  10, 17,
+  10, 17,
+  10, 17, 1,
+  1, 10, 17,
+  1, 10, 17, 5, 19,
+  1, 5, 10, 17, 19,
+  1, 5, 10, 17, 19, 5, 11, 15, 20,
+  1, 5, 5, 10, 11, 15, 17, 19, 20,
+  1, 5, 5, 10, 11, 15, 17, 19, 20, 3, 8, 12, 14, 16, 18, 23, 25,
+  1, 3, 5, 5, 8, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20, 23, 25,
End  1, 3, 5, 5, 8, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20, 23, 25,

*/

/* Practice
  public static void sort(int[] arr){
    int[] aux = new int[arr.length];
    int lo = 0;
    int hi = arr.length-1;
    sort(arr, aux, lo, hi);
  }


  public static void sort(int[] arr, int[] aux, int lo, int hi){
      if (hi <= lo)
        return;

      int mid = lo + (hi-lo)/2;
      sort(arr, aux, lo, mid);
      sort(arr, aux, mid+1, hi);
      merge(arr, aux, lo, mid, hi);
      show(arr);
  }


  private static void merge(int[] arr, int[] aux, int lo, int mid, int hi){
    for(int i=0; i<arr.length; i++){
      aux[i] = arr[i];
    }


    int i=lo; int j=mid+1;
    for (int k=lo; k<arr.length; k++){
      if(i>mid)
        arr[k] = aux[j++];
      else if (j>hi)
        arr[k] = aux[i++];
      else if (aux[j] < aux[i])
        arr[k] = aux[j++];
      else
        arr[k] = aux[i++];
    }
  }

  public static void show(int[] arr){
    for(int a: arr)
      System.out.print(a +" ");
    System.out.println();
  }

  public static void main (String[] args){
      int[] arr = {10, 17, 2, 19, 6, 11, 5, 15, 20, 8,  12, 16, 3,  23, 14, 18};
      Solution.sort(arr);
  }
 */