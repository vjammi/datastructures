package ds.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneGraph {

    class Node {
        public int key;
        public int val;
        public List<Node> neighbors;

        public Node() {
            key = 0;
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int key, int val, ArrayList<Node> neighbors) {
            this.key = key;
            this.val = val;
            this.neighbors = neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        return dfs(node, new HashMap<>());
    }

    public Node dfs(Node node, HashMap<Node, Node> map) {
        if (map.containsKey(node))
            return map.get(node);

        Node clone = new Node(node.val);
        map.put(node, clone); // map OLD node to NEW node!

        for (Node nei: node.neighbors){
            clone.neighbors.add(dfs(nei, map));
        }
        return clone;
    }

    public static void main(String[] args) {
        CloneGraph graph = new CloneGraph();
        // [[2,4],[1,3],[2,4],[1,3]]
        //graph.cloneGraph();
    }

}
