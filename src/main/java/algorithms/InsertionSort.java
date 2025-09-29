package algorithms;

import metrics.PerformanceTracker;

public class InsertionSort {
    public static void sort(int[] arr, PerformanceTracker pt) {
        if (arr == null) throw new IllegalArgumentException("Array is null");
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i]; pt.addAccesses(1);
            int left = 0, right = i - 1;

            while (left <= right) {
                int mid = (left + right) >>> 1;
                pt.addAccesses(1); pt.addComparison();
                if (key < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            int j = i - 1;
            while (j >= left) {
                arr[j + 1] = arr[j];
                pt.addAccesses(2); // read+write
                pt.addSwap();
                j--;
            }
            arr[left] = key; pt.addAccesses(1);
        }
    }
}
