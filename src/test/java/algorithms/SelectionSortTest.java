package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class SelectionSortTest {

    private void assertSortedWithAlgorithm(int[] arr) {
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);
        PerformanceTracker pt = new PerformanceTracker();
        SelectionSort.sort(arr, pt);
        assertArrayEquals(expected, arr);
    }

    @Test void testEmpty() { assertSortedWithAlgorithm(new int[]{}); }
    @Test void testSingle() { assertSortedWithAlgorithm(new int[]{42}); }
    @Test void testDuplicates() { assertSortedWithAlgorithm(new int[]{5,1,2,5,1}); }
    @Test void testSorted() { assertSortedWithAlgorithm(new int[]{1,2,3,4,5}); }
    @Test void testReverse() { assertSortedWithAlgorithm(new int[]{9,8,7,6,5}); }
    @Test
    void testRandom() {
        int n = 200;
        int[] a = new java.util.Random(2).ints(n, 0, 1000).toArray();
        assertSortedWithAlgorithm(a);
    }
}
