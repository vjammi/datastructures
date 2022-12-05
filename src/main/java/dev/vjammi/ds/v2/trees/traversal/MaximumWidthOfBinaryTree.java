package dev.vjammi.ds.v2.trees.traversal;

/*
    662. Maximum Width of Binary Tree
    Given the root of a binary tree, return the maximum width of the given tree.
    The maximum width of a tree is the maximum width among all levels.
    The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
    where the null nodes between the end-nodes are also counted into the length calculation.
    It is guaranteed that the answer will in the range of 32-bit signed integer.

    Input: root = [1,3,2,5,3,null,9]
    Output: 4
    Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).


    Complexity Analysis
    Let N be the total number of nodes in the input tree.

    Time Complexity: O(N)
    We visit each node once and only once. And at each visit, it takes a constant time to process.

    Space Complexity: O(N)
    We used a queue to maintain the nodes along with its indices, which is the main memory consumption of the algorithm.
    Due to the nature of BFS, at any given moment, the queue holds no more than two levels of nodes. In the worst case,
    a level in a full binary tree contains at most half of the total nodes (i.e. N/2​), i.e. this is also the
    level where the leaf nodes reside. Hence, the overall space complexity of the algorithm is O(N).

*/

public class MaximumWidthOfBinaryTree {
}
