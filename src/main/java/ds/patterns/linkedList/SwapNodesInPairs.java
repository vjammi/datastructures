package ds.patterns.linkedList;

public class SwapNodesInPairs {
    ListNode head;

    public class ListNode{
        int value;
        ListNode next;
        ListNode(int x){
            value = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return head;

        // Setup the dummy node to point to the head of the list
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Setup the walker and runner to start at the dummy node
        ListNode walker = dummy;
        ListNode runner = dummy;

        // Advance the runner so that the runner is 2 steps ahead of walker
        int i = 0;
        while(runner!=null && i < 2){
            runner = runner.next;
            i++;
        }

        // Now the main logic to swap the nodes
        //    Swap the nodes
        //    Advance the runner one step
        //    Advance the runner and walker 2 steps for processing the next pair
        while(runner!=null){
            // walker
            ListNode next = walker.next;
            // runner

            // Swap the Nodes
            next.next = runner.next; // 1 Point the next to runners next
            walker.next = runner;    // 2 Point the walker's next to runner
            runner.next = next;      // 3 Point the runner's next to next


            // Advance the runner 1 step to point to the last element of the current pair
            runner =  runner.next;

            // Now advance the runner and walker 2 steps for processing the next pair
            walker = runner;               // To advance the walker to take the runner's position
            int j = 0;
            while(runner!=null && j < 2){  // Advance the runner 2 steps ahead
                runner = runner.next;
                j++;
            }
        }

        return dummy.next;
    }

}
