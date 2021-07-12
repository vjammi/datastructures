package ds.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {
	
	private class TrieNode{
		Map<Character, TrieNode> children;
		boolean endOfWord;
		
		public TrieNode() {
			children = new HashMap<>();
			endOfWord = false;
		}
	}
	
	private final TrieNode root;
	public Trie() {
		root = new TrieNode();
	}
	
	
	public void insert(String word){
		
		TrieNode current = root;
		for (int i=0; i<word.length(); i++){
			Character c = word.charAt(i);
			
			TrieNode node = current.children.get(c);
			if(node == null){
				System.out.println("Does not contain character "+c);
				node = new TrieNode(); 
				current.children.put(c, node);
			}
			current = node;
		}
		current.endOfWord = true;
	}

	public boolean search(String word){
		
		TrieNode current = root;
		for (int i=0; i<word.length(); i++){
			Character c = word.charAt(i);
			
			TrieNode node = current.children.get(c);
			if(node == null){
				System.out.println("Character in the word sequence does not exist "+c +" Returning false...");
				return false;
			}
			System.out.println("Character Exists: "+c);
			current = node;
		}
		return current.endOfWord;
		
	}
	
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("abcde");
		trie.search("abcdef");		
	}
	
	
}
