package dev.vjammi.ds.v2.dev.functional.code.exercise_6.before;

public class Sample {
  public static String findIn(String word) {
    char[] letters = word.toCharArray();

    for(char candidate: letters) {
      int count = 0;

      for(char letter: letters) {
        if(candidate == letter) {
          count++;
        }
      }

      if(count > 1) {
        return String.valueOf(candidate);
      }
    }

    return "";
  }

  public static void main(String[] args) {
    System.out.println("hi: " + findIn("hi"));    
    System.out.println("hello: " + findIn("hello"));    
    System.out.println("pursevere: " + findIn("pursevere"));    
    System.out.println("jaguar: " + findIn("jaguar"));    
    System.out.println("hellothere: " + findIn("hellothere"));    
  }
}
