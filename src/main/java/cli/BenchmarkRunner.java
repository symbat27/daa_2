package cli;

import algorithms.InsertionSort;
import algorithms.SelectionSort;
import metrics.PerformanceTracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class BenchmarkRunner {
    public static void main(String[] args) throws Exception {
        String alg = "insertion";           // insertion | selection
        String dist = "random";             // random | sorted | reverse | nearly
        int[] sizes = new int[]{100,1000,10000};
        int repeats = 3;
        String outCsv = "results.csv";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--alg": alg = args[++i]; break;
                case "--dist": dist = args[++i]; break;
                case "--sizes": sizes = parseSizes(args[++i]); break;
                case "--repeats": repeats = Integer.parseInt(args[++i]); break;
                case "--out": outCsv = args[++i]; break;
                default: System.err.println("Unknown arg: " + args[i]);
            }
        }

        List<String> lines = new ArrayList<>();
        lines.add("algorithm,n,time_ns,comparisons,swaps,accesses");
        Random rnd = new Random(42);

        for (int n : sizes) {
            for (int r = 0; r < repeats; r++) {
                int[] arr = generateArray(n, dist, rnd);
                int[] copy = Arrays.copyOf(arr, arr.length);
                PerformanceTracker pt = new PerformanceTracker();
                pt.reset();
                pt.startTimer();
                if ("insertion".equalsIgnoreCase(alg)) {
                    InsertionSort.sort(copy, pt);
                } else if ("selection".equalsIgnoreCase(alg)) {
                    SelectionSort.sort(copy, pt);
                } else {
                    throw new IllegalArgumentException("Unknown algorithm: " + alg);
                }
                pt.stopTimer();
                lines.add(String.format("%s,%d,%d,%d,%d,%d",
                        alg, n, pt.getElapsedNs(), pt.getComparisons(), pt.getSwaps(), pt.getAccesses()));
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outCsv))) {
            for (String l : lines) bw.write(l + "\n");
        }
        System.out.println("Wrote " + outCsv);
    }

    private static int[] parseSizes(String s) {
        String[] parts = s.split(",");
        int[] res = new int[parts.length];
        for (int i = 0; i < parts.length; i++) res[i] = Integer.parseInt(parts[i].trim());
        return res;
    }

    private static int[] generateArray(int n, String dist, Random rnd) {
        int[] a = new int[n];
        switch (dist.toLowerCase()) {
            case "sorted":
                for (int i = 0; i < n; i++) a[i] = i; break;
            case "reverse":
                for (int i = 0; i < n; i++) a[i] = n - i; break;
            case "nearly":
            case "nearly-sorted":
                for (int i = 0; i < n; i++) a[i] = i;
                int swaps = Math.max(1, n / 100); // 1% random swaps
                for (int i = 0; i < swaps; i++) {
                    int x = rnd.nextInt(n), y = rnd.nextInt(n);
                    int tmp = a[x]; a[x] = a[y]; a[y] = tmp;
                }
                break;
            default: // random
                for (int i = 0; i < n; i++) a[i] = rnd.nextInt();
        }
        return a;
    }
}
