package dev.vjammi.ds.v2.trees.narray;

import java.util.List;

public class SerializeDeserializeNAryTree {

    class Node {
        public int val;
        public List<Node> children;
        public Node() {}
        public Node(int _val) {  val = _val;  }
        public Node(int _val, List<Node> _children) {  val = _val; children = _children; }
    }

    class Codec {
        // Encodes a tree to a single string.
        public String serialize(Node root) {

            return null;
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {

            return null;
        }
    }

    public static void main(String[] args) {
        // Your Codec object will be instantiated and called as such:
        // Codec codec = new Codec();
        // codec.deserialize(codec.serialize(root));
    }
}
