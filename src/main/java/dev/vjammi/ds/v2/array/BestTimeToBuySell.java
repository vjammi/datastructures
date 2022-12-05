package dev.vjammi.ds.v2.array;

/**
 * 121. Best Time to Buy and Sell Stock
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 *
 * */
public class BestTimeToBuySell {


    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i=0; i<prices.length-1; i++){
            int minPrice = prices[i];
            for (int j=i+1; j<prices.length; j++){
                int currentDayPrice = prices[j];
                int currentDayProfit = currentDayPrice - minPrice;
                if (currentDayPrice > minPrice && currentDayProfit > maxProfit)
                    maxProfit = currentDayProfit;
            }
        }
        return maxProfit;
    }

}
