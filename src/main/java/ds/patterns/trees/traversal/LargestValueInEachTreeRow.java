package ds.patterns.trees.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Find the largest value in each Tree Row
*/
public class LargestValueInEachTreeRow {

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

    public List<Integer> largestValues(TreeNode root) {
        if (root == null)
            return new ArrayList<Integer>();

        return bfs(root);
    }

    private List<Integer> bfs(TreeNode root){
        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int largestVal = Integer.MIN_VALUE;

            int levelSize = queue.size();
            for (int i=0; i<levelSize; i++){
                TreeNode node = queue.poll();

                largestVal = Math.max(node.val, largestVal);

                if (node.left!=null)
                    queue.offer(node.left);

                if(node.right!=null)
                    queue.offer(node.right);

            }
            result.add(largestVal);
        }
        return result;
    }
}
