package dev.vjammi.ds.v2.trees.path;

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

        pathSum(root, 0, stack, "", new StringBuilder(), result);

        return result;
    }

    /**
                1
             2      5
          3     4  null  null
    */
    public void pathSum(TreeNode node, int sum, Stack<Integer> stack, String str, StringBuilder strBuilder, List<List<Integer>> result) {

        if (node == null)
            return;

        // Note: The stack reference is being passed as a value to the next stack frame, which has an effect similar to pass by reference.
        // which will need to be later popped on the way back.

        // Push node into the stack
        stack.push(node.val);

        // Add the node value to the sum
        // The current sum is being passed as value to the next call stack. On the way back we still have the same value at this node.
        sum = sum + node.val;
        str = str +node.val;
        strBuilder = strBuilder.append(node.val);

        // Check if the current node is a leaf node. l & r nodes are nulls. Check if the sum so far is equal to the target we are looking for
        if (node.left == null && node.right==null && sum == targetSum)
            result.add(new ArrayList(stack));

        pathSum(node.left, sum, stack, str, strBuilder, result);
        pathSum(node.right, sum, stack, str, strBuilder, result);

        show(stack); show(sum, str, strBuilder);

        // *** Backtracking for Primitives vs NonPrimitives ***
        // While backtracking we pop the node or nodeValue out of the stack/stringBuilder/list object but not if we were using primitives.
        // The values for primitives are being copied* [pass by value] up the call stack.
        // We do not remove the last added char from the sum or str variables.

        // We only delete/remove the last added char from the strBuilder and stack objects.
        // Here the references for non-primitives are being copied [pass by value] up the call stack, instead values being copied and sent up the call stack.
        // This results in an effect similar to call by reference.

        // To backtrack we pop the node out of the stack/string builder/list
        stack.pop();
        strBuilder.deleteCharAt(strBuilder.length()-1);
    }

    private void show(Stack<Integer> stack) {
        stack.stream().forEach(System.out::print);
    }

    private void show(int sum, String str, StringBuilder strBuilder) {
        System.out.println(" | " + sum +" | "+ str +" | "+ strBuilder);
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


