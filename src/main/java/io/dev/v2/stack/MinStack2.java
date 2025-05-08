package io.dev.v2.stack;

public class MinStack2 {
        private Node head;

        private class Node {
            int x;
            int min;
            Node next;

            private Node(int x, int min, Node next) {
                this.x = x;
                this.min = min;
                this.next = next;
            }
        }

        public void push(int x) {
            if(head == null)
                head = new Node(x, x, null);
            else
                head = new Node(x, Math.min(x, head.min), head);
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.x;
        }

        public int getMin() {
            return head.min;
        }

    public static void main(String[] args) {
        //Your MinStack object will be instantiated and called as such:
        MinStack2 obj = new MinStack2();
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
