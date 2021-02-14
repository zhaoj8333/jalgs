package phase1.examples;

public class ForLoopSelfIncrement {
    public static void main(String[] args) {
        int a = 1;
        for (int i = 0; i < 10; ++i) {
//            a = a++;
                int tmp = a++;
                a = tmp;
//            a = ++a;
            System.out.println(a);
        }
        System.out.println();
        System.out.println(a);
    }

}
