package dev.vjammi.ds.v1.strings;

import java.util.HashMap;

public class WordPattern23 {

    public static void main(String[] args) {
/*
        String pattern = "abab";
        String str     = "redblueredblue";
*/

        String pattern  = "abcbc";
        String str      = "redbluegreenbluegreen";

        WordPattern23 wordPattern222 = new WordPattern23();
        boolean isItAMatch = wordPattern222.wordPattern(pattern, str);
        System.out.println(isItAMatch);
    }

    public boolean wordPattern(String pattern, String str) {
        if (pattern != null && str != null && pattern.length() == 0 && str.length() == 0) {
            return true;
        }
        if (pattern == null || pattern.length() == 0 || str == null || str.length() == 0) {
            return false;
        }
        String[] strArr = str.split(" ");
        if (pattern.length() != strArr.length) {
            return false;
        }

        HashMap<Character, String> map = new HashMap<Character, String>();
        HashMap<String, Character> mapStr = new HashMap<String, Character>();

        for (int i = 0; i < strArr.length; i++){
            if (!map.containsKey(pattern.charAt(i))) {
                map.put(pattern.charAt(i), strArr[i]);
            } else {
                if (!map.get(pattern.charAt(i)).equals(strArr[i])) {
                    return false;
                }
            }
            if (!mapStr.containsKey(strArr[i])) {
                mapStr.put(strArr[i], pattern.charAt(i));
            } else {
                if (mapStr.get(strArr[i]) != pattern.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

}
