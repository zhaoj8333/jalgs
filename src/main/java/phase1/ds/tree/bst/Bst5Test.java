package phase1.ds.tree.bst;

import phase1.ds.tree.printer.BinaryTrees;
import java.util.Arrays;
import java.util.Random;

public class Bst5Test {
    private static BinaryTree5<Integer> bt5 = new BinaryTree5<>();

    public static void main(String[] args) {
//        testPut();
//        testIterate();
//        testGetHeight();
        testIsComplete();
    }

    private static void testIsComplete() {
        genBst(true);
        System.out.println(bt5.isComplete());
    }

    private static void testGetHeight() {
        final int height = bt5.getHeight();
        System.out.println(height);
        final int height0 = bt5.getHeight0();
        System.out.println(height0);
    }

    private static void testIterate() {
        System.out.print("preOrder: ");
        bt5.preOrderTraversal();
        System.out.println();
        bt5.midOrderTraversalAsc();
        System.out.println();
        System.out.print("postOrder: ");
        bt5.postOrderTraversal();
        System.out.println();
        System.out.print("levelOrder: ");
        bt5.levelOrderTraversal();

    }

    private static void testPut() {
        genBst(false);
    }

    private static void genBst(boolean print) {
        int num = 20;
        Integer[] data = new Integer[num];
        for (int i = 0; i < num; i++) {
            data[i] = new Random().nextInt(100);
        }
        data = new Integer[] {10, 5, 3, 6, 30, 20};
        if (print) {
            System.out.println(Arrays.toString(data));
        }
        for (Integer datum : data) {
            bt5.put(datum);
        }
        if (print) {
            BinaryTrees.println(bt5);
        }
    }
}
