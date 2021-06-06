package ds.patterns.linkedList;

public class LinkedListCycleII {
    SwapNodesInPairs.ListNode head;

    public class ListNode{
        int value;
        ListNode next;
        ListNode(int x){
            value = x;
        }
    }

    public ListNode detectCycle(ListNode head) {

        if (head == null)
            return head;
        if (head.next == null)
            return null;
        if (head.next.next==null)
            return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode walker = dummy;
        ListNode runner = dummy;

        // Detect if there is a cycle
        boolean cyle = false;
        while(runner !=null && walker != null){
            walker = walker.next;
            if (runner.next==null) // ***
                return null;
            else
                runner = runner.next.next;
            if (walker == runner){
                cyle = true;
                break;
            }
        }

        // If there is a cycle then find the node where there is a cycle using floyd's cycle detection algorithm
        if (cyle){
            walker = dummy;
            // runner stays where it was last
            while(walker != runner)    {
                walker = walker.next;
                runner = runner.next;
            }
            return walker;
        }else{
            return null;
        }
    }
}
