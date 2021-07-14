package ds.patterns.trees.traversal;

/**
https://leetcode.com/problems/symmetric-tree/
01. Symmetric Tree
    Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
    Input: root = [1,2,2,3,4,4,3]
    Output: true
    Input: root = [1,2,2,null,3,null,3]
    Output: false
*/

public class SymmetricTree {

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

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return symmetric(root.left, root.right);
    }

    public boolean symmetric(TreeNode a, TreeNode b) {
        if (a == null && b == null)
            return true;

        // Are vals same?
        if ( (a != null && b!=null && a.val == b.val) ) {
            boolean left  = symmetric(a.left, b.right);
            boolean right = symmetric(a.right, b.left);

            // Are they structurally the same?
            if (left && right)
                return true;
            else
                return false;
        }else{
            return false;
        }
    }
}
