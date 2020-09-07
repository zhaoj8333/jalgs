package algs.hash;

import edu.princeton.cs.algs4.StdOut;
import java.io.*;

public class Hash {
    public static void main(String[] args) throws IOException {
//        StdOut.println(algs.hash(1));
//        intHash();
//        floatHash();
        stringHash();
//        HashConflictTester.hashTest();
    }

    private static void stringHash() {
        String[] str = new String[] {"a", "b", "aa", "bb", "数据库了对方"};
        for (int i = 0; i < str.length; i++) {
            char[] chars = str[i].toCharArray();
            int hash = 0;
            int h = hash;
            // h ==0 用以缓存hash值
            // chars.length > 0 空字符串哈西值为0
            if (h == 0 && chars.length > 0) {
                for (int j = 0; j < chars.length; j++) {
                    h = 31 * h + chars[j];
//     s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
//     使用素数31作为乘数，因为 31 * i == (i << 5) - i, 效率更高
                    StdOut.println(Integer.toBinaryString((int)chars[j]));
                }
                hash = h;
            }
//            StdOut.println(str[i] + " : " + h);
        }
    }

    private static void floatHash() {
        Float[] num = new Float[] {0.4f, 0.1f, 0.2f, 0.12f, 0.09f, 0.1f, 0.32f, 0.4f, 0.9f, 0.2f, 0.3f, 0.2f, 0.7f, 0.1232f, 0.9f};
        for (int i = 0; i < num.length; i++) {
//            System.out.println(num[i].hashCode());
        }

        for (int i = 0; i < num.length; i++) {
//            StdOut.println((int)(num[i] * 10000) % 97f);
//            StdOut.println(Float.toHexString(num[i]));
            StdOut.printf("%x\n", Float.floatToRawIntBits(num[i]));
        }

    }

    private static void intHash() {
        // 一般int选择一个素数即可，通过除留余数法得到即可
        int[] num = new int[] {212, 618, 302, 940, 702, 704, 612, 606, 772, 510, 423, 650, 317, 907, 507};
        for (int i = 0; i < num.length; i++) {
            StdOut.print(num[i] % 100 + " ");
            if (i == num.length - 1) {
            }
        }
        System.out.println();
        for (int i = 0; i < num.length; i++) {
            StdOut.print(num[i] % 97 % num.length + " ");
        }
        System.out.println();
    }

    /**
     * 散列函数：
     *     1. 散列函数的值必须是大于等于0的正整数，因为hash值要作为数组的下标
     *     2. 如果key1 == key2，那么hash(key1) == algs.hash(key2)
     *     3. 如果key1 == key2，那么hash(key1) != algs.hash(key2)
     *
     * 好的哈希函数必须：
     * 哈希函数不能太复杂
     * 哈希值必须尽可能的随机且均匀分布，将哈希冲突最小化
     */


    /**
     * 哈希冲突（散列冲突）：
     *     两个不同的关键字key，由于散列值相同，被映射到同一位置上，称为哈希冲突
     *
     * 解决方案：
     *     再好的散列函数也无法避免散列冲突<
     *     
     */
    private static int hash() {
        return hash();
    }


    /**
     * 哈希算法（摘要算法）：
     *    1. 一条任何大小的信息，计算出一个唯一的定长的摘要，对输入非常敏感，哪怕原始数据
     *       只修改了一个bit，最后的哈希值也是大不相同
     *    2. 摘要长度固定，散列冲突小
     *    3. 摘要不能被反向破译，即哈希算法是单向的
     *    4. 执行高效
     *
     * md5
     * sha
     * ......
     *
     *
     */
    private static int hash(int i) {
        return ((i + 2) << 2 % 8) ^ 5;
    }
}
