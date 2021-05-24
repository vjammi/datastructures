package ds.patterns.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PathSumII {

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

    private int targetSum;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        Stack<Integer> stack = new Stack();
        List<List<Integer>> result = new ArrayList<>();
        this.targetSum = targetSum;

        if (root == null)
            return result;

        pathSum(root, 0, stack, result);

        return result;
    }

    public void pathSum(TreeNode node, int sum, Stack<Integer> stack, List<List<Integer>> result) {

        if (node ==null)
            return;

        stack.push(node.val);
        sum = sum + node.val;
        if (node.left == null && node.right==null && sum == targetSum){
            result.add(new ArrayList(stack));
        }

        pathSum(node.left, sum, stack, result);
        pathSum(node.right, sum, stack, result);

        stack.pop();
    }

    public static void main(String[] args) {
        PathSumII obj = new PathSumII();
        //obj.pathSum();
    }
}
