package basic.math;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 计算一个二进制数中非0二进制位的数量
 */
public class PopCnt {
    public static void main(String[] args) {
        int num = 6;
        num = 13;

//        StdOut.println("binary format: " + Integer.toBinaryString(num));
        int cnt = byBytesMove(num);
//        StdOut.println("number of ds.set bits: " + cnt);
//        cnt = byByteMoveRecursively(num);
//        StdOut.println("number of ds.set bits: " + cnt);
//        cnt = bySubstract(num);
//        StdOut.println("number of ds.set bits: " + cnt);
//        cnt = bySubstractRecursive(num);
//        StdOut.println("number of ds.set bits: " + cnt);
//        byLookup(323);
//        num = 3234;
//        StdOut.println("binary format: " + Integer.toBinaryString(num));
        cnt = byBuiltin(3234);
        StdOut.println("number of ds.set bits: " + cnt);
    }

    /**
     * 汉明距离：(Hamming distance)，表示两个等长字符串杂对应位置上butong字符的数目，d(x,y)，
     *     度量了通过替换字符的方式将x变成y所需要的最小替换次数
     *     karolin
     *     kathrin 3
     *
     *   a:  1011101
     *   b:  1001001 2
     *    对于二进制串a,b，d(x,y)等于a XOR b中1的数目，称为汉明权重(popcount)
     *
     *
     *
     */
    private static int byBuiltin(int i) {
        StdOut.println(i);
        i = i - ((i >>> 1) & 0x55555555);                // 0101 0101 0101 0101 0101 0101 0101 0101
        StdOut.println("0x55555555: " + Integer.toBinaryString(0x55555555));
        // 01010101 01010101 01010101 01010101
        // (i >>>1) & 0x55555555 计算每个两位中右1位中1的数量
        // StdOut.println(Integer.toBinaryString(i));
        // i:       00000000 00000000 00001000 01010001
        // i >>> 1  00000000 00000000 00000100 00101000
        // &        01010101 01010101 01010101 01010101
        // res:     00000000 00000000 00000100 00000000
        // i - res:
        // i:       00000000 00000000 00001000 01010001
        // res:     00000000 00000000 00000100 00000000
        // i :      00000000 00000000 00000100 01010001
        // 先计算每两个bit位中有几个1，结果保存至这两个bit位上
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333); // 0011 0011 0011 0011 001011 00111 0 0011
        StdOut.println(i);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;                // 0000 1111 0000 1111 0000 1111 0000 1111
        StdOut.println(i);
        i = i + (i >>> 8);            // 0x00ff00ff      // 0000 0000 1111 1111 0000 0000 1111 1111
        StdOut.println(i);
        i = i + (i >>> 16);           // 0x0000ffff      // 0000 0000 0000 0000 1111 1111 1111 1111
        StdOut.println(i);
        return i & 0x3f;              // 0x3f            //
    }

    /**
     * 10:  0000 1010
     *     0000 1010
     *     0000 1001 & -> 0000 1000         1
     *     0000 1000 & 0111 -> 0000 0000    2
     *
     * 13:  0000 1101
     *      0000 1100 & -> 0000 1100        1
     *      0000 1100 & 0000 1011 -> 0000 1000 -> 2
     *      0000 1000 & 0000 0111 -> 0000 0000 -> 3
     *
     * 185: 1011 1001
     *      1011 1000 & -> 1011 1000        1
     *      1011 1000 & 1011 0111 -> 1011 0000 -> 2
     *      1011 0000 & 1010 1111 -> 1010 0000 -> 3
     *      1010 0000 & 1001 0000 -> 1000 0000 -> 4
     *      1000 0000 & 0111 1111 -> 0000 0000 -> 5
     *
     *  O(logn)
     */
    public static int bySubstract(int n) {
        int cnt = 0;
        while (n != 0) {
            n &= (n - 1);
            cnt ++;
        }
        return cnt;
    }

    public static int bySubstractRecursive(int n) {
        if (n == 0) {
            return 0;
        }

        return 1 + bySubstractRecursive(n & (n - 1));
    }

    public static int byBytesMove(int number) {
        int cnt = 0;
        while (number != 0) {
            cnt += (number & 1);
            // >> 如果是负数左侧会补1，应使用无符号右移
            number >>>= 1;
        }
        return cnt;
    }

    public static int byByteMoveRecursively(int number) {
        if (number == 0) {
            return 0;
        }

        return byByteMoveRecursively(number >> 1) + (number & 1);
    }
}
