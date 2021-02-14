package phase1.practice.bak;

public class DoubleFold {
    public static void main(String[] args) {
        int max = 10000;
        fold(max);
        fold1(max);
    }

    public static void fold(int n) {
        int init  = 1;
        int count = 0;

        while ((init = init << 1) < n) {
            count ++;
        }

        System.out.println(init);
        System.out.println(count);
    }

    public static void fold1(int n) {
        int init  = 1;
        int count = 0;

        while ((init *= 2) < n) {
            count ++;
        }

        System.out.println(init);
        System.out.println(count);
    }
}
