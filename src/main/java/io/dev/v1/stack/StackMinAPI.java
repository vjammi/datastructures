package io.dev.v1.stack;

public class StackMinAPI<Item> {

    private Node<Item> first = null;
    private Node<Item> tempFirst = null;
    private int N = 0;
    private Item minItem;

    private class Node<Item>{
        Item item;
        Node<Item> next;
    }

    public void push(Item item) {
        Node<Item> oldFirst = first;

        first = new Node<Item>();
        first.item = item;
        first.next= oldFirst;
        if (minItem == null){
            minItem =  item;
        }else if (!item.equals(null) && !minItem.equals(null) && lesser(item, minItem)){
            minItem =  item;
        }
        N++;
    }

    public void pushTemp(Item item){
        Node<Item> oldTempFirst = tempFirst;
        tempFirst = new Node<Item>();
        tempFirst.item = item;
        tempFirst.next= oldTempFirst;
    }

    private boolean lesser(Item item, Item minItem){

        //if (item instanceof Integer) {
            Integer itemVal = ((Integer) item).intValue();
            Integer minItemVal = ((Integer) minItem).intValue();

        return itemVal < minItemVal;
        //}
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

    public Item min()  {
        Item itemToReturn = null;
        Node current = first;
        while(current != null){
            if(current.item.equals(minItem)){
                itemToReturn = (Item) current.item;
            }else{
                Item item = (Item) current.item;
                pushTemp(item);
            }
            current = current.next;
        }
        first = null;
        N = 0;
        minItem = null;
        Node current2 = tempFirst;
        while(current2 != null){
            Item item = (Item) current2.item;
            push(item);
            current2 = current2.next;
        }
        tempFirst = null;
        return itemToReturn;
    }


    public int size(){
        return N;
    }


    public static void main(String[] args)  {
        StackMinAPI<Integer> mainStack = new StackMinAPI<>();
        mainStack.push(50);
        mainStack.push(30);
        mainStack.push(10);
        mainStack.push(20);
        mainStack.push(40);
        System.out.println("Size : " +mainStack.size());

        Integer min1 = mainStack.min();
        System.out.println("Min Item : " +min1);

        Integer min2 = mainStack.min();
        System.out.println("Min Item : " +min2);

        Integer min3 = mainStack.min();
        System.out.println("Min Item : " +min3);

        Integer min4 = mainStack.min();
        System.out.println("Min Item : " +min4);

        Integer min5 = mainStack.min();
        System.out.println("Min Item : " +min5);

    }

}
