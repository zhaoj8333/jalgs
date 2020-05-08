package sort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 选择排序的优化版本
 */
public class HeapSort {
    public static void main(String[] args) {
        Integer[] array = new Integer[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * 100);
        }
        StdOut.println(Arrays.toString(array));
//        sort(array);
        StdOut.println(Arrays.toString(array));
    }

    public static void sort(Integer[] array) {

    }
}
