package dev.vjammi.ds.v2.graph;

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
        2 -> 3
        ^    ^
        |    |
        0 -> 1
*/

public class AllPathsSourceToTarget {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        List<List<Integer>> result = new ArrayList();
        Stack<Integer> stack = new Stack<>();

        stack.push(0);
        dfs(0, graph, stack, result);
        System.out.println(result);

        return result;
    }

    private void dfs(int v, int[][] edges, Stack<Integer> stack, List<List<Integer>> result) {
        // target node = n-1 or edges.length-1
        if (v == edges.length-1){
            result.add(new ArrayList<>(stack));
            return;
        }

        int[] neighbors = edges[v];
        for (int neighbor : neighbors) {
            stack.push(neighbor);
            dfs(neighbor, edges, stack, result);
            stack.pop();
        }
    }

    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        List<List<Integer>> result = new ArrayList();

        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs2(0, graph, path, result);
        System.out.println(result);

        return result;
    }


    private void dfs2(int v, int[][] edges, List<Integer> path, List<List<Integer>> result) {

        //path.add(v);
        // target node = n-1 or edges.length-1
        if (v == edges.length-1){
            System.out.println(">"+v);
            result.add(new ArrayList(path));
            return;
        }

        int[] neighbors = edges[v];
        for (int neighbor : neighbors) {
            path.add(neighbor);
            dfs2(neighbor, edges, path, result);
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        AllPathsSourceToTarget obj = new AllPathsSourceToTarget();
        /*
                2 -> 3
                ^    ^
                |    |
                0 -> 1
        **/
        int[][] graph = {{1,2},     // 0
                         {3},       // 1
                         {3},       // 2
                         {}};       // 3

        obj.allPathsSourceTarget(graph);
        obj.allPathsSourceTarget2(graph);
    }
}
