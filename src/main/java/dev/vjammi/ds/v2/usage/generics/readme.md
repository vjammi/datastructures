## Table lists the primitive types and their corresponding wrapper classes, which are used by the Java compiler for autoboxing and unboxing
    Primitive type	Wrapper class
    -----------------------------
    boolean	        Boolean
    byte	        Byte
    char	        Character
    float	        Float
    int	            Integer
    long	        Long
    short	        Short
    double	        Double

## Autoboxing and Unboxing
Autoboxing is the automatic conversion that the Java compiler makes between the primitive types and their corresponding object wrapper classes. 
For example, converting an int to an Integer, a double to a Double, and so on. 
If the conversion goes the other way, this is called unboxing.

### Autoboxing

### Unboxing
```
public class Unboxing {
     public static void main(String[] args) {
        Integer i = new Integer(-8);
 
        // 1. Unboxing through method invocation
        int absVal = absoluteValue(i);
        System.out.println("absolute value of " + i + " = " + absVal);
        List<Double> ld = new ArrayList<>();
        ld.add(3.1416);    // PI is autoboxed through method invocation.
        
        // 2. Unboxing through assignment
        double phi = ld.get(0);
        System.out.println("phi = " + phi);
    }
    public static int absoluteValue(int i) {
        return (i < 0) ? -i : i;
    }
}
```

