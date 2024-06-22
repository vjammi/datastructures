package io.dev.v3.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CategoriesOfFunctionalInterfaces {

    public static void main(String[] args) {
        CategoriesOfFunctionalInterfaces obj = new CategoriesOfFunctionalInterfaces();
        obj.supplyCitiesUsingSupplierGet();
        obj.filterCitiesUsingPredicatesTest();
        obj.mapCitiesUsingFunctionApply();
        obj.printCitiesUsingConsumerAccept();
    }


    /**
     Supplier
     The Supplier Interface is a part of the java.util.function package.
     It represents a function that does not take in any argument but produces a value of type T.
     It contains only one method.
     T get();
     We created a city’s supplier for producing cities list and printing it.
     */
    public void supplyCitiesUsingSupplierGet() {
        Supplier<Integer> rand = () -> new Random().nextInt(10);
        rand.get();
    }

    /**
        Predicate
        A Predicate is a functional interface, which accepts an argument and returns a boolean.
        Usually, it is used to apply in a filter for a collection of objects.

            boolean test(T value);
            default Predicate<T> and(Predicate<? super T> other);
            default Predicate<T> negate();
            default Predicate<T> or(Predicate<? super T> other);
            static <T> Predicate<T> isEqual(Object targetRef);
            static <T> Predicate<T> not(Predicate<? super T> target);
    */
    public void filterCitiesUsingPredicatesTest() {
        List<String> cities = new ArrayList<>();
        cities.add("Seattle");
        cities.add("New York");
        cities.add("San Francisco");
        cities.add("Austin");

        Predicate<String> filterCity = city -> city.equals("Austin");

        System.out.println("Predicate " + filterCity.test("Austin"));
        cities.stream()
                .filter(filterCity)
                .forEach(System.out::println);
    }

    /**
        Function
        A Function is another in-build functional interface in java.util.function package,
        the function takes an input value and returns a value.
        The function interface has four methods, mostly function used in map feature of stream APIs.
            R apply(T var1);
            default <V> Function<V, R> compose(Function<V, T> before);
            default <V> Function<T, V> andThen(Function<R, V> after);
            static <T> Function<T, T> identity();
        We take an example, here we are passing city name as argument and transforming the city name to upper case.
    */
    public void mapCitiesUsingFunctionApply() {
        List<String> cities = new ArrayList<>();
        cities.add("Seattle");
        cities.add("New York");
        cities.add("San Francisco");
        cities.add("Austin");

        Function<String, String> transformToUpperCaseFn = city -> city.toUpperCase();

        System.out.println(transformToUpperCaseFn.apply("columbus"));

        cities.stream()
                .map(transformToUpperCaseFn)
                .forEach(System.out::println);
    }

    /**
     Consumer
     A Consumer is an in-built functional interface in the java.util.function package.
     We use consumers when we need to consume objects. i.e. forEach
     The consumer takes an input value and returns nothing.

     The consumer interface has two methods.
     void accept(T value);
     default Consumer<T> andThen(Consumer<? super T> after);
     */
    public void printCitiesUsingConsumerAccept() {
        List<String> cities = new ArrayList<>();
        cities.add("Seattle");
        cities.add("New York");
        cities.add("San Francisco");
        cities.add("Austin");

        Consumer<String> consumer = city -> System.out.println(city);
        consumer.accept("Austin");
        cities.forEach(consumer);
    }

}
