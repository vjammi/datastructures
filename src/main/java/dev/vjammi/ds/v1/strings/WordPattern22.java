package dev.vjammi.ds.v1.strings;

import java.util.HashMap;

public class WordPattern22 {

    public static void main(String[] args) {
        WordPattern22 wordPattern22 = new WordPattern22();

        String pattern3  = "aba";
        String str3      = "redbluered";
        boolean isItAMatch3 = wordPatternMatch(pattern3, str3);
        System.out.println(isItAMatch3);
        /*
        String pattern1 = "abab";
        String str1     = "redblueredblue";
        boolean isItAMatch1 = wordPattern22.wordPatternMatch(pattern1, str1);
        System.out.println(isItAMatch1);

        String pattern2  = "abcbc";
        String str2      = "redbluegreenbluegreen";
        boolean isItAMatch2 = wordPattern22.wordPatternMatch(pattern2, str2);
        System.out.println(isItAMatch2);
        */


    }
    /**
    public static boolean wordPatternMatch(String pattern, String str) {
        if(pattern.length()==0 && str.length()==0)
            return true;
        if(pattern.length()==0)
            return false;

        HashMap<Character, String> map = new HashMap<Character, String>();
        HashSet<String> set = new HashSet<String>();
        return helper(pattern, str, 0, 0, map, set);
    }

    public static boolean helper(String pattern, String str, int i, int j, HashMap<Character, String> map, HashSet<String> set){

        if(i==pattern.length() && j==str.length()){
            return true;
        }

        if(i>=pattern.length() || j>=str.length()) {
            return false;
        }

        char c = pattern.charAt(i);
        System.out.println("|_Char: " +c);
        for(int k=j+1; k<=str.length(); k++){

            String sub = str.substring(j, k);
            System.out.println(" |_Sub: "+sub);

            if(!map.containsKey(c) && !set.contains(sub)){
                System.out.println(" > Map contains Key char (" + c +") ? " +map.containsKey(c) +"  set.contains(" +sub +")? " +set.contains(sub));
                map.put(c, sub);
                set.add(sub);
                if(helper(pattern, str, i+1, k, map, set)) {
                    System.out.println(" > Returning True");
                    return true;
                }
                map.remove(c);
                set.remove(sub);

            }else if(map.containsKey(c) && map.get(c).equals(sub)){
                System.out.println(" >> Map contains Key char (" + c +") ? " +map.containsKey(c) +"  set.contains(" +sub +")? " +set.contains(sub));
                if(helper(pattern, str, i+1, k, map, set)) {
                    System.out.println(" >> Returning True");
                    return true;
                }
            }

        }

        return false;
    }
    */

    public static boolean wordPatternMatch(String pattern, String str) {

        if(pattern.length()==0 && str.length()==0)
            return true;

        if(pattern.length()==0)
            return false;

        HashMap<Character, String> map = new HashMap<>();
        boolean result = helper(pattern, str, 0, 0, map);

        return result;
    }

    public static boolean helper(String pattern, String str, int i, int j, HashMap<Character, String> map){
        if(i==pattern.length() && j==str.length()){
            return true;
        }

        if(i>=pattern.length() || j>=str.length())
            return false;

        char c = pattern.charAt(i);
        System.out.println("|_Char: " +c);
        for(int k=j+1; k<=str.length(); k++){
            String sub = str.substring(j, k);
            System.out.println("    |_Sub: "+sub);
            if(!map.containsKey(c) && !map.containsValue(sub)){
                System.out.println("        > Map contains Key char (" + c +") ? " +map.containsKey(c) +"  set.contains(" +sub +")? " +map.containsValue(sub));
                map.put(c, sub);
                if(helper(pattern, str, i+1, k, map)) {
                    System.out.println("        > Returning True");
                    return true;
                }
                map.remove(c);
            }else if(map.containsKey(c) && map.get(c).equals(sub)) {
                System.out.println("            >> Map contains Key char (" + c + ") ? " + map.containsKey(c) + "  map.get(" + sub + ")? " + map.get(c) + "==?" + sub);
                if (helper(pattern, str, i + 1, k, map)){
                    System.out.println("        >> Returning True");
                    return true;
                }
            }
            System.out.println("---");
        }

        return false;
    }

}
