

### Why String is Immutable or Final in Java? Explained
The string is Immutable in Java because String objects are cached in the String pool. Since cached String literals are shared between multiple clients there is always a risk, 
where one client's action would affect all other clients. For example, if one client changes the value of the String "Test" to "TEST", all other clients will also see that 
value as explained in the first example. Since caching of String objects was important for performance reasons this risk was avoided by making the String class Immutable. 
At the same time, String was made final so that no one can compromise invariant of String class like Immutability, Caching, hashcode calculation, etc by extending and overriding 
behaviors. Another reason why the String class is immutable could die due to HashMap.

Read more: https://javarevisited.blogspot.com/2010/10/why-string-is-immutable-or-final-in-java.html#ixzz7oGr3q2bO


### How StringBuffer saves the Memory?
A String is implemented as an immutable object; that is when you initially decide to put something into a String object, the JVM allocates a fixed-width array of exactly the size of your initial value. This is then treated as a constant inside the JVM, which allows for very significant performance savings in the case where the String’s value is not changed.
However, if you decide to change the String’s contents in any way, what the JVM then essentially does is copy the contents of the original String into a temporary space, make your changes, then save those changes into a whole new memory array. Thus, making changes to a String’s value after initialization is a fairly expensive operation.
StringBuffer, on the other hand, is implemented as a dynamically – growable array inside the JVM, which means that any update operation can occur on the existing memory location, with new memory allocated only as needed.
However, there is no opportunity for the JVM to make optimizations around the StringBuffer, since its contents are assumed to be changeable at any instance.


### transient and volatile Keywords
Transient
“The transient keyword in Java is used to indicate that a field should not be serialized.” According to language specification: Variables may be marked transient to indicate that they are not part of the persistent state of an object. For example, you may have fields that are derived from other fields, and should only be done so programmatically, rather than having the state be persisted via serialization.
For example, in class BankPayment.java fields like principal and rate can be serialized while interest can be calculated any time even after de-serialization.
If we recall, each thread in java has its own local memory space as well and it does all read/write operations in its local memory. Once all operations are done, it write back the modified state of variable in the main memory from where all threads access this variable.
Normally, this is the default flow inside JVM. But, the volatile modifier tells the JVM that a thread accessing the variable must always reconcile its own private copy of the variable with the master copy in memory. It means every time thread want to read the state of variable, it must flush its local memory state and update the variable from main memory.

Volatile
volatile is most useful in lock-free algorithms. You mark the variable holding shared data as volatile when you are not using locking to access that variable and you want changes made by one thread to be visible in another, or you want to create a “happens-after” relation to ensure that computation is not re-ordered, again, to ensure changes become visible at the appropriate time.
The volatile should be used to safely publish immutable objects in a multi-threaded Environment. Declaring a field like public volatile ImmutableObject foo secures that all threads always see the currently available instance reference.

The Volatile keyword is used to mark the JVM and thread to read its value from primary memory and not utilize cached value present in the thread stack. It is used in concurrent programming in java.