package bt;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Queens {
    public static void main(String[] args) {
        int n = 8;
        Stopwatch sw = new Stopwatch();
        new Queens().placeQueens(n);
        StdOut.println(sw.elapsedTime());
    }

    /**
     * 数组索引为行号，数组元素为列号 cols[4] = 5， 第4行的第5列
     *                            cols[row] = col
     */
    private int[] placed;

    // 某一列是否有皇后
    private boolean[] cols;
    // 某一对角线（左上角/右下角）是否有皇后
    private boolean[] leftTop;
    // 某一对角线（右上角/左下角）是否有皇后
    private boolean[] rightTop;

    /**
     * 摆法
     */
    private int ways;

    private void placeQueens(int n) {
        if (n < 1) {
            return;
        }
        placed = new int[n];
        cols = new boolean[n];
        leftTop = new boolean[2 << n - 1];
        rightTop = new boolean[2 << n - 1];
        place(0);
        StdOut.println("count: " + count);
        StdOut.println(n + " 皇后一共有 " + ways + " 种摆法");
    }

    int count = 0;
    private void place0(int row) {
        // 调用到最后一行，则停止
        if (row == placed.length) {
//            show();
            ways++;
            return;
        }
        // col列
        for (int col = 0; col < placed.length; col++) {
            if (isValid(row, col)) {
                // 剪枝： 如果无法摆放，则不做任何处理
                count++;
                // 第row行，第col列摆放
                placed[row] = col;
                // 摆了上一行，马上就会摆放下一行
                place0(row + 1);
                // 执行到此处说明 row 无法摆放
                // 无法摆放则必须清除掉之前的设置
            }

        }
        // 如果place函数返回，则会返回到row-1的情况，发生了回溯，col++，尝试第row-1行的col++列的情况
        // 当col达到最右侧即 col == 7时，继续执行
        // 当col++继续执行，越界时，表示该row行执行完毕，回到上一row，即row--
        //
        // StdOut.println("cols: " + Arrays.toString(cols));
    }

    /**
     * 从第 row 行开始摆放皇后
     */
    private void place(int row) {
        // 调用到最后一行，则停止
        if (row == placed.length) {
            show();
            ways++;
            return;
        }
        // col列
        for (int col = 0; col < placed.length; col++) {
            /*
            if (!isValid(row, col)) {
                // 剪枝： 如果无法摆放，则不做任何处理
                continue;
            }
            */
            count++;
            if (cols[col]) {
                continue;
            }
            int ltIndex = row - col + (placed.length - 1);
            int rtIndex = row + col;
            if (leftTop[ltIndex] || rightTop[rtIndex]) {
                continue;
            }
            cols[col] = true;
            leftTop[ltIndex] = rightTop[rtIndex] = true;
            // 第row行，第col列摆放
            placed[row] = col;
            // 摆了上一行，马上就会摆放下一行
            place(row + 1);
            // 执行到此处说明 row 无法摆放
            // 无法摆放则必须清除掉之前的设置
            cols[col] = false;
            leftTop[ltIndex] = rightTop[rtIndex] = false;
        }
        // 如果place函数返回，则会返回到row-1的情况，发生了回溯，col++，尝试第row-1行的col++列的情况
        // 当col达到最右侧即 col == 7时，继续执行
        // 当col++继续执行，越界时，表示该row行执行完毕，回到上一row，即row--
        //
        // StdOut.println("cols: " + Arrays.toString(cols));
    }

    /**
     * 第row行，第col列摆放是否合法
     */
    private boolean isValid(int row, int col) {
        // 第 row 行前面那些行 i
        for (int i = 0; i < row; i++) {
            // 1. 判断列
            if (placed[i] == col) {
                return false;
            }
            // 2. 因为本来就是按行，所以不用根据行判断是否合法
            // 3. 判断斜线
            if (row - i == Math.abs(col - placed[i])) {
                return false;
            }
        }
        return true;
    }

    private void show () {
        for (int i : placed) {
            for (int col = 0; col < placed.length; col++) {
                if (i == col) {
                    StdOut.print("1  ");
                } else {
                    StdOut.print("0  ");
                }
            }
            StdOut.println();
        }
        StdOut.println("--------------------------------");
    }
}
