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
    private int nextPostorderIndex = 0;
    private Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;

        for(int i=0; i< inorder.length; i++){
            inorderMap.put(inorder[i], i);
        }
        nextPostorderIndex = postorder.length - 1;
        return buildTree(0, inorder.length-1);
    }

    public TreeNode buildTree(int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex){ // or we could also say (rightIndex < leftIndex){
            return null;
        }

        int postOrderNodeVal = postorder[nextPostorderIndex--];
        TreeNode currNode = new TreeNode(postOrderNodeVal);
        int inorderIndexForCurrNode = inorderMap.get(postOrderNodeVal);

        currNode.right = buildTree(inorderIndexForCurrNode+1, rightIndex);
        currNode.left = buildTree(leftIndex, inorderIndexForCurrNode - 1);

        //TreeNode right = buildTree(inorderIndexForRootValue+1, rightIndex);
        //TreeNode left = buildTree(leftIndex, inorderIndexForRootValue - 1);
        //rootNode.right = right;
        //rootNode.left = left;

        return currNode;
    }

    private void populate(BinaryTree_ConstructFromPostOrderInOrder tree) {
        int[] arr = {3, 9, 20, 0, 0, 7, 15};
        root = insertLevelOrder(arr, root, 0);
    }

    // Function to insert nodes in level order
    public static void main(String[] args) {
        BinaryTree_ConstructFromPostOrderInOrder tree = new BinaryTree_ConstructFromPostOrderInOrder();
        tree.populate(tree);
        tree.print("", tree.root);

        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode node = tree.buildTree(inorder, postorder);
        tree.print("", node);
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

