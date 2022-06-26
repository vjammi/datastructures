package ds.patterns.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    /**
    323. Number of Connected Components in an Undirected Graph
    You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
    Return the number of connected components in the graph.

    Example 1:
    Input: n = 5, edges = [[0,1],[1,2],[3,4]]
    Output: 2

    Example 2:
    Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
    Output: 1

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
    */
public class NumOfConnectedComponents {

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

            List<Integer> list1 = adjList.get(edge[0]);
            list1.add(edge[1]);
            adjList.put(edge[0], adjList.get(edge[0]));

            List<Integer> list2 = adjList.get(edge[1]);
            list2.add(edge[0]);
            adjList.put(edge[1], list2);
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
            dfs(neighbor, adjList, visited);
        }
    }

    public static void main(String[] args) {
        NumOfConnectedComponents obj = new NumOfConnectedComponents();
        //int[][] edges = {{0,1},{1,2},{2,3},{3,4}};
        int[][] edges = {{0,1},{1,2},{3,4}};
        System.out.println(obj.countComponents(5, edges));
    }
}
