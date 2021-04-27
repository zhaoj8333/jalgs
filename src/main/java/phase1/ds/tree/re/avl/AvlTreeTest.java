package phase1.ds.tree.re.avl;

import phase1.ds.tree.printer.BinaryTrees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AvlTreeTest {

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            AvlTree<Integer> bt = AvlTreeTest.generateAvlTree();
//            BinaryTrees.println(bt);
//        }

        AvlTree<Integer> bt = AvlTreeTest.getFixedAvlTree();
        BinaryTrees.println(bt);
        System.out.println();
        bt.remove(14);
        BinaryTrees.println(bt);
        bt.remove(19);
        bt.remove(15);
        bt.remove(18);
        BinaryTrees.println(bt);

    }

    public static AvlTree<Integer> generateAvlTree() {
        int size = 40;
        AvlTree<Integer> bt = new AvlTree<>();
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

    public static AvlTree<Integer> getFixedAvlTree() {
        Integer[] list = {14, 0, 1, 13, 3, 8, 10, 15, 19, 11, 18, 9, 2, 17, 5, 7};
        AvlTree<Integer> bt = new AvlTree<>();
        for (Integer integer : list) {
            bt.add(integer);
        }
        return bt;
    }
}
