package io.dev.v2.graph;

import java.util.*;
/**
 * 133. Clone Graph
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 * Test case format:
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
 * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 * Example 1:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * */
public class CloneGraph {

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;

        Map<Node, Node> map = new HashMap();
        dfs(node, map);
        //dfs2(node, map);

        return map.get(node);
    }

    // 1. Create a copy of each node of the Graph
    // 2. Store the mapping of the node and its clone in a map
    // 3. Connect the nodes of the cloned nodes (graph)
    public void dfs(Node node, Map<Node, Node> map) {
        if (map.containsKey(node))
            return; // if we get to a map node, we just return

        // 1. Create a copy of each node of the Graph
        Node clone = new Node(node.val);
        // 2. Store the mapping of the node and its clone in a map
        map.put(node, clone);

        List<Node> neighbors = node.neighbors;
        for (Node neighbor: neighbors){
            dfs(neighbor, map);
            // 3. Connect the nodes of the cloned nodes (graph)
            clone.neighbors.add(map.get(neighbor)); // Add the neighbor's clone to the clone of the current node.
        }
    }

    public static void main(String[] args) {
        // 1:[2,4], 2:[1,3], 3:[2,4], 4:[1,3]
        CloneGraph graph = new CloneGraph();
        Node node1 = graph.populate();
        graph.cloneGraph(node1);
    }

    private Node populate() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        // node1
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        // node2
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        // node3
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        // node4
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        return node1;
    }

}

