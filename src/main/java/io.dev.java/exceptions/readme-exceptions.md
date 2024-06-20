### Exception Handling in Java

https://docs.oracle.com/javase/tutorial/essential/exceptions/index.html

#### The Three Kinds of Exceptions

1. Checked exception [i.e. java.io.FileNotFoundException]
   The first kind of exception is the checked exception. These are exceptional conditions that a well-written
   application
   should anticipate and recover from. For example, suppose an application prompts a user for an input file name, then
   opens the file by passing the name to the constructor for java.io.FileReader. Normally, the user provides the name of
   an
   existing, readable file, so the construction of the FileReader object succeeds, and the execution of the application
   proceeds normally. But sometimes the user supplies the name of a nonexistent file, and the constructor throws
   java.io.FileNotFoundException. A well-written program will catch this exception and notify the user of the mistake,
   possibly prompting for a corrected file name.

Checked exceptions are subject to the Catch or Specify Requirement. All exceptions are checked exceptions, except for
those indicated by Error, RuntimeException, and their subclasses.

2. Error [i.e. java.io.IOError]
   The second kind of exception is the error. These are exceptional conditions that are external to the application, and
   that the application usually cannot anticipate or recover from. For example, suppose that an application successfully
   opens a file for input, but is unable to read the file because of a hardware or system malfunction. The unsuccessful
   read will throw java.io.IOError. An application might choose to catch this exception, in order to notify the user of
   the
   problem — but it also might make sense for the program to print a stack trace and exit.

Errors are not subject to the Catch or Specify Requirement. Errors are those exceptions indicated by Error and its
subclasses.

3. Runtime exception [i.e. NullPointerException]
   The third kind of exception is the runtime exception. These are exceptional conditions that are internal to the
   application, and that the application usually cannot anticipate or recover from. These usually indicate programming
   bugs, such as logic errors or improper use of an API. For example, consider the application described previously that
   passes a file name to the constructor for FileReader. If a logic error causes a null to be passed to the constructor,
   the constructor will throw NullPointerException. The application can catch this exception, but it probably makes more
   sense to eliminate the bug that caused the exception to occur.

Runtime exceptions are not subject to the Catch or Specify Requirement. Runtime exceptions are those indicated by
RuntimeException and its subclasses.

Errors and runtime exceptions are collectively known as unchecked exceptions.

#### Unchecked Exceptions — The Controversy

Because the Java programming language does not require methods to catch or to specify unchecked exceptions 
(RuntimeException, Error, and their subclasses), programmers may be tempted to write code that throws only unchecked
exceptions or to make all their exception subclasses inherit from RuntimeException. Both of these shortcuts allow
programmers to write code without bothering with compiler errors and without bothering to specify or to catch any
exceptions. Although this may seem convenient to the programmer, it sidesteps the intent of the catch or specify
requirement and can cause problems for others using your classes.

Why did the designers decide to force a method to specify all uncaught checked exceptions that can be thrown within its
scope? Any Exception that can be thrown by a method is part of the method's public programming interface. Those who call
a method must know about the exceptions that a method can throw so that they can decide what to do about them. These
exceptions are as much a part of that method's programming interface as its parameters and return value.

The next question might be: "If it's so good to document a method's API, including the exceptions it can throw, why not
specify runtime exceptions too?" Runtime exceptions represent problems that are the result of a programming problem, and
as such, the API client code cannot reasonably be expected to recover from them or to handle them in any way. Such
problems include arithmetic exceptions, such as dividing by zero; pointer exceptions, such as trying to access an object
through a null reference; and indexing exceptions, such as attempting to access an array element through an index that
is too large or too small.

Runtime exceptions can occur anywhere in a program, and in a typical one they can be very numerous. Having to add
runtime exceptions in every method declaration would reduce a program's clarity. Thus, the compiler does not require
that you catch or specify runtime exceptions (although you can).

One case where it is common practice to throw a RuntimeException is when the user calls a method incorrectly. For
example, a method can check if one of its arguments is incorrectly null. If an argument is null, the method might throw
a NullPointerException, which is an unchecked exception.

Generally speaking, do not throw a RuntimeException or create a subclass of RuntimeException simply because you don't
want to be bothered with specifying the exceptions your methods can throw.

```
Here's the bottom line guideline: If a client can reasonably be expected to recover from an exception, make it a checked
exception. If a client cannot do anything to recover from the exception, make it an unchecked exception.
```

#### Throwable

The Throwable class is the superclass of all errors and exceptions in the Java language. Only objects that are instances
of this class (or one of its subclasses) are thrown by the Java Virtual Machine or can be thrown by the Java throw
statement. Similarly, only this class or one of its subclasses can be the argument type in a catch clause. For the
purposes of compile-time checking of exceptions, Throwable and any subclass of Throwable that is not also a subclass of
either RuntimeException or Error are regarded as checked exceptions.
Instances of two subclasses, Error and Exception, are conventionally used to indicate that exceptional situations have
occurred. Typically, these instances are freshly created in the context of the exceptional situation so as to include
relevant information (such as stack trace data).
A throwable contains a snapshot of the execution stack of its thread at the time it was created. It can also contain a
message string that gives more information about the error. Over time, a throwable can suppress other throwables from
being propagated. Finally, the throwable can also contain a cause: another throwable that caused this throwable to be
constructed. The recording of this causal information is referred to as the chained exception facility, as the cause
can, itself, have a cause, and so on, leading to a "chain" of exceptions, each caused by another.
One reason that a throwable may have a cause is that the class that throws it is built atop a lower layered abstraction,
and an operation on the upper layer fails due to a failure in the lower layer. It would be bad design to let the
throwable thrown by the lower layer propagate outward, as it is generally unrelated to the abstraction provided by the
upper layer. Further, doing so would tie the API of the upper layer to the details of its implementation, assuming the
lower layer's exception was a checked exception. Throwing a "wrapped exception" (i.e., an exception containing a cause)
allows the upper layer to communicate the details of the failure to its caller without incurring either of these
shortcomings. It preserves the flexibility to change the implementation of the upper layer without changing its API (in
particular, the set of exceptions thrown by its methods).
A second reason that a throwable may have a cause is that the method that throws it must conform to a general-purpose
interface that does not permit the method to throw the cause directly. For example, suppose a persistent collection
conforms to the Collection interface, and that its persistence is implemented atop java.io. Suppose the internals of the
add method can throw an IOException. The implementation can communicate the details of the IOException to its caller
while conforming to the Collection interface by wrapping the IOException in an appropriate unchecked exception. (The
specification for the persistent collection should indicate that it is capable of throwing such exceptions.)
A cause can be associated with a throwable in two ways: via a constructor that takes the cause as an argument, or via
the initCause(Throwable) method. New throwable classes that wish to allow causes to be associated with them should
provide constructors that take a cause and delegate (perhaps indirectly) to one of the Throwable constructors that takes
a cause. Because the initCause method is public, it allows a cause to be associated with any throwable, even a "legacy
throwable" whose implementation predates the addition of the exception chaining mechanism to Throwable.
By convention, class Throwable and its subclasses have two constructors, one that takes no arguments and one that takes
a String argument that can be used to produce a detail message. Further, those subclasses that might likely have a cause
associated with them should have two more constructors, one that takes a Throwable (the cause), and one that takes a
String (the detail message) and a Throwable (the cause).

#### Error

An Error is a subclass of Throwable that indicates serious problems that a reasonable application should not try to
catch. Most such errors are abnormal conditions. The ThreadDeath error, though a "normal" condition, is also a subclass
of Error because most applications should not try to catch it.
A method is not required to declare in its throws clause any subclasses of Error that might be thrown during the
execution of the method but not caught, since these errors are abnormal conditions that should never occur. That is,
Error and its subclasses are regarded as unchecked exceptions for the purposes of compile-time checking of exceptions.

#### Exception

The class Exception and its subclasses are a form of Throwable that indicates conditions that a reasonable application
might want to catch.
The class Exception and any subclasses that are not also subclasses of RuntimeException are checked exceptions. Checked
exceptions need to be declared in a method or constructor's throws clause if they can be thrown by the execution of the
method or constructor and propagate outside the method or constructor boundary.

#### RuntimeException

RuntimeException is the superclass of those exceptions that can be thrown during the normal operation of the Java
Virtual Machine.
RuntimeException and its subclasses are unchecked exceptions. Unchecked exceptions do not need to be declared in a
method or constructor's throws clause if they can be thrown by the execution of the method or constructor and propagate
outside the method or constructor boundary.


