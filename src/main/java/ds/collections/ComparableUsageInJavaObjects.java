package ds.collections;

import java.util.ArrayList;
import java.util.Collections;
/**
  Using Comparable Interface
  A comparable object is capable of comparing itself with another object.
  The class itself must implements the java.lang.Comparable interface to compare its instances.
*/

class SportsTeam implements Comparable<SportsTeam> {

    private String name;
    private int rating;
    private int year;

    public SportsTeam(String nm, int rt, int yr) {
        this.name = nm;
        this.rating = rt;
        this.year = yr;
    }

    // Used to sort SportsTeam by year or by rating
    public int compareTo(SportsTeam m) {
        return this.rating - m.rating;
    }

    // Used to sort SportsTeam by year or by year
    public int compareTo2(SportsTeam m) {
        return this.year - m.year;
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

    public static void main(String[] args) {
        ArrayList<SportsTeam> list = new ArrayList<SportsTeam>();
        list.add(new SportsTeam("Team 1", 3, 2015));
        list.add(new SportsTeam("Team 2", 7, 1977));
        list.add(new SportsTeam("Team 3", 8, 1980));
        list.add(new SportsTeam("Team 4", 4, 1983));

        Collections.sort(list);

        System.out.println("Teams after sorting ");
        for (SportsTeam sportsTeam : list) {
            System.out.println(sportsTeam.getName() + " " + sportsTeam.getRating() + " " + sportsTeam.getYear());
        }
    }

}
