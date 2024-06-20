package dev.vjammi.ds.v1.linkedlist;

public class Node {
     int key;
     Node next = null;

    public Node(int key) {
        this.key = key;
    }

    public void printChain(){
        Node current = this;
        while(current != null){
            System.out.print(">"+current.key);
            current = current.next;
        }
    }

    public void appendToNode(Node node) {
        this.next = node.next;
        node.next = this;
    }

    public Node addNode(Node newNode) {
        Node node = this;
        while (node.next != null)
            node = node.next;
        node.next = newNode;
        return node.next;
    }


    public Node addNode(int data) {
        Node node = this;
        while (node.next != null)
            node = node.next;
        node.next = new Node(data);
        return node.next;
    }

    public static Node deleteNode(Node head, int key) {
        if (head.key == key)
            return head.next;

        Node current = head;
        while (current.next != null) {

            Node next = current.next;
            if (next.key == key) {
                current.next = next.next;
                return head;
            }

            current = current.next;
        }

        return head;
    }

    public static void main(String[] args){
        Node head = new Node(0);
        head.addNode(1).addNode(2).addNode(3).addNode(4).addNode(5);
        System.out.println(head);
        deleteNode(head, 3);
        System.out.println(head);
    }
}
