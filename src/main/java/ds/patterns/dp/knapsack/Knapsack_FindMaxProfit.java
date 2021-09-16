package ds.patterns.dp.knapsack;

// https://youtu.be/mGfK-j9gAQA
public class Knapsack_FindMaxProfit {

    int maxProfit = 0;
    public int maxProfit(int[] weights, int[] profits, int capacity){
        backtrack(weights, profits, capacity, weights.length-1);
        return maxProfit;
    }

    int backtrack(int[] weights, int[] profits, int target, int n){
        if (n == 0 || target == 0) {
            System.out.println(" " + maxProfit);
            return 0;
        }

        // Include
        if (weights[n] < target) {
            int resultAfterInclude = profits[n] + backtrack(weights, profits, target - weights[n], n-1);       // Include
            int resultAfterExclude = backtrack(weights, profits, target, n-1);                                       // Exclude

            int maxProfitFromSubtree = Math.max(resultAfterExclude, resultAfterInclude);
            maxProfit = Math.max(maxProfit, maxProfitFromSubtree);
            // System.out.println(maxProfitFromSubtree + " "+maxProfit);
            return maxProfitFromSubtree;
        }
        // Exclude
        else {
            int resultAfterExclude = backtrack(weights, profits, target, n - 1);                                    // Exclude
            return resultAfterExclude;
        }
    }

    public static void main(String[] args) {
        Knapsack_FindMaxProfit obj = new Knapsack_FindMaxProfit();
        int[] weight = {4, 3, 5};     //{3, 2, 4};
        int[] profit = {7, 6, 11};    //{6, 8, 7};
        int target   = 10;        // 8;
        System.out.println(obj.maxProfit(weight, profit, target));
    }
}
