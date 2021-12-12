package ds.strings;

public class ValidPalindrome {
    /**
        125. Valid Palindrome
        A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
        Given a string s, return true if it is a palindrome, or false otherwise.

         Time complexity : O(n), If n is the length of the string ,we traverse over each character at-most once,
                           until the two pointers meet in the middle, or when we break and return early.
         Space complexity : O(1). No extra space required.

        // 0123456
        // racecar
        // ^     ^

        // 01234
        // madam
        // ^
        //     ^

        // 01234567
        // m;ad:a:m
        // ^      ^
    */
    // Note: Only alphanumeric characters are considered. So we can filter out other characters
    public boolean isPalindrome(String s) {

        int i = 0; int j = s.length()-1;
        while(i<=j){
            while( i<j && !Character.isLetterOrDigit(s.charAt(i)))
                i++;

            while( i<j && !Character.isLetterOrDigit(s.charAt(j)))
                j--;

            if ( Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)) )
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
