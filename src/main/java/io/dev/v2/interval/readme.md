
## Interval Problems

#### Overlapping intervals 
We take the min of the two start and max of the two end
- Check to determine if the intervals overlap
  1. newInterval NOT before the currentInterval
     - Is the end value of the newInterval < than the start value of the current interval
     
  2. newInterval NOT after the currentInterval
     - Is the start value of the newInterval > than the end of the current  interval
     
- To determine this we take 
  - The min of the 2 starts and 
  - The max of the 2 ends
   
- We do not add this newer merged interval yet to our list, but we will save it temporarily and wait to look at the next interval.
- If the next interval overlaps with the next current interval we will merge this with that and wait again until we have no overlapping intervals

#### Insert Interval
```
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
```

### Merge Intervals
```
    /**
     * Input list of intervals, Sort the intervals based on start time,
     * Creating a number line and place the intervals on the number line will help with these interval problems
     * We will iterate through the intervals to see
     *    Overlapping: Does the current interval overlap the previous interval
     *          Then we can merge them into a single interval
     *          If the current start is <= previous end
     *          merge them based on min of the 2 start and max of the 2 end vals
     *    Not overlapping
     */

```
```
    class IntervalsComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b){
            return a[0] - b[0];
        }
    }
    
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
```