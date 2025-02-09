package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParallelThreadSolutionTest {

    @Test
    public void testEmptyArray() throws InterruptedException {
        int[] data = {};
        assertFalse(ParallelThreadSolution.containsNonPrime(data));
    }

    @Test
    public void testSingleElementPrime() throws InterruptedException {
        int[] data = {7};
        assertFalse(ParallelThreadSolution.containsNonPrime(data));
    }

    @Test
    public void testSingleElementNotPrime() throws InterruptedException {
        int[] data = {4};
        assertTrue(ParallelThreadSolution.containsNonPrime(data));
    }

    @Test
    public void testFirstElementNotPrime() throws InterruptedException {
        int[] data = {8, 7, 13, 5};
        assertTrue(ParallelThreadSolution.containsNonPrime(data));
    }

    @Test
    public void testLastElementNotPrime() throws InterruptedException {
        int[] data = {7, 13, 5, 9};
        assertTrue(ParallelThreadSolution.containsNonPrime(data));
    }

    @Test
    public void testMiddleElementNotPrime() throws InterruptedException {
        int[] data = {7, 8, 13, 5};
        assertTrue(ParallelThreadSolution.containsNonPrime(data));
    }

    @Test
    public void testAllPrimes() throws InterruptedException {
        int[] data = {7, 13, 5, 17};
        assertFalse(ParallelThreadSolution.containsNonPrime(data));
    }

    @Test
    public void testNegativeNumbers() throws InterruptedException {
        int[] data = {-1, -2, -3, -4};
        assertTrue(ParallelThreadSolution.containsNonPrime(data));
    }

    @Test
    public void testZeroAndOne() throws InterruptedException {
        int[] data = {0, 1, 7, 13};
        assertTrue(ParallelThreadSolution.containsNonPrime(data));
    }
}