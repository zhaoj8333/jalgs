package phase1.ds.tree.re.rb;

import phase1.ds.tree.printer.BinaryTrees;
import phase1.ds.tree.re.avl.AvlTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RbTreeTest {

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//        RbTree<Integer> bt = RbTreeTest.generateRbTree();
//            BinaryTrees.println(bt);
//        }

//        RbTree<Integer> bt = RbTreeTest.getFixedRbTree();
//        BinaryTrees.println(bt);
//        System.out.println();
//        bt.remove(55);
//        BinaryTrees.println(bt);
//        bt.remove(19);
//        bt.remove(15);
//        bt.remove(18);
//        BinaryTrees.println(bt);

        compareAvlAndRb();
    }

    public static void compareAvlAndRb() {
        int size = 30;
        long start = System.currentTimeMillis();
        AvlTree<Integer> avl = new AvlTree<>();
        for (int i = 0; i < size; i++) {
            avl.add(i);
        }
        System.out.println("avl height: " + avl.height());
        long end = System.currentTimeMillis();
        System.out.println("avl add: " + (end - start));

        BinaryTrees.println(avl);
        System.out.println();
        start = System.currentTimeMillis();

        RbTree<Integer> rb = new RbTree<>();
        for (int i = 0; i < size; i++) {
            rb.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("rb height: " + rb.height());
        System.out.println("rb add: " + (end - start));
        BinaryTrees.println(rb);
        rb.remove(0);
        BinaryTrees.println(rb);

    }

    public static RbTree<Integer> generateRbTree() {
        int size = 40;
        RbTree<Integer> bt = new RbTree<>();
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Integer tmp = new Random().nextInt(size / 2);
            if (!set.contains(tmp)) {
                bt.add(tmp);
                list.add(tmp);
            }
            set.add(tmp);
        }

        System.out.println(list);
//        BinaryTrees.println(bt);

        return bt;
    }

    public static RbTree<Integer> getFixedRbTree() {
        Integer[] list = new Integer[]{55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
        RbTree<Integer> bt = new RbTree<>();
        for (Integer integer : list) {
            bt.add(integer);
        }
//        for (int i = 0; i < 25; i++) {
//            bt.add(i);
//        }
        return bt;
    }
}
