package ds.recursion;

public class SwapNodesInPairs {

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode swapPairs_iterative(ListNode head) {
        if (head == null)
            return head;

        // Setup the dummy node to point to the head of the list
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Setup the walker and runner to start at the dummy node
        ListNode walker = dummy;
        ListNode runner = dummy;

        // // Advance the runner so that the runner is 2 steps ahead of walker
        int i = 0;
        while(runner!=null && i < 2){
            runner = runner.next;
            i++;
        }

        // Now the main logic to swap the nodes
        // After swapping the nodes
        // Advance the runner one step
        // Now advance the runner and walker 2 steps for processing the next pair
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

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // Setup the dummy node to point to the head of the list
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        swap(dummy);
        return dummy.next;
    }

    private void swap(ListNode node) {
        if (node == null || node.next == null || node.next.next == null) // takes care of odd input such as [1,2,3]
            return;

        ListNode walker = node;
        ListNode runner = node;

        // Advance runner 2 steps not n+1/2+1 steps because n+1 would become null at the end
        // The current node here is the node before the nodes that are to ve swapped.
        //             node     >       1       >      2       >    3
        //                1     >       3       >      4       >    null
        //      dummy/walker        walkerNext       runner      runnerNext
        int steps = 0;
        for(int i=0; i<2; i++){
            runner = runner.next;
            steps++;
        }

        // Swap nodes
        ListNode walkerNext = walker.next;
        ListNode runnerNext = runner.next;
        walkerNext.next = runnerNext;
        runner.next = walkerNext;
        node.next = runner;
        node = node.next;

        swap(node.next);
    }

}
