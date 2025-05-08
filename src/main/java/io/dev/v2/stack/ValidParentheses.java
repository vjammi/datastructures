package io.dev.v2.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
  20. Valid Parentheses
    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

    An input string is valid if:
        Open brackets must be closed by the same type of brackets.
        Open brackets must be closed in the correct order.
        Every close bracket has a corresponding open bracket of the same type.

    Example 1:
        Input: s = "()"
        Output: true

    Example 2:
     Input: s = "()[]{}"
     Output: true

   Example 3:
     Input: s = "([)]"
     Output: false
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<String> stack = new Stack<>();

        Map<String, String> openToCloseMap = new HashMap<>();
        openToCloseMap.put("(", ")");
        openToCloseMap.put("{", "}");
        openToCloseMap.put("[", "]");
        Map<String, String> closeToOpenMap = new HashMap<>();
        closeToOpenMap.put(")", "(");
        closeToOpenMap.put("}", "{");
        closeToOpenMap.put("]", "[");

        for (int i = 0; i < s.length(); i++) {
            String c = s.charAt(i) + "";

            if (stack.isEmpty()) {
                if (!openToCloseMap.containsKey(c)){
                    return false;
                }
                stack.push(c);
            } else {
                if(openToCloseMap.containsKey(c)){ // if another open/left bracket - push it to the stack
                    stack.push(c);
                }else if (openToCloseMap.get(stack.peek()).equals(c)) { // if c is the closed bracket for front element of the stack - pop the front of the stack
                    stack.pop();
                } else if (closeToOpenMap.containsKey(c)){ // if it s valid closing bracket it should have popped the front element of the stack, in the previous check.
                    stack.push(c); // most likely an invalid closing bracket or an incorrect closing bracket
                    return false;
                }else{
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        System.out.println(validParentheses.isValid("()"));
        System.out.println(validParentheses.isValid("()[]{}"));
        System.out.println(validParentheses.isValid("(]"));
        System.out.println(validParentheses.isValid("{[]}"));
        System.out.println(validParentheses.isValid("){"));
    }
}
