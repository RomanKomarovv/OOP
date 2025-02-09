package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A class that checks if an array contains any non-prime numbers using parallel threads.
 */
public class ParallelThreadSolution {

    /**
     * The number of threads used for parallel execution.
     */
    private static final int THREAD_COUNT = 6;

    /**
     * Checks if a given number is not prime.
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
     * Determines if the array contains at least one non-prime number using parallel threads.
     *
     * @param arr the array of integers to check
     * @return true if the array contains a non-prime number, false otherwise
     * @throws InterruptedException if the thread is interrupted during execution
     */
    public static boolean containsNonPrime(int[] arr) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        boolean[] results = new boolean[THREAD_COUNT];
        int chunkSize = arr.length / THREAD_COUNT;

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int start = i * chunkSize;
            final int end = (i == THREAD_COUNT - 1) ? arr.length : (start + chunkSize);
            int finalI = i;
            executor.submit(() -> {
                for (int j = start; j < end; j++) {
                    if (isNotPrime(arr[j])) {
                        synchronized (results) {
                            results[finalI] = true;
                            return;
                        }
                    }
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        for (boolean res : results) {
            if (res) {
                return true;
            }
        }
        return false;
    }
}