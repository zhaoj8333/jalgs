package recursion;

import edu.princeton.cs.algs4.StdOut;

public class ClimbStairs {
    /**
     * 上楼梯、 跳台阶
     *
     * 假设楼梯有 n 阶台阶，可以一步上1阶或2阶，走完n阶台阶有多少走法
     *
     * 假设f(n)种走法， 第一步(或 最后一步)有2种走法
     *
     *     × 上1阶，还剩下n-1阶， 共 f(n-1)种走法
     *     × 上2阶，还剩下n-2阶， 共 f(n-2)种走法
     *
     * 所以, f(n) = f(n - 1) + f(n - 2)
     *     f(1) = 1;
     *     f(2) = 2;
     *     就是一个斐波那契数列，只是初始值不一样
     */
    public static void main(String[] args) {
        int n = 10;
        StdOut.println("climb : " + climb(n));
        StdOut.println("climb1: " + climb1(n));
    }

    private static int climb(int n) {
        if (n <= 2) {
            return n;
        }
        return climb(n - 1) + climb(n - 2);
    }

    private static int climb1(int n) {
        if (n <= 2) {
            return n;
        }
        int first  = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
//            int sum = first + second;
//            first = second;
//            second = sum;
            second = first + second;
            first  = second - first;
        }
        return second;
    }
}
