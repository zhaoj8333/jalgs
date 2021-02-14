package phase1.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FastFail {
    public static void main(String[] args) {
        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
    }

    private static void test6() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        final Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            final String tmp = itr.next();
            System.out.println(tmp);
            if ("1".equals(tmp)) {
                itr.remove();
            }
        }
        System.out.println(list);
    }

    // Unsupported
    private static void test5() {
        List<String> list = Arrays.asList("1", "2", "3", "4");
        for (String tmp : list) {
            if ("1".equals(tmp)) {
                list.remove("1");
            }
        }
    }

    // CME
    private static void test4() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        for (String tmp : list) {
            if ("1".equals(tmp)) {
                list.remove("1");
            }
        }
    }

    // CME
    private static void test3() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final String tmp = iterator.next();
            System.out.println(tmp);
            if ("4".equals(tmp)) {
                list.remove("4");
            }
        }
    }

    private static void test2() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final String tmp = iterator.next();
            System.out.println(tmp);
            if ("3".equals(tmp)) {
                list.remove("3");
            }
        }
    }

    // CME
    private static void test1() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final String tmp = iterator.next();
            System.out.println(tmp);
            if ("1".equals(tmp)) {
                list.remove("1");
            }
        }
    }
}
