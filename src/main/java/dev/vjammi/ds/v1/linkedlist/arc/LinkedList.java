package dev.vjammi.ds.v1.linkedlist.arc;

/**
 * Created by Vijay Jammi on 06/07/2018. GOLD
 */
public class LinkedList<Item> {

    private Node first, last;
    private int n;

    class Node {
        int key;
        String value;

        Node next;

        Node(int key, String value){
            this.key = key;
            this.value = value;
        }
    }

    public LinkedList(){
        this.first = null;
        this.last = null;
        n = 0;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    public int getN() {
        return n;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Node current = first;
        while (current !=null) {
            s.append(current.key + "-" +current.value);
            s.append(' ');
            current = current.next;
        }
        return s.toString();
    }

    public int size() {
        return n;
    }

    public void put(int key, String value) {
        System.out.print(" >> "+key);
        if (first == null){
            last = new Node(key, value);
            first = last;
            n++;
        }else{
            Node oldLast = last;
            last = new Node(key, value);
            oldLast.next = last;
            n++;
        }
    }

    public void put_refactored(int key, String value) {
        System.out.print(" >> "+key);

        Node oldLast = last;
        last = new Node(key, value);
        if (isEmpty()){
            first = last;
        }else{
            oldLast.next = last;
        }
        n++;
    }

    public void testGet(int key){
        Node listNode = get(key);
    }

    public Node get(int key) {
        Node current = first;

        while (current != null){
            if (key == current.key){
                System.out.println("Match Found, returning current: " + current.key +" " + current.value);
                return current;
            }
            System.out.println("Traversing :" + current.key +" " + current.value);
            current = current.next;
        }
        return null;
    }

    private Node get2(int key) {
        Node x = first;

        while (x != null){
            if (key == x.key){
                System.out.println("Traversing (Match Found, returning current) :" + x.key +" " + x.value);
                return x;
            }
            System.out.println("Traversing :" + x.key +" " + x.value);
            x = x.next;
        }
        return null;
    }

    private void traverseIteratively() {
        Node current = first;
        while (current != null){
            System.out.print(" >> " + current.key +"-" + current.value);
            current = current.next;
        }
    }

    public void testTraverseRecurssively(){
        traverseRecurssively(first);
    }

    public void traverseRecurssively(Node x){ // current
        // Identify the end of the list and return. Here I do not need to manipulate the last node.
        // See the contrast with reverseRecurssively() when you have to do something with the last node.
        if (x == null){
            System.out.println();
            return;
        }
        System.out.print("> [" +x.key +"-" +x.value +"] ");
        traverseRecurssively(x.next); // next
        System.out.print("< [" +x.key +"-" +x.value +"] ");
    }

    public void testReversing(){
        System.out.println();
        //reverseIteratively(first);

        // System.out.println("");
        // reverseIteratively2(first);
        //System.out.println("\n   first: " + this.first.key + " - last: " + this.last.key);

        //System.out.println("");
        //this.last = reverseRecurssively(first);
        //System.out.println("\n>>>>>>   first: " + this.first.key + " - last: " + this.last.key);

        /*
        System.out.println("");
        this.last = reverseRecurssively2(this.first);
        System.out.println("\n first: " + this.first.key + " - last: " + this.last.key);
        System.out.println("");
        reverseIteratively(first);
        this.last = reverseRecurssively2(this.first);
        System.out.println("\n first: " + this.first.key + " - last: " + this.last.key);
        System.out.println("");
        */
        reverseRecurssively3(this.first, this.first);
        System.out.println("\n   first: " + this.first.key + " - last: " + this.last.key);

        traverseIteratively();
        System.out.println("\n   first: " + this.first.key + " - last: " + this.last.key);

    }

    public void reverseIteratively(Node first){
        Node previous = null;
        Node current = first;
        Node next = null;

        // Since we are reversing the list, point current[first] to last.
        this.last = current;

        // cannot use while (children == null) - We would never get into the while then
        while (current != null){ // until we do not reach the end of the list, repeat

            // first things first - Secure the next node
            next = current.next; // Free up current.next, so that we could point it to the previous

            // reverse the pointer
            current.next = previous; // Now assign the reference of the previous to the current.next
            System.out.print(current.key +" << ");

            // when current.next is null[we have come to the end of the LinkedList1].
            // Now Point the first to this node, since we are reversing the list
            if (next == null){
                this.first = current;
            }

            // Move forward by shifting the pointers for previous and current
            previous = current;
            current = next;
        }

    }

    public void reverseIteratively2(Node first) {
        Node x = first;
        Node next = null;
        Node prev = null;

        this.last = first;
        while (x != null) {
            if (x.next == null) {
                this.first = x;
            }

            next = x.next;
            x.next = prev;
            System.out.print(" >>> x: " + x.key);

            prev = x;
            x = next;
        }
        System.out.println("\n   first: " + this.first.key + " - last: " + this.last.key);
    }

    public Node reverseRecurssively(Node current){

        // Identify the last node [current.next == null] and stop/break the recursion and return the last node [current].
        if (current.next == null){
            System.out.println("\n >> Before... current.key[current] : " + current.key + " current.children: "+current.next +" Breaking Loop - current.children == null, set current node to first: "+ current.key);
            this.first = current; // Setting the last to first
            return current; // Returning the last node. You know that you have the last node, when current.next == null
        }

        System.out.print(">> Before... Current: " + current.key);
        Node previous = reverseRecurssively(current.next);

        /*
        // While coming down the hill, Save current.next [returned node] to previous
                previous = current.next              // Save current.next [returned node] to previous
                           current <- previous.next  // Establish the reverse link
                           current.next = null       // Break the forward link
        // Cycle repeats until the second node[previous] points to the first node [current] and current becomes last
                previous = current.next              // Save current.next [returned node] to previous
                   current <- previous.next          // Establish the reverse link
                   current.next = null               // Break the forward link

        // Alternatively, get previous from current.next
                previous = current.next;  // get previous from current.next
                previous.next = current;  // current <- previous.next
                current.next = null;      // current.next -> null //Do not forget this...
        */

        if(current != null) {
            System.out.print("<< After... current[Current]: " + current.key +" Previous: " + previous.key);
            previous = current.next;  // Alternatively, get previous from current.next
            previous.next = current;  // current <- previous.next
            current.next = null;      // current.next -> null //Do not forget this...
        }

        return current;
    }

    public Node reverseRecurssively2(Node current){
        if (current.next == null){
            System.out.println(" >> Breaking Loop  - Before... current.key : " + current.key + " current.next "+current.next);
            this.first = current;
            return current;
        }

        System.out.println(">> Before... Current: " + current.key);
        reverseRecurssively2(current.next);

        Node previous = current.next;
        previous.next = current;
        System.out.println("<< current.key " +current.key + " Previous (new) " + previous.key);
        current.next = null;
        return current;
    }

    // Alternate for reverseRecurssively with void return type. Seems to work like a charm. Need to further test
    public void reverseRecurssively3(Node current, Node firstSaved){
        if (current.next == null){
            System.out.println(" >> Breaking Loop  - Before... current.key : " + current.key + " current.next "+current.next);
            this.first = current;
            return;
        }

        System.out.println(">> Before... Current: " + current.key);
        reverseRecurssively3(current.next, firstSaved);

        Node previous = current.next;
        previous.next = current;
        System.out.println("<< current.key " +current.key + " Previous (new) " + previous.key);
        current.next = null;
        if ( current == firstSaved){
            System.out.println("*** current.key " +current.key + " first.key " + firstSaved.key +" ***");
            this.last = firstSaved;
        }
    }

    public Node reverseRecurssively4(Node x){
        if (x.next !=  null) System.out.println(" x: "+x.key +" >> next: " +x.next.key);
        else System.out.println(" x: "+x.key +" >> next: " +null);
        if(x.next == null){
            this.first = x;
            return x;
        }

        Node prev = reverseRecurssively4(x.next); // prev = x.next for the current iteration of x
        //if (prev != null){ // do not need that also
        prev.next = x;
        x.next = null;
        System.out.println(" x[current]: "+x.key +" <-- previous: "+prev.key);
        //}
        return x;
    }

    public void reverse_practice() {
        Node prev = null;
        Node current = first;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;

            prev = current;
            current = next;
        }
        first = prev;
    }

    public void test(){

        put(1015, "Sam1015");
        put(1014, "Sam1014");
        put(1013, "Sam1013");
        put(1012, "Sam1012");
        put(1011, "Sam1011");
        put(1010, "Sam1010");
        put(1009, "Sam1009");

        Node node = get(1009);
        String nodeValue = node.value;
        String nodeValueToMatch = "Sam1009";
        if (nodeValue == nodeValueToMatch){
            System.out.println("Same-Sam1009");
        }

        System.out.println("\n list.toString() " + this);
        System.out.println("----------------");

        //list.traverseIteratively();
        //testTraverseRecurssively();
        System.out.println("----------------");

        testReversing();
        System.out.println("----------------");

        traverseIteratively();
        System.out.println("----------------");

    }

    public static void main(String[] args){
        LinkedList<String> listObject = new LinkedList<>();
        listObject.test();
    }

}
