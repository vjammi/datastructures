package dev.vjammi.ds.v1.graph.path;

import java.util.*;

public class DijkstraShortestPathWeightedGraph {
    private Map<Integer, List<Pair<Integer,Integer>> > adjList;

    class DistanceOfVertexFromSourceComparator implements Comparator<Pair<Integer,Integer>>{
        public int compare(Pair<Integer,Integer> a, Pair<Integer,Integer> b){
            return a.getValue().compareTo(b.getValue());
        }
    }

    public DijkstraShortestPathWeightedGraph() {
    }

    private void dijkstra(int noOfVertices) {
        adjList =new HashMap<>();

        for (int i=0; i<noOfVertices; i++){
            adjList.put(i, new ArrayList<Pair<Integer,Integer>>());
        }

        // Pair(vertex, weight) - adjList represents vertex A(0) and vertex B(1) and the weight between A(0) & B(1) = 4
        populateAdjList();

        int[] path = new int[noOfVertices];
        int[] distance = new int[noOfVertices];
        Arrays.fill(distance,Integer.MAX_VALUE); // Initialize distance array ti infinity or -1

        bfsDijkstra(0, noOfVertices - 1, path, distance);
    }

    /**
           Edge A - B distanceToNeighboringVertex: 4 priorDistance: 2147483647
           Edge A - C distanceToNeighboringVertex: 1 priorDistance: 2147483647
           Edge C - B distanceToNeighboringVertex: 3 priorDistance: 4
           Edge C - D distanceToNeighboringVertex: 5 priorDistance: 2147483647
           Edge B - E distanceToNeighboringVertex: 7 priorDistance: 2147483647

           Distance from A to A is 0 via path A
           Distance from A to B is 3 via path C
           Distance from A to C is 1 via path A
           Distance from A to D is 5 via path C
           Distance from A to E is 7 via path B

                        A --4-- B
                        |      /  \  4
                        1    2     E
                        |  /      /  4
                        C --4-- D

    */
    private void bfsDijkstra(int sourceVertex, int noOfVertices, int[] path, int[] distance) {
        // Store the reference of the previous vertex. Since this is the source we store the reference to itself.
        distance[sourceVertex] = 0;
        // Set the distance of the current vertex from the source vertex, in this case to itself, which will be 0
        path[sourceVertex] = sourceVertex;

        //PriorityQueue<Pair<Integer,Integer>> priorityQueue = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        PriorityQueue<Pair<Integer,Integer>> pq = new PriorityQueue<>(new DistanceOfVertexFromSourceComparator());
        pq.add(new Pair<>(sourceVertex,0));

        while(!pq.isEmpty()){

            Pair<Integer,Integer> currentVertexPair = pq.remove();
            Integer currentVertex = currentVertexPair.getKey();

            List<Pair<Integer,Integer>> neighbors = adjList.get(currentVertex);
            for(Pair<Integer,Integer> neighborPair: neighbors){
                int neighbor           = neighborPair.getKey();
                int weightOfEdge       = neighborPair.getValue();
                int newCalculatedDistanceToNeighbor = distance[currentVertex] + weightOfEdge; // Distance of the source vertex plus the weight of the edge to the the visiting neighboring vertex

                // If the newly calculated distance to neighbor [newCalculatedDistanceToNeighbor] < than the stored distance (distance[neighbor]) to neighbor [either infinity or previously calculated (visited) distance]
                if(newCalculatedDistanceToNeighbor < distance[neighbor]){
                    if (newCalculatedDistanceToNeighbor < distance[neighbor])
                        System.out.println("Distance being updated - Edge " +(char)(currentVertex +'A') +" - " +(char)(neighbor +'A') +" distanceToNeighbor: " +newCalculatedDistanceToNeighbor + " priorDistance: " +distance[neighbor]);
                    distance[neighbor] = newCalculatedDistanceToNeighbor; // Distance of the current vertex + the weight of the edge to the the this neighbor that is being visited
                    path[neighbor] = currentVertex;  // We will add the reference of the currentVertex as the vertex from which we have come to this neighbor
                    pq.add(new Pair<>(neighbor, distance[neighbor]));
                }else {
                    // if the newCalculatedDistanceToNeighbor > distance[neighbor], then we do not consider it
                }
            }
        }
        print(sourceVertex, noOfVertices, path, distance);
    }

    private void populateAdjList() {
        adjList.get(0).add(new Pair(1,4));
        adjList.get(0).add(new Pair(2,1));
        adjList.get(1).add(new Pair(4,4));
        adjList.get(2).add(new Pair(1,2));
        adjList.get(2).add(new Pair(3,4));
        adjList.get(3).add(new Pair(4,4));
    }

    public static void main(String[] args) {
        DijkstraShortestPathWeightedGraph obj = new DijkstraShortestPathWeightedGraph();
        obj.dijkstra(6);
    }

    private void print(int startVertex, int noOfVertices, int[] path, int[] distance) {
        System.out.print("");
        for(int i=0;i<noOfVertices; i++){
            System.out.print("Distance from "+(char)(startVertex+'A'));
            System.out.print(" to "+(char)(i+'A')+" is "+distance[i]);
            System.out.println(" via path "+(char)(path[i]+'A'));
        }
        System.out.print("");
    }

    private class Pair<T, T1> {
        int key;
        int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }
    }
}
