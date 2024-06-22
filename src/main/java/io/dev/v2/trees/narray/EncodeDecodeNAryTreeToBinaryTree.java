package io.dev.v2.trees.narray;

import java.util.List;

public class EncodeDecodeNAryTreeToBinaryTree {

    class Node {
        public int val;
        public List<Node> children;
        public Node() { }
        public Node(int _val) { val = _val; }
        public Node(int _val, List<Node> _children) { val = _val; children = _children; }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {

            return null;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {

            return null;
        }
    }

    public static void main(String[] args) {
        // Your Codec object will be instantiated and called as such:
        // Codec codec = new Codec();
        // codec.decode(codec.encode(root));
    }
}
