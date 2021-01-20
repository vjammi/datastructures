package algo.ds.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree_InvertTree {

    Node root;

    public class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    public void testPut(BinaryTree_InvertTree tree) {
        //int[] arr = {4,2,7,1,3,6,9};
        int[] arr = {2,3,0,1};
        for(int i =0; i < arr.length; i++){
            tree.put(arr[i], arr[i] + "");
        }
    }

    public void put(int key, String value){
        root = put(root, key, value);
    }

    public Node put(Node x, int key, String value){
        if (x == null){
            //System.out.println("x == null [x.key: 0  New Key: "  + key);
            return new Node(key);
        }
        if (key < x.key){
            // When it gets to the leaf node where (node == null), a new node is created, returned and assigned to x.left
            x.left = put(x.left, key, value);
        }else if(key > x.key){
            // When it gets to the leaf node where (node == null), a new node is created, returned and assigned to x.right
            x.right = put(x.right, key, value);
        }else if (key == x.key){
            //System.out.println("key == x.key [x.key: " + x.key + " New Key: "  + key +"]");
        }
        return x;
    }


    public Node invertTree(Node root) {
        invert(root);
        return root;
    }

    public void invert(Node node) {
        if (node == null) {
            return;
        }
        exch(node);
        invert(node.right);
        invert(node.left);
    }

    public void invert1(Node node) {
        exch(node);
        if (node.right != null) {
            invert(node.right);
        }
        if (node.left != null) {
            invert(node.left);
        }
    }

    private void exch(Node node) {
        Node temp = node.right;
        node.right = node.left;
        node.left = temp;
    }

    public Node invertTreeIterative(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null ) break;
            exch(node);
            //if (node.left != null)
            queue.add(node.left);
            //if (node.right != null)
            queue.add(node.right);
        }
        return root;
    }

    public void print(String prefix, Node node, boolean isLeft) {
        if (node != null) {
            print(prefix +"     ", node.right, false);
            System.out.println ( prefix +(" \t|-") + node.key+(" \t "));
            print( prefix + "     ", node.left, true);
        }
    }

    public static void main(String[] args) {
        BinaryTree_InvertTree tree = new BinaryTree_InvertTree();

        //setup
        tree.testPut(tree);
        tree.print("", tree.root, false);

        tree.invertTree(tree.root);
        tree.print("", tree.root, false);

        tree.invertTreeIterative(tree.root);
        tree.print("", tree.root, false);

    }

}
