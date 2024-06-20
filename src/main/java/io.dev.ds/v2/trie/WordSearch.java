package dev.vjammi.ds.v2.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 212. Word Search II
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells
 * are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 **/
// 47 / 63 test cases passed.
public class WordSearch {

    TrieNode root;

    public List<String> findWords(char[][] board, String[] words) {

        char[][] visited = new char[board.length][board[0].length];
        List<String> result = new ArrayList<>();

        root = new TrieNode();
        for (String word: words)
            add(word);

        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                char ch = board[i][j];
                if (startsWith(String.valueOf(ch))) {
                    dfs(board, i, j, result, new StringBuilder());
                }
            }
        }
        return result;
    }

    /**

     r-1,c

     r,c-1    r,c     r,c+1

     r+1,c

     */

    private boolean dfs(char[][] board, int r, int c, List<String> result, StringBuilder word){

        // If out of bounds return
        if (r<0 || r>board.length-1 || c<0 || c>board[0].length-1)
            return false;

        char ch = board[r][c];

        if (!startsWith(word + String.valueOf(ch)))
            return false;

        word.append(ch);

        if (contains(word.toString()) && !result.contains(word.toString())){
            result.add(word.toString());
            return true;
        }

        boolean north = dfs(board, r-1, c, result, word);
        if (north) return true;
        boolean south = dfs(board, r+1, c, result, word);
        if (south) return true;
        boolean east  = dfs(board, r, c+1, result, word);
        if (east) return true;
        boolean west  = dfs(board, r, c-1, result, word);
        return west;
    }

    private class TrieNode {
        Map<Character, TrieNode> map;
        boolean isWord;

        public TrieNode(){
            map = new HashMap();
            isWord = false;
        }
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


    private boolean contains(String word){
        TrieNode currentNode = root;
        StringBuilder builder = new StringBuilder();

        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            builder.append(ch);
            TrieNode childNode = currentNode.map.get(ch);

            if (childNode == null){
                System.out.println("Word Prefix Found: " + builder);
            }
            currentNode = childNode;
        }

        if (currentNode.isWord) {
            System.out.println("Word Found: " + builder);
            return true;
        }

        System.out.println("Word NOT Found: " + builder);
        return false;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        StringBuilder builder = new StringBuilder();

        for (int i=0; i<prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            if (current.map.get(ch) == null){
                System.out.println("Prefix NOT found " + prefix +". Prefix found so far " + builder);
                return false;
            }
            builder.append(ch);
            current = current.map.get(ch);
        }

        System.out.println("Prefix found " + prefix);
        return true;
    }


    public static void main(String[] args) {
        WordSearch ws = new WordSearch();

        // [["o","a","b","n"],["o","t","a","e"],["a","h","k","r"],["a","f","l","v"]]
        // ["oa","oaa"]
        char[][] board1 = new char[][]{ {'o','a','b','n'},
                                        {'o','t','a','e'},
                                        {'a','h','k','r'},
                                        {'a','f','l','v'}};
        String[] words1 = new String[]{"oa","oaa"};
        List<String> wordMatches1 = ws.findWords(board1, words1);
        System.out.println(wordMatches1);

//        char[][] board2 = new char[][]{{'o','a','a','n'}, {'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
//        String[] words2 = new String[]{"oath","pea","eat","rain"};
//        List<String> wordMatches2 = ws.findWords(board2, words2);
//        System.out.println(wordMatches2);
    }


}
