package ds.patterns.trees.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
    Path Sum II
    Given the root of a binary tree and an integer targetSum,
    return all root-to-leaf paths where each path's sum equals targetSum.
    A leaf is a node with no children.

    Example 1
                1
             2      5
          3     4
    Input = [1, 2, 5, 3, 4, null, null, null, null]  targetSum: 6
    Output: [[1,2,3]]

    Example 2
                    5
                 4      8
              11     13     4
            7    2       5      1
    Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
    Output: [[5,4,11,2],[5,8,4,5]]

    Intuition
                  /      1
                 /   2      5
                  3     4
                    [1,2,3,null, null] // Since we hit a null to the left of 3, check the sum.
                    [1,2,4,null]       // We need to backtrack, we pop the current node [3] and explore its parents right child.
                                       // Once we hit the a null again to the left and right we check the sum.
                    [1,5,null]         // We pop 2 and 4 and explore the right of the above node. Once we hit the a null again to the left and right we check the sum.

 **/
public class PathSumII {

    private int targetSum;

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

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        Stack<Integer> stack = new Stack();
        List<List<Integer>> result = new ArrayList<>();
        this.targetSum = targetSum;

        if (root == null)
            return result;

        pathSum(root, 0, stack, result);

        return result;
    }

    /**
                1
             2      5
          3     4  null  null
    */
    public void pathSum(TreeNode node, int sum, Stack<Integer> stack, List<List<Integer>> result) {

        if (node == null)
            return;

        // Push node into the stack going uphill
        stack.push(node.val);

        // Add the node value to the sum
        sum = sum + node.val;
        // Check if the the current node is a leaf node. l & r nodes are nulls. if yes check if the sum so far is the target we are looking for
        if (node.left == null && node.right==null && sum == targetSum){
            result.add(new ArrayList(stack));
        }

        pathSum(node.left, sum, stack, result);
        pathSum(node.right, sum, stack, result);

        // To backtrack we need to pop the node out of the stack going downhill
        stack.pop();
        // Ideally you should subtract the node val from the sum, but since we are not returning the sum we do not care.
        sum = sum - node.val;
    }

    private void pathSum() {

        TreeNode  root = new TreeNode();
        root.val = 1;
        TreeNode rootLeft = new TreeNode();
        rootLeft.val  = 2;
        root.left = rootLeft;
        TreeNode rootRight = new TreeNode();
        rootRight.val = 5;
        root.right = rootRight;

        TreeNode rootLeftLeft = new TreeNode();
        rootLeftLeft.val  = 3;
        rootLeft.left = rootLeftLeft;
        TreeNode rootLeftRight = new TreeNode();
        rootLeftRight.val = 4;
        rootLeft.right = rootLeftRight;

        int targetSum = 6;
        List<List<Integer>> lists = pathSum(root, targetSum);
        System.out.println(lists.toString());
    }

    public static void main(String[] args) {
        PathSumII obj = new PathSumII();
        obj.pathSum();
    }
}


