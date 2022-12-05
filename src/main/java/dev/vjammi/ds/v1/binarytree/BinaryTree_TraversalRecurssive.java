package dev.vjammi.ds.v1.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree_TraversalRecurssive {

    private TreeNode root;

    class TreeNode{
        int key;
        String val;
        TreeNode left, right;

        TreeNode(int key, String val){
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public void put(int key, String val){
        this.root = put(root, key, val);
    }

    private TreeNode put(TreeNode node, int key, String val) {
        if (node == null) {
            return new TreeNode(key, val);
        }

        if (key < node.key){
            node.left = put(node.left, key, val);
        }else if(key > node.key){
            node.right = put(node.right, key, val);
        }else if(key == node.key){
            node.val = val;
        }

        return node;
    }

    private void populate(BinaryTree_TraversalRecurssive tree) {
        int[] arr = {16, 10, 22, 8, 12, 20, 24, 6, 9, 11, 13, 19, 21, 23, 25, 7};
        //int[] arr = {3, 2, 20, 15, 7};
        //int[] arr = {1, 2};
        for(int i =0; i < arr.length; i++){
            tree.put(arr[i], String.valueOf(arr[i]));
        }
    }

    /** Input                      16, 10, 22, 8, 12, 20, 24, 6, 9, 11, 13, 19, 21, 23, 25, 7
         Traversal                  16, 10, 8, 6, null, 7, null, null, 9, null, null, 12, 11, null, null, 13, null, null, 22, 20, 19, null, null, 21, null, null, 24, 23, null, null, 25, null, null
         Preorder Traversal List    [16, 10, 8, 6, 7, 9, 12, 11, 13, 22, 20, 19, 21, 24, 23, 25]
     **/
    private void preorderTraversal() {
        List<Integer> preorderList = new ArrayList<>();
        preorder(root, preorderList);
        System.out.println("\n Preorder List "+ preorderList);

        List<Integer> preorderList2 = new ArrayList<>();
        preorder2(root, preorderList2);
        System.out.println("\n Preorder List "+ preorderList2);

        List<Integer> preorderList3 = new ArrayList<>();
        preorder3(root, preorderList3);
        System.out.println("\n Preorder List "+ preorderList3);

    }

    private void preorder(TreeNode node, List<Integer> preorderList) {
        if (node == null){
            printNode(node);
            return;
        }
        printNode(node); preorderList.add(node.key);
        preorder(node.left, preorderList);
        preorder(node.right, preorderList);
    }

    private TreeNode preorder2(TreeNode node, List<Integer> preorderList) {
        if (node == null){
            printNode(node);
            return null;
        }
        printNode(node); preorderList.add(node.key);
        preorder2(node.left, preorderList);
        preorder2(node.right, preorderList);
        return node;
    }

    private TreeNode preorder3(TreeNode node, List<Integer> preorderList) {
        printNode(node);
        if (node == null){
            return null;
        }
        preorderList.add(node.key);
        node.left = preorder3(node.left, preorderList);
        node.right = preorder3(node.right, preorderList);
        return node;
    }

    /** Input                      16, 10, 22, 8, 12, 20, 24, 6, 9, 11, 13, 19, 21, 23, 25, 7
        Output                     null, 6, null, 7, null, 8, null, 9, null, 10, null, 11, null, 12, null, 13, null, 16, null, 19, null, 20, null, 21, null, 22, null, 23, null, 24, null, 25, null,
        Inorder Traversal List      [6, 7, 8, 9, 10, 11, 12, 13, 16, 19, 20, 21, 22, 23, 24, 25] **/
    private void inorderTraversal() {
        ArrayList<Integer> inorderList = new ArrayList<Integer>();
        root = inorder(root, inorderList);
        System.out.println("\n Inorder List "+ inorderList);
    }

    private TreeNode inorder(TreeNode node, ArrayList<Integer> inorderList) {
        if (node == null){
            printNode(node);
            return null;
        }

        inorder(node.left, inorderList);

        printNode(node);
        inorderList.add(node.key);

        inorder(node.right, inorderList);

        return node;
    }

    /**  Input                       16, 10, 22, 8, 12, 20, 24, 6, 9, 11, 13, 19, 21, 23, 25, 7
         Traversal                   null, null, null, 7, 6, null, null, 9, 8, null, null, 11, null, null, 13, 12, 10, null, null, 19, null, null, 21, 20, null, null, 23, null, null, 25, 24, 22, 16,
         Postorder Traversal List    [7, 6, 9, 8, 11, 13, 12, 10, 19, 21, 20, 23, 25, 24, 22, 16]
     **/
    private void postorderTraversal() {
        List<Integer> postorderList = new ArrayList<>();
        postorder(root, postorderList);
        System.out.println("\n PostOrder List "+ postorderList);
    }

    private void postorder(TreeNode node, List<Integer> postorderList) {
        if (node == null){
            printNode(node);
            return;
        }
        postorder(node.left, postorderList);
        postorder(node.right, postorderList);
        printNode(node); // postorderList.add(node.key);
    }

    private void findMaxDept(){
        System.out.println(maxDepth(root));
    }

    private int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        System.out.println("<- Current Node " + node.val);
        int leftDepth = maxDepth(node.left);
        System.out.println("<- Left Subtree Depth @ Node " + node.val +" is " +leftDepth);
        System.out.println("-> Current Node " + node.val);
        int rightDepth = maxDepth(node.right);
        System.out.println("-> Right Subtree Depth @ Node " + node.val +" is " +rightDepth);
        /** Invoked when left and right nodes have been traversed, where
            either
                  Left node is null && right node is null [for leaf node]
             or
                  Left node is already traversed && right node is an not traversed yet [non leaf node] */
        int depth = max(leftDepth, rightDepth); // Math.max(left_depth, right_depth) + 1
        return depth + 1;
    }

    private int max(int leftDepth, int rightDepth) {
        if (leftDepth > rightDepth){
            System.out.println(" * Max is in Left Subtree "+leftDepth);
            return leftDepth;
        } else{
            System.out.println(" * Max is in Right Subtree "+rightDepth);
            return rightDepth;
        }
    }

    private void findMinDept(){
        System.out.println(minDepth(root));
    }

    private int minDepth(TreeNode root) {
        if (root == null ) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;
        int minDepth = 0;
        boolean leafNodeFound = false;
        while(!queue.isEmpty()){
            level++;
            int levelSize = queue.size();
            for(int i = 0; i < levelSize; i++){
                TreeNode treeNode = queue.poll();
                if(treeNode.left == null && treeNode.right == null){
                    if (!leafNodeFound) {
                        minDepth = level;
                        leafNodeFound = true;
                        return minDepth;
                    }else {
                        minDepth = Math.min(level, minDepth);
                    }
                    System.out.println("LeafNodeFound " +leafNodeFound +" @ Level " +level +" for Node " +treeNode.val + " MinDepth " +minDepth);
                }

                if (treeNode.left  != null) queue.add(treeNode.left);
                if (treeNode.right != null) queue.add(treeNode.right);
            }
            if (!leafNodeFound) minDepth++;
            System.out.println(" Level " +level +" MinDepth " +minDepth);
        }
        return minDepth;
    }

    public static void main(String[] args) {
        BinaryTree_TraversalRecurssive tree = new BinaryTree_TraversalRecurssive();
        tree.populate(tree);
        tree.print("", tree.root);

        //tree.preorderTraversal();
        tree.inorderTraversal();
        //tree.postorderTraversal();

        //tree.findMaxDept();
        tree.findMinDept();

    }

    public void print(String prefix, TreeNode node) {
        if (node != null) {
            print(prefix +"\t", node.right);
            System.out.println ( prefix +(" \t |-") + node.val);
            print( prefix + "\t", node.left);
        }
    }

    private void printNode(TreeNode node) {
        if (node != null)
            System.out.print(node.key +", ");
        else
            System.out.print(null +", ");
    }

}
