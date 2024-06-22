package io.dev.v2.dev.functional.code.exercise_2.before;

import java.math.*;

public class Sample {
  public static BigInteger factorial(long upTo) {
    BigInteger result = BigInteger.ONE;

    for(int i = 1; i <= upTo; i++) {
      result = result.multiply(BigInteger.valueOf(i));
    }

    return result;
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
