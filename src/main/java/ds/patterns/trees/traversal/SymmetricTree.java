package ds.patterns.trees.traversal;

/**
https://leetcode.com/problems/symmetric-tree/
101. Symmetric Tree
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
        if (root == null) return true;
        return symmetric(root.left, root.right);
    }

    private boolean symmetric(TreeNode leftNode, TreeNode rightNode){
        if ( leftNode == null && rightNode == null)
            return true;

        if ( ( leftNode == null && rightNode != null) || ( leftNode != null && rightNode == null) )
            return false;

        if ( (leftNode != null && rightNode != null) && (leftNode.val != rightNode.val) )
            return false;

        boolean left  = symmetric(leftNode.left, rightNode.right);
        boolean right = symmetric(leftNode.right, rightNode.left);

        if (left && right)
            return true;
        else
            return false;
    }

    public boolean symmetric2(TreeNode a, TreeNode b) {
        if ( (a == null && b != null) || (a != null && b == null) ) // a or b is null
            return false;

        if (a == null && b == null)     // base case
            return true;

        if (a != null && b !=null && a.val != b.val)  // a and b are not null but they are not symmetric
            return false;

        boolean left  = symmetric2(a.left, b.right);
        boolean right = symmetric2(a.right,b.left);

        if (left == false || right == false)    // either left or right returns false
            return false;

        return true; // both left or right return true
    }


}
