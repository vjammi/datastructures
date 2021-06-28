package ds.graph.path;

import java.util.*;

public class ShortestPathUnWeightedGraph {

    private Map<Integer, List<Integer>> adjList;

    public ShortestPathUnWeightedGraph(int noOfVertices) {
        adjList = new HashMap<>();
        for (int i=0; i<noOfVertices; i++){
            adjList.put(i, new ArrayList<Integer>());
        }

        adjList.get(0).add(1);
        adjList.get(0).add(3) ;

        adjList.get(1).add(3);
        adjList.get(1).add(4) ;

        adjList.get(2).add(0);
        adjList.get(2).add(5) ;

        adjList.get(3).add(5);
        adjList.get(3).add(6) ;

        adjList.get(4).add(6);
        adjList.get(4).add(4) ;

        //adjList.get(5);

        adjList.get(6).add(5);

   }

    public void bfs(int startVertex, int noOfVertices) {
        int[] path = new int[noOfVertices];
        int[] distance = new int[noOfVertices];
        Arrays.fill(distance,-1); // Initialize distance array

        distance[startVertex]=0; // Making distance for start vertex 0
        path[startVertex]=startVertex; // Updating path for start vertex to itself

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);

        while(!queue.isEmpty()){
            int size = queue.size();

            while(size > 0){
                int vertex = queue.remove();

                List<Integer> neighbors = adjList.get(vertex);
                for(Integer neighbor: neighbors){
                    if(distance[neighbor] == -1){
                        distance[neighbor] = distance[vertex] + 1;
                        path[neighbor] = vertex;
                        queue.add(neighbor);
                    }
                }
                size--;
            }

            System.out.println("Distance from "+(char)(startVertex+'A')+" :");
            for(int i=0;i<noOfVertices; i++){
                System.out.print("Distance to "+(char)(i+'A')+" is "+distance[i]);
                System.out.println(" from path "+(char)(path[i]+'A'));
            }
        }

        System.out.println("Path: " +path);
        System.out.println("Distance: " +distance);
    }

    public static void main(String[] args) {
        ShortestPathUnWeightedGraph obj = new ShortestPathUnWeightedGraph(7);
        obj.bfs(2, 7);
    }

}
