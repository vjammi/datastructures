package ds.practice.graphs;

import java.util.*;

public class GraphTraversal {
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
        for (Map.Entry entry: adjList.entrySet()){
            System.out.println("Key(vertex) " +entry.getKey() +" Value(neighbors) " +entry.getValue());
        }

        int[] visited = new int[vertices];
        ArrayList<Integer> path = new ArrayList<>();

        dfs(0, adjList, visited, path); // DFS connected graph

        System.out.println(path);
        return path;
    }

    private void dfs(int vertex, Map<Integer, List<Integer>> adjList, int[] visited, List<Integer> path) {
        if (visited[vertex] == 1)
            return;

        System.out.print(vertex +" ");
        path.add(vertex);

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
        GraphTraversal graph = new GraphTraversal();
        graph.traverse(vertices, edges);
    }
}
