package ds.patterns.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
     23. Merge k Sorted Lists
     You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
     Merge all the linked-lists into one sorted linked-list and return it.

     Example 1:
         Input: lists = [[1,4,5],[1,3,4],[2,6]]
         Output: [1,1,2,3,4,4,5,6]
         Explanation: The linked-lists are:
         [
         1->4->5,
         1->3->4,
         2->6
         ]
         merging them into one sorted list:
         1->1->2->3->4->4->5->6
 */

public class MergeKSortedLists {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class MinComparator implements Comparator<ListNode>{
        public int compare(ListNode node1, ListNode node2){
            Integer val1 = node1.val;
            Integer val2 = node2.val;
            return val1.compareTo(val2); // Ascending
        }
    }

    class MinIntegerComparator implements Comparator<Integer>{
        public int compare(Integer val1, Integer val2){
            return val2.compareTo(val1); // Ascending
        }
    }

    //         1->4->5>null
    // current          ^
    // next             ^
    // pq      1  4

    //         1->3->4>null
    // current          ^
    // next             ^
    // pq      1  4  1   3  4

    //         2->6>null
    // current       ^
    // next          ^
    // pq      1  4  1  3  4  2  6
    //         1 > 1 > 2 > 3 > 4 > 4 > 5 > 6
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>(new MinComparator());

        for (int i=0;i<lists.length; i++){
            ListNode current = lists[i];
            while(current!=null){
                ListNode next = current.next;  // Next is safe
                current.next = null;           // Free up the current node
                pq.add(current);               // Adding listNode to pq instead of the copy - pq.add(new ListNode(current.val));
                current = next;
            }
            // current == null
        }

        //    Note on pq.iterator()
        //    Returns an iterator over the elements in this collection.  There are no guarantees concerning the order in which the
        //    elements are returned (unless this collection is an instance of some class that provides a guarantee).
        //        ListNode head = null;
        //        ListNode tail = null;
        //         Iterator<Integer> iterator = pq.iterator();
        //         while(iterator.hasNext()){
        //             //ListNode node = iterator.next();
        //             Integer nodeVal = iterator.next();
        //             ListNode node   = new ListNode(nodeVal);
        //             node.next = null;       // Should not be necessary
        //             if (i==0) {
        //                 head = node;
        //                 tail = node;
        //             }else{
        //                 tail.next = node;
        //                 tail = node;
        //                 tail.next = null;
        //             }
        //             i++;
        //         }

        // int i=0;
        // pq      1  4  1  3  4  2  6
        // pq      1  1  2  3  4  4  6
        // node                      ^
        // head    1> 1> 2> 3> 4> 4> 6 > null
        // head    ^
        // tail                      ^
        // i=1

        ListNode tail = null;
        ListNode head = null;
        int i = 0;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            System.out.print(node.val +" > ");
            if (i == 0) {
                head = node;
                tail = node;
                node.next = null;
            } else {
                tail.next = node;
                tail = node;
                node.next = null;
            }
            i++;
        }
        return head;
    }

    private void testMergeLists() {
        //         1->4->5
        //               ^
        //         1->3->4,
        //         ^
        //         2->6
        ListNode head1 = new ListNode(1);
        head1.next =  new ListNode(4);
        head1.next.next =  new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next =  new ListNode(3);
        head2.next.next =  new ListNode(4);

        ListNode head3 = new ListNode(2);
        head3.next =  new ListNode(6);

        ListNode[] lists = new ListNode[3];
        lists[0] = head1;
        lists[1] = head2;
        lists[2] = head3;
        ListNode mergedList = mergeKLists(lists);

        while(mergedList!=null){
            System.out.print(mergedList.val +" ");
            mergedList = mergedList.next;
        }

    }

    public static void main(String[] args) {
        MergeKSortedLists obj = new MergeKSortedLists();
        obj.testMergeLists();
    }
}
