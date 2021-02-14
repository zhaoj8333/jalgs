package phase1.basic.math;

public class IntCache {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Integer i1 = 3;
        Integer i2 = 3;
        if (i1 == i2) {
            // ==
            System.out.println("i1 == i2");
        } else {
            System.out.println("i1 != i2");
        }

        Integer i3 = 300;
        Integer i4 = 300;
        if (i3 == i4) {
            System.out.println("i3 == i4");
        } else {
            // !=
            System.out.println("i3 != i4");
        }
        System.out.println();
        System.out.println(i3.equals(i4));
    }
}
