package dev.vjammi.ds.v2.usage.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class AnIterableList<Item> implements Iterable<Item>{
    ListNode head;
    ListNode tail;
    int size;

    class ListNode{
        Item value;
        ListNode next;
        void ListNode(Item value){
            this.value = value;
            this.next = null;
        }
    }

    public void add(Item value){
        if (head == null){
            ListNode node = new ListNode();
            node.value = value;
            node.next = null;
            head = node;
            tail = node;
        }else{
            ListNode node = new ListNode();
            node.value = value;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void add2(Item item) {
        ListNode oldTail = tail;
        tail = new ListNode();
        tail.value = item;
        tail.next = null;
        if (head == null )
            head = tail;
        else
            oldTail.next = tail;

        size++;
    }

    private class ListIterator implements Iterator<Item> {
        private ListNode current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.value;
            current =  current.next;
            return item;
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    @Override
    public void forEach(Consumer<? super Item> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (Item item: this){
            builder.append(item);
        }
        return builder.toString();
    }

    public static void main(String[] args) {

        AnIterableList<String> list = new AnIterableList<>();
        list.add("abc");
        list.add("def");
        list.add("ghi");
        list.add("jkl");
        System.out.println(list.size);

        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
