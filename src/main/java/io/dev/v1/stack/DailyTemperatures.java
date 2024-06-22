package io.dev.v1.stack;

// If the temperature on top of the stack is colder than the current day,
// pop the element, it means there is no warmer day than the current, record 0.
// Push the current element to the stack.

import java.util.Stack;

public class DailyTemperatures {

    class Node {
        int x;
        int i;
        Node(int x, int i){
            this.x = x;
            this.i = i;
        }
    }

    // [73, 74, 75, 71, 69, 72, 76, 73]
    // [1,  1,  4,  2,  1,  1,  0,  0 ]
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 0)
            return new int[0];

        Stack<Node> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i=temperatures.length-1; i>=0 ; i--){
            int curr = temperatures[i];
            System.out.println("Now processing "+ curr);
            while (!stack.isEmpty()){
                if (stack.peek().x > curr){
                    result[i] = stack.peek().i - i;
                    break;
                }else{
                    stack.pop();
                }
            }
            stack.push(new Node(curr, i));
            if (result[i] == 0) {
                System.out.println("Temperature warmer than the current " +curr
                        +" not found. Value at the current index is set to 0 by default");
            }
        }
        return result;
    }


    public static void main(String[] args) {

        int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};

        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] result = dailyTemperatures.dailyTemperatures(temperatures);
        for (int i=0; i<result.length-1; i++){
            System.out.print(result[i] +", ");
        }
    }
}
