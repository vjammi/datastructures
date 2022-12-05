package dev.vjammi.ds.v1.trie;

import java.util.ArrayList;


/* Implements a trie. We store the input list of words in tries so
 * that we can efficiently find words with a given prefix. 
 */ 
public class Trie_ListImpl
{
    // The root of this trie.
    private final TrieNode_MapImpl root;

    /* Takes a list of strings as an argument, and constructs a trie that stores these strings. */
    public Trie_ListImpl(ArrayList<String> list) {
        root = new TrieNode_MapImpl();
        for (String word : list) {
            root.addWord(word);
        }
    }  
    

    /* Takes a list of strings as an argument, and constructs a trie that stores these strings. */    
    public Trie_ListImpl(String[] list) {
        root = new TrieNode_MapImpl();
        for (String word : list) {
            root.addWord(word);
        }
    }    

    /* Checks whether this trie contains a string with the prefix passed
     * in as argument.
     */
    public boolean contains(String prefix, boolean exact) {
        TrieNode_MapImpl lastNode = root;
        int i = 0;
        for (i = 0; i < prefix.length(); i++) {
            lastNode = lastNode.getChild(prefix.charAt(i));
            if (lastNode == null) {
                return false;	 
            }
        }
        return !exact || lastNode.terminates();
    }
    
    public boolean contains(String prefix) {
    	return contains(prefix, false);
    }
    
    public TrieNode_MapImpl getRoot() {
    	return root;
    }
}
