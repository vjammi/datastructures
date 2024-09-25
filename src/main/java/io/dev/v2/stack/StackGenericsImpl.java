package io.dev.v2.stack;

import java.util.Iterator;

/**
 * Created by Vijay Jammi on 05/18/2018.
 */
public class StackGenericsImpl<Item> implements Iterable<Item>{

    private Node first = null;
    private int N = 0;

    private class Node{
        Item item;
        Node nextNode;
    }

    public void push(Item item){
        if (first == null){
            first = new Node();
            first.item = item;
            first.nextNode = null;
            N++;
            System.out.println(item +"(First " + N +"), ");
        }else{
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.nextNode = oldFirst;
            N++;
            System.out.println(item +"(" + N +"), ");
        }
    }

    public Item pop(){
        Item item = null;
        item = first.item;
        first = first.nextNode;
        N--;
        return item;
    }

    public void printContents(){
        Node node = first;
        while (node != null) {
            System.out.print(node.item + "(size="+N +"), ");
            node = node.nextNode;
        }
        System.out.println(" ");
    }

    private int size() {
        return N;
    }

    public static void main(String[] args){

        StackGenericsImpl<String> stack = new StackGenericsImpl<>();

        // Push
        for (int i = 0; i < 10; i++){
            stack.push(i+ "");
        }
        stack.printContents();

        System.out.println("Iterating Next... - Start");
        Iterator<String> iterator =  stack.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("Iterating Next... - End");

        // Pop
        for (int i = 0; i < 10; i++){
            String item = stack.pop();
            stack.printContents();
            System.out.println();
        }

   }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    public class ListIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current.nextNode == null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.nextNode;
            return item;
        }

        @Override
        public void remove() {

        }
    }
}
