package org.example;

import java.util.Arrays;

public class ParallelStreamSolution {
    public static boolean isNotPrime(int num) {
        if (num <= 1) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return true;
        }
        return false;
    }

    public static boolean containsNonPrime(int[] arr) {
        return Arrays.stream(arr).parallel().anyMatch(ParallelStreamSolution::isNotPrime);
    }
}
