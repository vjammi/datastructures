package algo.ds.binarytree;

public class BinaryTree_RecurssiveApproaches {

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

    public boolean hasPathSum() {
        pathSum(root, -10, true);
        System.out.println(isPathSum);
        return isPathSum;
    }

    /**
        Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along
        the path equals the given sum.
        Note: A leaf is a node with no children.
        Example: Given the below binary tree and sum = 22,
                     >5
                     / \
                   >4   8
                   /   / \
                 >11  13  4
                 /  \      \
                7   >2      1
       return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
     */
    private int total;
    private boolean isPathSum = false;
    public void pathSum(TreeNode node, int sum, boolean isRootNode) {
        if (node == null){
            return;
        }

        total = total + Integer.valueOf(node.val);
        System.out.println("Total @ node " +node.val +" is " +total);

        if (isRootNode && node.left == null && node.right == null && total == sum) {
            System.out.println(">>> root Node encountered @ node " + node.val + " Total " + total + " Sum " + sum);
            isPathSum = true;
        }

        if (node.left == null && node.right == null && !isRootNode) {
            if (total == sum) {
                System.out.println(">>> Leaf Node encountered @ node " + node.val + " Total " + total + " Sum " + sum);
                isPathSum = true;
            } else {
                total = total - Integer.valueOf(node.val);
                System.out.println("Leaf Node encountered @ node " + node.val + " sum!=total, hence removing nod.val from total. Total " + total +" Sum " +sum);
            }
        }

        isRootNode = false;
        pathSum(node.left, sum, isRootNode);
        pathSum(node.right, sum, isRootNode);

        if (node.left != null && isPathSum != true) {
            total = total - Integer.valueOf(node.val);
            System.out.println("Removing nod.val from total @ node " + node.val + " total " + total +" sum " +sum);
        }
    }


    public static void main(String[] args) {
        BinaryTree_RecurssiveApproaches tree = new BinaryTree_RecurssiveApproaches();
        tree.populate(tree);
        tree.print("", tree.root);

        tree.hasPathSum();
    }

    private void populate(BinaryTree_RecurssiveApproaches tree) {
        //int[] arr = {16, 10, 22, 8, 12, 20, 24, 6, 9, 11, 13, 19, 21, 23, 25, 7};
        //int[] arr = {1,2};
        //int[] arr = {5,4,8,11,0,13,4,7,2,0,0,0,0,0,1};
        //int[] arr = {-2, 0, -3};
        //int[] arr = {1};
        int[] arr =  {2,-1,0,0,-5,7,0,0,-5,0,-1,0,-4,-5};
        root = insertLevelOrder(arr, root, 0);
    }

    // Function to insert nodes in level order
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
