package ds.map.hashmap;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Objects;

public class HashMapDemo2 {

    public static void main(String[] args) {
        hashFunc();
    }

    private static void hashFunc() {
//        hash函数：
//        public int algs.hash(Object key) {
//            return hash_code(key) % table.length;
//        }
//        为了提高效率：前提是数组长度设计为 2^n(0111 1111)
//        public int algs.hash(Object key) {
//            return hash_code(key) & (table.length - 1);
//        }
//        良好的hash函数：
//           让hash值均匀分布，减少哈希碰撞次数，提升hash表的性能
//        java中，hashmap的key必须实现hashCode，equals方法，也允许key为null
//        intHash();
//        floatHash();
//        longHash();
//        doubleHash();
//        stringHash();
        customizedObjectHash();
    }

    private static class Person {
        private String name;
        private int age;
        private boolean sex;

        public Person(String name, int age, boolean sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        @Override
        public int hashCode() {
            int hash = Integer.hashCode(age);
            hash = 31 * hash + (name != null ? name.hashCode() : 0);
            return hash;
            /*
                31:  不仅符合2^n - 1, 而且是一个奇数， 有是 素数
                素数与其他数相乘的结果更容易产生唯一性，减少哈希冲突
             */
        }

        /**
         * 当哈希冲突时会被调用,用于比较两个key是否相等
         * 默认的equals是对比内存地址
         *
         * equals与hashcode最好一起都实现
         *
         * shallow comparison: The default implementation of equals in java.lang.Object simply checks if two references refer to the same object
         * deep comparison:  Comparision based on data members
         *
         * 对象指甲用equals
         * 基本类型之间用 ==
         *
         * 性质：
         *     自反性
         *     对称性
         *     传递性
         *     一致性
         *
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            // hashcode一致不代表两个对象相等，不能通过hashcode的比较而进行比较
            // 不同性质的甚至相同的对象 hashcode可能会相等
            // hashcode一致，计算出的索引也有可能一致
            Person person = (Person) o;
            return age == person.age && sex == person.sex && Objects.equals(name, person.name);
        }

        @Override
        public String toString() {
            return name;
        }

        // 自定义对象hashcode跟内存地址相关

        /*
            自定义对象作为key：
                最好同时重写hashcode与equals方法
                equals: 用来判断两个key是否是同一个key
                hashcode： 必须保证equals为true的两个key哈西值一样
         */
    }

    private static void customizedObjectHash() {
        Person p1 = new Person("a", 1, false);
        Person p2 = new Person("a", 1, false);
        StdOut.println(p1.hashCode() + " , " + p2.hashCode());
        StdOut.println("equals: " + (p1.equals(p2)));
        StdOut.println("==    : " + (p1 == p2));
        // 哈西值太大溢出时的处理，不作任何处理

        HashMap<Person, String> map = new HashMap<>();
        map.put(p1, "1");
        map.put(p2, "2");
        Person p3 = new Person("a", 2, false);
        map.put(p3, "3");
        StdOut.println(map);
    }

    private static void stringHash() {
//        jack: j * n^3 + a * n^2 + c * n^1 + k * n^0
//        [((j*n + a) * n) + c] * n + k
//        jdk中 n = 31, 31 * i = (i<<5 - i)
    }

    private static void doubleHash() {
        double val = 1232123L;
        long bits = Double.doubleToLongBits(val);
        long newVal = bits ^ (bits >>> 32);
        // 高32位与低32位异或计算
        // 如果是与运算 或 或运算，导致高位32位或低32位不起作用
        StdOut.println(newVal);
        StdOut.println(Long.toBinaryString(newVal));
    }

    private static void longHash() {
        // java中的hashcode类型是int，32位，不是long
        // 而long为64位
        long val = 23132L;
        long newVal = val ^ (val >>> 32);
        StdOut.println(newVal);
        StdOut.println(Long.toBinaryString(newVal));
    }

    private static void floatHash() {
//        浮点数的哈西值
//         将浮点在内存中的存储形式当成整数
        int i = Float.floatToRawIntBits(10.1f);
        StdOut.println(i);
        StdOut.println(Integer.toBinaryString(i));
    }

    private static void intHash() {
        // int的hash值即为本身
    }
}
