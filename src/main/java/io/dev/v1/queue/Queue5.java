package io.dev.v1.queue;
/**
 * Created by Vijay Jammi on 05/14/2018.
 */
public class Queue5 {

    private int n;
    private Node head; // first - Remove from the head
    private Node tail; // last - Insert from the tail

    private static class Node {
        private final int item;
        private Node next;

        public Node(int item) {
            this.item = item;
        }
    }

    public void add(int item) { // add
        Node node = new Node(item);
        if (tail != null){
            tail.next = node;
        }

        if (head == null ){
            head = node;
        }
        tail = node;
        n++;
    }

    public void remove() {   // remove
    }

    public boolean isEmpty() {

      if (head == null) {
            System.out.format("size: (%d), isEmpty: (%b) peak: (%d) \n", size(), head == null, 0);
      }else{
            System.out.format("size: (%d), isEmpty: (%b) , peak: (%d) \n", size(), head == null, peek());
      }

      return head == null;
    }

    public int size(){
        return n;
    }

    public int peek() {
        return head.item;
/*
        if (head != null){
            return head.item;
        } else{
            return 0;
        }
*/
    }


    public static void main(String[] args) {
        Queue5 queue = new Queue5();
        for (int i=0; i<10; i++){
            queue.add(i);
            //queue.isEmpty();
        }


    }
}

