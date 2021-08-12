# Graph
- A graph is a collection of vertices and edges. Unlike a Tree which uses a Node class, in a graph we usually care about the adjacent nodes.
- The Relationship between the edges. Neighbors of a node. We can usually represent as a graph with an adjacency list or adjacency matrix.
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
5. Graph Coloring
6. Indegrees

#### Adjacency list
An Adjacency List is usually a Hashmap, or a List of Lists
```   
        Key Value
        1   [2]
        2   [1,3,4]
        3   [2]
        4   [2]
```

#### Adjacency List Representation - Option 1
```
    someGraph(int n, int[][] edges) {
        LinkedList<Integer>[] adj; // Array of lists for Adjacency List Representation
        ...        
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
        ...
        adj[v].add(w); // Add w to v's list.
     }
```
#### Adjacency List Representation - Option 2
```
    someGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i=0; i<n; i++)
            adjList.put(i, new ArrayList<Integer>());
        ...
        for (int i=0; i<edges.length; i++){
            int[] edge = edges[i];
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);            
        }
        ...
    }
```

#### Adjacency Matrix - 2D array
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
- After this we need to iterate thru each node and explore all its neighbors.
- As we encounter nodes we use this boolean visited array to make the nodes that we have seen.
- As we finish exploring all the connected components, we increment by 1.
- Visited array prevents us from double counting a connected components and
- the for loop prevents from missing one of the connected component.
- Within the DFS, you mark the current node as visited and then you get the list of neighbors from the adjacency list.
- We DFS into the one we have not seen.
- Once all of them are visited. the DFS calls will automatically stop and then we are done.
- Calling DFS in a graph is no different from trees. It involves some setup with adjacency list and a visited array.

#### Complexity Analysis of Depth First Search
Time Complexity
- The time complexity of DFS if the entire tree is traversed is O(V) where V is the number of nodes.
- The time complexity of DFS actually depends on the data structure being used to represent the graph.

Graph represented as adjacency list
- Each node maintains a list of all its adjacent edges.
- If we assume that there are ```V number of nodes``` and ```E number of edges``` in the graph.
- For each node, we discover all its neighbors by traversing its adjacency list just once in linear time.
- Directed graph - For a directed graph, the sum of the sizes of the adjacency lists of all the nodes is E. So, the time complexity in this case is ```O(V) + O(E) = O(V + E)```
- Undirected graph - For an undirected graph, each edge appears twice. Once in the adjacency list of either end of the edge. The time complexity for this case will be ```O(V) + O (2E) ~ O(V + E)```.

Graph represented as an adjacency matrix (V x V array)
- For each node, we will have to traverse an entire row of length V in the matrix to discover all its outgoing edges.
- Each row in an adjacency matrix corresponds to a node in the graph and that row stores information about edges emerging from the node.
- Hence, the time complexity of DFS in this case is ```O(V * V) = O(V^2)```.

Space Complexity
- Since we are maintaining a stack to keep track of the last visited node in worst case, the stack could take upto the size of the nodes (or vertices) in the graph.
- Hence, the space complexity is O(V).

#### Complexity Analysis of Breadth First Search
Time Complexity
- The time complexity of BFS if the entire tree is traversed is O(V) where V is the number of nodes.
- The time complexity of BFS actually depends on the data structure being used to represent the graph.

Graph represented as Adjacency List
- Here, each node maintains a list of all its adjacent edges.
- If assume that there are V number of nodes and E number of edges in the graph.
- For each node, we discover all its neighbors by traversing its adjacency list just once in linear time.

Directed Graph
- For a directed graph, the sum of the sizes of the adjacency lists of all the nodes is E. So, the time complexity in this case is O(V) + O(E) = O(V + E).

Undirected Graph
- For an undirected graph, each edge appears twice. Once in the adjacency list of either end of the edge.
- The time complexity for this case will be ```O(V) + O (2E) ~ O(V + E)```.

Graph represented as an adjacency matrix (a V x V array)
- For each node, we will have to traverse an entire row of length V in the matrix to discover all its outgoing edges.
- Each row in an adjacency matrix corresponds to a node in the graph, and that row stores information about edges emerging from the node.
- Hence, the time complexity of BFS in this case is ```O(V * V) = O(V2)```.

Space Complexity
- Since we are maintaining a queue (FIFO architecture) to keep track of the visited nodes, in worst case, the queue could take upto the size of the nodes (or vertices) in the graph.
- Hence, the space complexity is ```O(V)```.


# Graph Problems
## 323. Number of Connected Components in an Undirected Graph [Undirected Graph, Connected Components]
You have a graph of n nodes. You are given an integer n, and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
Return the number of connected components in the graph.
```
    Input:     n = 5, 
           edges = [[0,1],[1,2],[3,4]]
    Output: 2
```

Solving the problem    
-First we will draw all the nodes as a graph from 0 to N-1, so that we visually see what we are working with.
-Then we can go thru the edges from the input and fill in the graph.
-These are undirected edges - 2 way streets.
-Once we draw we could see that there could be different clusters or islands of nodes.
-Each of these is called a connected component.
-We need to algorithmically count these connected components.
-Similar to the number of islands problem in a graph instead of a matrix.
-We want to pick a node and see all the nodes connected to it the next node.
-Eventually we will visit all the nodes in that cluster, that would be 1 connected component.
-The main logic is to use a DFS that lets use explore all the neighbors of a node, mark them as visited [an array]
-so that we do not visit them again.

Code
- We setup an adjacency list or a matrix  so that we can easily lookup each nodes neighbors.
- We will get the edges in as a list of pairs. You want to go thru each of the edges and list which nodes are adjacent in the adjacency list.
- We can use a hashmap, or a list of lists for the adjacency list.
- We will set the key as the nodes name and the values a list of its edges.
- We initially use a for-loop to initialize all these lists to be empty
- We then we iterate thru the input pairs and populate the adjacency list with edges.
- Since the graph is undirected, we need to add each edge twice, since a is b's neighbors. Then b is a's neighbor too.
- After this we need to iterate thru each node and explore all its neighbors.
- As we encounter nodes we use this boolean visited array to make the nodes that we have seen.
- As we finish exploring all the connected components, we increment by 1.
- Visited array prevents us from double counting a connected components and
- the for loop prevents from missing one of the connected component.
- Within the DFS, you mark the current node as visited and then you get the list of neighbors from the adjacency list.
- We DFS into the one we have not seen.
- Once all of them are visited. the DFS calls will automatically stop and then we are done.
- Calling DFS in a graph is no different from trees. It involves some setup with adjacency list and a visited array.

Implementation
```
    public int countComponents(int n, int[][] edges) {
        if(n==0)
            return 0;

        // Initialize adjList
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i=0; i < n; i++){
            adjList.put(i, new ArrayList<Integer>());
        }

        for (int i=0;i<edges.length; i++){
            int[] edge = edges[i];
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        int[] visited = new int[n];

        int connectedComponents = 0;
        for (int i=0; i<n; i++){
            if (visited[i] == 0) {
                dfs(i, adjList, visited);
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
            if (visited[neighbor]==0) {
                dfs(neighbor, adjList, visited);
            }
        }
    }

```

Complexity Analysis\
Here E = Number of edges, V = Number of vertices.

Time complexity: O(E+V).\
Building the adjacency list will take O(E) operations, as we iterate over the list of edges once, and insert each
edge into two lists.
During the DFS traversal, each vertex will only be visited once. This is because we mark each vertex as visited
as soon as we see it, and then we only visit vertices that are not marked as visited. In addition, when we iterate
over the edge list of each vertex, we look at each edge once. This has a total cost of O(E+V).

Space complexity: O(E+V).\
Building the adjacency list will take O(E) space. To keep track of visited vertices, an array of size O(V) is required.
Also, the run-time stack for DFS will use O(V) space.

## 207. Course Schedule  - Directed Graph + Cycle Detection (0 -1 1)
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

Intuition 
Similar to the above. But different in few ways. It's a cycle detection problem which kind of changes the way you traverse and label nodes.
Apart from few minor differences that the graph is directed and visited array used different labels to check for cycles. 
This problem is very similar in structure to the previous one. 

First we will draw all the nodes as a graph from 0-N-1, so that we visually see what we are working with. 
Then we can go thru the edges from the input and fill in the graph.
In this case these edges are directed edges - one way streets. we add arrows to the edges.
As you make up more examples you will notice that the components in the graph will not need to be connected. 
Can have separate connected components.
only case you cannot complete all the courses is when we have a cycle. *This problem is all about finding cycle*
Then how do you find cycles in a graph?
The traversal part is the same, we pick and node and visit all its neighbors and mark them as visited in the visited graph.
*Now anytime you come across a node that is already visited, you would know that have a cycle.* \
But there is an issue. *A cycle is only when you come across a node in the current traversal, not from the past traversal.*\
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
```
Note that we have a cycle within 2-3-4 [2-4-3-2]

##### Intuition
Pretty much like the previous one.
The only difference is, since this is a *directed graph* we only need to add each edge once
For the visited array, instead of using a boolean array we are using an integer array.     
then we run the DFS function. Note that not all the components may be connected.
We are done if the cycle is found. 
Within the DFS function. as we enter the dfs [up the hill] we mark the node as -1.
Now when we are done visiting, on the way back [downhill] we change it to 1, marking as the node as visited and we return true 
since there were no cycles found.    
The base cases basically ensure that we stop, when we see a node that we have encountered before. Obliviously when the node is labeled 0 we should visit it
if its 1 we checked it in a previous traversal and there are no cycles necessarily.
However, if its -1, it would mean we encountered the node in the same exploration.
 
If the above example is an undirected graph, you would not be able to use the same logic to detect if there is a cycle.
You pass the parent node to determine where you came from.

For example, this one below is not a cycle.    
        -1      -1
         0   -   1
This can be identified by passing the parent node as a param to the next node you are visiting.
   
##### Intuition
The problem could be modeled as yet another graph traversal problem, where each course can be represented as a vertex in a graph and the dependency between the courses can be modeled as a directed edge between two vertex.\
And the problem to determine if one could build a valid schedule of courses that satisfies all the dependencies (i.e. constraints) would be equivalent to determine if the corresponding graph is a DAG (Directed Acyclic Graph), i.e. there is no cycle existed in the graph.\
A typical strategy for graph traversal problems would be backtracking or simply DFS (depth-first search).\

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
            int[] prerequisite = prerequisites[i];
            adjacencyList.get(prerequisite[0]).add(prerequisite[1]);    // intutive     [0-1-2-4-3-2]
            //adjacencyList.get(prerequisite[1]).add(prerequisite[0]);  // Not intutive 
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

    private boolean dfs(Map<Integer, ArrayList<Integer>> adjacencyList, int[] visited, int node){
        // if we run into a node that visited in the current dfs traversal, then there is a cycle
        if (visited[node] == -1 )
            return false;

        // if we run into a node that we have already seen in an previous dfs traversal
        if (visited[node] == 1)
            return true;

        //Current node is not -1 or 1, it is 0. So mark the node as currently visiting before going uphill
        visited[node]  = -1;
        if (adjacencyList.containsKey(node)){
            List<Integer> list = adjacencyList.get(node);
            for (int neighbor: list){
                boolean cycle = dfs(adjacencyList, visited, neighbor);
                // if a cycle is found return false, which should bubble all the way up to return false, else continue
                if(!cycle)
                    return false;
            }
        }
        // Once we have visit all the current nodes neighbors, we mark the node as fully visited during downhill
        visited[node] = 1;
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


## 261. Graph Valid Tree [Fully Connected Graph + No Cycles (parent current child)] 
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
G contains no cycles if, and only if, the depth-first search never goes back to an already discovered node. We need to be careful though not to count trivial cycles of the form A → B → A that occur with most implementations of undirected edges.
Depth-first search requires being able to look up the adjacent (immediate neighbours) of a given node. Like many graph interview problems though, the input format we're given doesn't allow us to quickly get the neighbours of a node. Therefore, our first step is to convert the input into an adjacency list. Recall that an adjacency list is where we have a list of sub-lists, where each sub-list is the list of the immediate neighbours for the i'th node.

Complexity Analysis \
Let E be the number of edges, and N be the number of nodes or vertices [V ???]

Time Complexity : O(N+E) \
Creating the adjacency list requires 
- Initialising a list of length N, with a cost of O(N), and 
- then iterating over and inserting E edges, for a cost of O(E). 
- This gives us O(E) + O(N) = O(N + E).
- This means that the outer loop will run N times (Each node is added to the data structure once). 
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

        int[] visited  = new int[n]; // Mark the node visited
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
        if (visited[current] == 1) // Detects if there is a path that goes back to an already discovered node
            return false;   

        visited[current] = 1;
        List<Integer> children = adjList.get(current);
        for (int child: children){            
            if (parent != child) { // *** check prevents going back to parent node in a self loop (current = 1, parent of current = 0, child of current = 0)
                boolean validTree = dfs(child, adjList, visited, current); // add the current node as parent to the child node. 
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
        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, -1);
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
                if (parent.get(current) != child) {   
                    queue.offer(child);
                    // For current (say 0) we populate the children (1,2,3) in the map. 
                    // So for current 0 and child 1,2,3 we have 1:0, 2:0, 3:0 within the map
                    parent.put(child, current); 
                }
            }
        }
        return true;
    }

```

## 886. Possible Bipartition [UnDirected Graph + Graph Coloring - Group A & B - visited[node] != visit]
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

    private boolean dfs(int node, Map<Integer, List<Integer>> adjList, int[] visited, int visit, int parent) {
        if (visited[node] != 0 && visited[node] != visit)
            return false;

        if (visited[node] != 0 && visited[node] == visit){      // protects from re-processing of a node, when cycling thru all nodes of the adjList
            return true;
        }

        visited[node] = visit;                                  // v[1]=1,  v[2]=-1,  v[4]:1
        List<Integer> children = adjList.get(node);             // 1:[2,3], 2:[1,4],  4:[2]
        for (int child: children){
            // child  - child of index node
            // parent - parent of index node, parent method argument
            if (child != parent) {                              //(2 != 1) T,  (1 != 1) F,    (4 != 1)T,   (2 != 1)T
                boolean canBePartitioned = dfs(child, adjList,
                        visited, -visit,
                        node);                                  //(2,-(1),1), x(1,-(-1),2), (4,-(-1),2)
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


## 310. Minimum Height Trees
A tree is an undirected graph in which any two vertices are connected by exactly one path. 
In other words, any connected graph without simple cycles is a tree.
Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that 
there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. 
When you select a node x as the root, the result tree has height h. 
Among all possible rooted trees, those with minimum height (i.e. min(h)) are called minimum height trees (MHTs).
Return a list of all MHTs' root labels. 
You can return the answer in any order.
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Example 1:
    Input: n = 4, edges = [[1,0],[1,2],[1,3]]
    Output: [1]
    Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

Solution
    We will solve this using BFS.
    

###  Union Find 
###  Bellman
