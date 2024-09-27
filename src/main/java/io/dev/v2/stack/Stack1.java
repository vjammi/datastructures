package io.dev.v2.stack;

public class Stack1<Item> {

    private Node<Item> first = null;
    private int N = 0;

    private class Node<Item>{
        Item item;
        Node<Item> next;
    }

    public void push(Item item){
        Node<Item> oldFirst = first;

        first = new Node<Item>();
        first.item = item;
        first.next= oldFirst;
        N++;
    }

    public Item pop(){
        if (first == null) { // or size -- null
            return null;
        }
        Node<Item> nodeToReturn = first;
        first = first.next;
        N--;
        return nodeToReturn.item;
    }

    public int size(){
        return N;
    }


    public static void main(String[] args){

        ArithExprEval<Integer> stack = new ArithExprEval<>();
        stack.push(100);
        stack.push(200);
        stack.push(300);
        stack.push(400);
        System.out.println("Size : " +stack.size());
        Integer item1 = stack.pop();
        Integer item2 = stack.pop();
        stack.push(1000);
        stack.push(2000);
        stack.push(3000);
        stack.push(4000);
        System.out.println("Size : " +stack.size());
        Integer item3 = stack.pop();
        Integer item4 = stack.pop();
        Integer item5 = stack.pop();
        System.out.println("Size : " +stack.size());
        System.out.println("Items: " +item1 +" "+item2);

    }

}
