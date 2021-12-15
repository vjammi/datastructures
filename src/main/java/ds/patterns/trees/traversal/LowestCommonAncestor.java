package ds.patterns.trees.traversal;

import java.util.*;

    /**
        236. Lowest Common Ancestor of a Binary Tree
        Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
        According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

        Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        Output: 3
        Explanation: The LCA of nodes 5 and 1 is 3.

        Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
        Output: 5
        Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
    */
public class LowestCommonAncestor {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     LeetCode Solutions Approach 2: Iterative using parent pointers

     Intuition
     If we have parent pointers for each node we can traverse back from p and q to get their ancestors. The first common node we get during this traversal would be the LCA node. We can save the parent pointers in a dictionary as we traverse the tree.

     Algorithm
     Start from the root node and traverse the tree.
     Until we find p and q both, keep storing the parent pointers in a dictionary.
     Once we have found both p and q, we get all the ancestors for p using the parent dictionary and add to a set called ancestors.
     Similarly, we traverse through ancestors for node q. If the ancestor is present in the ancestors set for p, this means this is the first ancestor common between p and q (while traversing upwards) and hence this is the LCA node.
   */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        lca(root, p, q);

        // Storing parent pointers in a map
        lca_borrowed(root, p, q);
        return lca;
    }

    /**
        Scenario 1
        P is a LCA of Q or Q is a LCA of P. e.g.
            p = 2, q = 11, LCA = 2

        //                            1
        //                    2               3
        //                4       5       6       7
        //             8     9 10   11 12   13  14  15

        Scenario 2
        P and Q are on either sides of a node that is an LCA.
            p = 8, q = 15, LCA = 1
         //                            1
         //                    2               3
         //                4       5       6       7
         //             8     9 10   11 12   13  14  15


        Mark pFound and qFound. Once the both nodes are found return the node [p, q or node]
            if one of the them crosses the other on the way back the recursion
            or
            pw and q show ip to the left and right of a node.
    */

    boolean pFound;
    boolean qFound;
    TreeNode lca;
    TreeNode lca(TreeNode node, TreeNode p, TreeNode q){

        if (node == null)
            return null;


        TreeNode left = lca(node.left, p, q);
        TreeNode right = lca(node.right, p, q);

        // LCA found. In either scenarios - bubble it up
        if (lca != null){
            return lca;
        }

        // Scenario 2 - p and q are found on either side of the node - current node is the LCA
        else if ( (left == p && right == q) || (right == p && left == q) )  {
            lca = node;
            return lca;
        }

        // Scenario 1 - One node is an LCA of the other
        else if((left == q || right == q)  && node.val == p.val){
            lca = node;
            return lca;
        }
        // Scenario 1 - One node is an LCA of the other
        else if( (left == p || right == p) && node.val == q.val){
            lca = node;
            return lca;
        }


        // If either one of them is true. then then either p or q is found, so we cannot return from here yet
        else if (node.val == p.val){
            pFound = true;
        }else if (node.val == q.val){
            qFound = true;
        }

        // Once both p and q are found, we can now return
        else if (pFound && node.val == q.val)
            return q;
        else if (qFound && node.val == p.val)
            return p;


        if (left == p || right == p)  // If p is being returned from either of the children bubble it up
            return p;
        else if(left == q || right == q) // If q is being returned from either of the children bubble it up
            return q;
        else
            return node;     //  else return the node itself
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

    public TreeNode lca_borrowed(TreeNode root, TreeNode p, TreeNode q) {

        // Stack for tree traversal
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {

            TreeNode node = stack.pop();

            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        // Ancestors set() for node p.
        Set<TreeNode> ancestors = new HashSet<>();

        // Process all ancestors for node p using parent pointers.
        while (p != null) {  // root's parent is null
            ancestors.add(p);
            p = parent.get(p);
        }

        // The first ancestor of q which appears in p's ancestor set is their lowest common ancestor.
        while (!ancestors.contains(q))
            q = parent.get(q);

        return q;
    }
}
