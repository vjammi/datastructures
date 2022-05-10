package ds.patterns.trees.path;

/**
 * 298. Binary Tree Longest Consecutive Sequence
 * Given the root of a binary tree, return the length of the longest consecutive sequence path.
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The longest consecutive path needs to be from parent to child (cannot be the reverse).
 *
 * Example 1:
 * Input: root = [1,null,3,2,4,null,null,null,5]
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 *
 * */

public class BinaryTreeLongestConsecutiveSequence {

    private int result = 0;

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

    public int longestConsecutive(TreeNode root){
        if (root == null)
            return 0;

        longestSequence(root);
        return result;
    }

    private int longestSequence(TreeNode node){
        // If the current node is null then the path length is 0
        if (node == null)
            return 0;

        int left = longestSequence(node.left);
        int right = longestSequence(node.right);

        //*** Now do something with the previous left and right nodes return values ***

        int max =1; // LongestConsecutive path at the current node

        // Checking if the value of the current node is one less than its left child. If yes then they can form  a consecutive sequence.
        // We keep the longest we get - either from teh left or from teh right child.
        if (node.left == null || node.left.val == node.val + 1) {
            max = Math.max(left+1, max); // max at teh current node
        }
        // Checking if the value of the current node one less than its right child. If yes then they can form  a consecutive sequence.
        if (node.right == null || node.right.val == node.val + 1)
            max = Math.max(right+1, max); // max at the current node

        // If the current node cannot form a consecutive path with either children.
        // Then the Longest Consecutive path including the current node is 1.
        result = Math.max(result, max); // max within the tree as a whole

        return max;
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
