package ds.stack;

/**
 * Created by Vijay Jammi on 05/18/2018.
 */
public class Stack {

    private Node first;
    private int N = 0;

    class Node{
        String item;
        Node nextNode;
    }

    public void push(String item){
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

    public String pop(){
        String item = null;
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
        Stack stack = new Stack();

        // Push
        for (int i = 0; i < 10; i++){
            stack.push(i+ "");
        }
        stack.printContents();

        // Pop
        for (int i = 0; i < 10; i++){
            String item = stack.pop();
            stack.printContents();
            System.out.println("");
        }
   }
}
