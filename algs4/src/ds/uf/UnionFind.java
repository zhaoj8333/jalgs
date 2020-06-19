package ds.uf;

import edu.princeton.cs.algs4.StdOut;

public abstract class UnionFind implements UF {
    protected int[] parents;

    public UnionFind(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must >= 1");
        }
        this.parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
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
        StdOut.print("index:   ");
        for (int i = 0; i < parents.length; i++) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        StdOut.print("pointer: ");
        for (int parent : parents) {
            StdOut.print(parent + " ");
        }
        StdOut.println();
        StdOut.println("==================================");
    }
}
