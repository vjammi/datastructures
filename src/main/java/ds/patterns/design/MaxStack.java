package ds.patterns.design;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 716. Max Stack
 * Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.
 * Implement the MaxStack class:
 * MaxStack() Initializes the stack object.
 * void push(int x) Pushes element x onto the stack.
 * int pop() Removes the element on top of the stack and returns it.
 * int top() Gets the element on the top of the stack without removing it.
 * int peekMax() Retrieves the maximum element in the stack without removing it.
 * int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
 * You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.

 * Example 1:
 * Input
 * ["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
 * [[], [5], [1], [5], [], [], [], [], [], []]
 * Output
 * [null, null, null, null, 5, 5, 1, 5, 1, 5]
 *
 * Explanation
 * MaxStack stk = new MaxStack();
 * stk.push(5);   // [5] the top of the stack and the maximum number is 5.
 * stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
 * stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
 * stk.top();     // return 5, [5, 1, 5] the stack did not change.
 * stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
 * stk.top();     // return 1, [5, 1] the stack did not change.
 * stk.peekMax(); // return 5, [5, 1] the stack did not change.
 * stk.pop();     // return 1, [5] the top of the stack and the max element is now 5.
 * stk.top();     // return 5, [5] the stack did not change.
 *
 *
 *
 **/
class MaxStack {

    // Approach: Double Linked List + TreeMap
    TreeMap<Integer, List<ListNode>> map;
    DoubleLinkedList list;

    class DoubleLinkedList {
        ListNode head, tail;

        public DoubleLinkedList() {
            head = new ListNode(0);
            tail = new ListNode(0);
            head.next = tail;
            tail.prev = head;
        }

        public ListNode add(int val) {
            ListNode x = new ListNode(val);
            x.next = tail;
            x.prev = tail.prev;
            tail.prev = tail.prev.next = x;
            return x;
        }

        public int pop() {
            return unlink(tail.prev).val;
        }

        public int peek() {
            return tail.prev.val;
        }


        /**
         *      public ListNode unlink(ListNode node){
         *          // ...
         *      }
         *             >        >               node.prev.next = node.next;
         *       prev     node      next
         *             <        <               node.next.prev = node.prev;
         **/
        public ListNode unlink(ListNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node;
        }
    }

    class ListNode {
        int val;
        ListNode prev, next;
        public ListNode(int v) {val = v;}
    }

    public MaxStack() {
        map = new TreeMap();
        list = new DoubleLinkedList();
    }

    public void push(int x) {
        ListNode listNode = list.add(x);
        if(!map.containsKey(x))
            map.put(x, new ArrayList<ListNode>());
        map.get(x).add(listNode);
    }

    public int pop() {
        int val = list.pop();
        List<ListNode> L = map.get(val);
        L.remove(L.size() - 1);
        if (L.isEmpty()) map.remove(val);
        return val;
    }

    public int top() {
        return list.peek();
    }

    public int peekMax() {
        // Returns the first Entry in the TreeMap (according to the TreeMap's key-sort function). Returns null if the TreeMap is empty.
        // map.firstKey();
        // map.pollFirstEntry();
        // map.pollLastEntry();

        // Returns the last Entry in the TreeMap (according to the TreeMap's key-sort function). Returns null if the TreeMap is empty.
        return map.lastKey();
    }

    public int popMax() {
        int max = peekMax();
        List<ListNode> list = map.get(max);
        ListNode listNode = list.remove(list.size() - 1);
        this.list.unlink(listNode);
        if (list.isEmpty()) map.remove(max);
        return max;
    }


    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(5);   // [5] the top of the stack and the maximum number is 5.
        stack.push(4);   // [5] the top of the stack and the maximum number is 5.
        stack.push(3);   // [5] the top of the stack and the maximum number is 5.
        stack.push(6);   // [5] the top of the stack and the maximum number is 5.
        stack.push(5);   // [5] the top of the stack and the maximum number is 5.
        stack.push(7);   // [5] the top of the stack and the maximum number is 5.
        stack.push(2);   // [5] the top of the stack and the maximum number is 5.
        stack.push(1);   // [5] the top of the stack and the maximum number is 5.
        System.out.println(stack.toString());

        stack.push(5);   // [5] the top of the stack and the maximum number is 5.
        stack.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
        stack.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
        stack.top();     // return 5, [5, 1, 5] the stack did not change.
        stack.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
        stack.top();     // return 1, [5, 1] the stack did not change.
        stack.peekMax(); // return 5, [5, 1] the stack did not change.
        stack.pop();     // return 1, [5] the top of the stack and the max element is now 5.
        stack.top();     // return 5, [5] the stack did not change.
        System.out.println("");
    }
}