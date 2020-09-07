package algs.dp;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author allen
 *
 * 动态规划;
 *     动态: 会变化的状态
 *  1. 定义状态: (状态是原问题,子问题的解)
 *     比如定义dp(i)的含义
 *  2. 设置初始状态(边界)
 *     比如定义dp(0)的值
 *  3. 确定状态转移方程
 *     比如,确定dp(i)与dp(i-1)的关系
 *
 * 概念:
 *     是一种解决复杂问题的方法,通过将问题分解为多个更小的子问题,每个子问题只解1次,并存下来解,最后推出整个问题的解
 *
 * 可以用动态规划来解决的问题,通常具有如下特点
 *     最优子结构(最优化原理),通过求子问题的最优解,可以获得原问题的最优解
 *     无后效性:
 *         某状态的状态一旦确定,则此后过程的演变不在受此前各状态及决策的影响(未来与过去无关)
 *         在推导后面阶段的状态时,只关心前面阶段的具体状态值,不关心这个状态是怎么推导出来的
 *         * 从起点(0, 0)到终点(4, 4)走法问题,只能向右,向下走:
 *             algs.dp(i, 0) = algs.dp(0, j) = 1
 *             algs.dp(i, j) = algs.dp(i, j - 1) + algs.dp(i - 1, j)
 *             推导 algs.dp(i, j)只需要用到dp(i, j- 1),algs.dp(i - 1, j)的值,并不需要关心dp(i, j - 1), algs.dp(i - 1, j)是如何推导出来的
 *     有后效性: 无法用动态规划解决
 *         以上问题如果可以向左,向右,向上,向下走,且一个各自不能走2次
 *         则该问题是有后效性,下一步还得关注上一步是怎么走的
 */
@SuppressWarnings("all")
public class CoinChange {
    public static void main(String[] args) {
        Integer[] faces = {20, 5, 25, 1};
        count = 0;
        int coinCount = dp1(faces, 41);
        StdOut.println(coinCount);
        StdOut.println("count:" + count);
        StdOut.println();
        count = 0;
        coinCount = dp2(faces, 41);
        StdOut.println(coinCount);
        StdOut.println("count:" + count);
        StdOut.println();
        count = 0;
        coinCount = dp3(faces, 41);
//        coinCount = dp3Bak(faces, 41);
        StdOut.println(coinCount);
        StdOut.println("count:" + count);
        StdOut.println();

        faces = new Integer[] {20, 5, 25};
        coinCount = dp4(faces, 7);
        StdOut.println(coinCount);
        StdOut.println("count:" + count);
        StdOut.println();
    }

    private static int count = 0;

    /**
     *  如果无法凑齐money,则dp[i] 应该是 -1
     */
    private static int dp4(Integer[] faces, int money) {
        Arrays.sort(faces);
        count = 0;
        if (money < 1 || faces == null || faces.length == 0) {
            return -1;
        }
        int[] dp = new int[money + 1];
        for (int i = 1; i <= money; i++) {
            count++;
            int min = Integer.MAX_VALUE;
            for (Integer face : faces) {
                if (i < face) {
                    continue;
                }
                int tmp = dp[i - face];
                if (tmp < 0 || tmp >= min) {
                    continue;
                }
                min = tmp;
            }
            // 如果满足以下条件,则说明所有的面值 face 没法用
            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }
        }
        StdOut.println(Arrays.toString(dp));
        return dp[money];
    }

    /**
     * 递推(自底向上): 非递归 / 迭代
     *  由小到大
     *
     *  硬币面值分别为:
     *  1    5    20    25
     *
     *  动态规划最终形态
     */
    /*
        [0, 1, 2, 3, 4, 1] : 5
        [0, 1, 2, 3, 4, 1, 2] : 6
        [0, 1, 2, 3, 4, 1, 2, 3] : 7
        [0, 1, 2, 3, 4, 1, 2, 3, 4] : 8
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5] : 9
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2] : 10
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3] : 11
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4] : 12
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5] : 13
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6] : 14
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3] : 15
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4] : 16
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5] : 17
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6] : 18
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7] : 19
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1] : 20
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2] : 21
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3] : 22
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4] : 23
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5] : 24
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1] : 25
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2] : 26
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3] : 27
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4] : 28
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5] : 29
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 2] : 30
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 2, 3] : 31
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 2, 3, 4] : 32
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 2, 3, 4, 5] : 33
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6] : 34
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3] : 35
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4] : 36
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5] : 37
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6] : 38
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7] : 39
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 2] : 40
        [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 2, 3] : 41

        3

        动态规划: 去掉重叠的子问题
        然后通过递推得到结果
     */
    private static int dp3(Integer[] faces, int money) {
        if (money < 1) {
            return -1;
        }
        int[] dp = new int[money + 1];
        for (int i = 1; i <= money; i++) {
            count++;
//            algs.dp[i] = min{algs.dp[i - 25], algs.dp[i - 20], algs.dp[i - 5], algs.dp[i - 1]} + 1;
            int min = dp[i - 1];
            if (i >= 5) {
                min = Math.min(dp[i - 5], min);
            }
            if (i >= 20) {
                min = Math.min(dp[i - 20], min);
            }
            if (i >= 25) {
                min = Math.min(dp[i - 25], min);
            }
            dp[i] = min + 1;
        }
        return dp[money];
    }

    private static int dp3Bak(Integer[] faces, int money) {
        if (money < 1) {
            return -1;
        }
        int[] dp = new int[money + 1];
        Integer[] faces1 = new Integer[money + 1];
        for (int i = 1; i <= money; i++) {
            count++;
//            algs.dp[i] = min{algs.dp[i - 25], algs.dp[i - 20], algs.dp[i - 5], algs.dp[i - 1]} + 1;
            int min = Integer.MAX_VALUE;
            if (i >= 1 && dp[i - 1] < min) {
                min = dp[i - 1];
                faces1[i] = 1;
            }
            if (i >= 5 && dp[i - 5] < min) {
                min = dp[i - 5];
                faces1[i] = 5;
            }
            if (i >= 20 && dp[i - 20] < min) {
                min = dp[i - 20];
                faces1[i] = 20;
            }
            if (i >= 25 && dp[i - 25] < min) {
                min = dp[i - 25];
                faces1[i] = 25;
                // faces1[i]意思是凑够i分时,最后选择的硬币的面值
            }
            dp[i] = min + 1;
            print(faces1, i);
        }
        print(faces1, money);
        return dp[money];
    }

    /**
     * 凑够money分所需要的faces
     *
     * @param faces1 所选择的硬币
     * @param n 凑够money分
     */
    private static void print(Integer[] faces1, int n) {
        StdOut.print("[" + n + "] = " );
        while (n > 0) {
            StdOut.print(faces1[n] + " ");
            n -= faces1[n];
        }
        StdOut.println();
    }

    /**
     * 暴力递归优化: 记忆化搜索
     */
    private static int dp2(Integer[] faces, int money) {
        if (money < 1) {
            return -1;
        }
        int[] dp = new int[money + 1];
        dp[1] = dp[5] = dp[20] = dp[25] = 1;
        return dp2(money, dp);
    }

    private static int dp2(int money, int[] dp) {
        count++;
        if (money < 1) {
            return Integer.MAX_VALUE;
        }
        if (dp[money] == 0) {
            int min1 = Math.min(dp2(money - 25, dp), dp2(money - 20, dp));
            int min2 = Math.min(dp2(money - 5, dp), dp2(money - 1, dp));
            dp[money] = Math.min(min1, min2) + 1;
        }
        // StdOut.println(money + " : " + Arrays.toString(algs.dp));
        return dp[money];
    }

    /**
     * 暴力递归: 自顶向下,会出现重叠子问题
     *
     * 零钱兑换问题: 现有25, 20, 5, 1分的硬币,给客户找41分的零钱
     *
     *  贪心策略得到的并非总是最优解
     *
     * 使用动态规划:
     *  假设dp(n)是凑到n分需要的最少硬币个数
     *      algs.dp(41): 凑到41分的最少硬币个数
     *  如果第一次选择了25分的硬币,则dp(n) = algs.dp(n - 25) + 1
     *  如果第一次选择了20分的硬币,则dp(n) = algs.dp(n - 20) + 1
     *  如果第一次选择了5分的硬币,则dp(n)  = algs.dp(n - 5) + 1
     *  如果第一次选择了1分的硬币,则dp(n)  = algs.dp(n - 1) + 1
     *
     *  所以, algs.dp(n) = min {
     *      algs.dp(n-25))
     *      algs.dp(n-20))
     *      algs.dp(n-5))
     *      algs.dp(n-1))
     *  } + 1
     *
     * 贪心只考虑眼前最优解
     * 但是动态规划会考虑所有情况
     */
    private static int dp1(Integer[] faces, int money) {
        count++;
        // 基线条件
        // 如果结果为负数,则证明该方案不可行,应该丢弃
        if (money < 0) {
            return Integer.MAX_VALUE;
        }
        if (money == 25 || money == 20 || money == 5 || money == 1) {
            return 1;
        }
        int min1 = Math.min(dp1(faces, money - 25), dp1(faces, money - 20));
        int min2 = Math.min(dp1(faces, money - 5), dp1(faces, money - 1));
        return Math.min(min1, min2) + 1;
    }

}
