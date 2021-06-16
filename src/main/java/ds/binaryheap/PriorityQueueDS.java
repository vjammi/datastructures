package ds.binaryheap;

/**
 * Created by Vijay Jammi on 06/13/2018.
 *
 // put(Key k)
 // delMin()
 // isEmpty
 // min
 // size
 */
public class PriorityQueue {
    int[] a;
    int N = 0;

    public PriorityQueue(int capacity){
        a = new int[capacity];
    }

    private void insert(int val) {
        a[N] = val;
        N++;
        System.out.println("Val: "+val);
    }

    private int size() {
        return a.length;
    }

    private void delMin() {
        a[N] = 0;
        N--;
        System.out.println("Deleting Min : "+a[N]);
    }

    public static void main (String[] args){
        PriorityQueue pq = new PriorityQueue(500);
        int M = 20;
        int i = 0;
        while(i < 100){
            System.out.println("Inserting element #: " + i +": ");
            pq.insert(i+500);
            if (pq.size() > M ){
                pq.delMin();
            }
            i++;
        }

    }

}
