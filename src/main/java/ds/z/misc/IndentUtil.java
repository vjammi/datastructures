package ds.z.misc;

import java.util.ArrayList;
import java.util.List;

public class IndentUtil {

    public static String getIndent(int N) {
        String S = new String("");
        for (int i = 0; i < N; i++)
            S = S + "   > ";
        return S;
    }

    // int[]
    public static void showRoot(String indent, int[] input, int index, String choice, List<Integer> chosen){
        System.out.println(indent + asList(input) +" L(" +index +"-"+choice+") " +chosen);
    }
    public static void showLeft(String indent, int[] input, int index, int choice, List<Integer> chosen){
        System.out.println(indent +asList(input) +" L(" +index +"-"+choice+") " +chosen);
    }
    public static void show(String indent, int[] input, int index, int choice, List<Integer> chosen){
        System.out.println(indent +asList(input) +" N(" +index +"-"+choice+") " +chosen);
    }
    public static void showRight(String indent, int[] input, int index, int choice, List<Integer> chosen){
        System.out.println(indent +asList(input) +" R(" +index +"-"+choice+") " +chosen);
    }
    public static void showChosen(String indent, int[] input, int index, int choice, List<Integer> chosen){
        System.out.println(indent +asList(input) +" OUT(" +index +") " +chosen);
    }
    public static void showChosen(String indent, int[] input, List<Integer> chosen){
        System.out.println(indent +asList(input) +" OUT(" +") " +chosen);
    }
    public static void showReturn(String indent, int[] input, List<Integer> chosen){
        System.out.println(indent +asList(input) +" RET(" +") " +chosen);
    }

    // List<Integer>
    public static void showBeforeLeftI(String indent, List<Integer> input, int index, int choice, List<Integer> chosen){
        System.out.println(indent +input +" BL(" +index +"-"+choice+") " +chosen);
    }
    public static void showLeftI(String indent, List<Integer> input, int index, int choice, List<Integer> chosen){
        System.out.println(indent +input +" L(" +index +"-"+choice+") " +chosen);
    }
    public static void showAfterLeftI(String indent, List<Integer> input, int index, int choice, List<Integer> chosen){
        System.out.println(indent +input +" AL(" +index +"-"+choice+") " +chosen);
    }
    public static void showI(String indent, List<Integer> input, int index, int choice, List<Integer> chosen){
        System.out.println(indent +input +" N(" +index +"-"+choice+") " +chosen);
    }
    public static void showBeforeRightI(String indent, List<Integer> input, int index, int choice, List<Integer> chosen){
        System.out.println(indent +input +" BR(" +index +"-"+choice+") " +chosen);
    }
    public static void showRightI(String indent, List<Integer> input, int index, int choice, List<Integer> chosen){
        System.out.println(indent +input +" R(" +index +"-"+choice+") " +chosen);
    }
    public static void showAfterRightI(String indent, List<Integer> input, int index, int choice, List<Integer> chosen){
        System.out.println(indent +input +" AR(" +index +"-"+choice+") " +chosen);
    }
    public static void showChosenI(String indent, List<Integer> input, int index, int choice, List<Integer> chosen){
        System.out.println(indent +input +" OUT(" +index +") " +chosen);
    }
    public static void showChosenI(String indent, List<Integer> input, List<Integer> chosen){
        System.out.println(indent +input +" OUT(" +") " +chosen);
    }


    // List<String>
    public static void showLeft(String indent, List<String> input, int index, String choice, List<String> chosen){
        System.out.println(indent +input +" L(" +index +"-"+choice+") " +chosen);
    }
    public static void show(String indent, List<String> input, int index, String choice, List<String> chosen){
        System.out.println(indent +input +" N(" +index +"-"+choice+") " +chosen);
    }
    public static void showRight(String indent, List<String> input, int index, String choice, List<String> chosen){
        System.out.println(indent +input +" R(" +index +"-"+choice+") " +chosen);
    }
    public static void showChosen(String indent, List<String> input, List<String> chosen){
        System.out.println(indent +input +" OUT(" +") " +chosen);
    }
    public static void showChosen(String indent, String input, String chosen){
        System.out.println(indent +input +" OUT(" +") " +chosen);
    }

    public static List<Integer> asList(int[] candidates) {
        List<Integer> input = new ArrayList<>();
        for (int value : candidates) {
            input.add(value);
        }
        return input;
    }
}
