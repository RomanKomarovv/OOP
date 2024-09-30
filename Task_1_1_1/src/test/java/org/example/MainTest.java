package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testHeapSort1() {
        int[] testArr = {5, 4, 3, 2, 1};
        int[] expArr = {1, 2, 3, 4, 5};
        Main.heapsort(testArr);
        assertArrayEquals(expArr, testArr);
    }

    @Test
    void testHeapSort2() {
        int[] testArr = new int[]{1, 2, 3, 4, 5};
        int[] expArr = new int[]{1, 2, 3, 4, 5};
        Main.heapsort(testArr);
        assertArrayEquals(expArr, testArr);
    }

    @Test
    void testHeapSort3() {
        int[] testArr = new int[]{};
        int[] expArr = new int[]{};
        Main.heapsort(testArr);
        assertArrayEquals(expArr, testArr);
    }

    @Test
    void testHeapSort4() {
        int[] testArr = new int[]{1};
        int[] expArr = new int[]{1};
        Main.heapsort(testArr);
        assertArrayEquals(expArr, testArr);
    }

    @Test
    void testHeapSort5() {
        int[] testArr = new int[]{1, 1, 1, 1, 1, 1, 1};
        int[] expArr = new int[]{1, 1, 1, 1, 1, 1, 1};
        Main.heapsort(testArr);
        assertArrayEquals(expArr, testArr);
    }

    @Test
    void testHeapSort6() {
        int[] testArr = new int[]{1, 1, 1, 1, 2, 2, 2, 2};
        int[] expArr = new int[]{1, 1, 1, 1, 2, 2, 2, 2};
        Main.heapsort(testArr);
        assertArrayEquals(expArr, testArr);
    }

    @Test
    void testHeapSort7() {
        int[] testArr = new int[]{2, 2, 2, 2, 1, 1, 1, 1};
        int[] expArr = new int[]{1, 1, 1, 1, 2, 2, 2, 2};
        Main.heapsort(testArr);
        assertArrayEquals(expArr, testArr);
    }

    @Test
    void testHeapSort8() {
        int[] testArr = new int[]{-1, -2, -3, -4, -5};
        int[] expArr = new int[]{-5, -4, -3, -2, -1};
        Main.heapsort(testArr);
        assertArrayEquals(expArr, testArr);
    }

    @Test
    void testHeapSort9() {
        int[] testArr = new int[]{-5, -4, -3, -2, -1};
        int[] expArr = new int[]{-5, -4, -3, -2, -1};
        Main.heapsort(testArr);
        assertArrayEquals(expArr, testArr);
    }

    @Test
    void testHeapSort10() {
        int[] testArr = new int[]{5, 7, 2, 1, 2, 4, 5};
        int[] expArr = new int[]{1, 2, 2, 4, 5, 5, 7};
        Main.heapsort(testArr);
        assertArrayEquals(expArr, testArr);
    }

    @Test
    void testMainClass(){
        Main.main(null);
        assertTrue(true);
    }
}