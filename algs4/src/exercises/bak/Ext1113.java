package exercises.bak;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Ext1113 {
    public static void main(String[] args) {
        transpose(2, 5);
    }

    public static void transpose(int m, int n) {
        Character[][] arr = new Character[m][n];
        char a = 'A';
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = (char)(a++);
            }
        }
        printArr(arr);
        StdOut.println();

        Character[][] newArr = new Character[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newArr[i][j] = arr[j][i];
            }
        }
        printArr(newArr);
    }

    public static void printArr(Character[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            StdOut.println(Arrays.toString(arr[i]));
        }
    }
}
