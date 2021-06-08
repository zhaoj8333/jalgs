package phase1.ds.tree.uf;

/**
 * 幷查集是以数组实现的树形数据结构(二叉堆, 优先级队列也是用数组实现的树形结构)
 */
public abstract class UnionFind implements UF {
    protected int[] parents;

    public UnionFind(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must >= 1");
        }
        this.parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            // 最开始所有元素 各自属于一个单元素集合
            parents[i] = i;
        }
    }

    @Override
    public abstract int find(int index);

    @Override
    public abstract void union(int index1, int index2);

    @Override
    public boolean isSame(int index1, int index2) {
        return find(index1) == find(index2);
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index > parents.length) {
            throw new IllegalArgumentException("v is out of bounds");
        }
    }

    public void show() {
        System.out.print("index:   ");
        for (int i = 0; i < parents.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("pointer: ");
        for (int parent : parents) {
            System.out.print(parent + " ");
        }
        System.out.println();
        System.out.println("==================================");
    }
}
