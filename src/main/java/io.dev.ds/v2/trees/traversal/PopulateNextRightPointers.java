package dev.vjammi.ds.v2.trees.traversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 *                        1
 *
 *                  2     >      3
 *
 *           4   >   5     >    6   >    7
 *
 *        8 > 9 > 10 > 11  > 12 > 13 > 14 > 15
                1    2>         2>3
                1    2>         2>3
 *              2    4>         4>5
 *              2    5>         5>6
 *      Solution
 *          We will need to use a level order traversal.
 *          At any point we are going down and across/laterally. We do not need to recurse or backtrack.
 *          At any point we are connecting the nodes at its next level, children's level
 *          We will assign the next pointers to the
 *              1. left node
 *              2. right node
 *
 */

public class PopulateNextRightPointers {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {

        if (root == null)
            return root;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            Node node = queue.poll();
            // Assigning next pointer to a left node
            if (node.left !=null && node.right !=null ){
                node.left.next = node.right;
            }else if(node.left != null && node.right == null){
                Node nextNode = node.next;
                if (nextNode == null)
                    node.left.next = null;
                else
                    whileNextNotNull(nextNode, node.left);
            }

            // Assigning next pointer to a right node
            if(node.right != null ){
                Node nextNode = node.next;
                if (nextNode == null)
                    node.right.next = null;
                else
                    whileNextNotNull(nextNode, node.right);
            }

            if (node.left != null){
                queue.add(node.left);
            }
            if (node.right != null){
                queue.add(node.right);
            }

        }
        return root;
    }

    private void whileNextNotNull(Node nextNode, Node left) {
        while (nextNode != null) {
            if (nextNode.left != null) {
                left.next = nextNode.left;
                break;
            } else if (nextNode.right != null) {
                left.next = nextNode.right;
                break;
            } else {
                nextNode = nextNode.next;
            }
            left.next = null;
        }
    }

}
