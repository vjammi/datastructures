package ds.graph.path;

import java.util.*;

public class ShortestPathUnWeightedGraph {

    private int numberOfVertices;
    public void shortestPathBFS(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int i=0; i<numberOfVertices; i++){         // Create Adj List
            adjList.put(i, new ArrayList<Integer>());
        }
        populateAdjList(adjList);

        int[] path = new int[numberOfVertices]; // Stores a reference of the previous vertex, which lead to the vertex [building path]
        int[] distance = new int[numberOfVertices]; // Stores the distance to the the current vertex from the source
        Arrays.fill(distance,-1); // Initialize distance array elements with -1

        bfs(2, adjList, path, distance);
    }

    /**
                               A/0  - 1 -  B/1
                           1 /   \      /     \ 1
                         C/2       D/3         E/4
                          1 \    /     \      / 1
                              F/5 - 1 -   G/6

                         A  B  C   D   E   F   G
                         0  1  2   3   4   5   6

                 Edge C - A distanceToNeighbor: 1 priorDistance: -1
                 Edge C - F distanceToNeighbor: 1 priorDistance: -1
                 Edge A - B distanceToNeighbor: 2 priorDistance: -1
                 Edge A - D distanceToNeighbor: 2 priorDistance: -1
                 Edge F - G distanceToNeighbor: 2 priorDistance: -1
                 Edge B - E distanceToNeighbor: 3 priorDistance: -1

                 Distance from C to A is 1 via path C
                 Distance from C to B is 2 via path A
                 Distance from C to C is 0 via path C
                 Distance from C to D is 2 via path A
                 Distance from C to E is 3 via path B
                 Distance from C to F is 1 via path C
                 Distance from C to G is 2 via path F
     **/
    public void bfs(int sourceVertex, Map<Integer, List<Integer>> adjList, int[] path, int[] distance) {
        // Store the reference of the previous vertex. Since this is the source we store the reference to itself.
        path[sourceVertex] = sourceVertex;
        // Set the distance of the current vertex from the source vertex, in this case to itself, which will be 0
        distance[sourceVertex] = 0;

        Queue<Integer> queue = new LinkedList() ;
        queue.offer(sourceVertex);

        while(!queue.isEmpty()){

            int size = queue.size(); // We iterate all the neighbors of the current popped vertex, so get the neighboring count [size]
            while(size > 0){
                int currentVertex = queue.poll();

                List<Integer> neighbors = adjList.get(currentVertex);
                for(Integer neighbor: neighbors){ // Iterate over the neighbors

                    int weightOfEdge = 1; // Is a constant [1], in the case of an unweighted graph
                    int distanceToNeighbor = distance[currentVertex] + weightOfEdge;

                    // If not visited [-1] we will visit it and will update the neighbors distance to the distance of the currentVertex + 1
                    if(-1 == distance[neighbor]){
                        System.out.println("Edge " +(char)(currentVertex +'A') +" - " +(char)(neighbor +'A') +" distanceToNeighbor: " +distanceToNeighbor + " priorDistance: " +distance[neighbor]);
                        distance[neighbor] = distanceToNeighbor; // Distance of the current vertex + the weight of the edge to the the this neighbor that is being visited
                        path[neighbor] = currentVertex; // We will add the reference of the currentVertex as the vertex from which we have come to this neighbor
                        queue.offer(neighbor);
                    }
                }
                size--;
            }
        }
        print(sourceVertex, numberOfVertices, path, distance);
    }

    private void print(int startVertex, int numberOfVertices, int[] path, int[] distance) {
        System.out.print("");
        for(int i=0;i<numberOfVertices; i++){
            System.out.print("Distance from "+(char)(startVertex+'A'));
            System.out.print(" to "+(char)(i+'A')+" is "+distance[i]);
            System.out.println(" via path "+(char)(path[i]+'A'));
        }
        System.out.print("");
    }

    /**
                A/0  - 1 -  B/1
            1 /   \     /     \ 1
           C/2      D/3        E/4
           1 \    /      \     / 1
                F/5 - 1 -   G/6

            A  B  C   D   E   F   G
            0  1  2   3   4   5   6

     Edge C - A distanceToNeighbor: 1 priorDistance: -1
     Edge C - F distanceToNeighbor: 1 priorDistance: -1
     Edge A - B distanceToNeighbor: 2 priorDistance: -1
     Edge A - D distanceToNeighbor: 2 priorDistance: -1
     Edge F - G distanceToNeighbor: 2 priorDistance: -1
     Edge B - E distanceToNeighbor: 3 priorDistance: -1

     Distance from C to A is 1 via path C
     Distance from C to B is 2 via path A
     Distance from C to C is 0 via path C
     Distance from C to D is 2 via path A
     Distance from C to E is 3 via path B
     Distance from C to F is 1 via path C
     Distance from C to G is 2 via path F

     **/
    private void populateAdjList(Map<Integer, List<Integer>> adjList) {
        adjList.get(0).add(1);
        adjList.get(0).add(2) ;
        adjList.get(0).add(3) ;

        adjList.get(1).add(3);
        adjList.get(1).add(4) ;

        adjList.get(2).add(0);
        adjList.get(2).add(5) ;

        adjList.get(3).add(0) ;
        adjList.get(3).add(1) ;
        adjList.get(3).add(5);
        adjList.get(3).add(6) ;

        adjList.get(4).add(6);
        adjList.get(4).add(1) ;

        adjList.get(5).add(2);
        adjList.get(5).add(3) ;
        adjList.get(5).add(6) ;

        adjList.get(6).add(3);
        adjList.get(6).add(4);
        adjList.get(6).add(5);
    }

    public static void main(String[] args) {
        ShortestPathUnWeightedGraph obj = new ShortestPathUnWeightedGraph();
        obj.shortestPathBFS(7);
    }

}
