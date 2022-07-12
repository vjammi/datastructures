package ds.patterns.graph;

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

        ArrayList<Integer> chosen = new ArrayList<>();
        int[] visited1 = new int[vertices];
        dfsNeedsWork(0, adjList, visited1, chosen); // Connected graph
        System.out.println(chosen);
    }

    private void dfsNeedsWork(int vertex, Map<Integer, List<Integer>> adjList, int[] visited, List<Integer> path) {
        if (visited[vertex] == path.size()) {
            System.out.println(path);
            return;
        }

        path.add(vertex); System.out.print(vertex +" ");

        visited[vertex] = 1;
        List<Integer>  neighbors = adjList.get(vertex);
        for(int neighbor: neighbors){
            if (visited[neighbor] == 0){
                dfsNeedsWork(neighbor, adjList, visited, path);
            }
        }
    }

    //private void dfsAllPaths(int vertex, int target, Map<Integer, List<Integer>> adjList, List<Integer> chosen, List<List<Integer>> result) {
    //    chosen.add(vertex);
    //    if (vertex == adjList.size()) { // or vertex == target
    //        result.add(new ArrayList<Integer>(chosen)); System.out.println(new ArrayList<Integer>(chosen));
    //        return;
    //    }
    //
    //    List<Integer>  neighbors = adjList.get(vertex);
    //    for(int neighbor: neighbors){
    //        dfsAllPaths(neighbor, target, adjList, chosen, result);
    //        chosen.remove(chosen.size() - 1);
    //    }
    //}

    //      /  1 -  3 \
    //    0       /    5
    //      \  2 -  4 /

    public static void main(String[] args) {
        int vertices = 6;
        int[][] edges = {{0,1}, {0,2}, {1,3}, {2,3}, {2,4}, {4,5}, {3,5}};
        Graph101 graph = new Graph101();
        graph.traverse(vertices, edges);
    }
}
