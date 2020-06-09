package sort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 冯诺依曼提出
 */
public class MergeSort {
    private static int[] arr;

    public static void main(String[] args) {
        MergeSort merge = new MergeSort();
//        merge.sort();

    }

    public static void sort(Comparable[] arr) {
        StdOut.println(Arrays.toString(arr));
    }

    /**
     * 对 [begin, end) 范围内的数据进行归并排序
     *     左闭右开
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    /**
     *  将 [begin, end)和[mid,end)范围的两个序列合并
     */
    private void merge(int begin, int mid, int end) {

    }
}

