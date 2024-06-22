package io.dev.v2.dev.usage.operations;

public class CharSetsUsage {

    public static void main(String[] args) {
        isAnagram();
    }

    private static boolean isAnagram() {
        String s = "anagram";
        String t = "nagaram";
        int[] charSetS = new int[26];
        int[] charSetT = new int[26];

        for (int i=0; i<s.length(); i++){
            int ch = s.charAt(i);
            charSetS[ch-97] = charSetS[ch-97] + 1;
        }

        for (int i=0; i<t.length(); i++){
            int ch = t.charAt(i);
            charSetT[ch-97] = charSetT[ch-97] + 1;
        }

        for (int i=0; i<charSetS.length; i++){
            if (charSetS[i] != charSetT[i])
                System.out.println("*****NOT an Anagram");
            return false;
        }

        System.out.println(s + " is an anagram of " +t);
        return true;
    }
}
