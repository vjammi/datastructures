package ds.patterns.trees.bst;

public class InsertIntoABinarySearchTree {

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

    // O(logN)
    public TreeNode insertIntoBST(TreeNode root, int val) {
        return insert(root,val);
    }

    private TreeNode insert(TreeNode node, int val){
        if (node == null)
            return new TreeNode(val);

        if (val < node.val){
            node.left =  insert(node.left, val);
        }else if (val > node.val) {
            node.right =  insert(node.right, val);
        }else if (val == node.val){
            node.val = val;
        }
        return node;
    }
}
