package phase1.algs.greedy;

import java.util.Arrays;

/*
最优装载问题（加勒比海盗）:
        一艘货船，有很多古董，每一件都价值连城
        海盗船载重为W， 每一件古董重量为wi，海盗如何把尽可能多的古董装上船

    贪心策略：
        每一次都选择重量最小的古董
 */
public class Pirate {
    public static void main(String[] args) {
        int[] weights = new int[] {3, 5, 4, 10, 7, 14, 2, 11};
        Arrays.sort(weights);
        System.out.println(Arrays.toString(weights));

        int capacity = 30, weight = 0, count = 0;
        for (int i = 0; i < weights.length && weight < capacity; i++) {
            int newWeight = weight + weights[i];
            if (newWeight <= capacity) {
                weight = newWeight;
                count ++;
                System.out.println(weights[i]);
            }
        }
        System.out.println("一共" + count + "件古董");
    }
}
