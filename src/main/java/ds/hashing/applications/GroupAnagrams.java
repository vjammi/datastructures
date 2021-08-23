package ds.hashing.applications;

import java.util.*;

/**
     49. Group Anagrams
     Given an array of strings strs, group the anagrams together. You can return the answer in any order.
     An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

     Example 1:
     Input: strs = ["eat","tea","tan","ate","nat","bat"]
     Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

     Example 2:
     Input: strs = [""]
     Output: [[""]]

     Example 3:
     Input: strs = ["a"]
     Output: [["a"]]
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> lists =  new ArrayList<>();
        Map<Integer, List<String>> mapOfStrs = new HashMap<>();
        for(int i=0;i<strs.length;i++){
            int hashCode = getMap(strs[i]).hashCode();
            if (!mapOfStrs.containsKey(hashCode)){
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                mapOfStrs.put(hashCode, list);
            }else{
                List<String> list = mapOfStrs.get(hashCode);
                list.add(strs[i]);
                mapOfStrs.put(hashCode, list);
            }
        }
        for(Map.Entry<Integer, List<String>> entries : mapOfStrs.entrySet()){
            lists.add(entries.getValue());
        }

        return lists;
    }

    public int hashCode(String s){
        int hash = 0; //The hash value of the empty string is zero.
        for (int i = 0; i< s.length(); i++){
            char c = s.charAt(i);
            hash = (31 * hash) + (int) c; // Use the ascii value of the character
        }
        return hash;
    }

    public static void main(String[] args) {
        // Note: groupAnagrams.hashCode("eat") " is not equal to " groupAnagrams.hashCode("ate"))
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        //String[] arr = {"eat","tea","tan","ate","nat","bat"};
        String[] arr = { "eat","tea","tan","ate","nat","bat","ac","bd","aac","bbd","aacc","bbdd","acc","bdd"};
        List<List<String>>  result = groupAnagrams.groupAnagrams(arr);
        System.out.println(result);
    }

    private HashMap<Character, Integer> getMap(String str1) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0; i<str1.length(); i++){
            if (map.containsKey(str1.charAt(i))){
                map.put(str1.charAt(i), map.get(str1.charAt(i)) + (int)str1.charAt(i) );
            }else{
                map.put(str1.charAt(i), (int)str1.charAt(i));
            }
        }
        return map;
    }

    private String sort(String string){
        System.out.println(string);
        char[] chars = string.toCharArray(); // method on input string to create a char array for input string.
        Arrays.sort(chars); // method to sort char array.
        String sortedString = new String(chars);  // Use String class constructor to create a sorted string from char array. String is immutable in java, hence in third step we have to create a new string.
        System.out.println(sortedString);
        return sortedString;
    }

}
