package org.example;

/**
 * Class to check for non-prime numbers in an array sequentially.
 */
public class SequentialSolution {

    /**
     * Checks if a number is not prime.
     * @param num the number to check
     * @return true if not prime, false otherwise
     */
    public static boolean isNotPrime(int num) {
        if (num <= 1) return true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return true;
        }
        return false;
    }

    /**
     * Checks if the array contains any non-prime numbers.
     * @param arr the array of integers
     * @return true if any non-prime exists, false otherwise
     */
    public static boolean containsNonPrime(int[] arr) {
        for (int num : arr) {
            if (isNotPrime(num)) return true;
        }
        return false;
    }
}