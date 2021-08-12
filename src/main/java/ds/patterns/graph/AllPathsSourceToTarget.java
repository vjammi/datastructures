package ds.patterns.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

}
