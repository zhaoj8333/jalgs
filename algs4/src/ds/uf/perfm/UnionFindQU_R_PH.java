package ds.uf.perfm;

import java.util.Arrays;

/**
 * 路径压缩使路径上所有节点都指向根节点，成本稍高，且有递归调用
 *
 * 路径减半: 路径上每隔一个节点就指向其祖父节点
 */
public class UnionFindQU_R_PH extends UnionFindQU {

    private int[] sizes;

    public UnionFindQU_R_PH(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        Arrays.fill(sizes, 1);
    }

    /**
     * 路径减半
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
