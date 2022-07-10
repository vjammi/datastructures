package ds.patterns.trees.traversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. Serialize and Deserialize Binary Tree
 * */
public class SerializeDeserializeBinaryTree {


      // Definition for a binary tree node.
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String serializedTree =  "";
        Queue<TreeNode> queue =  new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node!= null) {
                serializedTree = serializedTree + node.val +";" ;
                queue.add(node.left);
                queue.add(node.right);
            }else{
                serializedTree = serializedTree + "-9999" + ";" ;
            }
        }
        System.out.println(serializedTree.substring(0, serializedTree.length()-1));
        return serializedTree;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String serializedData) {
        String[] data = serializedData.split(";");
        for (String element: data) {
            System.out.print(element +" ");
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = null;
        System.out.println("");
        int arraySize = data.length;
        int level = 0;
        int totalNodes = 0;
        while(true){
            int nodesInLevel = (int) Math.pow(2, level);
            int end = Math.min(totalNodes + nodesInLevel, data.length);
            for(int i = totalNodes; i< end; i++){
                if (level == 0){
                    if (!data[i].equals("-9999")) {
                        root = new TreeNode(Integer.parseInt(data[i]));
                        queue.add(root);
                        System.out.print("Root-" + data[i] + ", ");
                    }
                }else {
                    TreeNode levelNode = queue.poll();
                    if (!data[i].equals("-9999")) {
                        TreeNode leftNode = new TreeNode(Integer.parseInt(data[i]));
                        queue.add(leftNode);
                        levelNode.left = leftNode;
                        System.out.print("Left-" + data[i] + ", ");
                    }
                    i++;
                    if (!data[i].equals("-9999")) {
                        TreeNode rightNode = new TreeNode(Integer.parseInt(data[i]));
                        queue.add(rightNode);
                        levelNode.right = rightNode;
                        System.out.print("Right-" + data[i] + ", ");
                    }
                }
            }

            System.out.println("");
            totalNodes = totalNodes +nodesInLevel;
            System.out.println("level: " +level +" nodesInLevel: "+nodesInLevel + " totalNodes: "+totalNodes);
            if (totalNodes >= arraySize){
                break;
            }
            level++;
        }
        return root;
    }
}
