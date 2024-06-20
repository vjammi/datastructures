package dev.vjammi.ds.v1.graph.applications.ctci;

class Node {
    private final Node[] adjacent;
    public int adjacentCount;
    private final String vertex;
    public Question.State state;
    public Node(String vertex, int adjacentLength) {
        this.vertex = vertex;                
        adjacentCount = 0;        
        adjacent = new Node[adjacentLength];
    }
    
    public void addAdjacent(Node x) {
        if (adjacentCount < adjacent.length) {
            this.adjacent[adjacentCount] = x;
            adjacentCount++;
        } else {
            System.out.print("No more adjacent can be added");
        }
    }
    public Node[] getAdjacent() {
        return adjacent;
    }
    public String getVertex() {
        return vertex;
    }
}
