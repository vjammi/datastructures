package ds.math;

public class DecimalAndBinaryConversions {

    public static void main(String[] args) {
        String binary = DecimalAndBinaryConversions.decimalToBinary(233);
        System.out.println("");
        DecimalAndBinaryConversions.binaryToDecimal(Integer.valueOf(binary));
    }

    public static String decimalToBinary(int number) {
        StringBuilder binary = new StringBuilder();

        int remainder = 0;
        while (number > 0) {
            remainder = number % 2;
            number = number/2;
            binary.append(remainder);
            System.out.println(number +" " +remainder);
        }
        System.out.println("Binary values (before reversing):  " +binary);
        binary.reverse();
        System.out.println("Binary values (after reversing):  " +binary);
        return binary.toString();
    }

    public static long binaryToDecimal(long binary) {
        long decimal = 0;
        long remainder = 0;

        System.out.println("Binary value to be converted to decimal: " + binary);
        int i = 0;
        while (binary > 0) {
            remainder = binary % 10;
            binary = binary / 10;
            decimal = (long) (decimal + remainder * Math.pow(2, i));
            System.out.println(binary +" " +remainder +" " + (int) (remainder * Math.pow(2, i)));
            i++;
        }
        System.out.println("Binary to Decimal is " + decimal);
        return decimal;
   }
}
