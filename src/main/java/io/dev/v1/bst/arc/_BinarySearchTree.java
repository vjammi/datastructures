package io.dev.v1.bst.arc;

/**
 * Created by Vijay Jammi on 06/06/2018.
 */
public class _BinarySearchTree {

    Node root;

    class Node{
        int key;
        String value;
        Node left, right = null;

        public Node(int key, String value){
            this.key = key;
            this.value = value;
        }
    }

    public void testPut(int key, String value){
        root = put(root, key, value);
    }

    private Node put(Node x, int key, String value) {

        if (x == null){
            return new Node(key, value);
        }

        if (key == x.key){
            x.value = value;
        }else if (key < x.key){
            x.left = put(x.left, key, value);
        }else if(key > x.key){
            x.right = put(x.right, key, value);
        }

        return x;
    }

    public void testSearch(int key){
        Node node = search(root, key);
        System.out.println(">>>TrieNode Fetched: " + node.key +" - " + node.value);
    }

    private Node search(Node x, int key){
        if (x == null){
            System.out.println(">>>TrieNode Not Found for the Key:" + key);
            return null;
        }

        if (key == x.key){
            System.out.println(">>>TrieNode Found: " + x.key +" - " + x.value);
            return x;
        }else if(key < x.key){
            x.left = search(x.left, key);
        }else if(key > x.key){
            x.right = search(x.right, key);
        }

        return x;
    }

    public void testBreadthFirstTraversing(){
        System.out.println(">>> Traversing the Tree inOrder: ");
        inorder(root);
        System.out.println(">>> Traversing the Tree preOrder: ");
        preOrder(root);
        System.out.println(">>> Traversing the Tree postOrder: ");
        postOrder(root);
    }

    private void inorder(Node x){
        if (x == null){
            return;
        }
        inorder(x.left);
        System.out.println(x.key);
        inorder(x.right);
    }

    private void preOrder(Node x){
        if (x == null){
            return;
        }
        System.out.println(x.key);
        inorder(x.left);
        inorder(x.right);
    }

    private void postOrder(Node x){
        if (x == null){
            return;
        }
        inorder(x.left);
        inorder(x.right);
        System.out.println(x.key);
    }

    public static void main (String[] args){
        _BinarySearchTree bst = new _BinarySearchTree();

        System.out.println("----------------");
        System.out.println("1015");
        bst.testPut(1015, "Sam1015");

        System.out.println("1013");
        bst.testPut(1013, "Sam1013");

        System.out.println("1014");
        bst.testPut(1014, "Sam1014");

        System.out.println("1012");
        bst.testPut(1012, "Sam1012");

        //--------

        System.out.println("1017");
        bst.testPut(1017, "Sam1017");

        System.out.println("1016");
        bst.testPut(1016, "Sam1016");

        System.out.println("1018");
        bst.testPut(1018, "Sam1018");

        System.out.println("----------------");

        bst.testSearch(1012);
        System.out.println("----------------");
        bst.testSearch(1018);
        System.out.println("----------------");
        bst.testSearch(101);

        System.out.println("----------------");
        bst.testBreadthFirstTraversing();

    }

}
