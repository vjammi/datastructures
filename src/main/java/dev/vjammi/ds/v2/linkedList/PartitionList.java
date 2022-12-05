package dev.vjammi.ds.v2.linkedList;

/**
     86. Partition List
     Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
     You should preserve the original relative order of the nodes in each of the two partitions.

     Input: head = [1,4,3,2,5,2], x = 3
     Output: [1,2,2,4,3,5]
 */
public class PartitionList {

    ListNode head;
    ListNode tail;

    public class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
            next = null;
        }
    }

    public PartitionList() {
        this.head = null;
    }

   /*
           dummy    head
            0        1        4      3       2       5       2       null
           back    current    front


          head
            0                 null
           back    current    front

        list1Head
             0      1       2       2
                                list1Tail

        list2Head
             0       4      3      5
                               list2Tail

    */

    public ListNode partition(ListNode head, int x) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode list1Head = new ListNode(0);
        ListNode list1Tail = list1Head;

        ListNode list2Head = new ListNode(0);
        ListNode list2Tail = list2Head;

        ListNode previous = dummy;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = null;

            if (current.val < x){
                list1Tail.next = current;
                list1Tail = list1Tail.next;
            }else{
                list2Tail.next = current;
                list2Tail = list2Tail.next;
            }

            previous.next = next;
            current = next;
        }
        list1Tail.next = list2Head.next;
        return head; // or can also return list1Head.next
    }

    public static void main(String[] agrs){
        PartitionList obj = new PartitionList();

        //int[] arr = {1,5,4,2};
        int[] arr = {1,4,3,2,5,2};
        for (int i = 0; i <arr.length; i++){
            obj.insert(arr[i]);
        }
        obj.iterate(obj.head);
        ListNode newNode = obj.partition(obj.head, 3);
        obj.iterate(newNode);
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
        while (node != null) {
            System.out.print(" " +node.val);
            node = node.next;
        }
        System.out.println(" ");
    }
    public void iterate(int index) {
        System.out.println("\n Index: " +index);
        iterate(head);
    }
}

