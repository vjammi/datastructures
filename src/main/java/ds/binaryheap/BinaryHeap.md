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