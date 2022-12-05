package dev.vjammi.ds.v2.trees.traversal;

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
public class BinaryTreeLevelOrderTraversal {

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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        traversal(root, lists, 0);
        return lists;
    }


    public void traversal(TreeNode node, List<List<Integer>> lists, int level){
        if (node == null)
            return;

        if (level == lists.size()){  // Ugly way of checking a list for that level has already been created
            System.out.println(node.val +" -- " +level);
            List<Integer> list = new ArrayList<>();
            list.add(node.val);
            lists.add(list);
        }else{ // else - if a list for that level has already been created, then just retrieve the list by level and add the node value to the list
            lists.get(level).add(node.val);
            System.out.println(node.val +" - " +level);
        }

        traversal(node.left,  lists, level+1);
        traversal(node.right, lists, level+1);
    }

    public List<List<Integer>> levelOrderIterative(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }

        TreeNode node;
        while (!queue.isEmpty()) {

            int size = queue.size();
            List<Integer> level = new LinkedList<>();

            for (int i=0; i<size; i++) {                 // traverse all nodes of the same level
                node = queue.poll();
                level.add(node.val);                     // visit the node

                if (node.left != null)
                    queue.offer(node.left);              // push left child to queue if it is not null

                if (node.right != null)
                    queue.offer(node.right);             // push right child to queue if it is not null

            }
            result.add(level);
        }
        return result;
    }

    private void levelOrder() {
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

        System.out.println(levelOrder(root).toString());
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal btlo = new BinaryTreeLevelOrderTraversal();
        btlo.levelOrder();
    }
}
