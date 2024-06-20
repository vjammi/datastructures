package dev.vjammi.ds.v1.stack;

/**
 * Created by Vijay Jammi on 05/19/2018.
 */
public class StackArrayImpl {

    private String[] a;
    private int N = 0;

    public StackArrayImpl(int capacity){
        a = new String[capacity];
    }

    public void push(String item){
        if ( N == a.length ){ // Array is full, Time to resize
            resize(2 * a.length);
        }
        a[N] = item; // or a[N++] = item;
        N++;
        System.out.print(item + "(counter="+N +"), ");
    }

    private void resize(int newCapacity) {
        String[] copy = new String[newCapacity];
        for(int j = 0; j < a.length; j++){
            copy[j] = a[j];
        }
        System.out.print("\n Resizing a from " +N +" to " +copy.length);
        a = copy;
        System.out.println(" Resized a size " + a.length);
    }

    public String pop(){
        String item = a[N-1]; // or String item = a[--N];
        a[N-1] = null;
        N--;
        System.out.print(item + "(counter="+N +"), ");

        if (N > 0 && N < a.length / 4){
            System.out.print("\n Before Resizing a from " +N +" to " + a.length/2);
            resize(a.length/2);
            System.out.print("\n After Resizing a from " +N );
        }
        return item;
    }

    public void printContents(){
        for (int i = 0; i < a.length; i++ ){
            System.out.print(a[i] + "(counter="+N +"), ");
        }
        System.out.println(" ");
    }

    public static void main(String[] args){

        int capacity = 10;
        StackArrayImpl stackArrayImpl = new StackArrayImpl(capacity);

        // Push
        for (int i = 0; i < 100; i++){
            stackArrayImpl.push(i+ "");
        }
        //stackArrayImpl.printContents();

        System.out.println();

        // Pop
        for (int i = 0; i < 80; i++){
            String item = stackArrayImpl.pop();
        }
        System.out.println();

        stackArrayImpl.printContents();
    }

    private void doSomething(){

        for (int i=0; i<5; /*not incrementing here*/ )
            System.out.print(i++);

        System.out.println();

        for (int j=0; j<5; /*not incrementing here*/ )
            System.out.print(++j);
    }

}
