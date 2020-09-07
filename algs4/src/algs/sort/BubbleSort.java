package algs.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序：
 *
 * 1. 冒泡排序不是一种实际中会使用的排序算法
 * 2. 冒泡排序相对于其他排序唯一（除了插入排序）优势是：
 *    有能力发现是否是有效排序
 *    如果已经排序，冒泡排序复杂度为O(n)
 *    冒泡排序在处理大量数据时应该避免使用
 *
 *  The only advantage of bubble algs.sort is the ability to detect whether the array is sorted
 *
 * 3. The distance and direction that elements must move during the algs.sort determine bubble algs.sort's performance because elements move in different directions at different speeds
 * 4. And element that must move toward the end can move quickly because it can take part in successive swaps
 *    the largest will win every swap(Rabbits)
 * 5. An element that must move toward the beginning of the list can't move faster than one step per pass, so elements move toward the beginning very slowly.
 *    If the smallest is at the end, it will take n - 1 passes to move to the beginning.(Turtles)
 *
 * 6. Improvement:
 *    CockTail algs.sort: bi-direction bubble algs.sort
 *    Comb algs.sort    : compares elements separated by large gaps, and can move turtles extremely quickly
 *
 * 7. Bubble Sort also interacts poorly with modern CPU hardware.
 *    It produces at least twice as many writes as insertion algs.sort, twice as many cache misses, and asymptotically more branch mispredictions
 * 8. Bubble Sort is popular for its capability to detect a very small error, like swap of just two elements in almost-sorted arrays and fix it with just linear complexity
 *
 * @author allen
 */
public class BubbleSort extends Sortion {

    private static int count;

    public static void main(String[] args) {
        Random random = new Random();
        int num = 10;
        Integer[] arr1 = new Integer[num];
        Integer[] arr2;
        Integer[] arr3;

        for (int i = 0; i < num; i++) {
            Integer tmp = Math.round(random.nextInt(num << 1));
            arr1[i] = tmp;
        }
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
//        arr1 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        arr1 = new Integer[] {0, 4, 5, 1, 3, 10, 12, 14, 17, 19};
//        show(arr1);
//        sortOptimize(arr1);
//        show(arr1);

    }

    /**
     * 对于部分有序的情况
     *
     *  如果序列尾部已经局部有序，可以记录最后一次交换的位置，减少比较次数
     *
     *  排序算法稳定性：
     *      如果两个相等的元素，排序后位置不变，则是稳定算法
     */
    public static void sortOptimize(Comparable[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            int sortedIndex = 0; // 只能赋为 0
            for (int begin = 1; begin <= end; begin++) {
                if (less(arr[begin], arr[begin - 1])) {
                    swap(arr, begin, begin - 1);
                    sortedIndex = begin;
                }
                count++;
            }
            end = sortedIndex;
        }
    }

//    public static void algs.sort(Comparable[] arr) {
//        sortOptimize(arr);
//    }

    public static void sort(@NotNull Comparable[] arr) {
        int len = arr.length;
        int end = len;
        boolean isSorted = true;
        for (int i = 0; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                if (less(arr[j], arr[i])) {
                    isSorted = false;
                    swap(arr, i, j);
                }
            }
            if (isSorted) {
                return;
            }
        }
    }
}
