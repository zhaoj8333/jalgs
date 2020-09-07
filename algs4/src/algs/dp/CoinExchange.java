package algs.dp;

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class CoinExchange {
    public static void main(String[] args) {
        Integer[] faces = {20, 5, 25, 1};
        Arrays.sort(faces);

        int count = dp1(41, faces);
        StdOut.println("dp1: " + count);

        int count2 = dp2(41, faces);
        StdOut.println("dp2: " + count2);
    }

    private static int dp2(int money, Integer[] faces) {
        if (money < 1) {
            return -1;
        }
        int[] dp = new int[money + 1];
        for (Integer face : faces) {
            dp[face] = 1;
        }
        return dp2(dp, money);
    }

    private static int dp2(int[]dp, int money) {
        if (money < 1) {
            return Integer.MAX_VALUE;
        }
        if (dp[money] == 0) {
            int dp25 = dp2(dp, money - 25);
            int dp20 = dp2(dp, money - 20);
            int dp5  = dp2(dp, money - 5);
            int dp1  = dp2(dp, money - 1);
            dp[money] = Math.min(Math.min(dp25, dp20), Math.min(dp5, dp1)) + 1;
        }
        return dp[money];
    }

    private static int dp1(int money, Integer[] faces) {
        if (money < 0) {
            return Integer.MAX_VALUE;
        }
        for (Integer face : faces) {
            if (face.equals(money)) {
                return 1;
            }
        }
        int dp25 = dp1(money - 25, faces);
        int dp20 = dp1(money - 20, faces);

        int dp5 = dp1(money - 5, faces);
        int dp1 = dp1(money - 1, faces);
        return Math.min(Math.min(dp25, dp20), Math.min(dp5, dp1)) + 1;
    }
}
