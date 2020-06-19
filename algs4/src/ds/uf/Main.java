package ds.uf;

import ds.uf.perfm.*;
import edu.princeton.cs.algs4.Stopwatch;

public class Main {
    public static int count = 70000;
    private static Stopwatch sw = new Stopwatch();
    private static void reset() {
        sw = new Stopwatch();
    }

    public static void main(String[] args) {
        qf();
        qu();  // 快速union：优化接近一半的时间
        qus(); // size优化： 大幅优化
        qur(); // rank优化： 小幅优化
        pc();  // 路径压缩： 小幅优化
        ps();  // 路径分裂： 小幅优化
        ph();  // 与路径分裂 相差无几
    }

    private static void ph() {
        UnionFindQU_R_PH ph = new UnionFindQU_R_PH(count);
        reset();
        for (int i = 0; i < count; i++) {
            ph.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
        System.out.println("quickUnion path halving: " + sw.elapsedTime());
    }

    private static void ps() {
        UnionFindQU_R_PS ps = new UnionFindQU_R_PS(count);
        reset();
        for (int i = 0; i < count; i++) {
            ps.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
        System.out.println("quickUnion path splitting: " + sw.elapsedTime());
    }

    private static void pc() {
        UnionFindQU_R_PC pc = new UnionFindQU_R_PC(count);
        reset();
        for (int i = 0; i < count; i++) {
            pc.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
        System.out.println("quickUnion path compression: " + sw.elapsedTime());
        // 0.11比rank稍块
    }

    private static void qus() {
        UnionFindQU qus = new UnionFindQU_S(count);
        reset();
        for (int i = 0; i < count; i++) {
            qus.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
        System.out.println("quickUnion_S: " + sw.elapsedTime());
        // 0.023 优化非常明显
    }

    private static void qur() {
        UnionFindQU qur = new UnionFindQU_R(count);
        reset();
        for (int i = 0; i < count; i++) {
            qur.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
        System.out.println("quickUnion_R: " + sw.elapsedTime());
        // 0.1
    }

    private static void qf() {
        UnionFindQF qf = new UnionFindQF(count);
        reset();
        for (int i = 0; i < count; i++) {
            qf.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
        System.out.println("quickFind: " + sw.elapsedTime());  // 5.3s
    }

    private static void qu() {
        UnionFindQU qu = new UnionFindQU(count);
        reset();
        for (int i = 0; i < count; i++) {
            qu.union(
                    (int) (Math.random() * count),
                    (int) (Math.random() * count)
            );
        }
        System.out.println("quickUnion: " + sw.elapsedTime()); // 1.8s
    }
}
