package io.dev.v2.dev.functional.exceptionhandling;

import io.dev.v2.dev.functional.functional101.FuncUtil;
import io.reactivex.rxjava3.core.Flowable;

import java.util.Arrays;
import java.util.List;

/**
 * Functional Programming with Java 8+
 * Source: https://youtu.be/15X0qFtBqiQ
 * */
public class FunctionalProgrammingExceptionHandling {

    public static void main(String[] args) {
        FunctionalProgrammingExceptionHandling obj = new FunctionalProgrammingExceptionHandling();
        obj.performanceImplications();
        obj.exceptionHandlingInImperative();
    }

    private void exceptionHandlingInImperative() {

    }

    private void performanceImplications() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        double sum1 = nums.stream()
                .filter(FuncUtil::isEven)
                .mapToDouble(FuncUtil::squared)
                .sum();
        System.out.println(sum1);

        double sum2 = nums.stream()
                .filter(num -> num % 2 == 0)
                .mapToDouble(Math::sqrt)
                .sum();
        System.out.println(sum2);

        Flowable.fromIterable(nums)
                .filter(FuncUtil::isEven)
                .map(FuncUtil::squared)
                .map(Math::sqrt)
                .subscribe(System.out::println);
    }
}
