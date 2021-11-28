package ds.binarytree;

/**
   199. Binary Tree Right Side View

    Right View
    Print the last element (Stack???) for each level, while traversing the tree in level order traversal

    Left View
    Print the left most element (List) for each level, while traversing the tree in level order traversal
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree_RightSideView {

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

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        return bfs(root);
    }

    private List<Integer> bfs(TreeNode root){

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){

            int size = queue.size();
            for (int i=0 ; i<size; i++){
                TreeNode node = queue.poll();

                if (node.left != null)
                    queue.offer(node.left);

                if (node.right != null)
                    queue.offer(node.right);

                if (i == size-1)
                    result.add(Integer.valueOf(node.val));

            }

        }
        return result;
    }

}
