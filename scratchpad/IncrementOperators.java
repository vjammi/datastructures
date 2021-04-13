package scratchpad;

/**
 * Created by Vijay Jammi on 06/18/2018.
 */
public class IncrementOperators {

    public static void main(String[] args){
        int i = 3;

        int a = ++i; // a = 3, i = 4    // Increment and Assign
        System.out.println(a +" - " +i);

        int b = a++; // b = 4, a = 4    // Assign and Increment
        System.out.println(b +" - " +a);

    }
}
