
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