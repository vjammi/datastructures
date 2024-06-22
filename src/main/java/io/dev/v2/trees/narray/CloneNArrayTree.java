package io.dev.v2.trees.narray;

import java.util.*;

// 1490. Clone N-ary Tree
// https://leetcode.com/problems/clone-n-ary-tree/
public class CloneNArrayTree {

    class Node {
        public int val;
        public List<Node> children;
        public Node() {
            children = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }
        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // MyNotes.md: Do not pass all the tests on leetcode
    public Node cloneTree(Node root) {
        if (root == null)
            return null;

        //Map<Integer, Node> map = new HashMap<>();
        //cloneDFS(root, map);
        //return map.get(root.val);

        return cloneBFS(root);
    }

    public void cloneDFS(Node node, Map<Integer, Node> map) {
        if (node == null)
            return;

        Node copyOfNode = new Node(node.val);
        if ( !map.containsKey(node.val) )
            map.put(node.val, copyOfNode);

        List<Node> children = node.children;
        for(Node child: children){
            cloneDFS(child, map);
            if (map.containsKey(child.val)){
                copyOfNode.children.add(map.get(child.val));
            }
        }
    }


    public Node cloneBFS(Node root) {
        Map<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        Node copyOfRoot = new Node(root.val);
        map.put(root.val, copyOfRoot);

        while(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.println(" > " +node.val);
            List<Node> children = node.children;
            for(Node child: children){
                System.out.print(" >> " +child.val);
                Node copyOfChild = new Node(child.val);
                if (!map.containsKey(child.val)){
                    map.put(child.val, copyOfChild);
                    queue.offer(child);
                    map.get(node.val).children.add(copyOfChild);
                }else{
                    queue.offer(child);
                    map.get(node.val).children.add(copyOfChild);
                }
            }
        }
        return map.get(root.val);
    }

}
