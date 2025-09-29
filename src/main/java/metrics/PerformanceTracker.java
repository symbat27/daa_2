package metrics;

public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long accesses = 0;
    private long startTimeNs = 0;
    private long elapsedNs = 0;

    public void reset() {
        comparisons = 0;
        swaps = 0;
        accesses = 0;
        startTimeNs = 0;
        elapsedNs = 0;
    }

    public void startTimer() { startTimeNs = System.nanoTime(); }
    public void stopTimer() {
        if (startTimeNs != 0) {
            elapsedNs += System.nanoTime() - startTimeNs;
            startTimeNs = 0;
        }
    }

    public void addComparison() { comparisons++; }
    public void addComparisons(long c) { comparisons += c; }
    public void addSwap() { swaps++; }
    public void addSwaps(long s) { swaps += s; }
    public void addAccess() { accesses++; }
    public void addAccesses(long a) { accesses += a; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getAccesses() { return accesses; }
    public long getElapsedNs() { return elapsedNs; }
}
