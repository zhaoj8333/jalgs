package sort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author allen
 */
public class MergeSort extends Sortion {
    private static Comparable[] leftArr;
    private static Comparable[] array;

    public static void sort(Comparable[] arr) {
        array = arr;
        leftArr = new Comparable[array.length >> 1];
        sort(0, arr.length);
        // 整个： [0, array.length)
        // 左边： [0, mid)
        //        mid = array.length >> 1
        // 右边： [mid, array.length)
    }

    public static void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    private static int compare(Comparable a, Comparable b) {
        return a.compareTo(b);
    }

    /**
     * 将[begin, end) 和 [mid, end)合并
     */
    private static void merge(int begin, int mid, int end) {
        int li = 0,   le = mid - begin; // le: 左边数组长度
        int ri = mid, re = end;         // re:
        int ai = begin;
        for (int i = li; i < le; i++) {
            leftArr[i] = array[begin + i];
        }
        // 如果左边还没有结束
        while (li < le) {
            if (ri < re && compare(array[ri], leftArr[li]) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArr[li++];
            }
        }
    }
}
