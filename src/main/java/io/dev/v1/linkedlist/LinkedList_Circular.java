package io.dev.v1.linkedlist;

/**
 * Created by Vijay Jammi on 06/07/2018.
 */
// cicular List with 1 (Last) and 2 (first and Last) instance variables
public class LinkedList_Circular {
    //TrieNode first, last;
    Node last;
    int N = 0;

    class Node{
        int key;
        String value;
        Node next;

        Node(int key, String value){
            this.key = key;
            this.value = value;
        }
    }

    public void testPut(int key, String value){
        put(key, value);
    }

    private void put(int key, String value) {
        //if (first == null){
        if (N == 0){
            last = new Node(key, value);
            //first = last;
            last.next = last;
            N++;
        }else{
            Node oldLast = last;
            last = new Node(key, value);
            //oldLast.children = last;
            //TrieNode first = oldLast.children;
            last.next = oldLast.next;
            oldLast.next = last;
            N++;
        }
    }

    public void testGet(int key){
        Node node = get(key);
    }

    private Node get(int key) {
        //TrieNode current = first;
        Node current = last.next;
        Node next = null;
        int i = 0;

        while (i < N){
            if (key == current.key){
                System.out.println("Traversing (Match Found, returning current) :" + current.key +" " + current.value);
                return current;
            }else{
                System.out.println("Traversing :" + current.key +" " + current.value);
            }
            current = current.next;
            i++;
        }
        return null;
    }

    private void traverseList() {
        //TrieNode current = first;
        Node current = last.next;
        Node next = null;
        int i = 0;

        while (current != null && i < N){
            System.out.println("Traversing :" +i + " "+ current.key +" " + current.value);
            current = current.next;
            i++;
        }
    }

    public static void main (String[] args){
        LinkedList_Circular list = new LinkedList_Circular();

        System.out.println("----------------");

        System.out.println("1015");
        list.testPut(1015, "Sam1015");

        System.out.println("1013");
        list.testPut(1013, "Sam1013");

        System.out.println("1014");
        list.testPut(1014, "Sam1014");

        System.out.println("1012");
        list.testPut(1012, "Sam1012");

        System.out.println("1017");
        list.testPut(1017, "Sam1017");

        System.out.println("1016");
        list.testPut(1016, "Sam1016");

        System.out.println("1018");
        list.testPut(1018, "Sam1018");

        System.out.println("----------------");

        list.testGet(1017);
        System.out.println("----------------");

        list.traverseList();
        System.out.println("----------------");


    }


}
