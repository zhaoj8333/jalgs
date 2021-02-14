package phase1.ds.uf.bak;

import phase1.ds.uf.UnionFind;

public class UnionFindQF extends UnionFind {

    public UnionFindQF(int capacity) {
        super(capacity);
    }

    /**
     *  查找v所属集合
     */
    @Override
    public int find(int index) {
        rangeCheck(index);
        return parents[index];
    }

    /**
     *  把index1合并到index2
     *  将index1所在集合的元素 全部 嫁接到 index2所在的元素
     *  导致树的高度狗永远`永远是2
     */
    @Override
    public void union(int index1, int index2) {
        int p1 = find(index1);
        int p2 = find(index2);
        if (p1 == p2) {
            return;
        }
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1) {
                parents[i] = p2;
            }
        }
    }

    @Override
    public boolean isSame(int index1, int index2) {
        return find(index1) == find(index2);
    }

    public static void main(String[] args) {
        UnionFindQF ufqf = new UnionFindQF(12);
        ufqf.union(0, 1);
        ufqf.union(0, 3);
        ufqf.union(0, 4);
        ufqf.union(2, 3);
        ufqf.union(2, 5);

        ufqf.union(6, 7);

        ufqf.union(8, 10);
        ufqf.union(9, 10);
        ufqf.union(9, 11);

        ufqf.union(4, 6);

//        System.out.println(ufqf.isSame(0, 6));
//        System.out.println(ufqf.isSame(0, 5));
    }
}
