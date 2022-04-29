package ds.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private TrieNode root;

    public Trie(){
        this.root = new TrieNode();
    }

    class TrieNode{
        Map<Character, TrieNode> children;
        boolean endOfWord;

        public TrieNode(){
            this.children = new HashMap();
            endOfWord = false;
        }
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            TrieNode childOfCurrent = current.children.get(ch);
            if (childOfCurrent == null){
                childOfCurrent = new TrieNode();
                current.children.put(ch, childOfCurrent);
            }
            current = current.children.get(ch);
        }
       current.endOfWord = true;
    }

    public boolean search(String word){
        TrieNode current = root;
        for (int i=0; i<word.length(); i++){
            Character ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null){
                System.out.println("Word " +word +" NOT found..." );
                return false;
            }
            current = node;
        }

        if (current.endOfWord == true) {
            System.out.println("Word " + word + " found..." +true);  return true;
        }else {
            System.out.println("Word " + word + " found..." +false); return false;
        }
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            TrieNode childOfCurrent = current.children.get(ch);
            if (childOfCurrent == null){
                childOfCurrent = new TrieNode();
                current.children.put(ch, childOfCurrent);
            }else{
                System.out.println("Prefix " + prefix + " NOT found...");
                return false;
            }
        }
        System.out.println("Prefix " + prefix + " found...");
        return true;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("dob");
        trie.insert("doc");
        trie.insert("dod");
        trie.insert("doe");
        trie.insert("dof");
        trie.insert("dog");
        trie.insert("doggy");

        trie.search("dog");
        trie.search("doggy");
        trie.startsWith("dogg");
        trie.startsWith("dob");
    }

}
