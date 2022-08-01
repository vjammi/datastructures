package ds.patterns.trees.traversal;

/**
 * 572. Subtree of Another Tree
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure
 * and node values of subRoot and false otherwise.
 *
 * A subtree of a binary tree is a tree that consists of a node in tree and all of this node's descendants.
 * The tree could also be considered as a subtree of itself.
 *
 * */
public class SubtreeOfAnotherTree {


     // Definition for a binary tree node.
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

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) // Constraint: subRoot to have 1 <= size <= 1000
            return false;

        if (isSame(root, subRoot))
            return true;

        boolean foundInLeft  = isSubtree(root.left, subRoot);
        boolean foundInRight = isSubtree(root.right, subRoot);

        if (foundInLeft || foundInRight)
            return true;

        return false;
    }


    private boolean isSame(TreeNode node, TreeNode subNode){
        if (node == null && subNode == null)
            return true;

        if ( (node == null && subNode != null) || (node != null && subNode == null) )
            return false;

        if (node.val != subNode.val)
            return false;

        boolean leftSubTreeMatches  = isSame(node.left, subNode.left);
        boolean rightSubTreeMatches = isSame(node.right, subNode.right);

        if (leftSubTreeMatches && rightSubTreeMatches)
            return true;

        return false;
    }


}
