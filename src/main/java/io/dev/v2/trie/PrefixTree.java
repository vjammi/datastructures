package io.dev.v2.trie;

import java.util.HashMap;
import java.util.Map;

public class PrefixTree {

    TrieNode root;

    class TrieNode {
        Map<Character, TrieNode> map;
        boolean isWord;

        public TrieNode(){
            map = new HashMap();
            isWord = false;
        }
    }

    public PrefixTree(){
        root = new TrieNode();
    }

    /**
     *
     *  An Empty Trie initialized with an empty TrieNode with isWord = False
     *
     *                   [_ _ _ _ _ _ _ _ _ _ _ _]                                   // root = new TrieNode());
     *
     *
     *  Adding the word DOG to the Trie
     *
     *                   [_ _ _ D _ _ _ _ _ _ _  _]                                  // currentNode.map.put('D', new TrieNode());
     *                          *
     *                          |___________
     *                                      |
     *         [_ _ _ _ _ _ _ _ _ _ _ _ _ _ O _   _]                                 // currentNode.map.put('O', new TrieNode());
     *                                      *
     *                                      |__________
     *                                                |
     *                                   [_ _ _ _ _ _ G _   _ _ _ _ _ _ _ _ _ _]     // currentNode.map.put('G', new TrieNode());
     *                                                *
     *                                                |
     *                                   [_ _ _ _ _ _ _ _ _ _ _ _ _   _]
     *                                         isWord = True                        // currentNode.isWord = true;
     *
     *        Note: Within the new/empty TrieNode we insert for the last letter of the word, we set isWord to true
     */
    public void insert(String word){
        TrieNode currentNode = root;

        for (int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            if ( currentNode.map.get(ch) == null)
                currentNode.map.put(ch, new TrieNode());
            // CANNOT assign the childNode to the currentNode.
            // It has to be currentNode.map.get(ch), in case childNode null, we end up creating it prior to
            currentNode = currentNode.map.get(ch);
        }
        currentNode.isWord = true;
    }


    public boolean search(String word){
        TrieNode currentNode = root;
        StringBuilder builder = new StringBuilder();

        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            builder.append(ch);

            if (currentNode == null){
                System.out.println("Word NOT Found: " + word);
                return false;
            }

            if (currentNode.map.get(ch) == null){
                System.out.println("Word NOT Found: " +word);
                return false;
            }
            currentNode = currentNode.map.get(ch);
        }

        if (currentNode != null && currentNode.isWord) {
            System.out.println("Word Found: " + builder);
            return true;
        }

        System.out.println("Word NOT Found: " + word);
        return false;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        StringBuilder builder = new StringBuilder();

        for (int i=0; i<prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            if (current.map.get(ch) == null){
                System.out.println("Prefix NOT found " + prefix +". Prefix found so far " + builder);
                return false;
            }
            builder.append(ch);
            current = current.map.get(ch);
        }

        System.out.println("Prefix found " + prefix);
        return true;
    }

    public static void main(String[] args) {
        PrefixTree trie = new PrefixTree();
        trie.search("dot");
        //trie.insert("dog"); trie.insert("dot"); trie.insert("doting"); trie.insert("drag"); trie.insert("drastic"); trie.insert("top"); trie.insert("torn"); trie.insert("trap");
        //trie.search("dot");
        //trie.startsWith("dotingg");
    }
}