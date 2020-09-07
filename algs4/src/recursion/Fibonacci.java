package recursion;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 递归：
 *     函数（方法）直接或间接调用自身
 *
 * 函数调用过程：
 *
 * 栈内存溢出
 *
 * 基线条件： 边界条件、 递归基
 *
 * 使用递归不是为了取得最优解，而是为了简化解题思路，代码更加简洁
 *
 * 基本思想：
 *     × 拆解问题： 把规模大的问题变成规模较小的同类型问题，规模较小的问题又不断变成规模更小的问题，规模小到一定程度就可以直接得到它的解；
 *     × 求解： 由最小规模问题的解不断得到规模更大问题的解
 * 链表、 二叉树本身就是递归数据结构
 *
 *     1. 明确函数功能
 *     2. 明确原问题与子问题的关系, f(n) 与 f(n - 1)的关系
 *     3. 明确递归基（边界条件）
 *        递归过程中，子问题规模不断减小，当小到什么程度可以直接得出解
 *        寻找递归基: 问题规模小到什么程度可以直接得出解 ???
 *
 * 递归调用的复杂度：
 * 空间复杂度： 递归深度(最大调用深度) * 每次调用所需的辅助空间  类似二叉树的前序遍历
 *
 *
 *
 */
public class Fibonacci {

    public static void main(String[] args) {
//        StdOut.println(sum(10));
        int n = 41;
        int fib = fib(n);
        StdOut.println("fib:  " + fib);

//        int fib1 = fib1(n);
//        StdOut.println("fib1: " + fib1);
//        StdOut.println("count1: " + count1);
//        StdOut.println();

        int fib2 = fib2(n);
        StdOut.println("fib2: " + fib2);
        StdOut.println("count2: " + count2);
        StdOut.println();

        int fib3 = fib3(n);
        StdOut.println("fib3: " + fib3);
        StdOut.println("count3: " + count3);
        StdOut.println();

        int fib4 = fib4(n);
        StdOut.println("fib4: " + fib4);
        StdOut.println("count4: " + count4);
        StdOut.println();

        int fib5 = fib5(n);
        StdOut.println("fib5: " + fib5);
        StdOut.println();

        int fib6 = fibRecursionOptimize(n);
        StdOut.println("count6: " + count6);
        StdOut.println("fib6: " + fib6);
        StdOut.println();
   }
    private static int count6 = 0;
    private static int count4 = 0;
    private static int count3 = 0;
    private static int count2 = 0;
    private static int count1 = 0;

    /**
     * 线性代数 解法
     */
    private static int fib5(int n) {
        double c = Math.sqrt(5);
        double a = Math.pow(((1 + c) / 2), n);
        double b = Math.pow(((1 - c) / 2), n);
        return (int)((a - b) / c);
    }

    /**
     *  滚动数组
     */
    private static int fib4(int n) {
        if (n <= 2) {
            return 1;
        }
        int[] arr = new int[2];
        arr[0] = arr[1] = 1;
        for (int i = 1; i < n - 1; i++) {
            count4++;
            arr[i & 1] = arr[(i - 1) & 1] + arr[(i - 2) & 1];
            StdOut.println(i + " : " + arr[i & 1]);
            // arr[i % 2] = arr[(i - 1) % 2] + arr[(i - 2) % 2];
        }
        /*
        StdOut.println("================");
        for (int i = 2; i <= n; i++) {
            arr[i % 2] = arr[(i - 1) % 2] + arr[(i - 2) % 2];
            StdOut.println(i + " : " + arr[i & 1]);
        }
         */
        // return arr[n % 2];
        return arr[n & 1];
    }

    /**
     *  避免重复计算的优化
     */
    private static int fib3(int n) {
        if (n <= 2) {
            return 1;
        }
        int[] arr = new int[n + 1];
        // 必须初始化两个,否则递归时会反复往下查找,报ArrayOutofBoundsException
        arr[1] = arr[2] = 1;
        return fib3(n, arr);
    }

    private static int fib3(int n, int[] arr) {
        count3 ++;
//        StdOut.println("n: " + n + " : " + Arrays.toString(arr));
        if (arr[n] == 0) {
            arr[n] = fib3(n - 1, arr) + fib3(n - 2, arr); // 第一波不会干任何事情
        }
        return arr[n];
    }

    private static int fib2(int n) {
        if (n <= 2) {
            return 1;
        }
        int[] sum = new int[n + 1];
        sum[0] = 0;
        sum[1] = 1;
        for (int i = 2; i <= n; i++) {
            count2++;
            sum[i] = sum[i - 2] + sum[i - 1];
        }
        return sum[n];
    }

    private static int fib1(int n) {
        if (n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first   = second;
            second  = sum;
        }
        return second;
    }

    /**
     * T(n) = T(n - 1) + T(n - 2) + O(1)
     * 时间复杂度： O(2^n)
     * 空间复杂度： O(n) 递归调用深度
     *
     * 该斐波那契方法 不是尾递归调用
     */
    private static int fib(int n) {
        if (n <= 2) {
            return 1;
        }
        count1++;
        // 左边的递归调用结束后才回进行右侧的递归调用
        return fib(n - 1) + fib(n - 2);
    }

    private static int fibRecursionOptimize(int n) {
        return fibRecursionOptimize(n, 1, 1);
    }

    private static int fibRecursionOptimize(int n, int first, int second) {
        count6++;
        if (n <= 1) {
            return first;
        }
        return fibRecursionOptimize(n - 1, second, first + second);
    }

    private static int sum(int n) {
        if (n <= 1) {
            return n;
        }
        return n + sum(n - 1);
    }
}

