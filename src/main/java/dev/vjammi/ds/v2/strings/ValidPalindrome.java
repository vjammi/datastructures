package dev.vjammi.ds.v2.strings;

public class ValidPalindrome {
    /**
         125. Valid Palindrome

         A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
         Given a string s, return true if it is a palindrome, or false otherwise.

         Non-alphanumeric characters comprise of all the characters except alphabets and numbers.
         It can be punctuation characters like exclamation mark(!), at symbol(@), commas(, ), question mark(?), colon(:),
         dash(-) etc and special characters like dollar sign($), equal symbol(=), plus sign(+), apostrophes(')

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

         Note: Only alphanumeric characters are considered. We can skip over Non-alphanumeric characters
    */
    public boolean isPalindrome(String s) {

        int i = 0; int j = s.length()-1;

        // In a case of odd num of chars, i<j will skip checking the odd middle letter. e.g. racar
        // In a case of odd num of chars, for i<=j, both i and j will point to the same index. e.g. racar
        while( i<=j ){

            // You want to only check when i<j. When i<=j when i=j you do not want i and j to cross each other
            while( i<j && !Character.isLetter(s.charAt(i)) && !Character.isDigit(s.charAt(i)) )
                i++;

            // You want to only check when i<j. When i<=j when i=j you do not want i and j to cross each other
            while( i<j  && !Character.isLetter(s.charAt(j)) && !Character.isDigit(s.charAt(j)) )
                j--;

            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;

            i++; j--;
        }

        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome obj = new ValidPalindrome();
        obj.isPalindrome("A man, a plan, a canal: Panama");
        obj.isPalindrome("race a car");
        obj.isPalindrome("racar");
    }

}
