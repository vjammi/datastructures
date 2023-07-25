package dev.vjammi.ds.v2.dev.usage.traversal;

import java.util.Stack;

public class TreeTraversal {
    private TreeNode root;

    public TreeTraversal() {
    }

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
        root = new TreeNode();
        root.val = 1;
        TreeNode rootLeft = new TreeNode();
        rootLeft.val  = 2;
        root.left = rootLeft;
        TreeNode rootRight = new TreeNode();
        rootRight.val = 5;
        root.right = rootRight;

        TreeNode rootLeftLeft = new TreeNode();
        rootLeftLeft.val  = 3;
        rootLeft.left = rootLeftLeft;
        TreeNode rootLeftRight = new TreeNode();
        rootLeftRight.val = 4;
        rootLeft.right = rootLeftRight;

        return root;
    }

    private void treeUsage() {
        TreeNode root = buildTree();
        dfs_tree(root, "", new StringBuilder());
    }

    private void dfs_tree(TreeNode node, String str, StringBuilder strBuilder) {
        if (node == null)
            return;

        strBuilder.append(node.val);
        str = str + node.val;

        dfs_tree(node.left, str, strBuilder);
        dfs_tree(node.right, str, strBuilder);

        show(str, strBuilder);

        strBuilder.deleteCharAt(strBuilder.length() - 1);
        str = str; // No effect seen
    }

    private void show(Stack<Integer> stack) {
        stack.stream().forEach(System.out::print);
    }

    private void show(String str, StringBuilder strBuilder) {
        System.out.println(" | "+ str +" | "+ strBuilder);
    }


    private void main() {
        treeUsage();
    }


    public static void main(String[] args) {
        TreeTraversal obj = new TreeTraversal();
        obj.main();
    }
}
