package phase1.algs.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 非基于比较的排序, 是完全的以空间换时间
 *  复杂度可以比O(n * log n)更低
 *
 * 基于比较的排序复杂度最低为O(n * log n)
 *
 * 计数排序: 用于对整数排序
 *
 * 问题:
 *     无法对负数排序
 *     及其浪费空间
 *     不稳定排序
 */
public class CountingSort extends Sortion{

    public static void sort(Comparable[] arr) {
        int max = (int) arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < (int) arr[i]) {
                max = (int) arr[i];
            }
        }
        int[] counts = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            counts[(int) arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] -- > 0) {
                arr[index++] = i;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int[] counts = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] -- > 0) {
                arr[index++] = i;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] sequence = new int[100];
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = new Random().nextInt(100);
        }
        for (int i = 0; i < sequence.length; i++) {
            System.out.print(sequence[i] + " ");
        }
        System.out.println();
        sort(sequence);
    }
}
