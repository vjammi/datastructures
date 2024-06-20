### Access Modifiers

Access level modifiers determine whether other classes can use a particular field or invoke a particular method. There
are two levels of access control:

At the top level—public, or package-private (no explicit modifier). At the member level—public, private, protected, or
package-private (no explicit modifier). A class may be declared with the modifier public, in which case that class is
visible to all classes everywhere. If a class has no modifier (the default, also known as package-private), it is
visible only within its own package (packages are named groups of related classes — you will learn about them in a later
lesson.)

At the member level, you can also use the public modifier or no modifier (package-private) just as with top-level
classes, and with the same meaning. For members, there are two additional access modifiers: private and protected. The
private modifier specifies that the member can only be accessed in its own class. The protected modifier specifies that
the member can only be accessed within its own package (as with package-private) and, in addition, by a subclass of its
class in another package.

https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html

### Final Access Modifier

The final keyword is used in different contexts. final is a non-access modifier applicable only to a variable, a method,
or a class.

The final access modifier is a modifier applicable to classes, methods, and variables. If we declare a parent class
method as final then we can’t override that method in the child class because its implementation is final if a class is
declared as final we can’t extend the functionality of that class i.e. we can’t create a child class for that class i.e.
inheritance is not possible for final classes. Every method present inside the final class is always final y default but
every variable present inside the final class need not be final.

The main advantage of the final keyword is that we can achieve security, and we can provide a unique implementation. But
the main disadvantage of the final keyword is we are missing key benefits of OOPs like
- Inheritance (Because of the final class),
- Polymorphism (Because of the final method)
hence if there are no specific requirements then it is not recommended using the final keyword.

```
Note: If the variable is a reference, this means that the variable cannot be re-bound to reference another object. But
the object that it references is still mutable, if it was originally mutable.
```

### Static Access Modifier

The static keyword in Java is mainly used for memory management. The static keyword in Java is used to share the same
variable or method of a given class. The users can apply static keywords with variables, methods, blocks, and nested
classes.

Static access modifier is an access modifier that is applicable for methods and variables but not for classes. We can
declare top-level class with a static modifier but we can declare the inner class as static (such types of inner classes
are known as static nested classes). In the case of instance variable for every object, a separate copy will be created
but in the case of static variable, a single copy will be created at class level and shared by every object of that
class.

Final Access Modifier Static Access Modifier This modifier is applicable to both outer and inner classes, variables,
methods, and blocks. This modifier is only applicable to inner classes, methods, and variables. It is not necessary to
initialize the final variable at the time of its declaration. It is necessary to initialize the static variable at the
time of its declaration. Final variable cannot be reinitialized. Static variables can be reinitialized. Final method
can’t be inherited. Static methods can only access the static members of the class and can only be called by other
static methods. Final class can’t be inherited by any class. The static class object can’t be created, and it only
contains static members only. Final keyword does not support any block for initialization of final variables. Static
block is used to initialize the static variables. Final local variables are allowed. Unlike C/C++, static local
variables are not allowed in Java.

### Final Vs Static
https://stackoverflow.com/questions/13772827/difference-between-static-and-final
https://www.geeksforgeeks.org/java-final-vs-static-access-modifier/