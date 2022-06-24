package ds.bitmanipulations;

public class ReverseInteger {

    // Option 1: Using a temporary string to reverse the digits
    int reverse1(int x) {
        // Cast the number passed in to int-64.
        long num = (long) x;

        // Check if it's positive or negative.
        boolean isNegative = num < 0 ? true : false;

        // If it's negative then convert it to positive.
        if (isNegative) {
            num *= -1;
        }

        // Convert the number to a string.
        StringBuilder s = new StringBuilder(String.valueOf(num));

        // Reverse the string.
        s.reverse();

        // Convert back to int-64.
        int reversedNum = Integer.valueOf(s.toString());

        // Multiply back by -1 if the original number was negative.
        if (isNegative) {
            reversedNum *= -1;
        }

        // If there was an overflow, return 0.
        int MIN_VALUE = -2147483648;
        int MAX_VALUE = 2147483647;
        if (isNegative && reversedNum < MIN_VALUE) {
            return 0;
        }
        if (!isNegative && reversedNum > MAX_VALUE) {
            return 0;
        }

        return Integer.valueOf(reversedNum);
    }

    // Option 2: We go through each digit from right to left and make that the digit of our reversed number.
    //           This solution is still O(number of digits) but the operations (modulus and divide) are less expensive.
    public long reverse2(int x) {
        // Cast the number passed in to int-64.
        long num = (long) x;

        // Check if it's positive or negative.
        boolean isNegative = num < 0 ? true : false;

        // If it's negative then make it positive.
        if (isNegative) {
            num *= -1;
        }

        // Continually process each digit in the number.
        long reversedNum = 0;
        while (num != 0) {
            long digit = num % 10;
            num /= 10;
            reversedNum = reversedNum * 10 + digit;
        }

        // Make the new number negative if necessary.
        if (isNegative) {
            reversedNum *= -1;
        }

        // If there was an overflow, return 0.
        int MIN_VALUE = -2147483648;
        int MAX_VALUE = 2147483647;
        if (isNegative && reversedNum < MIN_VALUE) {
            return 0;
        }
        if (!isNegative && reversedNum > MAX_VALUE) {
            return 0;
        }

        return reversedNum;
    }


    // Option 3: eliminate the use of 64-bit integers by, at each step, checking if we're going to overflow if we were to add the next digit.
    int reverse3(int num) {
        // Check if it's positive or negative.
        boolean isNegative = num < 0 ? true : false;

        // Continually process each digit in the number.
        int reversedNum = 0;
        while (num != 0) {
            int digit = num % 10;
            num /= 10;

            if (isNegative) {
                digit *= -1;
            }

            // If this iteration would make us overflow, then stop and just return 0.
            if (reversedNum > 214748364) {
                return 0;
            }
            if (reversedNum == 214748364) {
                if (isNegative && digit > 8) {
                    return 0;
                }
                if (!isNegative && digit > 7) {
                    return 0;
                }
            }

            reversedNum = reversedNum * 10 + digit;
        }

        // Make the reversed number negative if necessary.
        if (isNegative) {
            reversedNum *= -1;
        }

        return reversedNum;
    }

    public static void main(String[] args) {
        ReverseInteger obj = new ReverseInteger();
        System.out.println(obj.reverse2(91123333));
    }
}
