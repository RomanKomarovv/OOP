package org.example;

public class Main {
    public static void heapsort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest);
        }
    }

    public static void print_array(int[] arr) {
        int length = arr.length;

        System.out.print('[');
        for (int i = 0; i < length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[length - 1] + "]");
    }

    public static void main(String[] args) {
        int[] array = {9, 9, 8, 9};
        heapsort(array);
        print_array(array);
    }
}
