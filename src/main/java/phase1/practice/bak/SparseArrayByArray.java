package phase1.practice.bak;

import java.util.Arrays;

public class SparseArrayByArray {
    public static void main(String[] args) {
        ex1();
    }

    private static void ex1() {
        int[][] array = new int[10][10];
        array[0][1] = 2;
        array[1][9] = 3;
        array[2][0] = 8;
        array[3][3] = 5;
        array[4][2] = 9;
        array[5][2] = 2;
        array[8][1] = 3;
        array[9][4] = 8;
        show(array);
        System.out.println("compress===================");
        int[][] sparseArray = compress1(array);
        show(sparseArray);

        System.out.println("decompress=================");
        int[][] res = decompress1(sparseArray);
        show(res);
    }

    private static int[][] decompress1(int[][] sparseArray) {
        int m = sparseArray[0][0];
        int n = sparseArray[0][1];
        int[][] array = new int[m][n];
        for (int i = 1; i < sparseArray.length; i++) {
            int line = sparseArray[i][0];
            int column = sparseArray[i][1];
            int value = sparseArray[i][2];
            array[line][column] = value;
        }

        return array;
    }

    private static int[][] compress1(int[][] array) {
        int sum = 0;
        for (int[] ints : array) {
            for (int i : ints) {
                if (i != 0) {
                    sum++;
                }
            }
        }
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = array.length;
        sparseArray[0][1] = array[0].length;
        sparseArray[0][2] = sum;

        int index = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    sparseArray[index][0] = i;
                    sparseArray[index][1] = j;
                    sparseArray[index][2] = array[i][j];
                    index++;
                }
            }
        }

        return sparseArray;
    }

    private static void show(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }
}
