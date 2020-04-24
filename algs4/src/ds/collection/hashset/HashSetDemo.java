package ds.collection.hashset;

import java.util.HashSet;

public class HashSetDemo {
    public static void main(String[] args) {
        demo1();
    }

    private static void demo1() {
        HashSet<String> hs = new HashSet<>();
        hs.add("a");
        hs.add("b");
        hs.add("c");
        hs.add("d");

        System.out.println(hs);
    }


}
