package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParallelStreamSolutionTest {

    @Test
    public void testEmptyArray() {
        int[] data = {};
        assertFalse(ParallelStreamSolution.containsNonPrime(data));
    }

    @Test
    public void testSingleElementPrime() {
        int[] data = {7};
        assertFalse(ParallelStreamSolution.containsNonPrime(data));
    }

    @Test
    public void testSingleElementNotPrime() {
        int[] data = {4};
        assertTrue(ParallelStreamSolution.containsNonPrime(data));
    }

    @Test
    public void testFirstElementNotPrime() {
        int[] data = {8, 7, 13, 5};
        assertTrue(ParallelStreamSolution.containsNonPrime(data));
    }

    @Test
    public void testLastElementNotPrime() {
        int[] data = {7, 13, 5, 9};
        assertTrue(ParallelStreamSolution.containsNonPrime(data));
    }

    @Test
    public void testMiddleElementNotPrime() {
        int[] data = {7, 8, 13, 5};
        assertTrue(ParallelStreamSolution.containsNonPrime(data));
    }

    @Test
    public void testAllPrimes() {
        int[] data = {7, 13, 5, 17};
        assertFalse(ParallelStreamSolution.containsNonPrime(data));
    }

    @Test
    public void testNegativeNumbers() {
        int[] data = {-1, -2, -3, -4};
        assertTrue(ParallelStreamSolution.containsNonPrime(data));
    }

    @Test
    public void testZeroAndOne() {
        int[] data = {0, 1, 7, 13};
        assertTrue(ParallelStreamSolution.containsNonPrime(data));
    }
}