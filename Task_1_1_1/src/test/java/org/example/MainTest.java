package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MainTest {

    @Test
    public void testReverseArray() {
        int[] array = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testHeapsort() {
        int[] array = {8, 3, 1, 9, 6};
        int[] expected = {1, 3, 6, 8, 9};
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
        int[] array = {9, 9, 8, 9, 8};
        int[] expected = {8, 8, 9, 9, 9};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testBigArray() {
        int[] array = {18, 743, 900, 45, 52, 500, 337, 4, 1, 932,
                       774, 295, 199, 309, 501, 604, 22, 10};

        int[] expected = {1, 4, 10, 18, 22, 45, 52, 199, 295, 309,
                          337, 500, 501, 604, 743, 774, 900, 932};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testBigNumbers() {
        int[] array = {10000, 5555, 99999, -7743, 33, -52000, 32455, 1,
                       -2, - 78999, -9909, 45005, -8, 200, -344};

        int[] expected = {-78999, -52000, -9909, -7743, -344, -8, -2, 1,
                           33, 200, 5555, 10000, 32455, 45005, 99999};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void test9() {
        int[] array = {52, 52, 52, 52, 52, -52, -52, -52, -52, -52};

        int[] expected = {-52, -52, -52, -52, -52, 52, 52, 52, 52, 52};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void test10() {
        int[] array = {9, 9, 9, 9, 9, 9, 9, 9, 9, 9};

        int[] expected = {9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void test11() {
        int[] array = {-1, -2, -3, -4, -5, -6, -7, -8};

        int[] expected = {-8, -7, -6, -5, -4, -3, -2, -1};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void test12() {
        int[] array = {5, 5, 5, 5, 5, 2, 2, 2, 2, 2};

        int[] expected = {2, 2, 2, 2, 2, 5, 5, 5, 5, 5};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testSmallNumbers() {
        int[] array = {69, 87, 47, 106, 5, 46, 183, 2, 10};
        int[] expected = {2, 5, 10, 46, 47, 69, 87, 106, 183};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testLargeNumbers() {
        int[] array = {-100, -1231, 38, 103, 16, 1, 55, -6, -232, -99528, 308, 153, 5, -69};
        int[] expected = {-99528, -1231, -232, -100, -69, -6, 1, 5, 16, 38, 55, 103, 153, 308};
        Main.heapsort(array);
        assertArrayEquals(expected, array);
    }
}
