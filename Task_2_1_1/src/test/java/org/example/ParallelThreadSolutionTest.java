package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for ParallelThreadSolution.
 */
public class ParallelThreadSolutionTest {

    /**
     * Tests empty array.
     */
    @Test
    public void testEmptyArray() throws InterruptedException {
        int[] data = {};
        assertFalse(ParallelThreadSolution.containsNonPrime(data));
    }

    /**
     * Tests single prime element.
     */
    @Test
    public void testSingleElementPrime() throws InterruptedException {
        int[] data = {7};
        assertFalse(ParallelThreadSolution.containsNonPrime(data));
    }

    /**
     * Tests single non-prime element.
     */
    @Test
    public void testSingleElementNotPrime() throws InterruptedException {
        int[] data = {4};
        assertTrue(ParallelThreadSolution.containsNonPrime(data));
    }

    /**
     * Tests array with first element non-prime.
     */
    @Test
    public void testFirstElementNotPrime() throws InterruptedException {
        int[] data = {8, 7, 13, 5};
        assertTrue(ParallelThreadSolution.containsNonPrime(data));
    }

    /**
     * Tests array with last element non-prime.
     */
    @Test
    public void testLastElementNotPrime() throws InterruptedException {
        int[] data = {7, 13, 5, 9};
        assertTrue(ParallelThreadSolution.containsNonPrime(data));
    }

    /**
     * Tests array with middle element non-prime.
     */
    @Test
    public void testMiddleElementNotPrime() throws InterruptedException {
        int[] data = {7, 8, 13, 5};
        assertTrue(ParallelThreadSolution.containsNonPrime(data));
    }

    /**
     * Tests array with all prime numbers.
     */
    @Test
    public void testAllPrimes() throws InterruptedException {
        int[] data = {7, 13, 5, 17};
        assertFalse(ParallelThreadSolution.containsNonPrime(data));
    }

    /**
     * Tests array with negative numbers.
     */
    @Test
    public void testNegativeNumbers() throws InterruptedException {
        int[] data = {-1, -2, -3, -4};
        assertTrue(ParallelThreadSolution.containsNonPrime(data));
    }

    /**
     * Tests array with zero and one.
     */
    @Test
    public void testZeroAndOne() throws InterruptedException {
        int[] data = {0, 1, 7, 13};
        assertTrue(ParallelThreadSolution.containsNonPrime(data));
    }

    @Test
    public void compareTest() throws InterruptedException {
        int[] numbers = {
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053
        };
        long start1 = System.nanoTime();
        ParallelStreamSolution.containsNonPrime(numbers);
        long t1 = System.nanoTime() - start1;
        System.out.printf("Parallel stream %.3f ms%n", t1/1E6);
        long start2 = System.nanoTime();
        ParallelThreadSolution.containsNonPrime(numbers);
        long t2 = System.nanoTime() - start2;
        System.out.printf("Parallel thread %.3f ms%n", t2/1E6);
    }
}