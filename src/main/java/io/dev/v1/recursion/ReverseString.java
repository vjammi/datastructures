package io.dev.v1.recursion;

public class ReverseString {


    public void reverseString(char[] s) {
        reverseStringRecurssive(0, s.length - 1, s);
        reverseStringIterative(s);
    }

    private void reverseStringRecurssive(int start, int end, char [] s) {
        if (start >= end) {
            return;
        }
        // swap between the first and the last elements.
        char tmp = s[start];
        s[start] = s[end];
        s[end] = tmp;

        reverseStringRecurssive(start + 1, end - 1, s);
    }

    public void reverseStringIterative(char[] s) {
        System.out.println(s);

        int i = 0;
        int j = s.length-1;
        while(i<=j){
            exch(s, i, j);
            i++;
            j--;
        }
        System.out.println(s);
    }

    private final StringBuilder output2 = new StringBuilder();
    public void reverseStringRecursive(String input){
        //reverseString1(input, 0);
        //System.out.println("");

        StringBuilder output = new StringBuilder();
        reverseString2(input, 0, output);
        System.out.println("Output1 " + output);
        System.out.println("Output2 " + output2);
    }

    // Solution 1
    public void reverseString1(String input, int i){
        if (i == input.length())
            return;

        System.out.print(input.charAt(i) +" > ");
        reverseString1(input, i+1);
        System.out.print(input.charAt(i) +" > ");
    }

    public void reverseString2(String input, int i, StringBuilder output){
        if (i == input.length())
            return;

        output.insert(0,input.charAt(i));  // Insert/Prefix at offset 0
        output2.append(input.charAt(i));         // Append/Suffix at offset i/n
        reverseString2(input, i+1, output);
        output2.append(input.charAt(i));         // Append/Suffix at offset i/n
        output.setCharAt(i, input.charAt(i));
    }

    // Solution 2
    private void reverse2(char[] string) {
        reverse2(string, 0, string.length-1);
    }

    public void reverse2(char[] s, int i, int j){
        if (i>j)
            return;

        exch(s, i, j);
        reverse2(s, i+1, j-1);
    }

    private void exch(char[] s, int i, int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void main(String[] args) {
        ReverseString obj = new ReverseString();

        String input1 = "hello";
        obj.reverseStringRecursive(input1);

        System.out.println("----");

        char[] input2 = {'h','e','l','l','o'};
        System.out.println(input2);
        obj.reverse2(input2);
        System.out.println(input2);
        obj.reverseString(input2);

    }


}
