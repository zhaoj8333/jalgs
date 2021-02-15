package phase2.leetcode.l75;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 给定一个包含红白蓝，共n个元素的数组，原地 排序，使得相同颜色的元素相邻，并按照红白蓝顺序排列
 * input: [2, 0, 2, 1, 1, 0]
 * output: [0, 0, 1, 1, 2, 2]
 */
public class SortColors {
    public static void main(String[] args) {
        int[] nums = new int[] {2, 0, 2, 1, 1, 2};
        sort(nums);
        System.out.println(Arrays.toString(nums));
        // [0, 0, 1, 1, 2, 2]
    }

    /**
     * 使用常数空间的扫描算法
     * 不要使用常规排序算法
     *
     * 三指针：
     * 红色指针右移：遇到1，跳过
     *            遇到0，跟绿色指针交换值，绿色指针++，红色指针++
     *            遇到2，跟紫色指针交换值，紫色指针--，红色指针不需要++,需要在此再次判断大小
     * 红色指针 > 紫色指针时，结束, 不能相等
     */
    private static void sort(int[] nums) {
        System.out.println(Arrays.toString(nums));
        int curr = 0; // red pointer
        int left = 0; // green pointer
        int right = nums.length - 1;  // purple pointer
        while (curr <= right) {
            int v = nums[curr];
            if (v == 0) {
                swap(nums, curr++, left++);  // 先交换，再++
            } else if (v == 1) {
                curr++;
            } else {
                swap(nums, curr, right--);
                // 右边的值交换到左边后，不能急于将curr++，因为交换后的左边值还有可能是2,所以还得做判断
                // curr不用++，会再次进入while循环做判断
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
