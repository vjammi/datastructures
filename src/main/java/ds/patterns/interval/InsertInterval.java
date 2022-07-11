package ds.patterns.interval;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InsertInterval {

    /**
     57. Insert Interval
     You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
     You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
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
         [[1,5      ],[6,7],[8,10],[12,16]]

         [[1,3],[6,9]]
         [2,5]

         Solution:
         Case 1: newInterval greater than currentInterval    [[1,2]...]
                                                              [4,8]
         Case 2: newInterval lesser than currentInterval     [6,9]...]
                                                             [4,8]
         Case 3: newInterval overlapping currentInterval     [[1,3],[6,9]...]        [[1,    5]...]
                                                                [2,6]                   [2,4]
     */

    // Implementation 1:
    public int[][] insert1(int[][] intervals, int[] newInterval) {

        List<int[]> resultList = new ArrayList<>();

        if (intervals.length == 0)
            return new int[][] {newInterval};

        for (int i=0; i<intervals.length; i++){
            int[] currentInterval = intervals[i];

            // Case 1: newInterval is greater than the current interval
            if (newInterval[0] > currentInterval[1]) {
                resultList.add(currentInterval);
                if (i == intervals.length-1) resultList.add(newInterval); // If current interval is the last of the intervals
            }

            // Case 2: newInterval is before the current interval
            else if (newInterval[1] < currentInterval[0]) {
                resultList.add(newInterval);                // 2a) Since newInterval is before the current interval. we first insert the newinterval
                for (int j=i; j<intervals.length; j++)      // 2b) We then add the remainder intervals to the result and return
                    resultList.add(intervals[j]);
                break;

            // Case 3: Overlapping intervals. We will take the min of the two start and max of the two end
            // Check to determine if the intervals overlap
            // NewInterval NOT before the current
                //  Is the end value of the newInterval < than the start value of the current interval
            // NewInterval NOT after the current
                // Is the start value of the newInterval > than the end of the current  interval
            // So we take a min of the 2 starts and the max of the 2 ends
            //      We do not add this newer merged interval yet to our list.
            //      We will save it temporarily, look at the next interval.
            //      If it overlaps with the next current interval we will merge this with that and wait again until we have no overlapping intervals
            // Finally when there is no overlapping interval, we first insert this merged interval and then add any intervals after it.

            // The implementation needs to change
            }else{
                int start = Math.min(currentInterval[0], newInterval[0]);
                int end   = Math.max(currentInterval[1], newInterval[1]);
                newInterval = new int[]{start, end};        // rewrite newInterval and not add it yet

                if (i==intervals.length-1)
                    resultList.add(newInterval); // If current interval is the last of the intervals
            }
        }

        int[][] result = new int[resultList.size()][2];
        return resultList.toArray(result);
    }

    /**
     * Insert interval implementation using a stack to hold the overlapping interval
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> resultList = new ArrayList<>();
        Stack<int[]> stack = new Stack<>();

        if (intervals.length == 0)
            return new int[][] {newInterval};

        for (int i=0; i<intervals.length; i++){

            int[] currentInterval = intervals[i];

            // Case 1: newInterval is before the currentInterval
            if (newInterval[1] < currentInterval[0]) {
                resultList.add(newInterval);                        // a) Since newInterval is before the current interval. we first insert the newinterval

                for (int j=i; j<intervals.length; j++)              // b) We then add the remainder intervals to the result and return
                    resultList.add(intervals[j]);
                break;

            }

            // Case 2: newInterval is after than the currentInterval
            else if (newInterval[0] > currentInterval[1]) {
                resultList.add(currentInterval);

                if (i == intervals.length-1)                        // If current interval is the last of the intervals
                    resultList.add(newInterval);
            }

            // Case 3: Overlapping intervals. We will take the min of the two start and max of the two end
            else{
                int start = Math.min(currentInterval[0], newInterval[0]);
                int end   = Math.max(currentInterval[1], newInterval[1]);
                newInterval = new int[]{start, end};                // rewrite new interval

                if (!stack.isEmpty()) stack.pop();
                stack.push(newInterval);

                if (i==intervals.length-1)
                    resultList.add(stack.pop());                    // If current interval is the last of the intervals
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
