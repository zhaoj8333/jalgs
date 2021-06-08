package phase1.ds.tree.uf.perfm;

import java.util.Arrays;

/**
 * 路径压缩使路径上所有节点都指向根节点，成本稍高，且有递归调用
 *
 * 路径减半: 路径上每隔一个节点就指向其祖父节点
 */
public class UnionFindQU_R_PH extends UnionFindQU {

    private int[] ranks;

    public UnionFindQU_R_PH(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        Arrays.fill(ranks, 1);
    }

    /**
     * 路径减半: 使路径上每隔一个节点指向其祖父节点 parents[parents[index]]
     */
    @Override
    public int find(int index) {
        while (index != parents[index]) {
            parents[index] = parents[parents[index]];
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
