package io.dev.v3.stream;

import io.dev.v3.stream.resources.Dish;

import java.util.*;
import java.util.stream.Collectors;

/**
 * To solve these you may need to go look at the stream java docs and look at what methods are available.
 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
 */
public class Streams101 {

    public static List<Dish> vegetarianDishes(List<Dish> menu) {

        List<Dish> vegetarianDishes = Optional.ofNullable(menu)
                .orElse(Collections.emptyList())
                .stream()
                .filter(dish -> dish.isVegetarian() == true)
                .collect(Collectors.toList());

        return vegetarianDishes;
    }

    public static List<String> threeHighCaloricDishNames(List<Dish> menu) {
        return Optional.ofNullable(menu)
                .orElse(Collections.emptyList())
                .stream()
                .filter(dish -> dish.getCalories() > 1000)
                .map(dish -> dish.getName())
                .limit(3)
                .collect(Collectors.toList());
    }

    public static long howManyDishes(List<Dish> menu) {

//        List<Dish> dishes = new ArrayList<>(menu);
//        dishes.removeIf(dish -> !dish.isVegetarian());



        return 0;
    }

    public static List<Integer> uniqueEvenNumbers(List<Integer> initial) {
        return null;
    }

    public static List<Integer> lengthOfDishNames(List<Dish> initial) {
        return null;
    }

    public static boolean isMenuVegetarianFriendly(List<Dish> initial) {
        return false;
    }

    public static boolean isEverythingUnder1000Calories(List<Dish> initial) {
        return false;
    }

    public static boolean isNothingOver1000Calories(List<Dish> initial) {
        return false;
    }

    public static Optional<Dish> vegetarianDish(List<Dish> initial) {
        return null;
    }

    public static Set<String> listCountriesOfOrigin(List<Dish> dishes) {
        return null;
    }
}
