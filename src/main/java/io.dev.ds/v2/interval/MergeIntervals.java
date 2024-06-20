package dev.vjammi.ds.v2.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

    /**
        56. Merge Intervals
        Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of
        the non-overlapping intervals that cover all the intervals in the input.

        Example 1:
        Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
        Output: [[1,6],[8,10],[15,18]]
        Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

        Example 2:
        Input: intervals = [[1,4],[4,5]]
        Output: [[1,5]]
        Explanation: Intervals [1,4] and [4,5] are considered overlapping.

        Constraints:
            1 <= intervals.length <= 104
            intervals[i].length == 2
            0 <= starti <= endi <= 104

        ----------------------------------------------------------------------------------------------------------------

        Scenario 1: [[1,3],[2,6],[8,10],[15,18]]
        Scenario 2: [[1,4],[2,3]]

        Complexity Analysis
            Time complexity : O(nlog n) because we are sorting
            Other than the sort invocation, we do a simple linear scan of the list, so the runtime is dominated by the O(n log n) ) complexity of sorting.

            Space complexity : O(log N) (or O(n))
            If we can sort intervals in place, we do not need more than constant additional space, the sorting itself takes O(log n) space.
            Otherwise, we must allocate linear space to store a copy of intervals and sort that.
    */

public class MergeIntervals {

    class IntervalsComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b){
            return a[0] - b[0];
        }
    }

    /**
     * Input list of intervals, Sort the intervals based on start time,
     * Creating a number line and place the intervals on the number line will help with these interval problems
     * We will iterate through the intervals to see
     *    Overlapping: Does the current interval overlap the previous interval
     *          Then we can merge them into a single interval
     *          If the current start is <= previous end
     *          merge them based on min of the 2 start and max of the 2 end vals
     *    Not overlapping
     *
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new IntervalsComparator());

        List<int[]> mergedIntervals = new ArrayList();

        for(int i=0; i<intervals.length; i++){

            int[] interval = intervals[i];
            if(mergedIntervals.size() == 0){
                mergedIntervals.add(interval);

            }else{
                boolean intervalToMergeFound = false;
                for(int j=0; j<mergedIntervals.size(); j++){
                    int[] mergedInterval = mergedIntervals.get(j);

                    if ( (mergedInterval[1] >= interval[0]) && (mergedInterval[1] <= interval[1]) ){  // scenario - [[1,3],[2,6], [8,10],[15,18]]
                        int[] newMergedInterval = {mergedInterval[0], interval[1]};
                        mergedIntervals.remove(mergedInterval);
                        mergedIntervals.add(newMergedInterval);
                        intervalToMergeFound = true;

                    }else if ( (mergedInterval[1] >= interval[0]) && (mergedInterval[1] >= interval[1]) ){ // scenario - [[1,4],[2,3]]
                        int[] newMergedInterval = {mergedInterval[0], mergedInterval[1]};
                        mergedIntervals.remove(mergedInterval);
                        mergedIntervals.add(newMergedInterval);
                        intervalToMergeFound = true;
                    }
                }

                if (intervalToMergeFound == false)
                    mergedIntervals.add(interval);
            }
        }

        int[][] finalMergedIntervals = new int[mergedIntervals.size()][2];
        for(int j=0; j<mergedIntervals.size(); j++){
            finalMergedIntervals[j] = mergedIntervals.get(j);
        }

        return finalMergedIntervals;
    }

}
