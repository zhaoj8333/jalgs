package phase1.ds.tree.uf.bak;

import phase1.ds.tree.uf.UnionFind;

public class UnionFindQU extends UnionFind {

   public UnionFindQU(int capacity) {
        super(capacity);
   }

   @Override
   public int find(int index) {
        rangeCheck(index);
        while (index != parents[index]) {
//            System.out.println("index: " + index);
            index = parents[index];
        }

        return index;
   }

    /**
     *  左边 index1 跟随 右边 index2
     *
     *  将index1的根节点嫁接到index2的根节点上
     *
     *  union的过程中，可能会出现树不平衡的情况，设置退化为链表
     *  优化：
     *      1. 基于size的优化，元素少的 嫁接 到元素多的树
     *      2. 基于rank的优化，矮的树 嫁接到 高的树
     */
    @Override
    public void union(int index1, int index2) {
        int p1 = find(index1);
        int p2 = find(index2);
        if (p1 == p2) {
            return;
        }
        parents[p1] = p2;
    }

    public static void main(String[] args) {
        UnionFindQU uf = new UnionFindQU(10);

        System.out.print("index: ");
        for (int i = 0; i < uf.parents.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("value: ");
        for (int i = 0; i < uf.parents.length; i++) {
            System.out.print(uf.parents[i] + " ");
        }
        System.out.println();
        System.out.println("===========================");
        System.out.println();

        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);

        System.out.print("index: ");
        for (int i = 0; i < uf.parents.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("value: ");
        for (int i = 0; i < uf.parents.length; i++) {
            System.out.print(uf.parents[i] + " ");
        }
        System.out.println();
        System.out.println("===========================");

        System.out.println("root of 0: " + uf.find(0) + "\n");
        // 5

        System.out.println("root value of 0: " + uf.parents[uf.find(0)]);
        // 5
    }
}
