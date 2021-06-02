package ds.binaryheap;

// GOLD - Has my notes
public class BinaryHeapMax {

    private int[] pq;
    private int N;

    public BinaryHeapMax(int capacity) {
        pq = new int[capacity + 1];
    }

    /**
        int i = 3;
        int a = i++; // a = 3, i = 4
        int b = ++a; // b = 4, a = 4
    */
    public void insert(int x) {
        //++N - because we leave out the 0th element of the array.
        // 1 + log base2 (N) compares
        // Insert a new element/node at the end of the heap.
        pq[++N] = x; // increment and assign
        // check if the inserted element is less than its parent. if yes, exchange it with its parent (swim operation)
        swim(N);
    }

    // Swim the larger key up
    private void swim(int k) {
        // Only if the array size > 1 then we do a swim operation else we ignore because we have just
        // one element in the array. && the value of the current index (k) is greater than than its parent

        // while we are not at the root && k's parent at k/2 is lesser than its parent
        while (k > 1 && less(k/2, k)) {
            // Exchange k with its parent. Exchange the value of the current k with its parent
            exch(k, k/2);
            // Move up. Set the current k to its parent so that we can now check the parent with its grandparent.
            k = k/2;
        }
    }

    // Sink the smaller key down. this we will use to delete the maximum in the heap.
    private void sink(int k) {
        while (2*k <= N) {
            // Figure which of the two children are bigger - 2k (j) or 2k+1 (j+1)
            int j = 2*k;
            if (j < N && less(j, j+1))
                j++;

            // If k is not less than either one of them, then we are done by breaking
            if (!less(k, j))
                break;

            // if one of the childeren are larger, than we exchange it with the larger and move down the heap
            exch(k, j);
            // move down the heap
            k = j;
        }
    }

    // Delete the maximum - size of the heap has to go down by 1 and return the maximum.
    public int delMax() {
        // the one we want to return is at the root to we will save away the value into max to return to the client
        int max = pq[1];
        // Since the heap has to go down by 1, we take the root and exchange it with the last element
        // and decrease the size of the heap by 1 (declare the last element no longer part of the heap)
        exch(1, N--);
        // The element which went from bottom to top will most likely violate the heap order,
        // its going to smaller than its children, so we do a sink by exchanging that node with the larger
        // of the two children
        sink(1);
        // we prevent loitering by nulling out that position
        pq[N + 1] = 0;

        return max;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private int size() {
        return pq.length;
    }

    private boolean less(int i, int j) {
        return pq[i] < pq[j];
    }

    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public static void main(String[] args) {
        BinaryHeapMax heap = new BinaryHeapMax(11);
        heap.loadData2();
    }

    private void loadData2(){
        //int[] nums = new int[]{3,4,8,9,7,10,9,15,20,13};
        int[] nums = new int[]{13,20,15,9,10,7,7,9,8,4,3};
        for (int num: nums){
            insert(num);
        }
        delMax();
        delMax();
    }
    private void loadData() {
        int i = 0;
        while (i < 10) {
            int key = i+5;
            printArraycontent(key);
            printArrayIndices(key);
            if (i > 7) {
                delMax();
                insert(key);
            }else{
                insert(key);
            }

            i++;
        }
    }

    private void printArraycontent(int key) {
        System.out.print(key  +"[");
        for (int i=0; i < pq.length; i++){
            System.out.print(pq[i] + ", ");
        }
        System.out.print("]\n");
    }

    private void printArrayIndices(int key) {
        System.out.print("   [");
        for (int i=0; i < pq.length; i++){
            System.out.print(i + ", ");
        }
        System.out.print("]");
        System.out.println("\n");
    }
}
