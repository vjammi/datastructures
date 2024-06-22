package io.dev.v2.linkedList;

public class SwapNodesInPairs {

    private ListNode head;
    private ListNode tail;

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs_iteratively(ListNode head) {
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
        while (i < 2) {
            runner = runner.next;
            i++;
        }

        // Now the main logic to swap the nodes
        //    Swap the nodes
        //    Advance the runner one step
        //    Advance the runner and walker 2 steps for processing the next pair
        while (runner != null) {
            // walker
            ListNode next = walker.next;
            // runner

            // Swap the Nodes
            next.next = runner.next; // 1 Point the next to runners next
            walker.next = runner;    // 2 Point the walker's next to runner
            runner.next = next;      // 3 Point the runner's next to next
            // Advance the runner 1 step to point to the last element of the current pair
            runner = runner.next;

            // For swapping the next pair, advance the runner and walker 2 steps
            walker = runner;               // Advance the walker to take the runner's position
            int j = 0;
            while (j < 2) {  // Advance the runner 2 steps ahead
                runner = runner.next;
                j++;
            }
        }

        return dummy.next;
    }


    public ListNode swapPairs_recursively(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // Setup the dummy node to point to the head of the list
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        swap(dummy);
        return dummy.next;
    }

    private void swap(ListNode node) {
        if (node.next == null || node.next.next == null) // takes care of odd input such as [1,2,3] // note needed node == null
            return;

        ListNode walker = node;
        ListNode runner = node;

        // Advance runner 2 steps not n+1/2+1 steps because n+1 would become null at the end
        // The current node here is the node before the nodes that are to be swapped.
        //     dummy/node 0     >       1       >      2       >    3    >   4   >   null
        //                1     >       3       >      4       >    null
        //       node/walker        walkerNext       runner      runnerNext
        for (int i = 0; i < 2; i++)
            runner = runner.next;

        // Swap nodes
        ListNode walkerNext = walker.next;
        ListNode runnerNext = runner.next;
        walkerNext.next = runnerNext;
        runner.next = walkerNext;
        node.next = runner;

        node = node.next;


        swap(node.next);

    }

    public static void main(String[] agrs) {
        SwapNodesInPairs obj = new SwapNodesInPairs();

        int[] arr = {1, 2, 3, 4, 5};
        for (int i = 0; i < arr.length; i++) {
            obj.insert(arr[i]);
        }
        obj.iterate(obj.head);

        ListNode head1 = obj.swapPairs_iteratively(obj.head);
        obj.iterate(head1);

        ListNode head2 = obj.swapPairs_recursively(obj.head);
        obj.iterate(head2);
    }

    private void insert(int x) {
        if (head == null) {
            ListNode node = new ListNode(x);
            tail = node;
            head = tail;
        } else {
            ListNode last = tail;
            ListNode node = new ListNode(x);
            last.next = node;
            tail = node;
        }
    }

    public void iterate(ListNode node) {
        System.out.print("Nodes: ");
        while (node != null) {
            System.out.print(" " + node.val);
            node = node.next;
        }
        System.out.println(" ");
    }

}
