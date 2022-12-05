package dev.vjammi.ds.v1.binarytree;

public class BinaryTree_PathSum {

    TreeNode root;

    class TreeNode{
        int key;
        String val;
        TreeNode left, right;

        TreeNode(int key, String val){
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private int total;
    private boolean isPathSum = false;

    public boolean hasPathSum(TreeNode root, int sum) {
        pathSum(root, sum, true);
        System.out.println(isPathSum);
        return isPathSum;
    }

    public void pathSum(TreeNode node, int sum, boolean isRootNode) {
        if (node == null){
            return;
        }

        total = total + Integer.valueOf(node.val);
        System.out.println("Total @ node " +node.val +" is " +total);

        if (isRootNode && node.left == null && node.right == null && total == sum) {
            System.out.println(">>> Root Node " + node.val + " Total " + total + " Sum " + sum);
            isPathSum = true;
        }

        if (!isRootNode && node.left == null && node.right == null && total == sum) {
            System.out.println(">>> Leaf Node " + node.val + " Total " + total + " Sum " + sum);
            isPathSum = true;
        }

        isRootNode = false;
        pathSum(node.left, sum, isRootNode);
        pathSum(node.right, sum, isRootNode);

        System.out.println("Total @ node " +node.val +" is " +total);
        if (isPathSum != true) {
            System.out.println("Removing nod @ node " + node.val + " from total " + total +" sum " +sum);
            total = total - Integer.valueOf(node.val);
            System.out.println("Total @ node " +node.val +" after removing node is " +total);
        }

    }

    private void populate(BinaryTree_PathSum tree) {
        //int[] arr = {16, 10, 22, 8, 12, 20, 24, 6, 9, 11, 13, 19, 21, 23, 25, 7};
        //int[] arr = {1,2};
        //int[] arr = {-2, 0, -3};
        //int[] arr = {1};
        //int[] arr =  {2,-1,0,0,-5,7,0,0,-5,0,-1,0,-4,-5};
        int[] arr = {5,4,8,11,0,13,4,7,2,0,0,0,0,0,1};
        root = insertLevelOrder(arr, root, 0);
    }

    // Function to insert nodes in level order
    public static void main(String[] args) {
        BinaryTree_PathSum tree = new BinaryTree_PathSum();
        tree.populate(tree);
        tree.print("", tree.root);
        tree.hasPathSum(tree.root, 22);

    }

    public TreeNode insertLevelOrder(int[] arr, TreeNode node, int key){
        // Base case for recursion
        if (key < arr.length) {
            TreeNode temp = null;
            if (arr[key] != 0) {
                temp = new TreeNode(arr[key], arr[key]+"");
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
            print(prefix +"\t", node.right);
            System.out.println ( prefix +(" \t |-") + node.val);
            print( prefix + "\t", node.left);
        }
    }

}
