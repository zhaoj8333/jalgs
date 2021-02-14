package phase1.algs.sort;

/**
 * @author allen
 */
public class InsertionSort extends Sortion {

    public static void sortOptimize(Comparable[] arr) {
        int len = arr.length;
        for (int begin = 1; begin < len; begin++) {
            Comparable temp = arr[begin];
            int insIndex = getInsIndex(arr, begin, temp);
            for (int i = begin; i > insIndex; i--) {
                arr[i] = arr[i - 1];
            }
            arr[insIndex] = temp;
        }
    }

    /**
     * 得到的插入位置为 第一个大于 v 的位置
     */
    private static int getInsIndex(Comparable[] arr, int rightIndex, Comparable v) {
        int left  = 0;
        int right = rightIndex;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (less(v, arr[mid])) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void sortByMove(Comparable[] arr) {
        int len = arr.length;
        for (int begin = 1; begin < len; begin++) {
            int curr = begin;
            Comparable temp = arr[curr];
            //            while (curr > 0 && arr[curr] < arr[curr - 1]) {
            //            有误: arr[curr]一直在变化，而此处比较的一直是temp
            while (curr > 0 && less(temp, arr[curr - 1])) {
                arr[curr] = arr[curr - 1];
                curr--;
            }
            arr[curr] = temp;
        }
    }

    public static void sort(Comparable[] arr) {
        int len = arr.length;
        for (int begin = 1; begin < len; begin++) {
            int curr = begin;
            Comparable temp = arr[curr];
            while (curr > 0 && less(temp, arr[curr - 1])) {
                swap(arr, curr, curr - 1);
                curr--;
            }
            arr[curr] = temp;
        }
    }

    public static void sortBySwap(Comparable[] arr) {
        sort(arr);
    }

    /*
    public static void main(String[] args) {
        Random random = new Random();
        int num = 200000;
        Integer[] arr1 = new Integer[num];
        Integer[] arr2;
        Integer[] arr3;

        for (int i = 0; i < num; i++) {
            Integer tmp = Math.round(random.nextInt(num));
            arr1[i] = tmp;
        }
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);

        Stopwatch sw = new Stopwatch();

        sortByMove(arr1);
        Assert.assertTrue(Sortion.isSorted(arr1));
        System.out.println("byMove: " + sw.elapsedTime());

        sw = new Stopwatch();
        sortBySwap(arr2);
        Assert.assertTrue(Sortion.isSorted(arr2));
        System.out.println("bySwap: " + sw.elapsedTime());

        sw = new Stopwatch();
        sortOptimize(arr3);
        Assert.assertTrue(Sortion.isSorted(arr3));
        System.out.println("optimize: " + sw.elapsedTime());

            byMove: 61.961
            bySwap: 69.019
            optimize: 38.999
    }

     */

}
