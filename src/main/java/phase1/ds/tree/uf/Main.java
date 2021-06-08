package phase1.ds.tree.uf;

import phase1.ds.tree.uf.generic.Student;
import phase1.ds.tree.uf.generic.UnionFindGeneric1;
import phase1.ds.tree.uf.perfm.*;

public class Main {
    public static int count = 700000;
//    private static Stopwatch sw = new Stopwatch();
//    private static void reset() {
//        sw = new Stopwatch();
//    }

    public static void main(String[] args) {
//        qf();
//        qu();  // 快速union：优化接近一半的时间
//        qus(); // size优化： 大幅优化
//        qur(); // rank优化： 小幅优化
//        pc();  // 路径压缩： 小幅优化
//        ps();  // 路径分裂： 小幅优化
        ph();  // 与路径分裂 相差无几
//        gen();
//        testGenPerm();
    }

    private static void testGenPerm() {
        UnionFindGeneric1<Student> ufg = new UnionFindGeneric1<>();
//        reset();
//        System.out.println("quickUnion generics: " + sw.elapsedTime());
    }

    private static void gen() {
        UnionFindGeneric1<Student> ufg = new UnionFindGeneric1<>();
        Student a = new Student("a", 10, true);
        Student b = new Student("b", 11, false);
        Student c = new Student("c", 12, false);
        Student d = new Student("d", 13, false);
        ufg.makeSet(a);
        ufg.makeSet(b);
        ufg.makeSet(c);
        ufg.makeSet(d);
        ufg.union(a, b);
        ufg.union(c, d);

        System.out.println(ufg.isSame(a, b));
        System.out.println(ufg.isSame(a, c));
    }

    private static void ph() {
        UnionFindQU_R_PH ph = new UnionFindQU_R_PH(count);
//        reset();
        for (int i = 0; i < count; i++) {
            ph.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
//        System.out.println("quickUnion path halving: " + sw.elapsedTime());
    }

    private static void ps() {
        UnionFindQU_R_PS ps = new UnionFindQU_R_PS(count);
//        reset();
        for (int i = 0; i < count; i++) {
            ps.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
//        System.out.println("quickUnion path splitting: " + sw.elapsedTime());
    }

    private static void pc() {
        UnionFindQU_R_PC pc = new UnionFindQU_R_PC(count);
//        reset();
        for (int i = 0; i < count; i++) {
            pc.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
        // 0.11比rank稍块
    }

    private static void qus() {
        UnionFindQU qus = new UnionFindQU_S(count);
//        reset();
        for (int i = 0; i < count; i++) {
            qus.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
//        System.out.println("quickUnion_S: " + sw.elapsedTime());
        // 0.023 优化非常明显
    }

    private static void qur() {
        UnionFindQU qur = new UnionFindQU_R(count);
//        reset();
        for (int i = 0; i < count; i++) {
            qur.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
//        System.out.println("quickUnion_R: " + sw.elapsedTime());
        // 0.1
    }

    private static void qf() {
        UnionFindQF qf = new UnionFindQF(count);
//        reset();
        for (int i = 0; i < count; i++) {
            qf.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
//        System.out.println("quickFind: " + sw.elapsedTime());  // 5.3s
    }

    private static void qu() {
        UnionFindQU qu = new UnionFindQU(count);
//        reset();
        for (int i = 0; i < count; i++) {
            qu.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
//        System.out.println("quickUnion: " + sw.elapsedTime()); // 1.8s
    }
}
