package ds.map;

import java.util.HashMap;

/**
 * HashMap实现
 *
 * jdk1.7：
 *     数组: O(1), 修改插入O(n)
 *     链表: O
 * jdk1.8
 *     数组
 *     链表
 *     红黑树
 */
public class HashMapTS {
    public static void main(String[] args) {
//        demo1();
        demo2();
    }

    private static void demo2() {

    }

    private static void demo1() {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("aaaa", 1);
        hm.put("bbbb", 2);
        hm.put("cccc", 3);
        hm.put("dddd", 4);
        hm.put("eeee", 5);

        System.out.println(hm);
        System.out.println(hm.get("aaaa"));
    }
}
