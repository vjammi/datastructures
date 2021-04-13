package ds.binarytree;

import java.util.HashMap;
import java.util.Map;

public class BinaryTree_ConstructFromPreOrderInOrder {

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

    private void buildTree(BinaryTree_ConstructFromPreOrderInOrder tree) {
        //int[] preorder = {3,9,20,15,7};
        //int[] inorder  = {9,3,15,20,7};

        int[] preorder = {16,10,8,6,7,9,12,11,13,22,20,19,21,24,23,25};
        int[] inorder  = {6,7,8,9,10,11,12,13,16,19,20,21,22,23,24,25};

        print("", buildTree(preorder, inorder));
    }

    private TreeNode root2;
    private int currentPreOrderIndex = 0;
    private int[] preorder;
    private int[] inorder;
    private Map<Integer, Integer> inOrderMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        for(int i = 0 ; i < inorder.length; i++){
            inOrderMap.put(inorder[i], i);
        }

        int leftIndex = 0; int rightIndex = preorder.length - 1;
        return buildTree(leftIndex, rightIndex);
    }

    /**
        Steps: While reading the elements [left ot right] in the PreOrder array and looking for that element in the InOrder array, we construct the binary tree using
                PreOrder traversal [+LR]
            1. On our way forwards [uphill], we add the current node to the stack and traverse the left node/subtree.
                1.1. The recursion of the left subtree is interrupted, when right index crosses the left index.
                   Note: Here we return null. This is similar to a return, while building a tree when we encounter node == null, while traversing either the left or the right nodes
            2. On our way backwards/downhill, while the element is popped out of the stack, we add the returned node to the left of the current node
            3. For the current node if there is a right node/subtree, we will traverse it, which might again have left and right nodes/subtrees.
                3.1 The recursion of the right node/subtree is interrupted when right index crosses the left index.
                    Note: Here we return null. This is similar to a return, while building a tree when we encounter node == null, while traversing either the left or the right nodes
            4. When both the left and right nodes/subtrees of the current node are completely traversed, we return the current node
                    Note: This returned node will intern be added to the left or right side of the parent node.

            Note: Assigning the returned node to the left and right of the current node allows us to build the tree here. We bubble up a returned node building the tree ground up, on our way back the recursion.
                        currentNode.left = returnedLeftNode;
                        currentNode.right = returnedRightNode;
                  Similarly, we we can use the same technique to also return a value on the way back [calculating the sum or size of the node], which can be added to the current nodes value, to be sent up the chain.
                        int size/sum = returnedValueOfTheLeftNode;
                        int size/sum = returnedValueOfTheRightNode;
    * */
    private TreeNode buildTree(int leftIndex, int rightIndex) {

        // Break the recursion by returning null [when right index crosses the left index. The same condition holds good, while traversing both the left and right side of an element in the InOrder array].
        // This is similar to a return, while building a tree, when we encounter node == null while traversing either the left or the right nodes
        if (rightIndex < leftIndex){
            return null;
        }

        // Base Case [+] of +AB
        int currentVal = preorder[currentPreOrderIndex];
        int currentInx = inOrderMap.get(currentVal);
        TreeNode currentNode = new TreeNode(inorder[currentInx]);
        currentPreOrderIndex++;

        // A/Left of +AB
        TreeNode left = buildTree(leftIndex, currentInx - 1);  // Returned left node
        currentNode.left = left; // On your way back, add the returned node to the left of the current node

        // B/Right of +AB
        TreeNode right = buildTree(currentInx + 1, rightIndex); // Returned right node
        currentNode.right = right; // On your way back, add the returned node to the right of the current node

        // Return the current node to be added to the left or the right side of the parent node.
        return currentNode;
    }

    public static void main(String[] args) {
        BinaryTree_ConstructFromPreOrderInOrder tree = new BinaryTree_ConstructFromPreOrderInOrder();
        tree.print("", tree.root);
        tree.buildTree(tree);
    }

    public void print(String prefix, TreeNode node) {
        if (node != null) {
            print(prefix +"\t\t", node.right);
            System.out.println ( prefix +("\t |- ") + node.val);
            print( prefix + "\t\t", node.left);
        }
    }

}
