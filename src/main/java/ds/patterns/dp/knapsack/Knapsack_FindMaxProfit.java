package ds.patterns.dp.knapsack;

// https://youtu.be/mGfK-j9gAQA
public class Knapsack {

    int maxProfit = 0;
    public int findMaxProfit(int[] weights, int[] profits, int capacity){
        return backtrack(weights, profits, capacity, weights.length-1);
        //return maxProfit;
    }

    int backtrack(int[] weights, int[] profits, int target, int n){
        if (n == 0 || target == 0)
            return 0;

        // Exclude
        if (weights[n] > target) {
            int resultAfterExclude = backtrack(weights, profits, target, n - 1);                                    // Exclude
            return resultAfterExclude;
        }

        // Include
        else {
            int resultAfterExclude = backtrack(weights, profits, target, n-1);                                       // Exclude
            int resultAfterInclude = profits[n] + backtrack(weights, profits, target - weights[n], n-1);       // Include
            return Math.max(resultAfterExclude, resultAfterInclude);
        }
    }

    public static void main(String[] args) {
        Knapsack obj = new Knapsack();
        int[] weight = {3, 2, 4};
        int[] profit = {6, 8, 7};
        int targetWeight = 8;
        System.out.println(obj.findMaxProfit(weight, profit, targetWeight));
    }
}
