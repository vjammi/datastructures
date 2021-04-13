package scratchpad.exceptions;

import java.util.Arrays;

public class ExceptionHandlingSamples {

    public static void main(String args[]){
        String[] strArray = {"ONE", "0.0", "TWO", "", "THREE", "FOUR", "FIVE"};
        new ExceptionHandlingSamples().testSomething(strArray);
    }

    private void testSomething(String[] strArray) {
        try {
            doSomething(strArray);
        }catch (IllegalArgumentException e){
            println("Handling IllegalArgumentException Gracefully [" +e.getMessage() +"]");
        }catch (MyCustomException e){
            println("Handling MyCustomException Gracefully [" +e.getMessage() +"]");
        }
    }

    public void doSomething(String[] strArray) throws IllegalArgumentException, MyCustomException {

        for (int i=0; i<strArray.length; i++){
            String str = strArray[i];
            if (str == ""){
                print(str);
                throw new IllegalArgumentException("Empty String...");
            }
            if (str == "-1"){
                print(str);
                throw new IllegalArgumentException("Illegal String. Require characters...");
            }
            if (str == "0.0"){
                print(str);
                throw new MyCustomException("Floats not allowed...");
            }
        }
        println("");
        Arrays.sort(strArray);
        for (int i=0; i<strArray.length; i++){
            print(strArray[i]);
        }
    }

    private void println(String str) {
        System.out.println(str);
    }

    private void print(String str) {
        System.out.print(str +" ");
    }

}
