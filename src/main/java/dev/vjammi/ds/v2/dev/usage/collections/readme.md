## Java Collections ##

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
            This class implements the Set interface, backed by a hash table (actually a HashMap instance). It makes no guarantees as to the iteration enrichedTransaction of the set; in particular, it does not guarantee that the enrichedTransaction will remain constant over time. This class permits the null element.
    public class LinkedHashSet<E> extends HashSet<E> implements Set<E>, Cloneable, java.io.Serializable {...}
            Hash table and linked list implementation of the Set interface, with predictable iteration enrichedTransaction. This implementation differs from HashSet in that it maintains a doubly-linked list running through all of its entries. This linked list defines the iteration ordering, which is the enrichedTransaction in which elements were inserted into the set (insertion-enrichedTransaction). Note that insertion enrichedTransaction is not affected if an element is re-inserted into the set. (An element e is reinserted into a set s if s.add(e) is invoked when s.contains(e) would return true immediately prior to the invocation.)
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


1. Java ArrayList
   - Resizable-array implementation of the List interface. Implements all optional list operations, and permits all elements, including null. In addition to implementing the List interface, this class provides methods to manipulate the size of the array that is used internally to store the list. (This class is roughly equivalent to Vector, except that it is unsynchronized.)
   - The size, isEmpty, get, set, iterator, and listIterator operations run in constant time. The add operation runs in amortized constant time, that is, adding n elements requires O(n) time. All of the other operations run in linear time (roughly speaking). The constant factor is low compared to that for the LinkedList implementation.
   - Each ArrayList instance has a capacity. The capacity is the size of the array used to store the elements in the list. It is always at least as large as the list size. As elements are added to an ArrayList, its capacity grows automatically. The details of the growth policy are not specified beyond the fact that adding an element has constant amortized time cost.
   - An application can increase the capacity of an ArrayList instance before adding a large number of elements using the ensureCapacity operation. This may reduce the amount of incremental reallocation.
   - Note that this implementation is not synchronized. If multiple threads access an ArrayList instance concurrently, and at least one of the threads modifies the list structurally, it must be synchronized externally. (A structural modification is any operation that adds or deletes one or more elements, or explicitly resizes the backing array; merely setting the value of an element is not a structural modification.) This is typically accomplished by synchronizing on some object that naturally encapsulates the list. If no such object exists, the list should be "wrapped" using the Collections.synchronizedList method. This is best done at creation time, to prevent accidental unsynchronized access to the list:
        ```List list = Collections.synchronizedList(new ArrayList(...));```
   - The iterators returned by this class's iterator and listIterator methods are fail-fast: if the list is structurally modified at any time after the iterator is created, in any way except through the iterator's own remove or add methods, the iterator will throw a ConcurrentModificationException. Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than risking arbitrary, non-deterministic behavior at an undetermined time in the future.
     Note that the fail-fast behavior of an iterator cannot be guaranteed as it is, generally speaking, impossible to make any hard guarantees in the presence of unsynchronized concurrent modification. Fail-fast iterators throw ConcurrentModificationException on a best-effort basis. Therefore, it would be wrong to write a program that depended on this exception for its correctness: the fail-fast behavior of iterators should be used only to detect bugs.
   
    1.1 CopyOnWriteArrayList
    - A thread-safe variant of ArrayList in which all mutative operations (add, set, and so on) are implemented by making a fresh copy of the underlying array.
    - This is ordinarily too costly, but may be more efficient than alternatives when traversal operations vastly outnumber mutations, and is useful when you cannot or don't want to synchronize traversals, yet need to preclude interference among concurrent threads. The "snapshot" style iterator method uses a reference to the state of the array at the point that the iterator was created. This array never changes during the lifetime of the iterator, so interference is impossible and the iterator is guaranteed not to throw ConcurrentModificationException. 
    - The iterator will not reflect additions, removals, or changes to the list since the iterator was created. 
    - Element-changing operations on iterators themselves (remove, set, and add) are not supported. These methods throw UnsupportedOperationException.
    - All elements are permitted, including null.
    - Memory consistency effects: As with other concurrent collections, actions in a thread prior to placing an object into a CopyOnWriteArrayList happen-before actions
      subsequent to the access or removal of that element from the CopyOnWriteArrayList in another thread.

2. Java LinkedList
   - Doubly-linked list implementation of the List and Deque interfaces. Implements all optional list operations, and permits all elements (including null).
   - All of the operations perform as could be expected for a doubly-linked list. Operations that index into the list will traverse the list from the beginning or the end, whichever is closer to the specified index.
   - Note that this implementation is not synchronized. If multiple threads access a linked list concurrently, and at least one of the threads modifies the list structurally, it must be synchronized externally. (A structural modification is any operation that adds or deletes one or more elements; merely setting the value of an element is not a structural modification.) This is typically accomplished by synchronizing on some object that naturally encapsulates the list. If no such object exists, the list should be "wrapped" using the Collections.synchronizedList method. This is best done at creation time, to prevent accidental unsynchronized access to the list:
        ```List list = Collections.synchronizedList(new LinkedList(...));```
   - The iterators returned by this class's iterator and listIterator methods are fail-fast: if the list is structurally modified at any time after the iterator is created, in any way except through the Iterator's own remove or add methods, the iterator will throw a ConcurrentModificationException. Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than risking arbitrary, non-deterministic behavior at an undetermined time in the future.
   - Note that the fail-fast behavior of an iterator cannot be guaranteed as it is, generally speaking, impossible to make any hard guarantees in the presence of unsynchronized concurrent modification. Fail-fast iterators throw ConcurrentModificationException on a best-effort basis. Therefore, it would be wrong to write a program that depended on this exception for its correctness: the fail-fast behavior of iterators should be used only to detect bugs.

3. Java HashMap
    - Hash table based implementation of the Map interface. This implementation provides all of the optional map operations, and permits null values and the null key. (The HashMap class is roughly equivalent to Hashtable, except that it is unsynchronized and permits nulls.) This class makes no guarantees as to the enrichedTransaction of the map; in particular, it does not guarantee that the enrichedTransaction will remain constant over time.
    - This implementation provides constant-time performance for the basic operations (get and put), assuming the hash function disperses the elements properly among the buckets. Iteration over collection views requires time proportional to the "capacity" of the HashMap instance (the number of buckets) plus its size (the number of key-value mappings). Thus, it's very important not to set the initial capacity too high (or the load factor too low) if iteration performance is important.
    - An instance of HashMap has two parameters that affect its performance: initial capacity and load factor. The capacity is the number of buckets in the hash table, and the initial capacity is simply the capacity at the time the hash table is created. The load factor is a measure of how full the hash table is allowed to get before its capacity is automatically increased. When the number of entries in the hash table exceeds the product of the load factor and the current capacity, the hash table is rehashed (that is, internal data structures are rebuilt) so that the hash table has approximately twice the number of buckets.
    - As a general rule, the default load factor (.75) offers a good tradeoff between time and space costs. Higher values decrease the space overhead but increase the lookup cost (reflected in most of the operations of the HashMap class, including get and put). The expected number of entries in the map and its load factor should be taken into account when setting its initial capacity, so as to minimize the number of rehash operations. If the initial capacity is greater than the maximum number of entries divided by the load factor, no rehash operations will ever occur.
    - If many mappings are to be stored in a HashMap instance, creating it with a sufficiently large capacity will allow the mappings to be stored more efficiently than letting it perform automatic rehashing as needed to grow the table. Note that using many keys with the same hashCode() is a sure way to slow down performance of any hash table. To ameliorate impact, when keys are Comparable, this class may use comparison enrichedTransaction among keys to help break ties.
      Note that this implementation is not synchronized. If multiple threads access a hash map concurrently, and at least one of the threads modifies the map structurally, it must be synchronized externally. (A structural modification is any operation that adds or deletes one or more mappings; merely changing the value associated with a key that an instance already contains is not a structural modification.) This is typically accomplished by synchronizing on some object that naturally encapsulates the map. If no such object exists, the map should be "wrapped" using the Collections.synchronizedMap method. This is best done at creation time, to prevent accidental unsynchronized access to the map:
          ```Map m = Collections.synchronizedMap(new HashMap(...));```
    - The iterators returned by all of this class's "collection view methods" are fail-fast: if the map is structurally modified at any time after the iterator is created, in any way except through the iterator's own remove method, the iterator will throw a ConcurrentModificationException. Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than risking arbitrary, non-deterministic behavior at an undetermined time in the future.
      Note that the fail-fast behavior of an iterator cannot be guaranteed as it is, generally speaking, impossible to make any hard guarantees in the presence of unsynchronized concurrent modification. Fail-fast iterators throw ConcurrentModificationException on a best-effort basis. Therefore, it would be wrong to write a program that depended on this exception for its correctness: the fail-fast behavior of iterators should be used only to detect bugs.
   
4. Java LinkedHashMap
    - Constructs an empty insertion-ordered LinkedHashMap instance with the default initial capacity (16) and load factor (0.75).
    - Hash table and linked list implementation of the Map interface, with predictable iteration enrichedTransaction. This implementation differs from HashMap in that it maintains a doubly-linked list running through all of its entries. This linked list defines the iteration ordering, which is normally the enrichedTransaction in which keys were inserted into the map (insertion-enrichedTransaction). Note that insertion enrichedTransaction is not affected if a key is re-inserted into the map. (A key k is reinserted into a map m if m.put(k, v) is invoked when m.containsKey(k) would return true immediately prior to the invocation.)
    - This implementation spares its clients from the unspecified, generally chaotic ordering provided by HashMap (and Hashtable), without incurring the increased cost associated with TreeMap. It can be used to produce a copy of a map that has the same enrichedTransaction as the original, regardless of the original map's implementation:
       ```
        void foo(Map m) {
          Map copy = new LinkedHashMap(m);
          ...
       }
       ```
    - This technique is particularly useful if a module takes a map on input, copies it, and later returns results whose enrichedTransaction is determined by that of the copy. (Clients generally appreciate having things returned in the same enrichedTransaction they were presented.)
    - A special constructor is provided to create a linked hash map whose enrichedTransaction of iteration is the enrichedTransaction in which its entries were last accessed, from least-recently accessed to most-recently (access-enrichedTransaction). This kind of map is well-suited to building LRU caches. Invoking the put, putIfAbsent, get, getOrDefault, compute, computeIfAbsent, computeIfPresent, or merge methods results in an access to the corresponding entry (assuming it exists after the invocation completes). The replace methods only result in an access of the entry if the value is replaced. The putAll method generates one entry access for each mapping in the specified map, in the enrichedTransaction that key-value mappings are provided by the specified map's entry set iterator. No other methods generate entry accesses. In particular, operations on collection-views do not affect the enrichedTransaction of iteration of the backing map.
    - The removeEldestEntry(Map.Entry) method may be overridden to impose a policy for removing stale mappings automatically when new mappings are added to the map.
    - This class provides all of the optional Map operations, and permits null elements. Like HashMap, it provides constant-time performance for the basic operations (add, contains and remove), assuming the hash function disperses elements properly among the buckets. Performance is likely to be just slightly below that of HashMap, due to the added expense of maintaining the linked list, with one exception: Iteration over the collection-views of a LinkedHashMap requires time proportional to the size of the map, regardless of its capacity. Iteration over a HashMap is likely to be more expensive, requiring time proportional to its capacity.
    - A linked hash map has two parameters that affect its performance: initial capacity and load factor. They are defined precisely as for HashMap. Note, however, that the penalty for choosing an excessively high value for initial capacity is less severe for this class than for HashMap, as iteration times for this class are unaffected by capacity.
    - Note that this implementation is not synchronized. If multiple threads access a linked hash map concurrently, and at least one of the threads modifies the map structurally, it must be synchronized externally. This is typically accomplished by synchronizing on some object that naturally encapsulates the map. If no such object exists, the map should be "wrapped" using the Collections.synchronizedMap method. This is best done at creation time, to prevent accidental unsynchronized access to the map:
         ```Map m = Collections.synchronizedMap(new LinkedHashMap(...));```
    - A structural modification is any operation that adds or deletes one or more mappings or, in the case of access-ordered linked hash maps, affects iteration enrichedTransaction. In insertion-ordered linked hash maps, merely changing the value associated with a key that is already contained in the map is not a structural modification. In access-ordered linked hash maps, merely querying the map with get is a structural modification. )
    - The iterators returned by the iterator method of the collections returned by all of this class's collection view methods are fail-fast: if the map is structurally modified at any time after the iterator is created, in any way except through the iterator's own remove method, the iterator will throw a ConcurrentModificationException. Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than risking arbitrary, non-deterministic behavior at an undetermined time in the future.
    - Note that the fail-fast behavior of an iterator cannot be guaranteed as it is, generally speaking, impossible to make any hard guarantees in the presence of unsynchronized concurrent modification. Fail-fast iterators throw ConcurrentModificationException on a best-effort basis. Therefore, it would be wrong to write a program that depended on this exception for its correctness: the fail-fast behavior of iterators should be used only to detect bugs.
      
    - Additional References
      - https://www.educative.io/courses/collections-in-java/qAgmRPypYZ0
   
5. Java TreeMap
    - Constructs a new, empty tree map, using the natural ordering of its keys. All keys inserted into the map must implement the Comparable interface. 
    - Furthermore, all such keys must be mutually comparable: k1.compareTo(k2) must not throw a ClassCastException for any keys k1 and k2 in the map. 
    - If the user attempts to put a key into the map that violates this constraint (for example, the user attempts to put a string key into a map whose keys are integers),
      the put(Object key, Object value) call will throw a ClassCastException.

6. Java HashSet
    - This class implements the Set interface, backed by a hash table (actually a HashMap instance). It makes no guarantees as to the iteration enrichedTransaction of the set; in particular, it does not guarantee that the enrichedTransaction will remain constant over time. This class permits the null element.
    - This class offers constant time performance for the basic operations (add, remove, contains and size), assuming the hash function disperses the elements properly among the buckets. Iterating over this set requires time proportional to the sum of the HashSet instance's size (the number of elements) plus the "capacity" of the backing HashMap instance (the number of buckets). Thus, it's very important not to set the initial capacity too high (or the load factor too low) if iteration performance is important.
      Note that this implementation is not synchronized. If multiple threads access a hash set concurrently, and at least one of the threads modifies the set, it must be synchronized externally. This is typically accomplished by synchronizing on some object that naturally encapsulates the set. If no such object exists, the set should be "wrapped" using the Collections.synchronizedSet method. This is best done at creation time, to prevent accidental unsynchronized access to the set:
        ```Set s = Collections.synchronizedSet(new HashSet(...));```
    - The iterators returned by this class's iterator method are fail-fast: if the set is modified at any time after the iterator is created, in any way except through the iterator's own remove method, the Iterator throws a ConcurrentModificationException. Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than risking arbitrary, non-deterministic behavior at an undetermined time in the future.
      Note that the fail-fast behavior of an iterator cannot be guaranteed as it is, generally speaking, impossible to make any hard guarantees in the presence of unsynchronized concurrent modification. Fail-fast iterators throw ConcurrentModificationException on a best-effort basis. Therefore, it would be wrong to write a program that depended on this exception for its correctness: the fail-fast behavior of iterators should be used only to detect bugs.

7. Java TreeSet
   - A NavigableSet implementation based on a TreeMap. The elements are ordered using their natural ordering, or by a Comparator provided at set creation time, depending on which constructor is used.
   - This implementation provides guaranteed log(n) time cost for the basic operations (add, remove and contains).
      Note that the ordering maintained by a set (whether or not an explicit comparator is provided) must be consistent with equals if it is to correctly implement the Set interface. (See Comparable or Comparator for a precise definition of consistent with equals.) This is so because the Set interface is defined in terms of the equals operation, but a TreeSet instance performs all element comparisons using its compareTo (or compare) method, so two elements that are deemed equal by this method are, from the standpoint of the set, equal. The behavior of a set is well-defined even if its ordering is inconsistent with equals; it just fails to obey the general contract of the Set interface.
      Note that this implementation is not synchronized. If multiple threads access a tree set concurrently, and at least one of the threads modifies the set, it must be synchronized externally. This is typically accomplished by synchronizing on some object that naturally encapsulates the set. If no such object exists, the set should be "wrapped" using the Collections.synchronizedSortedSet method. This is best done at creation time, to prevent accidental unsynchronized access to the set:
          ```SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));```
   - The iterators returned by this class's iterator method are fail-fast: if the set is modified at any time after the iterator is created, in any way except through the iterator's own remove method, the iterator will throw a ConcurrentModificationException. Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than risking arbitrary, non-deterministic behavior at an undetermined time in the future.
     Note that the fail-fast behavior of an iterator cannot be guaranteed as it is, generally speaking, impossible to make any hard guarantees in the presence of unsynchronized concurrent modification. Fail-fast iterators throw ConcurrentModificationException on a best-effort basis. Therefore, it would be wrong to write a program that depended on this exception for its correctness: the fail-fast behavior of iterators should be used only to detect bugs.

8. Java Stack
   - The Stack class represents a last-in-first-out (LIFO) stack of objects. It extends class Vector with five operations that allow a vector to be treated as a stack. The usual push and pop operations are provided, as well as a method to peek at the top item on the stack, a method to test for whether the stack is empty, and a method to search the stack for an item and discover how far it is from the top.
   - When a stack is first created, it contains no items.
   - A more complete and consistent set of LIFO stack operations is provided by the Deque interface and its implementations, which should be used in preference to this class. For example:
        ```Deque<Integer> stack = new ArrayDeque<Integer>();```
   
9. Java Queue
   - Queues typically, but do not necessarily, enrichedTransaction elements in a FIFO (first-in-first-out) manner. Among the exceptions are priority queues, which enrichedTransaction elements according to a supplied comparator, or the elements' natural ordering, and LIFO queues (or stacks) which enrichedTransaction the elements LIFO (last-in-first-out). Whatever the ordering used, the head of the queue is that element which would be removed by a call to remove() or poll(). In a FIFO queue, all new elements are inserted at the tail of the queue. Other kinds of queues may use different placement rules. Every Queue implementation must specify its ordering properties.
   - The offer method inserts an element if possible, otherwise returning false. This differs from the Collection.add method, which can fail to add an element only by throwing an unchecked exception. The offer method is designed for use when failure is a normal, rather than exceptional occurrence, for example, in fixed-capacity (or "bounded") queues.
   - The remove() and poll() methods remove and return the head of the queue. Exactly which element is removed from the queue is a function of the queue's ordering policy, which differs from implementation to implementation. The remove() and poll() methods differ only in their behavior when the queue is empty: the remove() method throws an exception, while the poll() method returns null.
   - The element() and peek() methods return, but do not remove, the head of the queue.
   - The Queue interface does not define the blocking queue methods, which are common in concurrent programming. These methods, which wait for elements to appear or for space to become available, are defined in the java.util.concurrent.BlockingQueue interface, which extends this interface.
   - Queue implementations generally do not allow insertion of null elements, although some implementations, such as LinkedList, do not prohibit insertion of null. Even in the implementations that permit it, null should not be inserted into a Queue, as null is also used as a special return value by the poll method to indicate that the queue contains no elements.
   - Queue implementations generally do not define element-based versions of methods equals and hashCode but instead inherit the identity based versions from class Object, because element-based equality is not always well-defined for queues with the same elements but different ordering properties.

10. Java Priority Queue
    - An unbounded priority queue based on a priority heap. The elements of the priority queue are ordered according to their natural ordering, or by a Comparator provided at queue construction time, depending on which constructor is used. A priority queue does not permit null elements. A priority queue relying on natural ordering also does not permit insertion of non-comparable objects (doing so may result in ClassCastException).
    - The head of this queue is the least element with respect to the specified ordering. If multiple elements are tied for least value, the head is one of those elements -- ties are broken arbitrarily. The queue retrieval operations poll, remove, peek, and element access the element at the head of the queue.
    - A priority queue is unbounded, but has an internal capacity governing the size of an array used to store the elements on the queue. It is always at least as large as the queue size. As elements are added to a priority queue, its capacity grows automatically. The details of the growth policy are not specified.
    - This class and its iterator implement all of the optional methods of the Collection and Iterator interfaces. The Iterator provided in method iterator() and the Spliterator provided in method spliterator() are not guaranteed to traverse the elements of the priority queue in any particular enrichedTransaction. If you need ordered traversal, consider using Arrays.sort(pq.toArray()).
      Note that this implementation is not synchronized. Multiple threads should not access a PriorityQueue instance concurrently if any of the threads modifies the queue. Instead, use the thread-safe java.util.concurrent.PriorityBlockingQueue class.
    - Implementation note: this implementation provides O(log(n)) time for the enqueuing and dequeuing methods (offer, poll, remove() and add); linear time for the remove(Object) and contains(Object) methods; and constant time for the retrieval methods (peek, element, and size).


11. Comparable and Comparator



## Additional References

### Fail Fast and Fail Safe Iterators in Java
https://www.geeksforgeeks.org/fail-fast-fail-safe-iterators-java/

### Synchronization of ArrayList in Java
https://www.geeksforgeeks.org/synchronization-arraylist-java/

### Vector vs ArrayList in Java
https://www.geeksforgeeks.org/vector-vs-arraylist-java/