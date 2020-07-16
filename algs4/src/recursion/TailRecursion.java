package recursion;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 尾调用： 一个函数的最后一个动作是调用函数
 *     如果最后一个动作时调用自身，称为尾递归，是尾调用的特殊情况
 *
 * 编译器会对尾调用进行优化，以达到节省栈空间的目的
 *
 * 尾调用并不是所有编译器都支持，因为不同函数的栈帧大小不一致，对编译器有一定的要求
 * 如果方法的栈帧结构无法动态修改，此类编程语言就无法修改，java就不支持
 *
 * 尾调用优化(尾调用消除)：
 *      @ 如果当前栈帧上的局部变量等内容都不需要用了，当前栈帧经过适当的改变后可以直接当做被尾调用的函数的栈帧使用。
 *      @ 然后可以jump到被尾调用的函数代码（直接跳到被调用函数的函数体的位置继续执行，不必再次操作栈帧空间）
 *
 * 生成栈帧改变代码与jump的过程称作尾调用消除 或 尾调用优化
 * 尾调用优化让位于尾位置的函数调用与goto语句性能一样高
 * 消除尾递归里的尾调用比消除一般的尾调用容易很多，因为栈大小都是一样的
 *
 * JVM会消除尾递归里的尾调用，但不会消除一般的尾调用，因为无法改变不了栈帧
 * 尾递归优化相对比较普遍, 平时的递归代码尽量考虑尾递归
 *
 * 尾递归：
 */
public class TailRecursion {

    public static void main(String[] args) {
        int n = 12000;
        Stopwatch sw = new Stopwatch();
        int res0 = 0;
        for (int i = 0; i < n; i++) {
            res0 = factorial(n);
        }
        StdOut.println(sw.elapsedTime());

        sw = new Stopwatch();
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = factorialOptimize(n);
        }
        StdOut.println("res:  " + res);
        StdOut.println(sw.elapsedTime());
        // 优化接近一半的时间
    }

    /**
     * 阶乘的尾递归优化
     */
    private static int factorialOptimize(int n) {
        return factorialOptimize(n, 1);
    }

    private static int factorialOptimize(int n, int res) {
        if (n <= 1) {
            return res;
        }
        return factorialOptimize(n - 1, res * n);
    }

    /**
     *  此方法不是尾调用，最后一个动作是乘法
     */
    private static int factorial(int n) {
        if (n <= 1) {
            return n;
        }
        return n * factorial(n - 1);
    }

    /**
     * 尾递归
     */
    private static void tailCall2(int n) {
        if (n < 0) {
            return;
        }
        tailCall2(n - 1);
    }

    /**
     * 尾调用
     */
    private static void tailCall() {
        int a = 1;
        int b  = a + 2;
        tailCall2(b);
    }
}
