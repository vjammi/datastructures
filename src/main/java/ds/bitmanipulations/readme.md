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

```
    public static String decimalToBinary(int number) {
        StringBuilder binary = new StringBuilder();
        int remainder = 0;
        while (number > 0) {
            remainder = number % 2;
            number = number/2;
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
    Power of 2: 	2^5 	2^4 	2^3 	2^2 	2^1 	2^0
                    -------------------------------------------
                    1x2^5 + 1x2^4 + 1x2^3 + 0x2^2 + 0x2^1 + 1x2^0 = 57
```

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