package io.dev.v2.trees.bst;

/**
 * 230. Kth Smallest Element in a BST
 *
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

 * Example 1:
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * */
public class KthSmallestElementInABst {

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

    public int kthSmallest(TreeNode root, int k) {
        return dfs(root, k);
        //return kthSmallestElement;
    }

    //int kthSmallestElement = -1;
    int count = 0;
    private int dfs(TreeNode node, int k){
        if (node == null)
            return -1;

        int left = dfs(node.left,  k);
        count++;
        if (count == k){
            //kthSmallestElement = node.val;
            System.out.println("kthSmallestElement "+ node.val);
            return node.val;
        }

        int right = dfs(node.right, k);

        if (left != -1)
            return left;
        else return right;
    }

}
