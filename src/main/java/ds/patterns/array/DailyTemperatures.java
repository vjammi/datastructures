package ds.patterns.array;


/**
 * 739. Daily Temperatures
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i]
 * is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for
 * which this is possible, keep answer[i] == 0 instead.
 *
 *      Input: temperatures = [73,74,75,71,69,72,76,73]
 *                             0  1  2  3  4  5  6  7
 *                                   ^   >>>     ^
 *                                   i           j
 *      Output:               [1, 1, 4, 2, 1, 1, 0, 0]
 *                                  6-2
 *
 * Option 1: Two loops
 *      Start i & j at the current element
 *      For the element at the ith index, increment the jth pointer unless arr[j] > arr[i]
 *      The next warmer day from the current day will be j-i
 *
 * Option 2: Iterate array from back to front, storing the temperatures for each day in the stack
 *      Start from the last index
 *      Peek to top element from the stack to check if the temperature is greater than current
 *          If true
 *              resultArr[i] = indexOfElementOnTheTheStack - indexOfCurrentElement
 *          else
 *              pop the element from the stack and Repeat - Peek to top element from the stack to check if the temperature is greater than current
 *      Once the next warmest day for the current day is determined, put the current element in the stack and increment the current pointer
 *
 * Reference:
 *  https://youtu.be/WGm4Kj3lhRI
 **/
public class DailyTemperatures {

}
