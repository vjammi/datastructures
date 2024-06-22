package io.dev.v1.stack;
import java.util.Stack;


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
            System.out.println("MinStack() ");
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