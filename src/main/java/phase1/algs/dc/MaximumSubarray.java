package phase1.algs.dc;


/**
 * 最大连续子序列和
 *
 * 给定一个长度为n的整数序列， 求最大连续子序列和
 *
 * 子序列： 可以不连续
 * 子串,子数组,子区间： 必须连续
 *
 * 该问题也属于 最大切片问题（Greatest Slice）
 *
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int max = get1(nums);
        max = get2(nums);
        max = get(nums);
    }

    private static int count = 0;

    /**
     * 使用 分治 解决
     *     将序列均匀的分割成 2 个子序列: [begin, end) = [begin, mid) + [mid, end), mid = (begin + end) >> 1
     *
     * 假设问题的解是 S[i, j), 则，问题的解有三种可能
     *     [i, j) 存在于 [begin, mid)中, 同时 S[i, j)也是[begin, mid) 的最大连续子序列和
     *     [i, j) 存在于 [mid, end)中, 同时 S[i, j) 也是[mid, end) 的最大连续子序列和
     *     [i, j) 一部分在[begin, mid)中，另一部分在 [mid, end)中, [i, j) = [i, mid) + [mid, j)
     *          [i. mid) : 左边,由mid从右到左连续加起来最大的就是[i, mid), 也就是说再往前加反而变小了
     *          [mid, j) : 右边同理
     */
    private static int get(int[] nums) {
        count = 0;
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return get(nums, 0, nums.length);
    }

    private static int get(int[] nums, int begin, int end) {
        count++;
        if (end - begin < 2) {
            return nums[begin];
        }
        int mid = (begin + end) >> 1;
        // 算横跨左右的
        int leftMax = 0;
        int leftSum = 0;
        for (int i = mid - 1; i >= begin ; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }
        int rightMax = 0;
        int rightSum = 0;
        for (int i = mid; i < end ; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }
        // 左边最大的 vs 右边最大的
        int bigger = Math.max(get(nums, begin, mid), get(nums, mid, end));

        return Math.max(leftMax + rightMax, bigger);
    }

    private static int get2(int[] nums) {
        count = 0;
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int sum;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                count++;
                sum += nums[j];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    /**
     * 解法1：穷举出所有可能的连续子序列，并计算和，取最大值
     */
    private static int get1(int[] nums) {
        count = 0;
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int k = i; k < j; k++) {
                    count++;
                    sum += nums[k];
                }
                max = Math.max(sum, max);
            }
        }
        return max;
    }

}
