package ds.strings;


public class LongestPalindromicSubstringSolution {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        String longestPalindrome = "";

        if(s.length() == 1){
            longestPalindrome = s ;
            return longestPalindrome;
        }

        String palindromeWithoutCenter = getPalindromeWithoutCenter(s);
        String palindromeWithCenter = "";
        if (s.length() > 2) {
            palindromeWithCenter = getPalindromeWithCenter(s);
        }

        longestPalindrome = palindromeWithoutCenter.length() > palindromeWithCenter.length() ? palindromeWithoutCenter : palindromeWithCenter;

        return longestPalindrome;
    }

    private String getPalindromeWithCenter(String s) {
        String longestPalindrome = "";
        for (int i=1; i < s.length()-1; i++){

            if (s.charAt(i-1) == s.charAt(i+1) ){
                //System.out.println(">" +s.charAt(i-1) +" " +s.charAt(i) +" "+s.charAt(i+1) +" @ index " +i);
                int j= i-1;
                int k = i+1;
                while(j >= 0 && k < s.length() && (s.charAt(j) == s.charAt(k))){
                    if (longestPalindrome.length() < s.substring(j,k+1).length()) {
                        longestPalindrome = s.substring(j,k+1);
                        //System.out.println(s.substring(j,k+1));
                    }
                    j--; k++;
                }
            }

        }
        return longestPalindrome;
    }

    private String getPalindromeWithoutCenter(String s) {
        String longestPalindrome = "";
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.substring(0,i+1));
            if (i+1 < s.length() ) {
                for (int j = i + 1; j >= i; j--) {
                    if (s.charAt(j) == s.charAt(i)) {
                        System.out.println(" > " + s.substring(i, j + 1));
                        if (s.substring(i, j + 1).length() > longestPalindrome.length()) {
                            longestPalindrome = s.substring(i, j + 1);
                            System.out.println(" >>> " + longestPalindrome);
                            j--;
                        }
                    }
                }
            }

        }
        return longestPalindrome;
    }

    private String getPalindromeWithoutCenter2(String s) {
        String longestPalindrome = "";
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.substring(0,i+1));

            int k = i;
            if (s.length() > k+1) {
                for (int j = k+1; j >= k; j--) {
                    if (s.charAt(j) == s.charAt(i)) {
                        System.out.println(" > " + s.substring(i, j + 1));
                        if (s.substring(i, j + 1).length() > longestPalindrome.length()) {
                            longestPalindrome = s.substring(i, j + 1);
                            System.out.println(" >> " + longestPalindrome);
                        }
                        j--;
                        k++;
                    }else{
                        System.out.println(" > breaking");
                        break;
                    }
                }
            }

        }
        return longestPalindrome;
    }

    /**
     Approach 4: Expand Around Center
     In fact, we could solve it in O(n^2)O(n2) time using only constant space.
     We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center,
     and there are only 2n - 12n−1 such centers.
     You might be asking why there are 2n - 12n−1 but not nn centers? The reason is the center of a palindrome can be
     in between two letters. Such palindromes have even number of letters (such as "abba") and its center are
     between the two 'b's.
     */
    public String longestPalindrome_around_center(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {

        LongestPalindromicSubstringSolution lpsObject = new LongestPalindromicSubstringSolution();

        String lps10 = lpsObject.getPalindromeWithoutCenter2("aaaa");
        System.out.println("aaaa: "+lps10);

        System.exit(0);

        String lps9 = lpsObject.longestPalindrome("aaaa");
        System.out.println("aaaa: "+lps9);

        String lps5 = lpsObject.longestPalindrome("bb");
        System.out.println("bb: "+lps5); // "bb"

        String lps1 = lpsObject.longestPalindrome("babad");
        System.out.println("babad: "+lps1); // bab /  aba
        String lps2 = lpsObject.longestPalindrome("babcdefghgfedcad");
        System.out.println("babcdefghgfedcad: "+lps2); // cdefghgfedc
        String lps3 = lpsObject.longestPalindrome("cbbd");
        System.out.println("cbbd: "+lps3);
        String lps4 = lpsObject.longestPalindrome("ac");
        System.out.println("ac: "+lps4);
        String lps6 = lpsObject.longestPalindrome("abb");
        System.out.println("abb: "+lps6);
        String lps7 = lpsObject.longestPalindrome(null);
        System.out.println("null: "+lps7);
        String lps8 = lpsObject.longestPalindrome("a");
        System.out.println("a: "+lps8);


    }

}
