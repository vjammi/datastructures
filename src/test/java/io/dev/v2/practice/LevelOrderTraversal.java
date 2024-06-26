package io.dev.v2.practice;

import io.dev.v2.util.BinaryTreeUtil;
import java.util.ArrayList;
import java.util.List;
public class LevelOrderTraversal  {
    public List<List<Integer>> levelOrder(BinaryTreeUtil.TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        levelOrder(root, levels, 0);
        return levels;
    }

    private void levelOrder(BinaryTreeUtil.TreeNode node, List<List<Integer>> levels, int level) {
        if (node == null)
            return;

        if (level < levels.size()){
            List<Integer> treeNodes = levels.get(level);
            treeNodes.add(node.val);
        }else{
            List<Integer> newNodes = new ArrayList<>();
            newNodes.add(node.val);
            levels.add(newNodes);
        }

        levelOrder(node.left, levels, level+1);
        levelOrder(node.right, levels, level+1);
    }
}
    /*
    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        //           0  1  2  3  4  5   6   7   8   9   10  11  12  13  14
        LevelOrderTraversal subject = new LevelOrderTraversal();
        LevelOrderTraversal.TreeNode root = subject.buildTree(arr);
        LevelOrderTraversal.TreeNode rootNode = subject.getRoot();
        assert rootNode == root;
        subject.print(" ", root);
    }
    **/
