package dev.vjammi.ds.v1.strings;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WordPattern {

    public static void main(String[] args) {
        String str = "red blue red blue";
        String pattern = "abab";

        HashMap<String, Integer> patternHashMap = findHashForThePattern(pattern);
        int hash1 = getHash(pattern, patternHashMap);

        String[] words = str.split(" ");
        HashMap<String, Integer> wordSequenceHashMap =  findHashForWordSequence(words);
        int hash2 = getHash(words, wordSequenceHashMap);

        if (hash1 == hash2){
            System.out.println("Hash Match Successful");
        }
    }

    private static HashMap<String, Integer> findHashForThePattern(String pattern) {
        HashMap<String, Integer> patternHashMap = new HashMap<>();
        int counter = 1;
        for (int i = 0; i < pattern.length(); i++) {
            String charString = String.valueOf(pattern.charAt(i));
            System.out.print("" + charString + ":" + counter + ", ");
            patternHashMap.put(charString, counter);
            counter ++;
        }
        return patternHashMap;
   }

    private static HashMap<String, Integer> findHashForWordSequence(String[] strings) {
        HashMap<String, Integer> wordSequenceHashMap = new HashMap<>();
        int counterA = 1;
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            System.out.print("" + string + ":" + counterA + ", ");
            wordSequenceHashMap.put(string, counterA);
            counterA ++;
        }
        System.out.println();
        return wordSequenceHashMap;
    }


    private static int getHash(String[] strings, HashMap<String, Integer> map) {
        int hash = 0;
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.print(entry.getKey() +":" +entry.getValue() +", ");
        }
        System.out.println();
        for(int i=0; i<strings.length; i++){
            String key = strings[i];
            System.out.print(key + ":" + map.get(key) + ", ");
            hash = hash + map.get(key);
        }
        System.out.println("\nHash Value " + "=" +hash);
        return hash;
    }

    private static int getHash(String pattern, HashMap<String, Integer> map) {
        int hash = 0;
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.print(entry.getKey() +":" +entry.getValue() +", ");
        }
        System.out.println();
        for(int i=0; i<pattern.length(); i++){
            String key = String.valueOf(pattern.charAt(i));
            System.out.print(key + ":" + map.get(key) + ", ");
            hash = hash + map.get(key);
        }
        System.out.println("\nHash Value " + "=" +hash);
        return hash;
    }


}
