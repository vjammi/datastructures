package ds.practice.graphs;

import java.util.*;

public class Graph101 {
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
        for (Map.Entry<Integer, List<Integer>> entry: adjList.entrySet()){
            Integer key          = entry.getKey();
            List<Integer>  value = entry.getValue();
            System.out.println("Key(vertex) " + key +" Value(neighbors) " + value);
        }

        System.out.println("--------------------dfs--------------------------");
        ArrayList<Integer> chosen1 = new ArrayList<>();
        int[] visited1 = new int[vertices];
        dfs(0, adjList, visited1, chosen1); // Connected graph
        System.out.println(chosen1);

        System.out.println("------------------dfsAllPaths----------------------");
        ArrayList<Integer> chosen2 = new ArrayList<>();
        int[] visited2 = new int[vertices];
        chosen2.add(0);
        dfsAllPaths(0, adjList, visited2, chosen2, new ArrayList());
    }

    private void dfs(int vertex, Map<Integer, List<Integer>> adjList, int[] visited, List<Integer> path) {
        if (visited[vertex] == 1) {
            return;
        }

        path.add(vertex); System.out.print(vertex +" ");

        visited[vertex] = 1;
        List<Integer>  neighbors = adjList.get(vertex);
        for(int neighbor: neighbors){
            if (visited[neighbor] == 0){
                dfs(neighbor, adjList, visited, path);
            }
        }
    }

    private void dfsAllPaths(int vertex, Map<Integer, List<Integer>> adjList, int[] visited, /*Stack<Integer>*/ List<Integer> chosen, List<List<Integer>> result) {
        if (chosen.size() == adjList.size()) {
            result.add(new ArrayList(chosen)); System.out.println(chosen);
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

        /**
              /  1 -  3 \
            0       /    5
              \  2 -  4 /
        */
    public static void main(String[] args) {
        int vertices = 6;
        int[][] edges = {{0,1}, {0,2}, {1,3}, {2,3}, {2,4}, {4,5}, {3,5}};
        Graph101 graph = new Graph101();
        graph.traverse(vertices, edges);
    }
}
