package phase1.ds.tree.uf.perfm;

import phase1.ds.tree.uf.UnionFind;

public class UnionFindQU extends UnionFind {
    public UnionFindQU(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int index) {
        rangeCheck(index);
        while (index != parents[index]) {
            index = parents[index];
        }
        return index;
    }

   @Override
    public void union(int index1, int index2) {
        int pos1 = find(index1);
        int pos2 = find(index2);
        if (pos1 == pos2) {
            return;
        }
        parents[pos1] = pos2;
    }


}
