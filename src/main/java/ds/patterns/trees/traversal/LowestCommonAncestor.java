package ds.patterns.trees.traversal;

public class LowestCommonAncestor {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    TreeNode lca;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lca(root, p, q);
        return lca;
    }

    TreeNode lca(TreeNode node, TreeNode p, TreeNode q){
        if (node == null)
            return null;

        TreeNode left = lca(node.left, p, q);
        TreeNode right = lca(node.right, p, q);

        // If/Once LCA is found, keep bubbling it up the tree
        if (lca != null){
            return lca;
        }

        // else find the LCA
        else if ( (left == p && right == q) || (right == p && left == q) )  {
            lca = node;  // current node is an LCA, with its left and right nodes containing p and q or q and p
            return lca;
        }else if((left == q || right == q)  && node.val == p.val){
            lca = node; // If one among p and q is found, being bubbled up the tree, and the other is the current node
            return lca;
        }else if( (left == p || right == p) && node.val == q.val){
            lca = node; // If one among p and q is found, being bubbled up the tree, and the other is the current node
            return lca;
        }else if (node.val == p.val){
            return p; // found the first node - p or q
        }else if (node.val == q.val){
            return q; // found the first node - p or q
        }

        // Bubble up p or q - if found, else bubble up the current node
        if (left == p || right == p)
            return p;
        else if(left == q || right == q)
            return q;
        else
            return node;
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        lca2(root, p, q);
        System.out.println(ancestor.val);
        return ancestor;
    }

    TreeNode ancestor = null;
    boolean foundAncestor = false;
    private boolean lca2(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null)
            return false;

        boolean left  =  lca2(node.left, p, q);
        boolean right =  lca2(node.right, p, q);

        if (foundAncestor){
            return true;
        }else if (!foundAncestor && left && right){
            foundAncestor = true;
            ancestor = node;
            System.out.println("Ancestor in different subtrees "+foundAncestor +" Ancestor: "+ancestor.val);
            return true;
        } else if ( (node == p || node == q) && (left || right) ){
            foundAncestor = true;
            ancestor = node;
            System.out.println("Ancestor in the same subtree  " +foundAncestor +" Ancestor: "+ancestor.val);
            return true;
        }else if ( node == p || node == q){
            return true;
        }else if (left || right) {
            return true;
        }else {
            return false;
        }
    }


}
