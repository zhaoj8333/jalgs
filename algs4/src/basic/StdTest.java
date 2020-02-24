package basic;

import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class StdTest {
    public static void main(String[] args) {
//        StdOut.println(Arrays.toString(args));
//        StdIn.readAllInts(args[0]);
//        testStdRandom(args);
//        testStdin(args);
//        testIn(args);
//        testDraw();
//        testFunc1();
//        testFunc2();
//        testRandom();
        testSort();
    }

    public static void testSort() {
        int n = 50;
        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.random();
        }

        Arrays.sort(a);

        for (int i = 0; i < n; i++) {
            double x = 1.0 * i / n;
            double y = a[i] / 2.0;
            double rw = 0.4 / n;
            double rh = a[i] / 2.0;

            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }

    public static void testRandom() {
        int n = 50;
        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.random();
            double x = 1.0 * i / n;
            double y = a[i] / 2.0;
            double rw = 0.4 / n;
            double rh = a[i] / 2.0;

            StdDraw.filledRectangle(x, y, rw, rh);
        }

//        for (int i = 0; i < n; i++) {
//            double x = 1.0 * i / n;
//            StdOut.println(x);
//        }
    }

    public static void testFunc2() {
        int n = 5;
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, Math.pow(2, n));
        StdDraw.setPenRadius(.5);

        for (int i = 0; i <= n; i++) {
            StdDraw.point(i, i);
            StdDraw.point(i, i * i);
            StdDraw.point(i, Math.pow(2, i));
        }
    }

    public static void testFunc1() {
        int n = 50;
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n * n);
        StdDraw.setPenRadius(.01);
        for (int i = 1; i <= n; i++) {
            StdDraw.point(i , i);
            StdDraw.point(i, i * i);
//            StdDraw.point(i, i * Math.log(i));
        }
    }

    public static void testDraw() {
//        StdDraw.line(0, 0, 4, 10);
//        StdDraw.point(9, 9);
//        StdDraw.text(1, 2, "abcdasjdklfasjkjlf");
//        StdDraw.circle(5, 5, 3);
//        StdDraw.filledCircle(5, 5, 2);
//        StdDraw.ellipse(3, 3, 3, 3);
//        StdDraw.filledEllipse(3, 3, 3, 3);
//        StdDraw.square(5, 5, 3);
//        StdDraw.filledSquare(5, 5, 3);

//        StdDraw.setPenRadius(.01);
        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);
        StdDraw.rectangle(8, 8,1, 1);
//        StdDraw.filledRectangle(5, 5, 1, 1);
        StdDraw.filledPolygon(new double[]{1, 8, 6, 4},  new double[]{5, 7, 1, 3});
    }

    public static void testIn(String[] args) {
        int[] data = In.readInts(args[0]);
        StdOut.println(Arrays.toString(data));
    }

    public static void testStdin(String[] args) {
        double sum = 0.0;
        int cnt = 0;

        while (! StdIn.isEmpty()) {
            sum += StdIn.readDouble();
            cnt ++;
        }

        double avg = sum / cnt;
        StdOut.printf("average is %.5f\n", avg);
    }

    public static void testStdRandom(String[] args) {
        int n = Integer.parseInt(args[0]);
        double lo = Double.parseDouble(args[1]);
        double hi = Double.parseDouble(args[2]);

        for (int i = 0; i < n; i++) {
            double x = StdRandom.uniform(lo, hi);
            StdOut.printf("%.2f\n", x);
        }
    }

}
