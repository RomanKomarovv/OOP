package org.example;

public class PrimeUtil {
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
}
