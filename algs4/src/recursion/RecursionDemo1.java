package recursion;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author allen
 */
public class RecursionDemo1 {
    /**
     * 递归：
     *     递： 调      满足递归条件时循环
     *     归： return  满足基线条件时归
     */
    public static void main(String[] args) {
//        int t = triangleNumbersByLoop(100);
//        StdOut.println("loop: " + t);
//
//        int t1 = triangleNumbersByRecursion(100);
//        StdOut.println("recursion: " + t1);

//        int t3 = fibonacciByRecursion(20);
//        System.out.println(t3);

        move(20, 'A', 'B', 'C');
    }

    /**
     * 第n项的数字
     * @param n
     * @return total
     */
    private static int triangleNumbersByLoop(int n) {
        int total = 0;
        while (n > 0) {
            total = total + n;
            n--;
        }

        return total;
    }

    private static int triangleNumbersByRecursion(int n) {
        if (n == 1) {
            return 1;
        }

        return n + triangleNumbersByRecursion(n - 1);
    }

    /**
     * 这种方式复杂度很高
     *
     * @param n int
     * @return int
     */
    private static int fibonacciByRecursion(int n) {
        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return fibonacciByRecursion(n - 1) + fibonacciByRecursion(n - 2);
        }
    }

    /**
     * 汉诺塔问题
     * @param n
     * @return
     */
    private static int hannoiTower(int n) {

        return 1;
    }

    /**
     *
     * @param top
     * @param from
     * @param inter
     * @param to
     */
    private static void move(int top, char from, char inter, char to) {
        if (top == 1) {
            StdOut.println("盘子 " + top + "，从 " + from + " 塔座到 " + to + " 塔座");
        } else {
            move(top - 1, from, to, inter);
            StdOut.println("盘子 " + top + "，从 " + from + " 塔座到 " + to + " 塔座");
            move(top - 1, inter, from, to);
        }

    }
}
