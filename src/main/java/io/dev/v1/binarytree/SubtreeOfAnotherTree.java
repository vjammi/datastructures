package io.dev.v1.binarytree;

/*
572. Subtree of Another Tree
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.

Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true

Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false

Intuition
// Navigate thru the parent tree to see if any node matches the root of the subtree
// if true
//      check if the subtree of the parent is identical to that of the subtree
// else
//      traverse the left & right side nodes from the root of the parent tree to check if the root nodes match

// Implement a helper function to check if the nodes of a subtree are identical.


*/
public class SubtreeOfAnotherTree {

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

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) // Constraint: subRoot to have 1 <= size <= 1000
            return false;

        if (isSame(root, subRoot))
            return true;

        boolean foundInLeft  = isSubtree(root.left, subRoot);
        boolean foundInRight = isSubtree(root.right, subRoot);

        return foundInLeft || foundInRight;
    }


    private boolean isSame(TreeNode node, TreeNode subNode){
        if (node == null && subNode == null)
            return true;

        if ( (node == null && subNode != null) || (node != null && subNode == null) )
            return false;

        if (node.val != subNode.val)
            return false;

        boolean leftSubTreeMatches  = isSame(node.left, subNode.left);
        boolean rightSubTreeMatches = isSame(node.right, subNode.right);

        return leftSubTreeMatches && rightSubTreeMatches;
    }

}
