package ds.patterns.trees.traversal;

    /**
        https://leetcode.com/problems/same-tree/
        100. Same Tree
        Given the roots of two binary trees p and q, write a function to check if they are the same or not.
        Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

        Input: p = [1,2,3], q = [1,2,3]
        Output: true
        //      1               1
        //    2   3           2   3

        Input: p = [1,2], q = [1,null,2]
        Output: false
        Input: p = [1,2,1], q = [1,1,2]
        Output: false
     */
public class SameTree {

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

    //      1               1
    //    2   3           2   3
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return dfs(p,q);
    }

    public boolean dfs(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        if ( (p != null && q == null) ||  (p == null && q != null) )
            return false;

        if ( (p != null && q != null) && (p.val != q.val) )
            return false;

        boolean leftSame = dfs(p.left, q.left);
        boolean rightSame = dfs(p.right, q.right);

        if (leftSame && rightSame)
            return true;

        return false;
    }

}
