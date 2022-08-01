package ds.patterns.trees.path;

/**
 * 298. Binary Tree Longest Consecutive Sequence
 * Given the root of a binary tree, return the length of the longest consecutive sequence path.
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The longest consecutive path needs to be from parent to child (cannot be the reverse).
 *
 * Example 1:
 * Input: root = [1,null,3,2,4,null,null,null,5]
 *                          1
 *                      /      \
 *                  null        3
 *                           /     \
 *                         2        4
 *                       /  \     /   \
 *                    null  null null  5
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 *
 **/

public class BinaryTreeLongestConsecutiveSequence {

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

    private int globalMax = Integer.MIN_VALUE;

    public int longestConsecutive(TreeNode root){
        if (root == null)
            return 0;

        longestSequence(root);

        return globalMax;
    }

    private int longestSequence(TreeNode node){
        // If the current node is null then the path length is 0
        if (node == null)
            return 0;

        int left = longestSequence(node.left);
        int right = longestSequence(node.right);

        //*** Now do something with the previous left and right nodes return values ***

        int longestConsecutiveSeqAtCurrentNode = 1; // If left and right is null we will receive 0 from left and right so the longestConsecutivePath at the current node will be 1

        // Check if the value of the current node is one less than its left or right child.
        // If true, the max of left or right can add to the consecutive sequence,
        // else we bubble up 1 from the current node to its parent
        // If the current node cannot form a consecutive path with either children, then the Longest Consecutive path including the current node is 1.

        // Checking if the value of the current node one less than its left child. If yes, then take the max of the left and current node
        if (node.left != null && node.left.val == node.val+1) {
            longestConsecutiveSeqAtCurrentNode = Math.max(longestConsecutiveSeqAtCurrentNode, left+1); // max at teh current node
        }
        // Checking if the value of the current node one less than its right child. If yes, then we take the max of the left and right node
        if (node.right != null && node.right.val == node.val+1)
            longestConsecutiveSeqAtCurrentNode = Math.max(longestConsecutiveSeqAtCurrentNode, right+1); // max at the current node

        globalMax = Math.max(globalMax, longestConsecutiveSeqAtCurrentNode); // max within the tree as a whole

        // If the current node cannot form a consecutive path with either children, then the Longest Consecutive path including the current node is 1.
        return longestConsecutiveSeqAtCurrentNode;
    }

    private void longestConsecutive() {
        TreeNode root = new TreeNode();
        root.val = 1;
        TreeNode rootLeft = new TreeNode();
        rootLeft.val  = 2;
        root.left = rootLeft;
        TreeNode rootRight = new TreeNode();
        rootRight.val = 5;
        root.right = rootRight;

        TreeNode rootLeftLeft = new TreeNode();
        rootLeftLeft.val  = 3;
        rootLeft.left = rootLeftLeft;
        TreeNode rootLeftRight = new TreeNode();
        rootLeftRight.val = 4;
        rootLeft.right = rootLeftRight;

        System.out.println(longestConsecutive(root));
    }

    public static void main(String[] args) {
        BinaryTreeLongestConsecutiveSequence lcs = new BinaryTreeLongestConsecutiveSequence();
        lcs.longestConsecutive();
    }
}
