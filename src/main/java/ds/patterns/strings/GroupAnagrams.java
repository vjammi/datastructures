package ds.patterns.strings;

import java.util.*;
/**
    49. Group Anagrams
    Given an array of strings strs, group the anagrams together. You can return the answer in any order.
    An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

    Input: strs = ["eat","tea","tan","ate","nat","bat"]
    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

    Input: strs = [""]
    Output: [[""]]

    Input: strs = ["a"]
    Output: [["a"]]

*/
public class GroupAnagrams {

    public List<List<String>> groupAnagrams_Solution2(String[] strs) {

        List<List<String>> result = new ArrayList<>();

        if (strs.length == 0)
            return result;

        Map<String, List<String>> map = new HashMap();
        for (int i=0; i<strs.length; i++){

            String anagram = strs[i];
            int[] charSet = new int[26];
            for(int j=0; j<anagram.length(); j++){
                int asciiValOfChar = (int) anagram.charAt(j);
                charSet[asciiValOfChar-97] = charSet[asciiValOfChar-97] + 1;
            }
            // TODO: Use StringBuilder
            StringBuffer buffer = new StringBuffer();
            for (int k=0; k<charSet.length; k++){
                // Adding a delimiter # to differentiate between [1#11...] vs [11#1...]
                buffer.append(charSet[k]+"#");   // 1#0#0#0#1#0#0#0#0#0#0#0#0#0#0#0#0#0#0#1#0#0#0#0#0#0# eat
                //buffer.append(charSet[k]+'#'); // 3635353536353535353535353535353535353536353535353535 eat
            }
            String key = buffer.toString();

            if (map.containsKey(key)){
                map.get(key).add(anagram);
                System.out.println(key.toString() +"       "+anagram);
            }else{
                List<String> list = new ArrayList<>();
                list.add(anagram);
                map.put(key, list);
                System.out.println(key.toString() +" "+anagram);
            }

        }

        for (Map.Entry<String, List<String>> entry: map.entrySet()){
            String key = entry.getKey();
            List<String> value = entry.getValue();
            result.add(value);
        }

        return result;
    }

    public List<List<String>> groupAnagrams_Solution1(String[] strs) {
        List<List<String>> result = new ArrayList<>();

        if (strs.length == 0)
            return result;

        Map<String, List<String>> map = new HashMap();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            String sortedStr = sort(str);

            if (map.containsKey(sortedStr)) {
                map.get(sortedStr).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sortedStr, list);
            }
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            result.add(value);
        }

        return result;
    }

    private String sort(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }


    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new GroupAnagrams().groupAnagrams_Solution1(strs));
        System.out.println(new GroupAnagrams().groupAnagrams_Solution2(strs));
    }

}
