package dev.vjammi.ds.v1.binarytree;

public class BinaryTree_LowestCommonAncestor {

    private Node root;

    class Node {
        int val;
        Node left, right, next;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public void put(int val){
        this.root = put(root, val);
    }
    private Node put(Node node, int val) {
        if (node == null) {
            return new Node(val);
        }
        if (val < node.val){
            node.left = put(node.left, val);
        }else if(val > node.val){
            node.right = put(node.right, val);
        }else if(val == node.val){
            node.val = val;
        }
        return node;
    }
    private void populate(BinaryTree_LowestCommonAncestor tree) {
        int[] arr = {16, 10, 22, 8, 12, 20, 24, 6, 9, 11, 13, 19, 21, 23, 25};
        root = insertLevelOrder(arr, root, 0);
    }
    public Node insertLevelOrder(int[] arr, Node node, int key){
        // Base case for recursion
        if (key < arr.length) {
            Node temp = null;
            if (arr[key] != -1) {
                temp = new Node(arr[key]);
            }
            node = temp;
            if (node != null) {
                // insert left child
                node.left = insertLevelOrder(arr, node.left, 2 * key + 1);
                // insert right child
                node.right = insertLevelOrder(arr, node.right, 2 * key + 2);
            }
        }
        return node;
    }
    public void print(String prefix, Node node) {
        if (node != null) {
            print(prefix +"\t", node.right);
            if (node.next != null){
                System.out.println ( prefix +(" \t |-") + "^" + node.val);
            }else{
                System.out.println ( prefix +(" \t |-") + node.val);
            }
            print( prefix + "\t", node.left);
        }
    }


    public static void main(String[] args) {
        BinaryTree_LowestCommonAncestor tree = new BinaryTree_LowestCommonAncestor();
        tree.populate(tree);

        //tree.print("", tree.root);
        //tree.print("", tree.connect1( tree.root));

    }

}
