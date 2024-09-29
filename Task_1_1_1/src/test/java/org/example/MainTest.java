package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MainTest {

    @Test
    public void testHeapsort() {
        int[] array = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testEmptyArray() {
        int[] array = {};
        int[] expected = {};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSingleElementArray() {
        int[] array = {52};
        int[] expected = {52};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testDuplicatesInArray() {
        int[] array = {9, 9, 8, 9};
        int[] expected = {8, 9, 9, 9};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }
}
