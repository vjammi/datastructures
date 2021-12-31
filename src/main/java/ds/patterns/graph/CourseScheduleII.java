package ds.patterns.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
     210. Course Schedule II
     There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
     For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
     Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

     Input: numCourses = 2, prerequisites = [[1,0]]
     Output: [0,1]
     Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].

     Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
     Output: [0,2,1,3]
     Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
     So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

     Input: numCourses = 1, prerequisites = []
     Output: [0]
 */
public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        boolean canFinish = true;

        for (int i=0; i<numCourses; i++){
            adjList.put(i, new ArrayList<>());
        }

        for (int i=0; i<prerequisites.length; i++){
            int[] edge = prerequisites[i];
            adjList.get(edge[0]).add(edge[1]); // [2->1] [1->0]
        }

        int[] visited = new int[numCourses];
        List<Integer> path = new ArrayList();

        for (int i=0; i<numCourses; i++){
            if (visited[i] == 0){
                canFinish = dfs(i, adjList, visited, path);
                if (!canFinish) {
                    int[] order = new int[0];
                    return order;
                }
            }
        }

        int[] order = new int[numCourses];
        for (int i=0; i< path.size(); i++){
            order[i] = path.get(i);
            System.out.print(order[i] +" ");
        }

        return order;
    }

    private boolean  dfs(int i, Map<Integer, List<Integer>> adjList, int[] visited, List<Integer> path){
        if (visited[i] == -1)
            return false;

        if (visited[i] == 1)
            return true;

        visited[i] = -1;
        List<Integer> neighbors = adjList.get(i);
        for (Integer neighbor: neighbors){
            boolean canFinish = dfs(neighbor, adjList, visited, path);
            if (canFinish == false)
                return canFinish;
        }
        visited[i] = 1;
        // Gives the order [0 1 2 3]  for numCourses 4 with prerequisites [[1,0],[2,0],[3,1],[3,2]]
        path.add(i);

        return true;
    }

    public static void main(String[] args) {
        int numCourses =4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        // Gives the order [0 1 2 3]
        new CourseScheduleII().findOrder(numCourses, prerequisites);
    }

}
