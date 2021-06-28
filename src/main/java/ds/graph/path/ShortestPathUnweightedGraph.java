package ds.graph.path;

import javafx.util.Pair;
import java.util.*;

public class ShortestPathUnweightedGraph {

    private List<Integer>[] adj;

    public ShortestPathUnweightedGraph(int noOfVertices) {
        adj = new ArrayList[noOfVertices];

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        adj[0]=list;

        list=new ArrayList<>();
        list.add(3);
        list.add(4);
        adj[1]=list;

        list=new ArrayList<>();
        list.add(0);
        list.add(5);
        adj[2]=list;

        list=new ArrayList<>();
        list.add(5);
        list.add(6);
        adj[3]=list;

        list=new ArrayList<>();
        list.add(6);
        adj[4]=list;

        adj[5]=new ArrayList<>();

        list=new ArrayList<>();
        list.add(5);
        adj[6]=list;
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
                int vertex=queue.remove();

                List<Integer> neighbors = adj[vertex];

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
    }

    public static void main(String[] args) {
        ShortestPathUnweightedGraph obj = new ShortestPathUnweightedGraph(7);
        obj.bfs(2, 7);
    }

}
