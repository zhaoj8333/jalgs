package operator;

import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

public class Xor {
    public static void main(String[] args) {
//        swap(1, 2);
//        findOnce();
//        bitFlip();
        parityCheck(21);
    }

    /**
     * 奇偶校验
     *     判断一个数中国1的数量是奇数还是偶数
     */
    private static void parityCheck(int x) {
        // p ^ 0 = p
        // 若二进制中每一位执行异或结果为1，则1数量为奇数，如果结果为0，则1的数量是偶数
        int r = 0;
        while (x > 0) {
            x >>= 1;
        }
    }

    /**
     * 确定a转换为b，需要反转的位数
     */
    private static void bitFlip() {
        int a = 18;
        System.out.println(Integer.toBinaryString(a));
        int b = 9;
        System.out.println("0" + Integer.toBinaryString(b));
        int count = 0;
        // 转换为 a ^ b中1的个数
        /*
            10010
            01001

            11011
         */

        int c = a ^ b;
//        StdOut.println(countOne1(c));
//        StdOut.println(countOne2(c));
        StdOut.println(countOne3(c));
        /*
        while (c != 0) {
            c = c & (c - 1);
            count ++;
        }
        StdOut.println(count);

         */
    }

    private static int countOne3(int c) {

        return 0;
    }

    /**
     *  O(n): 更快
     */
    private static int countOne2(int c) {
        int count = 0;
        for (count = 0; c > 0; ++count) {
//            c & (c - 1) 会清楚最低位的1
            c &= (c - 1);
        }

        return count;
    }

    /**
     * O(n)
     */
    private static int countOne1(int c) {
        int count = 0;
        /*
         while (c > 0) {
            if ((c & 1) == 1) {
                count++;
            }
            c >>= 1;
        }*/

        for (count = 0; c > 0 ; c >>= 1) {
            count += c & 1;
        }

        return count;
    }

    private static void findOnce() {
        // A ^ C ^ B ^ C ^ B & A & D
        // A^A^B^B^C^C^D
        // 0 ^ 0 ^ 0 ^ D
        // 0^D
        // D
        int[] arr = new int[]{1, 1, 2, 2, 12, 8, 8, 90, 90};
        // 异或 的 交换律和结合律
        int x = 0;
        for (int i = 0; i < arr.length; i++) {
            x ^= arr[i];
        }
        System.out.println(x);
    }

    private static void swap(int a, int b) {
        /*
            a = a ^ b;   a = a0 ^ b0, b = b0
            b = a ^ b;   a = a0 ^ b0, b = (a0 ^ b0) ^ b0 = a0
            a = a ^ b;   a = (a0 ^ b0) ^ a0, b = a0
         */
//        StdOut.println("a: " + a + ", b: " + b);
        // 0001
        // 0010
        a = a ^ b; // 0011
        StdOut.println("a: " + Integer.toBinaryString(a));
        // 0011
        // 0001
        b = a ^ b; // 0001
        StdOut.println("b: " + Integer.toBinaryString(b));
        a = a ^ b; // 0010
        StdOut.println("a: " + Integer.toBinaryString(a) + ", b: " + Integer.toBinaryString(b));
    }
}
