package ds.interval;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InsertInterval {

    /**
     57. Insert Interval
     You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
     Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
     Return intervals after the insertion.

     Inputs/Outputs
         intervals   = [[1,2],[3,5],[6,7],[8,10],[12,16]]
         newInterval =        [3,8] [3,8] [3,10]
         result:     = [[1,2],[3,            10],[12,16]]

         [[1,2],[3,5],[6,7],[8,10],[12,16]]
         [4,8]

         [[1,2],[3,5],[6,7],[8,10],[12,16]]
         [2,5]

         [[1,3],[6,9]]
         [2,5]

         Solution:
         Case 1: newInterval greater than currentInterval
         Case 2: newInterval lesser than currentInterval
         Case 3: newInterval overlapping currentInterval
     */

    // Implementation 1:
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> resultList = new ArrayList<>();

        if (intervals.length == 0)
            return new int[][] {newInterval};

        for (int i=0; i<intervals.length; i++){

            int[] interval = intervals[i];
            if (newInterval[0] > interval[1]) {             // Case 1: newInterval is greater than the current interval

                resultList.add(interval);
                if (i == intervals.length-1) resultList.add(newInterval); // If current interval is the last of the intervals

            }else if (newInterval[1] < interval[0]) {       // Case 2: newInterval is before the current interval
                resultList.add(newInterval);                // 2a) Since newInterval is before the current interval. we first insert the newinterval
                for (int j=i; j<intervals.length; j++)      // 2b) We then add the remainder intervals to the result and return
                    resultList.add(intervals[j]);
                break;
            }else{                                          // Case 3: Overlapping intervals. We will take the min of the two start and max of the two end

                int start = Math.min(interval[0], newInterval[0]);
                int end   = Math.max(interval[1], newInterval[1]);
                newInterval = new int[]{start, end};        // rewrite newInterval

                if (i==intervals.length-1) resultList.add(newInterval); // If current interval is the last of the intervals
            }
        }

        int[][] result = new int[resultList.size()][2];
        return resultList.toArray(result);
    }

    // Implementation 2: Using a stack to hold the overlapping interval
    public int[][] insert2(int[][] intervals, int[] newInterval) {

        List<int[]> resultList = new ArrayList<>();
        Stack<int[]> stack = new Stack<>();

        if (intervals.length == 0)
            return new int[][] {newInterval};

        for (int i=0; i<intervals.length; i++){

            int[] interval = intervals[i];
            if (newInterval[0] > interval[1]) {            // Case 1: newInterval is greater than the current interval
                resultList.add(interval);
                if (i == intervals.length-1) resultList.add(newInterval); // If current interval is the last of the intervals

            }else if (newInterval[1] < interval[0]) {       // Case 2: newInterval is before the current interval

                resultList.add(newInterval);                // 2a) Since newInterval is before the current interval. we first insert the newinterval
                for (int j=i; j<intervals.length; j++)      // 2b) We then add the remainder intervals to the result and return
                    resultList.add(intervals[j]);
                break;
            }else{                                          // Case 3: Overlapping intervals. We will take the min of the two start and max of the two end
                int start = Math.min(interval[0], newInterval[0]);
                int end   = Math.max(interval[1], newInterval[1]);
                newInterval = new int[]{start, end};        // rewrite new interval

                if (!stack.isEmpty()) stack.pop();
                stack.push(newInterval);
                if (i==intervals.length-1) resultList.add(stack.pop()); // If current interval is the last of the intervals
            }
        }

        int[][] result = new int[resultList.size()][2];
        return resultList.toArray(result);
    }


    public static void main(String[] args) {
        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4,8};
        InsertInterval obj = new InsertInterval();
        int[][] insert = obj.insert(intervals, newInterval);
        System.out.println(insert);
    }
}
