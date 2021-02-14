package phase1.practice.bak;

public class Ex1118 {
    public static void main(String[] args) {
        int a = mystery(2, 25);
        System.out.println("2, 25: " + a);
        int b = mystery(3, 11);
        System.out.println("3, 11: " + a);
        System.out.println();
        int c = mystery1(2, 25);
        System.out.println("2, 25: " + a);
        int d = mystery1(3, 11);
        System.out.println("3, 11: " + a);
    }

    public static int mystery(int a, int b) {
        if (b == 0) {
            return 0;
        }
        if (b % 2 == 0) {
            return mystery(a + a, b / 2);
        }
        return mystery(a + a, b / 2) + a;
    }

    public static int mystery1(int a, int b) {
        if (b == 0) {
            return 1;
        }
        if (b % 2 == 0) {
            return mystery(a * a, b / 2);
        }
        return mystery(a * a, b / 2) * a;
    }
}
