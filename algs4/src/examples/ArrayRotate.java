package examples;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ArrayRotate {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
//        moveRight1(arr, 4);
//        moveRight2(arr, 4);
        moveByReverse(arr, 4);
    }

    public static void moveByReverse(int[] arr, int moves) {
        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, 2);
        reverse(arr, 3, 6);
        StdOut.println(Arrays.toString(arr));
    }

    /**
     * 使用双指针 更简单
     * @param arr
     * @param start
     * @param end
     */
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;

            start ++;
            end --;
        }
    }

    /**
     * ??????????????????/
     * @param arr
     * @param moves
     */
    public static void moveRight2(int[] arr, int moves) {
        int[] temp = new int[moves];
        int index = moves - 1;
        for (int i = arr.length - 1; i >= arr.length - moves; i--) {
            temp[index] = arr[i];
            index--;
            arr[i] = arr[i - moves + 1];
        }
        for (int i = 0; i < moves; i++) {
            arr[i] = temp[i];
        }
        StdOut.println(Arrays.toString(temp));
        StdOut.println(Arrays.toString(arr));
    }

    /**
     * 给定一个数组，将数组元素向右移动 n 个位置，其中n为非负整数
     *
     * @param arr
     * @param moves
     */
    public static void moveRight1(int[] arr, int moves) {
        int start, end;
        for (int i = 0; i < moves; i++) {
            end = arr[arr.length - 1];
            for (int j = 0; j < arr.length; j++) {
                start = arr[j];
                arr[j] = end;
                end = start;
            }
        }
        StdOut.println(Arrays.toString(arr));
    }
}
