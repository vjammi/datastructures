package ds.graph.representation;

import java.util.*;

// This class represents a directed graph using adjacency list representation
class DirectedGraph {

    private int numberOfVertices; // No. of vertices
    private LinkedList<Integer>[] adj; // Array of lists for Adjacency List Representation

    DirectedGraph(int numberOfVertices)  {
        this.numberOfVertices = numberOfVertices;
        initializeAdjancyList(numberOfVertices);
    }

    private void initializeAdjancyList(int v) {
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w)    {
        // The function to do DFS traversal. It uses recursive DFSUtil()
        adj[v].add(w); // Add w to v's list.
    }

    // Graph Traversal
    void traverseGraphInDFS(int v)    {
        // Mark all the vertices as not visited (set as false by default in java)
        boolean visited[] = new boolean[numberOfVertices];
        // Call the recursive helper function to print DFS traversal
        traverseGraphInDFS(v, visited);
        System.out.println("\n");
    }

    private void traverseGraphInBFS(int v) {
        int[] path = bfsTraversal(v);
        System.out.println("\n");
        for(int vertex: path){
            System.out.print(vertex +" ");
        }
    }

    private int[] bfsTraversal(int v) {
        int[] path = new int[numberOfVertices];

        boolean visited[] = new boolean[numberOfVertices];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while(!queue.isEmpty()) {
            Integer element = queue.peek();
            visited[element] = true;
            System.out.print(element + " ");

            int i = 0;
            List<Integer> vertices = adj[element];
            for (Integer vertex : vertices) {
                if (!visited[vertex]) {
                    queue.add(vertex);
                    visited[vertex] = true;
                    path[i] = vertex;
                    i++;
                }
            }
            queue.poll();
        }
        return path;
    }

    // A function used by DFS
    void traverseGraphInDFS(int v, boolean visited[])    {
        // Mark the current node as visited and print it
        visited[v] = true;  System.out.print(v + " ");

        // Recursively traverse all the vertices adjacent to this vertex
        Iterator<Integer> iterator = adj[v].listIterator();
        while (iterator.hasNext())  {
            int n = iterator.next();
            if (!visited[n]) {
                traverseGraphInDFS(n, visited);
            }
        }
    }

    /**
                  1 - 4 - 7
               /  |          \
             0  - 2 - 5 - 8 - 10
               \             /
                  3 - 6 - 9

            0 1 2 5 8 10   4 7  3 6 9

               /  1 - 4 - 7  \
             /
             0  - 2 - 5 - 8 - 10
             \
               \  3 - 6 - 9 /

           0 1 4 7 10 2 5 8 3 6 9

     */
    public static void main(String args[])    {

        // Initialize the Graph of N Nodes or V Vertices - O(N) / O(V)
        DirectedGraph g = new DirectedGraph(11);

        // Populate the E Edges - O(E)
        g.addEdge(0, 1);  g.addEdge(0, 2);  g.addEdge(0, 3);
        g.addEdge(1, 4);  g.addEdge(2, 5);  g.addEdge(3, 6);
        g.addEdge(4, 7);  g.addEdge(5, 8);  g.addEdge(6, 9);
        g.addEdge(7, 10); g.addEdge(8, 10); g.addEdge(9, 10);

        System.out.println("Following is Depth First Traversal "+ "(starting from vertex 0)");
        g.traverseGraphInDFS(0); // Traverse starting node 0

        System.out.println("Following is Depth First Traversal "+ "(starting from vertex 0)");
        g.traverseGraphInBFS(0); // Traverse starting node 0

    }
}

