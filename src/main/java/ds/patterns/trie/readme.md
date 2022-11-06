## Trie 

```
                        [*]
                   d  /     \ t
                    [*]      [*]
                o  /       / o   \ r
                 [*]      [*]    [*]
              g /        / m       \ a
             [true]    [true]      [*]
                                     \ i
                                     [*]
                                      \ n
                                     [true]
            dog, tom, train                                    
```

```
        root = {Trie$TrieNode@698} 
         children = {HashMap@699}  size = 1
          {Character@703} d -> {Trie$TrieNode@704} 
            key = {Character@703} d
            value = {Trie$TrieNode@704} 
              children = {HashMap@705}  size = 1
              {Character@709} o -> {Trie$TrieNode@710} 
                key = {Character@709} o
                value = {Trie$TrieNode@710} 
                 children = {HashMap@711}  size = 1
                  {Character@715} g -> {Trie$TrieNode@716} 
                   key = {Character@715} g
                   value = {Trie$TrieNode@716} 
                    children = {HashMap@717}  size = 0
                    endOfWord = true
                    this$0 = {Trie@697} 
                 endOfWord = false
                 this$0 = {Trie@697} 
              endOfWord = false
              this$0 = {Trie@697} 
           endOfWord = false
           this$0 = {Trie@697}
```
#### Trie Implementation
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
        
        // 0 1 2 
        // d o g
        for (int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            TrieNode childNode  = currentNode.map.get(ch);
            if ( childNode == null)
               currentNode.map.put(ch, new TrieNode());         //  d o g
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

