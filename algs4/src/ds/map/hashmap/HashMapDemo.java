package ds.map.hashmap;

import java.util.HashMap;

/**
 * 允许null 键/值
 * 非同步
 * 不保证有序
 */
public class HashMapDemo {
    public static void main(String[] args) {
//        putProcess();
//        hashAlgs();
//        unsignedMoveRight();
//        hashMapProperties();
//        construct();
        putHashMap();
//        resizeHashmap();
    }

    private static void resizeHashmap() {
        /*
            当HashMap中的元素个数超过数组大小 （数组长度 * loadFactor）时，进行数组扩容
            扩容为扩大一倍，然后重新计算每个元素在数组中的位置，这是一个非常耗时的操作，
            预知元素个数能有效提高性能

            rehash:
                因为每次扩容都是翻倍，与原来计算的(n-1) & hash只是多了一个bit位，
                所以节点要么就在原来的位置，要么被分配到 “原位置 + 旧容量”的位置

                新索引高位是0，那么就存储在原位置
                如果高位是1， 则存储在“原位置 + 旧容量位置” 的位置

            扩充HashMap的时候，不需要重新计算hash，只需看原来hash值新增bit是1还是0即可
            省去重新计算hash的时间，且保证rehash之后更少的hash冲突
         */
    }

    /**
     * hash函数，散列函数
     *     哈希函数是通过把key的hash值映射到数组中的一个位置来进行访问
     */
    private static void xorHash(Object key) {
        /**
         * 扰动函数：
         *
         */
        int h;
        h = key.hashCode();
        System.out.println("h: " + h);
        System.out.println("h: " + Integer.toBinaryString(h));
        int i = h >>> 16;  // 0
        // 如果不做这一步，万一高16位变化很大，这样运算结果就不会改变，增加了hash碰撞的概率
        System.out.println("i: " + i);
        System.out.println("i: " + Integer.toBinaryString(i));

        int x = i ^ h;  // h
        System.out.println("x: " + x);
        System.out.println("x: " + Integer.toBinaryString(x));
        System.out.println("=======================");
        /*
            key == "aa"
            h: 00000000 00000000 00001100 00100000
            i: 00000000 00000000 00000000 00000000
            x: 00000000 00000000 00001100 00100000
         */

        /*
            key == "其他复杂的"
            h: 10101000 10101011 10000011 10001100
            i: 00000000 00000000 10101000 10101011 hashCode右移16位
            ^: 10101000 10101011 00101011 00100111 与本身做异或操作,混合哈西值的高位和低位，增加低位随机性
            保留hashCode高16位
            将hashCode的高16位与低16位异或作为低16位
         */

        /*
            1. 如果hashmap长度不是2的n次幂，以16长度为例， length - 1后几位必然为1，
            与hash值做按位与运算时，最后一位可能是0，也会是1，但是如果偶数与hash值
            作运算时,必然为0，所以某些位置上就永远映射不上值
            2. 即使散列函数很松散，但只取后几位碰撞会很严重
            3. 扰动函数：
                降低hash碰撞，尽可能分散
                保证算法高效：位运算
         */
    }

    private static void and(Object o) {
        int i = 16;
        int hash = o.hashCode();
        System.out.println("i:    " + Integer.toBinaryString(i - 1));
        System.out.println("hash: " + Integer.toBinaryString(hash));
        System.out.println("hash: " + hash);
        System.out.println("%:    " + (hash % i));
        System.out.println("&:    " + ((i - 1) & hash)); // 1
//      当i为2的n次幂时: 相当于对数组长度取余
//      因为位运算相对于取余操作很费时
        /*
            o == "a"

            i - 1: 00000000 00000000 00000000 00001111
            hash:  00000000 00000000 00000000 01100001
            &:
                   00000000 00000000 00000000 00000001
         */
        /*
            i - 1: 00000000 00000000 00000000 00001111
            hash:  00111101 00000011 00010010 11111001
            &:     00000000 00000000 00000000 00001001  9
         */

        /*
            为何hashmap长度总是2的n次幂，因为hash & (table.length - 1)来得到该对象
            的保存位。 hash & (table.length - 1)运算等价于对length取模，也即是 hash % length
         */
    }

    private static void putHashMap() {
//        xorHash("b");
//        xorHash("按实际的快乐飞卡萨");
//        and("a");
//        and("a的看法分");
        /**
         * 1. 通过hash值计算出key映射到哪个桶
         *    hashCode:
         *      hashmap允许key为null,如果为null，hashCode则为0，这一点不同于Hashtable
         *      key.hashCode() 任何对象都有一个hashCode,但是hashCode都比较大
         *      然后将hashCode整体右移16位，
         *      再与hashCode作按位异或操作
         * 2. 桶上没有冲突，直接插入
         * 3. 出现冲突，则处理冲突
         *    如果该桶使用红黑树，使用红黑树的方法写入
         *    否则采用链表方式插入。如果链表长度打到临界值，转变为红黑树
         * 4. 如果桶中有重复的键，则该键值替换为新值
         * 5. 如果size大于阈值threshold，扩容
         * 6. 如果节点长度即链表长度大于阈值8且数组长度大于64则将链表转化为红黑树
         *
         * treeifyBin: xxxxxx
         *
         */
         HashMap<String, Integer> hm = new HashMap<>();
         hm.put("aa", 1);
//        System.out.println("aa".hashCode());
         hm.put("bb", 2);
         hm.put("c", 3);
         hm.put("d", 3);
         hm.put("e", 3);
         hm.put("f", 3);
         hm.put("i", 3);
         hm.put("j", 3);
         hm.put("k", 3);
         hm.put("h", 3);
         hm.put("m", 3);
         hm.put("l", 3);
         hm.put("bb", 2222);
         hm.put("cc", 2222);
         hm.put("dd", 2222);
         hm.put("ee", 2222);
         hm.put("ff", 2222);
    }

    private static void construct() {
//        HashMap(); //空参构造
//        HashMap(int initialCapacity) // 指定容量大小
//        HashMap<String, Integer> hm = new HashMap<>(10);
//        ->
//        HashMap(int initialCapacity, float loadFactor);
//        System.out.println(hm.size());

//        HashMap(int initialCapacity, float loadFactor)
        /**
         * HashMap(Map<? extends K, ? extends V, > m) {
         *     this.loadFactor = DEFAULT_LOAD_FACTOR
         *     putMapEntries(m, false)
         * }
         *
         * this.threshold  = this.tableSizeFor(initialCapacity)
         * 而不是
         * this.threshold  = this.tableSizeFor(initialCapacity) * this.loadFactor
         * tableSizeFor(initialCapacity) 判断制定初始化容量是否是2的n次幂，不是则会转换
         * 但在jdk8以后的版本中，没有对table初始化，推迟到了put中，put中会对threshold重新计算
         */

        /**
         * putMapEntries(Map<? extends K, ? extends V> m, boolea evict)
         */
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("a", 1);
        hm.put("b", 2);
        hm.put("c", 3);
        HashMap<String, Integer> hm1 = new HashMap<>(hm);
        System.out.println(hm1);

    }

    private static void unsignedMoveRight() {
        int cap = 1 << 30;
        //       00111111 11111111 11111111 11111111
        int n   = cap - 1;
//        System.out.println("n: " + Integer.toBinaryString(n));
        n |= n >>> 1;   // 按位或
        n |= n >>> 2;   // 按位或
        n |= n >>> 4;   // 按位或
        n |= n >>> 8;   // 按位或
        n |= n >>> 16;   // 按位或
        System.out.println("n: " + Integer.toBinaryString(n));
        // >>> 1     00111111 11111111 11111111 11111111
        // >>> 最终  00111111 11111111 11111111 11111111
        /**
         * n: 9
         * 00000000 00000000 00000000 00001001   9
         * 00000000 00000000 00000000 00000100   9 >>> 1, 4
         * 00000000 00000000 00000000 00001001   9
         * ---------------------------------------
         * 00000000 00000000 00000000 00001101   13
         *
         * 多次右移后，结果为1111 ( 15 )
         */
        System.out.println(1 << 30);
        System.out.println(n + 1);
    }

    private static void defaultCapacityNotNpowerof2() {
        HashMap<String, Integer> hm = new HashMap<>(10);
        System.exit(0);
        /**
         * tableSizeFor(int cap)
         *
         * int n = cap - 1;
         * n |= n >>> 1;
         * n |= n >>> 2;
         * n |= n >>> 4;
         * n |= n >>> 8;
         * n |= n >>> 16;
         * return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
         *
         * 实例化HashMap时，如果给定initCapacity为10，该方法会找到 >= 该值的最小的2次幂
         */

        hm.put("aa", 1);
        hm.put("bb", 1);
        hm.put("cc", 1);
        hm.put("dd", 1);
        hm.put("ee", 1);
        System.out.println(hm);
        System.out.println("size: " + hm.size());
        System.out.println("重地".hashCode());
        System.out.println("通话".hashCode());
    }

    private static void defaultCapacityNpowerof2() {
        //  必须是2的n次幂
        //      DEEFAULT_INITAL_CAPACITY = 1 << 4
        //      默认加载因子：　DEFAULT_LOAD_FACTOR
        //   HashMap(int initialCapacity) 构造自定义初始容量和默认加载因子的HashMap
        //   2的n次幂原因：
        //      添加元素时，需要根据hash值，去确定其在数组中的位置。
        //      hashmap为了存取高效，要尽量减少碰撞，也就是尽量把数据分配均匀，每个链表长度大致相同，也即是说把数据存到哪个链表
        //      该算法实际上就是取模,hash % length,计算机中直接求余不如位运算效率高，所以源码中做了优化
        //      hash&(length - 1)，而实际上hash % length等于hash&(length - 1)前提是length = 2的n次幂
        //
        // 为何该方法可以减少hash碰撞????
        // 按位与
        // hash & (length - 1)如何减少hash碰撞??
        System.out.println((3 & (8 - 1)));
        /**
         * hash: 3 长度 8
         * 0000 0011  3
         * 0000 0111  7
         *----------------
         * 0000 0011  3
         * ================
         * hash: 2
         * 0000 0010
         * 0000 0111
         * --------------
         * 0000 0010  2
         *
         * 如果长度不是2的n次幂
         * hash: 3 长度 9
         * 0000 0011  3
         * 0000 1000  8
         * ----------------
         * 0000 0000  0
         * ================
         * hash: 2
         * 0000 0010  2
         * 0000 1000  8
         * --------------
         * 0000 0000  0
         *
         * 产生冲突
         *
         * 如果数组长度不是2的n次幂，计算出的索引特别容易相同，发生hash碰撞
         * 链表或红黑树过长，效率降低
         */
    }

    private static void hashMapProperties() {
        // 1. long serialVersionUID 序列化版本号
        // 2. 集合初始化容量:
//        defaultCapacityNpowerof2();
//        defaultCapacityNotNpowerof2();
        // 3. DEFAULT_LOAD_FACTOR: 0.75, 默认满12时扩容个
        // 4. MAXIMUM_CAPACITY: 1 << 30
        // 5. TREEIFY_THRESHOLD: 树节点所占空间是普通节点的两倍
        //   >>>>   链表超过8时，转为红黑树
        //    当hashCode离散型很好的时候，属性bin用到的概率非常小。
        //    但是随机hashCode下，离散型会很差，用户可能会使用不好的hashCode算法
        //    理想情况下hashCode算法下所有bin中节点分布频率会遵循泊松分布,一个bin中
        //    链表长度打到8的概率为0.00000006
        // 6. UNTREEIFY_THREASHOLD = 6
        //   >>>>   小于6时，转为链表
        //    红黑树查找复杂度为log(n)，长度为8时，为3
        //    链表平均查找复杂度为 n / 2，长度为8时为4，有转化为红黑树的必要
        //    链表长度如果为 <= 6， log(6) = 2.6，虽然速度快，但是转化为树结构和生成树的时间并不短

        // 7. MIN_TREEIFY_CAPACITY = 64 桶中转化为红黑树对应的数组最小值
        //    当map中的数量超过该值，表中的桶才能树形化，否则桶内元素太多会扩容，而不是树形化
        //    为了避免进行扩容，树形化选择的冲突，这个值不能小于 4 * TREEIFY_THRESHOLD(32)

        // 8. table就是hashmap中的数组，从来存放键值对
        // 9. 存放缓存
        // 10. size 元素个数,记录K-V数量，不是table的个数
        // 11. 阈值，当size大于该容量时进行扩容
        // 12. 加载因子 loadFactor（负载因子）
        //     0-1之间： 用来衡量hashmap的满的程度 loadFactor = size / Capacity(table.length)
        //         loadFactor太大导致查找元素效率低，
        //          太小则数组利用率低，存放数据很分散
        //          loadFactor = 0.75是一个比较好的临界值，大于0.75表示需要扩容,
        //          扩容需要rehash，赋值数据等操作，非常消耗性能，所以应该尽量减少库容次数，通过创建hashmap指定初始容量尽量避免
        //
        //      hashMap构造器中可以指定 loadFactor
        //

        /**
         * 为何loadFactor为 0.75，初始化临界值为12
         */
    }

    private static void hashAlgs() {
        //        System.out.println("a".hashCode());
//        System.out.println((new Integer(1)).hashCode());
        int a = 15;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(a >>> 2));

    }

    /**
     * HashMap<K, V>
     *
     *     HashMap<K, V>基于Map接口实现，实现映射操作,允许null值，不保证映射顺序，线程不安全
     *
     * jdk1.8之前：用数组+链表 组成
     * jdk1.8之后：数组+链表+红黑树，链表数量大于8时，且数组长度大于64，次索引上的所有数据使用红黑树存储
     *
     * 1. 特点：
     *      存取无序
     *      键和值都可以是null，但是键位置只能是一个null
     *      键位置唯一，底层数据结构控制键
     */
    private static void putProcess() {
        HashMap<String, Integer> sihs = new HashMap<>();
        /**
         * 创建hashmap集合对象时，
         * jdk8前，构造方法中创建了一个长度为16的 Entry[] table Map.Entry，用来存放键值对整体数据
         * jdk8以后，不是在hashmap的构造方法底层创建数组，而是第一次调用put时创建, Node[] table
         *
         */
        sihs.put("a", 1);
       /**
         * put: 存储("a", 1),
         *     根据 a 调用string类重写之后的hashCode方法计算出hash值，
         *     然后结合数组长度采用某种算法计算出向Node数组中存储数据的空间的索引值
         *     如果计算出的索引空间没有数据，则直接将 "a"-1 存储到数组
         *
         *     通过 “a"计算出索引
         *
         * hash计算方法：
         *     无符号右移 >>>
         *     按位异或 ^
         *     按位与 &
         *     平方取中
         *     取余
         *     伪随机数
         */
        sihs.put("b", 2);
        /**
         * 继续向哈希表中存储： "c" - 3
         *     拉链法：
         *     假设此时计算出的hashCode与某次计算出的一致，并且数组该索引处不是null，此时
         *     会比较两者hash值是否一致，不一致则画出一个节点来存储该数据
         */
        sihs.put("c", 3);
        sihs.put("d", 4);
        /**
         *  此时继续：
         *      d算出的hashcode计算出索引与上次一致，此时比较后存储的数据的hash值，相等，
         *      则发生hash冲突，底层调用所属类equals方法比较内容(key)是否相等
         *      1. 相等：用后者value替换前者value
         *      2. 不相等：继续向下和其他key比较，不相等，划出节点存储数据
         *
         *      如果链表长度大于阈值8，且数组长度大于64，将链表变为红黑树
         *
         *  扩容问题：
         *      当内容相等的元素较多超出临界值时，扩容为原容量的2倍，并将原数据复制
         *
         *  红黑树：
         *      哈希函数很难达到元素百分之百分布
         *
         *  HashMap继承结构:
         *  HashMap -> AbstractHashMap -> Map(interface), Cloneable, Serializable
         *  AbstractHashMap -> Map(interface) ？？？
         *
         *  集合框架创始人 Josh Bloch描述，这其实是一个失误
         */
        sihs.put("d", 444);

        System.out.println(sihs.toString());
    }
}
