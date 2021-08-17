package ds.bst;

/**
    235. Lowest Common Ancestor of a Binary Search Tree

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

        if( (nodeVal > pVal && nodeVal < qVal) || (nodeVal<pVal && nodeVal>qVal) ){
            lca = node;
            return;
        }else if (nodeVal == pVal){
            lca = p;
            return;
        }else if (nodeVal == qVal){
            lca = q;
            return;
        }else if (pVal < nodeVal && qVal < nodeVal ){
            lca(node.left, p, q, parent);
        }else if (pVal > nodeVal && qVal > nodeVal){
            lca(node.right, p, q, parent);
        }
    }
}
