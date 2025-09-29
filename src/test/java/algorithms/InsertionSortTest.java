package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortTest {

    private void assertSortedWithAlgorithm(int[] arr) {
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);
        PerformanceTracker pt = new PerformanceTracker();
        InsertionSort.sort(arr, pt);
        assertArrayEquals(expected, arr);
    }

    @Test void testEmpty() { assertSortedWithAlgorithm(new int[]{}); }
    @Test void testSingle() { assertSortedWithAlgorithm(new int[]{5}); }
    @Test void testDuplicates() { assertSortedWithAlgorithm(new int[]{3,1,2,3,1}); }
    @Test void testSorted() { assertSortedWithAlgorithm(new int[]{1,2,3,4,5}); }
    @Test void testReverse() { assertSortedWithAlgorithm(new int[]{5,4,3,2,1}); }
    @Test void testRandom() {
        int n = 200;
        int[] a = new java.util.Random(1).ints(n, 0, 1000).toArray();
        assertSortedWithAlgorithm(a);
    }
}
