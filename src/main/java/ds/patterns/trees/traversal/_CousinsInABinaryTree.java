package ds.patterns.trees.traversal;


/**
 * 993. Cousins in Binary Tree
 * Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 * Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.
 * <p>
 * Input: root = [1,2,3,4], x = 4, y = 3
 * Output: false
 * <p>
 * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 * Output: true
 * <p>
 * Input: root = [1,2,3,null,4], x = 2, y = 3
 * Output: false
 */


// Solution
// Traverse the tree via a level order traversal [which gives us the same depth from the parent - a requirements to be a cousin]
// and see if x and y are on the same level
// and checking the two are NOT siblings [children of same parent node]
// but cousins [children of different parent nodes]
// The solution on leetcode says we can put a null marker after each sibling, a way to determine sibling and cousin.

// 95 test cases pass for the current solution but logic needs to be changed
public class _CousinsInABinaryTree {

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

    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, x, y);
        return cousinsFound;
    }

    boolean cousinsFound = false;

    public int dfs(TreeNode node, int x, int y) {
        if (node == null)
            return 0;

        int leftDepth = dfs(node.left, x, y) + 1;
        int rightDepth = dfs(node.right, x, y) + 1;

        // if (cousinsFound)
        //     return true;

        if (node.left != null && node.right != null) {
            TreeNode left = node.left;
            TreeNode right = node.right;

            // left is not null
            if (left != null && right != null &&
                    (left.left != null && right.left != null && ((left.left.val == x && right.left.val == y) || (left.left.val == y && right.left.val == x)))
                    ||
                    (left.left != null && right.right != null && ((left.left.val == x && right.right.val == y) || (left.left.val == y && right.right.val == x)))
            ) {
                cousinsFound = true;
            }

            // right is not null
            if (left != null && right != null &&
                    (left.right != null && right.left != null && ((left.right.val == x && right.left.val == y) || (left.right.val == y && right.left.val == x)))
                    ||
                    (left.right != null && right.right != null && ((left.right.val == x && right.right.val == y) || (left.right.val == y && right.right.val == x)))
            ) {
                cousinsFound = true;
            }

        }

        return Math.max(leftDepth, rightDepth);
    }


}
