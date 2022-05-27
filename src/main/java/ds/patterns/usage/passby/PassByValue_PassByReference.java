package ds.patterns.usage.passby;

import java.util.*;
import java.util.function.Consumer;

/**
    Passing by Value and Pass by Reference

      Java always is Pass by Value
      C++ is Pass by Reference

      Pass by value:
        makes a copy in memory of the parameter’s value, or a copy of the contents of the parameter.
      Pass by reference:
        a copy of the address (or reference) to the parameter is stored rather than the value itself. In this case,
        modifying the value of the parameter will change the value. Here is an example in C++,
        which is very similar to Java syntax (cout is C++’s way of printing to the terminal):

      So, why does it appear that Java is (mostly) pass-by-reference, but books say that Java is always pass-by-value?
      Passing in the Cat myCat into adoptCat() and changing the name there using the parameter c did, in fact,
      change the name of the cat! This intuitively looks like the pass-by-reference example above:
      making a change to the passed-in object affects that object’s values in memory.
      We can think of Java intuitively as pass-by-reference for all objects. This is why we professors called Java pass-by-reference.

      That is, for a reference variable, the value on the stack is the address on the heap at which the real object resides.
      When any variable is passed to a method in Java, the value of the variable on the stack is copied into a new variable
      inside the new method.

      https://www.cs.virginia.edu/~jh2jf/courses/cs2110/java-pass-by-value.html#:~:text=Java%20is%20officially%20always%20pass,the%20reference%20for%20reference%20types.
      https://www.andrew.cmu.edu/course/15-121/lectures/Class%20Design/classes.html
 */
public class PassByValue_PassByReference {

    /** Pass by Value */
    public void testPassingPrimitiveTypes() {
        int y = 5;
        System.out.println(y); // prints "5"

        myMethod(y);
        System.out.println(y); // prints "5"
    }
    public void myMethod(int x) {
        // myMethod has a copy of x, so it doesn't
        // overwrite the value of variable y used
        // in main() that called myMethod
        x = 4;
    }

    public void testPassingString() {
        String s = "change me";
        changeIt(s);
        System.out.println(s);
    }

    public void changeIt(String x) {
        x = "changed";
    }

    private void testPassingByReferenceInCPP() {
        /**
         // Pass by Reference in C++
         int testPassingByReferenceInCPP() {
             int y = 5;
             cout << y; // prints "5"

             myMethod(y);
             cout << y; // prints "4"
         }

         // & below tells C++ to specifically "pass x by reference"
         int myMethod(int &x) {
             // myMethod has the address of x, or the
             // reference to x (rather than the value),
             // so it overwrites the value of variable y
             // used in main() that called myMethod
             x = 4;
         }
         **/
    }
    public void adoptCat(Cat c, String newName) {
        c.setName(newName);
    }

    public void testPassingReferenceTypes() {
        Cat myCat = new Cat();
        myCat.setName("Charlotte");
        System.out.println(myCat.getName()); // prints "Charlotte"

        adoptCat(myCat, "Lydia");
        System.out.println(myCat.getName()); // prints "Lydia"
    }


    private void testPassingArraysAsReferenceTypes() {
        int[] arr = new int[3];
        arr[0] = 1;
        addToArray(arr);
        arr[2] = 3;
        Arrays.stream(arr).forEach(System.out::println);
    }
    private void addToArray(int[] arr) {
        arr[1] = 2;
    }

    private void testPassingMapsAsReferenceTypes() {
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        addToMap(map);
        map.put("Three", 33);
        map.entrySet().forEach(System.out::println);
    }
    private void addToMap(Map<String, Integer> map) {
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);
    }

    private void testPassingListsAsReferenceTypes() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        appendToList(list);
        list.add(3);
        list.add(4);
        list.forEach(System.out::println);
    }

    private void appendToList(List<Integer> list) {
        list.add(2);
    }

    private void testPassingMyListsAsReferenceTypes() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        appendToList(list);
        list.add(3);
        list.add(4);
        list.forEach(System.out::println);
        System.out.println("");
    }

    private void appendToList(MyList list) {
        list.add(2);
    }

    private class MyList<Item> implements Iterable<Item>{
        ListNode head;
        ListNode tail;

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
            // n++;
            //assert check();
        }


        public Iterator<Item> iterator() {
            return new MyListIterator();
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

        private class MyListIterator implements Iterator<Item> {
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
    }

    private class Cat {
        private String name;

        // Setter
        public void setName(String name) {
            this.name = name;
        }

        // Getter
        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        PassByValue_PassByReference obj = new PassByValue_PassByReference();

        obj.testPassingPrimitiveTypes();
        obj.testPassingString();
        obj.testPassingReferenceTypes();
        obj.testPassingByReferenceInCPP();

        obj.testPassingArraysAsReferenceTypes();
        obj.testPassingMapsAsReferenceTypes();
        obj.testPassingListsAsReferenceTypes();
        obj.testPassingMyListsAsReferenceTypes();
    }


}
