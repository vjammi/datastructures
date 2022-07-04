package ds.patterns.trie;

import java.util.HashMap;
import java.util.Map;

public class PrefixTree {

    class TrieNode {
        Map<Character, TrieNode> charMap;
        boolean endOfWord;
        public TrieNode() {
            this.charMap = new HashMap();
            endOfWord = false;
        }
    }

    private TrieNode root;

    public PrefixTree() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            //TrieNode charTrieNode = current.charMap.get(ch);
            if (current.charMap.get(ch) == null){
                //charTrieNode = new TrieNode();
                current.charMap.put(ch, new TrieNode());
            }
            current = current.charMap.get(ch);
        }
        current.endOfWord = true;
    }

    public boolean search(String word){
        TrieNode current = root;
        for (int i=0; i<word.length(); i++){
            Character ch = word.charAt(i);
            //TrieNode node = current.charMap.get(ch);
            if (current.charMap.get(ch) == null){
                System.out.println("Word " +word +" NOT found..." );
                return false;
            }
            current = current.charMap.get(ch);
        }

        if (current.endOfWord == true) {
            System.out.println("Word " + word + " found..." +true);
            return true;
        }

        System.out.println("Word " + word + " found..." +false);
        return false;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            //TrieNode childOfCurrent = current.charMap.get(ch);
            if (current.charMap.get(ch) == null){
                //childOfCurrent = new TrieNode();
                current.charMap.put(ch, new TrieNode());
            }
        }
        System.out.println("prefix " + prefix + " found...");
        // current.endOfWord could be true (exact match) or false (letters present but not end of word)
        return true;
    }

    public static void main(String[] args) {
        //Your Trie object will be instantiated and called as such:
        PrefixTree obj = new PrefixTree();
        obj.insert("apple");
        boolean param_2 = obj.search("apple");
        boolean param_3 = obj.startsWith("app");
    }
}