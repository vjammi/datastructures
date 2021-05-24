package ds.graph.applications.foo;

import ds.graph.UndirectedGraph;

import java.util.Stack;

/******************************************************************************
 *  Compilation:  javac AllPaths.java
 *  Execution:    java AllPaths
 *  Depedencies:  Graph.java
 *
 *  Enumerate all simple paths (of length >= 1) in a graph between s and t.
 *  This implementation uses depth-first search and backtracking.
 *
 *  Warning: there can be exponentially many simple paths in a graph,
 *           so no algorithm is suitable for large graphs.
 *
 *  7 vertices, 9 edges
 *  0: 2 1
 *  1: 5 0
 *  2: 5 3 0
 *  3: 6 4 2
 *  4: 6 5 3
 *  5: 4 1 2
 *  6: 4 3
 *
 *
 *  all simple paths between 0 and 6:
 *  0-2-5-4-6
 *  0-2-5-4-3-6
 *  0-2-3-6
 *  0-2-3-4-6
 *  0-1-5-4-6
 *  0-1-5-4-3-6
 *  0-1-5-2-3-6
 *  0-1-5-2-3-4-6
 *  # paths = 8
 *
 *  all simple paths between 1 and 5:
 *  1-5
 *  1-0-2-5
 *  1-0-2-3-6-4-5
 *  1-0-2-3-4-5
 *  # paths = 4
 *
 ******************************************************************************/

public class AllPaths {

    private boolean[] onPath;        // vertices in current path
    private Stack<Integer> path;     // the current path
    private int numberOfPaths;       // number of simple path

    // show all simple paths from s to t - use DFS
    public AllPaths(UndirectedGraph graph, int s, int t) {
        onPath = new boolean[graph.numberOfVertices()];
        path   = new Stack<>();
        //showAllPaths(graph, s, t);
    }

    public void showAllPaths(UndirectedGraph graph, int s, int t) {
        dfs(graph, s, t);
    }

    // use DFS
    private void dfs(UndirectedGraph G, int v, int t) {

        // add v to current path
        path.push(v);
        onPath[v] = true;

        // found path from s to t
        if (v == t) {
            processCurrentPath();
            numberOfPaths++;
        }else {  // consider all neighbors that would continue path with repeating a node
            for (int w : G.adj(v)) {
                if (!onPath[w])
                    dfs(G, w, t);
            }
        }

        // done exploring from v, so remove from path
        path.pop();
        onPath[v] = false;
    }

    // this implementation just prints the path to standard output
    private void processCurrentPath() {
        Stack<Integer> reverse = new Stack<Integer>();
        for (int v : path)
            System.out.print(v +"-");
            //reverse.push(v);

        //if (reverse.size() >= 1)
        //    System.out.print(reverse.pop());

        while (!reverse.isEmpty())
            System.out.print(reverse.pop() +"-");

        System.out.println();
    }

    // return number of simple paths between s and t
    public int numberOfPaths() {
        return numberOfPaths;
    }

    /**
              1 - 4 - 7
           /  |          \
         0  - 2 - 5 - 8 - 10
           \             /
             3 - 6 - 9
         0 1 2 5 8 10   4 7  3 6 9

              1 - 4 - 7
           /             \
         0  - 2 - 5 - 8 - 10
           \             /
              3 - 6 - 9
         0 1 4 7 10 2 5 8 3 6 9
     */
    public static void main(String[] args) {
        // Build a graph of 11 vertices and add Edges to the Graph
        UndirectedGraph graph = new UndirectedGraph(11);
        graph.addEdge(0, 1);  graph.addEdge(0, 2);  graph.addEdge(0, 3);
        graph.addEdge(1, 4);  graph.addEdge(2, 5);  graph.addEdge(3, 6);
        graph.addEdge(4, 7);  graph.addEdge(5, 8);  graph.addEdge(6, 9);
        graph.addEdge(7, 10); graph.addEdge(8, 10); graph.addEdge(9, 10);
        System.out.println(graph);System.out.println();

        // Show all simple paths between 0 and 10 of a graph
        int s = 0; int t = 10;
        AllPaths paths = new AllPaths(graph, s, t);
        paths.showAllPaths(graph, s, t);
        System.out.println("# paths = " + paths.numberOfPaths()); System.out.println();


        // Show all simple paths between 1 and 5 of a graph
        s = 0; t = 5;
        AllPaths paths2 = new AllPaths(graph, s, t);
        paths.showAllPaths(graph, s, t);
        System.out.println("# paths = " + paths2.numberOfPaths());

    }

}