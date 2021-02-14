package phase1.ds.tree.bst;

import phase1.ds.tree.printer.BinaryTrees;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author allen
 */
public class Bst4Test<E> {

    private static BinaryTree4<Integer> bt4 = new BinaryTree4<>();
    private static Node<Integer> tmp;
    private static int rand;

    public static void main(String[] args) {
        testPut();
        // testNode();
//        testIterate();
//        testHeight();
//        testInvert();
//        testIsComplete();
//        getComplete();
//        testMin();
//        testMax();
//        testPredecessor();
//        testSuccessor();
//        testRemove();
        testRemoveMin();
        System.out.println();
        testRemoveMax();
    }

    private static void testRemoveMin() {
        bt4.removeMin();
        BinaryTrees.println(bt4);
    }

    private static void testRemoveMax() {
        bt4.removeMax();
        BinaryTrees.println(bt4);
    }

    private static void testRemove() {
        System.out.println();
        System.out.println("remove: " + tmp);
        bt4.remove0(tmp);
//        bt4.remove0(tmp.element);
        System.out.println();
        BinaryTrees.println(bt4);
    }

    private static void testSuccessor() {
        System.out.println("tmp : " + tmp);
        Node<Integer> succ = bt4.succ(tmp);
        System.out.println("succ: " + succ);
    }

    private static void testPredecessor() {
        System.out.println("tmp : " + tmp);
        Node<Integer> pred = bt4.pred(tmp);
        System.out.println("pred: " + pred);
    }

    private static void testMax() {
        System.out.println("max loop     : " + bt4.max());
        System.out.println("max phase1.algs.recursion: " + bt4.max0());
    }

    private static void testMin() {
        System.out.println("min loop     : " + bt4.min());
        System.out.println("min phase1.algs.recursion: " + bt4.min0());
    }

    public static void getComplete() {
        int count = 0;
        while (true) {
            count++;
            bt4 = new BinaryTree4<>();
            int num = 16;
            Integer[] arr = new Integer[num];
            for (int i = 0; i < num; i++) {
                arr[i] = new Random().nextInt(num * 3);
            }
            for (Integer integer : arr) {
                bt4.put(integer);
            }
            if (bt4.isComplete()) {
                break;
            }
        }
        System.out.println("count: " + count);
        BinaryTrees.println(bt4);
    }

    public static void testIsComplete() {
        System.out.println(bt4.isComplete());
    }

    public static void testInvert() {
//        bt4.invert();
//        BinaryTrees.println(bt4);
        bt4.invert0();
        BinaryTrees.println(bt4);
    }

    public static void testHeight() {
        int height = bt4.height();
        System.out.println("height: " + height);
        int height0 = bt4.height0();
        System.out.println("height: " + height0);
    }

    public static void testIterate() {
        ArrayList<Node<Integer>> list;
        list = bt4.iterate(1, true);
        System.out.println("prev  : " + list);
        list = bt4.iterate(1, false);
        System.out.println("prev  : " + list);

        list = bt4.iterate(2, true);
        System.out.println("mid   : " + list);
        list = bt4.iterate(2, false);
        System.out.println("mid   : " + list);

        list = bt4.iterate(3, true);
        System.out.println("post  : " + list);
        list = bt4.iterate(3, false);
        System.out.println("post  : " + list);

        list = bt4.iterate(4, true);
        System.out.println("level : " + list);
        list = bt4.iterate(4, false);
        System.out.println("level : " + list);

    }

    public static void testNode() {
        Integer ele = 33;
        System.out.println();
        System.out.println("元素 " + ele + " 的所在节点为： " + bt4.node(ele));
        System.out.println("元素 " + ele + " 的所在节点为： " + bt4.node0(ele));
    }

    public static void testPut() {
        int num = 16;
        Integer[] arr = new Integer[num];
        for (int i = 0; i < num; i++) {
            arr[i] = new Random().nextInt(num * 3);
        }
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < num; i++) {
//            bt4.put(arr[i]);
            bt4.put0(arr[i]);
        }
        while (true) {
            rand = new Random().nextInt(num * 3);
            tmp = bt4.node(rand);
            if (tmp != null) {
                break;
            }
        }
        BinaryTrees.println(bt4);
    }
}
