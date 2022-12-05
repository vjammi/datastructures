package dev.vjammi.ds.v1.graph.representation;

public class GraphAdjacencyMatrix {

    private final boolean[][] adjMatrix;
    private final int numVertices;

    /**
                 0
             /   |   \
           1  _  2 _  3

             X 0 1 2 3
             0 0 1 1 1
             1 1 0 1 0
             2 1 1 0 1
             3 1 0 1 0
     */

    // Initialize the matrix
    public GraphAdjacencyMatrix(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];
    }

    // Add edges
    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;
    }

    // Remove edges
    public void removeEdge(int i, int j) {
        adjMatrix[i][j] = false;
        adjMatrix[j][i] = false;
    }

    // Print the matrix
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(" ");
        for (int i = 0; i < numVertices; i++) {
            s.append(" " +i);
        }
        s.append("\n");
        for (int i = 0; i < numVertices; i++) {
            s.append(i + " ");
            for (boolean j : adjMatrix[i]) {
                s.append((j ? 1 : 0) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {

        GraphAdjacencyMatrix g = new GraphAdjacencyMatrix(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        System.out.print(g);
    }
}
