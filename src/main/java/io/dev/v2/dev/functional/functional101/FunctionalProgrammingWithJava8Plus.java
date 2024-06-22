package io.dev.v2.dev.functional.functional101;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Functional Programming with Java 8+
 * Source: https://youtu.be/15X0qFtBqiQ
 * */
public class FunctionalProgrammingWithJava8Plus {

    public static void main(String[] args) {
        FunctionalProgrammingWithJava8Plus obj = new FunctionalProgrammingWithJava8Plus();

        obj.simpleAnonymousFunction();
        obj.simpleLambdaFunction();
        obj.externalToInternalIteratorProgression();
        obj.functionalStyleOfWriting();
        obj.lambdasVsClosures();
        obj.lazyEvaluationAndFunctionComposition();
        obj.canNowAlsoParallizeTheseFunctionCompositions();
    }
    private void canNowAlsoParallizeTheseFunctionCompositions() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // .parallel()
        // Returns an equivalent stream that is parallel.
        // May return itself, either because the stream was already parallel,
        // or because the underlying stream state was modified to be parallel.
        nums.stream()
                .parallel()
                .filter( num -> num % 2 == 0 )
                .mapToDouble(Math::sqrt)
                .sum();

        // Parallel Stream
        // The default implementation creates a parallel Stream from the collection's Spliterator.
        nums.parallelStream()
                .filter( num -> num % 2 == 0 )
                .mapToDouble(Math::sqrt)
                .sum();
    }

    private void lazyEvaluationAndFunctionComposition() {
        // Lazy Evaluation or Deferred Execution
        // What polymorphism is to OO programming, lazy evaluation is to Functional programming.
        //        As Polymorphism is interdependent on abstraction and encapsulation. Lazy evaluation cannot be possible without immutability.
        //        Since it is a pure function, since it does not modify anything or depend on anything - so we can run it now or later or never run it.
        //        Impure fns run eagerly.
        //        So we cannot evaluate lazily without being immutable.
        //        Composing with lambda expressions
        // Since we are now able to evaluate a func lazily, we can now do func pipelining or composition.
        // Until we hit the terminal operator, intermediate operators are not executed.

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        nums.stream()
                .filter( num -> num % 2 == 0 )
                .mapToDouble(Math::sqrt)
                .sum();
    }

    private void lambdasVsClosures() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Lambda - immutable
        // Satisfies the rules of pure fn -
        //  1. Does not change any variable
        //  2. Does not depend on something that changes
        nums.stream()
                .map(element -> element * 2)
                .forEach(System.out::println);

        // Closure - This lambda has to close-over its defining scope looking for the variable factor to bind to
        // Closures carry state, but they need to be immutable
        int factor = 2; // In java 8 you do not have to explicitly declare local variables final
        nums.stream()
                .map(element -> element * 2 * factor)
                .forEach(System.out::println);

    }

    /**
     * Given an array of integers, find total sum, of the squared of the given even numbers
     * */
    private void functionalStyleOfWriting() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Notice here we are mutating the variables
        double sum1 = 0;
        for (int num: nums){
            //if (num % 2 == 0) sum1 += num;
            //if (num % 2 == 0) sum1 = sum1 + (num * num);
            // Using Math.pow() method to raise a number to a/any power
            if (num % 2 == 0)
                sum1 = sum1 + Math.pow(num, 2); //
        }
        System.out.println(sum1);

        // You tell what to do, instead of howe to do it
        // We do not mutate the data/variables, but transform the data
        // The code is more expressive / readable
        // The functions used here are pure - they do not depend on anything that is mutable, they do not change the world around them ???
        // You can now along with objects, can pass functions too
        // You are not able to compose functions in imperative style, which causes mutability.
        // But functional allows you to compose the functions by transforming the data (from one state to the other ???)

        double sum2 = nums.stream()
                .filter(num -> num % 2 == 0)
                //.map(num -> (int) num * num)
                .mapToDouble(num -> num * num)
                .sum();
        System.out.println(sum2);

    }

    private void externalToInternalIteratorProgression() {
        // External Iterator - traditional iterator - for loop
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (int i=0; i<nums.size(); i++){
            System.out.print(nums.get(i) +" ");
        }
        System.out.println("");
        // External Iterator - ???
        for (Integer num: nums){
            System.out.print(num +" ");

        }
        System.out.println("");

        // Internal Iterator - Anonymous fn
        nums.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer element) {
                System.out.println(element);
            }
        });

        // functional Interfaces - where ever there is a single abstract method interface expected - callable, runnable, we can now pass a lambda function.
        // Internal Iterator - Short hand 1 - for
        nums.forEach((Integer element) -> System.out.println(element));

        // Internal Iterator - Short hand 2 - With type inference with static typing, inferred at compile time. NOT a dynamic type inference.
        nums.forEach( (element) -> System.out.println(element));

        // Internal Iterator - Short hand 3 - With type inference - since we have only 1 parameter, we can now remove the parenthesis too
        nums.forEach(element -> System.out.println(element));

        // Internal Iterator - Short hand 4 - Method references
        // nums.forEach(element -> System.out.println(element));
        // We will remove the parameter element from both locations [???]
        // We will also remove the arrow ->
        // We will also replace the dot with ::
        nums.forEach(System.out::println);

        // So this style of programming is essentially a
        // 1. Declarative style of writing code and
        // 2. Functional style too
        // You tell what to do and pick an underlying function that knows how to do.

        // Imperative vs Declarative Programming styles
        // --------------------------------------
        // Imperative    vs          Declarative
        // --------------------------------------
        // How                     What
        // Mutate                  Transform
        // Side-effect             Pure [only effect]
        // Pass Objects            Not only pass objects but functions too
        // Hard to Compose         Functional Composition
    }


    private void simpleAnonymousFunction() {
        System.out.println("In simpleAnonymousFunction - starting a thread...");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I am running in another thread..."+Thread.currentThread().getId());
            }
        });
        thread.start();
        System.out.println("In simpleAnonymousFunction, exiting ...");
    }

    private void simpleLambdaFunction() {
        System.out.println("In simpleLambdaFunction - starting a thread...");
        Thread thread = new Thread(() -> System.out.println("I am running in another thread..."+Thread.currentThread().getId()) );
        thread.start();
        System.out.println("In simpleLambdaFunction, exiting ...");
    }
}
