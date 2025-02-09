package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SequentialSolutionTest {

    @Test
    public void testLargeNumbersWithNonPrime() {
        int[] data = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053, 100000000};
        assertTrue(SequentialSolution.containsNonPrime(data), "Array contains a non-prime number (100000000)");
    }

    @Test
    public void testLargeNumbersAllPrimes() {
        int[] data = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        assertFalse(SequentialSolution.containsNonPrime(data), "Array contains only prime numbers");
    }

    @Test
    public void testLargeNumbersWithNonPrimeAtEnd() {
        int[] data = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053, 100000000};
        assertTrue(SequentialSolution.containsNonPrime(data), "Array contains a non-prime number at the end");
    }

    @Test
    public void testVeryLargeNumbersWithNonPrime() {
        int[] data = {104729, 104743, 104759, 104761, 104773, 104777, 104789, 104797, 104801, 104807, 104827, 104833, 100000000};
        assertTrue(SequentialSolution.containsNonPrime(data), "Array contains a non-prime number (100000000)");
    }

}