package io.dev.v2.trees.path;

/**
    Given the root of a binary tree, return the number of uni-value subtrees.
    A uni-value subtree means all nodes of the subtree have the same value.
    Input: root = [5,1,5,5,5,null,5]
    Output: 4
                5
            1       5
         5    5       5
*/

// 192 / 200 test cases pass
public class CountUnivalueSubtrees {

    private int uniValue;

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

    public int countUnivalSubtrees(TreeNode root) {
        univalSubtrees(root);
        return uniValue;
    }

    public int univalSubtrees(TreeNode node) {

        if (node == null)
            return -1;

        if (node.left == null && node.right == null){ // All child nodes are uni valued nodes.
            uniValue++;
            return node.val;
        }

        int leftVal  = univalSubtrees(node.left);
        int rightVal = univalSubtrees(node.right);

        if (node.val == leftVal && node.val == rightVal)
            uniValue++; // increment, when node val is the same as both its children
        else if ( (leftVal == -1 && node.val == rightVal) || (rightVal == -1 && node.val == leftVal) )
                uniValue++; // increment, when node val is the same as one of its child and the other is null

        return node.val;
    }
}
