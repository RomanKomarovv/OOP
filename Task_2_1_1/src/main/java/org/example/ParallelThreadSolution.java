package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelThreadSolution {
    private static final int THREAD_COUNT = 6; // Количество потоков

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
        while (!executor.isTerminated()) {}

        for (boolean res : results) {
            if (res) {
                return true;
            }
        }
        return false;
    }
}