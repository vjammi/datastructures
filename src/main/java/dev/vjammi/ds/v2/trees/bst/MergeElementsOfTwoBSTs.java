package dev.vjammi.ds.v2.trees.bst;

/**
    1305. All Elements in Two Binary Search Trees
    Given two binary search trees root1 and root2. Return a list containing all the integers from both trees sorted in ascending order.

    Option 1: InOrder traversal of th tree nodes in ascending order. So we can
      - Traverse the first tree in inorder and store it in list 1   - O(M)
      - Traverse the second tree in inorder and store it in list 2  - O(N)
      - Merge the two sorted lists to combine the lists  - sorting is nlog(n) and in this (M+N)log((M+N))
    Option 2: InOrder traversal using iterative method using stack
      - Gives nodes in ascending order
      - Uses the height of the tree on the stack
*/
public class MergeElementsOfTwoBSTs {

}
