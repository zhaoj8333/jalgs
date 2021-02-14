package phase1.ds.uf.bak;

import java.util.Arrays;

/**
 * quickunion基于size的优化
 */
public class UnionFindQUSize extends UnionFindQU {
    private int[] sizes;

    public UnionFindQUSize(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        Arrays.fill(sizes, 1);
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
          if (sizes[p1] < sizes[p2]) {
              parents[p1] = p2;
              sizes[p2] += sizes[p1];
          } else {
              parents[p2] = p1;
              sizes[p1] += sizes[p2];
          }
     }

     public static void main(String[] args) {
         UnionFindQUSize uf = new UnionFindQUSize(10);
         uf.union(0, 1);
         uf.union(0, 3);
         uf.union(0, 4);

         uf.union(2, 3);
         uf.union(2, 5);
         System.out.println(uf.isSame(2, 3));
         System.out.println(uf.isSame(0, 6));

     }
}
