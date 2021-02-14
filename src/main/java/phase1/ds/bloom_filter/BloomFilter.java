package phase1.ds.bloom_filter;

/**
 * 布隆过滤器: 一个空间效率高的 概率型 数据结构, 用来判断一个元素不存在或可能存在
 *
 * 缺点: 有一定误判率, 删除困难
 *
 * 实质: 一个很长的 二进制向量 和一系列随机 映射函数(hash)
 *
 * 应用: 黑名单系统, 垃圾邮件过滤, 爬虫判重, 缓存穿透
 *
 * 添加: 将每一个哈希函数生成的索引为止设为1
 * 查询是否存在: 如果有一个函数生成的索引为止不为1, 就不存在(100%准确)
 *             如果每一个哈希函数生成的索引都为1, 就代表存在(存在一定误判率), 因为某个位的1是其他元素添加时设置的,
 * 删除: 复杂复杂度的非常高, 删除某一个元素可能会导致另一个元素被删除
 *
 * 降低误判率就是减少哈希冲突
 *
 * 添加,查询时间复杂度为O(K),k为哈希函数个数
 * 空间复杂度为O(m),m为二进制位个数
 *
 * 误判率影响因素:
 *     二进制位的个数m
 *     哈希函数个数
 *     数据规模n
 */
@SuppressWarnings("all")
public class BloomFilter<T> {

    /* 二进制向量长度(二进制位个数) */
    private int bitSize = 20;

    /* 二进制向量 */
    private long[] bits;
    // private long bitsLong;   // 比较大时使用数组
    // private long[] bits;
    private int hashSize;

    /**
     * @param n 数据规模
     * @param p 误判率
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0 || p >= 1) {
            throw new IllegalArgumentException("参数不准确");
        }
        double ln2 = Math.log(2);
        bitSize  = (int) (- (n * Math.log(p)) / (ln2 * ln2));
        hashSize = (int) (ln2 * bitSize / n);
        bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];
    }

    /**
     * 添加
     */
    public void put(T val) {
        nullCheck(val);
        final int hash1 = val.hashCode();
        final int hash2 = hash1 >>> 16;
        for (int i = 1; i <= hashSize; i++) {
            int hash = hash1 + (i * hash2);
            if (hash < 0) {
                hash = ~hash;
            }
            setBit(hash % bitSize);  // 设置某一个二进制位的索引
        }
    }

    /**
     * 按位或
     *
     * @param index
     */
    private void setBit(int index) {
        // 从左到右算
        long value = bits[index / Long.SIZE];
        // long内部从右到左计算
        bits[index / Long.SIZE] = value |= 1 << (index % Long.SIZE);
    }

    private boolean getBit(int index) {
        long value = bits[index / Long.SIZE];
        // 获取对应二进制位的值
        return (value & (1 << (index % Long.SIZE))) != 0;
    }

    private void nullCheck(T val) {
        if (val == null) {
            throw new IllegalArgumentException("value 不能为null");
        }
    }

    /**
     * 判定是否存在
     */
    public boolean contains(T val) {
        nullCheck(val);
        final int hash1 = val.hashCode();
        final int hash2 = hash1 >>> 16;
        for (int i = 1; i <= hashSize; i++) {
            int hash = hash1 + (i * hash2);
            if (hash < 0) {
                hash = ~hash;
            }
            return getBit(hash);  // 设置某一个二进制位的索引
        }
        return true;
    }

    public static void main(String[] args) {
//        testInt();
        testUrl();
    }

    private static void testUrl() {
        final BloomFilter<String> bf = new BloomFilter<>(10_0000_0000, 0.01);

    }

    private static void testInt() {
        final BloomFilter<Integer> filter = new BloomFilter<>(1_00_0000, 0.01);
        int count = 1_00_0000;
        for (int i = 1; i <= count; i++) {
            filter.put(i);
        }
        for (int i = 1; i <= count; i++) {
//            Assert.assertTrue(filter.contains(i));
        }
        int mistakes = 0;
        for (int i = 1_00_0000; i <= 2_00_0000; i++) {
            if (filter.contains(i)) {
                mistakes ++;
            }
        }
        System.out.println("误判: " + mistakes);
    }
}
