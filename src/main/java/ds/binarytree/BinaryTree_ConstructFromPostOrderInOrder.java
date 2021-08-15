package ds.binarytree;

import java.util.HashMap;
import java.util.Map;

public class BinaryTree_ConstructFromPostOrderInOrder {
    TreeNode root;
    class TreeNode{
        int val;
        TreeNode left, right;

        TreeNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private int[] postorder;
    private int[] inorder;
    private int postorderIndex = 0;
    private Map<Integer, Integer> inorderMap = new HashMap<>();

    private TreeNode buildTree() {
        //int[] inorder = {9,3,15,20,7};
        // int[] postorder = {9,15,7,20,3};
        int[] postorder = {7,6,9,8,11,13,12,10,19,21,20,23,25,24,22,16};
        //                 0,1,2,3,4, 5, 6, 7, 8, 9, 10,11,12,13,14,15
        //                                                    ^
        int[] inorder  = {6,7,8,9,10,11,12,13,16,19,20,21,22,23,24,25};
        //                0,1,2,3,4, 5, 6, 7, 8, 9, 10,11,12,13,14,15
        //                                                         ^  ^
        return buildTree(inorder, postorder);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;

        for(int i=0; i< inorder.length; i++){
            inorderMap.put(inorder[i], i);
        }
        postorderIndex = postorder.length - 1;
        return buildTree(0, inorder.length-1);
    }

    public TreeNode buildTree(int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex){
            return null;
        }
        // Select the next postorder node as the current root and decrement it postorder index
        int postOrderNodeVal = postorder[postorderIndex--];
        TreeNode currNode = new TreeNode(postOrderNodeVal);

        int inorderIndexForCurrNode = inorderMap.get(postOrderNodeVal);
        currNode.right = buildTree(inorderIndexForCurrNode+1, rightIndex);
        currNode.left  = buildTree(leftIndex, inorderIndexForCurrNode - 1);

        return currNode; // Return the current node to be added to the left or the right side of the parent node.
    }

    public static void main(String[] args) {
        BinaryTree_ConstructFromPostOrderInOrder tree = new BinaryTree_ConstructFromPostOrderInOrder();
        //tree.populate(tree);
        //tree.print("", tree.root);

        TreeNode node = tree.buildTree();
        tree.print("", node);
    }

    private void populate(BinaryTree_ConstructFromPostOrderInOrder tree) {
        int[] arr = {3, 9, 20, 0, 0, 7, 15};
        root = insertLevelOrder(arr, root, 0);
    }

    public TreeNode insertLevelOrder(int[] arr, TreeNode node, int key){
        // Base case for recursion
        if (key < arr.length) {
            TreeNode temp = null;
            if (arr[key] != 0) {
                temp = new TreeNode(arr[key]);
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

    public void print(String prefix, TreeNode node) {
        if (node != null) {
            print(prefix +"\t\t", node.right);
            System.out.println ( prefix +("\t |- ") + node.val);
            print( prefix + "\t\t", node.left);
        }
    }

}

