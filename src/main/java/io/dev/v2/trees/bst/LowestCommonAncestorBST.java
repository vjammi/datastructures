package io.dev.v2.trees.bst;

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
        lca(root, p, q);
        return lca;
    }

    /**
     *  Option 1
     *  1. Traverse the tree from the root node.
     *      1.1 If P and Q are to the left and right of the node, node is the lca
     *  2. If we reach either P or Q,
     *      it would be the ancestor of itself and the other node.
     *  3. Traverse the nodes in PreOrder traversal.
     *     Optimization: Since this is a BST, we could guide the traversal using BST properties
     *
     * Option 2
     * 1. Traverse the tree from the root node.
     *      1.1 If both the nodes p and q are in the right subtree, then continue the search the right subtree
     *      1.2 If both the nodes p and q are in the left subtree, then continue the search the left subtree
     * 2. If both step 1.1 & 1.2 are not true,
     *      it would mean the current node is the lca, return node as the LCA.
     *
     *  Runtime - log(n)
     **/
    TreeNode   lca;
    void lca(TreeNode node, TreeNode p, TreeNode q){
        if (node == null)
            return;

        int nodeVal = node.val; int pVal = p.val; int qVal = q.val;

        // Scenario 1: P and Q are to the left and right of the node, node is the lca
        if( (nodeVal > pVal && nodeVal < qVal) || (nodeVal<pVal && nodeVal>qVal) ){
            lca = node;
            return;
        }

        // Scenario 2: The first node that we reach P or Q, will be the ancestor of itself and the other node
        if (nodeVal == pVal){ // P found and Q is either on the left or right subtree. Regardless, P is the lca
            lca = p;
            return;
        }else if (nodeVal == qVal){ // Q found and P is on the left or right subtree. Regardless, Q is the lca
            lca = q;
            return;
        }

        // If p and q are NOT on either sides of the node (Scenario 1) && We have NOT found p or q yet (Scenario 2)
        // Then, we will further traverse the tree.
        //  We could traverse the tree using preorder traversal which would be an order of n runtime to find the lca in worst case
        //  As an optimization since the tree is a BST, we could the use bst properties to guide the search in log(n) time
        //
        // Optimization for log(n) search - Guide the search using BST properties

        // P and Q are either in the left or right subtree
        if (pVal < nodeVal && qVal < nodeVal )
            lca(node.left, p, q);

        // P and Q are either in the left or right subtree
        if (pVal > nodeVal && qVal > nodeVal)
            lca(node.right, p, q);


    }

    /**
     *  Runtime - n
     * */
    void lca1(TreeNode node, TreeNode p, TreeNode q){
        if (node == null)
            return;

        int nodeVal = node.val;
        int pVal = p.val;
        int qVal = q.val;

        // Scenario 1: P and Q are to the left and right of the node, node is the lca
        if( (nodeVal > pVal && nodeVal < qVal) || (nodeVal<pVal && nodeVal>qVal) ){
            lca = node;
            return;
        }

        // Scenario 2: If we ever reach a node P or Q, it will be the ancestor of itself and the other node.
        // P found and Q is either on the left or right subtree. Regardless, P is the lca
        if (nodeVal == pVal){ // P found and Q is on the left or right subtree. Regardless, P is the lca
            lca = p;
            return;
        }
        // Q found and P is on the left or right subtree. Regardless, Q is the lca
        else if (nodeVal == qVal){
            lca = q;
            return;
        }

        lca1(node.left, p, q);
        lca1(node.right, p, q);

    }

}
