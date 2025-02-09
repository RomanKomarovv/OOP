package org.example;

import java.util.Arrays;

/**
 * Test of Parallel Stream Solution
 */
public class ParallelStreamSolution {

    /**
     * Checks if a given number is non prime.
     *
     * @param num the number to check
     * @return true if the number is not prime, false otherwise
     */
    public static boolean isNotPrime(int num) {
        if (num <= 1) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the array contains at least one non-prime number using parallel streams.
     *
     * @param arr the array of integers to check
     * @return true if the array contains a non-prime number, false otherwise
     */
    public static boolean containsNonPrime(int[] arr) {
        return Arrays.stream(arr).parallel().anyMatch(ParallelStreamSolution::isNotPrime);
    }
}
