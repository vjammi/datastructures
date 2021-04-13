package ds.binarytree;

public class BinaryTreePruning {

    TreeNode root = null;

    public class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int x) {
            val = x;
        }
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


    public void pruneTree() {
        TreeNode node = pruneTree(root);
    }

    public TreeNode pruneTree(TreeNode node) {

        if (node == null) {
            //System.out.println("Node is null...");
            return null;
        }

        node.left = pruneTree(node.left);
        node.right = pruneTree(node.right);

        if ((node.left == null && node.right == null) && node.val == 0) {
            //System.out.println("Node value is 0 & Left & Right nodes are null. Node can be removed...) " +node.val);
            System.out.print(" " + node.val);
            return null;
        } else {
            //System.out.println("Node value is " +node.val);
            System.out.print(" " + node.val);
        }

        return node;
    }

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


}
