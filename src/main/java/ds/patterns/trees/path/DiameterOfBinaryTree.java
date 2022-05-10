package ds.patterns.trees.path;

/**
    543. Diameter of Binary Tree
    Given the root of a binary tree, return the length of the diameter of the tree.
    The diameter of a binary tree is the length of the longest path between any two nodes/leaves in a tree.
    This path may or may not pass through the root.

    Note: A diameter of a binary tree is the length of the longest path between any two leaves of a tree and is represented by the number of edges between them.

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


 **/
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

    // A diameter of a binary tree is the length of the longest path between any two leaves of a tree and is represented by the number of edges between them.
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        diameter =0;
        dfs(root);

        return diameter;
    }


    /**
     *  if node is None, we have reached the end of the tree, hence we should return 0;
     *  we want to recursively explore node's children, so we call longestPath again with node's left and right children.
     *  In return, we get the longest path of its left and right children leftPath and rightPath;
     *  if leftPath plus rightPath is longer than the current longest diameter found, then we need to update diameter;
     *  finally, we return the longer one of leftPath and rightPath. Remember to add 111 as the edge connecting it with its parent.
     **/
    int diameter;
    private int dfs(TreeNode node){
        if (node == null )
            return 0;

        int left  = dfs(node.left);
        int right = dfs(node.right);


        // We get the longest path of the nodes left and right children leftPath and rightPath;
        // if leftPath plus rightPath is longer than the current longest diameter found, then we need to update diameter;
        diameter = Math.max(diameter, left + right);

        // To bubble up the path, we return the longest of the leftPath/rightPath, adding 1 as the edge connecting this node with its parent.
        return Math.max(left, right) + 1;
    }

}
