package io.dev.java.functional;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalImpl {

    public static void main(String[] args) {
        OptionalImpl obj = new OptionalImpl();
//        obj.concept();
//        obj.optionalWrongImpl();
//        obj.optionalCorrectImpl();

        //String treat = obj.purchaseTreat("vj");
        //System.out.println(treat);

        String vehicle = obj.choiceOfCar("vj");
        System.out.println(vehicle);
    }

    private void concept() {
        // Cannot be null
        // You can create an Optional using the static method Optional.of(Object).
        // This creates an Optional out of the value passed in as the parameter -
        // The Optional contains an Integer with a value of 1 .
        // We specify the type of the optional using diamond  notation ( <type> )
        Optional<Integer> container1 = Optional.of(1);

        // Can be null
        // In this case, ofNullable is completely happy accepting either a null or non-null value.
        // We commonly use optionals to contain a value that sometimes might be null.
        // Optional.of can only be used for non-null values.
        // For nullable situations, Java provides the Optional.ofNullable(Object) method.
        Optional<Integer> container2 = Optional.ofNullable(2);

        Optional<String> bad = null; // This is still null... we need to instantiate the Optional
        Optional<String> good = Optional.ofNullable(null); // Good! Null contained within an instanc

        // Optional.orElse()
        // With a value
        Optional<String> value = Optional.ofNullable("value!"); // Outputs "value!"
        String val = value.orElse("else!"); // Outputs "else!"
        System.out.println(val);

        //In the method below, we attempt to pick a birthday dessert for a user. We assume most people want cake,
        //but if the user doesn't have a listed dessert choice, we'll give them a boring (but safe!) fruit basket.
        //Remember, this module is all about being a cool developer and writing cool code. Unsurprisingly, optionals


        // Without a value
        Optional<String> value2 = Optional.ofNullable(null);
        System.out.println(value2.orElse("else!"));
        // Outputs "else!"
    }

    //    The whole point of this data type is to provide a safe way to pass empty values around. Always wrap your
    //    nal value when using optionals, even if it's null!
    //    The example below returns a String message about the number of items in a shopper's cart at checkout.
    //    getNumberOfItemsInCart() returns an Optional<Integer> . isPresent() is used to see if there are items in
    //    the cart. If there are items, get() is used to retrieve the value stored in the Optional and populate the
    //    message If the cart is empty a different message is returned This looks a lot like a regular ll check
    public Optional<String> optionalWrongImpl() {
        Optional<String> item = null;

        // Some code

        // Wrong move, this should not be null
        return item;
    }
    public Optional<String> optionalCorrectImpl() {
        String item = null;
        // Some code

        // Great move
        return Optional.ofNullable(item);
    }

    public String purchaseTreat(String user) {
        Optional<String> choice = choiceOfDesert(user);
        return choice.orElse("ice cream");
    }

    private Optional<String> choiceOfDesert(String user) {
        return Optional.ofNullable(null);
        //return Optional.of("cake");
    }

    private String choiceOfCar(String user) {
        Optional<String> vehicle = getChoiceOfVehicle();
        return vehicle
                .filter(car -> isHonda(car))
                .orElse("Toyota" );
    }

    private String getChoiceOfCar(String user) {
        Optional<String> vehicle = getChoiceOfVehicle();
        Supplier<String> supplier = ()  -> getCarElse("");
        String  choice = vehicle
                                    .map(car -> getCarIf(car))
                                    .orElseGet(supplier);
        return choice;
    }

    private String getCarElse(Object car) {
        return null;
    }

    private String getCarIf(String car) {
        return null;
    }

    private static boolean isHonda(String id) {
        boolean honda = id.equals("honda");
        return honda;
    }

    private static Optional<String> getChoiceOfVehicle() {
        //return Optional.ofNullable(null);
        return Optional.of("honda");
    }

}
