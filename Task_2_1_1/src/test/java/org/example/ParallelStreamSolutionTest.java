package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for ParallelStreamSolution.
 */
public class ParallelStreamSolutionTest {

    /**
     * Tests empty array.
     */
    @Test
    public void testEmptyArray() {
        int[] data = {};
        assertFalse(ParallelStreamSolution.containsNonPrime(data));
    }

    /**
     * Tests single prime element.
     */
    @Test
    public void testSingleElementPrime() {
        int[] data = {7};
        assertFalse(ParallelStreamSolution.containsNonPrime(data));
    }

    /**
     * Tests single non-prime element.
     */
    @Test
    public void testSingleElementNotPrime() {
        int[] data = {4};
        assertTrue(ParallelStreamSolution.containsNonPrime(data));
    }

    /**
     * Tests array with first element non-prime.
     */
    @Test
    public void testFirstElementNotPrime() {
        int[] data = {8, 7, 13, 5};
        assertTrue(ParallelStreamSolution.containsNonPrime(data));
    }

    /**
     * Tests array with last element non-prime.
     */
    @Test
    public void testLastElementNotPrime() {
        int[] data = {7, 13, 5, 9};
        assertTrue(ParallelStreamSolution.containsNonPrime(data));
    }

    /**
     * Tests array with middle element non-prime.
     */
    @Test
    public void testMiddleElementNotPrime() {
        int[] data = {7, 8, 13, 5};
        assertTrue(ParallelStreamSolution.containsNonPrime(data));
    }

    /**
     * Tests array with all prime numbers.
     */
    @Test
    public void testAllPrimes() {
        int[] data = {7, 13, 5, 17};
        assertFalse(ParallelStreamSolution.containsNonPrime(data));
    }

    /**
     * Tests array with negative numbers.
     */
    @Test
    public void testNegativeNumbers() {
        int[] data = {-1, -2, -3, -4};
        assertTrue(ParallelStreamSolution.containsNonPrime(data));
    }

    /**
     * Tests array with zero and one.
     */
    @Test
    public void testZeroAndOne() {
        int[] data = {0, 1, 7, 13};
        assertTrue(ParallelStreamSolution.containsNonPrime(data));
    }
}