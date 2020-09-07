package algs.sort;

import edu.princeton.cs.algs4.*;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("all")
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
    public HashMap<String, Boolean> algs = new HashMap<>();

    public void getRandomSequences(int length) {
        Integer[] sequence = new Integer[length];
        for (int i = 0; i < length; i++) {
            sequence[i] = StdRandom.uniform(length) /* + StdRandom.uniform(length) */;
//            sequence[i] = length - i;
        }
        Boolean isInsertion = algs.get("Insertion");
        if (isInsertion != null && isInsertion) {
            insertion = Arrays.copyOf(sequence, sequence.length);
        }
        Boolean isShell = algs.get("Shell");
        if (isShell != null && isShell) {
            shell = Arrays.copyOf(sequence, sequence.length);
        }
        Boolean isSelect = algs.get("Selection");
        if (isSelect != null && isSelect) {
            selection = Arrays.copyOf(sequence, sequence.length);
        }
        Boolean isMerge = algs.get("Merge");
        if (isMerge != null && isMerge) {
            merge = Arrays.copyOf(sequence, sequence.length);
        }
        Boolean isQuick = algs.get("Quick");
        if (isQuick != null &&  isQuick) {
            quick = Arrays.copyOf(sequence, sequence.length);
        }
        Boolean isHeap = algs.get("Heap");
        if (isHeap != null && isHeap) {
            heap = Arrays.copyOf(sequence, sequence.length);
        }
        Boolean isBubble = algs.get("Insertion");
        if (isBubble != null && isBubble) {
            bubble = Arrays.copyOf(sequence, sequence.length);
        }
    }

    public void test(int testTimes, int length) {
        String[] tested = new String[]{"Bubble", "Selection", "Insertion", "Heap", "Merge", "Shell"};
        for (int i = 0; i < tested.length; i++) {
            algs.put(tested[i], true);
        }
        testTimes = 1;

        for (int i = 0; i < testTimes; i++) {
            getRandomSequences(length);
            Boolean isInsertion = algs.get("Insertion");
            if (isInsertion != null && isInsertion) {
                resetSw();
                InsertionSort.sort(insertion);
                insertionDuration += sw.elapsedTime();
            }
            Boolean isShell = algs.get("Shell");
            if (isShell != null && isShell) {
                resetSw();
                ShellSort.sort(shell);
                shellDuration += sw.elapsedTime();
            }
            Boolean isSelect = algs.get("Selection");
            if (isSelect != null && isSelect) {
                resetSw();
                SelectionSort.sort(selection);
                selectionDuration += sw.elapsedTime();
            }
            Boolean isMerge = algs.get("Merge");
            if (isMerge != null && isMerge) {
                resetSw();
                MergeSort.sort(merge);
                mergeDuration += sw.elapsedTime();
            }
            Boolean isQuick = algs.get("Quick");
            if (isQuick != null && isQuick) {
                resetSw();
                QuickSort.sort(quick);
                quickDuration += sw.elapsedTime();
            }
            Boolean isHeap = algs.get("Heap");
            if (isHeap != null && isHeap) {
                resetSw();
                HeapSort.sort(heap);
                heapDuration += sw.elapsedTime();
            }
            Boolean isBubble = algs.get("Insertion");
            if (isBubble != null && isBubble) {
                resetSw();
                BubbleSort.sort(bubble);
                bubbleDuration += sw.elapsedTime();
            }
        }

        StdOut.println("bubble   : avg time: " + (bubbleDuration / testTimes));
        Assert.assertTrue(Sortion.isSorted(bubble));

        StdOut.println("insert   : avg time: " + (insertionDuration / testTimes));
        Assert.assertTrue(Sortion.isSorted(insertion));
        StdOut.println("select   : avg time: " + (selectionDuration / testTimes));
        Assert.assertTrue(Sortion.isSorted(selection));
        StdOut.println("shell    : avg time: " + (shellDuration / testTimes));
        Assert.assertTrue(Sortion.isSorted(merge));
//        StdOut.println("quick    : avg time: " + (quickDuration / testTimes));
        StdOut.println("heap     : avg time: " + (heapDuration / testTimes));
//        StdOut.println("heap: " + Arrays.toString(heap));
        Assert.assertTrue(Sortion.isSorted(heap));
        StdOut.println("merge    : avg time: " + (mergeDuration / testTimes));
        Assert.assertTrue(Sortion.isSorted(merge));

    }

    public static void main(String[] args) {
        SortTest sort = new SortTest();
        sort.test(5, 10000);
//        StdOut.println("merge    : " + Arrays.toString(merge));
    }
}
