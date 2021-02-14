package phase1.practice.bak;

import java.util.Scanner;

public class Methods {
    public static void main(String[] args) {
//        ex1();
//        ex2(1, 12);
//        int max = ex3(12, 432, 982);
//        System.out.println("最大值为： " + max);
//        int sum = ex4(12, 432, 982);
//        System.out.println("和为： " + sum);

        Scanner sc = new Scanner(System.in);

        int n, m = 0;
        System.out.println("请输入行数：");
        n = sc.nextInt();
        System.out.println("请输入列数：");
        m = sc.nextInt();
        ex5(n, m);

    }

    public static void ex1() {
        for (int i = 1; i <= 10; i++) {
            if ((i & 1) != 1) {
                System.out.println(i + " 是偶数");
            }
        }
    }

    public static void ex2(double m, double n) {
        double min = m > n ? n : m;
        System.out.println("较小值为： " + min);
    }

    public static int ex3(int x, int y, int z) {
        int max = 0;

        int tmp = x > y ? x : y;

        return tmp > z ? tmp : z;
    }

    public static int ex4(int x, int y, int z) {
        return x + y + z;
    }

    public static void ex5(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print("@");
            }
            System.out.println();
        }
    }
}
