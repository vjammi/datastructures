package io.dev.java.lambda.exceptions;

import java.util.function.BiConsumer;

public class ExceptionHandlingWithLambdas {

    public static void main(String[] args) {
        int key = 1;
        int[] nums = {1, 2, 3,4, 5, 6, 7, 8, 9};

        //process(key, nums);
        //process(key, nums, (k, v) -> System.out.println(" Key "+ k + " Num "+v));
        //process1(key, nums, (k, v) -> System.out.println(v/k));

        // Option 1
        //process1(key, nums, (k, v) -> {
        //    try {
        //        System.out.println(v / k);
        //    }catch (Exception e){
        //        System.err.println(e.getMessage());
        //    }
        //});

        //Option 2
        //process2(key, nums, (k, v) -> System.out.println(v/k));

        //Option 3
        //process1(key, nums, (k, v) -> {
        //    try {
        //        System.out.println(v / k);
        //    }catch (Exception e){
        //        System.err.println(e.getMessage());
        //    }
        //});

        //Option 4
        process4(key, nums, wrapper((k, v) -> System.out.println(v/k)) );
    }

    private static void process4(int key, int[] nums, BiConsumer<Integer, Integer> consumer) {
        for (int num: nums){
            consumer.accept(key, num);
        }
    }
    private static BiConsumer<Integer, Integer> wrapper(BiConsumer<Integer, Integer> consumer)  {
       return (key, value) -> {
            try {
                consumer.accept(key, value);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        };
    }

    private static void process3(int key, int[] nums, BiConsumer<Integer, Integer> consumer) {
        for (int num: nums){
            try {
                consumer.accept(key, num);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
    }

    private static void process2(int key, int[] nums, BiConsumer<Integer, Integer> consumer) {
        for (int num: nums){
            try {
                consumer.accept(key, num);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
    }

    private static void process1(int key, int[] nums, BiConsumer<Integer, Integer> consumer) {
        for (int num: nums){
            consumer.accept(key, num);
        }
    }
    private static void process(int key, int[] nums) {
        for (int num: nums){
            System.out.println(" Key "+ key + " Num "+num);
        }
    }

}
