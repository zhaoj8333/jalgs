package hash;

/**
 * @author allen
 */
public class HashmapHashcode {
    public static void main(String[] args) {
//        xor();
        byteXor();
//        switchByXor();

//        objDiff();
        whyLength2n();
    }

    private static void switchByXor() {
        int a = 11;
        int b = 22;
//        System.out.println("a: " + a + ",  b: " + b);
        System.out.println("0" + Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));

        System.out.println();
        a = a ^ b;
        System.out.println(Integer.toBinaryString(a));
        b = a ^ b;
        System.out.println(Integer.toBinaryString(b));
        a = a ^ b;

        System.out.println("a: " + a + ",  b: " + b);

    }

    private static void byteXor() {
        int i = 234814235;
        int j = 15;

        int m = i >>> 16;
        int n = m ^ i;

        System.out.println("i: " + Integer.toBinaryString(i));
        System.out.println("m: " + Integer.toBinaryString(m));
        System.out.println("n: " + Integer.toBinaryString(n));

        int x = 15 & n;
        System.out.println("x: " + Integer.toBinaryString(x));
//        System.out.println(Integer.toBinaryString(j));
//        System.out.println(Integer.toBinaryString(i));
        // 00001101 11111110 11111011 00011011
        // 00000000 00000000 00000000 00001111
        // 00001101 11111110 11111011 00010100
//        System.out.println(Integer.toBinaryString(i ^ j)); // 4228
    }

    private static void whyLength2n() {
        /*
            &操作时，&出来的数字实际上和 %等价
            &必然会小于 &操作中最小的那个数
            该值必然会小于等于数组长度

            2^n:
             2^n - 1， 后几位全是 1，为奇数，否则有可能为偶数，
             偶数按位与计算时结果给一定是偶数,这样数组索引为奇数的位永远不会映射上值

             如果是奇数，结果有可能是奇数或偶数，是偶或奇取决于hashCode

            如果被与的数字0太多的话，则计算出的索引失去了hashCode计算的目的，增加了hash碰撞的概率
         */
    }

    /**
     * 散列函数的设计
     *
     *     1. 直接寻址法：
     *         hash(key) = key
     *         取关键字key的某个线性函数值为散列地址，即hash(key) = a x key + b
     *         该方法简单，均匀，不会有冲突，但是必须知道关键字key的分布情况，适合表较小且连续的情况，不常用
     *
     *     2. 除留余数法： 对于列表长为m的散列函数公式为 hash(key) = key mod p(p <= m)
     *         该方法关键在于选择合适的p，如果p选的不好可能产生hash冲突
     *         比如有12个关键字key，则为 hash(key) = key mod 12，但是比如有12,18，都会为6，则会产生hash冲突
     *         使用该方法的经验是：
     *              如果散列表为m，通常p为小于或等长（接近m）的最大质数或不包含小于20质因子的合数
     *              总之当p取小于m的最大质数时，产生的哈希值比较好
     *
     *     3. 平方取中法：
     *         hash(key) = key 平方的中间几位
     *         通过平方扩大差别，平方值的中间几位和这个数都相关，这样对不同关键字的哈希函数值
     *         不易产生冲突
     *
     */

    /**
     * 哈希冲突（散列冲突）：
     *     两个不同的关键字key，由于散列值相同，被映射到同一位置上，称为哈希冲突
     *     key1 != key2, 但是 hash(key1) == hash(key2)
     * 解决方案: 再好的散列函数也无法避免散列冲突
     *     1. 开放寻址，一旦出现冲突，从冲突的索引地址开始，依次查找，重新寻找一个空的散列地址，
     *         线性检测： 复杂读为O(n)，检测的步长为 +1
     *         二次探测： 每次检测时下标递增 n^2
     *         双重散列： 使用一组散列函数 hash1(key), hash2(key),hash3(key)
     *     2. 链表法： 简单很多，适合存储大对象，大数据量的链表，且更灵活，支持优化策略，比如用红黑树代替链表
     *
     * 散列表特点：
     *     1. 快速查询，插入，删除
     *     2. 内存占用合理
     *     3. 性能稳定
     *
     * 设计实现：
     *     1. 合适的散列函数
     *     2. 装载因子阈值，动态扩容策略
     *     3. 合适的散列冲突解决办法
     */

    private static void xor() {
//  HashMap:
//      (n - 1) & (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//      (n - 1) & (h = key.hashCode()) & (h >>> 16)
        // h: 32位长度的hash值
        int hash = "菩萨地方".hashCode();
        System.out.println(hash);
//        System.out.println(Integer.toBinaryString(hash));
        // 00111101 11110001 10010110 00101000    高16位
        int i = hash >>> 16;
        System.out.println(i);
//        System.out.println(Integer.toBinaryString(i));
        // 00000000 00000000 00111101 11110001    低16位

        // 高十六位 与低十六 位异或xor 的原因
        // objDiff

    }

    private static void objDiff() {
        /*
        1. 为何不与固定值做 & 运算
            比较对象不同时，如果参与的元素越多，对象相同的可能性越小

            如果直接拿hashCode直接和 固定值做 & 运算，32位长度的大量位没有起到作用
        2. 为何要做异或 ^ 运算
            & | ^ 三种运算符中，只有 ^运算符计算出的每个位得到的 0 或 1 的概率为0.5

        所以： 让尽量多的位数参与运算，且让数字每个位0 1分布尽量平均，尽量保证hash值的不同

         */
    }
}
