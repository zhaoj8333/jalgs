package ds.collection.linkedlist;

import java.util.LinkedList;

public class LinkedListTest1 {
    public static void main(String[] args) {
//        demo1();
    }

    private static void demo1() {
        /*
            ArrayList： 动态数组
                优点： 查询快
                缺点：
                    增删慢： 指定索引上的添加、 扩容问题
                    浪费内存空间
         */

        /*
            LinkedList也实现了动态数组接口
            链表没有索引概念,查询速度慢
            节约空间
            增删改速度快
         */
        LinkedList<String> link = new LinkedList<>();
        link.add("a");
        link.add("b");
        link.add("c");
        link.add("d");
        System.out.println(link);
        System.out.println(link.get(2));
    }
}
