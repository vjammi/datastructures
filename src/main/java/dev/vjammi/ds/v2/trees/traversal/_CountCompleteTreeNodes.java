package dev.vjammi.ds.v2.trees.traversal;

/**
    222. Count Complete Tree Nodes
    Given the root of a complete binary tree, return the number of the nodes in the tree.
    According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree,
    and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

    *** Design an algorithm that runs in less than O(n) time complexity.

    Input: root = [1,2,3,4,5,6]
    Output: 6

    Input: root = []
    Output: 0

    Input: root = [1]
    Output: 1

    //                    1
    //            2               3
    //        4       5       6       7
    //      8   9   10  11  12   N  N    N

*/
// Note: 12 / 18 test cases passed.
// Ref: https://youtu.be/CvrPf1-flAA
public class _CountCompleteTreeNodes {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;

        //return dfs_naive(root);
        return dfs(root);
    }

    public int dfs(TreeNode node){
        if (node == null)
            return 0;

        int leftDepth  = findLeftDepth(node.left)   + 1;
        int rightDepth = findRightDepth(node.right) + 1;

        if (leftDepth == rightDepth){
            return (int) Math.pow(2, leftDepth) - 1;
        }

        int leftCount = dfs(node.left);
        int rightCount = dfs(node.right);

        return leftCount + rightCount + 1;
    }

    private int findLeftDepth(TreeNode node) {
        if (node == null)
            return 0;

        int count = findLeftDepth(node.left);

        return count + 1;
    }

    private int findRightDepth(TreeNode node) {
        if (node == null)
            return 0;

        int count = findLeftDepth(node.right);

        return count + 1;
    }

    public int dfs_naive(TreeNode node) {
        if (node == null)
            return 0;

        int leftCount = dfs_naive(node.left);
        int rightCount = dfs_naive(node.right);

        return leftCount + rightCount + 1;
    }
}
