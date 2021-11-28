package ds.binarytree;

/*
814. Binary Tree Pruning
Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
A subtree of a node node is node plus every node that is a descendant of node.
* */
public class BinaryTreePruning {

    public class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode pruneTree(TreeNode root) {
        if(root == null)
            return root;

        dfs(root);

        // Finally, if the left and right of root is null and the val of root itself is 0, return null
        if (root.left == null && root.right == null && root.val == 0)
            return null;

        return root;
    }

    private TreeNode dfs(TreeNode node){
        if (node == null)
            return null;

        node.left = dfs(node.left);
        node.right = dfs(node.right);


        if (node.left == null && node.right == null && node.val == 0)
            return null; // Optimized way for the node to be removed. The current node with zero val is removed, by returning null. Holds good for the root too.
        else
            return node;

    }

/*
    public void pruneTree() {
        pruneTree(root);
    }
    TreeNode root = null;

    public static void main(String args[]) {
        BinaryTreePruning btp = new BinaryTreePruning();
        int[] arr = {1, 1, 0, 1, 1, 0, 1, 0};
        for (int i = 0; i < arr.length; i++) {
        }
        btp.put();

        System.out.println("\nBefore Pruning...");
        btp.postOrder();

        System.out.println("\nPruning...");
        btp.pruneTree();

        System.out.println("\nAfter Pruning...");
        btp.postOrder();
    }

    public void put() {
        //this.root = put(root, x);
        root = new TreeNode(1);

        this.root.left = new TreeNode(1);
        this.root.left.left = new TreeNode(1);
        this.root.left.right = new TreeNode(1);
        this.root.left.left.left = new TreeNode(0);

        this.root.right = new TreeNode(0);
        this.root.right.left = new TreeNode(0);
        this.root.right.right = new TreeNode(1);
    }

    private void postOrder() {
        postOrder(root);
    }

    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(" " + node.val);
    }
*/
}
