package ds.trie;
import java.util.HashMap;
import java.util.Map;

public class Trie {

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	private class TrieNode{
		Map<Character, TrieNode> children;
		boolean endOfWord;
		public TrieNode() {
			children = new HashMap<>();
			endOfWord = false;
		}
	}

	/**
	 * Iterative implementation of insert into trie
	 */
	public void put(String word){
		TrieNode current = root;

		for (int i = 0; i< getWordLength(word); i++){
		    Character c = word.charAt(i);

		    // Get the TrieNode for the char to insert.
			// If the char does not exist in the map, we get a null
			// [Note: If the partial word exists in this ds, then we will receive an existing node,
			// which we will have to traverse until we get to a point
			// where we no alphabets for this word sequence are in the ds]
		    TrieNode x = current.children.get(c);

		    // Now, If we receive a null TrieNode from above,
			// which means the current node does not have the char in the map
			// then we insert the char and put the reference of the new TrieNode as its value
			if(x == null){
				System.out.println("Does not contain character "+c);
				x = new TrieNode();
				// Key: Put the char c as a key in the current node's map
				// Value: Put the reference of the newly created node as the value in the current node's map
				current.children.put(c, x);
			}
			// we then reset c
			current = x;
		}
		// At the end of traversing we mark the current's endOfWord flag to true
		current.endOfWord = true;
	}

	/**
	 * Recursive implementation of insert into trie
	 */
	public void putRecursive(String word) {
		putRecursive(root, word, 0);
	}

	private void putRecursive(TrieNode current, String word, int index) {
		if (index == getWordLength(word)) {
			//if end of word is reached then mark endOfWord as true on current node
			current.endOfWord = true;
			return;
		}
		char ch = word.charAt(index);
		TrieNode node = current.children.get(ch);

		//if node does not exists in map then create one and put it into map
		if (node == null) {
			node = new TrieNode();
			current.children.put(ch, node);
		}
		putRecursive(node, word, index + 1);
	}

	private int getWordLength(String word) {
		return word.length();
	}

	/**
	 * Iterative implementation of search into trie.
	 */
	public boolean search(String word) {
		TrieNode current = root;
		for (int i = 0; i < getWordLength(word); i++) {
			char ch = word.charAt(i);
			TrieNode node = current.children.get(ch);
			//if node does not exist for given char then return false
			if (node == null) {
				return false;
			}
			current = node;
		}
		//return true if the endOfWord flag of the node referenced by the last char is true else return false.
		return current.endOfWord;
	}

	/**
	 * Recursive implementation of search into trie.
	 */
	public boolean searchRecursive(String word) {
		return searchRecursive(root, word, 0);
	}

	private boolean searchRecursive(TrieNode current, String word, int indexPlusOne) {
		if (indexPlusOne == getWordLength(word)) {
			//return true of current's endOfWord is true else return false.
			return current.endOfWord;
		}
		char ch = word.charAt(indexPlusOne);
		TrieNode node = current.children.get(ch);
		//if node does not exist for given char then return false
		if (node == null) {
			return false;
		}
		return searchRecursive(node, word, indexPlusOne + 1);
	}

	/**
	 * Delete word from trie.
	 */
	public void delete(String word) {
		delete(root, word, 0);
	}

	/**
	 * Returns true if parent should delete the mapping
	 */
	private boolean delete(TrieNode current, String word, int indexPlusOne) {
		if (indexPlusOne == getWordLength(word)) {
			//when end of word is reached only delete if currrent.endOfWord is true.
			if (!current.endOfWord) {
				return false;
			}
			current.endOfWord = false;
			//if current has no other mapping then return true
			return current.children.size() == 0;
		}
		char ch = word.charAt(indexPlusOne);
		TrieNode node = current.children.get(ch);
		if (node == null) {
			return false;
		}
		boolean shouldDeleteCurrentNode = delete(node, word, indexPlusOne + 1);

		//if true is returned then delete the mapping of character and trienode reference from map.
		if (shouldDeleteCurrentNode) {
			current.children.remove(ch);
			//return true if no mappings are left in the map.
			return current.children.size() == 0;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Trie trie = new Trie();

		trie.putRecursive("abc");
		trie.putRecursive("abcd");

		//trie.put("abc");

		trie.search("abc");

		trie.delete("abcd");
		trie.delete("abc");
	}

}
