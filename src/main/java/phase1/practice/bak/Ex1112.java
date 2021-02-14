package phase1.practice.bak;

import java.util.Arrays;

public class Ex1112 {
    public static void main(String[] args) {
        a();
    }

    public static void a() {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = 9 - i;
        }

        System.out.println(Arrays.toString(a));
        System.out.println();

        for (int i = 0; i < 10; i++) {
            a[i] = a[a[i]];
        }

        System.out.println(Arrays.toString(a));
    }
}
