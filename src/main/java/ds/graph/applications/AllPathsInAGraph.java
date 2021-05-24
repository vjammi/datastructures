package ds.graph.applications;

import ds.graph.UndirectedGraph;

import java.io.IOException;

import java.util.Stack;

public class AllPathsInAGraph {

    /*
    private ArrayList<String>[] adj;

    public Graph2(Scanner in) {
        in.nextLine();
        int size = Integer.parseInt(in.nextLine());
        adj = new ArrayList[size];

        for (int i=0; i<size; i++){
            adj[i] = new ArrayList<>();
        }

        while(in.hasNextLine()){
            String line = in.nextLine();
            System.out.println("" + line);
            int w = Integer.parseInt(line.split(" ")[0]);
            ArrayList<String> list = adj[w];
            String[] edges = line.split(" ");
            for(String edge: edges){
               list.add(edge);
            }
        }

    }

    public void print(){
        for (int i=0; i<adj.length; i++){
            ArrayList<String> list = adj[i];
            System.out.println(list);
        }
    }
*/
    boolean[] marked;
    Stack<Integer> stack = new Stack<>();
    public AllPathsInAGraph(int numberOfVertices) {
        marked = new boolean[numberOfVertices];
    }

    private void findAllPaths(UndirectedGraph graph, int v, int w) {
        marked[v] = true;
        stack.push(v);
        //System.out.print(v +"-"+marked[v] +" ");

        if (v == w){
            System.out.print("Path ");
            printPath(stack);
            System.out.println("");
        }else {
            Iterable<Integer> neighbors = graph.adj(v);
            for (Integer neighbor : neighbors) {
                if (!marked[neighbor]) {
                    findAllPaths(graph, neighbor.intValue(), w);
                }
            }
        }
        // done exploring from v, so un-mark
        marked[v] = false;
    }

    private void printPath(Stack<Integer> stack) {
        while(!stack.isEmpty()){
            System.out.print(stack.pop().intValue() +" - ");
        }
    }

    public static void main(String[] args) throws IOException {
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
        UndirectedGraph graph = new UndirectedGraph(11);
        graph.addEdge(0, 1);  graph.addEdge(0, 2);  graph.addEdge(0, 3);
        graph.addEdge(1, 4);  graph.addEdge(2, 5);  graph.addEdge(3, 6);
        graph.addEdge(4, 7);  graph.addEdge(5, 8);  graph.addEdge(6, 9);
        graph.addEdge(7, 10); graph.addEdge(8, 10); graph.addEdge(9, 10);
        System.out.println(graph);

        AllPathsInAGraph paths = new AllPathsInAGraph(11);
        paths.findAllPaths(graph, 0, 10);

    }

}