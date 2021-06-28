package ds.graph.traversal;

// BFS algorithm in Java

import java.util.*;

public class GraphTraversals {

    private int vertices;
    private List<Integer>[] adj;
    private boolean[] visited;

    //    // Create a graph
    //    GraphTraversals(int v) {
    //        V = v;
    //        adj = new LinkedList[v];
    //        for (int i = 0; i < v; ++i)
    //            adj[i] = new LinkedList();
    //    }

    // Graph creation
    GraphTraversals(int vertices) {
        if (vertices < 0)
            throw new IllegalArgumentException("Vertices need to be >= 0");

        this.vertices = vertices;

        adj = new LinkedList[vertices];
        visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++)
            adj[i] = new LinkedList<>();
    }


    // Add edges to the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // BFS algorithm
    void bfs(int vertex) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        visited[vertex] = true;
        queue.add(vertex);
        while (queue.size() != 0) {

            vertex = queue.poll();
            System.out.print(vertex + " ");
            Iterator<Integer> i = adj[vertex].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }

        }
    }

    // DFS algorithm
    void dfs(int vertex) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        Iterator<Integer> iterator = adj[vertex].listIterator();
        while (iterator.hasNext()) {
            int adj = iterator.next();
            if (!visited[adj])
                dfs(adj);
        }
    }

    public static void main(String[] args) {
        GraphTraversals g = new GraphTraversals(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        //g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");
        g.bfs(2);

        System.out.println("Following is Depth First Traversal " + "(starting from vertex 2)");
        g.dfs(2);
    }
}
