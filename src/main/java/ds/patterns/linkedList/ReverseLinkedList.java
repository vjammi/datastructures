package ds.patterns.linkedList;

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

    public ListNode reverseList_iterative(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode previous = null;
        ListNode current = head;
        while(current!=null){
            // previous
            // current
            ListNode next = current.next;

            current.next = previous;

            previous = current;
            current = next;
        }

        return previous;
    }

    public ListNode reverseList_recursively(ListNode head) {
        return head;
    }

    public static void main(String[] agrs){
        ReverseLinkedList obj = new ReverseLinkedList();

        int[] arr = {1,2,3,4,5};
        for (int i = 0; i <arr.length; i++){
            obj.insert(arr[i]);
        }
        obj.iterate(obj.head);

        ListNode head1 = obj.reverseList_iterative(obj.head);
        obj.iterate(head1);

        //ListNode head2 = obj.reverseList_recursively(obj.head);
        //obj.iterate(head2);
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
