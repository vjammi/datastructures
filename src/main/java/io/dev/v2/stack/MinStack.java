package io.dev.v2.stack;
import java.util.Stack;

/**
     155. Min Stack
     Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

     Implement the MinStack class:
         MinStack() initializes the stack object.
         void push(int val) pushes the element val onto the stack.
         void pop() removes the element on the top of the stack.
         int top() gets the top element of the stack.
         int getMin() retrieves the minimum element in the stack.
     You must implement a solution with O(1) time complexity for each function.

     Example 1:
     Input  ["MinStack","push","push","push","getMin","pop","top","getMin"]
            [[],[-2],[0],[-3],[],[],[],[]]
     Output [null,null,null,null,-3,null,0,-2]
     Explanation
         MinStack minStack = new MinStack();
         minStack.push(-2);
         minStack.push(0);
         minStack.push(-3);
         minStack.getMin(); // return -3
         minStack.pop();
         minStack.top();    // return 0
         minStack.getMin(); // return -2
 */

public class MinStack {
        Stack<Node> stack;
        int size;
        class Node {
            int x;
            int min;
            Node(int x, int min){
                this.x = x;
                this.min = min;
            }
        }

        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int x) {
            System.out.println("push() x " +x);
            if (stack.isEmpty())
                stack.push(new Node(x, x));
            else
                stack.push(new Node(x, Math.min(x, getMin()) )); // compare x with the min of the head
            size++;
        }

        public void pop() {
            Node node = stack.pop();
            System.out.println("pop() x "+ node.x +" min " + node.min);
            size--;
        }

        public int top() {
            Node node = stack.peek();
            System.out.println("top() x "+ node.x +" min " + node.min);
            return node.x;
        }

        public int getMin() {
            Node node = stack.peek();
            System.out.println("getMin() min " + node.min);
            return node.min;
        }

     public static void main(String[] args) {
          //Your MinStack object will be instantiated and called as such:
          MinStack obj = new MinStack();
          obj.push(5);
          obj.push(5);
          obj.push(5);
          obj.getMin();
          obj.pop();
          obj.top();
          int param_3 = obj.top();
          int param_4 = obj.getMin();
          int param_5 = obj.top();
     }
}