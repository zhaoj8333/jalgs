package phase1.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayAndArrayList {
//    数组与其他种类的容器之间的区别有三个方面：效率、类型和保存基本类型的能力。
//    在 Java 中，数组是一种效率最高的存储和随机访问对象引用序列的方式。
    public static void main(String[] args) {
//        arrayVsArrayList();
//        arrayToList1();
        arrayToList2();
    }

    enum Week{Sun, Mon, Tue, WeD, Thu, Fri, Sat};

    private static void arrayToList2() {
        Week[] weeks = {Week.Sun, Week.Mon, Week.Tue, Week.WeD, Week.Thu, Week.Fri};
        List<Week> list = Arrays.asList(weeks);

        // Arrays内部类 ArrayList没有add方法，不支持的操作
        // UnsupportedOperationException
        list.add(Week.Sat);
    }

    private static void arrayToList1() {
//        int[] arr = new int[]{ 1, 2, 3, 4, 5};
        Integer[] arr = new Integer[]{ 1, 2, 3, 4, 5};
        System.out.println("array : " + Arrays.toString(arr));
        List list = Arrays.asList(arr);
        System.out.println();
        // asList:
        // Java 中，数组会当做一个对象来处理，它是可以泛型的
        System.out.println("size  : " + list.size());
        System.out.println("list  : " + list);
        System.out.println("first : " + list.get(0));
    }

    private static void arrayVsArrayList() {
        int len = 100000000;
        demo1(len);
        demo2(len);
    }

    private static void demo2(int len) {
        ArrayList<Integer> al = initList(len);
        Long s = System.currentTimeMillis();
        long sum = 0;
        // al.get(i) 该操作是拆箱动作,有不必要的性能损耗
        for (int i = 0; i < al.size(); i++) {
            sum += al.get(i);
        }
        Long e = System.currentTimeMillis();
        System.out.println("arraylist: " + (e - s));
    }

    private static void demo1(int len) {
        //        array vs list
        int[] arr = initArr(len);
        Long s = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        Long e = System.currentTimeMillis();
        System.out.println("array: " + (e - s));
    }

    public static ArrayList<Integer> initList(int len) {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < len; i ++) {
            al.add(i);
        }

        return al;
    }

    public static int[] initArr(int len) {
        int[] arrays = new int[len];
        for (int i = 0; i < len; i++) {
            arrays[i] = i;
        }

        return arrays;
    }
}
