package sort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Random;

/**
 * 希尔排序（壳排序）：
 *     1. 插入排序的问题：多次的移动
 *     2. 加大插入排序中元素之间的间隔，并对这些间隔的元素进行插入排序，使得数据可以大幅移动。
 *        当完成该间隔的排序后，希尔排序会减少数据的间隔再进行排序下去。 依次进行
 *     3. 希尔排序确保 左边不会有太多大的数据，右边不会有太多小的数据
 *     4. 间隔的计算h：
 *        间隔h初始值为1，通过h=3*h + 1来循环计算,最大值不大于数组大小
 *        间隔的减少：h = (h-1) / 3
 *
 * 递减增量排序：
 *     希尔排序把序列看成一个矩阵，分成m列，逐列进行排序
 *     m从某个数逐渐减为1
 *     当m为1时，整个序列将完全有序
 *
 * @author allen
 */
public class ShellSort {
    /*
    public static void main(String[] args) {
//        insertionSort();
        Random r = new Random();
        Integer[] arr = new Integer[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(100);
        }
        StdOut.println(Arrays.toString(arr));
        sort(arr);
        StdOut.println(Arrays.toString(arr));
    }

     */

    public static void sort(Comparable[] arr) {
    }

    /**
     * 插入排序：如果数组右边的少数几个很小，则移动次数会很大
     */
    private static void insertionSort() {
        int[] arr = new int[]{99, 33, 19, 5, 27, 42, 71, 93, 41, 1};
        StdOut.println(Arrays.toString(arr));
        StdOut.println();

        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            int countTemp = 0;
            while (j >= 0 && arr[j] > tmp) {
                arr[j + 1] = arr[j];
                j--;
                countTemp ++;
            }
            System.out.println("countTemp: " + i + " =>  " + countTemp);
            arr[j + 1] = tmp;
        }
        StdOut.println(Arrays.toString(arr));

    }

}
