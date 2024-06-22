package io.dev.v2.linkedList;



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
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode walker = dummy;
        ListNode runner = dummy;

        // Detect if there is a cycle
        boolean cyle = false;
        while(runner !=null && walker != null){
            walker = walker.next;

            // Note: If there is no cycle runner will at some point will be null.
            //       If runner == null, it means we have no cycle, hence return null
            if (runner !=null && runner.next!=null)
                runner = runner.next.next; //
            else
                return null; // no cycle

            // if walker and runner meet, we have a cycle. we break out of here
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
