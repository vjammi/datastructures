package io.dev.v2.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Given a set of n people (numbered 1, 2, ..., n), we would like to split everyone into two groups of any size.
    Each person may dislike some other people, and they should not go into the same group.
    Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
    Return true if and only if it is possible to split everyone into two groups in this way.

    Example 1:
    Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
    Output: true
    Explanation: group1 [1,4], group2 [2,3]

    Example 2:
    Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
    Output: false

    Example 3:
    Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
    Output: false
*/
public class PossibleBipartition {

    public boolean possibleBipartition(int n, int[][] likes) {

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int i= 1; i< n+1; i++)
            adjList.put(i, new ArrayList<Integer>());

        for(int i=0; i< likes.length; i++){
            int[] edge = likes[i];
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        int[] visited = new int[n+1]; // notice the length of the of the array n+1
        for (int i=1; i<adjList.size()+1; i++){
            // While cycling thru all the unprocessed nodes (1 thru N), return false at the first occurrence of graph that is not bipartite
            if (visited[i] == 0 && !dfs(i, adjList, visited, 1, -1)){
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int node, Map<Integer, List<Integer>> adjList, int[] visited, int visit, int parent) {
        if (visited[node] != 0 && visited[node] != visit)       // Node has been previously visited but the node has the same color as the previous
            return false;

        if (visited[node] != 0 && visited[node] == visit){      // Node has been previously visited and is of the opposite color. We will not re-process the node.
            return true;
        }

        visited[node] = visit;                                  // v[1]=1,  v[2]=-1,  v[4]:1
        List<Integer> children = adjList.get(node);             // 1:[2,3], 2:[1,4],  4:[2]
        for (int child: children){
            // child  - child of index node
            // parent - parent of index node, parent method argument
            if (child != parent) {                              //(2 != 1) T,  (1 != 1) F,    (4 != 1)T,   (2 != 1)T
                boolean canBePartitioned = dfs(child, adjList,
                        visited, -visit,
                        node);                                  //(2,-(1),1), x(1,-(-1),2), (4,-(-1),2)
                if (!canBePartitioned)
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        PossibleBipartition obj = new PossibleBipartition();

        //int n = 5; int[][] edges = {{1,2},{2,3},{3,4},{4,5},{1,5}};   // false
        //int n = 4; int[][] edges = {{1,2},{1,3},{2,4}};               // true
        int n = 5; int[][] edges = {{1,2},{3,4},{4,5},{3,5}};           // false
        System.out.println(obj.possibleBipartition(n, edges));
    }

    List<Integer> [] disList;
    int[] color;

    public boolean possibleBipartition2(int N, int[][] dislikes) {
        // For each person, create a list of the people he/she dislikes
        disList = new List[N+1];
        for (int i = 1; i<=N ; i++)
            disList[i] = new ArrayList<Integer>();

        // iterate over dislikes[][], add one vertex to the other vertex's list
        // We need to add this edge twice due to the possibility of non-forest edges
        for (int[] edge : dislikes){
            disList[edge[0]].add(edge[1]);
            disList[edge[1]].add(edge[0]);
        }

        // create an array that stores the color of each vertex
        // two colors -1 & 1
        color = new int[N+1];

        // paint the vertices of the graph, start by assigning the first person a random color
        // use dfs, when an element isn't colored, we can randomly assign a color
        for (int person = 1; person <= N; person++){
            // color using dfs, return false if color conflicts arise during dfs
            // only color if the current element is not painted yet,otherwise, it has already been dfs checked
            if (color[person] == 0 && !dfs2(person, -1))
                return false;
        }

        // the graph can be colored bipartitely
        return true;
    }

    private boolean dfs2(int person, int color){
        // if person isn't painted yet
        if (this.color[person] == 0){
            // paint person with the specified color
            this.color[person] = color;

            int childColor = color == 1 ? -1 : 1;
            // dfs: paint its children - i.e the list of people it dislikes
            for ( int child : disList[person] ){
                // if color conflict arises during child's dfs
                if ( !dfs2(child, childColor) )
                    return false;
            }
            // person is painted
        }else{
            // current color conflicts with the color passed in
            return this.color[person] == color;
        }

        // no conflict during dfs
        return true;
    }

}
