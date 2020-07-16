package recursion;

import edu.princeton.cs.algs4.StdOut;

public class Hanoi {
    private static int count = 0;
    public static void main(String[] args) {
        hanoi(5, "A", "B", "C");
        StdOut.println("count: " + count);
    }

    /**
     * 将 n 个碟子 从 p1 挪动到 p3
     * p2为中间的柱子
     *
     * 分两种情况即可：
     *  × 当n == 1时，直接将盘子从   A 移动到 C
     *  × 当 n > 1时，可以拆分成 3个步骤
     *      ① 将 n - 1个盘子  从A搬到B
     *      ② 将编号为 n的盘子 从A移动到C
     *      ③ 将n - 1个盘子   从B移动到C
     *
     *      ①③为递归调用
     *
     * 时间复杂度： 2^n
     * 空间复杂度： O(n), 调用栈
     */
    //                          n       A          B          C
    private static void hanoi(int n, String p1, String p2, String p3) {
        count++;
        if (n == 1) {
            move(n, p1, p3);
            return;
        }
        hanoi(n - 1, p1, p3, p2);
        hanoi(n - 1, p2, p1, p3);
        move(n, p1, p3);
    }

    private static void move(int no, String from, String to) {
        StdOut.println("将 " + no + " 号盘子从 " + from + " 移动到 " + to);
    }

}
