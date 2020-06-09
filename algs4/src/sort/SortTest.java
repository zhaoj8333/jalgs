package sort;

import edu.princeton.cs.algs4.*;
import org.junit.Assert;

import java.util.Arrays;

public class SortTest {

    private static Comparable[] bubble;
    private static double bubbleDuration;
    private static Comparable[] selection;
    private static double selectionDuration;
    private static Comparable[] shell;
    private static double shellDuration;
    private static Comparable[] merge;
    private static double mergeDuration;
    private static Comparable[] quick;
    private static double quickDuration;
    private static Comparable[] heap;
    private static double heapDuration;
    private static Comparable[] insertion;
    private static double insertionDuration;

    private static Stopwatch sw;

    private static void resetSw() {
        sw = new Stopwatch();
    }

    private static String[] algs;

    public static void getRandomSequences(String[] algs, int length) {
        Integer[] sequence = new Integer[length];
        for (int i = 0; i < length; i++) {
            sequence[i] = StdRandom.uniform(length);
        }
        for (String alg : algs) {
            if ("Insertion".equals(alg)) {
                insertion = Arrays.copyOf(sequence, sequence.length);
            } else if ("Selection".equals(alg)) {
                selection = Arrays.copyOf(sequence, sequence.length);
            } else if ("Shell".equals(alg)) {
                shell = Arrays.copyOf(sequence, sequence.length);
            } else if ("Merge".equals(alg)) {
                merge = Arrays.copyOf(sequence, sequence.length);
            } else if ("Quick".equals(alg)) {
                quick = Arrays.copyOf(sequence, sequence.length);
            } else if ("Heap".equals(alg)) {
                heap = Arrays.copyOf(sequence, sequence.length);
            } else if ("Bubble".equals(alg)) {
                bubble = Arrays.copyOf(sequence, sequence.length);
            }
        }
    }

    public static void test(int testTimes, int length) {
        algs = new String[]{"Bubble", "Selection", "Insertion"};

        for (int i = 0; i < testTimes; i++) {
            getRandomSequences(algs, length);
            for (String alg : algs) {
                resetSw();
                if ("Insertion".equals(alg)) {
                    InsertionSort.sort(insertion);
                    insertionDuration += sw.elapsedTime();
                } else if ("Selection".equals(alg)) {
                    SelectionSort.sort(selection);
                    selectionDuration += sw.elapsedTime();
                } else if ("Shell".equals(alg)) {
                    ShellSort.sort(shell);
                    shellDuration += sw.elapsedTime();
                } else if ("Merge".equals(alg)) {
                    MergeSort.sort(merge);
                    mergeDuration += sw.elapsedTime();
                } else if ("Quick".equals(alg)) {
                    QuickSort.sort(quick);
                    quickDuration += sw.elapsedTime();
                } else if ("Heap".equals(alg)) {
                    HeapSort.sort(heap);
                    heapDuration += sw.elapsedTime();
                } else if ("Bubble".equals(alg)) {
                    BubbleSort.sort(bubble);
                    bubbleDuration += sw.elapsedTime();
                }
            }

        }
        testTimes = 1;
        StdOut.println("insert   : avg time: " + (insertionDuration / testTimes));
        Assert.assertTrue(BubbleSort.isSorted(insertion));
        StdOut.println("select   : avg time: " + (selectionDuration / testTimes));
        Assert.assertTrue(BubbleSort.isSorted(selection));
        StdOut.println("shell    : avg time: " + (shellDuration / testTimes));
        StdOut.println("merge    : avg time: " + (mergeDuration / testTimes));
        StdOut.println("quick    : avg time: " + (quickDuration / testTimes));
        StdOut.println("heap     : avg time: " + (heapDuration / testTimes));
        StdOut.println("bubble   : avg time: " + (bubbleDuration / testTimes));
        Assert.assertTrue(BubbleSort.isSorted(bubble));
    }

    public static void main(String[] args) {
        test(5, 5000);
    }
}
