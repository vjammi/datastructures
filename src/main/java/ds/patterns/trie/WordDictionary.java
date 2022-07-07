package ds.patterns.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 211. Design Add and Search Words Data Structure
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 * */

class WordDictionary {
    TrieNode root;

    class TrieNode {
        Map<Character, TrieNode> map;
        boolean isWord;

        public TrieNode(){
            map = new HashMap();
            isWord = false;
        }
    }


    public WordDictionary(){
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode currentNode = root;
        for (int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            TrieNode childNode  = currentNode.map.get(ch);
            if ( childNode == null)
                currentNode.map.put(ch, new TrieNode());
            // CANNOT assign the childNode to the currentNode.
            // It has to be currentNode.map.get(ch), in case childNode null, we end up creating it prior to
            currentNode = currentNode.map.get(ch);
        }
        currentNode.isWord = true;
    }

    public boolean search2(String word) {
        TrieNode currentNode = root;
        StringBuilder builder = new StringBuilder();

        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            builder.append(ch);
            TrieNode childNode = currentNode.map.get(ch);

            if (childNode == null){
                System.out.println("Word Prefix Found: " +builder.toString());
            }
            currentNode = childNode;
        }

        if (currentNode.isWord) {
            System.out.println("Word Found: " + builder.toString());
            return true;
        }

        System.out.println("Word NOT Found: " + builder.toString());
        return false;

    }

    /** Returns if the word is in the node. */
    public boolean searchInNode(String word, TrieNode node) {
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (!node.map.containsKey(ch)) {
                // if the current character is '.' check all possible nodes at this level
                if (ch == '.') {
                    for (char x : node.map.keySet()) {
                        TrieNode child = node.map.get(x);
                        boolean wordFound = searchInNode(word.substring(i + 1), child);
                        if (wordFound)
                            return true;
                    }
                }
                // if no nodes lead to answer or the current character != '.'
                return false;
            } else {
                // if the character is found go down to the next level in trie
                node = node.map.get(ch);
            }
        }
        return node.isWord;
    }

    /** Returns if the word is in the data structure.
     A word could contain the dot character '.' to
     represent any one letter.
     */
    public boolean search(String word) {
        return searchInNode(word, root);
    }

    public static void main(String[] args) {
     // Your WordDictionary object will be instantiated and called as such:
     WordDictionary obj = new WordDictionary();

     obj.addWord("dot");
     boolean param_2 = obj.search("d..");

     System.out.println(param_2);

     // ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
     // [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
    }
}
