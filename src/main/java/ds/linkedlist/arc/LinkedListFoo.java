package ds.linkedlist.arc;

public class LinkedListFoo {

    Node first;
    Node last;
    int N;

    private class Node{
        String item;
        Node next;
    }

    public void push(String item){

        if (N == 0){
            last = new Node();
            last.item = item;
            first = last;
            N++;
        }else{
            Node oldLast = last;
            last = new Node();
            last.item = item;
            oldLast.next=last;
            N++;
        }
    }


    public String popFront(){

        if (N == 0){
            return null;
        }
        String item = first.item;
        first = first.next;

return null;

    }


    public static void main (String args[]){
        LinkedListFoo linkedList = new LinkedListFoo();
        linkedList.push("10");
        linkedList.push("11");
        linkedList.push("12");

    }
}