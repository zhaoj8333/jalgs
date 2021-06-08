package phase1.ds.tree.uf.bak;

import java.util.Arrays;

/**
 * quickunion基于size的优化
 */
public class UnionFindQURankPathCompression extends UnionFindQU {
    private int[] ranks;

    /**
     * 路径压缩：
     *     find时使路径上的所有节点都指向根节点(从底部向上的节点),从而降低树的高度
     *
     * 路径压缩使路径上的所有节点都指向根节点,所以实现成本稍高
     * 再次优化：　
     *  路径分裂: 使路径上的每个节点都指向其祖父节点　parent的parent
     *  路径减半:
     */
    public UnionFindQURankPathCompression(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        Arrays.fill(ranks, 1);
    }

    @Override
    public int find(int index) {
        rangeCheck(index);
        if (parents[index] != index) {
            parents[index] = find(parents[index]);
        }
        return parents[index];
    }

    /**
     * 基于rank的优化同样会导致树高度越来越高
     */
     @Override
     public void union(int index1, int index2) {
          int p1 = find(index1);
          int p2 = find(index2);
          if (p1 == p2) {
             return;
          }
          if (ranks[p1] < ranks[p2]) {
              parents[p1] = p2;
          } else if (ranks[p1] > ranks[p2]) {
              parents[p2] = p1;
          } else {
              parents[p1] = p2;
              ranks[p2]++;
          }
     }

     public static void main(String[] args) {
         UnionFindQURankPathCompression uf = new UnionFindQURankPathCompression(10);
         uf.union(0, 1);
         uf.union(0, 3);
         uf.union(0, 4);

         uf.union(2, 3);
         uf.union(2, 5);
         System.out.println(uf.isSame(2, 3));
         System.out.println(uf.isSame(0, 6));

     }
}
