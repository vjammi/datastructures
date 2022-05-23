package ds.bst;

import java.util.LinkedList;

/**
                            1015
                   1013               1017
              1012     1014     1016        1018

            bst.put(1015, "Sam1015");
            bst.put(1013, "Sam1013");
            bst.put(1017, "Sam1017");
            bst.put(1012, "Sam1012");
            bst.put(1014, "Sam1014");
            bst.put(1016, "Sam1016");
            bst.put(1018, "Sam1018");
*/
public class BST {

    Node root;

    class Node{
        int key;
        String value;
        Node left, right;

        public Node(int key, String value){
            this.key = key;
            this.value = value;
        }
    }

    public void testPut(BST bst) {
        int[] arr = {11, 6, 8, 19, 4, 10, 5, 17, 43, 49, 31};
        for(int i =0; i < arr.length; i++){
            bst.put(arr[i], arr[i] +"");
        }
    }
    // Creating the root node
    public void put(int key, String value){
        root = put(root, key, value);
    }
    // Recursive put() - Creating the subsequent nodes after root
    // when x == null, create the new node. Until then reduce the search space to find the null node for put
    // O(log n)
    private Node put(Node x, int key, String value){
        if (x == null){
            System.out.println("x == null [x.key: 0  New Key: "  + key);
            return new Node(key, value);
        }
        if (key <= x.key){
            System.out.println("key < x.key [x.key: " + x.key + " New Key: "  + key +"]");
            // When it gets to the leaf node where (node == null), a new node is created, returned and assigned to x.left
            x.left = put(x.left, key, value);
        }else if(key > x.key){
            System.out.println("key > x.key [x.key: " + x.key + " New Key: "  + key +"]");
            // When it gets to the leaf node where (node == null), a new node is created, returned and assigned to x.right
            x.right = put(x.right, key, value);
        }else if (key == x.key){
            System.out.println("key == x.key [x.key: " + x.key + " New Key: "  + key +"]");
            x.value = value;
        }
        return x;
    }

    public void testSearch(int key){
        Node node1 = search(root, key);
        if (node1 != null)
            System.out.println("testSearch - search(...) - Key " +node1.key +" Value: "+ node1.value);
    }
    // Recursive search()/get()
    // value associated with the given key in subtree rooted at x; null if no such key
    private Node search(Node x, int key){
        if (x == null) {
            return null;
        }
        // Just like binary tree, reduce the search space at each step, which is why its running time is log n
        if (key <= x.key ){
            return search(x.left, key); // or x = search(x.left, key) also works
        }else if (key > x.key ){
            return search(x.right, key); // or x = search(x.right, key) also works
        }else {
            return x;
        }
    }
    public void testGet(int key){
        Node node2 = get(root, key);
        System.out.println("testSearch - get(...) - Key " +node2.key +" Value: "+ node2.value);
    }
    // Iterative search()/get()
    // value associated with the given key in subtree rooted at x; null if no such key
    private Node get(Node x, int key) {
        // Just like binary tree, reduce the search space at each step,  which is why its running time is log n
        while (x != null) {
            if (key <= x.key) {
                x = x.left;
            }else if (key > x.key){
                x = x.right;
            }else{
                return x;
            }
        }
        return null;
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     * This is like a search/get operation. When you find the element delete it.
     */
    public void delete(int key) {
        //if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
    }
    private Node delete(Node x, int key) {
        if (x == null)
            return null;

        if (key < x.key)
            x.left  = delete(x.left,  key);
        else if (key > x.key)
            x.right = delete(x.right, key);
        else {
            // if node to be deleted has a null right children, return its left, which could be null itself
            if (x.right == null)
                return x.left;
            // TrieNode to be deleted has a null left children, return its right, which could null itself
            if (x.left  == null)
                return x.right;

            // Save node to t so that it could be replaced (deleted)
            // by the children smallest key in its subtree
            Node t = x;

            // Replace x with its children largest element in its subtree by looking for the leftmost children of t.right and
            // replacing it as the new x.
            x = min(t.right);

            // ???? Delete the min node from t.right and return the remainder of t.right
            x.right = deleteMin(t.right);

            // The left children of old x (key to be deleted), goes to the left of new x
            x.left = t.left;
        }
        //x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    /**
     * Returns the smallest key in the symbol table.
     */
    public int min() {
        //if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }
    private Node min(Node x) {
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }
    /**
     * Removes the smallest key and associated value from the symbol table.
     */
    public void deleteMin() {
        //if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
        //assert check();
    }

    /**
     * Goal of this method is to return 1017 sub-tree by deleting the its min, which is 1016
     * Here to delete 1015 we send here the right of 1015, which is 1017 and the children of 1017 are 1016 and 1018.
     */
    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right; // returning the right of 1016 :)

        x.left = deleteMin(x.left); // 1017.left is now getting updated with 1016.right, causing 1016 tobe deleted. Interesting ha!
        //x.size = size(x.left) + size(x.right) + 1;
        return x; // return 1017
    }

    public void testReverseInorder(BST bst){
        System.out.println("\n--------testReverseInorder--------");
        bst.reverseInorder(root, "root node");
    }
    // Tree Traversals
    private void reverseInorder(Node x, String comment){
        if (x == null) {
            return;
        }
        reverseInorder(x.right, "right");
        System.out.print(", " +x.key);
        reverseInorder(x.left, "left");
    }

    public void testInorder(BST bst){
        System.out.println("\n--------testInorder--------");
        bst.inorder(root, "root node");
    }
    // Tree Traversals
    private void inorder(Node x, String comment){
        if (x == null) {
            //System.out.println("Return (x==null) "+comment +" " +x);
            return;
        }
        inorder(x.left, "left");
        System.out.print(", " +x.key);
        inorder(x.right, "right");
    }

    public void testPreOrder(BST bst){
        System.out.println("\n-------testPreOrder--------");
        bst.preOrder(root, "root node");
    }
    private void preOrder(Node x, String comment){
        if (x == null) {
            //System.out.println("Return (x==null) "+comment +" " +x);
            return;
        }
        System.out.print(", " + x.key);
        preOrder(x.left, "left");
        preOrder(x.right, "right");
    }

    public void testPostOrder(BST bst){
        System.out.println("\n--------testPostOrder--------");
        bst.postOrder(root, "root node");
    }
    private void postOrder(Node x, String comment){
        if (x == null) {
            //System.out.println("Return (x==null) "+comment +" " +x);
            return;
        }
        postOrder(x.left, "left");
        postOrder(x.right, "right");
        System.out.print(", " + x.key);
    }


    public void testLevelOrder(BST bst){
        //bst.levelOrder(root);
        System.out.println("\n--------testLevelOrder--------");
        bst.levelOrder_practice(root);
    }
    // LevelOrder Traversal - Iterative method
    private void levelOrder(Node root){
        if (root == null) return;

        java.util.Queue<Node> q = new LinkedList();
        //QueueImplementingIterable<TrieNode> q = new QueueImplementingIterable<>();

        // Enqueue root node
        q.add(root);
        while (!q.isEmpty()) {
            Node node = q.peek(); // Read first item of the queue
            System.out.print(node.key +" "); // Print node
            //q.remove(); // Remove TrieNode
            // Enqueue removed nodes Left and Right Children
            if (node.left != null)  q.add(node.left);
            if (node.right != null) q.add(node.right);
            q.remove(); // Remove TrieNode
        }
    }
    private void levelOrder_practice(Node root){
        if (root == null) {
            //System.out.println("Nothing to print...");
            return;
        }

        java.util.Queue<Node> queue = new LinkedList();

        ((LinkedList<Node>) queue).add(root);
        // queue.add(root); ???

        while(!queue.isEmpty()){
            // Read the node
            Node node = ((LinkedList<Node>) queue).peek();
            System.out.print(", " + node.key);

            if (node.left != null) {
                ((LinkedList<Node>) queue).add(node.left);
            }
            if (node.right != null) {
                ((LinkedList<Node>) queue).add(node.right);
            }

            // remove the node that we just read
            ((LinkedList<Node>) queue).remove();
        }
    }

    // MyNotes.md: Implement the floor and ceil functionality
    public float floor(float key)    {
        Node x = floor(root, key);

        if (x == null)
            return 0;

        return x.key;
    }
    private Node floor(Node x, float key){

        if (x == null)
            return null;

        // int cmp = key.compareTo(x.key);
        if (key == x.key)
            return x;
        if (key < x.key)
            return floor(x.left, key);

        Node t = floor(x.right, key);
        if (t != null)
            return t;
        else
            return x;
    }

    public static void main(String[] args){
        BST bst = new BST();
        bst.testPut(bst);

        bst.testSearch(6);
        bst.print("", bst.root, false);

        bst.delete(11);
        bst.print("", bst.root, false);

        bst.testInorder(bst);
        bst.testReverseInorder(bst);
        bst.testPreOrder(bst);
        bst.testPostOrder(bst);
        bst.testLevelOrder(bst);
        bst.floor(1013.5f);
    }

    public void print(String prefix, Node node, boolean isLeft) {
        if (node != null) {
            print(prefix +"     ", node.right, false);
            System.out.println ( prefix +(" \t|-") + node.key+(" \t "));
            print( prefix + "     ", node.left, true);
        }
    }

}

// MyNotes.md: Implement Rank functionality
/*
    public int rank(Key key){
        return rank(key, root);
    }

    private int rank(Key key, TrieNode x){
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else if (cmp == 0) return size(x.left);
    }
*/

// MyNotes.md: InOrder Traversal
/*
public Iterable<Key> keys()    {
    Queue1<Key> q = new Queue1<Key>();
    inorder(root, q);
    return q;
}
private void inorder(TrieNode x, Queue1<Key> q)    {
    if (x == null) return;
    inorder(x.left, q);
    q.insert(x.key);
    inorder(x.right, q);
}
*/

