package phase1.practice.bak;

import java.util.Arrays;

public class Ext1110 {
    public static void main(String[] args) {
        initialize();
        printTowDimensionalArray();
    }

    public static void initialize() {
//        int[] a;  // a might not have been initialized
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = i * i;
        }

        System.out.println(Arrays.toString(a));
    }

    public static void printTowDimensionalArray() {
        boolean[][] test = {{true, false, true}, {false, true, true}};

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (test[i][j]) {
                    System.out.println("*");
                } else {
                    System.out.println("");
                }
            }
        }
    }
}
