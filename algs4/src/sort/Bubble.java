package sort;

import edu.princeton.cs.algs4.StdOut;

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
 * 3. The distance and direction that elements must move during the sort determine bubble sort's performance because elements move in different directions at different speeds
 * 4. And element that must move toward the end can move quickly because it can take part in successive swaps
 *    the largest will win every swap(Rabbits)
 * 5. An element that must move toward the beginning of the list can't move faster than one step per pass, so elements move toward the beginning very slowly.
 *    If the smallest is at the end, it will take n - 1 passes to move to the beginning.(Turtles)
 *
 * 6. Improvement:
 *    CockTail sort: bi-direction bubble sort
 *    Comb sort    : compares elements separated by large gaps, and can move turtles extremely quickly
 *
 * 7. Bubble Sort also interacts poorly with modern CPU hardware.
 *    It produces at least twice as many writes as insertion sort, twice as many cache misses, and asymptotically more branch mispredictions
 * 8. Bubble Sort is popular for its capability to detect a very small error, like swap of just two elements in almost-sorted arrays and fix it with just linear complexity
 *
 */
public class Bubble {
    private static int count = 0;

    public static void main(String[] args) {
//        int[] arr = StdIn.readAllints();
//        StdOut.println("arr - length: " + arr.length);
//        StdOut.println("before: " + Arrays.toString(arr));
        Random random = new Random();
        int num = 10;
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = Math.round(random.nextInt(50));
        }
        StdOut.println("before: " + Arrays.toString(arr));
//        sort1(arr);
//        sort1Optimize(arr);
//        sortPuzzle(arr);
        sortDefine(arr);

//        sort2(arr);
//        StdOut.println("count: " + count);

//        sort3(arr);
//        StdOut.println("count: " + count);
    }

    /**
     * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 针对所有的元素重复以上的步骤，除了最后一个；
     * 重复步骤1~3，直到排序完成。
     */
    public static void sortDefine(int[] arr) {
//        int tmp;
//        for (int i = arr.length - 1; i > 0; i--) {
//            for (int j = 0; j < i; j++) {
//                count++;
//                if (arr[j + 1] < arr[j]) {
//                    tmp    = arr[j + 1];
//                    arr[j] = tmp;
//                    arr[j + 1] = arr[j];
//                }
//            }
//        }

        count = 0;
        int tmp;
        arr = new int[]{5, 33, 1, 13, 9, 2, 26, 30, 26, 28};
        boolean sorted = true; // 整体是否有序
        int lastSwap   = arr.length - 1; // 最后交换位置
        // 局部有序：
        // 在遍历过程中可以记下最后一次发生交换事件的位置, 下次的内层循环就到这个位置终止, 可以节约多余的比较操作.
        ALL_SORTED:
        for (int i = 0; i < arr.length - 1; i++) {
            int thisTurnSwap = lastSwap;
            // 如果内循环中的一轮没有发生交换，则数组有序
            boolean innerSwap = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                count++;
                if (arr[j] > arr[j + 1]) {
                    sorted = false;
                    tmp        = arr[j];
                    arr[j]     = arr[j + 1];
                    arr[j + 1] = tmp;
                    lastSwap   = j;
                    innerSwap = true;
                }

                StdOut.println(i + " : " + Arrays.toString(arr));
            }
//            StdOut.println("lastSwapped: " + lastSwapped);
            // 整体有序：退出循环
            if (sorted == true) {
                break ALL_SORTED;
            }
            System.out.println("thisTurnSwap: " + thisTurnSwap);
            if (lastSwap == thisTurnSwap) {
//                break;
            }
            if (innerSwap == false) {
                break;
            }
        }
        /*
            // [5, 1, 4, 2, 8]

            // first
            0 : [1, 5, 4, 2, 8]
            0 : [1, 4, 5, 2, 8]
            0 : [1, 4, 2, 5, 8]
            0 : [1, 4, 2, 5, 8]
            // second
            1 : [1, 4, 2, 5, 8]
            1 : [1, 2, 4, 5, 8]
            1 : [1, 2, 4, 5, 8]
            // third
            2 : [1, 2, 4, 5, 8]
            2 : [1, 2, 4, 5, 8]
            // 通过innerSwap可以优化掉以下的再次遍历
            3 : [1, 2, 4, 5, 8]
        */
        StdOut.println("count:  " + count);
        StdOut.println("after:  " + Arrays.toString(arr));
    }

    public static void wikiExample() {
        int[] arr = { 5, 1, 4, 2, 8};
        int   n   = arr.length;
        for (int i = 0; i < n; i++) {

        }
    }

    public static void sortPuzzle(int[] arr) {
        count = 0;
        int tmp = 0;
        int a = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            tmp = arr[i];
            /**
             * 冒泡排序： 是依次拿出数组中的一个元素，与其左边或右边的元素依次比较，如果大于(或小于)则调换位置
             * 1. 在一下内循环中，tmp的值一直是原始的未排序前的arr[i]，排序后不会做任何改变
             */
            for (int j = i + 1; j < arr.length; j++) {
                count++;
                if (tmp > arr[j]) {
                    /**
                     * 5. 就会起到应有的作用，否则tmp = arr[i]无法起到排序的作用
                     */
                    a = arr[j];
                    arr[j] = arr[i];
                    arr[i] = a;
                    /**
                     * 6. 此处，如果调换了被比较的两个元素位置，相应的tmp(比较的基准值)也应该响应发生变化，最好的就是不改变，直接使用arr[i]即可，防止增加荣誉
                     */
                    tmp = a;
                }
            }
        }
        StdOut.println("count:  " + count);
        StdOut.println("after:  " + Arrays.toString(arr));
    }

    /**
     * sort1
     * @param arr
     * @description: (arr.length + 1) * (arr.length) / 2
     */
    public static void sort1(int[] arr) {
        count = 0;
        int tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                count ++;
                if (arr[i] > arr[j]) {
                    /**
                     * 2. 如果此处arr[0] 与 arr[1] 调换了位置，则arr[i]就不是原来的arr[0]了,而是原来的arr[1]
                     * 也即是说，比较的基准值仍然是原来的值，但是这个基准值的索引却发生了变化，也就是位置发生了变化
                     * 而指向原来的arr[0]的tmp的指向也应该发生变化
                     *
                     * 下次继续比较时，比较的将是 arr[1]与剩余元素进行比较
                     * 也就是说 比较的基准值的位置可能在一直的不停的变化中
                     */
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
                /**
                 * 3. 如果arr[0] 与 arr[1]没有调换位置，则比较的基准值的位置不会改变
                 *
                 */
            }
        }

        StdOut.println("after:  " + Arrays.toString(arr));
    }

    public static void sort3(int[] arr) {
        count = 0;
        int tmp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                count++;
                if (arr[j] < arr[j - 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }

        StdOut.println("sort3:  " + Arrays.toString(arr));
    }

    public static void sort2(int[] arr) {
        count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                count ++;
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        StdOut.println(Arrays.toString(arr));
    }

    public static void sort1Optimize(int[] arr) {
        count = 0;
//        boolean isSorted = true;
        int tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                count++;
                if (arr[i] > arr[j]) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;

//                    isSorted = false;
                }
            }
//            如果整体有序就不用再排序
//            if (isSorted == true) {
//                return;
//            }
        }
        StdOut.println("after   " + Arrays.toString(arr));
    }
}
