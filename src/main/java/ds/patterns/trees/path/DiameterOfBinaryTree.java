package ds.patterns.trees.path;

/**
    543. Diameter of Binary Tree
    Given the root of a binary tree, return the length of the diameter of the tree.
    The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
    This path may or may not pass through the root.
    The length of a path between two nodes is represented by the number of edges between them.
    Input: root = [1,2,3,4,5]
    Output: 3
    Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

                1
              /  \
            2     3
          /  \
        4     5

    Input: root = [1,2]
    Output: 1

    Complexity Analysis
    If N be the number of nodes in the tree.
    Time complexity: O(N).  We traverse all nodes
    Space complexity: O(N). The space complexity depends on the size of our implicit call stack during our DFS, which relates to the height of the tree.
                            In the worst case, if the tree is skewed, the height of the tree would be O(N).
                            If the tree is balanced, it would be O(log N).


 * */
public class DiameterOfBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        diameter =0;
        dfs(root);

        return diameter;
    }

    int diameter;
    private int dfs(TreeNode node){
        if (node == null )
            return 0;

        int left  = dfs(node.left);
        int right = dfs(node.right);

        // Largest diameter of the left or right child
        diameter = Math.max(diameter, left + right);

        // longest of the left or right child, plus 1 for the path connecting the parent
        int maxOfLeftOrRightPlusCurrent = Math.max(left, right) + 1;
        return maxOfLeftOrRightPlusCurrent;
    }

}