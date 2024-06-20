package dev.vjammi.ds.v2.dev.usage.datatypes;

public class DataTypesInJava {

    // https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
    /**
        Primitive Data Types
        The Java programming language is statically-typed, which means that all variables must first be declared before they can be used. This involves stating the variable's type and name, as you've already seen:
            int gear = 1;
        Doing so tells your program that a field named "gear" exists, holds numerical data, and has an initial value of "1". A variable's data type determines the values it may contain, plus the operations that may be performed on it. In addition to int, the Java programming language supports seven other primitive data types. A primitive type is predefined by the language and is named by a reserved keyword. Primitive values do not share state with other primitive values. The eight primitive data types supported by the Java programming language are:
            byte: The byte data type is an 8-bit signed two's complement integer. It has a minimum value of -128 and a maximum value of 127 (inclusive). The byte data type can be useful for saving memory in large arrays, where the memory savings actually matters. They can also be used in place of int where their limits help to clarify your code; the fact that a variable's range is limited can serve as a form of documentation.
            short: The short data type is a 16-bit signed two's complement integer. It has a minimum value of -32,768 and a maximum value of 32,767 (inclusive). As with byte, the same guidelines apply: you can use a short to save memory in large arrays, in situations where the memory savings actually matters.
            int: By default, the int data type is a 32-bit signed two's complement integer, which has a minimum value of -231 and a maximum value of 231-1. In Java SE 8 and later, you can use the int data type to represent an unsigned 32-bit integer, which has a minimum value of 0 and a maximum value of 232-1. Use the Integer class to use int data type as an unsigned integer. See the section The Number Classes for more information. Static methods like compareUnsigned, divideUnsigned etc have been added to the Integer class to support the arithmetic operations for unsigned integers.
            long: The long data type is a 64-bit two's complement integer. The signed long has a minimum value of -263 and a maximum value of 263-1. In Java SE 8 and later, you can use the long data type to represent an unsigned 64-bit long, which has a minimum value of 0 and a maximum value of 264-1. Use this data type when you need a range of values wider than those provided by int. The Long class also contains methods like compareUnsigned, divideUnsigned etc to support arithmetic operations for unsigned long.
            float: The float data type is a single-precision 32-bit IEEE 754 floating point. Its range of values is beyond the scope of this discussion, but is specified in the Floating-Point Types, Formats, and Values section of the Java Language Specification. As with the recommendations for byte and short, use a float (instead of double) if you need to save memory in large arrays of floating point numbers. This data type should never be used for precise values, such as currency. For that, you will need to use the java.math.BigDecimal class instead. Numbers and Strings covers BigDecimal and other useful classes provided by the Java platform.
            double: The double data type is a double-precision 64-bit IEEE 754 floating point. Its range of values is beyond the scope of this discussion, but is specified in the Floating-Point Types, Formats, and Values section of the Java Language Specification. For decimal values, this data type is generally the default choice. As mentioned above, this data type should never be used for precise values, such as currency.
            boolean: The boolean data type has only two possible values: true and false. Use this data type for simple flags that track true/false conditions. This data type represents one bit of information, but its "size" isn't something that's precisely defined.
            char: The char data type is a single 16-bit Unicode character. It has a minimum value of '\u0000' (or 0) and a maximum value of '\uffff' (or 65,535 inclusive).
        In addition to the eight primitive data types listed above, the Java programming language also provides special support for character strings via the java.lang.String class. Enclosing your character string within double quotes will automatically create a new String object; for example, String s = "this is a string";. String objects are immutable, which means that once created, their values cannot be changed. The String class is not technically a primitive data type, but considering the special support given to it by the language, you'll probably tend to think of it as such. You'll learn more about the String class in Simple Data Objects


     Default Values
     It's not always necessary to assign a value when a field is declared. Fields that are declared but not initialized will be set to a reasonable default by the compiler. Generally speaking, this default will be zero or null, depending on the data type. Relying on such default values, however, is generally considered bad programming style.
     The following chart summarizes the default values for the above data types.
        Data Type 	Default Value (for fields)
         byte 	    0
         short 	    0
         int 	    0
         long 	    0L
         float 	    0.0f
         double 	0.0d
         char 	    '\u0000'
         String (or any object)   	null
         boolean 	false
     Local variables are slightly different; the compiler never assigns a default value to an uninitialized local variable. If you cannot initialize your local variable where it is declared, make sure to assign it a value before you attempt to use it. Accessing an uninitialized local variable will result in a compile-time error.

    */


    public static void main(String[] args) {

        // float and double, Float and Double
        /**
         // https://www.educative.io/edpresso/what-is-the-difference-between-float-and-double

         Float and double store decimal or floating-point numbers.
         The difference between them is the precision.

         A float is a 32-bit IEEE 754 single-precision floating-point number.
            1 bit for the sign 8 bits for the exponent 23 bits for the value.
            A float has 7 decimal digits of precision and occupies 32 bits.

         A double is a 64-bit IEEE 754 double-precision floating-point number.
            1 bit for the sign, 11 bits for the exponent, and 52 bits for the value.
            A double has 15 decimal digits of precision and occupies a total of 64 bits.
         **/

        // Notice how float cannot store the entire value since it has less precision than double.
        float singlePrecision32BitFloatingPointNum = 1f / 3;  // 0.33333334
        double doublePrecision64BitFloatingPointNum = 1d / 3; // 0.3333333333333333
        System.out.println("SinglePrecision 32Bit FloatingPoint Num " + singlePrecision32BitFloatingPointNum);
        System.out.println("DoublePrecision 64Bit FloatingPoint Num: " + doublePrecision64BitFloatingPointNum);

        // int and long, Integer and Long
        // https://www.differencebetween.com/difference-between-int-and-vs-long/


        // float and int
        /**
         // https://www.educative.io/edpresso/float-and-ints
         An integer is an integral data type used to store whole numbers.
         A float has the ability to store decimal or floating-point numbers.

                    Float 	                        Int
         Size 	    32 bits 	                    32 bits
         Range 	    1.2x10-38 to 3.4x1038 	        -2,147,483,648 to 2,147,483,647
         Example 	2.4, 3.8, 11.2 	                1,22,55
         */
        //  Adding literal at the end tells the compiler which type to use (b-bytes, s-short, l-long, f-float)
        int wholeNumber32Bit =  2147483647;             // Integer.MAX_VALUE; // 2147483647
        long wholeNumber64Bit = 9223372036854775807L ;  // Long.MAX_VALUE;    // 9223372036854775807
        System.out.println("32 Bit Whole Number " +wholeNumber32Bit);
        System.out.println("64 Bit Whole Number " +wholeNumber64Bit);

        long bytes = (1L << 32);
        System.out.println(" bytes " +bytes);

        /*
            Arithmetic Operators
            % (remainder)
            * (multiplication)
            + (addition)
            - (subtraction)
            / (division)
            = (assignment operator)

            Comparison Operators
            != (not equal to)
            < (less than)
            <= (less than or equal to)
            == (equal to)
            > (greater than)
            >= (greater than or equal to)

            Boolean Operators
            ! (logical not)
            && (logical and)
            || (logical or)

            Bitwise Operators
            & (bitwise and)
            << (bitshift left)
            >> (bitshift right)
            ^ (bitwise xor)
            | (bitwise or)
            ~ (bitwise not)
        * */
    }

}
