## Java Collections

## Comparable Vs Comparator
Comparable is meant for objects with natural ordering which means the object itself must know how it is to be ordered. For example a Date class.
Whereas, Comparator interface sorting is done through a separate class.
- Comparable interface compares “this” reference with the object specified and
- Comparator in Java compares two different class objects provided.
If any class implements Comparable interface in Java then
- Collection of that object either List or Array can be sorted automatically by using Collections.sort() or Arrays.sort() method and
- Objects will be sorted based on their natural order defined by CompareTo method.
Usage
- If sorting of objects needs to be based on natural order then we use Comparable Interface
- Whereas, if sorting needs to be done on attributes of different objects, then we use Comparator in Java.

### Using Comparable Interface
Sort using a type’s natural order. In other words Sort using natural order.
Implements a method called ```compareTo(...)```.
A comparable object is capable of comparing itself with another object.
The class itself must implement the java.lang.Comparable interface to compare its instances with other instances

(1) Build a list of SportsTeam
```
    ArrayList<SportsTeam> list = new ArrayList<SportsTeam>();
    list.add(new SportsTeam("Team 1", 3, 2015));
    list.add(new SportsTeam("Team 2", 7, 1977));
    list.add(new SportsTeam("Team 3", 8, 1980));
    list.add(new SportsTeam("Team 4", 4, 1983));
```
(2) SportsTeam implements the Comparable Interface and the associated compareTo abstract method of the Comparable interface.
```
    class SportsTeam implements Comparable<SportsTeam> {
        private String name;
        private int rating;
        private int year;

        // Used to sort SportsTeam by year or by rating
        public int compareTo(SportsTeam m) {
            //return this.year - m.year;
            return this.rating - m.rating;
        }
        ...
    }
```
(3) Call the sort(...) method of the Collections passing the list to be sorted.
```
    Collections.sort(list);
```
(4) You now have a sorted list based on the comparator that was passed.
```
     for (SportsTeam sportsTeam : list)
        System.out.println(sportsTeam.getName() + " " +sportsTeam.getRating() + " " + sportsTeam.getYear());
```

###  Using Comparator Interface
Sorting using alternate order.
Implements a method called ```compare(Key k1, Key k2) - Compares k1 and k2```. We are sorting based on different ordering.

Unlike Comparable, Comparator is external to the element type we are comparing. It’s a separate class. We create multiple separate classes (that implement Comparator) to compare by different members.
Collections class has a second sort() method and it takes Comparator. The sort() method invokes the compare() to sort objects.
To compare movies by Rating, we need to do 3 things :

Create a class that implements Comparator (and thus the compare() method that does the work previously done by compareTo()).
Make an instance of the Comparator class.
Call the overloaded sort() method, giving it both the list and the instance of the class that implements Comparator.

Sorting using a Comparator
(1) Build a list of SportsTeam
```
    ArrayList<SportsTeam> list = new ArrayList<SportsTeam>();
    list.add(new SportsTeam("Team 1", 3, 2015));
    list.add(new SportsTeam("Team 2", 7, 1977));
    list.add(new SportsTeam("Team 3", 8, 1980));
    list.add(new SportsTeam("Team 4", 4, 1983));
```
(2) Create an object of CompareByRating
```
     class CompareByRating implements Comparator<SportsTeam> {
         public int compare(SportsTeam m1, SportsTeam m2) {
             if (m1.getRating() < m2.getRating()) return -1;        // Lesser values take precedence here
             if (m1.getRating() > m2.getRating()) return 1;
             else return 0;
         }
     }
```
```
    class CompareByStringValue implements Comparator<String> {
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);                            // Natural sort order - asc
        }
    }

    class CompareBySomeLogic implements Comparator<String> {
        public int compare(String str1, String str2) {
            if (str1.charAt(5) < str2.charAt(5)) return -1;         // -1 Causes to take precedence asc or desc, here asc
            else if (str1.charAt(5) > str2.charAt(5)) return 1;
            else return 0;
        }
    }
```
(3) Call the sort(...) method of the Collections passing the list to be sorted and the associated comparator.
```
    Collections.sort(list, compareByRating);
```
(4) You now have a sorted list based on the comparator that was passed.
```
     for (SportsTeam sportsTeam : list)
        System.out.println(sportsTeam.getName() + " " +sportsTeam.getRating() + " " + sportsTeam.getYear());
```


REFERENCES
https://algs4.cs.princeton.edu/
https://www.geeksforgeeks.org/
