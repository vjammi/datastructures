package ds.strings;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WordPattern2 {

    public static void main(String[] args) {
        /*
        String pattern = "abab ";
        String str = "redblueredblue ";
        */

        String pattern  = "abcbc";
        String str      = "redbluegreenbluegreen";

        findPattern(pattern, str);
    }

    private static void findPattern(String pattern, String str) {

        int matchingSize = str.length()/2;

        for (int k = 4; k <= matchingSize; k++) {

            HashMap<String, Integer> mapA = new HashMap<>();
            int counterA = 1;
            for (int i = 0; i < str.length()-k; i++) {
                String stringPattern = str.substring(i, i+k);
                //System.out.println("@ index " +i + " > " +stringPattern + "");
                for (int j = i+k ; j < str.length()- k ; j++) {
                    String subString = str.substring(j, j + k);
                    if (stringPattern.equals(subString)) {
                        System.out.println(" >" + stringPattern + "-" + subString + ",     ");
                        if (!subString.equals("")){
                            mapA.put(subString, counterA);
                        }
                        counterA ++;
                    }
                }
            }
            show(str, mapA);

            HashMap<String, Integer> mapB = new HashMap<>();
            int counterB = 1;
            for (int i = 0; i < str.length()-k; i++) {
                String stringPattern = str.substring(i, i+k);
                //System.out.println("@ index " +i + " > " +stringPattern + "");
                for (int j = i+k ; j < str.length()- k ; j++) {
                    String subString = str.substring(j, j + k);
                    if (stringPattern.equals(subString)) {
                        System.out.println(" >" + stringPattern + "-" + subString + ",     ");
                        if (!subString.equals("")){
                            mapB.put(subString, counterB);
                        }
                        counterB ++;
                    }
                }
            }
            show(pattern, mapB);
        }
    }

    private static void show(String a, HashMap<String, Integer> map) {
        int hash = 0;
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.print(entry.getKey() +":" +entry.getValue() +", ");
        }
        System.out.println("");
        for(int i=0; i<a.length(); i++){
            String key = String.valueOf(a.charAt(i));
            if (key != null && !key.equals(" ") ){
                System.out.print(key + ":" + map.get(key) + ", ");
                hash = hash + map.get(key);
            }
        }
        System.out.print("Hash Value " + "=" +hash);
    }


}
