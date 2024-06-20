package dev.vjammi.ds.v2.z.misc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class TheLittleFlockHymnIndex {

    // C:\Users\Vijay Jammi\Desktop\TheLittleFlockHymnIndex1856Revised1881.txt

    public static void main(String[] args) {
        TheLittleFlockHymnIndex hymnIndex = new TheLittleFlockHymnIndex();
        hymnIndex.parseFile(new File("C:\\Users\\Vijay Jammi\\Desktop\\TheLittleFlockHymnIndex1856Revised1881.txt"));
    }

    private Map<Integer, String> parseFile(File file) {

        Map<Integer, String> map =  new HashMap();
        int[] hymnNums = new int[500];

        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            StringBuilder lineBuilder = new StringBuilder();
            int keyInd = 0;
            for (byte byt: fileBytes){
                char character = (char)byt;
                if (character == '\n'){
                    String line = lineBuilder.toString();
                    lineBuilder.delete(0, line.length());

                    if (!line.startsWith("HYMN") && !line.startsWith("INDEX") && !line.startsWith("APPENDIX") && !line.isEmpty()){
                        StringBuilder hymNum = new StringBuilder();
                        int hymnInx = 0;
                        for (int i=0; i<line.length(); i++){
                            char c = line.charAt(i);
                            if (c != ' '){
                                hymNum.append(c);
                            }else{
                                hymnInx=i;
                                int hymnNum = Integer.parseInt(hymNum.toString());
                                String hymnTitle = line.substring(hymnInx).trim();

                                map.put(hymnNum, hymnTitle);
                                hymnNums[keyInd] = hymnNum;
                                keyInd++;

                                break;
                            }
                        }
                    }
                }else{
                    lineBuilder.append(character);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Arrays.sort(hymnNums);
        for(int i=0; i<hymnNums.length; i++){
            Integer key = hymnNums[i];
            if (key!=0) {
                System.out.println(key +"; " +map.get(key).trim());
            }
        }

        return map;
    }
}
