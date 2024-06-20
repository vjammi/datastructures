package dev.vjammi.ds.v2.dev.functional.code.exercise_2.after;

import java.math.*;
import java.util.stream.*;

public class Sample {
  public static BigInteger factorial(long upTo) {
    return LongStream.rangeClosed(1, upTo)
      .mapToObj(BigInteger::valueOf)
      .reduce(BigInteger.ONE, BigInteger::multiply);
  }

  public static void main(String[] args) {
    System.out.println(factorial(0));
    System.out.println(factorial(1));
    System.out.println(factorial(2));
    System.out.println(factorial(5));
    System.out.println(factorial(10));
    System.out.println(factorial(30));
  }
}
