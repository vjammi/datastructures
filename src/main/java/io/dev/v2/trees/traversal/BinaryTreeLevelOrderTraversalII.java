package io.dev.v2.trees.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *  102. Binary Tree Level Order Traversal
    Given the root of a binary tree, return the level order traversal of its nodes' values.
     (i.e., from left to right, level by level).
    Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[9,20],[15,7]]
*/
public class BinaryTreeLevelOrderTraversalII {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        public TreeNode() {
        }
    }

    public List<List<Integer>> levelOrderBottomUp(TreeNode root) {
        List<List<Integer>> result =  new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<Integer> levelStack = null;
        while(!queue.isEmpty()){
            int size = queue.size();
            levelStack = new ArrayList<>();

            while(size > 0){
                TreeNode node = queue.poll();
                if (node != null)
                    levelStack.add(node.val);

                if (node != null && node.left != null)
                    queue.offer(node.left);

                if(node != null && node.right != null)
                    queue.offer(node.right);

                size--;
            }
            result.add(0, levelStack);
        }
        return result;
    }


    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversalII obj = new BinaryTreeLevelOrderTraversalII();
        System.out.println(obj.levelOrderBottomUp(obj.populateBinaryTree()));
    }

    public TreeNode populateBinaryTree() {
        TreeNode root = new TreeNode();
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

        return root;
    }
}
