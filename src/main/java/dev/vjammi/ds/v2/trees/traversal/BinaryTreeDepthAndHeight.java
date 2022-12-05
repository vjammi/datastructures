package dev.vjammi.ds.v2.trees.traversal;
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
        if (root == null)
            return 0;

        depth(root, 0);
        return maxDepthSeenSofar;
    }

    // TODO: Needs to be revisited. Does not look right
    //Input: root = [3,9,20,null,null,15,7]
    //       depth(node, 0)
    //    3                 1                 +1      1
    // 9    20          2      2              +1      2
    //    15   7             3    3           +1      3
    int maxDepthSeenSofar;
    public void depth(TreeNode node, int  currentDepth) {
        if (node == null)
            return;

        // If left and right nodes are null, you know its a leaf node, lets get the depth at this level and compare to the maxDepth
        int depthAtCurrentNode = currentDepth + 1;
        if (node.left == null && node.right == null) {
            maxDepthSeenSofar = Math.max(depthAtCurrentNode, maxDepthSeenSofar);
            System.out.println("depthAtCurrentNode: " +depthAtCurrentNode +" maxDepthSeenSofar "+maxDepthSeenSofar);
        }

        depth(node.left, depthAtCurrentNode);
        depth(node.right, depthAtCurrentNode);
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

        int leftHeight  = height(node.left);
        int rightHeight = height(node.right);

        int maxHeightSeenSoFar = Math.max(leftHeight, rightHeight) + 1; // Adding 1 for the current depth
        System.out.println(maxHeightSeenSoFar);

        return maxHeightSeenSoFar;
    }


}
