package io.dev.v2.trees;

import java.util.*;

public class BinaryTreeTraversals {
    TreeNode root;

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int key) {
            this.val = key;
        }

    }

    private List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrderedList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        // + A B
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            preOrderedList.add(node.val);                        //  +
            if (node.right!=null) stack.add(node.right);         //  B
            if (node.left!=null) stack.add(node.left);           //  A
        }
        return preOrderedList;
    }

    private List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inOrderList = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();  // Note we do not add the root to the stack here
        TreeNode current = root;                // But we set the root to the current

        // A + B
        while(current != null || !stack.isEmpty()){ // Notice its not && because we will be assigning current to null indicating we have reached teh leftmost part of the subtree

            if (current!=null) { // if (current!=null) is responsible for pushing elements into the stack and  lets me first visit the leftmost subtree of the binary tree
                stack.push(current);
                current = current.left; // lets us reach the left most part of a subtree
            }else { // else - Once i have reached the leftmost node of the Binary tree, when current becomes null
                // we now on our way back/up the tree, pop the node out of the stack and add its right node to the stack (if available) for traversing.
                TreeNode node = stack.pop();
                inOrderList.add(node.val);
                if (node.right!=null)
                    current = node.right;
                // else
                    // do nothing - We are done with this node. We have already traversed the leftmost side of this node.
            }
        }
        return inOrderList;
    }

    private List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> postOrderReverseList = new ArrayList<>();
        List<Integer> postOrderList = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // AB+
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node!=null) stack.push(node.left);
            if (node!=null) stack.push(node.right);
            if (node!=null) {
                postOrderList.add(0, node.val);
                postOrderReverseList.add(node.val);
            }
        }
        System.out.println("postOrderList iterative - without reversing "+postOrderReverseList);
        return postOrderList;
    }


    List<Integer> postOrderList = new ArrayList<>();
    private void postOrderTraversalRecursive(TreeNode node) {
        if (node == null)
            return;
        postOrderTraversalRecursive(node.left);
        postOrderTraversalRecursive(node.right);
        postOrderList.add(node.val);
    }

    private List<List<Integer>> levelOrderTraversalIterative(TreeNode root) {

        List<List<Integer>> levelOrderTraversalLists = new ArrayList();
        List<Integer> levelList = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 0;
        while(!queue.isEmpty()){

            int size = queue.size();
            levelList = new ArrayList();
            System.out.println("Processing level " +level +" of size " +size);

            while(size > 0){
                TreeNode node = queue.poll();
                levelList.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            levelOrderTraversalLists.add(levelList);
            level++;
        }
        return levelOrderTraversalLists;
    }

    List<List<Integer>> levelOrderTraversalRecursiveList =  new ArrayList<>();
    private void levelOrderTraversalRecursive(TreeNode node, int level) {
        if (node == null)
            return;

        if (levelOrderTraversalRecursiveList.size() == level) { // Ugly way of checking a list for that level has already been created
            List<Integer> list = new ArrayList<>();
            list.add(node.val);
            levelOrderTraversalRecursiveList.add(list); // Note: list.get(level).add(node.val) will throw IndexOutOfBoundsException: Index: 0, Size: 0
        }else {
            // if a list for that level has already been created, then just retrieve the list by level and add the node value to the list
            levelOrderTraversalRecursiveList.get(level).add(node.val);
        }
        levelOrderTraversalRecursive(node.left, level+1);
        levelOrderTraversalRecursive(node.right, level+1);
    }

    /**
                                 16

                     10                      22

                8         12            20         24

             6     9   11    13     19     21   23    25

           N   7

        preorderTraversalList  [16, 10, 8, 6, 7, 9, 12, 11, 13, 22, 20, 19, 21, 24, 23, 25]
        inOrderTraversalList   [6, 7, 8, 9, 10, 11, 12, 13, 16, 19, 20, 21, 22, 23, 24, 25]
        postOrderTraversalList [7, 6, 9, 8, 11, 13, 12, 10, 19, 21, 20, 23, 25, 24, 22, 16]

    */

    public static void main(String[] args) {
        BinaryTreeTraversals tree = new BinaryTreeTraversals();

        tree.testPut(tree);
        tree.print("", tree.root);

        List<Integer> preorderTraversalList = tree.preorderTraversal(tree.root);
        System.out.println("preorderTraversalList " + preorderTraversalList);

        List<Integer> inOrderTraversalList = tree.inorderTraversal(tree.root);
        System.out.println("inOrderTraversalList " + inOrderTraversalList);

        List<Integer> postOrderTraversalList = tree.postOrderTraversal(tree.root);
        System.out.println("postOrderTraversalList " + postOrderTraversalList);

        tree.postOrderTraversalRecursive(tree.root);
        System.out.println("postOrderTraversalListRecursive " + tree.postOrderList);

        List<List<Integer>>  levelOrderTraversalList = tree.levelOrderTraversalIterative(tree.root);
        // Iterative levelOrderTraversal [[16], [10, 22], [8, 12, 20, 24], [6, 9, 11, 13, 19, 21, 23, 25], [7]]
        System.out.println("Iterative levelOrderTraversal " + levelOrderTraversalList);
        tree.levelOrderTraversalRecursive(tree.root, 0);
        //tree.levelOrderTraversalRecursive2(tree.root, 0);
        System.out.println("levelOrderTraversalRecursiveList " +tree.levelOrderTraversalRecursiveList);
    }

    public void testPut(BinaryTreeTraversals tree) {
        //int[] arr = {1,2,0,3,0,4,0,5};
        //int[] arr = {4,9,2,1,3,6,10,5};
        int[] arr = {16, 10, 22, 8, 12, 20, 24, 6, 9, 11, 13, 19, 21, 23, 25, 7};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                tree.put(arr[i], null);
            } else {
                tree.put(arr[i], String.valueOf(arr[i]));
            }
        }
    }

    public void print(String prefix, TreeNode node) {
        if (node != null) {
            print(prefix + "\t", node.right);
            System.out.println(prefix + (" \t |-") + node.val);
            print(prefix + "\t", node.left);
        }
    }

    public void put(int key, String value) {
        root = put(root, key, value);
    }

    public TreeNode put(TreeNode x, int key, String value) {
        if (x == null) {
            //System.out.println("x == null [x.key: 0  New Key: "  + key);
            return new TreeNode(key);
        }
        if (key < x.val) {
            // When it gets to the leaf node where (node == null), a new node is created, returned and assigned to x.left
            x.left = put(x.left, key, value);
        } else if (key > x.val) {
            // When it gets to the leaf node where (node == null), a new node is created, returned and assigned to x.right
            x.right = put(x.right, key, value);
        } else if (key == x.val) {
            //System.out.println("key == x.key [x.key: " + x.key + " New Key: "  + key +"]");
        }
        return x;
    }
}
