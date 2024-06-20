package dev.vjammi.ds.v1.queue;
/**
 * Created by Vijay Jammi on 06/12/2017.
 */
import java.util.Iterator;

public class Queue2<Item> {

    private Node<Item> first; // link to least recently added node
    private Node<Item> last; // link to most recently added node
    private int N; // number of items on the queue

    private class Node<Item>  { // nested class to define nodes
        Item item;
        Node<Item> next;
    }

    public boolean isEmpty() {
        return first == null;
    } // Or: N == 0.

    public int size() {
        return N;
    }

    public void enqueue(Item item){
        // Add item to the end of the list.
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        N++;
    }

    public Item dequeue() {
        // Remove item from the beginning of the list.
        Item item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        N--;

        return item;
    }
    // See page 155 for iterator() implementation.
    // See page 150 for test client main().

    public Iterator<Item> iterator(){
        return new ListIterator<Item>(first);
    }

    public static void main(String[] args){
        Queue2 queue =  new Queue2();
        System.out.println(queue.size());
    }

    public class ListIterator<Item> implements Iterator<Item> {
        Node<Item> current;

        public ListIterator(Node first){
            this.current = first;
        }

        public boolean hasNext(){
            return current != null;
            // or return current != null
        }
        public Item next(){
            if (current == null){
                return null;
            }
            Item item = current.item;
            return item;
        }
    }

}

