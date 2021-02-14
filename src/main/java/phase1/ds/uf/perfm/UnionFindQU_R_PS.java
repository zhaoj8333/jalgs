package phase1.ds.uf.perfm;

import java.util.Arrays;

/**
 * 路径分裂
 *
 * 路径压缩使路径上所有节点都指向根节点，成本稍高，且有递归调用
 */
public class UnionFindQU_R_PS extends UnionFindQU {

    private int[] ranks;

    public UnionFindQU_R_PS(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        Arrays.fill(ranks, 1);
    }

    /**
     *  路径分裂： 路径上的每个节点都指向其祖父节点
     */
    @Override
    public int find(int index) {
        while (index != parents[index]) {
            // index = parents[index];
            int p = parents[index];
            parents[index] = parents[parents[index]];
            // index父节点  = index祖父节点
            index = p;
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
        if (ranks[pos1] < ranks[pos2]) {
            // pos1 并入 pos2
            parents[pos1] = pos2;
        } else if (ranks[pos1] > ranks[pos2]) {
            // pos2 并入 pos1
            parents[pos2] = pos1;
        } else {
            parents[pos1] = pos2;
            ranks[pos2] ++;
        }
    }

}
