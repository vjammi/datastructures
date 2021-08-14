package ds.graph.path;

import java.util.*;

public class ShortestPathUnWeightedGraph {

    private int numberOfVertices;
    public void shortestPathBFS(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int i=0; i<numberOfVertices; i++){
            adjList.put(i, new ArrayList<Integer>());
        }
        populateAdjList(adjList);

        int[] path = new int[numberOfVertices];
        int[] distance = new int[numberOfVertices];
        Arrays.fill(distance,-1); // Initialize distance array

        bfs(2, adjList, path, distance);
    }

    public void bfs(int startVertex, Map<Integer, List<Integer>> adjList, int[] path, int[] distance) {

        distance[startVertex] = 0; // Making distance for start vertex 0
        path[startVertex] = startVertex; // Updating path for start vertex to itself

        Queue<Integer> queue = new LinkedList() ;
        queue.offer(startVertex);

        while(!queue.isEmpty()){
            int size = queue.size();

            while(size > 0){
                int vertex = queue.poll();

                List<Integer> neighbors = adjList.get(vertex);
                for(Integer neighbor: neighbors){
                    if(distance[neighbor] == -1){
                        distance[neighbor] = distance[vertex] + 1;
                        path[neighbor] = vertex;

                        queue.offer(neighbor);
                    }
                }
                size--;
            }
            print(startVertex, path, distance);
        }
        System.out.println("Path: " +path);
        System.out.println("Distance: " +distance);
    }

    private void print(int startVertex, int[] path, int[] distance) {
        System.out.println("Distance from "+(char)(startVertex+'A')+" :");
        for(int i=0; i< this.numberOfVertices; i++){
            System.out.print("Distance to "+(char)(i+'A')+" is "+distance[i]);
            System.out.println(" from path "+(char)(path[i]+'A'));
        }
    }

    private void populateAdjList(Map<Integer, List<Integer>> adjList) {
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

    public static void main(String[] args) {
        ShortestPathUnWeightedGraph obj = new ShortestPathUnWeightedGraph();
        obj.shortestPathBFS(7);
    }

}
