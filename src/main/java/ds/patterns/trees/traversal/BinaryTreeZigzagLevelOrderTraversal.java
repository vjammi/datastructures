package ds.patterns.trees.traversal;

import ds.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
     103. Binary Tree Zigzag Level Order Traversal
     Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
     Example 1:
     Input: root = [3,9,20,null,null,15,7]
     Output: [[3],[20,9],[15,7]]

            3
               \
         9      20
        /
       n   n  15     7
     Output: [[3], [20,9], [15,7]]

               1
                 \
           2       3
        /
       4 null   null 5
     Output: [[1],[3,2],[4,5]]
*/
public class BinaryTreeZigzagLevelOrderTraversal {

        class TreeNode {
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
            public TreeNode() {
            }
        }

        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> lists = new ArrayList();
            zigzagLevelOrderTraversal(root, lists, 0);

            for (int i=0; i< lists.size(); i++){
                if (i%2 != 0){  // reversing the odd indexed list entries
                    List<Integer> list1 = lists.get(i);
                    List<Integer> list2 = new ArrayList();
                    for (int j=list1.size()-1; j>=0 ; j--){
                        list2.add(list1.get(j));
                    }
                    lists.remove(i);
                    lists.add(i, list2);
                }
            }
            return lists;
        }
        List<Integer> tempList = new ArrayList<Integer>();
        public void zigzagLevelOrderTraversal(TreeNode node, List<List<Integer>>lists, int level){
            if (node == null)
                return;

            if (lists.size() <= level){
                List<Integer> list = new ArrayList();
                list.add(node.val);
                lists.add(list);
                System.out.println(node.val +" -- "+ level);
            }else{
                lists.get(level).add(node.val);
                System.out.println(node.val +" - "+ level);
            }
            zigzagLevelOrderTraversal(node.left, lists, level+1);
            zigzagLevelOrderTraversal(node.right, lists, level+1);
        }

        private void zigzagLevelOrder() {
            TreeNode root = new TreeNode();
            root.val = 1;
            TreeNode rootLeft = new TreeNode();
            rootLeft.val  = 2;
            root.left = rootLeft;
            TreeNode rootRight = new TreeNode();
            rootRight.val = 3;
            root.right = rootRight;

            TreeNode rootLeftLeft = new TreeNode();
            rootLeftLeft.val  = 4;
            rootLeft.left = rootLeftLeft;
            TreeNode rootRightRight = new TreeNode();
            rootRightRight.val = 5;
            rootRight.right = rootRightRight;

            System.out.println(zigzagLevelOrder(root).toString());
        }

        public static void main(String[] args) {
            BinaryTreeZigzagLevelOrderTraversal btzzlo = new BinaryTreeZigzagLevelOrderTraversal();
            btzzlo.zigzagLevelOrder();
        }
}



class BinaryTreeZigzagLevelOrderTraversalSolutionBorrowed {

    class TreeNode {
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
        public TreeNode() {
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> sol = new ArrayList<>();
        traversal(root, sol, 0);
        return sol;
    }

    // O(n) solution by using LinkedList along with ArrayList. So insertion in the inner list and outer list are both O(1), using DFS and creating new lists when needed.
    private void traversal(TreeNode curr, List<List<Integer>> lists, int level) {
        if(curr == null) return;

        if(lists.size() <= level){
            List<Integer> newLevel = new LinkedList<>();
            lists.add(newLevel);
        }

        List<Integer> list  = lists.get(level);
        if(level % 2 == 0) {
            list.add(curr.val);
        }else {
            // Inserts the the element at the position 0, in the list. Before that it shifts the element
            // currently at that position if any to the right for the zigZag
            list.add(0, curr.val);
        }

        traversal(curr.left, lists, level + 1);
        traversal(curr.right, lists, level + 1);
    }

    private void zigzagLevelOrder() {
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

        System.out.println(zigzagLevelOrder(root).toString());
    }

    public static void main(String[] args) {
        BinaryTreeZigzagLevelOrderTraversalSolutionBorrowed btzzlo = new BinaryTreeZigzagLevelOrderTraversalSolutionBorrowed();
        btzzlo.zigzagLevelOrder();
    }

}

