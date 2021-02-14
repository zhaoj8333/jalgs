package phase1.practice.bak;

public class Ext1114 {
    public static void main(String[] args) {
//        lg1(1000);
//        lg2(1000);
        lg3(1000);
    }

    public static void lg1(int n) {
        int i = 0;
        for (i = 1; i < n; i *= 2) {

        }

        System.out.println(i / 2);
    }

    public static void lg2(int n) {
        int i = 1;
        while ((i *= 2) < n) {
//            System.out.println(i);
        }

        System.out.println(i / 2);
    }

    public static void lg3(int n) {
        int max = 1;
        for (int i = 0; max < n; i++) {
            max *= 2;
        }
        System.out.println(max / 2);
    }
}
