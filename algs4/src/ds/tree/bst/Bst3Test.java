package ds.tree.bst;

import cmp.Todo;
import ds.tree.printer.BinaryTrees;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * @author allen
 */
public class Bst3Test {
    private static BinaryTreeByLoop<Integer> btl;
    private static BinaryTreeByRecursion<Integer> btr;
    private static Integer[] arr;
    private static int count;
    private static int repeats = 1;

    static {
        count = 15;
        arr = new Integer[count];
        btl = new BinaryTreeByLoop<>();
        btr = new BinaryTreeByRecursion<>();
//        StdRandom.setSeed(count);
//        bt4.setByRecursion(true);
    }

    public static String strRepeat(int n, char s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        testPut();
//        testGet();
//        testMin();
//        testTraversal();
//        testTraversalWithReturn();
//        testHeight();
//        testIsComplete();
//        findComplete();
//        testInvert();
//        testRefactor();
//        testPrevAndSucc();
//        testFloorAndCeil();
//        testSelect();
//        testRank();
//        testRemove();
    }

    public static void testRank() {
        StdOut.println();
        Integer ele = 10;
        int rank = btr.rank(ele);
        StdOut.println("recursion: " + ele + " 的排名为: " + rank);

        rank = btl.rank(ele);
        StdOut.println("loop     : " + ele + " 的排名为: " + rank);
    }

    public static void testSelect() {
        StdOut.println();
        int k = 7;
        Integer selectK = btr.select(k);
        StdOut.println("recursion: 排名为 " + k + " 的键为： " + selectK);

        Integer selectL = btl.select(k);
        StdOut.println("loop     : 排名为 " + k + " 的键为： " + selectL);
    }

    public static void testRemove() {
        StdOut.println();
        for (int i = 0; i < 10; i++) {
            btl.remove(i);
            StdOut.println("-----------------------------------");
            StdOut.println("【 " + i + " 】");
            BinaryTrees.println(btl);
        }
    }

    public static void testFloorAndCeil() {
        StdOut.println();
        Node<Integer> floor = btr.floor(10);
        Node<Integer> ceil  = btr.ceil( 10);
        StdOut.println("floor by recursion: " + floor);
        StdOut.println("ceil  by recursion: " + ceil);
    }

    public static void testPrevAndSucc() {
        Node<Integer> prev = btl.pred(10);
        Node<Integer> succ  = btl.succ(10);
        StdOut.println();
        StdOut.println("prev by loop: " + prev);
        StdOut.println("succ  by loop: " + succ);
        StdOut.println();
//        floor = btr.floor(10);
//        ceil  = btr.ceil(10);
//        StdOut.println("floor by recursion: " + floor);
//        StdOut.println("ceil  by recursion: " + ceil);
    }

    @Todo
    public static void testRefactor() {
//        btl.refactor(btl.root().element);
        StdOut.println(strRepeat(60, '-'));
        BinaryTrees.println(btl);
    }

    public static void testInvert() {
//        btr.invert(btr.root().element);
        btl.invert(btl.root().element);
        StdOut.println(strRepeat(60, '-'));
        BinaryTrees.println(btl);
    }

    public static void findComplete() {
        Stopwatch st = new Stopwatch();
        do {
            for (int i = 0; i < 15; i++) {
                arr[i] = StdRandom.uniform(count);
            }
            for (int i = 0; i < repeats; i++) {
                btr = null;
                btr = new BinaryTreeByRecursion<>();
                for (Integer integer : arr) {
                    btr.put(integer);
                }
            }
        } while (! btr.isComplete());
        BinaryTrees.println(btr);
        StdOut.println("duration: " + st.elapsedTime());
    }

    public static void testIsComplete() {
        boolean isComplete = btr.isComplete();
        StdOut.println(isComplete);
    }

    public static void testHeight() {
        int height = btr.depth();
        StdOut.println("recursion: " + height);

        height = btl.depth();
        StdOut.println("loop     : " + height);

    }

    public static void testTraversalWithReturn() {
//        ArrayList<Integer> list = btr.traversal(1);
//        StdOut.println("前序： " + list);
//        ArrayList<Integer> list = btr.traversal(2);
//        StdOut.println("中序： " + list);

//        ArrayList<Integer> list = btr.traversal(3);
//        StdOut.println("后序： " + list);

//        ArrayList<Integer> list = btr.traversal(4);
//        StdOut.println("层序： " + list);
    }

    private static void testPrev() {
        StdOut.print("recursion前序： ");
        btr.traversal(1, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                StdOut.print(node.element + "  ");
            }
        });
        StdOut.println();
        StdOut.print("loop前序     ： ");
        btl.traversal(1, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                StdOut.print(node.element + "  ");
            }
        });
        StdOut.println();
    }

    private static void testMid() {
        StdOut.print("loop中序     ： ");
        btl.traversal(2, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                StdOut.print(node.element + "  ");
            }
        });
        StdOut.println();
        StdOut.print("recursion中序： ");
        btr.traversal(2, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                StdOut.print(node.element + "  ");
            }
        });
        StdOut.println();
    }

    private static void testPost() {
        StdOut.print("recursion后序： ");
        btr.traversal(3, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                StdOut.print(node.element + "  ");
            }
        });
        StdOut.println();
        StdOut.print("loop后序     ： ");
        btl.traversal(3, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                StdOut.print(node.element + "  ");
            }
        });
    }

    private static void testLevel() {
        StdOut.println();
        StdOut.print("recursion层序： ");
        btr.traversal(4, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                StdOut.print(node.element + "  ");
            }
        });

        StdOut.println();
        StdOut.print("loop层序     ： ");
        btl.traversal(4, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                StdOut.print(node.element + "  ");
            }
        });
    }

    public static void testTraversal() {
        StdOut.println();
        testPrev();

        StdOut.println();
        testMid();

        StdOut.println();
        testPost();

        StdOut.println();
        testLevel();
    }

    public static void testMin() {
        StdOut.println("recursion min : " + btr.min());
        StdOut.println("recursion max : " + btr.max());
        StdOut.println();
        StdOut.println("loop min : " + btl.min());
        StdOut.println("loop max : " + btl.max());
    }

    public static void testGet() {
        Node<Integer> x = btl.get(10);
        StdOut.println();
        StdOut.println("get: ");
        StdOut.println("loop: " + x.element);
        Node<Integer> node = btr.get(10);
        StdOut.println("recursive: " + node.element);
    }

    public static void testPut() {
        for (int i = 0; i < count; i++) {
            arr[i] = StdRandom.uniform(count);
        }
        Stopwatch st = new Stopwatch();
        for (int i = 0; i < repeats; i++) {
            btl = null;
            btl = new BinaryTreeByLoop<>();
            for (Integer integer : arr) {
                btl.putByLoop(integer);
            }
        }
        StdOut.println("loop: " + st.elapsedTime());
        st = new Stopwatch();
        for (int i = 0; i < repeats; i++) {
            btr = null;
            btr = new BinaryTreeByRecursion<>();
            for (Integer integer : arr) {
                btr.put(integer);
            }
        }
        StdOut.println("recursion: " + st.elapsedTime());
        /*
        1000000
        loop: 0.97
        recursion: 0.995

        非递归方式有稍微性能优势
         */
        StdOut.println();
        StdOut.println(Arrays.toString(arr));
        StdOut.println();
        BinaryTrees.println(btl);
        StdOut.println(strRepeat(60, '='));
        BinaryTrees.println(btr);

    }
}
