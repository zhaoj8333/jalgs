package phase1.algs.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

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
    private static Comparable[] counting;
    private static double countingDuration;

//    private static Stopwatch sw;
//    private static void resetSw() {
//        sw = new Stopwatch();
//    }
    public HashMap<String, Boolean> algs = new HashMap<>();

    public void getRandomSequences(int length) {
        Integer[] sequence = new Integer[length];
        for (int i = 0; i < length; i++) {
            sequence[i] = new Random().nextInt(length) /* + StdRandom.uniform(length) */;
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
        Boolean isBubble = algs.get("Bubble");
        if (isBubble != null && isBubble) {
            bubble = Arrays.copyOf(sequence, sequence.length);
        }
        Boolean isCounting = algs.get("Counting");
        if (isCounting != null && isCounting) {
            counting = Arrays.copyOf(sequence, sequence.length);
        }
    }

    public void test(int testTimes, int length) {
        String[] tested = new String[]{
//                "Bubble",
                "Quick",
//                "Selection",
//                "Insertion",
                "Heap",
                "Merge",
                "Shell",
                "Counting"
        };
        for (int i = 0; i < tested.length; i++) {
            algs.put(tested[i], true);
        }
//        testTimes = 5;

        for (int i = 0; i < testTimes; i++) {
            getRandomSequences(length);
            Boolean isInsertion = algs.get("Insertion");
            if (isInsertion != null && isInsertion) {
//                resetSw();
                InsertionSort.sort(insertion);
//                insertionDuration += sw.elapsedTime();
            }
            Boolean isShell = algs.get("Shell");
            if (isShell != null && isShell) {
//                resetSw();
                ShellSort.sort(shell);
//                shellDuration += sw.elapsedTime();
            }
            Boolean isSelect = algs.get("Selection");
            if (isSelect != null && isSelect) {
//                resetSw();
                SelectionSort.sort(selection);
//                selectionDuration += sw.elapsedTime();
            }
            Boolean isMerge = algs.get("Merge");
            if (isMerge != null && isMerge) {
//                resetSw();
                MergeSort.sort(merge);
//                mergeDuration += sw.elapsedTime();
            }
            Boolean isQuick = algs.get("Quick");
            if (isQuick != null && isQuick) {
//                resetSw();
                QuickSort.sort(quick);
//                quickDuration += sw.elapsedTime();
            }
            Boolean isHeap = algs.get("Heap");
            if (isHeap != null && isHeap) {
//                resetSw();
                HeapSort.sort(heap);
//                heapDuration += sw.elapsedTime();
            }
            Boolean isBubble = algs.get("Bubble");
            if (isBubble != null && isBubble) {
//                resetSw();
                BubbleSort.sort(bubble);
//                bubbleDuration += sw.elapsedTime();
            }
            Boolean isCount = algs.get("Counting");
            if (isCount != null && isCount) {
//                resetSw();
                CountingSort.sort(counting);
//                countingDuration += sw.elapsedTime();
            }
        }

//        System.out.println("bubble   : avg time: " + (bubbleDuration / testTimes));
//        Assert.assertTrue(Sortion.isSorted(bubble));

//        System.out.println("insert   : avg time: " + (insertionDuration / testTimes));
//        Assert.assertTrue(Sortion.isSorted(insertion));
//        System.out.println("select   : avg time: " + (selectionDuration / testTimes));
//        Assert.assertTrue(Sortion.isSorted(selection));
        System.out.println("shell    : avg time: " + (shellDuration / testTimes));
        System.out.println("heap     : avg time: " + (heapDuration / testTimes));
        System.out.println("merge    : avg time: " + (mergeDuration / testTimes));
        System.out.println("count    : avg time: " + (countingDuration / testTimes));
        System.out.println("quick    : avg time: " + (quickDuration / testTimes));

    }

    public static void main(String[] args) {
        SortTest sort = new SortTest();
        sort.test(10, 50000);
//        System.out.println("merge    : " + Arrays.toString(merge));
    }
}
