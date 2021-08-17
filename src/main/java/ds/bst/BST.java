package ds.bst;

import java.util.LinkedList;
import java.util.Queue;

public class BST {

    private Node root;

    public class Node{
        int key;
        String value;
        Node left, right;

        public Node(int key, String value){
            this.key = key;
            this.value = value;
        }
    }

    public void testPut(){
        root = put(root, 1500, "1500-V");
        root = put(root, 1300, "1300-V");
        root = put(root, 1700, "1700-V");

        root = put(root, 1200, "1200-V");
        root = put(root, 1400, "1400-V");

        root = put(root, 1600, "1600-V");
        root = put(root, 1800, "1800-V");
    }

    public void testPut2(){
        root = put(root, 50, "50");
        root = put(root, 16, "16");
        root = put(root, 90, "90");

        //root = put(root, 14, "14");
        //root = put(root, 40, "40");

        root = put(root, 78, "78");
        root = put(root, 100, "100");

        root = put(root, 10, "10");
        root = put(root, 15, "15");

        root = put(root, 35, "35");
        root = put(root, 45, "45");

        root = put(root, 75, "75");
        root = put(root, 82, "82");

        root = put(root, 5, "5");

        root = put(root, 32, "32");
        root = put(root, 36, "36");

        root = put(root, 81, "81");
        root = put(root, 85, "85");

        root = put(root, 37, "37");
        root = put(root, 79, "79");
        root = put(root, 87, "87");

        root = put(root, 14, "14");
        root = put(root, 40, "40");
    }

    public Node put(Node x, int key, String value){
        if (x == null){
            return new Node(key, value);
        }

        if (key < x.key){
            x.left = put(x.left, key, value);
        }else if (key > x.key){
            x.right = put(x.right, key, value);
        }else{
            x.value = value;
        }

        return x;
    }

    public void testGet(){
        Node node = get(root, 1250);
        if (node!=null){
            System.out.println("Key " +node.key);
        }

    }

    public Node get(Node x, int key){
        if (x == null){
            return null;
        }

        if (key < x.key){
            return get(x.left, key);
        }else if(key > x.key){
            return get(x.right, key);
        }else {
            return x;
        }

    }

    public void testTreeTraversals() {
        System.out.println("\nlevelOrder: ");
        levelOrder(root);
        System.out.println("\nPreOrder: ");
        preOrder(root);
        System.out.println("\nInOrder: ");
        inOrder(root);
        System.out.println("\npostOrder: ");
        postOrder(root);
    }

    private void levelOrder(Node root) {
        Node x = root;
        Queue<Node> queue = new LinkedList();
        queue.add(x);

        while(!queue.isEmpty()){
            Node node = ((LinkedList<Node>) queue).getFirst();
            System.out.print(", "+ node.key);
            if (node.left != null ){
                queue.add(node.left);
            }
            if (node.right != null ){
                queue.add(node.right);
            }
            queue.remove();

        }
    }

    /** In -  A+B    */
    public void inOrder(Node x){
        if (x == null)
            return;
        inOrder(x.left);
        System.out.print(", " +x.key);
        inOrder(x.right);
    }

    /**  Pre - +AB     */
    public void preOrder(Node x){
        if (x == null)
            return;
        System.out.print(", " +x.key);
        preOrder(x.left);
        preOrder(x.right);
    }

    /**  Post- AB+   */
    public void postOrder(Node x){
        if (x == null)
            return;
        postOrder(x.left);
        postOrder(x.right);
        System.out.print(", " +x.key);
    }

    public static void main(String args[]){
        BST bst = new BST();
        bst.testPut2();
        bst.testGet();
        bst.testTreeTraversals();
    }

}
