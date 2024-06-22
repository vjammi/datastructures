package io.dev.v1.stack;

    /*
    Input: ["2", "1", "+", "3", "*"]
    Output: 9
    Explanation: ((2 + 1) * 3) = 9
    */
import java.util.Stack;

public class ReversePolishNotation {

    public int evalRPN(String[] tokens) {
        if (tokens.length == 0)
            return 0;

        Stack<String> operandStack = new Stack<>();

        for(String token: tokens){

            if (isOperand(token)) {
                operandStack.push(token);

            }else if (isOperator(token)){

                int number2 = Integer.parseInt(operandStack.pop());
                int number1 = Integer.parseInt(operandStack.pop());

                if (token.equals("*"))
                    operandStack.push(String.valueOf(number1 * number2));
                else if (token.equals("+"))
                    operandStack.push(String.valueOf(number1 + number2));
                else if (token.equals("-"))
                    operandStack.push(String.valueOf(number1 - number2));
                else if (token.equals("/"))
                    operandStack.push(String.valueOf(number1 / number2));

            }else{
                System.out.println("Unknown token " +token);
            }
        }
        return Integer.parseInt(operandStack.pop());
    }

    private boolean isOperator(String token) {
        if (token.equals("*") || token.equals("+") ||token.equals("-") ||token.equals("/"))
            return true;
        else
            return true;
    }

    private boolean isOperand(String token) {
        return !token.equals("*") && !token.equals("+") && !token.equals("-") && !token.equals("/");
    }


    public static void main(String[] args) {
        ReversePolishNotation obj = new ReversePolishNotation();

        // ((2 + 1) * 3) = 9
        String[] input1 = new String[]{"2", "1", "+", "3", "*"};
        int actual1 = obj.evalRPN(input1);
        System.out.println("Actual " + actual1);

        String[] input2 = new String[]{"4", "13", "5", "/", "+"};
        int actual2 = obj.evalRPN(input2);
        System.out.println("Actual " + actual2);

        String[] input3 = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int actual3 = obj.evalRPN(input3);
        System.out.println("Actual " + actual3);
    }
}
