package phase1.practice.bak;

public class NarcissisticNumber {
    public static void main(String[] args) {
        get();
    }

    /**
     * 水仙花数：
     *  一个n位数（n>=3），每个位上的数字n次幂之和等于其本身
     */
    public static void get() {
        int hun, ten, one = 0;

        for (int n = 100; n < 1000; n++) {
            hun = n / 100;
            ten = n / 10 % 10;
            one = n % 10;
            if (hun * hun * hun + ten * ten * ten + one * one * one == n) {
//                System.out.println(hun + " + " + ten + " + " + one);
                System.out.println(n);
            }
        }
    }
}
