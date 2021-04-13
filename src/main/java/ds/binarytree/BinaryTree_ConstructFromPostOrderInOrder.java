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

    int[] postorder = {9,15,7,20,3};
    int[] inorder = {9,3,15,20,7};
    TreeNode root2;
    int currentPostOrderIndex = 0;
    Map<Integer, Integer> inorderMap = new HashMap<>();

    public void buildTree(BinaryTree_ConstructFromPostOrderInOrder tree) {

        for(int i=0; i< inorder.length; i++){
            inorderMap.put(inorder[i], i);
        }
        currentPostOrderIndex = postorder.length - 1;
        root2 = buildTree(0, inorder.length - 1);
        print("", tree.root2);
    }

    public TreeNode buildTree(int leftIndex, int rightIndex) {

        if (leftIndex > rightIndex){
            return null;
        }

        int currentInorderIndex = inorderMap.get(postorder[currentPostOrderIndex]);
        TreeNode node = new TreeNode(inorder[currentInorderIndex]);

        currentPostOrderIndex --;

        node.right = buildTree(currentInorderIndex+1, rightIndex);
        node.left = buildTree(leftIndex, currentInorderIndex - 1);

        return node;
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

        tree.buildTree(tree);

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

