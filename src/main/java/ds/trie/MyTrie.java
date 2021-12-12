package ds.trie;

import java.util.HashMap;
import java.util.Map;

public class MyTrie {

    private final TrieNode root;

    public MyTrie() {
        this.root = new TrieNode();
    }

    class TrieNode{
        Map<Character, TrieNode> children;
        boolean endOfWord;

        public TrieNode() {
            this.children = new HashMap<>();
            this.endOfWord = false;
        }

    }

    public void insert(String word){
        TrieNode current = root;

        for (int i=0; i< word.length(); i++){
            Character ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                node.children.put(ch, node);
            }
            current = node;
        }
        current.endOfWord = true;
    }

    public boolean search(String word){

        TrieNode current = root;
        for (int i=0; i< word.length(); i++){
            Character ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                System.out.println("Word " +word +" NOT found...");
                return false;
            }
            current = node;
        }

        System.out.println("Word " +word +" found ...");
        return current.endOfWord = true;
    }

    public static void main(String[] args) {
        MyTrie trie = new MyTrie();
        trie.insert("app");
        trie.insert("ape");
        trie.insert("ace");

        trie.search("app");
        trie.search("ape");
        trie.search("ace");

        System.out.println(trie.root.toString());
    }

}
