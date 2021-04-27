package phase1.ds.heap.re;

import phase1.ds.tree.printer.BinaryTrees;
import java.util.Arrays;
import java.util.Comparator;

public class BinaryHeapTest {

    public static void main(String[] args) {
//        testShiftUp();
        testHeapify();
    }

    private static void testHeapify() {
        Integer[] list = new Integer[] {68, 72, 43, 50, 38, 58, 30, 99};
        BinaryHeap<Integer> bh = new BinaryHeap<>(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == null) {
                    o1 = 0;
                }
                return o1 - o2;
            }
        });
        BinaryTrees.println(bh);
        System.out.println();
    }

    private static void testShiftUp() {
        BinaryHeap<Integer> bh = new BinaryHeap<>();
//        HashSet<Integer> set = new HashSet<>();
//        for (int i = 0; i < 10; i++) {
//            int r = (new Random()).nextInt(100);
//            if (!set.contains(r)) {
//                set.add(r);
//                bh.add(r);
//            }
//        }
        bh.add(68);
        bh.add(72);
        bh.add(43);
        bh.add(50);
        bh.add(38);
        bh.add(58);
        bh.add(30);
        bh.add(99);
//        System.out.println(set);
        System.out.println(Arrays.toString(bh.getElements()));
        BinaryTrees.println(bh);
        System.out.println();

        bh.remove();
        BinaryTrees.println(bh);

        System.out.println();
        bh.replace(100);
        BinaryTrees.println(bh);

    }
}
