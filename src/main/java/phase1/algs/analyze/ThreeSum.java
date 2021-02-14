package phase1.algs.analyze;

import java.util.Arrays;
import java.util.Random;

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
//                if (BinarySearch.rank(-arr[i] - arr[j], arr) > j) {
//                    cnt++;
//                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        N = Integer.parseInt(args[0]);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Random().nextInt(1000000);
        }

        System.out.println(count(arr));


        System.out.println(countByBinarySearch(arr));


    }
}
