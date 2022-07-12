package ds.patterns.graph;

import java.util.*;

// References: https://youtu.be/eL-KzMXSXXI & https://youtu.be/gDNm1m3G4wo?list=PLFj4kIJmwGu0yzwN3qZmw6p3E3zpoHpZz
public class TopologicalSort {

    Map<Integer, List<Integer>> adjList = new HashMap<>();

    private List<Integer> topologicalSort(int vertices, int[][] edges) {
        for (int i=0; i< vertices; i++){
            adjList.put(i, new LinkedList());
        }

        for(int i=0; i<edges.length; i++){
            int[] edge = edges[i];
            adjList.get(edge[0]).add(edge[1]); // Topological is a DAG - Directed with no cycles
        }

        ArrayList<Integer> result = new ArrayList<>();
        int[] visited = new int[vertices];

        for (int i=0; i<adjList.size(); i++) { // TODO: adjList.size() / v ?
            if (visited[i] != 1) dfs(0, adjList, visited, result);
        }

        System.out.println(result);
        return result;
    }

    private void dfs(int vertex, Map<Integer, List<Integer>> adjList, int[] visited, List<Integer> result) {
        if (visited[vertex] == 1)
            return;

        visited[vertex] = 1;
        List<Integer> neighbors = adjList.get(vertex);
        for (Integer neighbor: neighbors){
            dfs(neighbor, adjList, visited, result);
        }
        result.add(0, vertex); // Add the vertices in the reverse order.
    }
    /**
          0 -  1  -  3
            \  |  /
               2  -  4

        [0, 1, 3, 2, 4]
        [0, 1, 2, 4, 3]

     */
    public static void main(String[] args) {
        int vertices = 6;
        int[][] edges = {{0,1}, {0,2}, {1,2}, {1,3}, {2,2}, {2,4}};
        TopologicalSort graph = new TopologicalSort();
        graph.topologicalSort(vertices, edges);
    }
}
