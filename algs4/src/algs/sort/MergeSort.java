package algs.sort;

/**
 * 归并排序： Merge Sort
 *     执行流程：
 *          1. 不断将当前序列平均分隔为2个子序列，直到不能分隔（序列中只剩下1个元素）
 *          2. 不断地将2个子序列合并为一个有序序列，直到最终剩下1个有序序列
 * @author allen
 */
public class MergeSort extends Sortion {
    private static int[] leftArr;

    /**
     * 时间复杂度：
     *      n * O(logn)
     * 空间复杂度：
     *      O(n/2 + logn) = O(n)
     *      n/2是因为存放左侧数组
     *      logn是因为递归调用
     */
    public static void sort(Comparable[] arr) {
        leftArr = new int[arr.length >> 1];
        sort(0, arr.length, arr);
    }

    /**
     *  对[begin, end) 范围的数据进行归并排序
     *  T(n) = T(n / 2) + T(n / 2)
     */
    public static void sort(int begin, int end, Comparable[] arr) {
        if (end - begin < 2) {
            return;
        }
        int mid = (begin + end) >> 1;
        sort(begin, mid, arr);
        sort(mid, end, arr);
        merge(begin, mid, end, arr);
    }

    /**
     * 对 [begin, mid), [mid, end) 范围的序列合并称为一个有序数组
     * O(n)
     */
    public static void merge(int begin, int mid, int end, Comparable[] arr) {
        int li = 0, le = mid - begin;   // 左数组
        int ri = mid, re = end;         // 右数组
        int ai = begin;                 // array数组
        // 备份左边数组
        for (int i = li; i < le; i++) {
            leftArr[i] = (int) arr[begin + i];
        }
        // 如果左边还没有结束才进行迭代
        while (li < le) {
            if (ri < re && less(arr[ri], leftArr[li])) {
                arr[ai++] = arr[ri++];
            } else {
                arr[ai++] = leftArr[li++];
            }
        }
    }
}
