package phase1.algs.sort;

/**
 * 选择排序的优化版本
 *
 * 堆排序并不稳定
 * 最好，最坏复杂度 O(n*log(n))
 */
@SuppressWarnings("all")
public class HeapSort extends Sortion {
    private static Comparable[] array;
    private static int heapSize = 0;
    public static void main(String[] args) {
//        Integer[] array = new Integer[10];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = (int)(Math.random() * 100);
//        }
//        System.out.println(Arrays.toString(array));
//        phase1.algs.sort(array);
//        System.out.println(Arrays.toString(array));
    }

    /**
     * 1. 对原序列进行原地建堆
     * 2. 重复以下操作：
     *  交换堆顶元素与尾元素
     *  堆元素数量 - 1
     *  对0位置进行1次下沉操作
     *
     * 复杂度：O(n * logN)
     */
    public static void sort(Comparable[] arr) {
        array = arr;
        heapSize = array.length;
        heapify(heapSize);
        while (heapSize > 1) {
            // 交换堆顶元素和尾部元素
            swap(array, 0, --heapSize);
            // 0位置下沉
            shiftDown(0);
        }
    }

    /**
     * 自下而上的下滤
     */
    private static void heapify(int heapSize) {
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            shiftDown(i);
        }
    }

    // index位置的元素必须有子节点，才能让其下沉，或者说 才能有子节点跟其进行比较
    // 非叶子节点才能进入while循环
    // 第一个叶子节点就是非叶子节点的数量
    private static void shiftDown(int index) {
        Comparable element = array[index];
        int half = heapSize >> 1;

        while (index < half) {
            int childIndex = (index << 1) + 1; // 2n + 1
            Comparable child = array[childIndex];
            int rightIndex = childIndex + 1;   // 2n + 2
            if (rightIndex < heapSize && array[rightIndex].compareTo(child) > 0) {
                child = array[childIndex = rightIndex];
            }
            if (element.compareTo(child) >= 0) {
                break;
            }
            array[index] = child;
            index = childIndex;
        }
        array[index] = element;
    }
}
