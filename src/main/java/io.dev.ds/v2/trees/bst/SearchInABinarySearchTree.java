package dev.vjammi.ds.v2.trees.bst;

/**
    You are given the root of a binary search tree (BST) and an integer val.
    Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.
        Input: root = [4,2,7,1,3], val = 2
        Output: [2,1,3]
        Input: root = [4,2,7,1,3], val = 5
        Output: []
 */
public class SearchInABinarySearchTree {

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

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return null;

        search(root, val);

        return nodeToReturn;
    }

    TreeNode nodeToReturn = null;
    private TreeNode search(TreeNode node, int val){
        if (node == null)
            return null;

        if (val < node.val){
            node.left =  search(node.left, val);
        }else if (val > node.val) {
            node.right =  search(node.right, val);
        }else if (val == node.val){
            nodeToReturn = node;
            return node;
        }
        return node;
    }

}
