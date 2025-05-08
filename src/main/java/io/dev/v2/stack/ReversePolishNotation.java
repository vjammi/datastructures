package io.dev.v2.stack;

    /*
    Input: ["2", "1", "+", "3", "*"]
    Output: 9
    Explanation: ((2 + 1) * 3) = 9
    */
import java.util.Stack;

/**
     150. Evaluate Reverse Polish Notation

     You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
     Evaluate the expression. Return an integer that represents the value of the expression.

     Note that:
         The valid operators are '+', '-', '*', and '/'.
         Each operand may be an integer or another expression.
         The division between two integers always truncates toward zero.
         There will not be any division by zero.
         The input represents a valid arithmetic expression in a reverse polish notation.
         The answer and all the intermediate calculations can be represented in a 32-bit integer.

    Example 1:
     Input: tokens = ["2","1","+","3","*"]
     Output: 9
     Explanation: ((2 + 1) * 3) = 9

    Example 2:
     Input: tokens = ["4","13","5","/","+"]
     Output: 6
     Explanation: (4 + (13 / 5)) = 6
     Example 3:

     Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
     Output: 22
     Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     = ((10 * (6 / (12 * -11))) + 17) + 5
     = ((10 * (6 / -132)) + 17) + 5
     = ((10 * 0) + 17) + 5
     = (0 + 17) + 5
     = 17 + 5
     = 22
 **/
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

        Integer.parseInt("");
    }
}
