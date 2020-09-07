package algs.sort;

import java.util.Random;

/**
 * Selection Sort:
 *  1. An in-place comparison sorting algorithm, O2 time complexity, and generally perform worse than insertion algs.sort;
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
 *  6. Selection algs.sort almost always outperforms bubble algs.sort and gnome algs.sort(侏儒排序)
 *  7. Selection algs.sort must scan all remaining elements to find the n - 1 element
 *  8. Selection algs.sort is preferable to insertion algs.sort in terms of writes(O(n) swaps O(n^2) swaps), This can be important if
 *      writes are significantly more expensive than reads, such as EEPROM or Flash memory
 *  9. Selection algs.sort is greatly outperformed on larger arrays by O(n log n) such as merge algs.sort.
 *  10. Variants: HeapSort
 *  11. cocktail algs.sort: bidirectional algs.sort
 *  12. Selection algs.sort can be implemented as a stable algs.sort.
 * @author allen
 */
public class SelectionSort extends Sortion {

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

    /*
    public static void main(String[] args) {
        Random random = new Random();
        int num   = 10;
        Integer[] arr = new Integer[num];
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(100); // 0 - 99
        }

//        int[] arr = StdIn.readAllInts();
//        StdOut.println("arr-length: " + arr.length);
//        StdOut.println("before: " + Arrays.toString(arr));

//        show(arr);
//        algs.sort(arr);
//        show(arr);
        //        sort1(arr);
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

     */

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int index = i;
            for (int j = i + 1; j < n; j++) {
                if (less(arr[j], arr[index])) {
                    index = j;
                }
            }
            if (index != i) {
                swap(arr, index, i);
            }
        }
    }

    /**
     *  选择排序：
     *     通过使用堆,选最值，堆排序
     */
    public static void sortOptimize(String[] arr) {

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
