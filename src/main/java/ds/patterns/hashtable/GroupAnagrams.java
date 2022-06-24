package ds.patterns.hashtable;

import java.util.*;

public class GroupAnagrams {

    /**
         Input   = ["eat","tea","tan","ate","nat","bat"]
         Output  = [["bat"],["nat","tan"],["ate","eat","tea"]]
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<Integer, List<String>> map = new HashMap<>();
        for(int i =0; i< strs.length; i++){
            Integer[] charSet = new Integer[26];
            String str = strs[i];
            for (int j=0; j<str.length(); j++){

                //int ch = (int) str.charAt(j);
                //int chIndex = ch-96-1;
                char ch = str.charAt(j);
                int chIndex = ch - 'a';

                if (charSet[chIndex] != null)
                    charSet[chIndex] = charSet[chIndex] + 1;
                else
                    charSet[chIndex] = 1;
            }
            int key = Arrays.hashCode(charSet);               // Option 1: Getting the hashCode() of the contents of the array
            // String strKey = getStrRepresentation(charSet); // Option 2: Getting the string representation of the charASet, delimited with an #

            System.out.println(str +" - " +Arrays.asList(charSet) +" - " +key);
            if (map.containsKey(key)){ //charSet.hashCode()
                map.get(key).add(str);
            }else{
                List<String> list = new ArrayList();
                list.add(str);
                map.put(key, list);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry: map.entrySet()){
            result.add(entry.getValue());
        }

        return result;
    }

    private String getStrRepresentation(Integer[] charSet) {
        StringBuilder keyBuilder = new StringBuilder("");
        for (int k = 0; k < charSet.length; k++) {
            keyBuilder.append('#'); keyBuilder.append(k);
        }
        return keyBuilder.toString();
    }

    public static void main(String[] args) {
        //new GroupAnagrams().groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
        new GroupAnagrams().groupAnagrams(new String[]{"tin","ram","zip","cry","pus","jon","zip","pyx"});
    }
}
