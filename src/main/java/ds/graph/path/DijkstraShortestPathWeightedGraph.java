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

        //Pair(vertex, weight)
        //List<Pair<Integer,Integer>> list=new ArrayList<>();
        adjList.get(0).add(new Pair(1,4));
        adjList.get(0).add(new Pair(2,1));
        //adjList[0]=list;

        //list=new ArrayList<>();
        adjList.get(1).add(new Pair(4,4));
        //adjList[1]=list;

        //list=new ArrayList<>();
        adjList.get(2).add(new Pair(1,2));
        adjList.get(2).add(new Pair(3,4));
        //adjList[2]=list;

        //list=new ArrayList<>();
        adjList.get(3).add(new Pair(4,4));
        //adjList[3]=list;

        //list=new ArrayList<>();
        //adjList[4]=list;
        //adjList.get(4)
    }

    private void dijkstra(int startVertex, int noOfVertices) {
        int[] path = new int[noOfVertices];
        int[] distance = new int[noOfVertices];
        Arrays.fill(distance,-1); // Initialize distance array

        distance[startVertex]=0;        // Making distance for start vertex 0
        path[startVertex]=startVertex;  // Updating path for start vertex to itself

        PriorityQueue<Pair<Integer,Integer>> priorityQueue = new PriorityQueue<>((a, b) -> a.getValue()-b.getValue());
        priorityQueue.add(new Pair<>(startVertex,0));

        while(!priorityQueue.isEmpty()){
            Pair<Integer,Integer> pair = priorityQueue.remove();
            Integer vertex = pair.getKey();

            List<Pair<Integer,Integer>> neighbors = adjList.get(vertex);
            for(Pair<Integer,Integer> neighbor: neighbors){

                int neighboringVertex = neighbor.getKey();
                int weight            = neighbor.getValue();
                int newDistance       = distance[vertex] + weight;

                if(distance[neighboringVertex] == -1 || distance[neighboringVertex] > newDistance){
                    distance[neighboringVertex] = newDistance;
                    path[neighboringVertex] = vertex;
                    priorityQueue.add(new Pair<>(neighboringVertex,distance[neighboringVertex]));
                }

            }

        }

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
