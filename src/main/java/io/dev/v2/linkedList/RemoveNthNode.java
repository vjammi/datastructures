package io.dev.v2.linkedList;

public class RemoveNthNode {
    private ListNode head;
    private ListNode tail;

    public class ListNode{
        int val;
        ListNode next;

        ListNode(int x){
            val = x;
            next = null;
        }
    }


    /**
     Given linked list: 1->2->3->4->5, and n = 2.
     After removing the second node from the end, the linked list becomes 1->2->3->5.
     */
    public ListNode removeNthFromEnd_iteratively(ListNode head, int n) {
        if (head == null)
            return head;

        // Setup the dummy node to point to the head of the list
        ListNode dummy = new ListNode(0) ;
        dummy.next = head;

        // Setup the walker and runner to start at the dummy node
        ListNode walker = dummy;
        ListNode runner = dummy;

        // Advance the runner so that the dist between is N+1
        int i=0;
        while(i < n+1){
            runner =  runner.next;
            i++;
        }

        // Now advance the runner and walker one step at a time until the runner reaches the end - runner is null
        while(runner!=null){
            walker = walker.next;
            runner = runner.next;
        }

        // Delete the Nth node from the end.
        ListNode next = walker.next; // Save the next node temporarily so that its next could be set to null
        walker.next = walker.next.next;
        next.next = null;

        // return dummy.next
        return dummy.next;
    }

    public ListNode removeNthFromEnd_recursively(ListNode head, int n) {
        if (head == null)
            return head;

        // Setup the dummy node to point to the head of the list
        ListNode dummy = new ListNode(0) ;
        dummy.next = head;

        removeNthNode(dummy, n);

        return dummy.next;
    }

    private int removeNthNode(ListNode node, int n) {
        if (node == null)
            return 0;

        int level = removeNthNode(node.next, n) + 1;
        if (level == n+1){
            System.out.println("*** Node Value " +node.val+ " Level "+level);
            // Note the same iterative delete logic - deleting Nth node from the end.
            ListNode next = node.next; // Save the next node temporarily so that its next could be set to null
            node.next = node.next.next;
            next.next = null;
        }

        return level;
    }

    public static void main(String[] agrs){
        RemoveNthNode obj = new RemoveNthNode();

        int[] arr = {1,2,3,4,5};
        for (int i = 0; i <arr.length; i++){
            obj.insert(arr[i]);
        }
        obj.iterate(obj.head);

        ListNode head1 = obj.removeNthFromEnd_iteratively(obj.head, 2);
        obj.iterate(head1);

        ListNode head2 = obj.removeNthFromEnd_recursively(obj.head, 2);
        obj.iterate(head2);
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

