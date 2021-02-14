package phase1.algs.sort;

/**
 * 执行流程：
 *  1. 轴点元素(pivot)，每次假设选择0位置的元素为pivot
 *
 *  2. 将小于pivot的元素放在pivot左侧
 *     大于pivot的元素放在pivot右侧
 *     等于pivot的元素放在左右都可以
 *
 *  3. 对子序列进行1,2操作，直到不能再分割, 递归
 *
 *  快排的本质： 将每个元素都转换为轴点元素
 *
 *  复杂度:
 *      最好情况: 轴点左右元素数量比较均匀 O(n*logn)
 *      最坏情况: 轴点左右元素极度不均匀: O(n^2), 可以通过随机选择轴点元素的方式避免
 */
public class QuickSort extends Sortion {

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length);
    }

    /**
     * [begin, end)
     */
    private static void sort(Comparable[] arr, int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        //
        final int mid = pivotIndex(arr, begin, end);
        sort(arr, begin, mid);
        sort(arr, mid + 1, end);
    }

    /**
     * [begin, end)
     */
    private static int pivotIndex(Comparable[] arr, int begin, int end) {
        // 随机选择一个元素与begin交换
        swap(arr, begin, begin + (int) (Math.random() * (end - begin)));
        Comparable pivot = arr[begin];
        end--;
        while (begin < end) {
            // 从右到左扫描
            while (begin < end) {
                // 右边元素 > 轴点元素
                if (less(pivot, arr[end])) {
                    end--;
                } else {    // 右边元素 <= 轴点元素
                    arr[begin++] = arr[end];
                    break;
                }
            }
            // 从左到右扫描
            while (begin < end) {
                if (less(arr[begin], pivot)) {
                    begin++;
                } else {
                    arr[end--] = arr[begin];
                    break;
                }
            }
        }
        arr[end] = pivot;
        return end;
    }
}
