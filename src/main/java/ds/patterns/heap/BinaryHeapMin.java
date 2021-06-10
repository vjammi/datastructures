package ds.patterns.heap;

public class BinaryHeapMin {

    private int[] pq;        // number of items on heap
    private int n;          // store items at indices 1 to n

    public BinaryHeapMin(int capacity) {
        pq = new int[capacity+1];
    }

    /**
         insert(x) - at the end. Exchange with the parent and swim up until the heap order is restored
         Insert a new element at the end of the heap and increment the size of the heap
         If the newly inserted element is less than its parent, recursively exchange the element with its parent (swim operation) until the heap order is restored.
     */
    private void insert(int num) {
        // increment counter and insert to the end of the heap
        pq[++n] = num;

        // Swim until heap order is restored
        swim(n);
    }

    private void swim(int k) {
        while (k > 1 && pq[k] < pq[k/2]) {
            exch(k, k/2);
            k = k/2;
        }
    }

    /**
        delMin() from the heap - Exchange with the last element and sink it until heap order is restored.

        Save off the root of the heap/tree to a min variable
        Exchange the last element of the tree/heap with the root and decrement the size of the heap by 1 element.
        The element which went from bottom to top will most likely violate the heap order, so recursively exchange that node with the min of its two children (sink operation), until the heep order is restored
     */
    private int delMin() {
        int min = pq[1];
        pq[1] = pq[n];
        n--;
        sink(1);
        pq[n + 1] = 0;
        return min;
    }

    private void sink(int k) {
        int minChild = 0;
        int l = 2*k;
        int m = 2*k+1;

        while(l<= n || m<= n){
            if (m <= n)
                minChild = pq[l] < pq[m] ? l : m;
            else if (l <= n)
                minChild = l;

            if (pq[minChild] < pq[k])
                exch(k, minChild);
            else
                break;

            k = minChild;
            l = 2*k;
            m = 2*k+1;
        }
    }

    private void sink_minheap(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void sink_maxheap(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeapOrdered(int k) {
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && greater(k, left))  return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

    private boolean greater(int i, int j) {
        return pq[i] > pq[j];
    }

    private boolean less(int i, int j) {
        return pq[i] < pq[j];
    }

    private void exch(int i, int j) {
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    public int size(){
        return n;
    }

    public boolean isHeapMinOrdered(){
        if (pq.length < 1)
            return false;
        return isMinOrdered(1);
    }

    public boolean isMinOrdered(int k){
        if (k > n) // We have looked at all the elements of the heap and have found them to the ordered, hence return true.
            return true;

        System.out.print(k  +"("+pq[k]+") ");

        isMinOrdered(2*k);
        isMinOrdered(2*k+1);

        boolean leftSideHeapOrdered = false;
        boolean rightSideHeapOrdered = false;

        if ((2*k)+1 < n) {
            if (pq[k] <= pq[2 * k])
                leftSideHeapOrdered = true;
            else
                leftSideHeapOrdered = false;
        }else{
            leftSideHeapOrdered = true;
        }

        if ((2*k)+1 < n){
            if (   pq[k]<= pq[(2*k)+1])
                rightSideHeapOrdered = true;
            else
                rightSideHeapOrdered = false;
        }else{
            rightSideHeapOrdered = true;
        }

        if (leftSideHeapOrdered && rightSideHeapOrdered) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{15,14,13,20,15,10,9,8,7,4,3};
        BinaryHeapMin heap = new BinaryHeapMin(nums.length);
        heap.testMinHeap(nums);
    }

    private void testMinHeap(int[] nums){
        for (int num: nums){
            insert(num);
            printArray(num);
        }
        //System.out.println("isMinHeapOrdered: "+isMinHeapOrdered(2));
        //delMin(); printArray(pq[1]);
        //System.out.println("isMinHeapOrdered: "+isMinHeapOrdered(2));
        //delMin(); printArray(pq[1]);

        //pq[1] = 9;
        //printArray(9);
        System.out.println("isHeapMinOrdered: " +isHeapMinOrdered());
        System.out.println("isMinHeapOrdered: "+isMinHeapOrdered(1));
    }

    private void printArray(int num) {
        System.out.print(num +" [");
        for (int i=0; i < pq.length; i++){
            System.out.print(pq[i] + ", ");
        }
        System.out.print("]\n");
    }
}

