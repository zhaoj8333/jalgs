package phase1.ds.tree.re.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTree<Integer> bt = generateBinaryTree();
//        BinaryTrees.println(bt);
//
//        Integer eight = 8;
//        Node<Integer> eightNode = bt.get(eight);
//        System.out.println(bt.pred(eightNode));
//        System.out.println(bt.succ(eightNode));

        bt.traversal();
//        System.out.println(bt.height());
//        System.out.println("isComplete: " + bt.isComplete());

//        BinaryTree<Integer> bt = getCompleteBinaryTree();
//        BinaryTrees.println(bt);
//
//        bt.reverse();
//        BinaryTrees.println(bt);
//        BaseBinaryTree<Integer> fbt = generateFixedTree();
//        fbt = generateBinaryTree();
//        BinaryTrees.println(fbt);
//        fbt.remove(8);
//        fbt.remove(1);
//        fbt.remove(9);
//        BinaryTrees.println(fbt);

    }

    public static BinaryTree<Integer> getCompleteBinaryTree() {
        while (true) {
            BinaryTree<Integer> bt = generateBinaryTree();
            if (bt.isComplete()) {
                return bt;
            }
        }
    }

    public static BinaryTree<Integer> generateBinaryTree() {
        int size = 40;
        BinaryTree<Integer> bt = new BinaryTree<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Integer tmp = new Random().nextInt(size / 2);
            list.add(tmp);
            bt.add(tmp);
        }

        System.out.println(list);
//        BinaryTrees.println(bt);

        return bt;
    }

    public static BinaryTree<Integer> generateFixedTree() {
        Integer[] numbers = new Integer[] {9, 16, 3, 1, 18, 14, 7, 19, 15, 0, 12, 4, 12, 8};
        numbers = new Integer[] {1, 13, 0, 6, 8, 12, 11, 6, 18, 19, 13, 12, 19, 14, 11, 5, 17, 12, 12, 18, 6, 16, 9, 0, 13, 0, 17, 7, 13, 8, 19, 19, 7, 7, 1, 6, 7, 2, 7, 18};
        BinaryTree<Integer> fbt = new BinaryTree<>();
        for (Integer number : numbers) {
            fbt.add(number);
        }
        return fbt;
    }
}
