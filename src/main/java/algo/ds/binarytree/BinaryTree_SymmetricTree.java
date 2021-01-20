package algo.ds.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree_SymmetricTree {

    TreeNode root;

    class TreeNode{
        int val;
        TreeNode left, right;

        TreeNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private boolean symmetricInner(TreeNode left, TreeNode right) {
        if (left == null && right == null){
            return true;
        }

        if (left == null && right != null){
            return false;
        }else if (left != null && right == null){
            return false;
        }

        if (left.val == right.val){
            System.out.println(" Left " +left.val +" is symmetric to Right " +right.val);
        }else{
            System.out.println(" Left " +left.val +" is symmetric to Right " +right.val);
            return false;
        }
        return symmetricInner(left.right, right.left);
    }

    private boolean symmetricOutter(TreeNode left, TreeNode right) {
        if (left == null && right == null){
            return true;
        }

        if (left == null && right != null){
            return false;
        }else if (left != null && right == null){
            return false;
        }

        if (left.val == right.val){
            System.out.println(" Left " +left.val +" is symmetric to Right " +right.val);
        }else{
            System.out.println(" Left " +left.val +" is not symmetric to Right " +right.val);
            return false;
        }
        return symmetricOutter(left.left, right.right) && symmetricOutter(left.right, right.left);
    }

    public boolean checkRecurssivelyIfTreeIsSymmetric(TreeNode root) {
        if (root == null)
            return true;

        boolean leftEval = symmetricOutter(root.left, root.right);
        boolean rightEval = symmetricInner(root.left, root.right);
        if (leftEval && rightEval == true) {
            System.out.println(true);
            return true;
        }else{
            System.out.println(false);
            return false;
        }
    }

    /**
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
                1
               / \
              2   2
             / \ / \
            3  4 4  3

       But the following [1,2,2,null,3,null,3] is not:
              1
             / \
             2   2
             \   \
             3    3
     */
    public boolean checkIterativellyIfTreeIsSymmetric(TreeNode root) {
        return isSymmetric(root);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            int[] levelElements = new int[size];
            for (int i = 0; i< size; i++){
                TreeNode node = queue.poll();
                if (node != null) {
                    System.out.print(node.val+" ");
                    levelElements[i] = node.val;     // If node is not null add the val into the array. If it is null do not forget to add a -1 for null, into the array. Add a null node to the queue, lest [1,2,2,null,3,null,3] will be concluded as symmetric.
                    if (node.left != null) {
                        queue.add(node.left);
                    }else{
                        queue.add(null);    // It is actually possible to add a null value into a queue. This makes a lot of difference
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }else{
                        queue.add(null); // Add a -1 into the array, for the null node added above, This makes a lot of difference. Add a null node to the queue, lest [1,2,2,null,3,null,3] will be concluded as symmetric.
                    }
                }else{
                    levelElements[i] = -1; // Add a -1 into the array, for the null node added above, lest this [1,2,2,null,3,null,3] will be concluded as symmetric.
                }

            }

            System.out.println("");
            int i = 0;
            int j = levelElements.length-1;
            while(i <= j){
                if (levelElements[i] != levelElements[j]){
                    System.out.println(levelElements[i] +" not symmetric to " +levelElements[j]);
                    return false;
                }else{
                    System.out.println(levelElements[i] +" is symmetric to " +levelElements[j]);
                }
                i++;
                j--;
            }

        }
        return true;
    }

    private void populate(BinaryTree_SymmetricTree tree) {
        int[] arr = {9,25,25,0,-95,-95,0,-100,0,0,-15};
        //int[] arr = {1,2,2,3,4,4,3};
        //int[] arr = {1,2,2,0,3,0,3};

        //int[] arr = {1,2};
        //int[] arr = {1,2,2,3,4,4,3};
        // [6,82,82,null,53,53,null,-58,null,null,-58,null,-85,-85,null,-9,null,null,-9,null,48,48,null,33,null,null,33,81,null,null,81,5,null,null,5,61,null,null,61,null,9,9,null,91,null,null,91,72,7,7,72,89,null,94,null,null,94,null,89,-27,null,-30,36,36,-30,null,-27,50,36,null,-80,34,null,null,34,-80,null,36,50,18,null,null,91,77,null,null,95,95,null,null,77,91,null,null,18,-19,65,null,94,null,-53,null,-29,-29,null,-53,null,94,null,65,-19,-62,-15,-35,null,null,-19,43,null,-21,null,null,-21,null,43,-19,null,null,-35,-15,-62,86,null,null,-70,null,19,null,55,-79,null,null,-96,-96,null,null,-79,55,null,19,null,-70,null,null,86,49,null,25,null,-19,null,null,8,30,null,82,-47,-47,82,null,30,8,null,null,-19,null,25,null,49]
        root = insertLevelOrder(arr, root, 0);
    }

    // Function to insert nodes in level order
    public static void main(String[] args) {
        BinaryTree_SymmetricTree tree = new BinaryTree_SymmetricTree();
        tree.populate(tree);
        tree.print("", tree.root);
        //System.out.println(tree.checkIterativellyIfTreeIsSymmetric(tree.root));
        tree.checkRecurssivelyIfTreeIsSymmetric(tree.root);
    }

    public TreeNode insertLevelOrder(int[] arr, TreeNode node, int key){
        // Base case for recursion
        if (key < arr.length) {
            TreeNode temp = null;
            if (arr[key] != 0) {
                temp = new TreeNode(arr[key]);
            }
            node = temp;
            if (node != null) {
                // insert left child
                node.left = insertLevelOrder(arr, node.left, 2 * key + 1);
                // insert right child
                node.right = insertLevelOrder(arr, node.right, 2 * key + 2);
            }
        }
        return node;
    }


    public void print(String prefix, TreeNode node) {
        if (node != null) {
            print(prefix +"\t\t", node.right);
            System.out.println ( prefix +("\t |- ") + node.val);
            print( prefix + "\t\t", node.left);
        }
    }

}

