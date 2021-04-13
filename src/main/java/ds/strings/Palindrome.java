package ds.strings;

/**
 * Created by Vijay Jammi on 06/04/2018.
 */

public class Palindrome {

    public int isPalindrome(String a) {
        //Remove all spaces and non alpha characters
        String ab =  a; //a.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        System.out.println(ab);

        for (int i=0; i<ab.length()/2+1; i++) {
            System.out.println(">: "+ab.charAt(i) + " <: " +ab.charAt((ab.length()-1)-i));
            if(ab.charAt(i) != ab.charAt((ab.length()-1)-i)) {
                return 0;
            }
        }
        return 1;
    }

    public boolean isPalindrome1(String str) {
        int n = str.length();
        for( int i = 0; i < n/2; i++ ) {
            if (str.charAt(i) != str.charAt(n - i - 1)) return false;
        }
        return true;
    }

    public boolean isPalindrome2(String string){
        int length = string.length();

        for (int i = 0; i < length/2; i++){
            if (string.charAt(i) != string.charAt((length-i)-1)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        Palindrome p = new Palindrome();
        //System.out.println(p.isPalindrome("abcjjcba"));
        System.out.println(p.isPalindrome1("abcjkcba"));
        System.out.println(p.isPalindrome2("abcjkcba"));
    }
}
