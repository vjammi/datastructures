## Binary and Decimal

### How to convert decimal to binary?
1. Divide the number by 2.
2. Get the integer quotient for the next iteration.
3. Get the remainder for the binary digit.
4. Repeat the steps until the quotient is equal to 0.

To convert 13 to binary
```
    Division by 2 	Quotient 	Remainder 	Bit #
            13/2 	6 	        1 	        0
            6/2 	3 	        0 	        1
            3/2 	1 	        1 	        2
            1/2 	0 	        1 	        3
    So 13 = 1101
```
Impl
```
    public static String decimalToBinary(int number) {
        StringBuilder binary = new StringBuilder();
        int remainder = 0;
        while (number > 0) {
            remainder = number % 2;
            number = number / 2;
            binary.append(remainder);
            System.out.println(number +" " +remainder);
        }
        binary.reverse();
        return binary.toString();
    }
```

### How to convert binary to decimal?
To find the decimal value of 111001
```
    Binary number: 	1 	    1 	    1 	    0 	    0 	    1
                    --------------------------------------------
    Power of 2: 	2^5 	2^4 	2^3 	2^2 	2^1 	2^0
                    ---------------------------------------------
                    1x2^5 + 1x2^4 + 1x2^3 + 0x2^2 + 0x2^1 + 1*2^0 
                    ---------------------------------------------
                    1*32  + 1*16  + 1*8   + 0*4   + 0*1   + 1*1  = 57
                    ---------------------------------------------  
```
Convert binary to decimal
```
            Binary        Quotient    Remainder   Decimal Number
                          num / 10    num % 10     2^i * remainder                	
          ---------------------------------------------------------
            111001        11100       1           2^0 * 1       1
            11100         1110        0           2^1 * 0       0
            1110          111         0           2^2 * 0       0
            111    	      11 	      1           2^3 * 1 	    8   
            11            1	          1           2^4 * 0 	    16    
            1             0	          1           2^5 * 1       32
          --------------------------------------------------------
                                                                57
```
Impl
```
    public static long binaryToDecimal(long binary) {
        long decimal = 0;
        long remainder = 0;
        int i = 0;
        while (binary > 0) {
            remainder = binary % 10;
            binary = binary / 10;
            decimal = (long) (decimal + remainder * Math.pow(2, i));
            i++;
        }
        return decimal;
   }
```

REFERENCES
https://www.rapidtables.com/convert/number/binary-to-decimal.html
