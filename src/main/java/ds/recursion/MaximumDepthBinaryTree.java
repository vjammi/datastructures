package ds.recursion;

public class MaximumDepthBinaryTree {

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

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        depthTopDown(root, 0);
        //return depthBottomUp(root);

        return maxDepth;

    }

    //Input: root = [3,9,20,null,null,15,7]
    //    3                 0
    // 9    20          1      1
    //    15   7             2    2
    int maxDepth = 0;
    public void depthTopDown(TreeNode node, int currentDepth) {
        if (node == null)
            return;

        if (node.left == null && node.right == null)
            maxDepth = Math.max(currentDepth+1, maxDepth);

        depthTopDown(node.left, currentDepth+1);
        depthTopDown(node.right, currentDepth+1);
    }

    private int depthBottomUp(TreeNode node){
        if (node == null)
            return 0;

        int leftDepth  = depthBottomUp(node.left);
        int rightDepth = depthBottomUp(node.right);

        int depthMax = Math.max(leftDepth, rightDepth) + 1;
        System.out.println(depthMax);

        return depthMax;
    }

}
