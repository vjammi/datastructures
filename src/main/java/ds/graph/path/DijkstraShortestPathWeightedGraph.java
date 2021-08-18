package ds.graph.path;

import javafx.util.Pair;
import java.util.*;

public class DijkstraShortestPathWeightedGraph {
    private Map<Integer, List<Pair<Integer,Integer>> > adjList;

    public DijkstraShortestPathWeightedGraph(int noOfVertices) {
        adjList =new HashMap<>();

        for (int i=0; i<noOfVertices; i++){
            adjList.put(i, new ArrayList<Pair<Integer,Integer>>());
        }
        // Pair(vertex, weight) - adjList represents vertex A(0) and vertex B(1) and the weight between A(0) & B(1) = 4
        adjList.get(0).add(new Pair(1,4));
        adjList.get(0).add(new Pair(2,1));
        adjList.get(1).add(new Pair(4,4));
        adjList.get(2).add(new Pair(1,2));
        adjList.get(2).add(new Pair(3,4));
        adjList.get(3).add(new Pair(4,4));
    }

    class DistanceOfVertexFromSourceComparator implements Comparator<Pair<Integer,Integer>>{
        public int compare(Pair<Integer,Integer> a, Pair<Integer,Integer> b){
            return a.getValue().compareTo(b.getValue());
        }
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
    private void dijkstra(int startVertex, int noOfVertices) {
        int[] path = new int[noOfVertices];
        int[] distance = new int[noOfVertices];
        Arrays.fill(distance,Integer.MAX_VALUE);        // Initialize distance array ti infinity or -1

        distance[startVertex] = 0;                      // Making distance for start vertex 0
        path[startVertex] = startVertex;                // Updating path for start vertex to itself

        //PriorityQueue<Pair<Integer,Integer>> priorityQueue = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        PriorityQueue<Pair<Integer,Integer>> priorityQueue = new PriorityQueue<>(new DistanceOfVertexFromSourceComparator());
        priorityQueue.add(new Pair<>(startVertex,0));

        while(!priorityQueue.isEmpty()){

            Pair<Integer,Integer> vertexObj = priorityQueue.remove();
            Integer vertex = vertexObj.getKey();

            List<Pair<Integer,Integer>> neighbors = adjList.get(vertex);
            for(Pair<Integer,Integer> neighborPair: neighbors){
                int neighbor          = neighborPair.getKey();
                int weightOfEdge      = neighborPair.getValue();
                int distanceToNeighbor = distance[vertex] + weightOfEdge; // Distance of the source vertex plus the weight of the edge to the the visiting neighboring vertex

                if(distanceToNeighbor < distance[neighbor]){
                    if (distanceToNeighbor < distance[neighbor]) System.out.println("Edge " +(char)(vertex +'A') +" - " +(char)(neighbor +'A') +" distanceToNeighbor: " +distanceToNeighbor + " priorDistance: " +distance[neighbor]);
                    distance[neighbor] = distanceToNeighbor;     // Distance of the source vertex plus the weight of the edge to the the visiting neighboring vertex
                    path[neighbor] = vertex;                              // Will add the vertex from where we have come to this neighboring vertex
                    priorityQueue.add(new Pair<>(neighbor, distance[neighbor]));
                }

            }
        }
        print(startVertex, noOfVertices, path, distance);
    }

    public static void main(String[] args) {
        DijkstraShortestPathWeightedGraph obj = new DijkstraShortestPathWeightedGraph(5);
        obj.dijkstra(0,5);
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

}
