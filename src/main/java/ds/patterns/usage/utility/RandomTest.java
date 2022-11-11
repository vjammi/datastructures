package ds.patterns.usage.utility;

import java.util.Random;

public class RandomTest {

    public static void main(String[] args) {
        // Returns the next int value [pseudorandom, uniformly distributed] between zero (inclusive) and bound (exclusive)
        // bound – the upper bound (exclusive). Must be positive.
        Random random = new Random();
        int upperBound = 5; // upper bound exclusive
        int randomInt = random.nextInt(upperBound);
        System.out.println("Random number between between 0 and "+ (upperBound-1) +": " +randomInt);

        // Returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0.
        // Returned values are chosen pseudorandomly with (approximately) uniform distribution from that range.
        System.out.println("Random number between 0-1 " +Math.random());
    }

}
