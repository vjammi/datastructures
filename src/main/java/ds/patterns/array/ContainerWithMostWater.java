package ds.patterns.array;

/**
    11. Container With Most Water
    You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
    Find two lines that together with the x-axis form a container, such that the container contains the most water.
    Return the maximum amount of water a container can store.
    Notice that you may not slant the container.

    Input: height = [1,8,6,2,5,4,8,3,7]
    Output: 49
    Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

 */
public class ContainerWithMostWater {

    // Runtime: O(n^2)
    public int maxAreaNaive(int[] height) {
        int maxArea = 0;
        for (int i=0; i<height.length; i++){
            for (int j=0; j<height.length; j++){
                int minHeight = Math.min(height[i], height[j]);    // shortest of the two heights
                int width = j-i;                                   // width between the 2 endpoints
                int area = minHeight * width;                      // area within the 2 endpoints
                maxArea = Math.max(maxArea, area);                 // global max area update
            }
        }
        return maxArea;
    }

    //    Approach 2: Two Pointer Approach
    //    The intuition behind this approach is that the area formed between the lines will always be limited by the height of the shorter line. Further, the farther the lines, the more will be the area obtained.
    //    We take two pointers, one at the beginning and one at the end of the array constituting the length of the lines. Futher, we maintain a variable maxarea\text{maxarea}maxarea to store the maximum area obtained till now. At every step, we find out the area formed between them, update maxarea\text{maxarea}maxarea and move the pointer pointing to the shorter line towards the other end by one step.
    //    The algorithm can be better understood by looking at the example below:
    //
    //         height = [1,8,6,2,5,4,8,3,7]
    //                   ^               ^
    //                  left            right
    // Runtime: O(n)
    public int maxArea(int[] height) {
        int left  = 0;
        int right = height.length-1;
        int maxArea = 0;
        while(left < right){
            int minHeight = Math.min(height[left], height[right]);  // shortest of the two heights
            int width     = right-left;                             // width between the 2 endpoints
            int area      = minHeight * width;                      // area within the 2 endpoints
            maxArea       = Math.max(maxArea, area);                // global max area update

            if (height[left] < height[right])                       // for the next iteration, determine either to increment the left index or decrement the right index
                left++;
            else
                right--;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height =  {1,8,6,2,5,4,8,3,7};
        ContainerWithMostWater obj = new ContainerWithMostWater();
        obj.maxArea(height);
    }

}
