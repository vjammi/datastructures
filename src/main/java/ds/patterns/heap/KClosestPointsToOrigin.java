package ds.patterns.heap;

import java.util.*;

/**
     973. K Closest Points to Origin
     Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
     The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
     You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
     Input: points = [[1,3],[-2,2]], k = 1
     Output: [[-2,2]]

     Explanation:
     The distance between (1, 3) and the origin is sqrt(10).
     The distance between (-2, 2) and the origin is sqrt(8).
     Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
     We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
     Example 2:

     Input: points = [[3,3],[5,-1],[-2,4]], k = 2
     Output: [[3,3],[-2,4]]
     Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 */

public class KClosestPointsToOrigin {

    class Coordinate{
        int x;
        int y;
        Double dist;

        public Coordinate(int x, int y, Double dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    class MinDistComparator implements Comparator<Coordinate> {
        public int compare(Coordinate coord1, Coordinate coord2){
            return coord1.dist.compareTo(coord2.dist); // or // return (int) (coord1.dist - coord2.dist);
        }
    }

    class MaxDistComparator implements Comparator<Coordinate> {
        public int compare(Coordinate coord1, Coordinate coord2){
            return coord2.dist.compareTo(coord1.dist);
        }
    }

    // Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2)
    // [[1,3],[-2,2]] = Sqrt((1-0)^2 + (3-0)^2)
    public int[][] kClosest(int[][] points, int k) {

        // Create a Map of dist to points[][]   - O(N)
        Map<Coordinate, Double> map = new HashMap<>();
        for(int i=0; i<points.length; i++){
            int[] coord = points[i];
            double dist = Math.sqrt(((coord[0]-0) *(coord[0]-0)) + ((coord[1]-0)*(coord[1]-0)));
            System.out.println(dist);
            map.put(new Coordinate(coord[0], coord[1], dist), dist);
        }

        // Load the elements of the map in a PQ using minOrder Comparator n + nlog(n)???
        Queue<Coordinate> priorityQueue = new PriorityQueue(new MinDistComparator());
        Set<Map.Entry<Coordinate, Double>> entrySet = map.entrySet();
        for (Map.Entry entry: entrySet){
            Coordinate coord = (Coordinate) entry.getKey();
            Double     dist  = (Double) entry.getValue();
            // if (priorityQueue.size() > k) priorityQueue.poll();
            priorityQueue.add(coord);
        }

        // Return the top k min distance - k log K ???
        int[][] kClosestPoints = new int[k][2];
        for(int i=0; i<k; i++){
            Coordinate coordinate = priorityQueue.poll();
            int[] coord = new int[2];
            coord[0] = coordinate.x;
            coord[1] = coordinate.y;
            kClosestPoints[i] =  coord;
        }

        return kClosestPoints;
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin obj = new KClosestPointsToOrigin();
        int k = 2;
        int[][] points = {{3,3},{5,-1},{-2,4}};
        obj.kClosest(points, k);
    }
}
