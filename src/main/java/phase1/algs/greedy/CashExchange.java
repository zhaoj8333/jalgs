package phase1.algs.greedy;

import java.util.Arrays;

/*
    假设有 25分、，10分，5分，1分的硬币，现要给客户41分的零钱，如何办到硬币个数最少

    贪心策略：
        每一次 都优先选择面值最大的硬币
        贪心策略不一定可以得到全局最优解
        因为一般没有测试所有可能的解，容易过早做决定，没发打到最优解
        贪图眼前利益最大化，看不到长远未来，走一步看一步

    优点：
        简单、高效、不需要穷举所有可能
        通常作为其他算法的辅助算法来使用

    获取最优解需要使用 动态规划

 */
public class CashExchange {
    public static void main(String[] args) {
        Integer[] faces1 = {10, 5, 25, 1};
        change(faces1, 41, 4);
        Integer[] faces = {10, 5, 25, 1};
        change(faces);
        faces = new Integer[]{25, 20, 5, 1};
        change(faces, 41);
        faces = new Integer[] {11, 19, 7, 4, 2};
        change1(faces, 51);
    }

    private static void change1(Integer[] faces, int money) {
        Arrays.sort(faces);
        System.out.println(Arrays.toString(faces));
        int coinCount = 0, index = faces.length - 1;
        while (index >= 0) {
            while (money >= faces[index]) {
                money -= faces[index];
                coinCount++;
                System.out.print(faces[index] + " ");
                // 19 19 11 2
            }
            index--;
        }
        System.out.println();
        System.out.println("硬币数量:" + coinCount);
    }

    private static void change(Integer[] faces, int money) {
        Arrays.sort(faces);
        System.out.println(Arrays.toString(faces));
        int coins = 0, idx = faces.length - 1;
        while (idx >= 0) {
            while (money >= faces[idx]) {
                System.out.println("---- " + faces[idx]);
                money -= faces[idx];
                coins++;
            }
            idx--;
        }
        System.out.println(coins);
    }

    private static void change(Integer[] faces) {
        Arrays.sort(faces, (Integer f1, Integer f2) -> f2 - f1);
        int money = 44, coins = 0, i = 0;
//        change(faces, money, coins);
        while (i < faces.length) {
            if (money < faces[i]) {
                i++;
                continue;
            }
            System.out.println(faces[i]);
            money -= faces[i];
            coins++;
            if (i > 0) {
                i--;
            }
        }
    }

    private static void change(Integer[] faces, int money, int coins) {
        for (int i = 0; i < faces.length; i++) {
            if (money < faces[i]) {
                continue;
            }
            System.out.println(faces[i]);
            money -= faces[i];
            coins++;
            i = faces.length;
        }
        System.out.println("coins: " + coins);
    }
}
