package phase1.algs.dc;


import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * 最大连续子序列和
 *
 * 给定一个长度为n的整数序列， 求最大连续子序列和
 *
 * 子序列： 可以不连续
 * 连续子序列： ...
 *
 * 子串,子数组,子区间： 必须连续
 *
 * 该问题也属于 最大切片问题（Greatest Slice），最大切片，最大区段
 *
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        Random random = new Random(System.nanoTime());
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100) - 50;
        }
        System.out.println(Arrays.toString(array));
        maxSubarray(array);
    }

    private static void maxSubarray(int[] array) {
        System.out.println("bruteForce: " + byBruteForce(array));
        System.out.println("bruteForceOptimized: " + bruteForceOptimized(array));

        System.out.println("divideConquer: " + divideConquer(array));
//        sum = b

    }

    /**
     * 采用分治算法：
     *
     * 3种可能：
     *     [i, j) 存在于 [begin, mid) 中
     *     [i, j) 存在于 [mid, end) 中
     *     [i, j) 一部分存在于[begin, mid)中，另一部分存在于[mid, end)中
     */
    private static int divideConquer(int[] array) {
        if (Objects.isNull(array) || array.length == 0) return 0;
        return divideConquer(array, 0, array.length);
    }

    private static int divideConquer(int[] array, int begin, int end) {
        if (end - begin < 2) return array[begin];

        int mid = (begin + end) >> 1;
        int leftMax = array[mid - 1], leftSum = array[mid - 1];
        for (int i = mid - 2; i >= begin; i--) {
            leftSum += array[i];
            leftMax = Math.max(leftMax, leftSum);
        }
        int rightMax = array[mid], rightSum = array[mid];
        for (int i = mid + 1; i < end; i++) {
            rightSum += array[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        return Math.max(
                leftMax + rightMax,
                Math.max(divideConquer(array, begin, mid), divideConquer(array, mid, end))
        );
    }

    /**
     * 暴力解法 存在重复计算的问题
     */
    private static int bruteForceOptimized(int[] array) {
        if (Objects.isNull(array) || array.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < array.length; begin++) {
            int sum = 0;
            for (int end = begin; end < array.length; end++) {
                sum += array[end];
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    /**
     * 暴力解法
     */
    private static int byBruteForce(int[] array) {
        if (Objects.isNull(array) || array.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < array.length; begin++) {
            for (int end = begin; end < array.length; end++) {
                int sum = 0;
                for (int i = begin; i <= end; i++) {
                    sum += array[i];
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}
