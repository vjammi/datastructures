package ds.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code LinkedQueue} class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *
 *  It supports the usual <em>insert</em> and <em>delete</em>
 *  operations, along with methods for peeking at the first item,
 *  testing if the queue is empty, and iterating through
 *  the items in FIFO order.
 *  <p>
 *  This implementation uses a singly linked list with a non-static nested class
 *  for linked-list nodes.  See {@link Queue} for a version that uses a static nested class.
 *  The <em>insert</em>, <em>delete</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class SinglyLinkedList<Item> implements Iterable<Item> {
    private Node head;    // beginning of queue
    private Node tail;     // end of queue
    private int n;         // number of elements on queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    /**
     * Initializes an empty queue.
     */
    public SinglyLinkedList() {
        head = null;
        tail = null;
        n = 0;
        assert check();
    }

    /**
     * Is this queue empty?
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of items in this queue.
     * @return the number of items in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the item least recently added to this queue.
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return head.item;
    }

    /**
     * Adds the item to this queue.
     * @param item the item to add
     */
    public void insert(Item item) {
        Node oldTail = tail;
        tail = new Node();
        tail.item = item;
        tail.next = null;
        if (isEmpty())
            head = tail;
        else
            oldTail.next = tail;

        n++;
        assert check();
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item delete() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = head.item;
        head = head.next;
        n--;
        if (isEmpty()) tail = null;   // to avoid loitering
        assert check();
        return item;
    }

    /**
     * Returns a string representation of this queue.
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    // check internal invariants
    private boolean check() {
        if (n < 0) {
            return false;
        }
        else if (n == 0) {
            if (head != null) return false;
            if (tail != null) return false;
        }
        else if (n == 1) {
            if (head == null || tail == null) return false;
            if (head != tail)                 return false;
            if (head.next != null)            return false;
        }
        else {
            if (head == null || tail == null) return false;
            if (head == tail)      return false;
            if (head.next == null) return false;
            if (tail.next  != null) return false;

            // check internal consistency of instance variable n
            int numberOfNodes = 0;
            for (Node x = head; x != null && numberOfNodes <= n; x = x.next) {
                numberOfNodes++;
            }
            if (numberOfNodes != n) return false;

            // check internal consistency of instance variable last
            Node lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            if (tail != lastNode) return false;
        }

        return true;
    }


    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new LinkedIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * Unit tests the {@code LinkedQueue} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<String>();

        String[] strings = {"1", "-", "2", "-", "3", "11", "-", "22", "-", "33"};
        for (String item: strings){
            if (!item.equals("-"))
                list.insert(item);
            else if (!list.isEmpty())
                System.out.println(list.delete() + " ");
        }

        Iterator<String> iterator = list.iterator();

        while(iterator.hasNext()){
            String item = iterator.next();
            System.out.println(item);
        }

        System.out.println("(" + list.size() + " left on queue)");
    }
}



