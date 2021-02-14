package phase1.ds.array;

import java.util.Arrays;

/**
 * 环形数组：
 *     把第一个元素视为 最后一个元素的下一个元素1
 */
public class CircularArray {
    public static void main(String[] args) {
        char[] a = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};
//        byDoubleArray(a,3);
        gfg(a, 3);
    }

    private static void gfg(char[] a, int startIndex) {
        int n = a.length;
        for (int i = startIndex; i < n + startIndex; i++) {
            System.out.println(a[i % n] + " ");
            // 超过n的部分，取余后自然从 0 索引重新开始访问
        }
    }

    private static void byDoubleArray(char[] a, int startIndex) {
        int n = a.length;
        char [] b = new char[2 * n];

        for (int i = 0; i < n; i++) {
            b[i] = b[n + i] = a[i];
        }
        System.out.println(Arrays.toString(b));
        for (int i = startIndex; i < n + startIndex; i++) {
            System.out.println(b[i] + " ");
        }
    }
}
