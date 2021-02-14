package phase1.practice.bak;

public class Ext117 {
    public static void main(String[] args) {
//        a();
//        b();
//        bOptimize();
        c();
    }

    public static void a() {
        double t = 9.0;
        double tmp = 0.0;
        while ((tmp = Math.abs(t - 9.0 / t)) > .001) {
            System.out.println(tmp);
            t = (9.0 / t + t) / 2.0;
        }

        System.out.printf("%.5f\n", t);
    }

    public static void b() {
        int sum = 0;
        for (int i = 1; i <= 1000; i ++) {
            for (int j = 0; j < i; j++) {
                sum ++;
            }
        }
        /**
         * i = 1: +1
         * i = 2: +2
         * i = 3: +3
         * ....
         * i = n: +n
         * =>
         * 1 + 2 + 3 + 4 + ... + n
         */
        System.out.println(sum);
        sum = 0;

        for (int i = 1000; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                sum ++;
            }
        }
        System.out.println(sum);
    }

    public static void bOptimize() {
        int sum = 0;
        sum = 1000 * (1000 + 1) / 2;
        System.out.println(sum);
    }

    public static void c() {
        int sum = 0;

        for (int i = 1; i < 1000; i *= 2) {
            for (int j = 0; j < 1000; j++) {
                sum ++;
            }
        }
        System.out.println(sum);
    }
}
