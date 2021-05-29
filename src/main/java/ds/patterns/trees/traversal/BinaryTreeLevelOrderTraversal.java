package ds.patterns.trees.traversal;

import ds.patterns.trees.path.BinaryTreeLongestConsecutiveSequence;
import ds.patterns.trees.path.BinaryTreeMaximumPathSum;

import java.util.ArrayList;
import java.util.List;

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
