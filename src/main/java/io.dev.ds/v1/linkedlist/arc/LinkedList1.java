package dev.vjammi.ds.v1.linkedlist.arc;

/**
 * Created by Vijay Jammi on 06/05/2018.
 */
// Java program for reversing the linked list

class LinkedList1 {
    static Node first;

    static class Node {
        int data;
        Node next;
        Node(int d) {
            data = d;
            next = null;
        }
    }

    /* Function to reverseIteratively the linked list */
    public void reverse() {
        Node prev = null;
        Node current = first;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        first = prev;
    }

    // prints content of double linked list
    void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        LinkedList1 list = new LinkedList1();
        first = new Node(85);
        first.next = new Node(15);
        first.next.next = new Node(4);
        first.next.next.next = new Node(20);
        first.next.next.next.next = new Node(40);
        first.next.next.next.next.next = new Node(50);

        System.out.println("Given Linked list");
        list.printList(first);
        list.reverse();
        System.out.println();
        System.out.println("Reversed linked list ");
        list.printList(first);
    }
}
