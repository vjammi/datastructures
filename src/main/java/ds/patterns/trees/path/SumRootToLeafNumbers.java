package ds.patterns.trees.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 129. Sum Root to Leaf Numbers
 *
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * Each root-to-leaf path in the tree represents a number.
 *     For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
 * A leaf node is a node with no children.
 *
 * Example 1:
 * Input: root = [1,2,3]
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 *
 **/
public class SumRootToLeafNumbers {

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

    private int totalSum;
    private List<Integer> list;

    public int sumNumbers(TreeNode root) {
        this.totalSum = 0;
        this.list = new ArrayList<Integer>();

        // Option 1 Using StringBuilder - Need to remember the sb allowed operations
        dfs_sb2(root, new StringBuilder());

        // Option 2 Using List and StringBuilder - Need to remember the sb allowed operations
        dfs_sb(root, new ArrayList<String>());

        // Option 3: Using List and inserting into the front of the list and removing from the front of the list (0th index)
        dfs_sb(root, new ArrayList<String>());

        // Option 4: Using Stack [FILO inserts create issues]. Required list of numbers to their Decimal Number Conversation [Undesired Complexity]
        dfs_stack(root, new Stack<Integer>());

        System.out.println(list);
        return totalSum;
    }


    public void dfs_sb(TreeNode node, List<String> chosen) {
        if (node == null)
            return;

        chosen.add(String.valueOf(node.val) );

        if (node.left==null && node.right==null){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<chosen.size(); i++)
                sb.append(chosen.get(i));
            totalSum = totalSum + Integer.valueOf(sb.toString());
            list.add(Integer.valueOf(sb.toString()));
        }

        dfs_sb(node.left, chosen);
        dfs_sb(node.right, chosen);

        chosen.remove(chosen.size()-1);
    }


    public void dfs_sb2(TreeNode node, StringBuilder chosen) {
        if (node == null)
            return;

        chosen.append(node.val);

        if (node.left==null && node.right==null){
            totalSum = totalSum + Integer.valueOf(chosen.toString());
            list.add(Integer.valueOf(chosen.toString()));
        }

        dfs_sb2(node.left, chosen);
        dfs_sb2(node.right, chosen);

        chosen.deleteCharAt(chosen.length()-1);
    }

    public void df_list(TreeNode node, List<Integer> chosen) {
        if (node == null)
            return;

        chosen.add(0, node.val);

        if (node.left==null && node.right==null){
            int sum = 0;
            List<Integer> nodes = new ArrayList<>(chosen);
            for(int i=0; i<nodes.size(); i++)
                // sum = sum + (int)(nodes.get(nodes.size()-1 -i) * Math.pow(10, i));
                sum = sum + (int)(nodes.get(i) * Math.pow(10, i));

            totalSum = totalSum + sum;
            list.add(sum);
        }

        df_list(node.left, chosen);
        df_list(node.right, chosen);

        chosen.remove(0); //chosen.size()-1
    }

    public void dfs_stack(TreeNode node, Stack<Integer> chosen) {
        if (node == null)
            return;

        chosen.push(node.val);

        if (node.left==null && node.right==null){
            int sum = 0;
            List<Integer> nodes = new ArrayList<>(chosen);
            for(int i=0; i<nodes.size(); i++)
                sum = sum + (int)(nodes.get((nodes.size()-1)-i) * Math.pow(10, i));

            totalSum = totalSum + sum;
            list.add(sum);
        }

        dfs_stack(node.left, chosen);
        dfs_stack(node.right, chosen);

        chosen.pop();
    }

}
