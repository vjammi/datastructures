package scratchpad.comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Book implements Comparable<Book>{
    String name;
    String title;
    int year;
    String publication;

    public Book(String name, String title, int year, String publication) {
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

    public int compareTo1(Book that){
        // return this.getName().compareTo(that.getName());
        // or implement the compareTo yourself just like in the String class
        int len1 = this.getName().length();
        int len2 = that.getName().length();

        int lim = Math.min(len1, len2);

        char v1[] = this.getName().toCharArray();
        char v2[] = that.getName().toCharArray();

        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                System.out.println("> this.name:" +this.name +" that.name " +that.name + " c1 - c2: "+ (c1 - c2));
                return c1 - c2;
            }
            k++;
        }
        System.out.println("> this.name:" +this.name +" that.name " +that.name + " len1 - len2: "+ (len1 - len2));
        return len1 - len2;
    }

    public int compareTo(Book that){
        int dis = this.getName().compareTo(that.getName());
        System.out.println("> this.name:" +this.name +" that.name " +that.name + " dis: "+dis);
        return dis;
    }

}


class SortByYear implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        //return o1.getYear() - o2.getYear();
        if (o1.getYear() < o2.getYear()) return -1;
        if (o1.getYear() > o2.getYear()) return 1;

        return 0;
    }
    //@Override
    public int compare2(Book o1, Book o2) {
        return o1.getYear() - o2.getYear();
    }
}

class MainBook{

    public static void main(String[] args){
        ArrayList<Book> books = new ArrayList<Book>();

        books.add(new Book("AMPC Bible", "Bible", 1965, "AMPC"));
        books.add(new Book("NKJV Bible", "Bible", 1995, "Nelson"));
        books.add(new Book("DARBY Bible", "Bible", 1985, "Darby"));
        books.add(new Book("AMP Bible", "Bible", 1975, "AMP"));


        printList(books, "Before - using comparator");
        SortByYear SORT_BY_YEAR = new SortByYear();
        Collections.sort(books, SORT_BY_YEAR);
        printList(books, "Before");

        printList(books, "Before - using comparable");
        Collections.sort(books);
        printList(books, "After");
    }

    private static void printList(List<Book> books, String title) {
        System.out.println(title);
        for(Book book: books){
            System.out.println(book.getName() + ", " + book.getTitle() +", "+book.getYear() +", "+book.getPublication() );
        }
    }
}
