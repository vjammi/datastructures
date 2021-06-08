package ds.recurssion;

import java.util.List;

public class SwapNodesInPairs {

    private ListNode head;
    private ListNode tail;

    public class ListNode {

        int val;
        ListNode next;

        public ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val; this.next = next;
        }
    }

    public SwapNodesInPairs() {
    }

    private void buildList(int[] nodes) {
        for (int nodeVal: nodes){
            if (head == null){
                head = new ListNode(nodeVal);
                tail = head;
            }else{
                ListNode newNode = new ListNode(nodeVal);
                tail.next = newNode;
                tail = newNode;
            }
        }
    }

    public void swapPairs() {
        printList(head);
        head = swapPairs(head);
        printList(head);
    }

    private void printList(ListNode head) {
        ListNode current = head;
        while(current!=null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println(" ");
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return null;

        ListNode aux =  new ListNode(0, head);

        //aux = swapUphill(aux, head);
        //return aux.next;

        aux = swapDownhill(aux);
        return aux.next;
    }

    public ListNode swapUphill(ListNode previousNode, ListNode currentNode) {
        if (currentNode == null)
            return null;

        ListNode nextNode = currentNode.next;
        if (nextNode != null) {
            System.out.println(previousNode.val +" " +currentNode.val +" " + nextNode.val);
            ListNode nextToNextNode = nextNode.next;
            previousNode.next = nextNode;
            nextNode.next = currentNode;
            currentNode.next = nextToNextNode;
        }
        swapUphill(currentNode, currentNode.next);
        return previousNode;
    }

    public ListNode swapDownhill_borrowed(ListNode currentNode) {
        if (currentNode == null || currentNode.next == null)
            return currentNode;

        ListNode nextNode = currentNode.next;
        currentNode.next = swapDownhill(currentNode.next.next);
        nextNode.next = nextNode;
        return nextNode;
    }

    public ListNode swapDownhill(ListNode currentNode) {
        if (currentNode.next == null || currentNode.next.next == null){ //currentNode == null ||
            System.out.println("Recurssion Base Case reached " +currentNode.val);
            return currentNode;
        }

        ListNode nextNode = currentNode.next; //equivalent to returning a node out from the next call
        nextNode.next = swapDownhill(currentNode.next.next);

        ListNode nextToNextNode = nextNode.next;
        System.out.println(currentNode.val +" " +nextNode.val +" " + nextToNextNode.val);

        currentNode.next    =  nextToNextNode;     // 1
        nextNode.next       = nextToNextNode.next;    // 2 ???
        nextToNextNode.next = nextNode;         // 3

        return currentNode;
    }

    public static void main(String[] args) {
        int[] nodes = {1,2,3,4,5,6,7,8};
        SwapNodesInPairs list = new SwapNodesInPairs();
        list.buildList(nodes);
        list.swapPairs();
    }
}
