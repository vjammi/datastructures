package ds.twopointers;

import ds.recurssion.ReverseString;

public class Reverse {

    public void reverseString(char[] s) {
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

    public void exch(char[] s, int i, int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void main(String[] args) {
        Reverse obj = new Reverse();
        char[] string = {'h','e','l','l','o'};
        obj.reverseString(string);
    }
}
