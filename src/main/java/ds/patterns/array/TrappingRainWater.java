package ds.patterns.array;

/**
    42. Trapping Rain Water
    Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6
    Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
                 In this case, 6 units of rain water (blue section) are being trapped.

   TODO: Approach
        1. Brute Force:
           At each index, find the max(right) and max(left) by iterating left and right
           Once found the water that can trapped at that index will be min(max(right), max(left)) - height[i]
        2.
*/
public class TrappingRainWater {

    // Brute Force
    // At each index, find the max(right) and max(left) by iterating left and right side of the index
    // Once the max values are determined, the water trapped at that index will be
    //      min(max(right), max(left)) - height[i]

    //               input                  = [0,1,0,2,1,0,1,3,2,1,2,1]
    //              maxLeft                 =
    //             maxRight                 =
    //min(maxLeft, maxRight) - height[i]    =
    //               output                 = [0 0 1 0 1 2 1 0 0 1 0 0]
    public int trap(int[] height) {

        int totalWaterTrapped = 0;
        for (int i=0; i<height.length; i++){

            int maxLeft  = 0;
            int maxRight = 0;

            if (i==0){
                maxLeft = 0;
            }else{
                for (int j=i-1; j>=0; j--){
                    maxLeft = Math.max(maxLeft, height[j]);
                }
            }

            if (i == height.length-1){
                maxRight = 0;
            }else{
                for (int k=i+1; k<height.length; k++){
                    maxRight = Math.max(maxRight, height[k]);
                }
            }

            int waterTrappedAtCurrentIndex = Math.min(maxLeft, maxRight) - height[i];
            if (waterTrappedAtCurrentIndex < 0)
                waterTrappedAtCurrentIndex = 0;
            System.out.print(waterTrappedAtCurrentIndex +" ");

            totalWaterTrapped = totalWaterTrapped + waterTrappedAtCurrentIndex;
        }

        return totalWaterTrapped;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater obj = new TrappingRainWater();
        obj.trap(height);
    }

}

