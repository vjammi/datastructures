package ds.patterns.trees.traversal;
/**
    https://leetcode.com/problems/invert-binary-tree/
    226. Invert Binary Tree
    Given the root of a binary tree, invert the tree, and return its root.

    //    Input: root = [4,2,7,1,3,6,9]
    //                4
    //            2      7
    //          1  3   6   9
    //
    //    Output: [4,7,2,9,6,3,1]
    //                4
    //            7       2
    //          9   6   3   1

     Input: root = [2,1,3]
        Output: [2,3,1]
        Input: root = []
        Output: []
*/
public class InvertBinaryTree {

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

    //         [4,2,7,1,3,6,9]
    //                4
    //            2      7
    //          1  3   6   9

    //         [4,7,2,9,6,3,1]
    //                4
    //            7       2
    //          9   6   3   1

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;

        invert(root);
        return root;
    }

    public void invert(TreeNode node){
        if (node == null)
            return;

        swapNodes(node);    // Inverted at pre-order
        invert(node.left);
        invert(node.right);
        //swapNodes(node);  // Can also be inverted in post-order
    }

    public void swapNodes(TreeNode node){
        TreeNode safe = node.left;
        node.left = node.right;
        node.right = safe;
    }
}
