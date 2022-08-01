package ds.patterns.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    /**
        207. Course Schedule
        There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
        You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

        For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
        Return true if you can finish all courses. Otherwise, return false.

        Example 1:
        Input: numCourses = 2, prerequisites = [[1,0]]
        Output: true
        Explanation: There are a total of 2 courses to take.
        To take course 1 you should have finished course 0. So it is possible.

        Example 2:
        Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
        Output: false
        Explanation: There are a total of 2 courses to take.

        To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

         Graph needs to be directed, connected??? with NO Cycles
         i.e. [2->1] [1->0]
         Here,
         numCourses: Nodes of the graph
         prerequisites: Edges between the nodes of the graph

         numCourses = 9
         prerequisites: [[8,7],[7,6],[6,5],[5,4],[4,3],[3,2],[2,1],[1,0]]
         adjList: {  0:[],   1:[0],  2:[1],  3:[2],  4:[3],  5:[4],  6:[5],  7:[6],  8:[7]   }
         canFinish: true
           7       5       3      1
          /  \   /   \   /   \  /  \
         8     6        4      2     0

         numCourses = 9
         prerequisites: [[8,7], [7,6], [6,5], [5,4], [4,3], [3,2], [2,1], [2,4], [1,0]]
         adjList: {  0:[],   1:[0],  2:[1,4],  3:[2],  4:[3],  5:[4],  6:[5],  7:[6],  8:[7]   }
         canFinish: false

            7       5       3      1
          /  \   /   \   /   \  /  \
         8     6        4   -  2     0

        20
        [[0,10],[3,18],[5,5],[6,11],[11,14],[13,1],[15,1],[17,4]]
        0 > 10
        3 > 18
        5 > 5        // Cycle
        6 > 11 > 14
        13 > 1
        15 > 1
        17 > 4

    */

public class CourseSchedule {
    // int[][] prerequisites = {{0,1},{1,2}, {3,2}, {4,3}, {2,4}};
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        if (numCourses == 0 || prerequisites == null || prerequisites.length ==0)
            return true;

        Map<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        // initialize the adjList
        for (int i=0; i<numCourses; i++){
            adjList.put(i, new ArrayList<>());
        }

        // populate the adjList - directed graph one side only populated
        for (int i=0; i<prerequisites.length; i++){
            int[] prerequisite = prerequisites[i];
            adjList.get(prerequisite[0]).add(prerequisite[1]);    // intuitive
        }

        // visited array where 0=not visited, 1= visited, -1= currently visiting
        int[] visited =  new int[numCourses];
        // *** Iterate through the courses
        for (int i=0;i<numCourses; i++){
            boolean cycle = dfs(adjList, visited, i);
            // if a cycle is found return false else continue;
            if(!cycle)
                return false;
        }

        // No cycle found. all courses can be finished
        return true;
    }

    private boolean dfs(Map<Integer, ArrayList<Integer>> adjacencyList, int[] visited, int node){

        // *** if we run into a node that visited in the current dfs traversal, then there is a cycle
        if (visited[node] == -1 ){
            return false;
        }

        // *** if we run into a node that we have already seen in a previous dfs traversal
        if (visited[node] == 1){
            return true;
        }

        //Current node is not -1 or 1, it is 0. So mark the node as currently visiting before going uphill
        visited[node]  = -1;
        if (adjacencyList.containsKey(node)){
            List<Integer> list = adjacencyList.get(node);
            for (int neighbor: list){
                boolean cycle = dfs(adjacencyList, visited, neighbor);
                // if a cycle is found return false, which should bubble all the way up to return false, else continue
                if(!cycle)
                    return false;
            }
        }
        // Once we have visited all the current nodes neighbors, we mark the node as fully visited during downhill
        visited[node] = 1;
        return true;
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();

        int numCourses1 = 5;
        int[][] prerequisites1 = {{0,1},{1,2}, {3,2}, {4,3}, {2,4}};
        System.out.println(cs.canFinish(numCourses1, prerequisites1));

        int numCourses2 = 3;
        int[][] prerequisites2 = {{1,0},{2,1}};
        System.out.println(cs.canFinish(numCourses2, prerequisites2));

    }
}
