package io.dev.v2.linkedList;

public class ReverseLinkedList {
    private ListNode head;
    private ListNode tail;

    public class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }
    //                         head
    //               null       1   >   2   >   3   >   4   >   5   >   null
    //                ^         ^       ^
    // 1-reverse     prev <(1) curr    next
    // 2-increment             prev    curr    next
    //                                                          prev    curr
    //                                                          head
    public ListNode reverseList_iterative(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode previous = null; // Important for the first node, that needs to be turned to last
        ListNode current = head;
        while(current!=null){
            // previous
            // current
            ListNode next = current.next;

            current.next = previous; // Turning the direction of the next. tail.next==null

            previous = current;
            current = next;
        }

        this.head = previous;   // Set head to the previous
        return previous;        // Why return previous but not current? Because the current becomes null and no longer go inside while
    }

    public ListNode reverseList_recursively(ListNode head) {
        ListNode last = reverse(head);
        return head;
    }

    /*
        #    node    previous   next
        --------------------------------
        4    5                  null
        3    4    <  5
        2    3    <  4          null
        1    2      3           null
        0    1      2           null
        return 1

  head  1  currNode
        |  ^
        2  prev
        |  ^
        3
        |  ^
        4  currNode
        |  ^
        5  head / prev   -- when node.next == null, point node to head && return node as prev
        |
        Null

    //                         head
    //                      1   >   2   >   3   >   4   >   5   >   null

    //                 (tail.next==null)
    //                     tail                  newHead
    //             null  <  1   <   2   <   3   <   4   <   5
    //                          x>      x>      x>      x>
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;

        ListNode tail = reverse(head);
        tail.next = null;
        System.out.println("New Head " +newHead.val +" New Tail " +tail.val);

        return newHead;
    }

    ListNode newHead;
    public ListNode reverse(ListNode node) {
        if (node.next == null){
            newHead = node;
            return node;
        }

        ListNode previous =  reverse(node.next);
        previous.next = node; // Change direction

        return node;
    }

    public static void main(String[] agrs){
        ReverseLinkedList obj = new ReverseLinkedList();

        int[] arr = {1,2,3,4,5};
        for (int i = 0; i <arr.length; i++){
            obj.insert(arr[i]);
        }
        obj.iterate(obj.head);
        obj.reverseList_iterative(obj.head);
        obj.iterate(obj.head);
        System.out.println();
        obj.iterate(obj.head);
        obj.reverseList_recursively(obj.head);
        obj.iterate(obj.head);
    }

    private void insert(int x) {
        if (head == null){
            ListNode node = new ListNode(x);
            tail = node;
            head = tail;
        }else{
            ListNode last = tail;
            ListNode node = new ListNode(x);
            last.next = node;
            tail = node;
        }
    }

    public void iterate(ListNode node) {
        System.out.print("Nodes: ");
        while (node != null) {
            System.out.print(" " +node.val);
            node = node.next;
        }
        System.out.println(" ");
    }
}
