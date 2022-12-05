package dev.vjammi.ds.v2.usage.operations;

public class MathOperations {
    /**
        Rabin-Karp's suggested Hash Function
            For base 10
                [(int)d * 10^2] + [(int)e * 10^1] + [(int)f * 10^0]
                [100 * 10^2]    + [101 * 10^1]    + [102 * 10^0]
                Math.pow(10, 2)         // Params: a – the base. b – the exponent.

            For english alphabets we can take the base as 26
                Math.pow(26, 2)         // Params: a – the base. b – the exponent.

            If Lower/Uppercase and special chars are included then we should consider total ascii codes of 127
                Math.pow(127, 2)        // Params: a – the base. b – the exponent.
    */
    public static void main(String[] args) {
        double pow = Math.pow(10, 2);
        int max = Math.max(1, 2);
        int min = Math.min(1, 2);
    }
}
