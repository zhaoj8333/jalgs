package phase1.algs.dp;

public class CoinChange1 {
    public static void main(String[] args) {
//        final int coins = coins(41);
//        System.out.println(coins);
//        System.out.println(count);
//        count = 0;
//        final int i = coins1(41);
//        System.out.println(i);
//        System.out.println(count);

        coins2(41);

    }

    /**
     * 递推(自底向上)
     */
    private static int coins2(int money) {
        if (money < 1) {
            return -1;
        }
        int[] dp = new int[money + 1];
        for (int i = 1; i <= money; i++) {
            dp[i] = 0;
        }
        return dp[money];
    }

    private static int coins1(int n) {
        if (n < 1) {
            return -1;
        }
        int[] dp = new int[n + 1];
        dp[1] = dp[5] = dp[20] = dp[25] = 1;
        return coinsByRemember(n, dp);
    }

    /**
     * 记忆化搜索, 减少重叠子问题
     */
    private static int coinsByRemember(int money, int[] dp) {
//        count++;
        if (money < 1) {
            return Integer.MAX_VALUE;
        }
        if (dp[money] == 0) {
            final int min1 = Math.min(coinsByRemember(money - 25, dp), coinsByRemember(money - 20, dp));
            final int min2 = Math.min(coinsByRemember(money - 5, dp), coinsByRemember(money - 1, dp));
            dp[money] = Math.min(min1, min2) + 1;
        }
        return dp[money];
    }

    private static int count = 0;

    /**
     * 暴力递归: 自顶向下(从大到小, 出现重叠子问题)
     */
    private static int coins(int money) {
//        count += 1;
        if (money <= 0) {
            return Integer.MAX_VALUE;
        }
        if (money == 25 || money == 20 || money == 5 || money == 1) {
            return 1;
        }

        int min1 = Math.min(coins(money - 25), coins(money - 20));
        int min2 = Math.min(coins(money - 5), coins(money - 1));

        return Math.min(min1, min2) + 1;
    }
}
