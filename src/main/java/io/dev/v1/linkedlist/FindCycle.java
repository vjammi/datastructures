package io.dev.v1.linkedlist;

public class FindCycle {

    private void testCheckCycle() {
        Node head2 = new Node(100);
        head2.addNode(200).addNode(300).addNode(400).addNode(500).addNode(600).addNode(head2);
        Node head1 = new Node(12);
        head1.addNode(14).addNode(head2);

        Node cycleNode = checkCycle(head1);
        checkStart(head1, cycleNode);
    }

    private Node checkStart(Node head, Node cycle) {
        Node startNode = head;
        Node cycleNode = cycle;
        while(startNode != cycleNode){
            startNode = startNode.next;
            cycleNode = cycleNode.next;

            if (startNode == cycleNode){
                println("Start Node @ " +startNode.key +"-" +cycleNode.key);
                return cycleNode;
            }
        }
        return null;
    }

    private Node checkCycle(Node head) {
        if (head == null) return null;

        Node fast = head;
        Node slow = head;
        while(slow != null && fast != null){
            slow = slow.next;
            fast = fast.next.next;
            println(slow.key +"-" +fast.key +" > ");
            if (slow == fast){
                println("Cycle Found @ "+ slow.key +"-" +fast.key);
                return slow;
            }
        }
        return null;
    }

    public static void main(String[] args){
        FindCycle obj = new FindCycle();
        obj.testCheckCycle();
    }

    private void println(String str) {
        System.out.println(str);
    }

    private void print(String str) {
        System.out.print(str +" ");
    }
}
