package ds.patterns.trees.bst;

public class ValidateBinarySearchTree {

    private TreeNode root;

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

    public boolean isValidBST(TreeNode root) {
        // Approach 1 - Lower and Upper bound checks
        return validate(root,Integer.MIN_VALUE, Integer.MAX_VALUE);

        // Approach 2 - Inorder processing
        // inorder(root);
        // return isValid;

    }

    /**
      Time Complexity:  O(N) - Assuming we have n nodes in the binary tree, in the worst case we traverse all the n nodes
      Space Complexity: O(N) - The space required by the recursion stack [in the worst case for a skewed tree], when we perform the tree traversal.

     The current node's value must be between low and high.
                                      4
                                 2         6
                             1      3   5      7

      lower bound (node) upper bound

                                  -inf(4)+inf

                         -inf(2)4             4(6)+inf

                    -inf(1)2    2(3)4    4(5)6      6(7)+inf

                         current  low      high     isValid
                         4        null              true
         Left Subtree    2        null     4
         Right Subtree   6        4                false
     */
    public boolean validate(TreeNode node, Integer lowerBound, Integer upperBound) {
        // Empty trees are valid BSTs.
        if (node == null) {
            return true;
        }

            //  The current node's value must be between low and high.
        if ((lowerBound != null && node.val <= lowerBound) || (upperBound != null && node.val >= upperBound)) {
            return false;
        }

        // The left and right subtree must also be valid.
        boolean left   = validate(node.left,  lowerBound, node.val);
        boolean right  = validate(node.right, node.val,   upperBound);

        if (left == true && right == true)
            return true;
        else
            return false;
    }

    /**
                10
            5          15
        3      7           13   [13 is an invalid node]

     */
    // In a valid BST, the inorder traversal of the tree will give us the elements in increasing order.
    // Lets use that property to validate the traversal. The value of the node currently processing should be > than the value we have already processed
    private boolean isValid = true;
    private TreeNode  prevNode = null;
    private TreeNode inorder(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode left = inorder(node.left);
        // if (left!=null && left.val >= node.val) isValid = false; // Not required any more with the introduction of previous

        // Inorder node check -  the value of the current node needs to be greater than the previous node we have already processed.
        // <= will work if there are no duplicates. to handle duplicates we can return false when node.val < prevNode.val
        if (prevNode!=null && node.val <= prevNode.val ) // node.val < prevNode.val - to handle duplicates
            isValid = false;

        // Inorder node assignment
        prevNode = node;

        TreeNode right = inorder(node.right);
        // if (right!=null && right.val <= node.val)  isValid = false; // Not required any more with the introduction of previous

        return node;
    }

    public static void main(String[] args) {
        ValidateBinarySearchTree obj = new ValidateBinarySearchTree();
        obj.testIsValidBST();
    }

    private void testIsValidBST() {
        TreeNode root = new TreeNode(5);

        root.left  = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);

        root.right = new TreeNode(6);
        root.right.left = new TreeNode(3);          // Invalid BST
        root.right.right = new TreeNode(7);

        boolean isValid = isValidBST(root);
    }
}
