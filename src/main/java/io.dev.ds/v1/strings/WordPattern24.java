package dev.vjammi.ds.v1.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPattern24 {

    public static void main(String[] args) {
        String pattern  = "abcbc";
        String str      = "redbluegreenbluegreen";
        /*
        String pattern = "abab";
        String str     = "redblueredblue";
        */
        WordPattern24 wp = new WordPattern24();
        System.out.println(wp.wordPatternMatch(pattern, str));
    }

    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        boolean result = wordPatternMatch(pattern, str, 0, 0, map, set);
        return result;
    }

    public boolean wordPatternMatch(String pattern, String str, int pos1, int pos2, Map<Character, String> map, Set<String> set) {
        if (pos1 == pattern.length()) {
            return pos2 == str.length();
        }

        char ch = pattern.charAt(pos1);
        String val = map.get(ch);
        if (val != null) {
            return pos2 + val.length() <= str.length() && val.equals(str.substring(pos2, pos2 + val.length())) && wordPatternMatch(pattern, str, pos1 + 1, pos2 + val.length(), map, set);
        } else {
            for (int i = pos2; i < str.length() - (pattern.length() - pos1 - 1); i++) {
                String newStr = str.substring(pos2, i + 1);
                if (set.contains(newStr)) {
                    continue;
                }
                set.add(newStr);
                map.put(ch, newStr);
                if (wordPatternMatch(pattern, str, pos1 + 1, i + 1, map, set)) {
                    return true;
                }
                set.remove(newStr);
            }
            map.remove(ch);
        }
        return false;
    }

}
