package phase1.algs.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * 希尔给出的步长序列为: n / 2 ^ k
 *
 * @author allen
 */
public class ShellSort extends Sortion {
    /*
    public static void main(String[] args) {
//        insertionSort();
        Random r = new Random();
        Integer[] arr = new Integer[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(100);
        }
        System.out.println(Arrays.toString(arr));
        phase1.algs.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

     */

    public static void sort(Comparable[] arr) {
        final List<Integer> steps = shellStepSequence(arr);
//        System.out.println("step:" + steps);
        for (Integer step : steps) {
            sort(arr, step);
        }
    }

    /**
     * 假设元素在第col列, 第row行, 步长为step
     * 则这个元素在数组中的索引为: col + row * step
     */
    private static void sort(Comparable[] arr, int step) {
        for (int col = 0; col < step; col++) {
            for (int begin = col + step; begin < arr.length; begin += step) {
                int cur = begin;
                while (cur > col && less(arr[cur], arr[cur - step])) {
                    swap(arr, cur, cur - step);
                    cur -= step;
                }
            }
        }
    }

    private static List<Integer> shellStepSequence(Comparable[] arr) {
        List<Integer> stepSequence = new ArrayList<>();
        int step = arr.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }

    /**
     * 插入排序：如果数组右边的少数几个很小，则移动次数会很大
     */
    private static void insertionSort() {
        int[] arr = new int[]{99, 33, 19, 5, 27, 42, 71, 93, 41, 1};
        System.out.println(Arrays.toString(arr));
        System.out.println();

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
        System.out.println(Arrays.toString(arr));

    }

}
