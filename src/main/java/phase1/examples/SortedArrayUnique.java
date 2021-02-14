package phase1.examples;

import java.util.Arrays;

public class SortedArrayUnique {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 5, 5, 9, 12, 12, 12, 12, 45};
//        int[] res = removeDuplicatesByDoublePointer(nums);
//        int[] res = removeDuplicatesByDoublePointer1(nums);
        int[] res = removeDuplicatesByDoublePointer2(nums);
        System.out.println(Arrays.toString(res));
    }

    private static int[] removeDuplicatesByDoublePointer2(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }

        int i = 0;
        int j = 0;
        while (j < nums.length - 1) {
            int cur  = nums[i];
            int next = nums[++j];
            if (cur != next) {
                nums[++i] = next;
            }
        }
        int[] ret = new int[i + 1];
        System.arraycopy(nums, 0, ret, 0, ret.length);

        return ret;
    }

    private static int[] removeDuplicatesByDoublePointer1(int[] nums) {
        /*
        1. 前指针p，后指针q
        2. 比较 p，q元素是否相等，相等q右移一位，不等将q上的元素赋值给p+1位置上,p后移一位，q后移一位
         */
        if (nums.length <= 1) {
            return nums;
        }
        int p = 0;
        int q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                nums[++p] = nums[q];
            }
            q++;
        }

        int[] ret = new int[p + 1];
        System.arraycopy(nums, 0, ret, 0, ret.length);

        return  ret;
    }

    private static int[] removeDuplicatesByDoublePointer(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }

        /*
        i是慢指针， j是快指针
         */
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                // 将nums[j] 赋值给 nums[i+1]
                /*
                i++
                nums[i] = nums[j]
                 */
                nums[++i] = nums[j];
            } else {
                // 表示有重复项
                // 跳过重复项
            }
        }

        int[] ret = new int[i + 1];
        System.arraycopy(nums, 0, ret, 0, ret.length);

        return ret;
    }
}
