package ds.slidingwindow;

// LC-209
// Window size changes overtime
/**
    209. Minimum Size Subarray Sum
    Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous
    subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no
    such subarray, return 0 instead.

    Example 1:             8
    Input: nums = [2 3 1 2 4 3]
                     ^     ^
                     i   j
         target = 7
    Output: 2
    Explanation: The subarray [4,3] has the minimal length under the problem constraint.
*/
//Naive solution would be to find a nexted forloop - o(N2)
// Setup 2 pointers - start and end pointers.
// Move the end pointer until a window is found. Then adjust the window by moving the start pointer.
// then we move the end pointer to find next window

public class MinSizeSubArraySum {

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0)
            return  0;

        int i=0; int sum=0; int minLength = Integer.MAX_VALUE;
        for (int j=0;j<nums.length;j++){
            sum = sum + nums[j];
            if (sum >= target)
                minLength = Math.min(minLength, (j-i) + 1);
            while (sum >= target){
                minLength = Math.min(minLength, (j-i) + 1);
                System.out.println("Sum: " + sum +" minLength: " +minLength+ " i: " + i + " j: " + j);
                sum = sum - nums[i];
                i=i+1;
            }
        }
        return (minLength != Integer.MAX_VALUE) ? minLength : 0;
    }

    public static void main(String[] args) {
        MinSizeSubArraySum obj = new MinSizeSubArraySum();
        int target = 7;
        int[] nums = {2,3,1,2,4,3};
        System.out.println(obj.minSubArrayLen(target, nums));
    }

}
