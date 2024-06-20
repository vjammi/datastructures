package dev.vjammi.ds.v2.dev.usage.operations;

/**
 * A mutable sequence of characters. This class provides an API compatible with StringBuffer, but with no guarantee of synchronization.
 * This class is designed for use as a drop-in replacement for StringBuffer in places where the string buffer was being used by a
 * single thread (as is generally the case). Where possible, it is recommended that this class be used in preference to StringBuffer
 * as it will be faster under most implementations.
 *
 * The principal operations on a StringBuilder are the append and insert methods, which are overloaded so as to accept data of any type.
 * Each effectively converts a given datum to a string and then appends or inserts the characters of that string to the string builder.
 * The append method always adds these characters at the end of the builder; the insert method adds the characters at a specified point.
 * For example, if z refers to a string builder object whose current contents are "start", then the method call z.append("le") would
 * cause the string builder to contain "startle", whereas z.insert(4, "le") would alter the string builder to contain "starlet".
 *
 * In general, if sb refers to an instance of a StringBuilder, then
 *  sb.append(x) has the same effect as sb.insert(sb.length(), x).
 *
 * Every string builder has a capacity. As long as the length of the character sequence contained in the string builder does not exceed the capacity,
 * it is not necessary to allocate a new internal buffer. If the internal buffer overflows, it is automatically made larger.
 *
 * Instances of StringBuilder are not safe for use by multiple threads. If such synchronization is required then it is recommended that StringBuffer be used.
 * */

public class StringBuilderOperations {

    public void operations(String input){
        traversal(input, 0);
        System.out.println("result " + result);

        reverse1(input, 0);
        System.out.println("result " + result1);

        reverse2(input, 0);
        System.out.println("result " + result2);
    }

    private final StringBuilder result1 = new StringBuilder();
    public void reverse1(String input, int i){
        if (i == input.length())
            return;
        result1.insert(0,input.charAt(i));     // Insert/Prefix at offset 0
        reverse1(input, i+1);

    }

    private final StringBuilder result2 = new StringBuilder();
    public void reverse2(String input, int i){
        if (i == input.length())
            return;
        reverse2(input, i+1);
        result2.append(input.charAt(i));         // Append/Suffix at offset i/n
    }

    private final StringBuilder result = new StringBuilder();
    public void traversal(String input, int i){
        if (i == input.length()-1) {
            result.append(input.charAt(i));
            return;
        }
        result.append(input.charAt(i));
        //result.insert(0, input.charAt(i)); // offset, char
        traversal(input, i+1);
        //result.deleteCharAt(result.length()-1);
    }

    public static void main(String[] args) {
        StringBuilderOperations obj = new StringBuilderOperations();
        obj.operations("hello");
    }

}
