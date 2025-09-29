package algorithms;

import metrics.PerformanceTracker;

public class SelectionSort {
    public static void sort(int[] arr, PerformanceTracker pt) {
        if (arr == null) throw new IllegalArgumentException("Array is null");
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            pt.addAccess();

            for (int j = i + 1; j < n; j++) {
                pt.addAccess();
                pt.addComparison();
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                int tmp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = tmp;
                pt.addSwap();
                pt.addAccesses(3);
            }
        }
    }
}
