package ds.recursion;

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

    // Solution 1
    public void reverse1(String str, int i){
        if (i >= str.length()) {
            System.out.println("\n");
            return;
        }
        System.out.print(str.charAt(i) +" > ");
        reverse1(str, i+1);
        System.out.print(str.charAt(i) +" > ");
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

        String str1 = "hello";
        obj.reverse1(str1, 0);

        System.out.println("");

        char[] str2 = {'h','e','l','l','o'};
        System.out.println(str2);
        obj.reverse2(str2);
        System.out.println(str2);

        obj.reverseString(str2);
    }
}
