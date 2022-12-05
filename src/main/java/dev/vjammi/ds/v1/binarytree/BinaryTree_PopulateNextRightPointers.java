package dev.vjammi.ds.v1.binarytree;

public class BinaryTree_PopulateNextRightPointers {

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
    private void populate(BinaryTree_PopulateNextRightPointers tree) {
        //int[] arr = {16, 10, 22, 8, 12, 20, 24, 6, 9, 11, 13, 19, 21, 23, 25};
        //int[] arr = {1,2,3,4,5,6,7};
        //int[] arr = {1,2,3,4,5,0,7};
        //int[] arr = {1,2,3,4,0,0,5};
        //int[] arr = {1,2,3,4,5,0,6,7,0,0,0,0,8};
        int[] arr = {2,1,3,0,7,9,1,2,-1,1,0,-1,-1,8,8,-1,-1,-1,-1,7};
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

    public Node connect1(Node root) {
        if(root == null){
            return root;
        }

        root.next = null;
        /*
        Node nodeToLeft = root.left;
        Node nodeToRight = root.right;
        while(nodeToLeft != null && nodeToRight != null){
            nodeToLeft.next = nodeToRight;
            nodeToLeft = nodeToLeft.right;
            nodeToRight = nodeToRight.left;
        }
        */
        connectNode1(root);
        return root;
    }
    public void connectNode1(Node node) {
        if (node == null) {
            return;
        }

        if (node.left != null ){
            node.left.next = node.right;
        }

        if (node.right != null && node.next != null && node.next.left != null){
            System.out.println(""+ node.right.val +">"+node.next.left.val +" ");
            node.right.next = node.next.left;
        }

        connectNode1(node.left);
        connectNode1(node.right);
    }

    public static void main(String[] args) {
        BinaryTree_PopulateNextRightPointers tree = new BinaryTree_PopulateNextRightPointers();
        tree.populate(tree);

        //tree.print("", tree.root);
        //tree.print("", tree.connect1( tree.root));
        tree.print("", tree.connect2( tree.root));
    }

    private Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        connectNode(root);
        return root;
    }

    private void connectNode(Node node) {
        if (node == null){
            return;
        }


        if (node.left !=null ){
            if (node.right != null){
                node.left.next = node.right;
            } else {
                Node nextNode = node.next;
                while(nextNode != null) {
                    if (nextNode.left != null) { //&& node.nextNode != null
                        node.left.next = nextNode.left;
                        break;
                    } else if (nextNode.right != null) { //&& node.nextNode != null
                        node.left.next = nextNode.right;
                        break;
                    } else {
                        nextNode = nextNode.next;
                    }
                }
                if (nextNode != null) System.out.println(node.left.next.val +" > " +nextNode.val);
            }
        }

        if (node.right !=null ){
            Node nextNode = node.next;
            while(nextNode != null) {
                if (nextNode.left != null) { //&& node.nextNode != null
                    node.right.next = nextNode.left;
                    break;
                } else if (nextNode.right != null) { //&& node.nextNode != null
                    node.right.next = nextNode.right;
                    break;
                } else {
                    nextNode = nextNode.next;
                }
            }
            if (nextNode != null) System.out.println(node.right.next.val +" > " +nextNode.val);
        }


        //        if (node.right != null && node.next != null && node.next.left != null){
        //            node.right.next = node.next.left;
        //        }else if(node.right != null && node.next != null && node.next.left == null){
        //            node.right.next = node.next.right;
        //        }

        connectNode(node.left);
        connectNode(node.right);
    }
}
