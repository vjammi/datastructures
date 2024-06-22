package io.dev.v2.array;

/**
 * 121. Best Time to Buy and Sell Stock
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * */
public class BestTimeToBuySell {


    /**   Option 1: Two Passes
    //         1. Find the min value and day/index
    //         2. From the min index, iteratre forward to find the max profit
    //         Time: O(n)
    //         Space: O(n)
    //
    //     Option 2: Single pass
    //         On each iteration check
    //         1) if the price on the current day < one of the previous day price.
    //             if yes,
    //                then update the minPriceIndex and minPriceVal
    //             else
    //                keep the minPriceIndex and priceOnMinPriceIndex
    //         2) Recalculate the current the profit compared to the minPriceIndex
    //         Time:  O(n)
    //         Space: O(1)
     */

    public int maxProfit(int[] prices) {
        int minPriceIndex = 0;
        int priceOnMinPriceIndex = Integer.MAX_VALUE;
        int profit = 0;
        for (int i=0; i<prices.length; i++){
            if (prices[i] < priceOnMinPriceIndex){
                minPriceIndex  = i;
                priceOnMinPriceIndex = prices[i];
            }
            profit = Math.max(profit, prices[i] - prices[minPriceIndex]);
        }
        return profit;
    }

}
