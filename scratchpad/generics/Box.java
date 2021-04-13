package scratchpad.generics;

/**
 https://docs.oracle.com/javase/tutorial/java/generics/types.html

 Begin by examining a non-generic Box class that operates on objects of any type. It needs only to provide two methods:
 set, which adds an object to the box, and get, which retrieves it:
     public class Box {
         private Object object;
         public void set(Object object) { this.object = object; }
         public Object get() { return object; }
     }
 Since its methods accept or return an Object, you are free to pass in whatever you want, provided that it is not one
 of the primitive types. There is no way to verify, at compile time, how the class is used. One part of the code may place
 an Integer in the box and expect to get Integers out of it, while another part of the code may mistakenly pass in a String,
 resulting in a runtime error.

 Type Parameter Naming Conventions
 By convention, type parameter names are single, uppercase letters. This stands in sharp contrast to the variable naming
 conventions that you already know about, and with good reason: Without this convention, it would be difficult to tell the
 difference between a type variable and an ordinary class or interface name.

The most commonly used type parameter names are:

E - Element (used extensively by the Java Collections Framework)
K - Key
N - Number
T - Type
V - Value
S,U,V etc. - 2nd, 3rd, 4th types
*/
/**
 * Generic version of the Box class.
 * @param <T> the type of the value being boxed
 */
public class Box<T> {
    // T stands for "Type"
    private T t;

    public void set(T t) {
        this.t = t;
    }
    public T get() {
        return t;
    }

    public static void main(String args[]){
        Box<Integer> integerBox =  new Box<>();
        integerBox.set(1);
        integerBox.get();
    }
}
