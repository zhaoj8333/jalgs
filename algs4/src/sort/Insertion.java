package sort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Insertion sort:
 *
 * 1. it is much less efficient on large lists than quicksort, heapsort, or merge sort
 * 2. advantages:
 *    Simple implementation
 *    Efficient for small data sets, much like other quadratic sorting algorithms
 *    More efficient than selection sort and bubble sort
 *    Adaptive: efficient for substantially sorted data sets
 *    Stable:
 *    In-place: O(1) space complexity
 *    Online:
 */
public class Insertion {

    private static int count;

    public static void main(String[] args) {
//        Random random = new Random();
//        int num = 10;
        int[] arr = new int[10];
//        for (int i = 0; i < 10; i++) {
//            arr[i] = random.nextInt(100);
//        }

        StdOut.println("========================================");
//        StdOut.println(Arrays.toString(arr));
//        sort1(arr);
//        sort2(arr);
//        StdOut.println("count 1: " + count);

//        sort3();
//        StdOut.println("count: " + count);
//        sort4();
//        StdOut.println("count: " + count);

        sortDraw();
    }

    public static void sortDraw() {
        int[] arr = new int[]{40, 99, 60, 24, 33, 1, 21, 27, 42, 71, 93, 41, 89};
        int len = arr.length;
        SortDraw.setScale(arr);

//        doDraw(arr, 0, false, false);

        for (int i = 1; i < len; i++) {
            int tmp = arr[i];
            int j   = i - 1;
            while (j >= 0 && arr[j] > tmp) {
                arr[j + 1] = arr[j];
                j--;
//                StdOut.println(j);
            }
            arr[j + 1] = tmp;

            boolean clear = true;
            if (i == len - 1) {
                clear = false;
            }
            SortDraw.doDraw(arr, true, clear);
//            StdOut.println(Arrays.toString(arr));
        }
    }

    public static void sort4() {
        int[] arr = new int[]{99, 33, 1, 21, 27, 42, 71, 93, 41, 89};
        StdOut.println(Arrays.toString(arr));
        StdOut.println();
        count = 0;

        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                count++;
                /**
                 * 此处总是在交换元素
                 */
                int tmp = arr[j];
                arr[j]  = arr[j - 1];
                arr[j - 1] = tmp;
            }
            StdOut.println(Arrays.toString(arr));
        }

        StdOut.println();
        StdOut.println(Arrays.toString(arr));
    }

    public static void sort3() {
//        int[] arr = new int[]{99, 33, 1, 21, 27, 42, 71, 93, 41, 89};
        int[] arr = new int[]{1, 21, 27, 33, 42, 41, 89, 71, 93, 99};
        StdOut.println(Arrays.toString(arr));
        StdOut.println();
        count = 0;
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j   = i - 1;
            while (j >= 0 && arr[j] > tmp) {
                count++;
                /**
                 * 将较大的元素往右移动
                 */
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = tmp;

            StdOut.println(Arrays.toString(arr));
        }

        StdOut.println();
        StdOut.println(Arrays.toString(arr));
    }

    public static void sort2(int[] arr) {
        arr = new int[]{99, 33, 1, 21, 27, 42, 71, 93, 41, 89};
        StdOut.println("before: " + Arrays.toString(arr));
        StdOut.println();
        int tmp = 0;

        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];
            int j   = i - 1;
            // move elements of arr[0 ... i-1] that are greater than key, to the right position
            while (j >= 0 && arr[j] > key) { // arr[j] > key 只右移 比当前大的元素
                arr[j + 1] = arr[j]; // arr[i] = arr[i - 1]
                j--;                 // 先右移右边的，后右移左边的
            }
//            StdOut.println("j: " + j);
            arr[j + 1] = key;
//            StdOut.println("j : " + j);
//            StdOut.println(Arrays.toString(arr));
//            System.exit(0);
        }

        StdOut.println(Arrays.toString(arr));
    }

    /**
     *  Loop from i = 1 to n - 1
     *  Pick element arr[i] and insert it to sorted sequence arr[0 ... i - 1]
     */
    public static void sort1(int[] arr) {
        count = 0;
        arr = new int[]{38, 99, 1, 21, 27, 42, 71, 93, 41, 89};
        StdOut.println("before: " + Arrays.toString(arr));
        StdOut.println();
        int tmp = 0;

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                count++;
                if (arr[j - 1] > arr[j]) {
                    tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
            StdOut.println(Arrays.toString(arr));

        }

        StdOut.println();
        StdOut.println("after : " + Arrays.toString(arr));
    }
}
