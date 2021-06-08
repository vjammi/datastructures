package ds.recurssion;

public class ReverseString {

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
    }
}
