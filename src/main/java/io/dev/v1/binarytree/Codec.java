package io.dev.v1.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    private TreeNode root;

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
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
        System.out.println();
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

            System.out.println();
            totalNodes = totalNodes +nodesInLevel;
            System.out.println("level: " +level +" nodesInLevel: "+nodesInLevel + " totalNodes: "+totalNodes);
            if (totalNodes >= arraySize){
                break;
            }
            level++;
        }
        return root;
    }

    public static void main(String[] args) {
         //Your Codec object will be instantiated and called as such
         Codec ser = new Codec();
         ser.populate();
         ser.print("", ser.root);
         Codec deser = new Codec();
         TreeNode ans = deser.deserialize(ser.serialize(ser.root));
         ser.print("", ans);
    }

    private void populate() {
        //int[] arr = {16, 10, 22, 8, 12, 20, 24, 6, 9, 11, 13, 19, 21, 23, 25, -1, 7};
        //int[] arr = {2,1,3,0,7,9,1,2,-1,1,0,-1,-1,8,8,-1,-1,-1,-1,7};
        //int[] arr = {1,2,3,-1,-1,4,5};
        //int[] arr = {4,-7,-3,-9999,-9999,-9,-3,9,-7,-4,-9999,6,-9999,-6,-6,-9999,-9999,0,6,5,-9999,9,-9999,-9999,-1,-4,-9999,-9999,-9999,-2};
        String[] arr = {"4","-7","-3",null,null,"-9","-3","9","-7","-4",null,"6",null,"-6","-6",null,null,"0","6","5",null,"9",null,null,"-1","-4",null,null,null,"-2"};
        root = insertLevelOrder(arr, root, 0);
    }
    public TreeNode insertLevelOrder(String[] arr, TreeNode node, int key){
        // Base case for recursion
        if (key < arr.length) {
            TreeNode temp = null;
            if (arr[key] != null) {
                temp = new TreeNode(Integer.parseInt(arr[key]));
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