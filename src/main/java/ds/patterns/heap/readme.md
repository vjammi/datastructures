# Heaps

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

## Priority Queue Runtime
    Add and Remove - O(logN)
    Build a Sorted Heap - nlog(n)

### Java PriorityQueue Usage

#### Implement your Comparator
```
    class MinIntegerComparator implements Comparator<Integer>{
        public int compare(Integer x, Integer y){
            return x - y;
        }
    }
    class MaxIntegerComparator implements Comparator<Integer>{
        public int compare(Integer x, Integer y){
            return y - x;
        }
    }
```
#### Initialize your Priority Queue with the Comparator
```
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(nums.length+1, new MinIntegerComparator()); // Default - MinIntegerComparator
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(nums.length+1, new MaxIntegerComparator()); // MaxIntegerComparator

    Queue<Integer> minHeap = new PriorityQueue<>(nums.length+1, new MinIntegerComparator()); // Default - MinIntegerComparator
    Queue<Integer> maxHeap = new PriorityQueue<>(nums.length+1, new MaxIntegerComparator()); // MaxIntegerComparator
```
#### Load elements into the Priority Queue
```
    for(int i = 1; i< nums.length; i++){
        minHeap.add(nums[i]);
    }
```
#### Iterate/Poll the elements from the Priority Queue
```
    Iterator<Integer> heapIterator0 = minHeap.iterator();
    while (heapIterator0.hasNext()) {
        System.out.print(heapIterator0.next() + " ");
    }
```

### Top K Frequent Elements
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Solution
1. Setup the PQ with the custom comparator to order the elements based on the highest frequencies [descending order of the frequencies]
```
    class FrequencyComparator implements Comparator<Integer>{
        public int compare(Integer n1, Integer n2) {
            return charToFreqMap.get(n1).compareTo(charToFreqMap.get(n2));  // descending
        }
    }
```
2. Build the HashMap
```
    // build hash map character and its frequency - O(N) time
    charToFreqMap = new HashMap();
    for (int num: nums) {
        if (charToFreqMap.containsKey(num)) charToFreqMap.put(num, charToFreqMap.get(num)+1);
        else charToFreqMap.put(num, 1);
    }
```
3. Add all frequencies from the HaspMap into the PQ.
   PQ will order the elements based on the comparator logic [charToFreqMap.get(n1).compareTo(charToFreqMap.get(n2))]
   Poll the elements from PQ, when the size of the PQ grows beyond k elements[??? can keep all the frequencies and return only the top k elements]
```
   // Init the heap the less frequent element first
   Queue<Integer> heap = new PriorityQueue<>(new FrequencyComparator());
   // keep k top frequent elements in the heap - O(N log k) < O(N log N) time
   for (int key: charToFreqMap.keySet()) {
       heap.add(key);
       if (heap.size() > k) heap.poll();
   }
```
4. Return the top k elements. Poll the elements from the PQ and copy them into an array to return.
```
        // build an output array - O(k log k) time. k = number of items to store in the pq
        int[] topKFrequentElements = new int[k];
        for (int i=k-1; i>=0; i--){
            topKFrequentElements[i] = heap.poll();
        }
        return topKFrequentElements;
```

### 973 K Closest Points to Origin
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
    Input: points = [[1,3],[-2,2]], k = 1
    Output: [[-2,2]]
    Explanation:
    The distance between (1, 3) and the origin is sqrt(10).
    The distance between (-2, 2) and the origin is sqrt(8).
    Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
    We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].

Euclidean distance Calculations
```
    // Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2)
    // Euclidean distance for [1,3]  from origin [0,0] would be sqrt((1-0)^2 + (3-0)^2))
    // Euclidean distance for [-2,2] from origin [0,0] would be sqrt((-2-0)^2 + (2-0)^2))
    public int[][] kClosest(int[][] points, int k) {
        // ...
    }
```
Create a Map of dist to points[][] - O(N)
```
     class Coordinate{
            int x;
            int y;
            Double dist;

            public Coordinate(int x, int y, Double dist){
                this.x = x;
                this.y = y;
                this.dist = dist;
            }
        }
    ...

    Map<Coordinate, Double> map = new HashMap<>();
    for(int i=0; i<points.length; i++){
        int[] coord = points[i];
        double dist = Math.sqrt(((coord[0]-0) *(coord[0]-0)) + ((coord[1]-0)*(coord[1]-0)));
        System.out.println(dist);
        map.put(new Coordinate(coord[0], coord[1], dist), dist);
    }
```
Build a MinDistComparator
```
    class MinDistComparator implements Comparator<Coordinate> {
        public int compare(Coordinate coord1, Coordinate coord2){
            return coord1.dist.compareTo(coord2.dist); // or // return (int) (coord1.dist - coord2.dist);
        }
    }

    class MaxDistComparator implements Comparator<Coordinate> {
        public int compare(Coordinate coord1, Coordinate coord2){
            return coord2.dist.compareTo(coord1.dist);
        }
    }
```
Load the elements of the map in a PQ using MinDistComparator - n + nlog(n)???
```
    // Load the elements of the map in a PQ using minOrder Comparator n + nlog(n)???
    Queue<Coordinate> priorityQueue = new PriorityQueue(new MinDistComparator());
    Set<Map.Entry<Coordinate, Double>> entrySet = map.entrySet();
    for (Map.Entry entry: entrySet){
        Coordinate coord = (Coordinate) entry.getKey();
        Double     dist  = (Double) entry.getValue();
        // if (priorityQueue.size() > k) priorityQueue.poll();
        priorityQueue.add(coord);
    }
```
Return the top k min distance - k log K ???
```
    // Return the top k min distance - k log K ???
    int[][] kClosestPoints = new int[k][2];
    for(int i=0; i<k; i++){
        Coordinate coordinate = priorityQueue.poll();
        int[] coord = new int[2];
        coord[0] = coordinate.x;
        coord[1] = coordinate.y;
        kClosestPoints[i] =  coord;
    }

    return kClosestPoints;
    ```

    public static void main(String[] args) {
        KClosestPointsToOrigin obj = new KClosestPointsToOrigin();
        int k = 2;
        int[][] points = {{3,3},{5,-1},{-2,4}};
        obj.kClosest(points, k);
    }

### Merge K Sorted Lists

### Find median from Data Stream
Median is the number that is in the middle of an ordered list. If there are two medians, then it is the average value of the two.
2,3,5 -   Median = 3
2,3,4,5 - Median = (3+4)/2 = 3.5

### Meeting Rooms II
### Task Scheduler
### Find K Pairs with Smallest Sum


## references
https://algs4.cs.princeton.edu/24pq/MaxPQ.java.html
https://www.geeksforgeeks.org/why-is-binary-heap-preferred-over-bst-for-priority-queue/
https://www.geeksforgeeks.org/priority-queue-class-in-java-2/
