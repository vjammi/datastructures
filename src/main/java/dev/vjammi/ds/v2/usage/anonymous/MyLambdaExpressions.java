package dev.vjammi.ds.v2.usage.anonymous;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 *
 **/
public class MyLambdaExpressions {

    public class Person {

        public enum Sex {
            MALE, FEMALE
        }

        String name;
        LocalDate birthday;
        Sex gender;
        String emailAddress;

        public int getAge() {
            // ...
            return 0;
        }

        public void printPerson() {
            // ...
        }
    }

    // Approach 1: Create Methods That Search for Members That Match One Characteristic
    public static void printPersonsOlderThan(List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    // Approach 2: Create More Generalized Search Methods
    public static void printPersonsWithinAgeRange(
            List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }

    // Approach 3: Specify Search Criteria Code in a Local Class
    interface CheckPerson {
        boolean test(Person p);
    }
    public static void printPersons(
            List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    class CheckPersonEligibleForSelectiveService implements CheckPerson {
        public boolean test(Person p) {
            return p.gender == Person.Sex.MALE &&
                    p.getAge() >= 18 &&
                    p.getAge() <= 25;
        }
    }

    public void option3(){
        //printPersons(roster, new CheckPersonEligibleForSelectiveService());
    }

    // Approach 4: Specify Search Criteria Code in an Anonymous Class
    //    public void option4(){
    //        printPersons(
    //                roster,
    //                new CheckPerson() {
    //                    public boolean test(Person p) {
    //                        return p.getGender() == Person.Sex.MALE
    //                                && p.getAge() >= 18
    //                                && p.getAge() <= 25;
    //                    }
    //                }
    //    }


    // Approach 5: Specify Search Criteria Code with a Lambda Expression
    //The CheckPerson interface is a functional interface. A functional interface is any interface that contains only one abstract method. (A functional interface may contain one or more default methods or static methods.) Because a functional interface contains only one abstract method, you can omit the name of that method when you implement it. To do this, instead of using an anonymous class expression, you use a lambda expression, which is highlighted in the following method invocation:
    //
    //printPersons(
    //    roster,
    //    (Person p) -> p.getGender() == Person.Sex.MALE
    //        && p.getAge() >= 18
    //        && p.getAge() <= 25
    //);
    // See Syntax of Lambda Expressions for information about how to define lambda expressions.
    // You can use a standard functional interface in place of the interface CheckPerson, which reduces even further the amount of code required.


    // Approach 7: Use Lambda Expressions Throughout Your Application
    // Reconsider the method printPersonsWithPredicate to see where else you could use lambda expressions:
    // public static void printPersonsWithPredicate(
    //    List<Person> roster, Predicate<Person> tester) {
    //    for (Person p : roster) {
    //        if (tester.test(p)) {
    //            p.printPerson();
    //        }
    //    }
    // }


    // public static void processPersons(
    //    List<Person> roster,
    //    Predicate<Person> tester,
    //    Consumer<Person> block) {
    //        for (Person p : roster) {
    //            if (tester.test(p)) {
    //                block.accept(p);
    //            }
    //        }
    //}

    //processPersons(
    //     roster,
    //     p -> p.getGender() == Person.Sex.MALE
    //         && p.getAge() >= 18
    //         && p.getAge() <= 25,
    //     p -> p.printPerson()
    //);

    public static void processPersonsWithFunction(
            List<Person> roster,
            Predicate<Person> tester,
            Function<Person, String> mapper,
            Consumer<String> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    // processPersonsWithFunction(
    //    roster,
    //    p -> p.getGender() == Person.Sex.MALE
    //        && p.getAge() >= 18
    //        && p.getAge() <= 25,
    //    p -> p.getEmailAddress(),
    //    email -> System.out.println(email)
    //);

    // This parameterized type contains a method that has the same return type and parameters as CheckPerson.boolean test(Person p).
    // Consequently, you can use Predicate<T> in place of CheckPerson as the following method demonstrates:
    interface Predicate<Person> {
        boolean test(Person t);
    }
    public static void printPersonsWithPredicate(
        List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

}

