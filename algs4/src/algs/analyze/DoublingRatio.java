package algs.analyze;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

public class DoublingRatio {
    public static double timeTrial(int N) {
        int MAX = 1000000;
        int[] arr = new int [N];
        for (int i = 0; i < N; i++) {
            arr[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch sw = new Stopwatch();
        int cnt = ThreeSum.count(arr);

        return sw.elapsedTime();
    }

    public static void main(String[] args) {
        double prev = timeTrial(123);
        for (int N = 250; true; N <<= 1) {
            double time = timeTrial(N);
            StdOut.printf("%6d %7.1f ", N, time);
            StdOut.printf("%5.1f\n", time / prev);

            prev = time;
        }
    }
}
