package ds.binarysearchtree;
/**
 * Created by Vijay Jammi on 06/04/2018.
 */
public class BalancedBinarySearchTree {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        int key;
        String val;
        Node left, right;
        boolean color; // color of parent link

        public Node(int key, String value, boolean color){
            this.key = key;
            this.val = value;
            this.color = color;
        }
    }

    private boolean isRed(Node x)   {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h)    {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private Node rotateRight(Node h)   {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private void flipColors(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public String get(int key) {
        Node x = root;
        while (x != null) {
            //int cmp = key.compareTo(x.key);
            if (key < x.key) x = x.left;
            else if (key > x.key) x = x.right;
            else if (key == x.key) return x.val;
        }
        return null;
    }

    private Node put(Node h, int key, String val) {

        // Regular BST Impl
        if (h == null){
            return new Node(key, val, RED);
        }
        //int cmp = key.compareTo(h.key);
        if (key < h.key) {
            h.left = put(h.left, key, val);

        }else if (key > h.key) {
            h.right = put(h.right, key, val);

        }else if (key == h.key) {
            h.val = val;
        }

        // Additional Implementation for Balanced BST
        if (isRed(h.right) && !isRed(h.left)){
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)){
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)){
            flipColors(h);
        }

        return h;
    }

    public void testPut(BalancedBinarySearchTree bbst) {
        System.out.println("----------------");
        System.out.println("1015");
        root = put(root, 1015, "Sam1015");

        System.out.println("1013");
        root = put(root,1013, "Sam1013");

        System.out.println("1014");
        root = put(root,1014, "Sam1014");

        System.out.println("1012");
        root = put(root,1012, "Sam1012");

        //--------

        System.out.println("1017");
        root = put(root,1017, "Sam1017");

        System.out.println("1016");
        root = put(root,1016, "Sam1016");

        System.out.println("1018");
        root =  put(root,1018, "Sam1018");
    }

    public void testPutUnOrdered(BalancedBinarySearchTree bbst) {
        System.out.println("----------------");

        System.out.println("1018");
        put(root,1018, "Sam1018");

        System.out.println("1012");
        put(root,1012, "Sam1012");

        System.out.println("1016");
        put(root,1016, "Sam1016");

        System.out.println("1013");
        put(root,1013, "Sam1013");

        System.out.println("1017");
        put(root,1017, "Sam1017");

        System.out.println("1014");
        put(root,1014, "Sam1014");

        System.out.println("1015");
        bbst.put(root, 1015, "Sam1015");

    }

    public void inorder(Node x, String comment){
        if (x == null) {
            System.out.println("Return (x==null) "+comment +" " +x);
            return;
        }else{
            System.out.println("Traversing node x!=null "+comment +" " +x.key);
        }
        inorder(x.left, "left");
        System.out.println("x.key = " + x.key);
        inorder(x.right, "right");
    }

    public void testInorder(BalancedBinarySearchTree bbst){
        System.out.println("--------testInorder--------");
        bbst.inorder(root, "root node");
    }

    public static void main(String[] args){

        BalancedBinarySearchTree bbst = new BalancedBinarySearchTree();
        bbst.testPut(bbst);
        bbst.testInorder(bbst);
        //bbst.testPreOrder(bst);
        //bbst.testPostOrder(bst);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");

        BalancedBinarySearchTree bbst2 = new BalancedBinarySearchTree();
        bbst2.testPutUnOrdered(bbst);
        bbst2.testInorder(bbst);
    }


}
