package ds.patterns.strings;

public class LongestPalindromicSubstring {

    /*
    * Input: "babad"
      Output: "bab" or "aba"
    * Input: "babcdefghgfedcad"
    *          ^      ^
    *         0123456789012345
      Output: "bab" or "aba"
    */
    public String longestPalindrome(String s) {
        String longestPalindrome = "";

        if (s == null){
            return "";
        }

        if (s.isEmpty() || s.length() == 1 ){
            return s;
        }

        if(s.length() == 2 && (s.charAt(0) == s.charAt(1))){
            longestPalindrome = s.substring(0,2);
            System.out.println(longestPalindrome);
            return longestPalindrome;
        }else if(s.length() == 2 && (s.charAt(0) != s.charAt(1))){
            longestPalindrome = String.valueOf(s.charAt(0));
            System.out.println(longestPalindrome);
            return longestPalindrome;
        }

        for (int i=0; i < s.length()-1; i++){

            if (s.charAt(i) == s.charAt(i+1) ){
                //System.out.println(">" +s.charAt(i-1) +" " +s.charAt(i) +" "+s.charAt(i+1) +" @ index " +i);
                int j= i;
                int k = i+1;
                while(j >= 0 && k < s.length() && (s.charAt(j) == s.charAt(k))){
                    if (longestPalindrome.length() < s.substring(j,k+1).length()) {
                        longestPalindrome = s.substring(j,k+1);
                        //System.out.println(s.substring(j,k+1));
                    }
                    j--; k++;
                }

            }else if(s.charAt(i) == s.charAt(i+1)){
                //System.out.println(">>" +s.charAt(i-1) +" " +s.charAt(i));
                longestPalindrome = s.substring(i,i+1);
                //System.out.println(s.substring(i-1,i+1));
            }

        }
        return longestPalindrome;
    }

    public static void main(String[] args) {

        LongestPalindromicSubstring lpsObject = new LongestPalindromicSubstring();
        String lps1 = lpsObject.longestPalindrome("babad");
        System.out.println("babad: "+lps1); // bab /  aba
        String lps2 = lpsObject.longestPalindrome("babcdefghgfedcad");
        System.out.println("babcdefghgfedcad: "+lps2); // cdefghgfedc
        String lps3 = lpsObject.longestPalindrome("cbbd");
        System.out.println("cbbd: "+lps3); // "bb"
        String lps4 = lpsObject.longestPalindrome("ac");
        System.out.println("ac: "+lps4); // ""
        String lps5 = lpsObject.longestPalindrome("bb");
        System.out.println(": "+lps5); // "bb"
        String lps6 = lpsObject.longestPalindrome("abb");
        System.out.println(": "+lps6); // ""
        String lps7 = lpsObject.longestPalindrome(null);
        System.out.println(": "+lps7); // null

    }

}
