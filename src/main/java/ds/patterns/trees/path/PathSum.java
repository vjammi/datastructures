package ds.patterns.trees.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 112. Path Sum
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * A leaf is a node with no children.
 *
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * Explanation: The root-to-leaf path with the target sum is shown.
 * */
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
        return dfs(root, targetSum);

        //    Stack<Integer> stack = new Stack<Integer>();
        //    List<List<Integer>> result = new ArrayList<>();
        //    boolean hasPath = dfsDebug(root, targetSum, stack, result);
        //    System.out.println("Final Result " +result);
        //    return hasPath;
    }

    public boolean dfs(TreeNode node, int targetSum) {
        if (node == null)
            return false;

        if (node.left == null && node.right == null && (targetSum - node.val) == 0 )
            return true;

        boolean leftTrue = dfs(node.left,  targetSum-node.val);
        boolean rightTrue = dfs(node.right, targetSum-node.val);

        if (leftTrue || rightTrue)
            return true;

        return false;
    }


    public boolean dfsDebug(TreeNode node, int targetSum, Stack<Integer> chosen, List<List<Integer>> result) {
        if (node == null){
            return false;
        }

        chosen.push(node.val);

        if (node.left == null && node.right == null && (targetSum - node.val) == 0 ){
            result.add(new ArrayList(chosen));
            System.out.println("Chosen " +chosen);
            System.out.println("Result " +result);
            return true;
        }

        boolean leftTrue = dfsDebug(node.left,  targetSum-node.val, chosen, result);
        boolean rightTrue = dfsDebug(node.right, targetSum-node.val, chosen, result);

        chosen.pop();

        if (leftTrue || rightTrue)
            return true;

        return false;
    }

}
