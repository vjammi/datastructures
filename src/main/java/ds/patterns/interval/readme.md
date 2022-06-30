
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