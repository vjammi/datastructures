package ds.strings;

public class ValidPalindrome {
    //    0123456789
    //    race a car
    // i  ^
    // j           ^

    // Time complexity : O(n)O(n), in length nn of the string. We traverse over each character at-most once,
    //                   until the two pointers meet in the middle, or when we break and return early.
    // Space complexity : O(1)O(1). No extra space required, at all.
    public boolean isPalindrome(String s) {
        int i=0; int j=s.length()-1;
        while(j>=0 && i<=s.length()-1 && i<j){ // *** i<j

            while(i<j && i<=s.length()-1 && !Character.isLetterOrDigit(s.charAt(i)) )
                i++;
            while(i<j && j>=0 && !Character.isLetterOrDigit(s.charAt(j)) )
                j--;

            if ( Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))  )
                return false;

            i++; j--;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome obj = new ValidPalindrome();
        obj.isPalindrome("A man, a plan, a canal: Panama");
        obj.isPalindrome("race a car");
    }

}
