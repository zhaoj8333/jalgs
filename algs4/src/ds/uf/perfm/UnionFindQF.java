package ds.uf.perfm;

import ds.uf.UnionFind;

public class UnionFindQF extends UnionFind {
    public UnionFindQF(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int index) {
        rangeCheck(index);
        return this.parents[index];
    }

   @Override
    public void union(int index1, int index2) {
        int pos1 = find(index1);
        int pos2 = find(index2);
        if (pos1 == pos2) {
            return;
        }
       for (int i = 0; i < parents.length; i++) {
           if (parents[i] == pos1) {
               parents[i] = pos2;
           }
       }
    }

}
