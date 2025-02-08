package org.example;

public class SequentialSolution {
    public static boolean isNotPrime(int num) {
        if (num <= 1) return true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return true;
        }
        return false;
    }

    public static boolean containsNonPrime(int[] arr) {
        for (int num : arr) {
            if (isNotPrime(num)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] data = {6, 8, 7, 13, 5, 9, 4};
        long startTime = System.nanoTime();
        boolean result = containsNonPrime(data);
        long endTime = System.nanoTime();
        System.out.println("Sequential Result: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + " ns");
    }
}