package org.example;

import java.util.Arrays;

/**
 * Test of Parallel Stream Solution
 */
public class ParallelStreamSolution {

    /**
     * Determines if the array contains at least one non-prime number using parallel streams.
     *
     * @param arr the array of integers to check
     * @return true if the array contains a non-prime number, false otherwise
     */
    public static boolean containsNonPrime(int[] arr) {
        return Arrays.stream(arr).parallel().anyMatch(PrimeUtil::isNotPrime);
    }
}
