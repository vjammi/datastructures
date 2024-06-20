package dev.vjammi.ds.v2.dev.functional.code.exercise_6.after;

import java.util.stream.Stream;

public class Sample {
  public static String findIn(String word) {
    return Stream.of(word.split(""))
      .filter(letter -> word.lastIndexOf(letter) > word.indexOf(letter))
      .findFirst()
      .orElse("");
  }

  public static void main(String[] args) {
    System.out.println("hi: " + findIn("hi"));    
    System.out.println("hello: " + findIn("hello"));    
    System.out.println("pursevere: " + findIn("pursevere"));    
    System.out.println("jaguar: " + findIn("jaguar"));    
    System.out.println("hellothere: " + findIn("hellothere"));    
  }
}
