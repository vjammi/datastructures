package dev.vjammi.ds.v2.design.problems;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * 2034. Stock Price Fluctuation
 * You are given a stream of records about a particular stock. Each record contains a timestamp and the corresponding price of the stock at that timestamp.
 * Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect. Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.
 *
 * Design an algorithm that:
 * Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
 * Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
 * Finds the maximum price the stock has been based on the current records.
 * Finds the minimum price the stock has been based on the current records.
 *
 * Implement the StockPrice class:
 * StockPrice() Initializes the object with no price records.
 * void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
 * int current() Returns the latest price of the stock.
 * int maximum() Returns the maximum price of the stock.
 * int minimum() Returns the minimum price of the stock.
 *
 * Example 1:
 * Input  ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
 *        [[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
 * Output [null, null, null, 5, 10, null, 5, null, 2]
 *
 * Explanation
 * StockPrice stockPrice = new StockPrice();
 * stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
 * stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
 * stockPrice.current();     // return 5, the latest timestamp is 2 with the price being 5.
 * stockPrice.maximum();     // return 10, the maximum price is 10 at timestamp 1.
 * stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
 *                           // Timestamps are [1,2] with corresponding prices [3,5].
 * stockPrice.maximum();     // return 5, the maximum price is 5 after the correction.
 * stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
 * stockPrice.minimum();     // return 2, the minimum price is 2 at timestamp 4.
 *
 **/
public class StockPrice {

    private final TreeMap<Integer, Integer> timestampKeyOrderedMap;
    private final TreeMap<TimestampToPricePair, Integer> priceValueOrderedMap;
    private int latestTimestamp;

    class TimestampToPriceComparator implements Comparator<TimestampToPricePair>{
        public int compare(TimestampToPricePair key1, TimestampToPricePair key2){
            return key2.price - key1.price;
        }
    }

    class TimestampToPricePair{
        int price;
        int timestamp;

        TimestampToPricePair(int timestamp, int price){
            this.timestamp = timestamp;
            this.price = price;
        }
    }

    public StockPrice() {
        timestampKeyOrderedMap = new TreeMap();
        priceValueOrderedMap = new TreeMap(new TimestampToPriceComparator());
        this.latestTimestamp = 0;
    }

    public void update(int timestamp, int price) {
        if (!timestampKeyOrderedMap.containsKey(timestamp) ){
            timestampKeyOrderedMap.put(timestamp, price);
            priceValueOrderedMap.put(new TimestampToPricePair(timestamp, price), price);
            latestTimestamp = Math.max(latestTimestamp, timestamp);
        }else{
            int prevPrice = timestampKeyOrderedMap.get(timestamp);
            timestampKeyOrderedMap.put(timestamp, price);
            priceValueOrderedMap.remove(new TimestampToPricePair(timestamp, prevPrice));
            priceValueOrderedMap.put(new TimestampToPricePair(timestamp, price), price);
            latestTimestamp = Math.max(latestTimestamp, timestamp); // latestTimestamp will not get updated if timestamp is prior to the most recent timestamnp
        }
    }

    public int current() {
        return timestampKeyOrderedMap.get(latestTimestamp);
    }

    public int maximum() {
        return priceValueOrderedMap.firstEntry().getValue();
    }

    public int minimum() {
        return priceValueOrderedMap.lastEntry().getValue();
    }

    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10);   // Timestamps are [1] with corresponding prices [10].
        stockPrice.update(2, 5);    // Timestamps are [1,2] with corresponding prices [10,5].
        stockPrice.current();                      // return 5, the latest timestamp is 2 with the price being 5.
        stockPrice.maximum();                      // return 10, the maximum price is 10 at timestamp 1.
        stockPrice.update(1, 3);    // The previous timestamp 1 had the wrong price, so it is updated to 3.
                                                   // Timestamps are [1,2] with corresponding prices [3,5].

        stockPrice.maximum();                      // return 5, the maximum price is 5 after the correction.
        stockPrice.update(4, 2);    // Timestamps are [1,2,4] with corresponding prices [3,5,2].
        stockPrice.minimum();                      // return 2, the minimum price is 2 at timestamp 4.
    }
}
