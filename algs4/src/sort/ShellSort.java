package sort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

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
 * @author allen
 */
public class ShellSort {
    public static void main(String[] args) {
//        insertionSort();
        sort1();
    }

    private static void sort1() {
        int[] arr = new int[]{99, 33, 1, 21, 27, 42, 71, 93, 41, 89};

        int h = 1;
        while (h < arr.length / 3) {
            h = h * 3 + 1;
        }

        while (h > 0) {
            // 插入排序
            int tmp = 0;
            for (int i = h; i < arr.length; i++) {
                
            }
            // 减小间隔
            h = (h - 1) / 3;

        }

        StdOut.println(h);

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
