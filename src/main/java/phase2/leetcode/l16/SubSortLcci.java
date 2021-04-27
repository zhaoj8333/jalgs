package phase2.leetcode.l16;

import java.util.Arrays;
import java.util.Objects;

/**
 * 部分排序：
 *    给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m, n]的元素排好序，整个数组就是有序的, n - m要尽量小，也就是说，找出符合条件的最短序列。
 *    函数返回值为 [m, n], 若不存在这样的m,n(例如整个数组有序)，则返回[-1, -1]
 *
 * input:  [1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19]
 * output: [3, 9]
 */
public class SubSortLcci {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 5, 4, 3, 2, 6, 7};
        int[] res  = sort(nums);
        System.out.println(Arrays.toString(res));
    }

    /**
     * input: [1, 5, 4, 3, 2, 6, 7]
     * 寻找 没有排序的部分
     * 寻找 逆序对
     */
    private static int[] sort(int[] nums) {
        int left = -1, right = -1;
        if (Objects.isNull(nums) || nums.length == 0) {
            return new int[] {left, right};
        }
        // 正序扫描
        // 获取max sentinel
        int sentinel= nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > sentinel) { // 不记录 == 的情况
                sentinel = nums[i];
            } else {
                right = i;
            }
        }
        if (right == -1) {
            return new int[] {left, right};
        }
        // 获取最小sentinel
        sentinel = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] <= sentinel) {
                sentinel = nums[i];
            } else {
                left = i;
            }
        }
//        System.out.println(left);
//        System.out.println(sentinel);
        // {1, 4}
        return new int[]{left, right};
    }
}
