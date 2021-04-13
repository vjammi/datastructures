package ds.trie;

public class TrieST <Value> {

    private TrieNode root;
    private int R = 256;

    private static class TrieNode {
        TrieNode[] children;
        Object value;

        public TrieNode(int R){
            children = new TrieNode[R];
        }
    }

    private void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private TrieNode put(TrieNode x, String key, Value val, int index) {

        if (x == null) { x = new TrieNode(R); }

        if (index == key.length()){
            x.value = val;
            return x;
        }

        char c = key.charAt(index);
        printASCIIOrUnicodeInxOfCharacter(c);

        // x.children[c] will get the the TrieNode corresponding to character c
        // from an array of child TrieNodes of x
        x.children[c] = put(x.children[c], key, val, index +1);
        return x;
    }

    private void printASCIIOrUnicodeInxOfCharacter(char c) {
        int unicodeInxOfC = c;
        System.out.println("Value of char c is " +c + " and its index is " +unicodeInxOfC);
    }

    /*
    TrieNode[] arrayOfChildTrieNodes = x.children;
    TrieNode childTrieNode = arrayOfChildTrieNodes[c];
    childTrieNode = put(childTrieNode, key, val, index +1);
    return childTrieNode;
    */

    public static void main(String[] arg){
        TrieST trie = new TrieST();
        trie.put("abc", 1);
        trie.put("cde", 2);
        trie.put("efg", 3);
        trie.put("ghi", 4);
    }

}
