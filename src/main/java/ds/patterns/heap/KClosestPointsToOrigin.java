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

    Distances - Euclidean vs Manhattan
        The squaring and square rooting in the euclidean distance function is basically to get absolute values of each dimension assessed.
        Manhattan distance just bypasses that and goes right to abs value (this may be a cheaper function call then pow'ing and sqrt'ing)

        Using one way vs the other when it gets to higher level stuff, like comparing least squares or linear algebra (?).

        Manhattan distance is easier to calculate by hand, bc you just subtract the values of a dimension then abs them and add all the results.
        Euclidean distance is harder by hand bc you're squaring anf square rooting. So some of this comes down to what purpose you're using it for.

        Reference: https://math.stackexchange.com/questions/139600/how-do-i-calculate-euclidean-and-manhattan-distance-by-hand
                   *** https://youtu.be/p3HbBlcXDTE
 */

public class KClosestPointsToOrigin {

    class Coordinate{
        int x;
        int y;
        Double distFromOrigin;

        public Coordinate(int x, int y, Double distFromOrigin){
            this.x = x;
            this.y = y;
            this.distFromOrigin = distFromOrigin;
        }
    }

    class MinDistFromOriginComparator implements Comparator<Coordinate> {
        public int compare(Coordinate coord1, Coordinate coord2){
            return coord1.distFromOrigin.compareTo(coord2.distFromOrigin);
        }
    }

    class MaxDistComparator implements Comparator<Coordinate> {
        public int compare(Coordinate coord1, Coordinate coord2){
            return coord2.distFromOrigin.compareTo(coord1.distFromOrigin);
        }
    }

    // Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2)
    // Points=[[1,3],[-2,2]]
    // Origin=[0,0]
    // Distance for point [1,3] from origin [0,0] = Math.sqrt((1-0)^2 + (3-0)^2)
    public int[][] kClosest(int[][] points, int k) {

        // Create a Map of dist to points[][]   - n log(n)
        Map<Coordinate, Double> map = new HashMap<>();
        for(int i=0; i<points.length; i++){
            int[] coord = points[i];
            double dist = Math.sqrt(((coord[0]-0) *(coord[0]-0)) + ((coord[1]-0)*(coord[1]-0)));
            System.out.println(dist);
            map.put(new Coordinate(coord[0], coord[1], dist), dist);
        }

        // Load the elements of the map in a PQ using minOrder Comparator - n log(k)
        Queue<Coordinate> priorityQueue = new PriorityQueue(new MaxDistComparator());
        Set<Map.Entry<Coordinate, Double>> entrySet = map.entrySet();
        for (Map.Entry entry: entrySet){
            Coordinate coord = (Coordinate) entry.getKey();
            Double     distFromOrigin  = (Double) entry.getValue();
            priorityQueue.add(coord);
            if (priorityQueue.size() > k)
                priorityQueue.poll();
        }

        // Return the top k min distance

        // Option 1 [n log(n)] - Storing all elements in the heap
        // While using MinDistance Comparator, reading all elements from the map, and storing all* the elements into the
        // heap based on their min distance from the origin
        int[][] kClosestPoints1 = new int[k][2];
         for(int i=0; i<k; i++){
             Coordinate coordinate = priorityQueue.poll();
             int[] coord = new int[2];
             coord[0] = coordinate.x;
             coord[1] = coordinate.y;
             System.out.println(coordinate.x +" " +coordinate.y +" " +coordinate.distFromOrigin +" ");
             kClosestPoints1[i] =  coord;
         }
        // Option 2 [Preferred - nlog(k)] - Storing only k elements in the heap, using max distance comparator]
        // While using MaxDistance Comparator, reading all elements from the map, but storing only* max of k elements
        // into the heap based on their max distance from the origin. which will leave us with k min distances in reverse order.
        // TODO: To be implemented as an optimization

        // Load the heap into the output array
        int[][] kClosestPoints2 = new int[k][2];
        for (int i=k-1; i>=0; i--){
            Coordinate coordinate = priorityQueue.poll();
            int[] coord = new int[2];
            coord[0] = coordinate.x;
            coord[1] = coordinate.y;
            kClosestPoints2[i] =  coord;
            System.out.println(coordinate.x +" " +coordinate.y +" " +coordinate.distFromOrigin +" ");
        }

        return kClosestPoints2;
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin obj = new KClosestPointsToOrigin();
        int k = 2;
        int[][] points = {{5,-1},{-2,4},{3,3}};
        obj.kClosest(points, k);
    }
}
