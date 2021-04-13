package ds.graph.princeton;

public class AllPaths2 {
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
        Graph graph = new Graph(11);
        graph.addEdge(0, 1);  graph.addEdge(0, 2);  graph.addEdge(0, 3);
        graph.addEdge(1, 4);  graph.addEdge(2, 5);  graph.addEdge(3, 6);
        graph.addEdge(4, 7);  graph.addEdge(5, 8);  graph.addEdge(6, 9);
        graph.addEdge(7, 10); graph.addEdge(8, 10); graph.addEdge(9, 10);
        System.out.println(graph);
        System.out.println();
    }
}