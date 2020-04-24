package ds.collection.arraylist;

import java.util.ArrayList;

public class ArrayListDemo1 {
    public static void main(String[] args) {
//        demo1();
//        demo2();
//        demo3();
//
//        demo4();
//        demo5();

        int arr[] = new int[10];

    }

    private static void demo5() {
        ArrayList<String> list = new ArrayList<>(10);
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");

        list.set(0, "AAAAA");
        System.out.println(list);
    }

    private static void demo4() {
        ArrayList<String> arrs = new ArrayList<>(10);
        arrs.add("a");
        arrs.add("b");
        arrs.add("c");
        arrs.add("d");
        arrs.add("e");
        arrs.add("f");
        arrs.add("g");
        System.out.println(arrs);

//        arrs.remove(0);
//        System.out.println(arrs);
//        boolean res = arrs.remove("a");
//        System.out.println(arrs);
//        System.out.println(res);
//        arrs.remove("g");
//        System.out.println(arrs);
    }

    public static void demo3() {
        int n = 10;
        ArrayList<Integer> arrl = new ArrayList<>(n);
        for (int i = 0;i < n; i++) {
            arrl.add(i + 100);
        }
        System.out.println(arrl);
        // remove
        arrl.remove(0);

        /*
         * 根据元素还是索引删除，依赖于所给的类型
         * 但是元素是Integer时，可能会出现越界异常,所以应该使用索引删除
         *
         * 非得根据元素删除
         * */

        arrl.remove(Integer.valueOf(109));

        System.out.println(arrl);
        // get
        for (int i = 0; i < arrl.size(); i++) {
            System.out.println(arrl.get(i));
        }
    }

    public static void demo2() {
        // 加入泛型的集合容器
        ArrayList<Integer> list = new ArrayList<>();
        // ArrayList<Type> Type必须是引用类型
//        ArrayList<Double> list = new ArrayList<>();
        // 数据类型必须一致
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);
        list.add(500);
        list.add(8, 10);
        // IndexOutOfBoundsException
        // 这里的索引，不是面向底层的数组容器
        System.out.println(list);
    }

    /**
     * 集合:
     *  java动态数组,比静态数组慢
     *  可以存储任意数据类型
     *
     *  ArrayList is initialized by a size, the size can be changed with CRUD
     *  ArrayList can be randomly accessed
     *  ArrayList cannot be used for primitive types, You must use wrapper class
     *  ArrayList in java can be seen as similar to vector in C++
     */
    public static void demo1() {
        // 空参数构造时，会创建一个长度为10的数组
        ArrayList list = new ArrayList();
        System.out.println(list);
        list.add("aaaa");
        list.add("bbbb");
        list.add(1111);
        list.add(true);
        // 长度未达到10时，剩余不会显示

        System.out.println(list);
    }


}

