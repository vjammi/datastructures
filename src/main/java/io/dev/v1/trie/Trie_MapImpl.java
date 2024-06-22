package io.dev.v1.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie_MapImpl {

	private final TrieNode root;
	public Trie_MapImpl() {
		root = new TrieNode();
	}

	private class TrieNode{

		boolean endOfWord;
		Map<Character, TrieNode> map;

		public TrieNode() {
			map = new HashMap<>();
			endOfWord = false;
		}
	}

	public void insert(String word){
		
		TrieNode current = root;
		for (int i=0; i<word.length(); i++){

			Character c = word.charAt(i);
			TrieNode node = current.map.get(c);

			if(node == null){
				System.out.println("Does not contain character "+c);
				node = new TrieNode(); 
				current.map.put(c, node);
			}
			current = node;
		}
		current.endOfWord = true;
	}

	public boolean search(String word){
		
		TrieNode current = root;
		for (int i=0; i<word.length(); i++){
			Character c = word.charAt(i);
			
			TrieNode node = current.map.get(c);
			if(node == null){
				System.out.println("Character in the word sequence does not exist "+c +" Returning false...");
				return false;
			}
			System.out.println("Character Exists: "+c);
			current = node;
		}
		System.out.println("Word " +word +" found ...");
		return current.endOfWord;
		
	}
	
	public static void main(String[] args) {
		Trie_MapImpl trie = new Trie_MapImpl();

		trie.insert("app");
		trie.insert("ape");
		trie.insert("ace");

		trie.search("app");
		trie.search("ape");
		trie.search("ace");


	}
	
	
}
