package ds.patterns.fb;

import java.util.HashMap;
import java.util.Map;

public class EnglishToInteger {

    public static int convertStringToNumber(String st) {

        Map<String,Integer> map = new HashMap<>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("twenty", 20);

        Map<String,Integer> big = new HashMap<>();
        big.put("hundred", 100);
        big.put("thousand", 1000);
        big.put("million", 1000000);

        st = st.toLowerCase();

        String[] tokens = st.split(" ");
        int number = 0;
        int result = 0;
        for(int i = 0; i< tokens.length; i++) {
            if(map.containsKey(tokens[i])) {
                number += map.get(tokens[i]);
            }
            if(big.containsKey(tokens[i])) {
                result += number * big.get(tokens[i]);
                number = 0;
            }
        }
        result += number;
        return result;
    }
    public static void main(String[] args) {
        System.out.println(" ans is "+ convertStringToNumber("One hundred twenty three"));
        System.out.println(" ans is "+ convertStringToNumber("zero"));
    }

}
