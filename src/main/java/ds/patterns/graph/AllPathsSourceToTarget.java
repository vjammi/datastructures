package ds.patterns.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
    Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
    The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).

    Example 1:
    Input: graph = [[1,2],[3],[3],[]]
    Output: [[0,1,3],[0,2,3]]
    Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
*/

public class AllPathsSourceToTarget {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        List<List<Integer>> result = new ArrayList();
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.length];

        stack.push(0);
        dfs(graph, 0, visited, stack, result);
        System.out.println(result);

        return result;
    }

    private void dfs(int[][] graph, int v, boolean[] visited, Stack<Integer> stack, List<List<Integer>> result) {

        if (v == graph.length-1){
            result.add(new ArrayList(stack));
            return;
        }

        visited[v] = true;
        int[] neighbors = graph[v];
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                stack.push(neighbor);
                dfs(graph, neighbor, visited, stack, result);
                stack.pop();
            }
        }
        visited[v] = false;
    }

    public static void main(String[] args) {
        AllPathsSourceToTarget obj = new AllPathsSourceToTarget();
        int[][] graph = {{1,2},{3},{3},{}};
        obj.allPathsSourceTarget(graph);
    }
}
