package dev.vjammi.ds.v1.stack;

// https://www.youtube.com/watch?v=Jhaf7G_sO3k
public class SortAStack<Item> {

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

    public Item peep(){
        if (first == null) { // or size -- null
            return null;
        }
        Node<Item> nodeToReturn = first;
        return nodeToReturn.item;
    }

    public int size(){
        return N;
    }

    public static void main(String[] args){
        SortAStack<Integer> mainStack = new SortAStack<>();
        SortAStack<Integer> tempStack = new SortAStack<>();
        mainStack.push(50);
        mainStack.push(30);
        mainStack.push(10);
        mainStack.push(20);
        mainStack.push(40);

        System.out.println("Stack 1 Size : " +mainStack.size() +" " +mainStack);
        System.out.println("Stack 2 Size : " +tempStack.size() +" " +tempStack);

        if (mainStack.size() == 0){
            throw new IllegalArgumentException();
        }

        while(mainStack.size() != 0){
            Integer mainStackItem = mainStack.pop();

            if (tempStack.size() == 0){ // && mainStackItem != null
                tempStack.push(mainStackItem);

            }else if(tempStack.peep() != null && mainStackItem != null && mainStackItem > tempStack.peep()){
                tempStack.push(mainStackItem);

            }else if (tempStack.peep() != null && mainStackItem != null && mainStackItem < tempStack.peep()){

                Integer tempStackItemm = tempStack.peep();
                while(tempStackItemm != null && mainStackItem < tempStackItemm){
                    mainStack.push(tempStack.pop());
                    tempStackItemm = tempStack.peep();
                }
                tempStack.push(mainStackItem);
            }
        }
        mainStack = tempStack;
        tempStack = null;
        System.out.println(tempStack);
        System.out.println(mainStack);
    }
}
