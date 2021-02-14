package phase1.ds.tree.bst;

import phase1.ds.tree.printer.BinaryTrees;
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
        System.out.println();
        Integer ele = 10;
        int rank = btr.rank(ele);
        System.out.println("phase1.algs.recursion: " + ele + " 的排名为: " + rank);

        rank = btl.rank(ele);
        System.out.println("loop     : " + ele + " 的排名为: " + rank);
    }

    public static void testSelect() {
        System.out.println();
        int k = 7;
        Integer selectK = btr.select(k);
        System.out.println("phase1.algs.recursion: 排名为 " + k + " 的键为： " + selectK);

        Integer selectL = btl.select(k);
        System.out.println("loop     : 排名为 " + k + " 的键为： " + selectL);
    }

    public static void testRemove() {
        System.out.println();
        for (int i = 0; i < 10; i++) {
            btl.remove(i);
            System.out.println("-----------------------------------");
            System.out.println("【 " + i + " 】");
            BinaryTrees.println(btl);
        }
    }

    public static void testFloorAndCeil() {
        System.out.println();
        Node<Integer> floor = btr.floor(10);
        Node<Integer> ceil  = btr.ceil( 10);
        System.out.println("floor by phase1.algs.recursion: " + floor);
        System.out.println("ceil  by phase1.algs.recursion: " + ceil);
    }

    public static void testPrevAndSucc() {
        Node<Integer> prev = btl.pred(10);
        Node<Integer> succ  = btl.succ(10);
        System.out.println();
        System.out.println("prev by loop: " + prev);
        System.out.println("succ  by loop: " + succ);
        System.out.println();
//        floor = btr.floor(10);
//        ceil  = btr.ceil(10);
//        System.out.println("floor by phase1.algs.recursion: " + floor);
//        System.out.println("ceil  by phase1.algs.recursion: " + ceil);
    }

    public static void testRefactor() {
//        btl.refactor(btl.root().element);
        System.out.println(strRepeat(60, '-'));
        BinaryTrees.println(btl);
    }

    public static void testInvert() {
//        btr.invert(btr.root().element);
        btl.invert(btl.root().element);
        System.out.println(strRepeat(60, '-'));
        BinaryTrees.println(btl);
    }

    public static void findComplete() {
//        Stopwatch st = new Stopwatch();
        do {
            for (int i = 0; i < 15; i++) {
//                arr[i] = StdRandom.uniform(count);
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
//        System.out.println("duration: " + st.elapsedTime());
    }

    public static void testIsComplete() {
        boolean isComplete = btr.isComplete();
        System.out.println(isComplete);
    }

    public static void testHeight() {
        int height = btr.depth();
        System.out.println("phase1.algs.recursion: " + height);

        height = btl.depth();
        System.out.println("loop     : " + height);

    }

    public static void testTraversalWithReturn() {
//        ArrayList<Integer> list = btr.traversal(1);
//        System.out.println("前序： " + list);
//        ArrayList<Integer> list = btr.traversal(2);
//        System.out.println("中序： " + list);

//        ArrayList<Integer> list = btr.traversal(3);
//        System.out.println("后序： " + list);

//        ArrayList<Integer> list = btr.traversal(4);
//        System.out.println("层序： " + list);
    }

    private static void testPrev() {
        System.out.print("recursion前序： ");
        btr.traversal(1, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                System.out.print(node.element + "  ");
            }
        });
        System.out.println();
        System.out.print("loop前序     ： ");
        btl.traversal(1, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                System.out.print(node.element + "  ");
            }
        });
        System.out.println();
    }

    private static void testMid() {
        System.out.print("loop中序     ： ");
        btl.traversal(2, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                System.out.print(node.element + "  ");
            }
        });
        System.out.println();
        System.out.print("recursion中序： ");
        btr.traversal(2, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                System.out.print(node.element + "  ");
            }
        });
        System.out.println();
    }

    private static void testPost() {
        System.out.print("recursion后序： ");
        btr.traversal(3, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                System.out.print(node.element + "  ");
            }
        });
        System.out.println();
        System.out.print("loop后序     ： ");
        btl.traversal(3, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                System.out.print(node.element + "  ");
            }
        });
    }

    private static void testLevel() {
        System.out.println();
        System.out.print("recursion层序： ");
        btr.traversal(4, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                System.out.print(node.element + "  ");
            }
        });

        System.out.println();
        System.out.print("loop层序     ： ");
        btl.traversal(4, new BinaryVisitor<Integer>() {
            @Override
            public void visit(Node<Integer> node) {
                System.out.print(node.element + "  ");
            }
        });
    }

    public static void testTraversal() {
        System.out.println();
        testPrev();

        System.out.println();
        testMid();

        System.out.println();
        testPost();

        System.out.println();
        testLevel();
    }

    public static void testMin() {
        System.out.println("phase1.algs.recursion min : " + btr.min());
        System.out.println("phase1.algs.recursion max : " + btr.max());
        System.out.println();
        System.out.println("loop min : " + btl.min());
        System.out.println("loop max : " + btl.max());
    }

    public static void testGet() {
        Node<Integer> x = btl.get(10);
        System.out.println();
        System.out.println("get: ");
        System.out.println("loop: " + x.element);
        Node<Integer> node = btr.get(10);
        System.out.println("recursive: " + node.element);
    }

    public static void testPut() {
        for (int i = 0; i < count; i++) {
//            arr[i] = StdRandom.uniform(count);
        }
//        Stopwatch st = new Stopwatch();
        for (int i = 0; i < repeats; i++) {
            btl = null;
            btl = new BinaryTreeByLoop<>();
            for (Integer integer : arr) {
                btl.putByLoop(integer);
            }
        }
//        System.out.println("loop: " + st.elapsedTime());
//        st = new Stopwatch();
        for (int i = 0; i < repeats; i++) {
            btr = null;
            btr = new BinaryTreeByRecursion<>();
            for (Integer integer : arr) {
                btr.put(integer);
            }
        }
//        System.out.println("phase1.algs.recursion: " + st.elapsedTime());
        /*
        1000000
        loop: 0.97
        phase1.algs.recursion: 0.995

        非递归方式有稍微性能优势
         */
        System.out.println();
        System.out.println(Arrays.toString(arr));
        System.out.println();
        BinaryTrees.println(btl);
        System.out.println(strRepeat(60, '='));
        BinaryTrees.println(btr);

    }
}
