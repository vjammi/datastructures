package ds.practice.binarytree;

import ds.binarytree.BinaryTree_ConstructFromPreOrderInOrder;
import sun.awt.windows.WPrinterJob;

import java.util.HashMap;
import java.util.Map;

public class BuildATree {

    private int preorderIndex = 0;

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val){
            this.val = val;
            this.left= null;
            this.right=null;
        }
    }

    public TreeNode build(int[] preorder, int[] inorder){
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i=0; i< inorder.length; i++){
            inorderMap.put(inorder[i], i);
        }
        TreeNode treeNode = buildTree(preorder, inorderMap, 0, inorder.length -1);

        System.out.println(treeNode.val);
        return treeNode;
    }

    private TreeNode buildTree(int[] preorder, Map<Integer, Integer> inorderMap, int leftIndex, int rightIndex) {
        if (rightIndex < leftIndex)
            return null;

        int rootValFromPreorder = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValFromPreorder);
        int rootValInorderIndex = inorderMap.get(rootValFromPreorder);

        TreeNode left  = buildTree(preorder, inorderMap, leftIndex, rootValInorderIndex - 1);
        TreeNode right = buildTree(preorder, inorderMap,rootValInorderIndex + 1, rightIndex);

        root.left = left;
        root.right = right;

        return root;
    }

    public static void main(String[] args) {
        BuildATree tree = new BuildATree();

        int[] preorder = {16,10,8,6,7,9,12,11,13,22,20,19,21,24,23,25};
        //                0  1  2 3 4 5 6  7  8  9  10 11 12 13 14 15
        int[] inorder  = {6,7,8,9,10,11,12,13,16,19,20,21,22,23,24,25};
        //                0 1 2 3 4  5  6  7  8  9  10 11 12 13 14 15
        TreeNode node = tree.build(preorder, inorder);
        tree.print("", node);
    }

    public void print(String prefix, TreeNode node) {
        if (node != null) {
            print(prefix +"\t\t", node.right);
            System.out.println ( prefix +("\t |- ") + node.val);
            print( prefix + "\t\t", node.left);
        }
    }
}
