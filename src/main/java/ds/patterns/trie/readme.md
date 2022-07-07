## Trie Implementation
```
public class Trie {
    TrieNode root;

    private class TrieNode {
        Map<Character, TrieNode> map;
        boolean isWord;

        public TrieNode(){
            map = new HashMap();
            isWord = false;
        }
    }

    public Trie(){
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
        Trie trie = new Trie();
        trie.add("dog"); wb.add("dot"); 
        //trie.add("doting"); trie.add("drag"); trie.add("drastic"); trie.add("top"); trie.add("torn"); trie.add("trap");
        trie.search("dot");
    }

}
```
#### Reference
https://courses.cs.duke.edu/fall12/cps100/Recitations/Recitation11.html

