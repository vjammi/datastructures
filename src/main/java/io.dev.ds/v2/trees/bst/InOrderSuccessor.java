package dev.vjammi.ds.v2.trees.bst;

/**
 * Given the root of a binary search tree and a node p in it, return the in-order successor of that node in the BST.
 * If the given node has no in-order successor in the tree, return null. *
 * The successor of a node p is the node with the smallest key greater than p.val.
 * [6,2,8,0,4,7,9,null,null,3,5]
 *  2
 *
 * The successor of a node p is the node with the smallest key greater than p.val.
 * ** Successor of a node is the next max value of the node
    //                   6                Successor of 6 is 7
    //             2           8          Successor of 2 is 3, Successor of 8 is 9
    //         0      4     7     9       Successor of 9 is null
    //             3    5                 Successor of 3 is 4
 */
/**
 *  1. Find the node for which we are trying to find the ancestor
 *
 *  2. If
 *         Node has a right subtree then find the min in the right subtree
 *     else
 *        - Walk  from the root to that current node to find the deepest/nearest ancestor for which the current node will be to its left
 *        - This can be done by storing the node from where we take left until we reach the current node.
 *        - while traversing if we find another deeper node where the current node will be to its left, then the ancestor wil be updated
 *
 *  Reference:
 *      https://youtu.be/5cPbNCrdotA?t=947
 */

public class InOrderSuccessor {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    //               6                Successor of 6 is 7
    //         2           8          Successor of 2 is 3, Successor of 8 is 9
    //     0      4     7     9       Successor of 9 is null
    //         3    5                 Successor of 3 is 4
    public TreeNode inorderSuccessor(TreeNode root, TreeNode pNode) {
        // Fetch Node - Not needed
        // TreeNode pNodeFetched = search(root, pNode.val);

        // If node has a right child find the min of the right node
        if (pNode.right != null){
            TreeNode successor = findMinToTheRight(pNode);
            return successor;
        }else{
            TreeNode successor = null;
            TreeNode current = root;
            while (current != pNode ){
                if (pNode.val < current.val) {
                    successor = current; // So far this is the deepest node for which current node is in left
                    current = current.left;
                }else{
                    current = current.right;        //  For p==3, we traverse 6 >left> 2 >right> 4 >left> 3
                }
            }
            return successor;
        }
    }

    public TreeNode findMinToTheRight(TreeNode node) {
        if (node == null)
            return null;

        while (node.left != null){
            node = node.left;
        }
        System.out.println("findMinToTheRight: " +node.val);
        return node;
    }

    private TreeNode search(TreeNode node, int val) {
        if (node == null)
            return null; // node not found return null

        if (val < node.val){
            node.left = search(node.left, val); // go left
        }else if (val > node.val){
            node.right = search(node.right, val); // go right
        }else if (val == node.val){
            System.out.println(node.val);
            return node; // node found return node
        }

        return node;
    }


    TreeNode successor = null;
    private void successor1(TreeNode node, TreeNode p) {
        if (node == null)
            return;

        if (p.val < node.val) {
            System.out.println(" < " + node.val + " " + node.right);
            successor = node;
            successor1(node.left, p);
        } else if (p.val >= node.val) {
            System.out.println(" > " + node.val + " " + node.right);
            successor1(node.right, p);
        }
    }

    boolean pFound;
    private void successor(TreeNode node, TreeNode p, TreeNode parent) {
        if (node == null)
            return;

        if (successor != null)
            return;

        if (p.val < node.val) {
            parent = node;
            successor(node.left, p, parent);
        } else if (p.val > node.val) {
            parent = node;
            successor(node.right, p, parent);
        } else if (p.val == node.val) {
            pFound = true;
            if (parent == null) { // It has to the root of the tree. At this time pFound == true
                TreeNode minOfRight = node.right;
                while (minOfRight != null && minOfRight.left != null) {
                    minOfRight = minOfRight.left;
                }
                successor = minOfRight; // Successor is not node.right but the smallest of the right subtree, which could be null
            } else if (parent != null && parent.val > node.val) { // left side of the tree
                if (node.right != null) {
                    TreeNode minOfRight = node.right;
                    while (minOfRight != null && minOfRight.left != null) {
                        minOfRight = minOfRight.left;
                    }
                    successor = minOfRight; // Successor is not node.right but the smallest of the right subtree, which could be null
                } else {
                    successor = parent; // Successor is the parent because node.right  is null
                }
            } else if (parent != null && parent.val < node.val) { // right side of the tree
                TreeNode minOfRight = node.right;
                while (minOfRight != null && minOfRight.left != null) {
                    minOfRight = minOfRight.left;
                }
                successor = minOfRight; // Successor is not node.right but the smallest of the right subtree, which could be null
            }
            return;
        }

        if (pFound && successor == null && node.val > p.val) {
            successor = node;
        }
    }

}
