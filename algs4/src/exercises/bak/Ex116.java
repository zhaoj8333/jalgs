package exercises.bak;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Ex116 {
    public static void main(String[] args) {
        fibonacci1(100);
        StdOut.println();
        fibonacci2(100);
    }

    public static void fibonacci1(int n) {
        long[] nums = new long[n];

        long f = 1L;
        long g = 1L;

        for (int i = 0; i < n; i++) {
            nums[i] = f;
            g = f - g;
            f = f + g;

//            f = (f + (f - g));  // !!!有误
        }

        StdOut.println(Arrays.toString(nums));
    }

    public static void fibonacci2(int n) {
        long[] nums = new long[n];
        nums[0] = nums[1] = 1;

        for (int i = 2; i < n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }

        StdOut.println(Arrays.toString(nums));
    }
}
