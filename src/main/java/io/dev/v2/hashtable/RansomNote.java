package io.dev.v2.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 *  383. Ransom Note
 *  Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 *  Each letter in magazine can only be used once in ransomNote
 *      ransom note         "leetcode is cool"
 *      magazine            "close call as fools take sides"
 **/
public class RansomNote {

    /**
     * Time: If (m > n)
     *          O(m)
     *      else
     *          O(m for iterating through the magazine) + O(1 for inserts into map) + O(n for iterating through the note)
     *
     *      Creating a HashMap of counts for the magazine is O(m),
     *      as each insertion/count update is O(1)
     *      and is done for each of the m characters
     * Space: O(1)
     *      The map will never contain more than 26 keys/chars, so we could say it's of O(1) constant space complexity.
     *
     * */
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> magazineMap = new HashMap<>();
        for (int i=0; i<magazine.length(); i++){
            char ch = magazine.charAt(i);
            if (magazineMap.containsKey(ch))
                magazineMap.put(ch, magazineMap.get(ch)+1);
            else
                magazineMap.put(ch, 1);
        }

        for (int i=0; i<ransomNote.length(); i++){
            char ch = ransomNote.charAt(i);
            if (magazineMap.containsKey(ch)){
                magazineMap.put(ch, magazineMap.get(ch)-1);
                if (magazineMap.get(ch) == 0)
                    magazineMap.remove(ch);
            }else{
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new RansomNote().canConstruct("leetcode is cool", "close call as fools take sides"));
    }
}
