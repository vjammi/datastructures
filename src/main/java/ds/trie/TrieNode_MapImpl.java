package ds.trie;

import java.util.HashMap;

/* One node in the trie. Most of the logic of the trie is implemented
 * in this class.
 */
public class TrieNode_MapImpl {
    /* The children of this node in the trie.*/
    private HashMap<Character, TrieNode_MapImpl> children;
    private boolean terminates = false;

    // The character stored in this node as data.
    private char character;	

	/* Constructs a trie node and stores this character as the node's value.
	 * Initializes the list of child nodes of this node to an empty hash map. */
    public TrieNode_MapImpl() {
    	children = new HashMap<Character, TrieNode_MapImpl>();
    }

    /* Constructs a trie node and stores in the node the char passed in
     * as the argument. Initializes the list of child nodes of this
     * node to an empty hash map.
     */
    public TrieNode_MapImpl(char character) {
        this();
        this.character = character;
    }

    /* Returns the character data stored in this node. */
    public char getChar() {
        return character;
    }

    /* Add this word to the trie, and recursively create the child
     * nodes. */
    public void addWord(String word) {
    	if (word == null || word.isEmpty()) {
    		return;
    	}
    	
        char firstChar = word.charAt(0);

        TrieNode_MapImpl child = getChild(firstChar);
        if (child == null) {
            child = new TrieNode_MapImpl(firstChar);
            children.put(firstChar, child);
        } 

        if (word.length() > 1) {
            child.addWord(word.substring(1));
        } else {
        	child.setTerminates(true);
        }
    }

    /* Find a child node of this node that has the char argument as its
     * data. Return null if no such child node is present in the trie.
     */
    public TrieNode_MapImpl getChild(char c) {
    	return children.get(c);
    }

    /* Returns whether this node represents the end of a complete word. */
    public boolean terminates() {
    	return terminates;
    }
    
    /* Set whether this node is the end of a complete word.*/
    public void setTerminates(boolean t) {
    	terminates = t;
    }
}
