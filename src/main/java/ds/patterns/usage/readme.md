## Java Collection

### Java Collection Interface and Implementation Classes
```
- The root interface in the collection hierarchy. 
- A collection represents a group of objects, known as its elements. 
- Some collections allow duplicate elements and others do not. 
- Some are ordered and others unordered. 
- The JDK does not provide any direct implementations of this interface: it provides implementations of more specific subinterfaces like Set and List. 
- This interface is typically used to pass collections around and manipulate them where maximum generality is desired.


        
Interfaces                                                         Iterable
                                                                      | [ext]
                                                                  Collection
                                                                      | [ext]
                                    ------------------------------------------------------------                                  
                                   | [impl]           | [ext]         | [ext]                   | [ext]          
                                   |                 List            Set                      Queue            
                                   |                  |               |                         |
                                   |                  |           SortedSet                  Dequeue      

Abstract Classes                   | [implements]     |               |                         
                              AbstractCollection      |               |
                                   | [extends]        | [impl]        |
                                    -----------------                 |
                                           |                          |
                              ------AbstractList-----             AbstractSet
                              | [extends]            |            |         |                   | [ext]
                    AbstractSequentialList           |            |         |                   |
                    
Implementations               |                      |            |         |                   |               
  Classes                     |                   ArrayList    HashSet      |              PriorityQueue               
                          LinkedList               Vector         |         |               ArrayQueue
                                                   Stack    LinkedHashSet TreeSet           LinkedList
                                          


Interfaces
    public interface Iterable<T> {...}
    public interface Collection<E> extends Iterable<E> {...}
    
    public interface List<E> extends Collection<E> {...}
    public interface Set<E> extends Collection<E> {...}
    public interface Queue<E> extends Collection<E> {...}

Abstract Classes
    public abstract class AbstractCollection<E> implements Collection<E> { }
    public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {...}
    public abstract class AbstractSequentialList<E> extends AbstractList<E> {...}
    public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E> {...}
    public abstract class AbstractQueue<E> extends AbstractCollection<E> implements Queue<E> {...}

Implementations
    public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable {...}
    public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {...}
    public class Vector<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable{...}
    public class Stack<E> extends Vector<E> {...}
    
    public class HashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, java.io.Serializable{...}
            This class implements the Set interface, backed by a hash table (actually a HashMap instance). It makes no guarantees as to the iteration order of the set; in particular, it does not guarantee that the order will remain constant over time. This class permits the null element.
    public class LinkedHashSet<E> extends HashSet<E> implements Set<E>, Cloneable, java.io.Serializable {...}
            Hash table and linked list implementation of the Set interface, with predictable iteration order. This implementation differs from HashSet in that it maintains a doubly-linked list running through all of its entries. This linked list defines the iteration ordering, which is the order in which elements were inserted into the set (insertion-order). Note that insertion order is not affected if an element is re-inserted into the set. (An element e is reinserted into a set s if s.add(e) is invoked when s.contains(e) would return true immediately prior to the invocation.)
    public class TreeSet<E> extends AbstractSet<E> implements NavigableSet<E>, Cloneable, java.io.Serializable {...}
            A NavigableSet implementation based on a TreeMap. The elements are ordered using their natural ordering, or by a Comparator provided at set creation time, depending on which constructor is used.

See also
    Set, List, Map, SortedSet, SortedMap, HashSet, TreeSet, ArrayList, LinkedList, Vector, Collections, Arrays, AbstractCollection, 
    ConcurrentSkipListSet, ConcurrentSkipListMap,
```
### Java Map Interface and Implementation Classes
```                                                               
Interfaces
                                        Map
                                         | 
                               --------  | -----------------
                    [extends] |          |                  | [implements]
                          SortedMap      |              AbstractMap
                    [extends] |          |                  |
                          NavigableMap   |                  |
Impl. Classes                            | [implements]     | [extends]
                                            ---------------
                                                  |
                                               HashMap              
                                               TreeMap
                                           ConcurrentHashMap                                    
                                               HashTable

public interface Map<K, V> {...}
public interface SortedMap<K,V> extends Map<K,V> {...}
public abstract class AbstractMap<K,V> implements Map<K,V> {...}
public interface NavigableMap<K,V>  extends SortedMap<K,V> {...}
                            
public class HashMap<K,V>   extends AbstractMap<K,V>    
                            implements Map<K,V>, Cloneable, Serializable {...}                            
public class TreeMap<K,V>   extends AbstractMap<K,V>
                            implements NavigableMap<K,V>, Cloneable, java.io.Serializable {...}
public class ConcurrentHashMap<K,V> extends AbstractMap<K,V>
                            implements ConcurrentMap<K,V>, Serializable {...}
public class Hashtable<K,V> extends Dictionary<K,V>
                            implements Map<K,V>, Cloneable, java.io.Serializable {
                                                                                                        
```

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
Sort using a type’s natural order.
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

## ASCII TABLE
ASCII characters are from 0 to 127
```
Dec  Char                           Dec  Char     Dec  Char     Dec  Char
---------                           ---------     ---------     ----------
  0  NUL (null)                      32  SPACE     64  @         96  `
  1  SOH (start of heading)          33  !         65  A         97  a
  2  STX (start of text)             34  "         66  B         98  b
  3  ETX (end of text)               35  #         67  C         99  c
  4  EOT (end of transmission)       36  $         68  D        100  d
  5  ENQ (enquiry)                   37  %         69  E        101  e
  6  ACK (acknowledge)               38  &         70  F        102  f
  7  BEL (bell)                      39  '         71  G        103  g
  8  BS  (backspace)                 40  (         72  H        104  h
  9  TAB (horizontal tab)            41  )         73  I        105  i
 10  LF  (NL line feed, new line)    42  *         74  J        106  j
 11  VT  (vertical tab)              43  +         75  K        107  k
 12  FF  (NP form feed, new page)    44  ,         76  L        108  l
 13  CR  (carriage return)           45  -         77  M        109  m
 14  SO  (shift out)                 46  .         78  N        110  n
 15  SI  (shift in)                  47  /         79  O        111  o
 16  DLE (data link escape)          48  0         80  P        112  p
 17  DC1 (device control 1)          49  1         81  Q        113  q
 18  DC2 (device control 2)          50  2         82  R        114  r
 19  DC3 (device control 3)          51  3         83  S        115  s
 20  DC4 (device control 4)          52  4         84  T        116  t
 21  NAK (negative acknowledge)      53  5         85  U        117  u
 22  SYN (synchronous idle)          54  6         86  V        118  v
 23  ETB (end of trans. block)       55  7         87  W        119  w
 24  CAN (cancel)                    56  8         88  X        120  x
 25  EM  (end of medium)             57  9         89  Y        121  y
 26  SUB (substitute)                58  :         90  Z        122  z
 27  ESC (escape)                    59  ;         91  [        123  {
 28  FS  (file separator)            60  <         92  \        124  |
 29  GS  (group separator)           61  =         93  ]        125  }
 30  RS  (record separator)          62  >         94  ^        126  ~
 31  US  (unit separator)            63  ?         95  _        127  DEL
```

## UNICODE / UTF-8






REFERENCES
https://algs4.cs.princeton.edu/
https://www.geeksforgeeks.org/
