package phase1.practice.hw;

public class CoinChange {
    public static void main(String[] args) {
        final int coins = coins(41, new int[]{1, 5, 25, 20});
        System.out.println(coins);
    }

    private static int coins(int n, int[] faces) {
        if (n < 1 || faces == null || faces.length == 0) {
            return -1;
        }
        int[] dp = new int[n + 1];

        return 0;
    }
}
