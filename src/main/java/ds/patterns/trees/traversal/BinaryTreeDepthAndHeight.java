package ds.patterns.trees.traversal;

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
        depthTopDown(root, 1); // Note: Depth of the root node is 1

        return maxDepth;
    }

    public int maxHeight(TreeNode root){
        if (root == null)
            return 0;
        int heightBottomUp = heightBottomUp(root);
        System.out.println("heightBottomUp " + heightBottomUp);
        return heightBottomUp;
    }

    //Input: root = [3,9,20,null,null,15,7]
    //    3                 1
    // 9    20          2      2
    //    15   7             3    3
    int maxDepth = 0;
    public void depthTopDown(TreeNode node, int currentDepth) {
        if (node == null)
            return;

        // If left and right nodes are null, you know its a leaf node, lets get the depth at this level and compare to the maxDepth
        if (node.left == null && node.right == null)
            maxDepth = Math.max(currentDepth, maxDepth);

        depthTopDown(node.left, currentDepth+1);
        depthTopDown(node.right, currentDepth+1);
    }

    //Input: root = [3,9,20,null,null,15,7]
    //    3                 4
    // 9    20              3
    //    15   7            2
    //           8          1
    //       null  null     0
    private int heightBottomUp(TreeNode node){
        if (node == null)
            return 0;

        int leftDepth  = heightBottomUp(node.left);
        int rightDepth = heightBottomUp(node.right);

        int depthMax = Math.max(leftDepth, rightDepth) + 1;
        System.out.println(depthMax);
        return depthMax;
    }


}
