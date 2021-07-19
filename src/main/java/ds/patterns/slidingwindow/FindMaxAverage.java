package ds.patterns.slidingwindow;

public class FindMaxAverage {

    public double findMaxAverage(int[] nums, int k){
        int n = nums.length, sum = 0, maxSum = Integer.MIN_VALUE;

        for (int i=0; i<k; i++){
            sum = sum + nums[i];
        }

        int j = 0;
        for (int i=k; i<=n; i++){
            maxSum = Math.max(sum, maxSum);

            sum = sum - nums[j];
            j--;
        }

        return (maxSum * 1.0) / k;
    }
}
