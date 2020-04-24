package sort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

/**
 * Selection Sort:
 *  1. An in-place comparison sorting algorithm, O2 time complexity, and generally perform worse than insertion sort;
 *  2. Selection Sort divides the input list into to two parts:
 *      a sorted sublist which is built up from left to right
 *      remaining unsorted items that occupy the rest of the list
 *  3. It proceeds by finding the smallest(or largest) element in the unsorted sublist, swapping it with the leftmost
 *  unsorted element
 *  4. It makes the minimum possible number of swaps, n-1 in the worst case
 *
 *  5. Analyze:
 *      Selecting the minimum requires scanning n elements (n-1) and then swapping it into the first position.
 *      The total number of comparison is :
 *      (n - 1) + (n - 2) + ... + 1 = (n^2 - n) / 2
 *
 *  6. Selection sort almost always outperforms bubble sort and gnome sort(侏儒排序)
 *  7. Selection sort must scan all remaining elements to find the n - 1 element
 *  8. Selection sort is preferable to insertion sort in terms of writes(O(n) swaps O(n^2) swaps), This can be important if
 *      writes are significantly more expensive than reads, such as EEPROM or Flash memory
 *  9. Selection sort is greatly outperformed on larger arrays by O(n log n) such as merge sort.
 *  10. Variants: HeapSort
 *  11. cocktail sort: bidirectional sort
 *  12. Selection sort can be implemented as a stable sort.
 */
public class SelectionSort /**implements Comparable<Selection> */{

    private static int count;

    private final String str = "";

    private final int num = 0;

//    @Override
//    public int compareTo(String a) {
//        StdOut.println(a.charAt());
//
////        if (this.num > that.num) {
////            return 1;
////        } else {
////            return 0;
////        }
//        return 0;
//    }

    public static String[] generateString(int num) {
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxzy";
        String[] arr  = new String[num];
        Random random = new Random();

        for (int i = 0; i < num; i++) {
            int randomInt = random.nextInt(str.length() - 1);
            arr[i] = str.substring(randomInt, randomInt + 1);
        }

        return arr;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int num   = 10;
        int[] arr = new int[num];
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(100); // 0 - 99
        }

//        int[] arr = StdIn.readAllInts();
//        StdOut.println("arr-length: " + arr.length);
//        StdOut.println("before: " + Arrays.toString(arr));
//
        sort1(arr);
//        StdOut.println("count 1 : " + count);
//        String[] arr = generateString(10);
//        StdOut.println(Arrays.toString(arr));
//        sort2(arr);
//        StdOut.println("count 2 : " + count);

//        StdOut.println("========================================");
//        StdOut.println(Arrays.toString(arr));
//       冒泡排序 与 选择排序
//       选择排序交换次数比较少，效率较高
    }

    public static void sort2(String[] arr) {
        count = 0;
        int index = 0;
        String tmp = "";
        for (int i = 0; i < arr.length; i++) {
            index = i;
            for (int j = i + 1; j < arr.length; j++) {
                count++;
                if (arr[j].compareTo(arr[j + 1]) == 0) {
                    index = j;
                }
            }
            if (index != i) {
                swap(arr, index, i);
            }
        }
        StdOut.println(arr);
    }

    public static void swap(String[] arr, int m, int n) {
        String temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }

    public static void sort1(int[] arr) {
        count = 0;

//        arr = new int[]{10, 1, 52, 60, 22, 14, 19, 42};
//        StdOut.println("before: " + Arrays.toString(arr));
//        StdOut.println();
        SortDraw.setScale(arr);

        int index, tmp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            index = i;
            for (int j = i + 1; j < arr.length; j++) {
                count++;
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            /**
             * 交换代码在内层循环之外，所以交换次数总是 N
             */
            if (i != index) {
                tmp = arr[i];
                arr[i] = arr[index];
                arr[index] = tmp;
            }
//            StdOut.println("i: " + i + " " + Arrays.toString(arr));
            SortDraw.doDraw(arr,true, true);
        }
//        StdOut.println();
//        StdOut.println("after:  " + Arrays.toString(arr));
    }

}


/*
原顺序： [10, 1, 52, 60, 22, 14, 19, 42]

i: 0 [1, 10, 52, 60, 22, 14, 19, 42]  1   [0-7]
i: 1 [1, 10, 52, 60, 22, 14, 19, 42]  10  [1-7]
i: 2 [1, 10, 14, 60, 22, 52, 19, 42]  14  [2-7]
i: 3 [1, 10, 14, 19, 22, 52, 60, 42]  19  [3-7]
i: 4 [1, 10, 14, 19, 22, 52, 60, 42]  22  [4-7]
i: 5 [1, 10, 14, 19, 22, 42, 60, 52]  42  [5-7]
i: 6 [1, 10, 14, 19, 22, 42, 52, 60]  52  [6-7]
*/
