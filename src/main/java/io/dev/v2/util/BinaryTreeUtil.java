package io.dev.v2.util;

/**
 *  BinaryTreeUtil tree = new BinaryTreeUtil();
 *  tree.build(arr);
 *  tree.getRoot();
 *  tree.print(" ", root);
 *  subject.levelOrder(root)
 **/
public class BinaryTreeUtil {
    private TreeNode root;
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public TreeNode build(int[] arr) {
        this.root = build(arr, 0, arr.length-1);
        return root;
    }
    public TreeNode getRoot() {
        return root;
    }
    public TreeNode build(int[] nums, int start, int end) {
        if (start > end)
            return null;

        // Find the middle element for the arr.
        int mid = (start+end)/2;

        // create the node. The mid becomes the root of the BST subtree.
        TreeNode node = new TreeNode(nums[mid]);

        // Recursively form balanced BSTs using the left and right halves of the original arr
        node.left  = build(nums, start, mid-1);
        node.right = build(nums, mid+1, end);

        return node;
    }
    public void print(String prefix, TreeNode node) {
        if (node != null) {
            print(prefix + "\t", node.right);
            System.out.println(prefix + (" \t |-") + node.val);
            print(prefix + "\t", node.left);
        }
    }
}
