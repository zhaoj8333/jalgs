package phase1.ds.tree.uf.perfm;

import java.util.Arrays;

public class UnionFindQU_S extends UnionFindQU {

    private int[] sizes;

    public UnionFindQU_S(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        Arrays.fill(sizes, 1);
    }

   @Override
    public void union(int index1, int index2) {
        int pos1 = find(index1);
        int pos2 = find(index2);
        if (pos1 == pos2) {
            return;
        }
        if (sizes[pos1] < sizes[pos2]) {
            // pos1 并入 pos2
            parents[pos1] = pos2;
            sizes[pos2]  += sizes[pos1];
        } else {
            // pos2 并入 pos1
            parents[pos2] = pos1;
            sizes[pos1]  += sizes[pos2];
        }
    }

}
