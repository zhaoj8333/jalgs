package ds.array.sparse;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 五子棋棋盘存储(10 * 10)
 *
 * 稀疏数组（稀疏矩阵）: 数组大部分元素为0，或同一个值，使用稀疏数组保存该值
 *
 * 记录数组一共一共几行几列，有多少不同值
 * 把不同值的元素行列和值记录在一个小规模数组中，缩小程序规模
 */
public class Gomoku {
    public static void main(String[] args) {
//        demo1();
        demo2();
    }

    private static void demo2() {
        // 0表示没有棋子， 1表示黑，2表示白
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[4][5] = 4;
        showArray(chessArray);
        int[][] sparseArray = array2Sparse(chessArray, 0);
        StdOut.println();
        showArray(sparseArray);
        StdOut.println();
        int[][] array = sparse2Array(sparseArray, 0);
        showArray(array);
    }

    private static int[][] sparse2Array(int[][] sparse, int exclude) {
        int[][] array = new int[sparse[0][0]][sparse[0][1]];
        for (int i = 1; i < sparse.length; i++) {
            array[sparse[i][0]][sparse[i][1]] = sparse[i][2];
        }

        return array;
    }

    private static int[][] array2Sparse(int[][] array, int exclude) {
        int sum = 0;
        for (int[] ints : array) {
            for (int anInt : ints) {
                if (anInt != exclude) {
                    sum++;
                }
            }
        }
//        StdOut.println("\nelements number: " + sum);
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = array.length;
        sparseArray[0][1] = array[0].length;
        sparseArray[0][2] = sum;

        int index = 1;
        // 行
        for (int i = 0; i < array.length; i++) {
            // 列
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != exclude) {
                    sparseArray[index][0] = i;
                    sparseArray[index][1] = j;
                    sparseArray[index][2] = array[i][j];
                    index++;
                }
            }
        }

        return sparseArray;
    }

    private static void showArray(int[][] array) {
        for (int[] elem : array) {
            StdOut.println(Arrays.toString(elem));
        }
    }


    private static void demo1() {
        // 7 * 6
        int[][] array = new int[][] {
            {0, 0, 0, 22, 0, 0, 15},
            {0, 11, 0, 0, 0, 17, 0},
            {0, 0, 0, 16, 0, 0, 0},
            {0, 0, 0, 0, 0, 39, 0},
            {91, 0, 0, 22, 0, 0, 0},
            {0, 0, 28, 0, 0, 0, 0},
        };

        // 3 * 9
        // 稀疏数组： 保存棋盘，地图等
        //
        // 二维数组转成稀疏数组
        //    遍历二维数组，得到数据个数sum
        //    根据sum创建sparseArray int[sum + 1][3]
        //    将二维数组的有效数据sum存入sparseArray中
        //
        // 稀疏数组恢复成二维数组
        //    读取sparseArray第一行，创建原始数组 array = int[6]]7]
        //    读取sparseArray之后的数据，依次给array赋值
        int[][] sparseArray = {
                {6, 7, 8},
                {0, 3, 22},
                {0, 6, 15},
                {1, 1, 11},
                {1, 5, 17},
                {2, 3, 16},
                {3, 5, 39},
                {4, 0, 91},
                {5, 2, 28},
        };
    }
}
