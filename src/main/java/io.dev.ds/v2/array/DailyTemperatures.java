package dev.vjammi.ds.v2.array;


import java.util.Stack;

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

    class Node {
        int temp;
        int i;
        Node(int temp, int i){
            this.temp = temp;
            this.i = i;
        }
    }

    /**
     *  temperatures[]  [73, 74, 75, 71, 69, 72, 76, 73]
     *                   0   1   2   3   4   5   6   7
     *        current: (75,2)    ^
     *        stack:   (71, 3), (69, 4), (72, 5), (76, 6)
     *
     *  resultArr[]:    [1,  1,  4,  2,  1,  1,  0,  0 ]
     *                           ^
     *        current (72,5)
     *        stack:  (76, 6)
     *        result[i]: 6-5 =1
     *
     *        poped:  (73, 7)
     *
     */

    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 0)
            return new int[0];

        Stack<Node> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i=temperatures.length-1; i>=0 ; i--){

            int currTemp = temperatures[i];
            System.out.println("Now processing "+ currTemp);

            while (!stack.isEmpty()){
                Node topElement = stack.peek();
                if (topElement.temp > currTemp){
                    result[i] = topElement.i - i;
                    break;
                }else{
                    stack.pop();
                }
            }

            stack.push(new Node(currTemp, i));

            if (result[i] == 0) {
                System.out.println("Temperature warmer than the current " +currTemp +" not found. " +
                                    " Value at the current index is set to 0 by default");
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] tempArr = new DailyTemperatures().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        System.out.println(tempArr);
    }

}
