package dev.vjammi.ds.v1.linkedlist.arc;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Vijay Jammi on 02/11/2019. Converted it to a Generic impl
 */
public class LinkedListGeneric<Item> implements Iterable<Item>{

    public Node<Item> getFirst() {
        return first;
    }

    public Node<Item> getLast() {
        return last;
    }

    private Node<Item> first, last;
    private int n;

    public static class Node<Item>{
        public Item getItem() {
            return item;
        }

        public Node getNext() {
            return next;
        }

        //int key; String value;
        Item item;
        Node next;
        Node(Item item){ //int key, String value
            //this.key = key; this.value = value;
            this.item = item;
        }
    }

    public LinkedListGeneric(){
        this.first = null;
        this.last = null;
        n = 0;
    }
    public Iterator<Item> iterator(){
        return new LinkedListIterator<>(first);
    }
    private class LinkedListIterator<Item> implements Iterator<Item> {
        private Node<Item>  current;
        public LinkedListIterator(Node<Item> first){
            current = first;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public void remove(){  throw new UnsupportedOperationException();}
        @Override
        public Item next(){
            if (!hasNext()) {throw new NoSuchElementException();}
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public boolean isEmpty() {
        return first == null;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node current = first;
        while (current !=null) {
            s.append(current.item);
            s.append(' ');
        }
        return s.toString();
    }

    public int size() {
        return n;
    }

    private void testPut() {
        put(1015);
        put(1014);
        put(1013);
        put(1012);
        put(1011);
        put(1010);
        put(1009);
    }
    public void put(Integer item) { //int key, String value
        System.out.print(" >> "+item);
        if (first == null){
            last = new Node(item); //key, value
            first = last;
            n++;
        }else{
            Node oldLast = last;
            last = new Node(item); //key, value
            oldLast.next = last;
            n++;
        }
    }

    public void testFind(){
        Node<Integer> node = find(new Integer("1015"));
        System.out.println("Item Found >>>> " + node.item );
    }

    public Node find(Integer key) { //int key
        Node<Item> current = first;
        Node<Item> next = null;

        while (current != null){
            if (current.item.equals(key)){
                System.out.println("Match Found, returning current: " + current.item );
                return current;
            }
            System.out.println("Traversing :" + current.item);
            current = current.next;
        }
        return null;
    }

    private void traverseList() {
        Node<Item> current = first;
        Node<Item> next = null;
        while (current != null){
            System.out.print(" >> " + current.item);
            current = current.next;
        }
    }
    public void testTraverseRecurssively(){
        traverseRecurssively(first);
    }
    public void traverseRecurssively(Node x){ // current
        if (x == null){
            System.out.println();
            return;
        }
        System.out.print("> [" +x.item+"] ");
        traverseRecurssively(x.next); // next
        System.out.print("< [" +x.item +"] ");
    }

    public void testReversing(){
        System.out.println();
        reverseIteratively(first);
        // System.out.println("");
        // reverseIteratively2(first);
        //System.out.println("\n   first: " + this.first.key + " - last: " + this.last.key);

        //System.out.println("");
        //this.last = reverseRecurssively(first);
        //System.out.println("\n>>>>>>   first: " + this.first.key + " - last: " + this.last.key);

        //System.out.println("");
        //this.last = reverseRecurssively2(this.first);
        //System.out.println("\n first: " + this.first.key + " - last: " + this.last.key);
        //System.out.println("");
        //reverseIteratively(first);
        //this.last = reverseRecurssively2(this.first);
        //System.out.println("\n first: " + this.first.key + " - last: " + this.last.key);

        //System.out.println("");
        //this.last = reverseRecurssively3(first);
        //System.out.println("\n   first: " + this.first.key + " - last: " + this.last.key);

    }
    public void reverseIteratively(Node first){
        Node previous = null;
        Node current = first;
        Node next = null;

        // Since we are reversing the list, point current[first] to last.
        this.last = current;

        // cannot use while (children == null) - We would never get into the while then
        while (current != null){ // until we do not reach the end of the list, repeat

            // first things first - Secure the next node
            next = current.next; // Free up current.next, so that we could point it to the previous

            // reverse the pointer
            current.next = previous; // Now assign the reference of the previous to the current.next
            System.out.print(current.item +" << ");

            // when current.next is null[we have come to the end of the LinkedList1].
            // Now Point the first to this node, since we are reversing the list
            if (next == null){
                this.first = current;
            }

            // Move forward by shifting the pointers for previous and current
            previous = current;
            current = next;
        }

    }
    public void reverseIteratively2(Node first) {
        Node x = first;
        Node next = null;
        Node prev = null;

        this.last = first;
        while (x != null) {
            if (x.next == null) {
                this.first = x;
            }

            next = x.next;
            x.next = prev;
            System.out.print(" >>> x: " + x.item);

            prev = x;
            x = next;
        }
        System.out.println("\n   first: " + this.first.item + " - last: " + this.last.item);
    }
    public Node reverseRecurssively(Node x){
        // When x.next == null, we know that we got hold of the last node, We will now catch the node [x] and return it, to break the recurssive loop.
        if (x.next == null){
            System.out.println("\n >> Before... x.key[current] : " + x.item + " x.children: "+x.next +" Breaking Loop - x.children == null, set x node to first: "+ x.item);
            this.first = x; // Setting the last to first
            return x; // Returning the last node. You know that you have the last node, when x.next == null
        }
        System.out.print(">> Before... Current: " + x.item);
        Node previous = reverseRecurssively(x.next);
        if(x != null) {
            System.out.print("<< After... x[Current]: " + x.item +" Previous: " + previous.item);
            previous = x.next; // ??? i think previous is already x.next from above. Please check
            previous.next = x;
            x.next = null; // Do not forget this...
        }else{
            System.out.print("???? << After... x: " + x);
        }
        return x;
    }
    public Node reverseRecurssively2(Node current){
        if (current.next == null){
            System.out.println(" >> Breaking Loop  - Before... current.key : " + current.item + " current.next "+current.next);
            this.first = current;
            return current;
        }

        System.out.println(">> Before... Current: " + current.item);
        reverseRecurssively2(current.next);

        Node previous = current.next;
        previous.next = current;
        System.out.println("<< current.key " +current.item + " Previous (new) " + previous.item);
        current.next = null;
        return current;
    }
    public Node reverseRecurssively3(Node x){
        if (x.next !=  null) System.out.println(" x: "+x.item +" >> next: " +x.next.item);
        else System.out.println(" x: "+x.item +" >> next: " +null);
        if(x.next == null){
            this.first = x;
            return x;
        }

        Node prev = reverseRecurssively3(x.next); // prev = x.next for the current iteration of x
        //if (prev != null){ // do not need that also
        prev.next = x;
        x.next = null;
        System.out.println(" x[current]: "+x.item +" <-- previous: "+prev.item);
        //}
        return x;
    }
    public void reverse_practice() {
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

    public static void main (String[] args){
        LinkedListGeneric<Integer> list = new LinkedListGeneric<Integer>();

        list.testPut();
        System.out.println("----------------");

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer x = iterator.next();
            System.out.println("X " +x);
        }
        list.testFind();

        list.traverseList();

        list.testTraverseRecurssively();
        System.out.println("----------------");

        list.testReversing();
        System.out.println("----------------");

        list.traverseList();
        System.out.println("----------------");

    }

}

