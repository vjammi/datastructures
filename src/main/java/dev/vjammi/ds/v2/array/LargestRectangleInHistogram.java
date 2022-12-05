package dev.vjammi.ds.v2.array;

/**
    84. Largest Rectangle in Histogram
    Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

    Input: heights = [2,1,5,6,2,3]
    Output: 10
    Explanation: The above is a histogram where width of each bar is 1.
    The largest rectangle is shown in the red area, which has an area = 10 units.
*/
public class LargestRectangleInHistogram {

    /**
        #1 BruteForce
          Iterate over the array - At each index
          Scan backwards while
              heightOfTheBar >= heightOfTheBarAtTheCurrentIndex
          Scan forwards while
              heightOfTheBar >= heightOfTheBarAtTheCurrentIndex
          Add up the rectangle that can be formed at the current index
              areaAtCurrentIndex = areaToTheLeft + currentArea + areaToTheRight

         #2 Keep track of the min height

         #3 Using Stack
    */
    // TODO: [Hard] Need to come back and solve it in an optimal way - using stack [https://leetcode.com/problems/largest-rectangle-in-histogram/solution/]
    public int largestRectangleArea(int[] heights) {

        int largestArea = 0;
        for(int i=0; i<heights.length; i++){

            int currentHeight = heights[i];
            int currentArea = 0;

            int areaToLeft = 0;
            for(int j=i-1; j>=0; j--){
                if (heights[j] >= currentHeight)
                    areaToLeft = areaToLeft + (currentHeight*1);
                else
                    break;
            }

            int areaToRight = 0;
            for(int k=i+1; k<heights.length; k++){
                if (heights[k] >= currentHeight)
                    areaToRight = areaToRight + (currentHeight*1);
                else
                    break;
            }

            currentArea  = areaToLeft + (currentHeight*1) + areaToRight;
            largestArea = Math.max(largestArea, currentArea);
        }

        return largestArea;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram obj = new LargestRectangleInHistogram();
        int[] heights = {2,1,5,6,2,3};
        System.out.println(obj.largestRectangleArea(heights));
    }


}
