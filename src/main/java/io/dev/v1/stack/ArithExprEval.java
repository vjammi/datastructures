package io.dev.v1.stack;

public class ArithExprEval<Item> {

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
        /*
        ArithExprEval<Integer> stack = new ArithExprEval<>();
        stack.push(100);
        stack.push(200);
        stack.push(300);
        stack.push(400);
        System.out.println("Size : " +stack.size());
        Integer item1 = (Integer) stack.pop();
        Integer item2 = (Integer) stack.pop();
        stack.push(1000);
        stack.push(2000);
        stack.push(3000);
        stack.push(4000);
        System.out.println("Size : " +stack.size());
        Integer item3 = (Integer) stack.pop();
        Integer item4 = (Integer) stack.pop();
        Integer item5 = (Integer) stack.pop();
        System.out.println("Size : " +stack.size());
        System.out.println("Items: " +item1 +" "+item2);
        */

        /*
        Char 40 (        Char 50 2        Char 43 +        Char 53 5
        Char 41 )        Char 42 *        Char 40 (        Char 51 3
        Char 43 +        Char 50 2        Char 41 )        Char 41 )
        0-9 48 - 57
        */

        //ArithExprEval<String> val = new ArithExprEval<>();
        //ArithExprEval<Double> opr = new ArithExprEval<>();
        ArithExprEval<Integer> val = new ArithExprEval<>();
        ArithExprEval<Integer> opr = new ArithExprEval<>();

        String expression = "((2+5)*(3+2))";
        for (int i = 0; i< expression.length(); i++){
            int c = expression.charAt(i);
            System.out.println("Char " +c +" "+ expression.charAt(i) +" " +val.size() + " " +opr.size());
            if (c == 40){
                // Do Nothing...
            }else if (c >= 48 && c <= 57) {
                val.push(Integer.parseInt("" +expression.charAt(i)));
            }else if (c == 43 || c == 42) {
                opr.push(Integer.parseInt("" +c));
            }else if (c == 41) {
                Integer val1 = val.pop();
                Integer val2 = val.pop();
                Integer ops1 = opr.pop();

                Integer result = 0;
                if (ops1.equals(43)){
                    result = val1 + val2;
                }else if(ops1.equals(42)){
                    result = val1 * val2;
                }
                System.out.println("Result: " +result);
                val.push(result);
            }

        }

    }




}
