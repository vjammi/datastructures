package ds.patterns.trees.traversal;

/**
    543. Diameter of Binary Tree
    Given the root of a binary tree, return the length of the diameter of the tree.
    The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
    The length of a path between two nodes is represented by the number of edges between them.
    Input: root = [1,2,3,4,5]
    Output: 3
    Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

    Input: root = [1,2]
    Output: 1

 * */
public class DiameterOfBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        diameter =0;
        dfs(root);

        return diameter;
    }

    int diameter;
    private int dfs(TreeNode node){
        if (node == null )
            return 0;

        int left  = dfs(node.left);
        int right = dfs(node.right);

        diameter = Math.max(diameter, left + right); // the largest diameter of teh left or right child

        return Math.max(left, right) + 1; // longest of the left or right child, plus 1 for the path connecting the parent
    }

}
