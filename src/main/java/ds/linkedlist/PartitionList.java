package ds.linkedlist;

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

    public ListNode partition(ListNode head, int x) {

        if (head == null)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);

        ListNode walker = dummy;
        ListNode runner = dummy;

        ListNode runner1 = dummy1;
        ListNode runner2 = dummy2;

        int counter = 0;
        while(runner!=null && counter < 2){
            runner = runner.next;
            counter++;
        }

        // Iterate thru the original list splitting the partitioning the list into 2
        while(walker.next!=null){

            // Freeup the node from the list
            ListNode next = walker.next;
            walker.next = runner; // remove walkers next element from the list
            next.next = null;     // dis-associate the node from the list by setting up the next to null

            if (next.val < x){
                runner1.next = next;
                runner1 = runner1.next;
            }else{ // >= x
                runner2.next = next;
                runner2 = runner2.next;
            }

            if(runner!=null) {
                runner = runner.next; // Advance runner by 1 step
            }

        }
        runner1.next=dummy2.next;
        return dummy1.next;
    }

    public static void main(String[] agrs){
        PartitionList obj = new PartitionList();

        int[] arr = {1,5,4,2};
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

