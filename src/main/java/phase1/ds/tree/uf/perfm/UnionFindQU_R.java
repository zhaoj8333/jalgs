package phase1.ds.tree.uf.perfm;

import java.util.Arrays;

public class UnionFindQU_R extends UnionFindQU {

    private int[] ranks;

    public UnionFindQU_R(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        Arrays.fill(ranks, 1);
    }

   @Override
    public void union(int index1, int index2) {
        int pos1 = find(index1);
        int pos2 = find(index2);
        if (pos1 == pos2) {
            return;
        }
        if (ranks[pos1] < ranks[pos2]) {
            // pos1 并入 pos2
            parents[pos1] = pos2;
        } else if (ranks[pos1] > ranks[pos2]){
            // pos2 并入 pos1
            parents[pos2] = pos1;
        } else {
            parents[pos1] = pos2;
            ranks[pos2] ++;
        }
    }

}
