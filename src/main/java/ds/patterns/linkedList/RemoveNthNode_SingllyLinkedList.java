package ds.patterns.linkedList;

public class RemoveNthNode_SingllyLinkedList {
    ListNode head;

    public class ListNode{
        int value;
        ListNode next;
        ListNode(int x){
            value = x;
        }
    }

    public void removeNthFromEnd(int index) {
        head = removeNthFromEnd(head, index);
    }

    /**
     Given linked list: 1->2->3->4->5, and n = 2.
     After removing the second node from the end, the linked list becomes 1->2->3->5.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
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
        while(runner!=null & i < n+1){
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

    public ListNode removeNthFromEnd_previous(ListNode head, int n) {
        if (head != null && head.next == null) {
            head = null;
            return head;
        }

        ListNode current = head;
        while (current != null) {
            ListNode forward = current;

            int i = n;
            while (i != 0 && forward.next != null) {
                forward = forward.next;
                i--;
            }

            if (i == 1 && current == head && forward.next == null) {
                head = current.next;
                current.next = null;
                break;
            }

            if (i == 0 && forward.next == null) {
                current.next = current.next.next ; // = forward;
                break;
            }else {
                current = current.next;
            }
        }

        return head;
    }

    private void add(int[] arr) {
        ListNode last = null;
        for(int i=0; i<arr.length; i++){
            if (head == null){
                last = new ListNode(arr[i]);
                head = last;
            }else{
                ListNode oldLast = last;
                last = new ListNode(arr[i]);
                oldLast.next = last;
            }
        }
        System.out.println("\n-----------------");
        iterate();
    }

    public void iterate() {
        ListNode node = head;
        while (node != null) {
            System.out.print(" " +node.value);
            node = node.next;
        }
        if (node != null)  System.out.println(" " +node.value);
    }
    public void iterate(int index) {
        System.out.println("\n Index: " +index);
        iterate();
    }
    public static void main(String[] agrs){
        RemoveNthNode_SingllyLinkedList nthList = new RemoveNthNode_SingllyLinkedList();

        int index = 0;
        int n = 5;
        int[] arr = {1,2,3,4,5}; index = 1;
        for (int i = 0; i <arr.length; i++){
            nthList.head = null;
            nthList.add(arr);
            nthList.removeNthFromEnd(n);
            nthList.iterate(n);
            n = n - 1;
        }

        nthList.head = null;
        int[] arr1 = {1, 2}; index = 1;
        nthList.add(arr1);
        nthList.removeNthFromEnd(index);
        nthList.iterate(index);

        nthList.head = null;
        int[] arr2 = {1, 2}; index = 2;
        nthList.add(arr2);
        nthList.removeNthFromEnd(index);
        nthList.iterate(index);

        nthList.head = null;
        int[] arr3 = {1}; index = 1;
        nthList.add(arr3);
        nthList.removeNthFromEnd(index);
        nthList.iterate(index);


    }
}

