package io.dev.v1.queue;
import java.util.Iterator;

public class Queue1<Item> implements Iterable<Item>{
    private Node<Item> first;
    private Node<Item> last = null;
    private int N = 0;
    public class Node<Item>{
        Item item;
        Node<Item> next;
    }
    public Iterator<Item> iterator(){
        return new ListIterator<Item>(first);
    }
    private class ListIterator<Item> implements Iterator<Item>{
        private final Node<Item> current;
        public ListIterator(Node<Item> first){
            current = first;
        }
        public boolean hasNext(){
            return current!=null;
        }
        public void remove(){ throw new UnsupportedOperationException();}
        public Item next(){
            if (current == null){
                return null;
            }
            Item item = current.item;
            return item;
        }
    }
    public Iterator<Item> iterator1(){
        return new ListIterator1<Item>(first);
    }

    private class ListIterator1<Item> implements Iterator<Item> {
        private final Node<Item> current;

        public ListIterator1(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return null;
        }
    }

    public void enqueue(Item item){
        if (last == null){
            last = new Node<Item>();
            last.item = item;
            last.next = null;
            first = last;
            N++;
            System.out.println(item +"(First " + N +"), ");
        }else{
            Node oldLast = last;
            last = new Node();
            last.item = item;
            oldLast.next = last;
            N++;
            System.out.println(item +"(" + N +"), ");
        }
    }

    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public void printContents(){
        Node<Item> node = first;
        while (node != null) {
            System.out.print(node.item + ", ");
            node = node.next;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public static void main(String[] args){
        Queue1<String> queue = new Queue1<>();
        for (int i = 0; i < 10; i++){
            queue.enqueue(i+ "");
        }
        queue.printContents();
        printContentsViaIterable(queue);

    }

    private static void printContentsViaIterable(Queue1<String> queue) {
        Iterator<String> i = queue.iterator();
        while(i.hasNext()){
            String item = i.next();
            System.out.print(item + ", ");
        }
    }


}
