package dev.vjammi.ds.v2.design.problems;

/**
 *  173. Binary Search Tree Iterator
 *  Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *
 *      BSTIterator(TreeNode root)
 *          Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor.
 *          The pointer should be initialized to a non-existent number smaller than any element in the BST.
 *     boolean hasNext()
 *          Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 *     int next()
 *          Moves the pointer to the right, then returns the number at the pointer.
 *
 *  Notice that by initializing the pointer to a non-existent smallest number,
 *     the first call to next() will return the smallest element in the BST.
 *  You may assume that next() calls will always be valid.
 *     That is, there will be at least a next number in the in-order traversal when next() is called.
 *
 * Example 1:
 * Input
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * Output
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 *
 * Explanation
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // return 3
 * bSTIterator.next();    // return 7
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 9
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 15
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 20
 * bSTIterator.hasNext(); // return False
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [1, 105].
 *     0 <= Node.val <= 106
 *     At most 105 calls will be made to hasNext, and next.
 *
 * Follow up:
 *     Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
 **/
public class BinarySearchTreeIterator {

    /**
     *  Approach #1: The first approach that we will look at is based on this idea. We will be using additional memory
     *  and we will flatten the binary search tree into an array. Since we need the elements to be in a sorted order,
     *  we will do an inorder traversal over the tree and store the elements in a new array and then build the iterator
     *  functions using this new array.
     *
     *  1. Initialize an empty array that will contain the nodes of the binary search tree in the sorted order.
     *
     *  2. We traverse the binary search tree in the inorder fashion and for each node that we process, we add it to our array nodes.
     *     Note that before processing a node, its left subtree has to be processed (or recursed upon) and after processing a node,
     *     its right subtree has to be recursed upon.
     *
     *  3. Once we have all the nodes in an array, we simply need a pointer or an index in that array to implement the two functions next and hasNext.
     *     Whenever there's a call to hasNext, we simply check if the index has reached the end of the array or not. For the call to next function,
     *     we simply return the element pointed by the index. Also, after a the next function call is made, we have to move the index one step forward
     *     to simulate the progress of our iterator.
     */


    /**
     * Approach #2: Iterative Inorder Traversal
     *     Load within the Constructor
     *          Pushing the elements into stack until we encounter a null to the left
     *     next()
     *          Waiting for the element to be popped from within next()
     *          We add back the right of the element into the stack when an element is popped off the stack.
     **/

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public BinarySearchTreeIterator(TreeNode root) {

    }

    public int next() {
        return 0;
    }

    public boolean hasNext() {
        return false;
    }



}
