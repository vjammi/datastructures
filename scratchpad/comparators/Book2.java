package scratchpad.comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Princeton implementation with inner class that implements Comprator<Book2>. See also Student class
public class Book2 {

    public static Comparator<Book2> SORT_BY_YEAR = new SortByYear();

    // Similar to the above ???
    // public static List<String> books = new ArrayList<String>();

    private String name;
    private String title;
    private int year;
    private String publication;

    public Book2(String name, String title, int year, String publication) {
        this.name = name;
        this.title = title;
        this.year = year;
        this.publication = publication;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getPublication() {
        return publication;
    }

    private static class SortByYear implements Comparator<Book2> {
        @Override
        public int compare(Book2 o1, Book2 o2) {
            //return o1.getYear() - o2.getYear();
            if (o1.getYear() < o2.getYear()) return -1;
            if (o1.getYear() > o2.getYear()) return 1;
            return 0;
        }
    }

    private static void printList(List<Book2> books, String title) {
        System.out.println(title);
        for(Book2 book: books){
            System.out.println(book.getName() + ", " + book.getTitle() +", "+book.getYear() +", "+book.getPublication() );
        }
    }

    public static void main(String[] args){
        ArrayList<Book2> books = new ArrayList<Book2>();

        books.add(new Book2("AMPC Bible", "Bible", 1965, "AMPC"));
        books.add(new Book2("NKJV Bible", "Bible", 1995, "Nelson"));
        books.add(new Book2("DARBY Bible", "Bible", 1985, "Darby"));
        books.add(new Book2("AMP Bible", "Bible", 1975, "AMP"));


        printList(books, "Before - using comparator");
        SortByYear SORT_BY_YEAR = new SortByYear();
        Collections.sort(books, SORT_BY_YEAR);
        printList(books, "Before");

    }

}
