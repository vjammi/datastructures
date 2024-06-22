### Collections & Arrays Utility Classes 
```
Collections
- This class consists exclusively of static methods that operate on or return collections. 
- It contains polymorphic algorithms that operate on collections, "wrappers", which return a new collection backed by a specified collection, and a few other odds and ends.

Arrays
- This class contains various methods for manipulating arrays (such as sorting and searching). 
- This class also contains a static factory that allows arrays to be viewed as lists.
```

## Usage of common builtin functions

### Using Comparable Interface
Sort using a type’s natural enrichedTransaction.
Implements a method called ```compareTo(...)```. A comparable object is capable of comparing itself with another object.
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
        public int compareTo(SportsTeam that) {
            //return this.year - that.year;
            return this.rating - that.rating;
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
Sorting using alternate enrichedTransaction.
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
            return str1.compareTo(str2);                            // Natural sort enrichedTransaction - asc
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
    Collections.sort(list, new CompareByRating());
```
(4) You now have a sorted list based on the comparator that was passed.
```
     for (SportsTeam sportsTeam : list)
        System.out.println(sportsTeam.getName() + " " +sportsTeam.getRating() + " " + sportsTeam.getYear());
```

### Comparators
### IntegerComparator
```
    private static class IntegerComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer a, Integer b){
            return Integer.valueOf(a).compareTo(Integer.valueOf(b));
        }
    }

    public void sortArrayOfNumbers(int[] nums){
        Arrays.sort(nums);
        System.out.println(nums);
    }

    public void sortArrayOfNumbers(Integer[] nums){
        Arrays.sort(nums);
        System.out.println(nums);
    }

    public void sortArrayOfIntegers(List<Integer> list){
        Collections.sort(list);
        Collections.sort(list, new IntegerComparator());
        System.out.println(list);
    }

    
```
### StringComparator
```
    private static class StringComparator implements Comparator<String>{
        public int compare(String a, String b) {
            //return (b + a).compareTo(a + b);
            return Character.valueOf(b.charAt(0)).compareTo(a.charAt(0));
        }
    }

    private String sortStringTest(String string){
        System.out.println(string);
        char[] chars = string.toCharArray(); // method on input string to create a char array for input string.
        Arrays.sort(chars); // method to sort char array.
        String sortedString = new String(chars);  // Use String class constructor to create a sorted string from char array. String is immutable in java, hence in third step we have to create a new string.
        System.out.println(sortedString);
        return sortedString;
    }

    private void sortStringArraysTest() {
        String[] strArr = {"4", "81", "92", "12"};
        Arrays.sort(strArr, new StringComparator()); // (a, b) -> (b + a).compareTo(a + b)
        for (String str: strArr)
            System.out.print(str +" ");
    }
    
```
### Interval Comparator - int[]
```    
    class IntervalComparator implements Comparator<int[]>{
        public int compare(int[] a, int[] b){
            return a[0]-b[0];
        }
    }

    class IntArrComparator implements Comparator<int[]>{
        public int compare(int[] arr1, int[] arr2){
            return Integer.valueOf(arr1[0]).compareTo(arr2[0]);
        }
    }
    
    private void sortIntArraysTest() {
        int[][] intArrays = new int[][]{{5, 4}, {1,4}, {8,1}, {9,2}, {2,2}};
        //Arrays.sort(intArrays, new IntArrComparator());
        Arrays.sort(intArrays, new IntervalComparator());
        System.out.println(intArrays);
        for (int[] arr: intArrays)
            System.out.println(arr[0] +", "+arr[1] +"   ");
    }

```
### List Comparator
```

    private class ListComparator implements Comparator<Interval>{
        public int compare(Interval interval1, Interval interval2){
            return Integer.valueOf(interval1.a).compareTo(interval2.a);
        }
    }

    class Interval {
        int a;
        int b;
        public Interval(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    private void sortListTest() {
        List<Interval> list = new ArrayList();
        list.add(new Interval(5,10) );
        list.add(new Interval(4,7) );
        list.add(new Interval(1,7) );
        list.add(new Interval(3,5) );
        Collections.sort(list, new ListComparator());
        System.out.println(list.toString());
    }
```

REFERENCES
https://algs4.cs.princeton.edu/
https://www.geeksforgeeks.org/
