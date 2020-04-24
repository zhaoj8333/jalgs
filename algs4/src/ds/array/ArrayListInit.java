package ds.array;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class ArrayListInit {
    public static void main(String[] args) {
        demo01();
    }

    private static void demo01() {
        ArrayList<String> list = new ArrayList<>(2);
        /**
         * ArrayList:
         *     ArrayList.elementData
         *     DEFAULTCAPACITY_EMPTY_ELEMENTDATA
         */
        list.add("a");
        list.add("b");
        list.add("c");

        Object o = list.get(0);
        StdOut.println(o);
    }
}
