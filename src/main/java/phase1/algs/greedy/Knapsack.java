package phase1.algs.greedy;

import java.util.*;

/**
 *   背包问题：
 *       有n件物品 和 一个最大承重为W的背包，每件物品重量是wi， 价值是vi
 *       在保证总重量不超过W的前提下，将哪几件物品装入背包，可以使得背包总价值最大
 *       * 每个物品只有1件，也就是每个物品只能选择0或1件，因此成为 0 - 1 背包问题
 *
 *   三个方案：
 *      1. 价值主导：优先选择机制最高的物品
 *      2. 重量主导：优先选择重量最轻的物品放进背包
 *      3. 价值密度主导： 价值密度 = 价值 / 重量
 */
public class Knapsack {
    public static void main(String[] args) {
        // 价值主导
        select((Article a1, Article a2) -> a2.value - a1.value);
        System.out.println();
        //  重量主导
        select(Comparator.comparingInt((Article a) -> a.weight));
        System.out.println();
        // 价值密度主导
        select((Article a1, Article a2) -> {
            return Double.compare(a2.valueDensity, a1.valueDensity);
        });

    }

    private static void select(Comparator<Article> cmp) {
        Article[] articles = new Article[] {
                new Article(35, 10),
                new Article(30, 40),
                new Article(60, 30),
                new Article(50, 50),
                new Article(40, 35),
                new Article(10, 40),
                new Article(25, 30),
        };
        Arrays.sort(articles, cmp);
        int capacity = 150, weight = 0, value = 0;
        List<Article> selected = new LinkedList<>();
        for (int i = 0; i < articles.length && weight < capacity; i++) {
            int newWeight = weight + articles[i].weight;
            if (newWeight <= capacity) {
                weight = newWeight;
                value += articles[i].value;
                selected.add(articles[i]);
            }
        }
        System.out.println("总价值：" + value);
        System.out.println("总重量：" + weight);
        System.out.println("物品列表： " + selected);
        System.out.println("================================================");
    }

    private static class Article {
        public int weight;
        public int value;
        public double valueDensity;

        public Article(int weight, int value) {
            if (weight <= 0) {
                throw new IllegalArgumentException("weight can not lower than 0");
            }
            this.weight = weight;
            this.value = value;
            this.valueDensity = (double)value / (double)weight;
        }

        @Override
        public String toString() {
            return "\n[" + "w: " + weight + ", v: " + value + ", vd: " + valueDensity + ']';
        }
    }
}
