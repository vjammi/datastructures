package io.dev.v2.trees.construct;

/**
    108. Convert Sorted Array to Binary Search Tree   https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
         Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
         A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
         Example 1:
         Input: nums = [-10,-3,0,5,9]
         Output: [0,-3,9,-10,null,5]
         Explanation: [0,-10,5,null,-3,null,9] is also accepted:

         Solution: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/solution/

         Construct BST from Inorder Traversal: Why the Solution is Not Unique
         It's known that inorder traversal of BST is an array sorted in the ascending order.
         Having sorted array as an input, we could rewrite the problem as Construct Binary Search Tree from Inorder Traversal.

         Does this problem have a unique solution, i.e. could inorder traversal be used as a unique identifier to encore/decode BST? The answer is no.
         Here is the funny thing about BST.
            Inorder traversal is not a unique identifier of BST.
        At the same time both
            preorder and postorder traversals are unique identifiers of BST.
         From these traversals one could restore the inorder one:
            inorder = sorted(postorder) = sorted(preorder), and
            inorder + postorder or
            inorder + preorder are both unique identifiers of whatever binary tree.
         So, the problem "sorted array -> BST" has multiple solutions.

         Here we have an additional condition: the tree should be height-balanced, i.e. the depths of the two subtrees of every node never differ by more than 1.
         Does it make the solution to be unique? Still no.
         Basically, the height-balanced restriction means that at each step one has to pick up the number in the middle as a root.
         That works fine with arrays containing odd number of elements but there is no predefined choice for arrays with even number of elements.
         One could choose left middle element, or right middle one, and both choices will lead to different height-balanced BSTs.
         Let's see that in practice:
            in Approach 1 we will always pick up left middle element [Left Middle Node as a Root],
            in Approach 2 - right middle one [right Middle Node as a Root].
        That will generate different BSTs but both solutions will be accepted.

 */
public class SortedArrayToBST {


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

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length ==0) return null;

        return buildTree(nums, 0, nums.length-1);
    }

    public TreeNode buildTree(int[] nums, int start, int end) {

        if (start>end)
            return null;

        // Find the middle element for the arr.
        int mid = (start+end)/2;

        // create the node. The mid becomes the root of the BST subtree.
        TreeNode node = new TreeNode(nums[mid]);

        // Recursively form balanced BSTs using the left and right halves of the original arr
        node.left  = buildTree(nums, start, mid-1);
        node.right = buildTree(nums, mid+1, end);

        return node;
    }
}
