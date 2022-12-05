package dev.vjammi.ds.v1.bst;

/**
    Given the root of a binary search tree and a node p in it, return the in-order successor of that node in the BST. If the given node has no in-order successor in the tree, return null.
    The successor of a node p is the node with the smallest key greater than p.val.
    [6,2,8,0,4,7,9,null,null,3,5]
    2

    *** Successor of a node is the next max value of the node
                 6                           Successor of 6 is 7
            2        8                       Successor of 2 is 3, Successor of 8 is 9
          0   4    7   9                     Successor of 9 is null
            3   5

 */
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
        //successor(root, p, null);
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

    boolean pFound;
    private void successor(TreeNode node, TreeNode p, TreeNode parent) {
        if (node == null)
            return;

        if (successor != null)
            return;

        if (p.val < node.val){
            parent = node;
            successor(node.left, p, parent) ;
        }else if (p.val > node.val){
            parent = node;
            successor(node.right, p, parent);
        }else if (p.val == node.val){
            pFound = true;
            if (parent == null){ // It has to the root of the tree. At this time pFound == true
                TreeNode minOfRight =  node.right;
                while (minOfRight != null && minOfRight.left != null){
                    minOfRight = minOfRight.left;
                }
                successor = minOfRight; // Successor is not node.right but the smallest of the right subtree, which could be null
            }else if (parent!=null && parent.val > node.val){ // left side of the tree
                if (node.right != null){
                    TreeNode minOfRight =  node.right;
                    while (minOfRight != null && minOfRight.left != null){
                        minOfRight = minOfRight.left;
                    }
                    successor = minOfRight; // Successor is not node.right but the smallest of the right subtree, which could be null
                }else{
                    successor = parent; // Successor is the parent because node.right  is null
                }
            }else if (parent!=null && parent.val < node.val){ // right side of the tree
                TreeNode minOfRight =  node.right;
                while (minOfRight != null && minOfRight.left != null){
                    minOfRight = minOfRight.left;
                }
                successor = minOfRight; // Successor is not node.right but the smallest of the right subtree, which could be null
            }
            return;
        }

        if (pFound && successor == null && node.val > p.val){
            successor = node;
        }
    }

}
