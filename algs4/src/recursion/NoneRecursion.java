package recursion;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 递归 转 非递归：
 *
 *     递归调用中，会将每次调用的参数，局部变量都保存在 栈帧中（Stack Frame）中
 *
 *     若递归调用深度较大，会占用大量栈空间，甚至导致栈溢出
 *     递归会存在大量重复计算，性能非常差
 *     递归可以100%转化为非递归
 *
 *  1. 万能转化方法：
 *     自己维护一个栈，来保存参数，局部变量
 *
 * 自己模拟函数调用栈帧结构
 * 自己维护一个栈时，空间复杂度并不会得到优化
 *
 * 2. 在某些时候，可以重复使用一组相同的变量来保存每个栈帧的内容，将空间复杂度由 O(n)转化为O(1)
 *
 */
public class NoneRecursion {
    public static void main(String[] args) {
        log(4);
        StdOut.println("-----------------------");
        log(4);
    }

    private static void log(int n) {
        if (n < 1) {
            return;
        }
        log(n - 1);
        int v = n + 10;
        StdOut.println(v);
    }

    private static void log1(int n) {
        Stack<StackFrame> frames = new Stack<>();
        while (n > 0) {
            frames.push(new StackFrame(n, n + 10));
            n--;
        }
        while (! frames.isEmpty()) {
            StackFrame frame = frames.pop();
            StdOut.println(frame.getV());
        }
    }

    private static class StackFrame {
        private int n;
        private int v;

        public StackFrame(int n, int v) {
            this.n = n;
            this.v = v;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }
    }
}
