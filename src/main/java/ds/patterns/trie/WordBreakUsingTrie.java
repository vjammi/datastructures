package ds.patterns.trie;

import java.util.HashMap;
import java.util.Map;

public class WordBreakUsingTrie {
    TrieNode root;

    private class TrieNode {
        Map<Character, TrieNode> map;
        boolean isWord;

        public TrieNode(){
            map = new HashMap();
            isWord = false;
        }
    }

    public WordBreakUsingTrie(){
        root = new TrieNode();
    }

    private void add(String word){
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


    private boolean search(String word){
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

    public static void main(String[] args) {
        WordBreakUsingTrie wb = new WordBreakUsingTrie();
        wb.add("dog"); wb.add("dot"); // wb.add("doting"); wb.add("drag"); wb.add("drastic"); wb.add("top"); wb.add("torn"); wb.add("trap");
        wb.search("dot");
    }

}
