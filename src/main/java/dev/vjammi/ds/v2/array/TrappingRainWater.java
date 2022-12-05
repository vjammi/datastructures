package dev.vjammi.ds.v2.array;

/**
    42. Trapping Rain Water
    Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6
    Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
                 In this case, 6 units of rain water (blue section) are being trapped.
*/
public class TrappingRainWater {

    // Solution #1 Brute Force
    // At each index, find the max(left) and max(right) by iterating left and right side of the index
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
                maxLeft = height[i];
            }else{
                for (int j=i-1; j>=0; j--){
                    maxLeft = Math.max(maxLeft, height[j]);
                }
            }

            if (i == height.length-1){
                maxRight = height[i];
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

    // Solution #2 - With 2 Arrays
    //        Runtime O(n)
    //        Space O(n)
    //     Iterating from left to right,
    //          find the max(left) for each element in the array and store it in  maxLeftArray
    //     Iterating from right to left,
    //          find the max(right) for each element in the array and store it in maxRightArray
    //     Once we build the 2 arrays, we iterate thru the array one more time to
    //          find the water trapped at each index, which would be
    //          min(max(right), max(left)) - height[i]

    //               input                        = [0,1,0,2,1,0,1,3,2,1,2,1]
    //              maxLeftArray[]                =
    //             maxRightArray[]                =
    //min(maxLeft[i], maxRight[i]) - height[i]    =
    //               output                       = [0 0 1 0 1 2 1 0 0 1 0 0]
    public int trapWith2Arrays(int[] height) {

        int maxLeft = 0;
        int[] maxLeftArray = new int[height.length];
        for (int j=0; j<height.length; j++){
            if (j==0)
                maxLeft = height[j];
            else
                maxLeft = Math.max(maxLeft, height[j]);

            maxLeftArray[j] = maxLeft;
        }

        int maxRight = 0;
        int[] maxRightArray = new int[height.length];
        for (int k=height.length-1; k>=0; k--){
            if (k == height.length-1)
                maxRight = height[k];
            else
                maxRight = Math.max(maxRight, height[k]);

            maxRightArray[k] = maxRight;
        }

        int totalWaterTrapped = 0;
        for (int i=0; i<height.length; i++){
            int waterTrappedAtCurrentIndex = Math.min(maxLeftArray[i], maxRightArray[i]) - height[i];
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

