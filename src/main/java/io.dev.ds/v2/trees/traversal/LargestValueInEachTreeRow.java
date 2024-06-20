package dev.vjammi.ds.v2.trees.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    515. Find Largest Value in Each Tree Row
    Input: root = [1,3,2,5,3,null,9]
    Output: [1,3,9]

    Input: root = [1,2,3]
    Output: [1,3]

    Input: root = [1]
    Output: [1]

    Input: root = [1,null,2]
    Output: [1,2]

    Input: root = []
    Output: []
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

        //return bfs(root);

        List<Integer> result = new ArrayList<>();
        dfs(root, result, 0);
        return result;
    }

    /*
        level       listSize
        0           1
        1           2
        2           3
    */
    private void dfs(TreeNode node, List<Integer> result, int level){
        if (node == null)
            return;

        if (result.size() <= level){                    // Level not created so far. Add new level to the list.
            result.add(node.val);                       // Add the node val - first val
        }else{                                          // Adding the already created level
            Integer largestVal = Math.max(result.get(level), node.val);

            // Can use either
            // result.remove(level); result.add(level, largestVal);    // remove and add val at index
            // or
            result.set(level, largestVal);                             // Set val at index
        }

        dfs(node.left, result, level+1);
        dfs(node.right, result, level+1);

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
