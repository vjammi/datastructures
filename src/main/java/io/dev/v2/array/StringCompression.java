package io.dev.v2.array;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Failing Tests
 **/
public class StringCompression{

    class Pair<K, V>{
        K key;
        V val;
        public Pair(K key, V val){
            this.key = key;
            this.val = val;
        }
    }


    //     ["a","a","b","b","c","c","c"]
    //     0   1   2   3   4   5   6
    //     ^       ^
    //     i       j
    //
    //     i = 2
    //     j = 2
    //     currPair = <a 2>
    //     output = [a, 2, b, 2, c 3]

    public int compress(char[] chars) {
        if (chars.length == 1)
            return 1;
        Pair<Character, Integer> currPair = null;
        List<Pair<Character, Integer>> output = new ArrayList<>();
        int i=0;
        for (int j=0; j<chars.length; j++){
            if (chars[i] == chars[j]){
                if (currPair == null) currPair = new Pair<>(chars[i], 1);
                currPair.val = (int) currPair.val + 1;
            }else{
                output.add(new Pair(Character.valueOf(currPair.key), currPair.val));
                //currPair = null;
                i=j;
                currPair = new Pair<>(chars[i], 1);
            }
        }
        output.add(new Pair(Character.valueOf(currPair.key), currPair.val));

        chars = new char[output.size()*2];

        int k=0;
        for (int ii=0; ii<output.size(); ii++){
            Pair<Character, Integer> pair = output.get(ii);
            chars[k] = (char) pair.key;
            chars[++k] = String.valueOf(pair.val).charAt(0);
            k++;
        }
        return chars.length;
    }

    public static void main(String[] args) {

        int a = 1;
        char b = (char) a;
        System.out.println(b);

        int aa = '1';
        char bb = (char) aa;
        System.out.println(bb);

        StringCompression obj = new StringCompression();
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int actual = obj.compress(chars);
        System.out.println(actual);
    }
}

