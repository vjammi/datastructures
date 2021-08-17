package ds.patterns.trees.path;


// 115 out of 116 Tests Cases pass
public class PathSum {

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

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;

        if (targetSum == 0 && root !=null)
            return false;

        return pathSum(root, targetSum);
    }

    boolean pathSum(TreeNode node, int sum){
        if (node == null)   // We have already checked if sum == 0 before it gets null and have returned.
            return false;

        sum = sum - node.val;
        if (node!=null && node.left == null && node.right == null && sum == 0)
            return true;

        boolean left  = pathSum(node.left, sum);
        boolean right = pathSum(node.right, sum);

        if(left || right)
            return true;
        else
            return false;
    }
}
