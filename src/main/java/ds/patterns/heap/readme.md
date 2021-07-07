## Min Heap

[heap-representation](img\heap-representations.png)

MinHeap [ insert(x) at the end ] UsesSwim Operation 
Exchange with the parent and swim up until the heap order is restored
- Insert a new element at the end of the heap and increment the size of the heap
- If the newly inserted element is less than its parent, recursively exchange the element with its parent (swim operation) until the heap order is restored.

MinHeap [ delMin() from the heap] Uses Sink Operation
Exchange with the last element and sink it until heap order is restored. 
- Save off the root of the heap/tree to a min variable
- Exchange the last element of the tree/heap with the root and decrement the size of the heap by 1 element.
- The element which went from bottom to top will most likely violate the heap order, 
  so recursively exchange that node with the *min* of its two children, until the heep order is restored, also called the sink operation.
  Note: Need to compare the 2 children first and then compare the min of the children with the parent. 
```
    Input: 
        int[] nums = new int[]{15,14,13,20,15,10,9,8,7,4,3};
    
    Array to Heap Representation
        i       0   1   2   3   4   5   6   7   8   9   10  11
       a[i]     -   3   4   10  9   7   14  13  20  15  15  8
                    3
                        4   10
                                9    7  14  13 
                                                20  15  15  8
    
    Heap Representation as a Complete Binary Tree  
                                    1(3)
                      2(4)                         3(10)
                4(9)          5(7)           6(14)       7(13) 
            8(20)  9(15)  10(15)  11(8)    

    PreOrder Sequence of elements                    
        1(3) 2(4) 4(9) 8(20) 9(15) 5(7) 10(15) 11(8) 3(10) 6(14) 7(13)

```
Reference: https://algs4.cs.princeton.edu/24pq/MinPQ.java.html

        public class BinaryHeapMin {
        
            private int n;
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
    
        }

## Is Heap Min Ordered?

```
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

    private boolean isMinHeapOrdered(int k) {
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && greater(k, left))  return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

```

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

Reference: https://algs4.cs.princeton.edu/24pq/MaxPQ.java.html

## Other references
https://www.geeksforgeeks.org/why-is-binary-heap-preferred-over-bst-for-priority-queue/
https://www.geeksforgeeks.org/priority-queue-class-in-java-2/

## Priority Queue
Runtime: Add and remove O(logN)

Java PriorityQueue
PriorityQueue<Integer> heap = new PriorityQueue<>(...);

#### Top K Frequent Elements
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Flow of 
1. Setup - Initialize the PQ with a custom compareator
2. Add all data to te PQ. It will order the elements. 
3. We remove the elements as we need them

#### K-Closest Points to Origin

#### Merge K Sorted Lists

#### Find median from Data Stream
Median is the number that is in the middle of an ordered list. If there are two medians, then it is the average value of the two.
2,3,5 -   Median = 3
2,3,4,5 - Median = (3+4)/2 = 3.5

#### Meeting Rooms II
#### Task Scheduler
#### Find K Pairs with Smallest Sum


