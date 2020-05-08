package analyze;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class ThreeSum {
    private static int N = 0;

    public static int count(int[] arr) {
        int n = arr.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public static int countByBinarySearch(int[] arr) {
        Arrays.sort(arr);
        N = arr.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (BinarySearch.rank(-arr[i] - arr[j], arr) > j) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        N = Integer.parseInt(args[0]);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = StdRandom.uniform(-1000000, 1000000);
        }

        Stopwatch timer = new Stopwatch();
        StdOut.println(count(arr));
        double time = timer.elapsedTime();
        StdOut.println("duration: " + time);


        timer = new Stopwatch();
        StdOut.println(countByBinarySearch(arr));
        time = timer.elapsedTime();
        StdOut.println("duration: " + time);


    }
}
