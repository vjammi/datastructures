package io.dev.v2.trees.traversal;
// 104. Maximum Depth of Binary Tree
public class BinaryTreeDepthAndHeight {

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

    // A binary tree's maximum depth is the number of nodes along the longest path from the
    // root node down to the farthest leaf node.
    public int maxDepth(TreeNode root) {
        dfs(root, 1);
        return maxDepth;
    }
    int maxDepth = 0;
    public void dfs(TreeNode node, int depth){
        if (node == null)
            return;

        if (node.left == null && node.right == null) {
            maxDepth = Math.max(maxDepth, depth);
        }
        dfs(node.left,  depth+1);
        dfs(node.right, depth+1);
    }

    public int maxHeight(TreeNode root){
        if (root == null)
            return 0;
        int heightBottomUp = height(root);
        System.out.println("heightBottomUp " + heightBottomUp);
        return heightBottomUp;
    }

    //Input: root = [3,9,20,null,null,15,7]
    //                3                 4
    //             9    20              3
    //                15   7            2
    //                       8          1
    //                   null  null     0
    private int height(TreeNode node){
        if (node == null)
            return 0;

        int leftHeight  = height(node.left)  + 1; // Adding 1 for the current depth to the left subtree
        int rightHeight = height(node.right) + 1; // Adding 1 for the current depth to the right subtree

        int maxHeightSeenSoFar = Math.max(leftHeight, rightHeight);
        System.out.println(maxHeightSeenSoFar);

        return maxHeightSeenSoFar;
    }


}
