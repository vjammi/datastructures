package ds.bst;

public class MyRedBlackBinarySearchTree {

    private Node root = null;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public class Node{
        int key;
        String value;
        Boolean color;
        int size;

        Node left;
        Node right;

        public Node(int key, String value, Boolean color, int size){
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    public Node delete(int key){
        Node x = delete(root, key);
        System.out.println(">>> Returning TrieNode - key: " +x.key +" " +x.value);
        return x;
    }

    public Node delete(Node h, int key){
        if (h == null){
            return null;
        }

        if (key < h.key){
            h.left = delete(h.left, key);
        }else if (key > h.key){
            h.right = delete(h.right, key);
        }else{
            System.out.println(">>> TrieNode Found. Will proceed to delete - key: " +h.key +" " +h.value);

            if (h.left == null){
                return h.right;
            }
            if (h.right == null){
                return h.left;
            }
            Node t = h;

            h = min(t.right);
            h.right = deleteMin(t.right);
            h.left = t.left;
        }

        return h;
    }

    public Node min(Node h){
        if (h.left == null){
            return h;
        }
        min(h.left);
        return h;
    }

    private Node deleteMin(Node h) {
        if (h.left == null)
            return h.right;
        h.left = deleteMin(h.left);
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    public void put(int key, String value){
        root = put(root, key, value);
        root.color = BLACK;
    }

    public Node put(Node h, int key, String value){
        if (h == null){
            return new Node(key, value, RED, 1);
        }

        if (key < h.key){
            h.left = put(h.left, key, value);
        }else if (key > h.key){
            h.right = put(h.right, key, value);
        }else{
            System.out.println(">>> TrieNode Updated for key: " +h.key +" with Value: " +h.value);
            h.value = value;
        }

        if (isRED(h.right) && (!isRED(h.left)) ){
            h = rotateLeft(h);
        }
        if(isRED(h.left) && isRED(h.left.left)){
            h = rotateRight(h);
        }
        if (isRED(h.right) && isRED(h.left)){
            flipColors(h);
        }

        h.size = size(h.left) + size(h.right) +1;

        return h;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        x.color = h.color;
        h.color = RED;

        x.size = h.size;
        h.size = size(h.left) + size(h.right) +1;

        return x;
    }

    public Node rotateRight(Node h){
        Node x = h.left;

        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;

        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;

        return x;
    }

    private void flipColors(Node h){
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public boolean isRED(Node x){
        if(x == null) return false;

        return x.color == RED;
    }

    public boolean isBLACK(Node x){
        if(x == null) return true; // TODO: ???

        return x.color == BLACK;
    }

    public int size(Node x){
        if (x == null)
            return 0;

        return x.size;
    }

    public Node get(int key){
        Node x = get(root, key);
        System.out.println(">>> Returning TrieNode - key: " +x.key +" " +x.value);
        return x;
    }

    public Node get(Node x, int key){
        if (x == null){
            return null;
        }

        if (key < x.key){
            return get(x.left, key);
        }else if (key > x.key){
            return get(x.right, key);
        }else{
            System.out.println(">>> TrieNode Found. Returning TrieNode - key: " +x.key +" " +x.value);
            return x;
        }
    }

    private static void testPut(MyRedBlackBinarySearchTree bst) {
        bst.put(1015, "Sam1015");
        bst.put(1013, "Sam1013");
        bst.put(1017, "Sam1017");
        bst.put(1012, "Sam1012");
        bst.put(1014, "Sam1014");
        bst.put(1016, "Sam1016");
        bst.put(1018, "Sam1018");
    }

    private static void testPutAscendingOrder(MyRedBlackBinarySearchTree bst) {
        bst.put(1012, "Sam1012");
        bst.put(1013, "Sam1013");
        bst.put(1014, "Sam1014");
        bst.put(1015, "Sam1015");
        bst.put(1016, "Sam1016");
        bst.put(1017, "Sam1017");
        bst.put(1018, "Sam1018");
    }

    private static void testPutDescendingOrder(MyRedBlackBinarySearchTree bst) {
        bst.put(1018, "Sam1018");
        bst.put(1017, "Sam1017");
        bst.put(1016, "Sam1016");
        bst.put(1015, "Sam1015");
        bst.put(1014, "Sam1014");
        bst.put(1013, "Sam1013");
        bst.put(1012, "Sam1012");
    }

    public static void main(String[] args){
        MyRedBlackBinarySearchTree bst = new MyRedBlackBinarySearchTree();

        //testPut(bst);
        //testPutAscendingOrder(bst);
        testPutDescendingOrder(bst);

        Node node1 = bst.get(1012);
        System.out.println(">>> Returning TrieNode - key: " +node1.key +" Value: " +node1.value + " Size: " +node1.size);

        Node node2 = bst.get(1015);
        System.out.println(">>> Returning TrieNode - key: " +node2.key +" Value: " +node2.value + " Size: " +node2.size);

    }


}
