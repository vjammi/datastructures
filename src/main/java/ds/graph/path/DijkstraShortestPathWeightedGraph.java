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

        // Pair(vertex, weight)
        // adjList represents vertex A(0) and vertex B(1) and the weight between A(0) & B(1) = 4
        adjList.get(0).add(new Pair(1,4));
        adjList.get(0).add(new Pair(2,1));

        adjList.get(1).add(new Pair(4,4));

        adjList.get(2).add(new Pair(1,2));
        adjList.get(2).add(new Pair(3,4));

        adjList.get(3).add(new Pair(4,4));

        //adjList.get(4)
    }

    class DistanceOfVertexFromSourceComparator implements Comparator<Pair<Integer,Integer>>{
        public int compare(Pair<Integer,Integer> a, Pair<Integer,Integer> b){
            return a.getValue().compareTo(b.getValue());
        }
    }

      /**
        Distance from A
            Distance to A is 0 from path A
            Distance to B is 3 from path C
            Distance to C is 1 from path A
            Distance to D is 5 from path C
            Distance to E is 7 from path B

            A --4-- B
            |      /  \  4
            1    2     E
            |  /      /  4
            C --4-- D

    */
    private void dijkstra(int startVertex, int noOfVertices) {
        int[] path = new int[noOfVertices];
        int[] distance = new int[noOfVertices];
        Arrays.fill(distance,-1); // Initialize distance array

        distance[startVertex] = 0;        // Making distance for start vertex 0
        path[startVertex] = startVertex;  // Updating path for start vertex to itself

        //PriorityQueue<Pair<Integer,Integer>> priorityQueue = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        PriorityQueue<Pair<Integer,Integer>> priorityQueue = new PriorityQueue<>(new DistanceOfVertexFromSourceComparator());
        priorityQueue.add(new Pair<>(startVertex,0));

        while(!priorityQueue.isEmpty()){
            Pair<Integer,Integer> pair = priorityQueue.remove();
            Integer vertex = pair.getKey();

            List<Pair<Integer,Integer>> neighbors = adjList.get(vertex);
            for(Pair<Integer,Integer> neighboringVertex: neighbors){
                int neighbor = neighboringVertex.getKey();
                int weighOfCurrentPath  = neighboringVertex.getValue();
                // Check if the newer distance lesser than the old distance.
                // Distance gets updated if the newer distance is lesser than the old
                int newerDistance = distance[vertex] + weighOfCurrentPath;
                if(distance[neighbor] == -1 || newerDistance < distance[neighbor]){ // distance[neighboringVertex] > newDistance
                    if (newerDistance < distance[neighbor])  System.out.println("*** Newer Distance: " +newerDistance + " Previous Distance: " +distance[neighbor]);
                    distance[neighbor] = newerDistance; // distance[vertex] + weightOfNeighboringVertex;
                    path[neighbor] = vertex;
                    priorityQueue.add(new Pair<>(neighbor,distance[neighbor]));
                }
            }
        }
        print(startVertex, noOfVertices, path, distance);
    }

    private void print(int startVertex, int noOfVertices, int[] path, int[] distance) {
        System.out.println("Distance from "+(char)(startVertex+'A')+" :");
        for(int i=0;i<noOfVertices; i++){
            System.out.print("Distance to "+(char)(i+'A')+" is "+distance[i]);
            System.out.println(" from path "+(char)(path[i]+'A'));
        }
    }


    public static void main(String[] args) {
        DijkstraShortestPathWeightedGraph obj = new DijkstraShortestPathWeightedGraph(5);
        obj.dijkstra(0,5);
    }

}
