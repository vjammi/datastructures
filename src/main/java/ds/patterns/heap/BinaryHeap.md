## Min Heap

MinHeap [ insert(x) at the end ]
Exchange with the parent and swim up until the heap order is restored
- Insert a new element at the end of the heap and increment the size of the heap
- If the newly inserted element is less than its parent, recursively exchange the element with its parent (swim operation) until the heap order is restored.

MinHeap [ delMin() from the heap]
Exchange with the last element and sink it until heap order is restored. 
- Save off the root of the heap/tree to a min variable
- Exchange the last element of the tree/heap with the root and decrement the size of the heap by 1 element.
- The element which went from bottom to top will most likely violate the heap order, so recursively exchange that node with the *min* of its two children (sink operation), until the heep order is restored


    public class BinaryHeapMin {
        private int N;
        private int[] pq;
    
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
    
        /**
            delMin() from the heap - Exchange with the last element and sink it until heap order is restored.
    
            Save off the root of the heap/tree to a min variable
            Exchange the last element of the tree/heap with the root and decrement the size of the heap by 1 element.
            The element which went from bottom to top will most likely violate the heap order, so recursively exchange that node with the min of its two children (sink operation), until the heep order is restored
         */
        private int delMin() {
            int min = pq[1];
            pq[1] = pq[N];
            N--;
            sink(1);
            pq[N + 1] = 0;
            return min;
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
    
        private void exch(int i, int j) {
            int tmp = pq[i];
            pq[i] = pq[j];
            pq[j] = tmp;
        }
    
        public int size(){
            return N;
        }
    }



## Max Heap

MaxHeap [ insert(x) at the end ]
Exchange with the parent and swim up until the heap order is restored
- Insert a new element at the end of the heap and increment the size of the heap
- If the newly inserted element is greater than its parent, exchange the element with its parent (swim operation) 
- Recursively repeat, until the heap order is restored.

MaxHeap [ delMax() from the heap]
Exchange with the last element and sink it until heap order is restored. 
- Save off the root of the heap/tree to a min variable
- Exchange the last element of the tree/heap with the root and decrement the size of the heap by 1 element.
- The element which went from bottom to top will most likely violate the heap order. 
- Exchange that node with the *max* element of its two children (sink operation), 
- Recursively repeat until the heep order is restored.

## Other resources
https://www.geeksforgeeks.org/why-is-binary-heap-preferred-over-bst-for-priority-queue/
https://www.geeksforgeeks.org/priority-queue-class-in-java-2/