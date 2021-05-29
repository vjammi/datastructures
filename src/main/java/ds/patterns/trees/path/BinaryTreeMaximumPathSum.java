package ds.patterns.trees.path;

/**
    Binary Tree Maximum Path Sum
    A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting
    them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
    The path sum of a path is the sum of the node's values in the path.

    Given the root of a binary tree, return the maximum path sum of any path.
    Remember that in your recursive function, you almost always have to do something with the current node's value.
    Combining or modifying it w3ith the solution from its children.
    You need to know how to combine those intermediate solutions into the current solution and you need to pass it back up
    the recursive tree stack, and the cycle repeats itself.

    Input: root = [1,2,3]
            1
        2       3
    Output: 6
    Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

    Input: root = [-10,9,20,null,null,15,7]
    Output: 42
    Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

    Intuition
                -10

        9                   20      //return currentNode + its left segment or it right seg, which ever is positive

                  15(Left)      7(Right)

    First we get the max sum paths from left and right subtrees
    If either of them are negative, then we are better off not using them at all, in other words we either take a 0 or a positive sum.
    Next we find the max path sum that can be formed including our current node.

 **/

public class BinaryTreeMaximumPathSum {

    // Since its possible that all the values of the tree are -ve.
    // The variable need to have the smallest possible number to start.
    private int max = Integer.MIN_VALUE;

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

    public int maxPathSum(TreeNode root) {
        search(root);
        return max;
    }

    private int search(TreeNode node){
        // We need to setup the base case. if the current node is null then the max path sum at this point is 0
        if (node == null)
            return 0;

        int leftOutput = search(node.left);
        int rightOutput = search(node.right);

        // If there is a negative value coming from the subtree, we pick 0 instead, we zero it out.
        // We only want to take positive sums from the subtrees
        int left = Math.max(0, leftOutput);
        int right = Math.max(0, rightOutput);

        // Max path sum that could be formed including the current node adding the left and right to the current node val.
        // If this new value is best Max path sum of the entire tree so far then we take it. If not we use the one we have so far.
        // Note that global max variable is not returned but is used to compute the Max Path Sum at each step
        max = Math.max(max, node.val + left+ right);
        // Because the we need to decide which segment we what to return to the parent. because we cannot send the whole.
        // because it might be using a structure that is forking but the parent needs to us a straight/linear continuous structure.
        // Since we need to maintain a continuous structure, we need to add the current node.
        // We then choose add the left or the right segment of the subtree. we cannot add both segments.
        int returnVal = Math.max(left, right) + node.val;

        return returnVal;
    }

    /**
                    10
                 9      20
                    /        \
                     15    7

                    /   1  \
                    2      5
                 3   \ 4

     If we have negative on the above node, we need to see if bringing the -ve node would outweigh the sum from the other side of the tree.
     To connect with the parent [10] we cannot use the two paths of the subtree [15-20-7].
     We need to choose either 20-15 or 20-7.

     How do we account for these ?
         We need to bring the largest path segment from the left and right subtrees.
         then we connect them by adding the current node.
         The parent should ignore if there is a -ve value coming out of the subtree.

         First we get the max sum paths from left and right subtrees
         If either of them are negative, then we are better off not using them at all, in other words we either take a 0 or a positive sum.
         Next we find the max path sum that can be formed including our current node.
    */
    private void maxPathSum() {
        TreeNode root = new TreeNode();
        root.val = 1;
        TreeNode rootLeft = new TreeNode();
        rootLeft.val  = 2;
        root.left = rootLeft;
        TreeNode rootRight = new TreeNode();
        rootRight.val = 5;
        root.right = rootRight;

        TreeNode rootLeftLeft = new TreeNode();
        rootLeftLeft.val  = 3;
        rootLeft.left = rootLeftLeft;
        TreeNode rootLeftRight = new TreeNode();
        rootLeftRight.val = 4;
        rootLeft.right = rootLeftRight;
        System.out.println(maxPathSum(root));
    }

    public static void main(String[] args) {
        BinaryTreeMaximumPathSum mps = new BinaryTreeMaximumPathSum();
        mps.maxPathSum();
    }

}
