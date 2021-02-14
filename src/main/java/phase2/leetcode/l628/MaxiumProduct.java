package phase2.leetcode.l628;

import java.util.Arrays;

public class MaxiumProduct {
    public static void main(String[] args) {
//        int[] ints1 = new int[] {-100,-98,-1,2,3,4};
//        Arrays.sort(ints1);
//        System.out.println(Arrays.toString(ints1));
//        int[] ints = {3, 17, 11, 19, 13, 2, 101, 100, 312};
//        int i = solution1(ints);
//        System.out.println(i);
//        int j = solution2(ints);
//        System.out.println(j);
//
//        int k = solution2Optimize(ints);
//        System.out.println(k);


    }

    /**
     * 排序优化
     */
    private static int solution2Optimize(int[] nums) {
        int[] maxs = selectMaxN(nums, 3);
        int result = 0;

        return result;
    }

    private static int[] selectMaxN(int[] nums, int n) {
        int count = 0;
        if (n >= nums.length) {
            return nums;
        }
        int[] newNums = new int[nums.length];
        System.arraycopy(nums,0, newNums, 0, nums.length);

        int[] maxes = new int[n];
        for (int i = 0; i < n; i++) {
            int lastMax = Integer.MIN_VALUE;
            int maxIndex = 0;
            for (int j = 0; j < newNums.length; j++) {
                count++;
                if (newNums[j] > lastMax) {
                    lastMax = newNums[j];
                    maxIndex = j;
                }
                maxes[i] = lastMax;
            }
            newNums[maxIndex] = Integer.MIN_VALUE;
        }
//        System.out.println("count: " + count);
        return maxes;
    }

    /**
     * 正数： 通过排序找出最大的三个数，直接返回即可
     */
    private static int solution2(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        if (nums[0] < 0 && nums[1] < 0) {
            return Math.max(nums[0] * nums[1] * nums[2], Math.max(nums[0] * nums[1] * nums[len - 1], nums[len - 1] * nums[len - 2] * nums[len - 3]));
        } else {
            return nums[0] * nums[1] * nums[2];
        }
    }

    /**
     * 暴力法
     */
    private static int solution1(int[] nums) {
        int count = 0;
        int maxProd = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    count ++;
                    int tmp = nums[i] * nums[j] * nums[k];
                    if (tmp > maxProd) {
                        maxProd = tmp;
                    }
                }
            }
        }
//        System.out.println("count: " + count);
        return maxProd;
    }

}
