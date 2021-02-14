package phase1.ds.uf.perfm;

import java.util.Arrays;

/**
 * 基于rank的优化树高会相对平衡，但是union越多，树的高度依然会越来越高
 */
public class UnionFindQU_R_PC extends UnionFindQU {

    private int[] sizes;

    public UnionFindQU_R_PC(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        Arrays.fill(sizes, 1);
    }

    /**
     *  路径压缩： find时使得路径上的所有节点都指向根节点，降低树高
     */
    @Override
    public int find(int index) {
        if (index != parents[index]) {
            parents[index] = find(parents[index]);
        }
        return parents[index];
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
