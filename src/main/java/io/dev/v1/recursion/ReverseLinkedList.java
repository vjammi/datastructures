package io.dev.v1.recursion;

public class ReverseLinkedList {

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    ListNode newHead;

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode last = reverse(head);
        return newHead;
    }

    private ListNode reverse(ListNode node) {
        if (node.next == null) {
            this.newHead = node;
            return node;
        }
        ListNode next = node.next; // Same as the below previous node

        ListNode previous = reverse(node.next);
        previous.next = node;
        node.next = null; // The effect of this might not be visible except on the last node [the first turned to last].

        return node;
    }

    public ListNode reverseList_iterative(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode previous = null;
        ListNode current = head;
        while(current!=null){
            // previous - already set
            // current  - already set
            ListNode next = current.next;

            current.next = previous;

            previous = current;
            current = next;
        }

        return previous;
    }

}
