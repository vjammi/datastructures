package ds.collections;

import java.util.*;

public class CommonJavaCollectionUsage {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode buildTree() {
        TreeNode root = new TreeNode();
        root.val = 16;

        TreeNode left = new TreeNode();
        root.left = left;
        root.val = 10;

        TreeNode right = new TreeNode();
        root.right = right;
        root.val = 22;
        return root;
    }

    private void main() {
        stringBuilderUsage();
        graphUsage();
        treeUsage();
    }

    private StringBuilder stringBuilderUsage() {
        StringBuilder sb = new StringBuilder();
        String chosen = "Some Choice";

        sb.append(chosen);
        //dfs (...)
        sb.deleteCharAt(sb.length()-1);

        return sb;
    }

    private void treeUsage() {
        dfs_tree(buildTree(), new StringBuilder(), "");
    }

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

    private void dfs_tree(TreeNode node, StringBuilder tmpResult, String str) {
        if (node == null)
            return;

        tmpResult.append(node.val);
        str = str + node.val;

        dfs_tree(node.left, tmpResult, str);
        tmpResult.deleteCharAt(tmpResult.length() - 1);
        str = str.substring(0, str.length());

        dfs_tree(node.right, tmpResult, str);
        tmpResult.deleteCharAt(tmpResult.length() - 1);
        str = str.substring(0, str.length());

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
        CommonJavaCollectionUsage obj = new CommonJavaCollectionUsage();
        obj.main();
    }
}
