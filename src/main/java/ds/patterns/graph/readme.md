##Graph Problems
Collection of vertices and edges. Unlike a Tree which uses a Node class, in a graph we usually care about the adjacent nodes here.
1. Directed & undirected edges
2. Connected Components
3. Cycle Detection
4. Topological Sort
5. Graph Coloring
6. Indegrees

Relationship between the edges. Neighbors of a node. We can usually represent a graph with an adjacency list or adjacency matrix.

              3
             / 
            2
           /  \
          1     4
        
####Adjacency list
   An Adjacency List is usually a Hashmap, or a List of Lists
   
   
        Key Value
        1   [2]
        2   [1,3,4]
        3   [2]
        4   [2]

####Adjacency Matrix - 2D array 
We mark all the spots where 2 nodes are connected and populate the matrix with all the edges. 
It is east to lookup all the nods neighbors.

        1   2   3   4
    1       T
    2   T       T   T
    3       T
    4       T

A big chunk of work within the graph problem is 
1. Setting up the  Adjacency List or the Matrix.
2. Visited Array 
3. Once you do that, you just have to iterate thru the graph using DFS or BFS, and keep track of everything you have seen using some sort of a visited array. 



#### 323. Number of Connected Components in an Undirected Graph
You have a graph of n nodes. You are given an integer n, and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
Return the number of connected components in the graph.
Input:     n = 5, 
       edges = [[0,1],[1,2],[3,4]]
Output: 2

Solving the problem    
    First we will draw all the nodes as a graph from 0-N-1, so that we visually see what we are working with. 
    Then we can go thru the edges from the input and fill in the graph.
    These are undirected edges - 2 way streets.
    Once we draw we could see that there could be different clusters or islands of nodes.
    Each of these is called a connected component. 
    We need to algorithmically count these connected components. 
    Similar to the number of islands problem in a graph instead of a matrix.
    We want to pick a node and see all the nodes connected to it the next node. 
    Eventually we will visits all the nodes in that cluster, that would be 1 connected component. 
    The main logic is to use a DFS that lets use explore all the neighbors of a node, mark them as visited [an array] so that we do not visit them again.  
     
Code
    We setup an adjacency list or a matrix  so that we can easily lookup each nodes neighbors.  
    you will get the edges in as a list of pairs. you want to go thru each of the edges and list which nodes are adjacent in the adjacency list.
    We can use a hashmap, or a list of lists for the adjacency list.
    We will set the key as the nodes name and the values a list of its edges.
    We initially use a for loop to initialize all these lists to be empty then we iterate thru the input pairs and populate the 
    adjacency list with edges.
    Since the graph is undirected, we need to add each edge twice, since a is b's neighbors. Then b is a's neighbor too.
    After this we need to iterate thruy each node and explore all its neighbors. 
    As we encounter nodes we use this boolean visited array to make the nodes that we have seen.
    As we finish exploring all the connected componets, we increment by 1.
    Visited array precents us from double counting a cooected componet.
    and the forloop prevents from missing one of the connected componet. 
    Within the DFS, you mark the current node as visited and then you get the list of neighbors from the adjency list. 
    We DFS into the once we have not seen. 
    Once all of them are visited. the DFS calls will stop automatically and you are done.
    Calling DFS in a graph is no different from trees. It involves some setup with adjuncy list and a visited array.

#### 207. Course Schedule
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

Similar to the above. But different in few ways. It's a cycle detection problem which kind of changes the way you traverse and label nodes.
Apart from few minor differences that the graph is directed and visited array used diffeent labels to check for cycles. 
This problem is very similar in structure to the previous one. 

First we will draw all the nodes as a graph from 0-N-1, so that we visually see what we are working with. 
Then we can go thru the edges from the input and fill in the graph.
In this case these edges are directed edges - 1 way streets. we add arrows to the edges.
As you make up more examples you will notice that the components in the graph will not need to be connected. 
Can have saperate connected components.
only case you cannot complete all teh courses is when we have a cycle. This problem is all about finding cycle
Then how do you find cycles in a graph?
The traversal part is the same, we pick and node and visit all its neighbors and mark them as visited in the visited graph.
Now anytime you come across a node that is already visited, you would know that have a cycle. But there us an issue
A cycle is only when you come across a node in the current traversal, not from the past traversal.

This calls for a pattern to make the nodes as visited
     0 = unvisited
    -1 = visiting
     1 = visited

While we are visiting, we mark them with -1. once done we change the -1s to 1s.
                1         -1
                1         3
              /   \    /    \ 
            0       2    -   4 -1
            1      -1
Note that we have a cycle within 2-3-4
    
    
Code
Pretty much like the previous one.
The only difference is, since this is a directed graph we only need to add each edge once
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
        
#### 261. Graph Valid Tree
You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
Return true if the edges of the given graph make up a valid tree, and false otherwise.

Example 1:
Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true

Example 2:
Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false

#### Possible Bi-partition [Example of Graph coloring, also called bipartite graph]
Given a set of n people (numbered 1, 2, ..., n), we would like to split everyone into two groups of any size.
Each person may dislike some other people, and they should not go into the same group.
Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
Return true if and only if it is possible to split everyone into two groups in this way.

Example 2:
Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]

Solution
    Very similar to the LC 207 Course Schedule. 
    The only difference is that this is an undirected graph. 
    If DFS finds two neighboring nodes with the same grouping/coloring, then we return false - not a Bipartite graph

Example 1:
    Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[5,2]]

Group Labeling
   0 = UnVisited
  -1 = Group A
   1 = Group B
                   
                     3   1
             1     /   \
             1 - 2      4 0     Example of a bipartite
                 0 \   /
                     1   1
                    
                      3  1   
             1     /  | 
             1 - 2    |         Example of not a bipartite
                   \  | 
                      1  1
If we run into a situation where two nodes share the same label, then the graph is not a bipartite.
                                 
                        
#### 310. Minimum Height Trees
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
    

6. Other Problems 
    Union Find, 
    Bellman
