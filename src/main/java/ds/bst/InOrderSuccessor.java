package ds.bst;

public class InOrderSuccessor {

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

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;
        successor(root, p);
        return successor;
    }

    TreeNode successor  = null;
    private void successor(TreeNode node, TreeNode p){
        if (node == null)
            return;

        if (p.val < node.val) {
            System.out.println(" < " +node.val +" "+ node.right);
            successor = node;
            successor(node.left, p);
        }else if (p.val >= node.val) {
            System.out.println(" > " +node.val +" "+ node.right);
            successor(node.right, p);
        }
    }
}
