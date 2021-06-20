package ds.patterns.graph;

import java.util.*;

/**
    You have a graph of n nodes labeled from 0 to n - 1.
    You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
    Return true if the edges of the given graph make up a valid tree, and false otherwise.

        Example 1:
        Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
        Output: true

        Example 2:
        Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
        Output: false

    Constraints:
         1 <= 2000 <= n
         0 <= edges.length <= 5000
         edges[i].length == 2
         0 <= ai, bi < n
         ai != bi
         There are no self-loops or repeated edges.

    Solution
     Approach 1: Graph Theory + Iterative Depth-First Search

     Intuition
     Note that this same approach also works with recursive depth-first search and iterative breadth-first search. We'll look at these briefly in the Algorithm section.
     Recall that a graph, G, is a tree iff the following two conditions are met:

     G is fully connected. In other words, for every pair of nodes in G, there is a path between them.
     G contains no cycles. In other words, there is exactly one path between each pair of nodes in G.
     Depth-first search is a classic graph-traversal algorithm that can be used to check for both of these conditions:

     G is fully connected if, and only if, we started a depth-first search from a single source and discovered all nodes in G during it.
     G contains no cycles if, and only if, the depth-first search never goes back to an already discovered node. We need to be careful though not to count trivial cycles of the form A → B → A that occur with most implementations of undirected edges.
     Depth-first search requires being able to look up the adjacent (immediate neighbours) of a given node. Like many graph interview problems though, the input format we're given doesn't allow us to quickly get the neighbours of a node. Therefore, our first step is to convert the input into an adjacency list. Recall that an adjacency list is where we have a list of sub-lists, where each sub-list is the list of the immediate neighbours for the i'th node.
 */

public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {

        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int i=0; i<n; i++){
            adjList.put(i, new ArrayList<Integer>());
        }

        for (int i=0; i<edges.length; i++){
            int[] edge = edges[i];

            List<Integer> list = adjList.get(edge[0]);
            list.add(edge[1]);
            adjList.put(edge[0], list);

            // *** To avoid going back to the parent use parent != child check  for the current node
            List<Integer> list2 = adjList.get(edge[1]);
            list2.add(edge[0]);
            adjList.put(edge[1], list2);
        }

        int[] visited  = new int[n];
        //boolean validTree = dfs(0, adjList, visited, -1);
        //boolean validTree = bfs(0, adjList, visited);
        boolean validTree = bfs2(0, adjList, visited);
        if (!validTree) return false; // if there is an edge from child to parent

        for (int visit: visited){
            if (visit == 0) return false; // if any un-visited nodes, return false
        }

        return true;
    }

    private boolean dfs(int current, Map<Integer, List<Integer>> adjList, int[] visited, int parent) {
        if (visited[current] == 1){
            System.out.println(current + " " + parent +" " +visited[current] +" < ");
            return false;
        }
        visited[current] = 1; // Mark the node visited
        List<Integer> children = adjList.get(current);
        for (int child: children){
            if (parent != child) { // *** Prevents going back to parent in a self loop (current = 1, parent of current = 0, child of current = 0)
                boolean validTree = dfs(child, adjList, visited, current);
                if (!validTree) return false;
            }
            else{
                // *** current = 1, parent of current = 0, child of current = 0
                System.out.println(child + " " + parent +" " +visited[child] +" ??? ");
            }
        }
        return true;
    }

    public boolean bfs(int i, Map<Integer, List<Integer> > adjList, int[] visited){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);

        while(!queue.isEmpty()){
            Integer current = queue.poll();

            if (visited[current] == 1)
                return false;

            visited[current] = 1;
            List<Integer> children = adjList.get(current);
            for (int child: children){
                queue.add(child);
                adjList.get(child).remove(current);
            }
        }
        return true;
    }

    public boolean bfs2(int i, Map<Integer, List<Integer> > adjList, int[] visited){
        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);

        while(!queue.isEmpty()){
            Integer current = queue.poll();

            if (visited[current] == 1)
                return false;

            visited[current] = 1;
            List<Integer> children = adjList.get(current);
            for (int child: children){
                //*** For current 1, child  0 evaluates to false, so skips over
                // However for current 1, child 4 evaluates to true
                if (parent.get(current) != child) {
                    queue.offer(child);
                    // For current (say 0) we populate the children (1,2,3) in the map.
                    // So for current 0 and child 1,2,3 we have 1:0, 2:0, 3:0 within the map
                    parent.put(child, current);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        GraphValidTree obj = new GraphValidTree();

        int n = 5;
        int[][] edges = { {0,1}, {0,2}, {0,3}, {1,4}};
        //int[][] edges = { {0,1}, {1,2}, {2,3}, {1,3}, {1,4}};
        //int[][] edges = { {0,1}, {2,3}};
        //int[][] edges = { {1,0}};
        System.out.println(obj.validTree(n, edges));
    }

}
