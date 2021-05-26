Graph Problems
Collection of vertices and edges. Unlike a Tree which uses a Node class, in a graph we usually care about the adjacent nodes here.
    1. Directed & undirected edges
    2. Connected Components
    3. Cycle Detection
    4. Topological Sort
    5. Graph Coloring
    6. Indegrees

Relationship between teh edges. Neighbors of a node. We can usually represent a graph with an adjacency list or adjacency matrix.
              3
             / 
            2
           /  \
          1     4
        
Adjacency list 
        Key Value
        1   [2]
        2   [1,3,4]
        3   [2]
        4   [2]
Adjacency Matrix - 2D array. We mark all the spots where 2 nodes are connected and populate the matrix with all the edges. 
It is east to lookup all the nods neighbors.
        1   2   3   4
    1       T
    2   T       T   T
    3       T
    4       T

A big chunk of work within the graph problem is setting up the  Adjacency List or the Matrix. Once you do that, you just have to 
iterate thru the graph using DFS or BFS, and keep track of everything you have seen using some sort of a visited array. 

Setting up the Adjacency list or Matrix
Visited Array

1. Adjacency List
   An Adjacency List is usually a Hashmap, or a List of Lists
2. Adjacency Matrix

1. 323. Number of Connected Components in an Undirected Graph
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

2. 207. Course Schedule
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
    Now when we are done visiting, on the way back [downhill] we change it to 1, marking as tghe node as visited and we return true 
    since there were no cycles found.    
    The base cases basically ensure that we stop, when we see a node that we have encountered before. Obviousouly when the node is labeled 0 we should visit it
    if its 1 we checked it in a previous traversal and there are no cycles necessarily.
    However, if its -1, it would mean we encountered the node in the same exploration.
    Now if this were to be a undirected graph, you woujld know if there is a cycle if you pass the parent node in
    Note: This is not a cycle. This can be fixed by passing the parent node as a param to the next node you are visiting.   
            -1      -1
            0   -    1
     
3. Graph Valid Tree
    
     

4. Possible Bi-partition

    This is an undirected graph. If DFS finds two neighboring nodes with the same grouping, then we return false - not a Bi-partite
    
    
      
5. Min Height Tree
    We will solve this using BFS.
    

6. Other Problems 
    Union Find, 
    Bellman
