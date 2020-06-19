package ds.uf.bak;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * quickunion基于size的优化
 */
public class UnionFindQURank extends UnionFindQU {
    private int[] ranks;

    public UnionFindQURank(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        Arrays.fill(ranks, 1);
    }

    /**
     * 基于size的优化也可能存在树不平衡的问题
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
         UnionFindQURank uf = new UnionFindQURank(10);
         uf.union(0, 1);
         uf.union(0, 3);
         uf.union(0, 4);

         uf.union(2, 3);
         uf.union(2, 5);
         StdOut.println(uf.isSame(2, 3));
         StdOut.println(uf.isSame(0, 6));

     }
}
