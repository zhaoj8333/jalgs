package ds.tree.bst;

import ds.tree.printer.BinaryTrees;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;

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
        StdOut.println();
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
        StdOut.println();
        StdOut.println("remove: " + tmp);
        bt4.remove0(tmp);
//        bt4.remove0(tmp.element);
        StdOut.println();
        BinaryTrees.println(bt4);
    }

    private static void testSuccessor() {
        StdOut.println("tmp : " + tmp);
        Node<Integer> succ = bt4.succ(tmp);
        StdOut.println("succ: " + succ);
    }

    private static void testPredecessor() {
        StdOut.println("tmp : " + tmp);
        Node<Integer> pred = bt4.pred(tmp);
        StdOut.println("pred: " + pred);
    }

    private static void testMax() {
        StdOut.println("max loop     : " + bt4.max());
        StdOut.println("max algs.recursion: " + bt4.max0());
    }

    private static void testMin() {
        StdOut.println("min loop     : " + bt4.min());
        StdOut.println("min algs.recursion: " + bt4.min0());
    }

    public static void getComplete() {
        int count = 0;
        while (true) {
            count++;
            bt4 = new BinaryTree4<>();
            int num = 16;
            Integer[] arr = new Integer[num];
            for (int i = 0; i < num; i++) {
                arr[i] = StdRandom.uniform(num * 3);
            }
            for (Integer integer : arr) {
                bt4.put(integer);
            }
            if (bt4.isComplete()) {
                break;
            }
        }
        StdOut.println("count: " + count);
        BinaryTrees.println(bt4);
    }

    public static void testIsComplete() {
        StdOut.println(bt4.isComplete());
    }

    public static void testInvert() {
//        bt4.invert();
//        BinaryTrees.println(bt4);
        bt4.invert0();
        BinaryTrees.println(bt4);
    }

    public static void testHeight() {
        int height = bt4.height();
        StdOut.println("height: " + height);
        int height0 = bt4.height0();
        StdOut.println("height: " + height0);
    }

    public static void testIterate() {
        ArrayList<Node<Integer>> list;
        list = bt4.iterate(1, true);
        StdOut.println("prev  : " + list);
        list = bt4.iterate(1, false);
        StdOut.println("prev  : " + list);

        list = bt4.iterate(2, true);
        StdOut.println("mid   : " + list);
        list = bt4.iterate(2, false);
        StdOut.println("mid   : " + list);

        list = bt4.iterate(3, true);
        StdOut.println("post  : " + list);
        list = bt4.iterate(3, false);
        StdOut.println("post  : " + list);

        list = bt4.iterate(4, true);
        StdOut.println("level : " + list);
        list = bt4.iterate(4, false);
        StdOut.println("level : " + list);

    }

    public static void testNode() {
        Integer ele = 33;
        StdOut.println();
        StdOut.println("元素 " + ele + " 的所在节点为： " + bt4.node(ele));
        StdOut.println("元素 " + ele + " 的所在节点为： " + bt4.node0(ele));
    }

    public static void testPut() {
        int num = 16;
        Integer[] arr = new Integer[num];
        for (int i = 0; i < num; i++) {
            arr[i] = StdRandom.uniform(num * 3);
        }
        StdOut.println(Arrays.toString(arr));
        for (int i = 0; i < num; i++) {
//            bt4.put(arr[i]);
            bt4.put0(arr[i]);
        }
        while (true) {
            rand = StdRandom.uniform(num * 3);
            tmp = bt4.node(rand);
            if (tmp != null) {
                break;
            }
        }
        BinaryTrees.println(bt4);
    }
}
