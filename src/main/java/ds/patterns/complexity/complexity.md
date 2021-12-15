## Complexity Analysis

### Binary Tree - DFS
Time Complexity : O(N), where N is the number of nodes in the binary tree. In the worst case we might be visiting all the nodes of the binary tree.
Space Complexity : O(N). In the worst case space utilized by the recursion stack would be N, since the height of a skewed binary tree could be N. 
                   Can also be said as O(depth) = O(log N) to keep the recursion stack, where d is a tree depth.

### Binary Tree - BFS
Let N be the total number of nodes in the input tree.
Time Complexity: O(N)
We visit each node once and only once. And at each visit, it takes a constant time to process.
Space Complexity: O(N)
We used a queue to maintain the nodes along with its indices, which is the main memory consumption of the algorithm.
Due to the nature of BFS, at any given moment, the queue holds no more than two levels of nodes. In the worst case,
a level in a full binary tree contains at most half of the total nodes (i.e. N/2​), i.e. this is also the
level where the leaf nodes reside. Hence, the overall space complexity of the algorithm is O(N).
	
### HashMap - Space Complexity 
Storing N strings of max length K in a HashMap 
O(N*K)

### Arrays 
Storing N strings, each in an Array of 26 char length [each array being a charset of 26 chars long]
O(N*A) - Length of A is 26 


## Graph Algorithm

### DFS Traversal
Depth first Search
Time:  O(N) number of nodes in the graph
Data Structures: HashSet / Visited array to detect a cycle

### BFS Traversal
Breadth first
Time:  O(N) number of nodes in the graph
Data Structures: Queue, HashSet / Visited array to detect a cycle

### Union Find
Union together disjoint sets and combine them together in some efficient way.
i.e. Number of connected components in an un-directed graph using Union Find  
Time: O(n log n)
Data Structures: Forest of Trees

### Topological Sort
Using DFS, we are given a DAG, we need to find a 
We can have several valid topological sort
Time:  O(N) number of nodes in the graph
Data Structures: HashSet / Visited array to detect a cycle

### Dijkastra's Algorithm
Used to find the shortest path from one node to every other node in the graph.
Time:  E Log V, 
Data Structures: 
A Heap/Priority Queue. We will be looking at the edge of min weight. 
A HashSet / Visited array to detect a cycle.
i.e. Network delay time.

### Prim's or Kruskal's for Min Spanning Tree
### Prim's or Kruskal's for Min Spanning Tree 
Time: 
Data Structures:


### 
Time: 
Data Structures:

### 
Time: 
Data Structures:

### 
Time: 
Data Structures:

### 
Time: 
Data Structures:

### 
Time: 
Data Structures:

