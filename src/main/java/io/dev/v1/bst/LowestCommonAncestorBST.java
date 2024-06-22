package io.dev.v1.bst;

/**
    235. Lowest Common Ancestor of a Binary Search Tree
     Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
     According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     Output: 6
     Explanation: The LCA of nodes 2 and 8 is 6.


                    6                       p = 2, q = 8, lca = 6
              2          8                  p = 2, q = 4, lca = 2
            0   4      7   9
             3   5
*/

public class LowestCommonAncestorBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lca(root, p, q, null);
        return lca;
    }

    TreeNode   lca;
    void lca(TreeNode node, TreeNode p, TreeNode q, TreeNode parent){
        if (node == null)
            return;

        int nodeVal = node.val;
        int pVal = p.val;
        int qVal = q.val;

        if( (nodeVal > pVal && nodeVal < qVal) || (nodeVal<pVal && nodeVal>qVal) ){ // P and Q are to the left and right of the node, node is the lca
            lca = node;
            return;
        }else if (nodeVal == pVal){ // P found and Q is on the left or right subtree. Regardless, P is the lca
            lca = p;
            return;
        }else if (nodeVal == qVal){ // Q found and P is on the left or right subtree. Regardless, Q is the lca
            lca = q;
            return;
        }else if (pVal < nodeVal && qVal < nodeVal ){ // P and Q are either in the left or right subtree
            lca(node.left, p, q, parent);
        }else if (pVal > nodeVal && qVal > nodeVal){ // P and Q are either in the left or right subtree
            lca(node.right, p, q, parent);
        }
    }
}
