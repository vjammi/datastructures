## Sorting Algorithms

### Selection Sort
We will move the  index i from left to right. In the ith iteration, we look for the smallest remaining entry in the array, to the right of i.
When we find the index of smallest entry [min] to the right of i, we then swap that with the ith entry in the array
swap a[i] and a[min]. We will increment i, until i reaches the end of the array.

Selection Sort Implementation
```
    public void selectionSort(){
       for(int i=0; i<a.length; i++){
         // Start with i as min index, then look for min to the right of i in the array
         int min = i;
           // In the ith iteration we look for the smallest remaining entry
           // to the right of i [starting j=i+1]
           for(int j=i+1; j < a.length; j++){
               if ( a[j] < a[min]){
                   min = j;
               }
           }
         // Swap the smallest element to the right of i, with the ith element
        exch(i, min);
        // Note the last element swaps it with itself at the end.
       }
    }
```

### Insertion Sort
We will move the  index i from left to right as before but now in the ith iteration we will move a[i] to the left of array to put it in its right position. Now we start by initializing i at the first card. The idea is everything from i to its left is sorted and everything to the right we have not looked yet.
In the below case when i is the 3rd entry in the array, now we start an index j [at i], moving from right to left, we exchange the value at a[j] with each larger element to the left of j.
Starting j at i

if a[j-1] > a[j] , exchange a[j] with a[j-1] and decrement j

Continue exchanging a[j] with a[j-1], as long as a[j-1] > a[j],

Insertion Sort Implementation
```
    // int[] arr = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
    // Sort in ascending order
    // Move index i from left to right. In the ith iteration, swap a[i] with each larger entry to its left
    // Assumption is everything to the left of i is in ascending sorted order and everything to the right we have not seen yet.
    private void sort(int[] arr) {
        int n = arr.length;
        for (int i=0; i < n ; i++){
            printArray(arr);
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
```
## Merge Sort
The idea of merge sort is based on the idea of merging.  to understand how merging works, we can think of an abstract inplace merge. So we have an array a[], whose first half is sorted and its second half is sorted and the computation we need to perform is to merge those two sorted sub halves, sorting the two subarrays into one.
So given two sorted subarrays a[lo] to a[mid] and a[mid+1] to a[hi], we will replace the two sorted subarrays with one sorted subarray from a[lo] to a[hi]

Merge Design
The method we will use is based on using an auxiliary array to hold the data. First thing we will do is to copy everything to the auxiliary array. Once that is done we will copy back to the original array in sorted order.
To do this we will have to maintain 3 indices
i - entry in the left half of the aux array
j - entry in the right half of the aux array
k - entry in the sorted results.

We will compare the min values between i and j and copy the smallest into the original array. In whichever half is the element taken, we increment the pointer in that half. We also increment the value of k. So we either increment i and k or j and k. Once one of the sub-arrays are exhausted, we then take the remaining elements from the other sub half and move them into the original array. Note that the Merge Sort does the recursion before it does the work.

Merge Sort Implementation
```
    public static void sort(int[] arr){
        int low = 0; int high = arr.length-1;
        int[] aux = new int[arr.length];
        sort(arr, aux, low, high);
    }

    private static void sort(int[] arr, int[] aux, int lo, int hi){
        if (hi <= lo)
            return;                      // checks if there is something to do first
        int mid = lo + (hi-lo) / 2;      // computes the mid point
        sort(arr, aux, lo, mid);         // sort the left/first half
        sort(arr, aux, mid+1, hi);       // sort the right/second half
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
            // When all elements from one of the either halves of the array have been merged into the original arr, then copy
            // the remainder sorted half of the other half of the array into the original array.
            if      (i>mid)                     arr[k] = aux[j++];
            else if (j>hi)                      arr[k] = aux[i++];
            // Normal Path
            else if (aux[j]<aux[i])             arr[k] = aux[j++];
            else                                arr[k] = aux[i++];
        }
    }
```

## Heap Sort
We can use any priority queue to develop a sorting method. We insert all the keys to be sorted into a minimum-oriented priority queue,
then repeatedly use remove the minimum to remove them all in order. When using a heap for the priority queue, we obtain heapsort.
To sort, we use swim() and sink() functions. Doing so allows us to sort an array without needing any extra space, by maintaining the
heap within the array to be sorted.

Heapsort breaks into two phases:
1. Heap construction - Within the heap construction phase, we reorganize the original array into a heap.
We can accomplish this task in time proportional to n lg n,by proceeding from left to right through the array, using swim() to ensure that the entries to the left of the scanning pointer make up a heap-ordered complete tree, like successive priority queue insertions. A clever method that is much more efficient is to proceed from right to left, using sink() to make subheaps as we go. Every position in the array is the root of a small subheap; sink() works or such subheaps, as well. If the two children of a node are heaps, then calling sink() on that node makes the subtree rooted there a heap.
2. Sortdown: Within the sortdown phase we pull the items out of the heap in decreasing order to build the sorted result.
Most of the work during heapsort is done during the second phase, where we remove the largest remaining items from the heap and put it into the array position vacated as the heap shrinks.

##### Heap Sort Implementation
```
    // Rearranges the array in ascending order, using the natural order.
    public static void sort(Comparable[] pq) {
        int n = pq.length;

        // heapify phase
        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);

        // sortdown phase
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
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

    public static void main(String[] args) {
        String[] a = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
        Heap.sort(a);
    }
```