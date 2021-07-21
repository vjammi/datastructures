package ds.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Unlike Comparable, Comparator is external to the element type we are comparing. It’s a separate class. We create multiple separate classes (that implement Comparator) to compare by different members.
 * Collections class has a second sort() method and it takes Comparator. The sort() method invokes the compare() to sort objects.
 * To compare movies by Rating, we need to do 3 things :
 * <p>
 * <p>
 * Create a class that implements Comparator (and thus the compare() method that does the work previously done by compareTo()).
 * Make an instance of the Comparator class.
 * Call the overloaded sort() method, giving it both the list and the instance of the class that implements Comparator.
 *
 *
 *         Sort based on the Comparator
 *          (1) Build a list of SportsTeam
 *          (2) Create an object of CompareByRating
 *          (3) Call Collections.sort(...), passing the list to be sorted and the associated comparator
 *          (4) You now have a sorted list based on the comparator you have passed
 *
 */

public class ComparatorUsageInJavaObjects {

   // A class 'SportsTeam' that implements Comparable
    class SportsTeam implements Comparable<SportsTeam> {
        private double rating;
        private String name;
        private int year;

        // Used to sort movies by year
        public int compareTo(SportsTeam m) {
            return this.year - m.year;
        }

        // Constructor
        public SportsTeam(String nm, double rt, int yr) {
            this.name = nm;
            this.rating = rt;
            this.year = yr;
        }

        // Getter methods for accessing private data
        public double getRating() {
            return rating;
        }

        public String getName() {
            return name;
        }

        public int getYear() {
            return year;
        }
    }

    // Class to compare SportsTeam by ratings
    class CompareByRating implements Comparator<SportsTeam> {
        public int compare(SportsTeam team1, SportsTeam team2) {
            if (team1.getRating() < team2.getRating()) return -1;
            if (team1.getRating() > team2.getRating()) return 1;
            else return 0;
        }
    }

    // Class to compare SportsTeam by name
    class CompareByName implements Comparator<SportsTeam> {
        public int compare(SportsTeam m1, SportsTeam m2) {
            return m1.getName().compareTo(m2.getName());
        }
    }

    // Class to compare String by its value
    class CompareByStringValue implements Comparator<String> {
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }

    // Class to compare String by its value
    class CompareByStringValueAt implements Comparator<String> {
        public int compare(String str1, String str2) {
            if (str1.charAt(5) < str2.charAt(5)) return -1;
            else if (str1.charAt(5) > str2.charAt(5)) return 1;
            else return 0;
        }
    }

    private void testComparatorSportsTeam() {

        ArrayList<SportsTeam> list = new ArrayList<SportsTeam>();
        list.add(new SportsTeam("Team 1", 3, 2015));
        list.add(new SportsTeam("Team 2", 7, 1977));
        list.add(new SportsTeam("Team 3", 8, 1980));
        list.add(new SportsTeam("Team 4", 4, 1983));

        System.out.println("Sorted by rating");
        CompareByRating compareByRating = new CompareByRating();
        Collections.sort(list, compareByRating);
        for (SportsTeam sportsTeam : list)
            System.out.println(sportsTeam.getRating() + " " +sportsTeam.getName() + " " +sportsTeam.getYear());


        System.out.println("\nSorted by name");
        CompareByName compareByName = new CompareByName();
        Collections.sort(list, compareByName);
        for (SportsTeam sportsTeam : list)
            System.out.println(sportsTeam.getName() + " " +sportsTeam.getRating() + " " + sportsTeam.getYear());

        // Uses Comparable to sort by year
        System.out.println("\nSorted by year");
        Collections.sort(list);
        for (SportsTeam sportsTeam : list)
            System.out.println(sportsTeam.getYear() + " " +sportsTeam.getRating() + " " +sportsTeam.getName() + " ");
    }

    private void testComparatorJavaString() {

        ArrayList<String> list = new ArrayList<>();
        list.add(new String("Team 442"));
        list.add(new String("Team 100"));
        list.add(new String("Team 211"));
        list.add(new String("Team 322"));

        System.out.println("\nSorted by String Value");
        CompareByStringValue compareByStringValue = new CompareByStringValue();
        Collections.sort(list, compareByStringValue);
        for (String str : list)
            System.out.println(str +" ");

        System.out.println("\nSorted by String Value at a certain index");
        CompareByStringValueAt compareByStringValueAt = new CompareByStringValueAt();
        Collections.sort(list, compareByStringValueAt);
        for (String str : list)
            System.out.println(str +" ");
    }

    public static void main(String[] args) {
        ComparatorUsageInJavaObjects obj = new ComparatorUsageInJavaObjects();
        obj.testComparatorSportsTeam();
        obj.testComparatorJavaString();
    }
}
