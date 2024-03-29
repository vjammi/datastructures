# Graph
- A graph is a collection of vertices and edges. Unlike a Tree which uses a Node class, in a graph we usually care about the adjacent nodes.
- The Relationship between the edges. Neighbors of a listNode. We can usually represent as a graph with an adjacency list or adjacency matrix.
```
              3
             / 
            2
           /  \
          1     4
```
## Types of Graph Problems
1. Directed & Undirected edges
2. Connected Components
3. Cycle Detection
4. Topological Sort
   - Class Pre-Requisites
   - Program Dependencies
   - Build an operator graph [directed acyclic graph] and split the graph into stages for task scheduling in
5. Graph Coloring
     Bi-bipartite Graph
6. InDegrees and OutDegrees



### Properties of a Graph
- Relationship between Vertices & Edges
  A graph can have anywhere between 0 edges to a max of V*(V-1) edges
- Directed & Undirected edges
- Walk, Path, Trail in a Graph
- Strongly Connected and Disconnected Graphs
  Directed/Strongly Connected Graph Any vertex to any other vertex
- Cycle in a Graph
  Directed Acyclic Graph [DAG]:
  UnDirected Acyclic Graph

REFERENCES
https://youtu.be/AfYqN3fGapc
https://youtu.be/ZdY1Fp9dKzs


#### Adjacency list
An Adjacency List is usually a Hashmap, or a List of Lists
```   
        Key Value
        1   [2]
        2   [1,3,4]
        3   [2]
        4   [2]
```

#### Adjacency Matrix Representation
```
    // Array of linked lists - adjacency list representation
    private LinkedList<Integer>[] adj;

    // Function to initialize the adjacency list
    private void initialize(int v) {
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    // Function to add an edge into the graph
    public void addEdge(int v, int w)    {
        adj[v].add(w); // Add an edge w to the linked list adj[v]
    }

           /  1 - 4 - 7  \
         /
         0  - 2 - 5 - 8 - 10
         \
           \  3 - 6 - 9 /

        0 1 4 7 10 2 5 8 3 6 9

    public void addEdges(){
        ...
        // Populate the E Edges - O(E)
        g.addEdge(0, 1);  g.addEdge(0, 2);  g.addEdge(0, 3);
        g.addEdge(1, 4);  g.addEdge(2, 5);  g.addEdge(3, 6);
        g.addEdge(4, 7);  g.addEdge(5, 8);  g.addEdge(6, 9);
        g.addEdge(7, 10); g.addEdge(8, 10); g.addEdge(9, 10);
        ...
    }
```
#### A Sparse Graph - Represented as an Adjacency List
```
public class Graph {
    Map<Integer, List<Integer>> adjList;

    private ArrayList<Integer> traverse(int vertices, int[][] edges) {
        adjList = new HashMap();
        
        for (int i=0; i<vertices; i++){
            adjList.put(i, new LinkedList());
        }
        for (int i=0; i<edges.length; i++){
            int[] edge = edges[i];
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        
        int[] visited = new int[vertices];
        ArrayList<Integer> path = new ArrayList<>();

        dfs(0, adjList, visited, path); // DFS connected graph
        
        return path;
    }

    private void dfs(int vertex, Map<Integer, List<Integer>> adjList, int[] visited, List<Integer> path) {
        if (visited[vertex] == 1)
            return;

        path.add(vertex); System.out.print(vertex +" ");

        visited[vertex] = 1;
        List<Integer>  neighbors = adjList.get(vertex);
        for(int neighbor: neighbors){
            if (visited[neighbor] == 0){
                dfs(neighbor, adjList, visited, path);
            }
        }
    }
        /**
              /  1 -  3 \
            0       /    5
              \  2 -  4 /
        */
    public static void main(String[] args) {
        int vertices = 6;
        int[][] edges = {{0,1}, {0,2}, {1,3}, {2,3}, {2,4}, {4,5}, {3,5}};
        Graph graph = new Graph();
        graph.traverse(vertices, edges);
    }
}
```

#### A Dense Graph - Represented as an Adjacency Matrix
We mark all the spots where 2 nodes are connected and populate the matrix with all the edges. 
It is east to lookup all the nods neighbors.
```
        0   1   2   3
    0       T
    1   T       T   T
    2       T
    3       T
```
#### Adjacency Matrix Representation
```
    // Initialize the matrix
    public GraphAdjacencyMatrix(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];
    }

    // Add edges
    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;
    }

    // Remove edges
    public void removeEdge(int i, int j) {
        adjMatrix[i][j] = false;
        adjMatrix[j][i] = false;
    }
```

#### A big chunk of work within the graph problem is
- Setting up the  Adjacency List or the Matrix.
- Once you do that, you iterate thru the graph using DFS or BFS, and keep track of everything you have seen using a visited array.

#### Solving Graph Problems
- First we will draw all the nodes as a graph from 0 to N-1, so that we visually see what we are working with.
- Then we can go thru the edges from the input and fill in the graph.

#### Implementing Graph Problems
- We setup an adjacency list or a matrix  so that we can easily lookup each nodes neighbors.
- We will get the edges in as a list of pairs. You want to go thru each of the edges and list which nodes are adjacent in the adjacency list.
- We can use a hashmap, or a list of lists for the adjacency list.
- We will set the key as the nodes name and the values a list of its edges.
- We initially use a for-loop to initialize all these lists to be empty
- We then we iterate thru the input pairs and populate the adjacency list with edges.
- Since the graph is undirected, we need to add each edge twice, since a is b's neighbors. Then b is a's neighbor too.
- After this we need to iterate thru each listNode and explore all its neighbors.
- As we encounter nodes we use this boolean visited array to make the nodes that we have seen.
- As we finish exploring all the connected components, we increment by 1.
- Visited array prevents us from double counting a connected components and
- the for loop prevents from missing one of the connected component.
- Within the DFS, you mark the current listNode as visited and then you get the list of neighbors from the adjacency list.
- We DFS into the one we have not seen.
- Once all of them are visited. the DFS calls will automatically stop and then we are done.
- Calling DFS in a graph is no different from trees. It involves some setup with adjacency list and a visited array.

#### Complexity Analysis of Depth First Search
Time Complexity
- The time complexity of DFS if the entire tree is traversed is O(V) where V is the number of nodes.
- The time complexity of DFS actually depends on the data structure being used to represent the graph.

##### Graph representation as adjacency list
- Each listNode maintains a list of all its adjacent edges within a list.
- If we assume that there are ```V number of nodes``` and ```E number of edges``` in the graph.
- For each listNode, we discover all its neighbors by traversing its adjacency list just once in linear time.
- Directed graph - For a directed graph, the sum of the sizes of the adjacency lists of all the nodes is E. So, the time complexity in this case is ```O(V) + O(E) = O(V + E)```
- Undirected graph - For an undirected graph, each edge appears twice. Once in the adjacency list of either end of the edge. The time complexity for this case will be ```O(V) + O (2E) ~ O(V + E)```.

##### Graph representation as an adjacency matrix (v x v matrix)
- Each listNode maintains a list of all its adjacent edges within an array.
- Each row in the adjacency matrix corresponds to a listNode in the graph and that row stores information about the edges emerging from the listNode.
- We traverse the entire row of length V in the matrix to discover all its outgoing edges.
- Hence, the time complexity of DFS in this case is ```O(V * V) = O(V^2)```.

Space Complexity
- Since we are maintaining a stack to keep track of the last visited listNode in worst case, the stack could take upto the size of the nodes (or vertices) in the graph.
- Hence, the space complexity is O(V).

#### Complexity Analysis of Breadth First Search
Time Complexity
- The time complexity of BFS if the entire tree is traversed is O(V) where V is the number of nodes.
- The time complexity of BFS actually depends on the data structure being used to represent the graph.

##### Graph representation as an Adjacency List
- Here, each listNode maintains a list of all its adjacent edges.
- If we assume that there are V number of nodes and E number of edges in the graph.
- For each listNode, we discover all its neighbors by traversing its adjacency list just once in linear time.

Directed Graph
- For a directed graph, the sum of the sizes of the adjacency lists of all the nodes is E. So, the time complexity in this case is O(V) + O(E) = O(V + E).

Undirected Graph
- For an undirected graph, each edge appears twice. Once in the adjacency list of either end of the edge.
- The time complexity for this case will be ```O(V) + O (2E) ~ O(V + E)```.

Graph represented as an adjacency matrix (a V x V array)
- For each listNode, we will have to traverse an entire row of length V in the matrix to discover all its outgoing edges.
- Each row in an adjacency matrix corresponds to a listNode in the graph, and that row stores information about edges emerging from the listNode.
- Hence, the time complexity of BFS in this case is ```O(V * V) = O(V2)```.

Space Complexity
- Since we are maintaining a queue (FIFO architecture) to keep track of the visited nodes, in worst case, the queue could take upto the size of the nodes (or vertices) in the graph.
- Hence, the space complexity is ```O(V)```.

Adjacency list vs  Adjacency matrix - Space Complexity
- In general, the space complexity of an adjacency list is O(V+E), and in the worst case, it is O(V2) when every listNode is connected to all the other nodes.
- Here, V represents the number of vertices and E represents the number of edges in the graph.
- The space complexity of the adjacency matrix is O(V2).
- Benefits of Adjacency list over Adjacency matrix w.r.t space complexity 
  - The real advantage of adjacency lists is that they allow save space for the graphs that are not really densely connected. 
  - If the number of edges is much smaller than V^2, then adjacency lists will take O(V+E), and not O(V^2) space.


# Graph Problems
## 323. Number of Connected Components in an Undirected Graph - Undirected Graph + Connected Components
You have a graph of n nodes. You are given an integer n, and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
Return the number of connected components in the graph.
```
    Input:     n = 5, 
           edges = [[0,1],[1,2],[3,4]]
    Output: 2
```

### Solution
First we will draw all the nodes as a graph from 0 to N-1, so that we visually see what we are working with.
Then we can go thru the edges from the input and fill in the graph.
These are undirected edges - 2 way streets.
Once we draw we could see that there could be different clusters or islands of nodes.
Each of these is called a connected component.
We need to algorithmically count these connected components.
Similar to the number of islands problem in a graph instead of a matrix.
We want to pick a listNode and see all the nodes connected to it the next listNode.
Eventually we will visit all the nodes in that cluster, that would be 1 connected component.
The main logic is to use a DFS that lets use explore all the neighbors of a listNode, mark them as visited [an array]
so that we do not visit them again.

We setup an adjacency list or a matrix  so that we can easily lookup each nodes neighbors.
We will get the edges in as a list of pairs. You want to go thru each of the edges and list which nodes are adjacent in the adjacency list.
We can use a hashmap, or a list of lists for the adjacency list.
We will set the key as the nodes name and the values a list of its edges.
We initially use a for-loop to initialize all these lists to be empty
We then we iterate thru the input pairs and populate the adjacency list with edges.
Since the graph is undirected, we need to add each edge twice, since a is b's neighbors. Then b is a's neighbor too.
After this we need to iterate thru each listNode and explore all its neighbors.
As we encounter nodes we use a boolean visited array to mark the nodes that we have seen.
As we finish exploring all the connected components, we increment by 1.
Visited array prevents us from double counting a connected components and the for loop prevents from missing one of the connected component.

Within the DFS, we mark the current listNode as visited and then get the list of neighbors from the adjacency list.
We then DFS into the listNode we have not seen.
Once all the nodes are visited, the DFS calls will stop. We are done.

Calling DFS in a graph is no different from trees. It involves some setup with adjacency list and a visited array.

### Representation
```
        n     = 5
        edges = [[0,1],[1,2],[3,4]]

        adjList
                0 [1]
                1 [0,2]
                2 [1]
                3 [4]
                4 [3]
        
        Undirected Graph        
                0 - 1   3
                    |   |
                    2   4
```

```
        n     = 5
        edges = [[0,1],[1,2],[2,3],[3,4]]
        
        adjList
                0 [1]
                1 [0,2]
                2 [1,3]
                3 [2,4]
                4 [3]

        Undirected Graph        
                0 - 1   3
                    | / |
                    2   4
```
### Implementation
```
    public int countComponents(int n, int[][] edges) {
        if(n==0) return 0;

        // Initialize the adj lists to be empty 
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i=0; i < n; i++){
            adjList.put(i, new ArrayList<Integer>());
        }
        
        //  Iterate thru the input pairs to populate the adjacency list with edges, adding each edge twice - undirected graph
        //  O(E) time and O(E) space
        for (int i=0;i<edges.length; i++){
            int[] edge = edges[i];
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        
        // A boolean visited array to mark the nodes that we have seen.
        // O(V) space        
        int[] visited = new int[n]; 

        int connectedComponents = 0;
        // visited array prevents us from double counting a connected components and the for loop prevents from missing one of the connected component
        // O (V) time
        for (int i=0; i<n; i++){
            if (visited[i] == 0) {
                dfs(i, adjList, visited);   // Runtime stack - O(V) space
                connectedComponents++;
            }
        }

        return connectedComponents;
    }

    private void dfs(int vertex, Map<Integer, List<Integer>> adjList, int[] visited) {
        if (visited[vertex] == 1)
            return;

        visited[vertex] = 1;
        List<Integer> neighbors = adjList.get(vertex);
        for(Integer neighbor: neighbors){
            dfs(neighbor, adjList, visited);
        }
    }
    
```

Complexity Analysis
- If ```E is the number of edges``` and ```V is the number of vertices```.

Time complexity ```O(E+V)```
- Building the adjacency list will take O(E) operations, as we iterate over the list of edges once, and insert each edge into two lists.
- During the DFS traversal, each vertex will only be visited once. This is because we mark each vertex as visited as soon as we see it, and then we only visit vertices that are not marked as visited.
- In addition, when we iterate over the edge list of each vertex, we look at each edge once.
- This has a total cost of O(E+V).

Space complexity ``` O(E+V)```
- Building the adjacency list will take O(E) space.
- To keep track of visited vertices, an array of size O(V) is required.
- Also, the run-time stack for DFS will use O(V) space.

Adjacency list vs  Adjacency matrix - Space Complexity
- In general, the space complexity of an adjacency list is O(V+E), and in the worst case, it is O(V2) when every listNode is connected to all the other nodes. 
- Here, V represents the number of vertices and E represents the number of edges in the graph.
- The space complexity of the adjacency matrix is O(V2).

## 207. Course Schedule  - Directed Graph + Cycle Detection (0,-1,1)
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
    Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
    Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.

Example 2:        
Input: numCourses = 5, 
prerequisites = [[0,1], [1,2], [3,2], [4,3], [2,4]]

Similar to the above. But different in few ways.
- It is a cycle detection problem which kind of changes the way you traverse and label nodes.
Apart from few minor differences that
- the graph is directed and visited array used different labels to check for cycles.
This problem is very similar in structure to the previous one. 
First we will draw all the nodes as a graph from 0 to n-1, so that we visually see what we are working with.
Then we can go thru the edges from the input and fill in the graph.
In this case these edges are directed edges - one way streets. we add arrows to the edges.
As you make up more examples you will notice that the components in the graph will not need to be connected, the graph can have separate connected components.
The only case you cannot complete all the courses is when we have a cycle.
*This problem is all about finding cycle*

Then how do you find cycles in a graph?
The traversal part is the same, we pick and listNode and visit all its neighbors and mark them as visited in the visited graph.
*Now anytime you come across a listNode that is already visited, you would know that have a cycle.* \
But there is an issue. *A cycle is only when you come across a listNode in the current traversal, not from the past traversal.*\
This calls for the below pattern - keeping track of visiting and visited nodes. 
```
     0 = unvisited
    -1 = visiting
     1 = visited
```
While we are visiting, we mark them with -1. once done we change the -1s to 1s.
```            
                1         -1
                1         3
              /   \    /    \ 
            0       2    -   4 -1
            1      -1
          ???
            0   [0,1]
            1   [1,2]
            2   [3,2]
            3   [4,3]
            4   [2,4]
```
Note that we have a cycle within 2-3-4 [2-4-3-2]


##### Intuition
Pretty much like the previous one.
The only difference is, since this is a *directed graph* we only need to add each edge once
For the visited array, instead of using a boolean array we are using an integer array.     
then we run the DFS function. Note that not all the components may be connected.
We are done if the cycle is found. 
Within the DFS function. as we enter the dfs [up the hill] we mark the listNode as -1.
Now when we are done visiting, on the way back [downhill] we change it to 1, marking as the listNode as visited and we return true 
since there were no cycles found.    
The base cases basically ensure that we stop, when we see a listNode that we have encountered before. Obliviously when the listNode is labeled 0 we should visit it
if its 1 we checked it in a previous traversal and there are no cycles necessarily.
However, if its -1, it would mean we encountered the listNode in the same exploration.
 
If the above example is an undirected graph, you would not be able to use the same logic to detect if there is a cycle.
You pass the parent listNode to determine where you came from.

For example, this one below is not a cycle.    
        -1      -1
         0   -   1
This can be identified by passing the parent listNode as a param to the next listNode you are visiting.
   
##### Solution
The problem could be modeled as yet another graph traversal problem, where each course can be represented as a vertex in a graph and the dependency between the courses can be modeled as a directed edge between two vertex.\
And the problem to determine if one could build a valid schedule of courses that satisfies all the dependencies (i.e. constraints) would be equivalent to determine if the corresponding graph is a DAG (Directed Acyclic Graph), i.e. there is no cycle existed in the graph.\
A typical strategy for graph traversal problems would be backtracking or simply DFS (depth-first search).\

1. Directed Graph
We only need to add each edge once

2. Cycle detection
A cycle is only when you come across a listNode in the current traversal, not from the past traversal.
This calls for the below pattern - keeping track of visiting and visited nodes.
```
    0 = unvisited
   -1 = visiting
    1 = visited
```
3. Components in the graph need not be connected
The components in the graph will not need to be connected, the graph can have separate connected components.

```
   Graph is directed, need not be connected. Its a cycle detection problem.
    i.e. [2->1] [1->0]
   Here,
    numCourses: Nodes of the graph
    prerequisites: Edges between the nodes of the graph

   numCourses = 9
   prerequisites: [[8,7],[7,6],[6,5],[5,4],[4,3],[3,2],[2,1],[1,0]]
   adjList: {  0:[],   1:[0],  2:[1],  3:[2],  4:[3],  5:[4],  6:[5],  7:[6],  8:[7]   }
   canFinish: true
     7       5       3      1
    /  \   /   \   /   \  /  \
   8     6        4      2     0

   numCourses = 9
   prerequisites: [[8,7], [7,6], [6,5], [5,4], [4,3], [3,2], [2,1], [2,4], [1,0]]
   adjList: {  0:[],   1:[0],  2:[1,4],  3:[2],  4:[3],  5:[4],  6:[5],  7:[6],  8:[7]   }
   canFinish: false

      7       5       3      1
    /  \   /   \   /   \  /  \
   8     6        4   -  2     0
```

##### Implementation
```
  public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length ==0)
            return true;
        
        Map<Integer, ArrayList<Integer>> adjacencyList = new HashMap<>();        
        // initialize the adjacencyList
        for (int i=0; i<numCourses; i++) 
            adjacencyList.put(i, new ArrayList<>());
        
        // populate the adjacencyList - directed graph one side only populated
        for (int i=0; i<prerequisites.length; i++){
            int[] edge = prerequisites[i];
            //                   1   ->   0
            adjacencyList.get(edge[0]).add(edge[1]); // [2->1] [1->0]
        }

        // visited array where 0=not visited, 1= visited, -1= currently visiting
        int[] visited =  new int[numCourses];
        // iterate thru the
        for (int i=0;i<numCourses; i++){
            boolean cycle = dfs(adjacencyList, visited, i);            
            if(!cycle) return false; // if a cycle is found return false else continue;
        }

        // No cycle found. all courses can be finished
        return true;
    }

    private boolean dfs(Map<Integer, ArrayList<Integer>> adjacencyList, int[] visited, int listNode){
        // if we run into a listNode that visited in the current dfs traversal, then there is a cycle
        if (visited[listNode] == -1 )
            return false;

        // if we run into a listNode that we have already seen in an previous dfs traversal
        if (visited[listNode] == 1)
            return true;

        //Current listNode is not -1 or 1, it is 0. So mark the listNode as currently visiting before going uphill
        visited[listNode]  = -1;

        List<Integer> list = adjacencyList.get(listNode);
        for (int neighbor: list){
            boolean cycle = dfs(adjacencyList, visited, neighbor);
            // if a cycle is found return false, which should bubble all the way up to return false, else continue
            if(!cycle)
                return false;
        }

        // Once we have visit all the current nodes neighbors, we mark the listNode as fully visited during downhill
        visited[listNode] = 1;
        return true;
    }

```        
##### Complexity
Time Complexity: O(∣E∣+∣V∣^2) where |E| is the number of dependencies, |V| is the number of courses and d is the maximum length of acyclic paths in the graph.\
- First of all, it would take us |E| steps to build a graph in the first step.\
- For a single round of backtracking, in the worst case where all the nodes chained up in a line, it would take us maximum |V| steps to terminate the backtracking.\ 
- Again, follow the above worst scenario where all nodes are chained up in a line, it would take us in total \sum_{i=1}^{|V|}{i} = \frac{(1+|V|)\cdot|V|}{2}∑ i=1 i=2(1+∣V∣) steps to finish the check for all nodes.
- As a result, the overall time complexity of the algorithm would be O(|E| + |V|^2) .

Space Complexity: O(|E| + |V|), with the same denotation as in the above time complexity.
- We built a graph data structure in the algorithm, which would consume |E| + |V| space.
- In addition, during the backtracking process, we employed a sort of bitmap (path) to keep track of all visited nodes, which consumes |V| space.
- Finally, since we implement the function in recursion, which would incur additional memory consumption on call stack. In the worst case where all nodes are chained up in a line, the recursion would pile up |V| times.
- Hence, the overall space complexity of the algorithm would be O(|E| + 3.|V|) = O(|E| + |V|).

## 261. Graph Valid Tree - Fully Connected Graph + No Cycles (parent current child) 
You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
Return true if the edges of the given graph make up a valid tree, and false otherwise.

```
Example 1:
Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true
                0
             /  |  \
            1   2   3
            |
            4
Example 2:
Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false
                0 -  1 -  2
                       \  |
                     4    3
```

Intuition - Graph Theory + Depth-First Search (recursive /iterative)
Note that this same approach also works with recursive depth-first search and iterative breadth-first search. 
Recall that a graph, G, is a tree iff the following two conditions are met:    
G is fully connected. In other words, for every pair of nodes in G, there is a path between them.
G contains no cycles. In other words, there is exactly one path between each pair of nodes in G.
Depth-first search is a classic graph-traversal algorithm that can be used to check for both of these conditions:
G is fully connected if, and only if, we started a depth-first search from a single source and discovered all nodes in G during it.
G contains no cycles if, and only if, the depth-first search never goes back to an already discovered listNode. We need to be careful though not to count trivial cycles of the form A → B → A that occur with most implementations of undirected edges.
Depth-first search requires being able to look up the adjacent (immediate neighbours) of a given listNode. Like many graph interview problems though, the input format we're given doesn't allow us to quickly get the neighbours of a listNode. Therefore, our first step is to convert the input into an adjacency list. Recall that an adjacency list is where we have a list of sub-lists, where each sub-list is the list of the immediate neighbours for the i'th listNode.

Complexity Analysis \
Let E be the number of edges, and N be the number of nodes or vertices [V ???]

Time Complexity : O(N+E) \
Creating the adjacency list requires 
- Initialising a list of length N, with a cost of O(N), and 
- then iterating over and inserting E edges, for a cost of O(E). 
- This gives us O(E) + O(N) = O(N + E).
- This means that the outer loop will run N times (Each listNode is added to the data structure once). 
- For each of the N nodes, its adjacent edges is iterated over once. 
- In total, this means that all E edges are iterated over once by the inner loop. 
- This, therefore, gives a total time complexity of O(N + E).
- Because both parts are the same, we get a final time complexity of O(N + E).

Space Complexity : O(N+E)
- The adjacency list is a list of length N, with inner lists with lengths that add to a total of E. This gives a total of O(N + E) space.
- In the worst case, the stack/queue will have all N nodes on it at the same time, giving a total of O(N) space.
- In total, this gives us O(E + N) space.

Implementation
```        
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int i=0; i<n; i++)
            adjList.put(i, new ArrayList<Integer>());

        for (int i=0; i<edges.length; i++){
            int[] edge = edges[i];
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);            
        }

        int[] visited  = new int[n]; // Mark the listNode visited
        //boolean validTree = dfs(0, adjList, visited, -1);
        //boolean validTree = bfs(0, adjList, visited);
        boolean validTree = bfs2(0, adjList, visited);
        
        // if there is a cycle - an edge from child to parent, graph would not be a tree - return false        
        if (!validTree) return false; 

        //  G is fully not fully connected - if we are left with any un-visited nodes, not a tree - return false
        for (int visit: visited){ 
            if (visit == 0) return false; 
        }

        //  G is a fully connected graph with all nodes visited and no cycles
        return true;
    }

    private boolean dfs(int current, Map<Integer, List<Integer>> adjList, int[] visited, int parent) {
        if (visited[current] == 1) // Detects if there is a path that goes back to an already discovered listNode
            return false;   

        visited[current] = 1;
        List<Integer> children = adjList.get(current);
        for (int child: children){            
            if (parent != child) { // *** check prevents going back to parent listNode in a self loop (current = 1, parent of current = 0, child of current = 0)
                boolean validTree = dfs(child, adjList, visited, current); // add the current listNode as parent to the child listNode. 
                if (!validTree) return false;
            }
        }
        return true;
    }
    
    public boolean bfs(int i, Map<Integer, List<Integer> > adjList, int[] visited){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);

        while(!queue.isEmpty()){
            Integer current = queue.poll();
            
            if (visited[current] == 1)
                return false;
            
            visited[current] = 1;
            List<Integer> children = adjList.get(current);
            for (int child: children){
                queue.add(child);
                // Delete the opposite direction edges from the adjacency list. 
                // In other words, when we follow an edge A → B, we lookup Bs adjacency list and delete A from it, 
                // effectively removing the opposite edge of B → A.
                adjList.get(child).remove(current); 
            }
        }
        return true;
    }

    public boolean bfs2(int i, Map<Integer, List<Integer> > adjList, int[] visited){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);

        while(!queue.isEmpty()){
            Integer current = queue.poll();

            if (visited[current] == 1)
                return false;

            visited[current] = 1;
            List<Integer> children = adjList.get(current);
            for (int child: children){
                //*** For current 1, child  0 evaluates to false, so skips over
                // However for current 1, child 4 evaluates to true
                if (map.get(current) != child) {  // check witin the map if the current/parent is a child
                    queue.offer(child);
                    // For current (say 0) we populate the children (1,2,3) in the map.
                    // For a child [1/2/3] we add its parent/current [0] into the map - So the map will contain 1:0, 2:0, 3:0
                    map.put(child, current); // Add the parent of the child into the map
                }
            }
        }
        return true;
    }

```

## Topological Sort
A topological ordering is a linear ordering of its vertices such that for every directed edge uv from vertex u to vertex v, u comes before v in the ordering.

Time Complexity: O(V + E)
```
          0 -  1  -  3
            \  |  /
               2  -  4

        [0, 1, 3, 2, 4]
        [0, 1, 2, 4, 3]
```

```
    private List<Integer> topologicalSort(int vertices, int[][] edges) {
        ...

        for (int i=0; i<adjList.size(); i++) {
            if (visited[i] != 1) dfs(0, adjList, visited, result);
        }
        ...
    }

    private void dfs(int vertex, Map<Integer, List<Integer>> adjList, int[] visited, List<Integer> result) {
        if (visited[vertex] == 1)
            return;

        visited[vertex] = 1;
        List<Integer> neighbors = adjList.get(vertex);
        for (Integer neighbor: neighbors){
            dfs(neighbor, adjList, visited, result);
        }
        result.add(0, vertex); // Add the vertices in the reverse enrichedTransaction.
    }
```

## 886. Possible BiPartition [UnDirected Graph + Graph Coloring - Group A & B - visited[listNode] != visit]
Given a set of n people (numbered 1, 2, ..., n), we would like to split everyone into two groups of any size.
Each person may dislike some other people, and they should not go into the same group.
Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
Return true if and only if it is possible to split everyone into two groups in this way.

Example 1:
Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[5,2]]
Output: false
Example 2:
Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]

Intuition \
Very similar to the LC 207 Course Schedule. 
* The only difference is that this is an undirected graph. * 
* If DFS finds two neighboring nodes with the same grouping/coloring, then we return false, which would mean it is not a Bipartite graph.*
If we run into a situation where two nodes share the same label, then the graph is not a bipartite.

```
Group Labeling
   0 = UnVisited
  -1 = Group A
   1 = Group B

Example of a bipartite graph
        n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[5,2]]

                     3   1
             1     /   \
             1 - 2      4 0     
                 0 \   /
                     5   1

Example of a non bipartite graph
        n = 5;      int[][] edges    = {{1,2},{3,4},{4,5},{3,5}};           

                 -1      1   
                 2 ----  3
               /         | 
          1  1           |         
               \         | 
                 5 ----  4
                 1      -1           
```
Implementation
```
    public boolean possibleBipartition(int n, int[][] likes) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int i= 1; i< n+1; i++)
            adjList.put(i, new ArrayList<Integer>());
        for(int i=0; i< likes.length; i++){
            int[] edge = likes[i];
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        int[] visited = new int[n+1];                   // notice the length of the of the array n+1
        for (int i=1; i<adjList.size()+1; i++){
            // While cycling thru all the unprocessed nodes (1 thru N), 
            // return false at the first occurrence of graph that is not bipartite
            if (visited[i] == 0 && !dfs(i, adjList, visited, 1, -1)){
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int listNode, Map<Integer, List<Integer>> adjList, int[] visited, int visit, int parent) {
        if (visited[listNode] != 0 && visited[listNode] != visit)
            return false;

        if (visited[listNode] != 0 && visited[listNode] == visit){      // protects from re-processing of a listNode, when cycling thru all nodes of the adjList
            return true;
        }

        visited[listNode] = visit;                                  // v[1]=1,  v[2]=-1,  v[4]:1
        List<Integer> children = adjList.get(listNode);             // 1:[2,3], 2:[1,4],  4:[2]
        for (int child: children){
            // child  - child of index listNode
            // parent - parent of index listNode, parent method argument
            if (child != parent) {                              //(2 != 1) T,  (1 != 1) F,    (4 != 1)T,   (2 != 1)T
                boolean canBePartitioned = dfs(child, adjList,
                        visited, -visit,
                        listNode);                                  //(2,-(1),1), x(1,-(-1),2), (4,-(-1),2)
                if (!canBePartitioned)
                    return false;
            }
        }
        return true;
    }
```
Complexity \ 

Time complexity \
O(E+V) 

Space complexity \
O(E+V) - Adjacency list for our nodes and a visited array for storing the adjacent 

## Indegrees and Outdegrees
Any graph can be seen as collection of nodes connected through edges.
If edges have direction then graphs are known as directed graphs else undirected graphs.
In case of directed graphs, number of edges going into a listNode is known as in degree of the corresponding listNode and number of edges coming out of a listNode is known as outdegree of the corresponding listNode. For any graph sum of total indegree should be equal to total outdegree. (Why ? Think !)
Assumptions: Self loops contribute to both indegree and outdegree.
Reference: https://www.quora.com/What-is-the-indegree-and-outdegree-of-a-graph

## 310. Minimum Height Trees
A tree is an undirected graph in which any two vertices are connected by exactly one path. 
In other words, any connected graph without simple cycles is a tree.
Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that 
there is an undirected edge between the two nodes ai and bi in the tree, you can choose any listNode of the tree as the root. 
When you select a listNode x as the root, the result tree has height h. 
Among all possible rooted trees, those with minimum height (i.e. min(h)) are called minimum height trees (MHTs).
Return a list of all MHTs' root labels. 
You can return the answer in any enrichedTransaction.
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Example 1:
    Input: n = 4, edges = [[1,0],[1,2],[1,3]]
    Output: [1]
    Explanation: As shown, the height of the tree is 1 when the root is the listNode with label 1 which is the only MHT.

Solution
    We will solve this using BFS.
    

###  Union Find 
###  Bellman


### 797 All Paths From Source to Target
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1,
find all possible paths from listNode 0 to listNode n - 1 and return them in any enrichedTransaction.
The graph is given as follows: graph[i] is a list of all nodes you can visit from listNode i (i.e., there is a directed edge from listNode i to listNode graph[i][j]).

Input:  graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

```
        // Given, n nodes labeled from 0 to n - 1. Find all possible paths from listNode 0 to listNode n - 1
        int[][] graph = {{1,2},     // 0 {1,2}
                         {3},       // 1 {3}
                         {3},       // 2 {3}
                         {}};       // 3 {}
                2 -> 3
                ^    ^
                |    |
                0 -> 1
```
Implementation 1
```
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList();
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.length];

        stack.push(0);
        dfs(graph, 0, visited, stack, result);
        System.out.println(result);

        return result;
    }

  private void dfs(int[][] graph, int v, boolean[] visited, Stack<Integer> stack, List<List<Integer>> result) {
        if (v == graph.length-1){
            result.add(new ArrayList(stack));
            return;
        }

        visited[v] = true;
        int[] neighbors = graph[v];
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                stack.push(neighbor);
                dfs(graph, neighbor, visited, stack, result);
                stack.pop();
            }
        }
        visited[v] = false;
    }

```
Implementation 2
```
    /**
              /  1 -  3 \
            0       /    5
              \  2 -  4 /

            [0, 1, 3, 2, 4, 5]
            [0, 1, 3, 5, 4, 2]
            [0, 2, 4, 5, 3, 1]
    */
    Map<Integer, List<Integer>> adjList;

    private void traverse(int vertices, int[][] edges) {
        adjList = new HashMap();

        for (int i=0; i<vertices; i++){
            adjList.put(i, new LinkedList());
        }
        for (int i=0; i<edges.length; i++){
            int[] edge = edges[i];
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        ArrayList<Integer> chosen = new ArrayList<>();
        int[] visited = new int[vertices];
        chosen.add(0);
        dfsAllPaths(0, adjList, visited, chosen, new ArrayList());
    }

    private void dfsAllPaths(int vertex, Map<Integer, List<Integer>> adjList, int[] visited, /*Stack<Integer>*/ List<Integer> chosen, List<List<Integer>> result) {
        if (chosen.size() == adjList.size()) {
            result.add(new ArrayList(chosen)); System.out.println(chosen);
            return;
        }

        if (visited[vertex] == 1) {
            return;
        }

        visited[vertex] = 1;
        List<Integer>  neighbors = adjList.get(vertex);
        for(int neighbor: neighbors){
            if (visited[neighbor] == 0){
                chosen.add(neighbor);
                dfsAllPaths(neighbor, adjList, visited, chosen, result);
                chosen.remove(chosen.size() - 1);
            }
        }
        visited[vertex] = 0;
    }
```


## OTHER REFERENCES


