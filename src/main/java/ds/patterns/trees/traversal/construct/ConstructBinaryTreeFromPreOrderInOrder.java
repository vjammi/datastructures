package ds.patterns.trees.traversal.construct;

import java.util.HashMap;
import java.util.Map;

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
// Break the recursion by returning null [when right index crosses the left index.
// The same condition holds good, while traversing both the left and right side of an element in the InOrder array].
// This is similar to a return, while building a tree, when we encounter node == null,
// traversing either the left or the right nodes
// At some point when we hit the root of the leftmost child - with its null left and right pointers.
//      Left index will continue to be 0 but right pointer will now become 0-1 = -1.
//      This is when we return. Right index (0-1) becomes smaller than the left index (0).
// Similarly, at some point when we hit the root of the rightmost child - with null left and right pointers.
//      Left and right index point the same node. Left index will continue to be incremented by 1,
//      thereby crossing the length of the array. However, the right index will continue to be at n-1.
//      This is when we return again. Right index (n-1) becomes smaller than the left index (currentInd + 1 = n)
// Either ways in both cases right index becomes smaller than the left index, causing the return.

//  preorder = {16,10,8,6,7,9,12,11,13,22,20,19,21,24,23,25};
//  inorder  = {6,7,8,9,10,11,12,13,16,19,20,21,22,23,24,25};
//        leftIndex		rightIndex		inOrderNodeIndex		currentNode/nodeVal			return
//
//                3				3										 9							null / R - 9
//        3				3										 9							null / L - 9
//        3				3										 9							9/R
//        x		1+1				0																	null/R - 7
//        x		1				0																	null/L - 7
//        x		0+1=1			1				1						 7							7/R
//        x		0				-1																	null
//        0				1				0						 6							6/L
//        0				3				2						 8							8	[6/L & 9/R]
//        0				7				4						 10
//        0				15				8						 16
public class ConstructBinaryTreeFromPreOrderInOrder {
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

    private int preorderIndex = 0;
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

    // https://youtu.be/FBaSrNSf9po?list=PLFj4kIJmwGu2WedpHdv1p_LrLGvwqDvjZ
    // Inorder  - H D P L A Z C E
    // PreOrder - A D H L P Z C E
    private TreeNode buildTree(int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex){ // or we could also say (rightIndex < leftIndex){
            return null;
        }

        // Select the next preorder node as the current root and increment it preorder index
        int preorderNodeVal = preorder[preorderIndex++];
        TreeNode currNode = new TreeNode(preorderNodeVal); // node on the stack

        // A/Left of +AB & B/Right of +AB
        int inorderIndexForCurrNode = inOrderMap.get(preorderNodeVal); // lookup the index of the preOrderNodeVal within the inorder map.
        currNode.left  = buildTree(leftIndex, inorderIndexForCurrNode - 1 );  // On your way back, add the returned node to the left of the current root node
        currNode.right = buildTree(inorderIndexForCurrNode + 1, rightIndex ); // On your way back, add the returned node to the right of the current root node

        return currNode; // Return the current node to be added to the left or the right side of the parent node.
    }

    private void buildTree() {
        int[] preorder = {3,9,20,15,7};
        int[] inorder  = {9,3,15,20,7};
        //int[] preorder = {16,10,8,6,7,9,12,11,13,22,20,19,21,24,23,25};
        //int[] inorder  = {6,7,8,9,10,11,12,13,16,19,20,21,22,23,24,25};
        TreeNode root = buildTree(preorder, inorder);
        print("", root);
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromPreOrderInOrder tree = new ConstructBinaryTreeFromPreOrderInOrder();
        tree.print("", tree.root);
        tree.buildTree();
    }

    public void print(String prefix, TreeNode node) {
        if (node != null) {
            print(prefix +"\t\t", node.right);
            System.out.println ( prefix +("\t |- ") + node.val);
            print( prefix + "\t\t", node.left);
        }
    }

}