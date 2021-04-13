package ds.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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

        if (stack.isEmpty()){
            return true;
        }else{
            return false;
        }
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
