package ds.binaryheap;

public class BinaryHeapMin {

    private int N;
    private int[] pq;

    public BinaryHeapMin(int capacity) {
        pq = new int[capacity+1];
    }

    public int size(){
        return N;
    }

    private int delMin() {
        int max = pq[1];
        pq[1] = pq[N];
        N--;
        sink(1);
        pq[N + 1] = 0;
        return max;
    }

    private void sink(int k) {
        int minChild = 0;
        int l = 2*k;
        int m = 2*k+1;

        while(l<=N || m<=N){
            if (m <= N)
                minChild = pq[l] < pq[m] ? l : m;
            else if (l <= N)
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

    private void insert(int num) {
        // increment counter and insert to the end of the heap
        pq[++N] = num;

        // Swim until heap order is restored
        swim(N);
    }

    private void swim(int k) {
        while (k > 1 && pq[k] < pq[k/2]) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void exch(int i, int j) {
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    public static void main(String[] args) {
        BinaryHeapMin heap = new BinaryHeapMin(9);
        heap.loadData();
    }

    private void loadData(){
        int[] nums = new int[]{13,20,15,10,9,8,7,4,3};
        for (int num: nums){
            insert(num);
            printArray(num);
        }
        delMin(); printArray(pq[1]);
        delMin(); printArray(pq[1]);
        delMin(); printArray(pq[1]);
        delMin(); printArray(pq[1]);
        delMin(); printArray(pq[1]);
        delMin(); printArray(pq[1]);
    }

    private void printArray(int num) {
        System.out.print(num +" [");
        for (int i=0; i < pq.length; i++){
            System.out.print(pq[i] + ", ");
        }
        System.out.print("]\n");
    }
}
