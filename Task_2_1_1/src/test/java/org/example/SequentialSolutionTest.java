package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the SequentialSolution, focusing on testing with large numbers.
 */
public class SequentialSolutionTest {

    /**
     * Tests an array of large numbers where one of the numbers is not prime.
     * The array contains both prime and non-prime numbers, with a non-prime number included.
     */
    @Test
    public void testLargeNumbersWithNonPrime() {
        int[] data = {
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053, 100000000
        };
        assertTrue(SequentialSolution.containsNonPrime(data));
    }

    /**
     * Tests an array of large numbers where all numbers are prime.
     * Ensures that the method correctly identifies when there are no non-prime numbers.
     */
    @Test
    public void testLargeNumbersAllPrimes() {
        int[] data = {
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053
        };
        assertFalse(SequentialSolution.containsNonPrime(data));
    }

    /**
     * Tests an array of large numbers where the non-prime number is located at the end.
     * Verifies that the method correctly identifies the non-prime number regardless of its position.
     */
    @Test
    public void testLargeNumbersWithNonPrimeAtEnd() {
        int[] data = {
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053, 100000000
        };
        assertTrue(SequentialSolution.containsNonPrime(data));
    }

    /**
     * Tests an array of very large numbers where one of the numbers is not prime.
     * Ensures that the method works correctly with extremely large numbers.
     */
    @Test
    public void testVeryLargeNumbersWithNonPrime() {
        int[] data = {
                104729, 104743, 104759, 104761, 104773, 104777,
                104789, 104797, 104801, 104807, 104827, 104833, 100000000
        };
        assertTrue(SequentialSolution.containsNonPrime(data));
    }
}