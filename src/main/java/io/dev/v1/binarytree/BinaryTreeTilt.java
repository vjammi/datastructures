package io.dev.v1.binarytree;

/*
 563. Binary Tree Tilt
 Absolute difference between all the nodes in the left and right subtree.

 Given the root of a binary tree, return the sum of every tree node's tilt.
 The tilt of a tree node is the absolute difference between the sum of all left subtree node values and all right subtree node values. If a node does not have a left child, then the sum of the left subtree node values is treated as 0. The rule is similar if there the node does not have a right child.

 Solution
 This problem requires you to bubble up multiple intermediate calculations to its parent
   1. Calculate the Tilt of the two children [abs(left-right)]
      1.1 Set the calculated tilt value as the new value of the node
      1.2 Add the current tilt to the total running total for the tree [totalTilt = totalTilt + tilt]
   2. Calculate the sum of all the values of the subtree, and return to its parent [subTreeSum = node.val + left + right]

 Complexity Analysis
    Let N be the number of nodes in the input tree.
    Time Complexity: O(N)
    We traverse each node once and only once. During the traversal, we calculate the tilt value for each node.

    Space Complexity: O(N)
    Although the variables that we used in the algorithm are of constant-size, we applied recursion in the algorithm which incurs additional memory consumption in function call stack.
    In the worst case where the tree is not well balanced, the recursion could pile up N times. As a result, the space complexity of the algorithm is O(N).
*/
public class BinaryTreeTilt {

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

    public int dfs(TreeNode node) {
        if (node == null)
            return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        int tilt = Math.abs(left - right);
        totalTilt = totalTilt + tilt;

        int subTreeSum = node.val + left + right;
        node.val = tilt;

        return subTreeSum;
    }
    int totalTilt = 0;

    public int findTilt(TreeNode root) {
        if (root == null)
            return 0;

        dfs(root);

        return totalTilt;
    }
}
