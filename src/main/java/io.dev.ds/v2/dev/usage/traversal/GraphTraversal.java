package dev.vjammi.ds.v2.dev.usage.traversal;

import java.util.*;

public class GraphTraversal {

    private void graphUsage() {
        // Input
        int n = 5;
        int[][] edges = new int[5][2];

        // Declare adjList
        Map<Integer, List<Integer>> adjList = new HashMap();

        // Initialize adjList
        for (int i=0; i<n ; i++){
            adjList.put(i, new ArrayList<Integer>());
        }

        // Build adjList
        for (int i = 0; i<edges.length; i++){
            int[] edge = edges[i];
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        // Iterate over the adjList
        int[] visited = new int[n];
        for (int i=0; i< adjList.size(); i++){
            dfs_graph(0, adjList, new Stack<Integer>(), visited);
        }

        // return result
        // ???
        //adjList.get(child).remove(current);
    }

    private void dfs_graph(int vertex, Map<Integer, List<Integer>> adjList, Stack<Integer> stack, int[] visited) {
        if (visited[vertex] == 1) return;

        // Iterate over a vertex and its neighbors
        visited[vertex] = -1;
        List<Integer> neighbors = adjList.get(vertex);
        for (int i=0; i<neighbors.size(); i++){
            stack.push(neighbors.get(i));
            dfs_graph(vertex, adjList, stack, visited);
            stack.pop();
        }
        visited[vertex] = 1;
    }

    public static void main(String[] args) {
        GraphTraversal obj = new GraphTraversal();
        obj.graphUsage();
    }
}
