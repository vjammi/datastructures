package io.dev.v3.async;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class AsyncPlayTest {

    private AsyncPlay subject;

    @BeforeEach
    void setUp() {
        subject = new AsyncPlay();
    }

    @Test
    void shouldReturnSumOfSquaredOfPrimeNumbersFromTheStream() {
        List<Integer> nums = List.of(1,2,3,4,5,6,7,8,9);
        int sum = subject.sumOfSquaredOfPrimes(nums);
    }
}