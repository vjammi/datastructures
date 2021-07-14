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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lca(root, p, q);
        System.out.println(ancestor.val);
        return ancestor;
    }

    TreeNode ancestor = null;
    boolean foundAncestor = false;
    private boolean lca(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null)
            return false;

        boolean left  =  lca(node.left, p, q);
        boolean right =  lca(node.right, p, q);

        if (foundAncestor){
            return true;
        }else if (!foundAncestor && left && right){
            foundAncestor = true;
            ancestor = node;
            System.out.println("Ancestor in different subtrees "
                    +foundAncestor +" Ancestor: "+ancestor.val);
            return true;
        } else if ( (node == p || node == q) && (left || right) ){
            foundAncestor = true;
            ancestor = node;
            System.out.println("Ancestor in the same subtree  "
                    +foundAncestor +" Ancestor: "+ancestor.val);
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
